package com.coolfunclub.dms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class SalesRep extends Person {
    @Id
    @Column(name = "mSSN")
    private int mSSN;
    @OneToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    @OneToOne(optional = true)
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
