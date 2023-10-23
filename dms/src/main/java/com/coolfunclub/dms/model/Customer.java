package com.coolfunclub.dms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name= "customers")
public class Customer extends Person{

    @Column(name = "mDriverLicenseID")
    @Id
    private String mDriverLicenseID;
    @OneToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    // Getters and setters for driver license ID
    public String getDriverLicenseID() {
        return mDriverLicenseID;
    }

    public void setDriverLicenseID(String mDriverLicenseID) {
        this.mDriverLicenseID = mDriverLicenseID;
    }

    public void setAccount(Account account){
        this.account=account;
    }
    public Account getAccount(){
        return account;
    }


}
