package com.carloscardona.microservices.services.customer.dao;

import org.springframework.data.repository.CrudRepository;

import com.carloscardona.microservices.services.customer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}