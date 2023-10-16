package com.coolfunclub.dms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.coolfunclub.dms.model.Customer;
import com.coolfunclub.dms.model.Manager;
import com.coolfunclub.dms.service.CustomerService;
import com.coolfunclub.dms.service.ManagerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") String id ){
        return customerService.getCustomerById(id);
    }

    @PostMapping(value = "/customers")
    public void createCustomer(@RequestBody Customer customer ){
        customerService.createCustomer(customer);
    }

    @PutMapping(value = "/customers")
    public void updateCustomer(@RequestBody Customer customer ){
        customerService.updateCustomer(customer);
    }


}
