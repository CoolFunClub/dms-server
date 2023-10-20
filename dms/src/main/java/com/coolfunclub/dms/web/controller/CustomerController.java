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
import org.springframework.web.bind.annotation.RequestMapping;
import com.coolfunclub.dms.model.Customer;
import com.coolfunclub.dms.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("cfc/")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @PostMapping(value = "addcustomers")
  public void addCustomer(@RequestBody Customer customer ){
     customerService.addCustomer(customer);
}

@GetMapping(value = "customers")
public List<Customer> getCustomers(){
    return customerService.getAllCustomers();
}

@GetMapping(value = "/customers/{id}")
public Customer getCustomerById(@PathVariable("id") Long id ){
    return customerService.getCustomerById(id);
}

@DeleteMapping(value = "/customers/{id}")
public void deleteCustomerById(@PathVariable("id") Long id ){
    customerService.deleteCustomer(id);
}

//Note:
// Don't pass the PersonID with the JSON attriputes, but instead put it in the link path e.g. localhost:8080/cfc/customers/95
@PutMapping(value = "/customers/{id}")
public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
    customer.setId(id);
    customerService.updateCustomer(customer);
}
}
