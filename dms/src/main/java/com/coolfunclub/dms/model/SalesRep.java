package com.coolfunclub.dms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class SalesRep extends Person {
    @Column(name = "mSSN")
    private int mSSN;

    //Getters and Setters
    public int getSSN(){
        return mSSN;
    }

    public void setSSN(int mSSN){
        this.mSSN = mSSN;
    }
}
