package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.Customer;
import com.coolfunclub.dms.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    //Constructor
    public CustomerService(){

    }

    public void addCustomer(Customer customer) {
            customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = new LinkedList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public void deleteCustomer(Long personID) {
        customerRepository.deleteById(personID);
    }

    public Customer getCustomerById(Long personID) {
        return customerRepository.findById(personID).orElse(null);
    }

    public void updateCustomer(Customer updatedCustomer) {
            customerRepository.save(updatedCustomer);
     }
}

