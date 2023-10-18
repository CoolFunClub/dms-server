package com.coolfunclub.dms.model;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Inheritance;
//import jakarta.persistence.InheritanceType;
//import jakarta.persistence.PrePersist;
//@Inheritance(strategy = InheritanceType.JOINED)
//@Entity
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

import java.util.UUID;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String  mPersonID; //PK

    protected String mFirstName;
    protected String mLastName;
    protected String mDateBirth;
    protected String mGender;
    protected String mPhone;
    protected String mEmail;
    protected String mAddress;

    public Person(String firstName, String lastName,String dob, String gender, String phone, String email, String address){
        //this.mPersonID = personID;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mDateBirth = dob;
        this.mGender = gender;
        this.mPhone = phone;
        this.mEmail = email;
        this.mAddress = address;
    }

    public Person(){ //Default Constructor
    }

    @PrePersist
    public void generateId() {
        if (this.mPersonID == null || this.mPersonID.isEmpty()) {
            this.mPersonID = UUID.randomUUID().toString();
        }
    }


/*  // Set methods
       public void setSSN(String personID) {
        this.mPersonID = personID;
    }   */

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setDateBirth(String mDateBirth) {
        this.mDateBirth = mDateBirth;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    // Get methods
    public String getpersonID() {
        return mPersonID;
    }

    public String getFirstName() {
        return mFirstName;
    }


    public String getLastName() {
        return mLastName;
    }

    public String getDateBirth() {
        return mDateBirth;
    }

    public String getGender() {
        return mGender;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getAddress() {
        return mAddress;
    }
}
