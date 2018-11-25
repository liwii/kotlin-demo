package com.example.kotlindemo.application.input

import javax.validation.constraints.NotBlank

class CommentInput {
    @NotBlank
    var content: String? = null
}