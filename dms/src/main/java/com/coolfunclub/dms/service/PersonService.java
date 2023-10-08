package com.coolfunclub.dms.service;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolfunclub.dms.model.Customer;
import com.coolfunclub.dms.model.Person;
import com.coolfunclub.dms.model.SalesRep;
import com.coolfunclub.dms.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository; //PersonRepository interface

    
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(String personId) {
        personRepository.deleteById(personId);
    }

    public Person getPersonById(String personId) {
        return personRepository.findById(personId).orElse(null);
    }

        public Person updatePerson(Person updatedPerson) {
        // Add business logic for updating an existing person if needed
        Person existingPerson = personRepository.findById(updatedPerson.getpersonID()).orElse(null);
        if (existingPerson != null) {
            // Update common properties
            existingPerson.setFirstName(updatedPerson.getFirstName());
            existingPerson.setLastName(updatedPerson.getLastName());
            existingPerson.setDateBirth(updatedPerson.getDateBirth());
            existingPerson.setGender(updatedPerson.getGender());
            existingPerson.setPhone(updatedPerson.getPhone());
            existingPerson.setEmail(updatedPerson.getEmail());
            existingPerson.setAddress(updatedPerson.getAddress());

            // Handle specific properties for subclasses
            if (existingPerson instanceof Manager && updatedPerson instanceof Manager) {
                // Update Manager specific properties
                // ((Manager) existingPerson).setManagerSpecificProperty(((Manager) updatedPerson).getManagerSpecificProperty());
            } else if (existingPerson instanceof Customer && updatedPerson instanceof Customer) {
                // Cast and update Customer specific properties
                Customer existingCustomer = (Customer) existingPerson;
                Customer updatedCustomer = (Customer) updatedPerson;

                //Update Customer specific properties
                existingCustomer.setDriverLicenseID(updatedCustomer.getDriverLicenseID());

                // Save the updated Customer entity
                return personRepository.save(existingCustomer);

            } else if (existingPerson instanceof SalesRep && updatedPerson instanceof SalesRep) {
                // Update SalesRep specific properties
                // ((SalesRep) existingPerson).setSalesRepSpecificProperty(((SalesRep) updatedPerson).getSalesRepSpecificProperty());
            }
            return personRepository.save(existingPerson);
        } else {
            return null; // Person not found
        }
    }
}
