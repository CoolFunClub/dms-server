package com.coolfunclub.dms.model;

import jakarta.persistence.Entity;

@Entity
public class SalesRep extends Person {

    private int mSSN;

    //Getters and Setters
    public int getSSN(){
        return mSSN;
    }

    public void setSSN(int mSSN){
        this.mSSN = mSSN;
    }
}
