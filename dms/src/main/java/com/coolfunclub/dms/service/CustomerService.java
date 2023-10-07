package com.coolfunclub.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolfunclub.dms.model.Customer;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

        public Customer createCustomer(Customer customer) {
            return customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }


    public Customer updateCustomer(String customerId, Customer updatedCustomer) {

        // Retrieve the existing customer from the database
        Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
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
            throw new EntityNotFoundException("Customer with ID " + customerId + " not found.");
        }
    }

}
