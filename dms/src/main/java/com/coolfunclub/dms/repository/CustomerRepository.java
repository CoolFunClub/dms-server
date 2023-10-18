package com.coolfunclub.dms.repository;

import org.springframework.data.repository.CrudRepository;

import com.coolfunclub.dms.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    
}
