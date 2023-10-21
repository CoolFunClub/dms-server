package com.coolfunclub.dms.model;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

 /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long  mPersonID; //PK      */

    protected String mFirstName;
    protected String mLastName;
    protected String mDateBirth;
    protected String mGender;
    protected int mPhone;
    protected String mEmail;
    protected String mAddress;

/*
    public void setId(Long mPersonID){
        this.mPersonID = mPersonID;
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

    public void setPhone(int mPhone) {
        this.mPhone = mPhone;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

 /*   // Get methods
    public Long getpersonID() {
        return mPersonID;
    } */

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

    public int getPhone() {
        return mPhone;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getAddress() {
        return mAddress;
    }
}
