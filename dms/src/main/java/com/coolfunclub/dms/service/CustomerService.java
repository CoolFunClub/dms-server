package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolfunclub.dms.model.Customer;
import com.coolfunclub.dms.model.Manager;
import com.coolfunclub.dms.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

     public List<Customer> getAllCustomers(){
        List<Customer> Customers = new LinkedList<>();
        customerRepository.findAll().forEach(Customers::add);
        return Customers;
    }

        public Customer createCustomer(Customer customer) {
            return customerRepository.save(customer);
    }

    public void deleteCustomer(String personID) {
        customerRepository.deleteById(personID);
    }

    public Customer getCustomerById(String personID) {
        return customerRepository.findById(personID).orElse(null);
    }


    public Customer updateCustomer(Customer updatedCustomer) {

        String personID = updatedCustomer.getpersonID();

        // Retrieve the existing customer from the database
        Customer existingCustomer = customerRepository.findById(personID).orElse(null);
        if (existingCustomer != null) {
            // Update common properties from the updatedCustomer
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setDateBirth(updatedCustomer.getDateBirth());
            existingCustomer.setGender(updatedCustomer.getGender());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAddress(updatedCustomer.getAddress());

            // Update customer-specific properties
            existingCustomer.setDriverLicenseID(updatedCustomer.getDriverLicenseID());

            // Save the updated customer entity
            return customerRepository.save(existingCustomer);
        } else {
            // Handle the case where the customer is not found in the database
            throw new EntityNotFoundException("Customer with ID " + personID + " not found.");
        }
    }

}
