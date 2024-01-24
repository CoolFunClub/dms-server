package com.coolfunclub.dms.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


/* @Entity */
/* @Inheritance(strategy = InheritanceType.JOINED) */
public abstract class Purchase {

/*     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purID; */


    private Date purDate;
    private double tax;
    private double registrationFee;

    public Purchase(){

    }

   public Purchase(Date purDate, double tax, double registrationFee) {
        this.purDate = purDate;
        this.tax = tax;
        this.registrationFee = registrationFee;
    }

/*     public Long getPurID() {
        return purID;
    }

    public void setPurID(Long purID) {
        this.purID = purID;
    } */



    public Date getPurDate() {
        return purDate;
    }

    public void setPurDate(Date purDate) {
        this.purDate = purDate;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

}
