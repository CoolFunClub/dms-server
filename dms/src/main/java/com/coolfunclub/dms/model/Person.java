package com.coolfunclub.dms.model;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    private String mPersonID; //PK
    private String mFirstName;
    private String mLastName;
    private String mDateBirth;
    private String mGender;
    private String mPhone;
    private String mEmail;
    private String mAddress;

    public Person(String personID, String firstName, String lastName,String dob, String gender, String phone, String email, String address){
        this.mPersonID = personID;
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

       // Set methods
       public void setSSN(String personID) {
        this.mPersonID = personID;
    }
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
