package com.example.kotlindemo.application.controller.web

import com.example.kotlindemo.application.input.CommentInput
import com.example.kotlindemo.application.input.CustomerInput
import com.example.kotlindemo.application.service.CommentApplicationService
import com.example.kotlindemo.application.service.CustomerApplicationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/customers")
class CustomerController (
        private val customerApplicationService: CustomerApplicationService,
        private val commentApplicationService: CommentApplicationService
){
    @GetMapping("")
    fun index(model: Model): String {
        val customers = customerApplicationService.findAll()
        model.addAttribute("customers", customers)
        return ("customers/index")
    }

    @GetMapping("{id}")
    fun show(
            @PathVariable id: Int,
            model: Model,
            commentInput: CommentInput
    ): String {
        val customer = customerApplicationService.findById(id)
        val comments = commentApplicationService.selectAllByCustomerId(id)
        model.addAttribute("customer", customer)
        model.addAttribute("comments", comments)
        return "customers/show"
    }

    @GetMapping("new")
    fun new(customerInput: CustomerInput) : String{
        return "customers/new"
    }

    @PostMapping("")
    fun create(
            @Validated customerInput: CustomerInput,
            bindingResult: BindingResult
    ) : String {

        if(bindingResult.hasErrors()) {
            return "customers/new"
        }
        val id = customerApplicationService.create(customerInput)
        return "redirect:/customers/${id}"
    }

    @GetMapping("{id}/edit")
    fun edit(
            @PathVariable id: Int,
            customerInput: CustomerInput
    ): String {
        val customer = customerApplicationService.findById(id)
        customerInput.name = customer.name
        customerInput.email = customer.email

        return "customers/edit"
    }

    @PatchMapping("{id}")
    fun update(
            @PathVariable id: Int,
            @Validated customerInput: CustomerInput,
            bindingResult: BindingResult
    ): String {
        if(bindingResult.hasErrors()) {
            return "customers/edit"

        }
        customerApplicationService.update(id, customerInput)

        return "redirect:/customers"
    }

    @DeleteMapping("{id}")
    fun delete(
            @PathVariable id: Int
    ) : String {
        customerApplicationService.delete(id)
        return "redirect:/customers"
    }

}