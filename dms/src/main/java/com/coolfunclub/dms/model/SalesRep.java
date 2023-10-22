package com.coolfunclub.dms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class SalesRep extends Person {
    @Column(name = "mSSN")
=======
import jakarta.persistence.Id;

@Entity
public class SalesRep extends Person {

    @Id
>>>>>>> c63527f (- Adjusting Inheritance by deleting the PersonID PK and assign driverLic, SSN, SSN to be the PK of the child classes- Customer, Manager, SalesRep respectively)
    private int mSSN;
    @OneToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    //Getters and Setters
    public int getSSN(){
        return mSSN;
    }

    public void setSSN(int mSSN){
        this.mSSN = mSSN;
    }
    public void setAccount(Account account){
        this.account=account;
    }
    public Account getAccount(){
        return account;
    }
}
