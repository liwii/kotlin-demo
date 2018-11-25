package com.example.kotlindemo.application.service

import com.example.kotlindemo.domain.model.Comment
import com.example.kotlindemo.domain.repository.CommentRepository
import com.example.kotlindemo.application.input.CommentInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentApplicationService (
        private val commentRepository: CommentRepository
){
    fun selectAllByCustomerId(customerId: Int) : List<Comment>{
        return commentRepository.selectAllByCustomerId(customerId)
    }

    fun create(commentInput: CommentInput, customerId: Int) : Int{
        val comment = Comment(
                content = commentInput.content!!,
                customerId  = customerId
        )
        return commentRepository.create(comment)
    }
}