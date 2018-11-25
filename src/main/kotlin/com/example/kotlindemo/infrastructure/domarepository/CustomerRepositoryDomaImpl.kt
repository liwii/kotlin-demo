package com.example.kotlindemo.infrastructure.domarepository

import com.example.kotlindemo.domain.model.Customer;
import com.example.kotlindemo.domain.repository.CustomerRepository;
import com.example.kotlindemo.infrastructure.doma.entity.CustomerDomaEntity;
import com.example.kotlindemo.infrastructure.doma.dao.CustomerDomaDao;
import org.springframework.stereotype.Repository;

@Repository
class CustomerRepositoryDomaImpl(
        private val customerDomaDao: CustomerDomaDao
) : CustomerRepository {
    override fun findAll(): List<Customer> {
        return customerDomaDao.selectAll().map{ _mapToModel(it)}
    }

    override fun findById(id: Int): Customer? {
         return customerDomaDao.selectById(id)?.let { _mapToModel(it) }
    }

    override fun create(customer: Customer) : Int {
        val domaEntity = _mapToDomaEntity(customer)
        customerDomaDao.insert(domaEntity)
        return domaEntity.id
    }

    override fun update(customer: Customer) {
        val domaEntity = _mapToDomaEntity(customer)
        customerDomaDao.update(domaEntity)
    }

    override fun delete(customer: Customer) {
        val domaEntity = _mapToDomaEntity(customer)
        customerDomaDao.delete(domaEntity)
    }

    private fun _mapToModel(domaEntity: CustomerDomaEntity) : Customer {
        return Customer(
                id = domaEntity.id,
                name = domaEntity.name,
                email = domaEntity.email
        )
    }

    private fun _mapToDomaEntity(customer: Customer) : CustomerDomaEntity {
        return CustomerDomaEntity().also {
            it.email = customer.email
            it.id = customer.id
            it.name = customer.name
        }
    }
}