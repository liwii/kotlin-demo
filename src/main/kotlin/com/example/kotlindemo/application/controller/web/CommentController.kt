package com.example.kotlindemo.application.controller.web

import com.example.kotlindemo.application.input.CommentInput
import com.example.kotlindemo.application.service.CommentApplicationService
import com.example.kotlindemo.application.service.CustomerApplicationService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage

@Controller
class CommentController (
        private val customerApplicationService: CustomerApplicationService,
        private val commentApplicationService: CommentApplicationService
){
    @Autowired
    lateinit var mailSender : MailSender

    @PostMapping("customers/{customerId}/comments")
    fun create(
            @Validated commentInput: CommentInput,
            bindingResult: BindingResult,
            @PathVariable customerId: Int
    ) : String {
        if(bindingResult.hasErrors()) {
            return "redirect:/customers/${customerId}"
        }
        commentApplicationService.create(commentInput, customerId)
        val customer = customerApplicationService.findById(customerId)
        commentInput.content?.let {
            GlobalScope.launch{
                sendMail(customer.email, it)
            }
        }

        return "redirect:/customers/${customerId}"
    }

    private fun sendMail(toMail: String, content: String) {
        var message = SimpleMailMessage().apply {
            setFrom( "liukoki@gmail.com")
            setTo(toMail)
            setSubject("コメントが届いています")
            setText(content)
        }
        try {
            mailSender.send(message)
        } catch (e : MailException) {
            // do not something
        }
    }
}