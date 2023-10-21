package com.coolfunclub.dms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "managers")
public class Manager extends Person {

    private int mSSN;

    //Getters and Setters
    public int getSSN(){
        return mSSN;
    }

    public void setSSN(int mSSN){
        this.mSSN = mSSN;
    }
}
