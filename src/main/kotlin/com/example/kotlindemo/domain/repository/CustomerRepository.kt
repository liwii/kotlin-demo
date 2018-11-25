package com.example.kotlindemo.domain.repository

import com.example.kotlindemo.domain.model.Customer;

interface CustomerRepository {
    fun findAll() : List<Customer>
    fun findById(id: Int) : Customer?
    fun create(customer: Customer) : Int
    fun update(customer: Customer)
    fun delete(customer: Customer)
}