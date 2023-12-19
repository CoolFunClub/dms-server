package com.coolfunclub.dms.model;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class Finance extends Purchase{

    private double interestRate;
    private double downPay;
    private double term;

    public Finance(){

    }

    public Finance(Date purDate,double tax,double registrationFee, double interestRate, double downPay, double term) {
        super(purDate, tax, registrationFee);
        this.interestRate = interestRate;
        this.downPay = downPay;
        this.term = term;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getDownPay() {
        return downPay;
    }

    public void setDownPay(double downPay) {
        this.downPay = downPay;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

}
