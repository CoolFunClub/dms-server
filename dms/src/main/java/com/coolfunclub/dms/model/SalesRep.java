package com.coolfunclub.dms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SalesRep extends Person {

    @Id
    private int mSSN;

    //Getters and Setters
    public int getSSN(){
        return mSSN;
    }

    public void setSSN(int mSSN){
        this.mSSN = mSSN;
    }
}
