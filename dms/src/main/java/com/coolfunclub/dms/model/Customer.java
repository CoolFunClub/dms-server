package com.coolfunclub.dms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name= "customers")
public class Customer extends Person{

    private String mDriverLicenseID;

    // Getters and setters for driver license ID
    public String getDriverLicenseID() {
        return mDriverLicenseID;
    }

    public void setDriverLicenseID(String driverLicenseID) {
        this.mDriverLicenseID = driverLicenseID;
    }

}
