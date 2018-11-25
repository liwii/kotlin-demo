package com.example.kotlindemo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model

@Controller
class HomeController {
    @GetMapping("")
    fun index(model: Model): String {
        model.addAttribute("message", "Hello! Kotlin")
        return "home"
    }
}