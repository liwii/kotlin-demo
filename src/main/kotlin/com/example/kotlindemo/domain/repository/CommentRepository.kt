package com.example.kotlindemo.domain.repository

import com.example.kotlindemo.domain.model.Comment

interface CommentRepository {
    fun selectAllByCustomerId(customerId: Int): List<Comment>
    fun create(comment: Comment): Int
}