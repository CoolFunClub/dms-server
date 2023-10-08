package com.coolfunclub.dms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mPersonID")
public class SalesRep extends Person {

    public SalesRep(String firstName, String lastName,String dob, String gender, String phone, String email, String address){
        super(firstName, lastName, dob, gender, phone, email, address);
    }
    
}
