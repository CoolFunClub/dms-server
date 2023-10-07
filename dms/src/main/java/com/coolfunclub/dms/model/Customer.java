package com.coolfunclub.dms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Customer extends Person{

    @Id
    private String mDriverLicenseID;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    // Constructor
    public Customer(String personID, String firstName, String lastName, String dob, String gender,
                    String phone, String email, String address, String driverLicenseID) {
        super(personID, firstName, lastName, dob, gender, phone, email, address);
        this.mDriverLicenseID = driverLicenseID;
    }

    // Default constructor
    public Customer() {
        super(); // Calls the default constructor of the parent class (Person)
    }

    // Getters and setters for driver license ID
    public String getDriverLicenseID() {
        return mDriverLicenseID;
    }

    public void setDriverLicenseID(String driverLicenseID) {
        this.mDriverLicenseID = driverLicenseID;
    }

    // Method to set customer information-**
    public void setCustomerInformation(String personID, String firstName, String lastName, String dob, String gender,
                                        String phone, String email, String address, String driverLicenseID) {
        super.setSSN(personID);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setDateBirth(dob);
        super.setGender(gender);
        super.setPhone(phone);
        super.setEmail(email);
        super.setAddress(address);
        this.mDriverLicenseID = driverLicenseID;
    }
}
