package com.coolfunclub.dms.model;

import java.util.Date;
import jakarta.persistence.Entity;

@Entity
public class FullPurchase extends Purchase {

    private double totalPaid;

    public FullPurchase(){

    }

    public FullPurchase(Date purDate, double tax, double registrationFee, double totalPaid) {
        super(purDate, tax, registrationFee);
        this.totalPaid = totalPaid;
    }

    public FullPurchase(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

}
