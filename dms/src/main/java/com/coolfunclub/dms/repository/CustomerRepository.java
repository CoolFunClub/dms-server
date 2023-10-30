package com.coolfunclub.dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coolfunclub.dms.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

// boolean exsitsByDriverLic(String driverLicenseID);

}
