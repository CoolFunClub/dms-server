package com.coolfunclub.dms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
=======
import jakarta.persistence.Id;
>>>>>>> c63527f (- Adjusting Inheritance by deleting the PersonID PK and assign driverLic, SSN, SSN to be the PK of the child classes- Customer, Manager, SalesRep respectively)
import jakarta.persistence.Table;


@Entity
@Table(name= "customers")
public class Customer extends Person{
<<<<<<< HEAD
    @Column(name = "mDriverLicenseID")
=======

    @Id
>>>>>>> c63527f (- Adjusting Inheritance by deleting the PersonID PK and assign driverLic, SSN, SSN to be the PK of the child classes- Customer, Manager, SalesRep respectively)
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
