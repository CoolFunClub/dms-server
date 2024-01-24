package com.coolfunclub.dms.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class FullPurchase extends Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fullPurId;

    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName ="id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_vin",referencedColumnName ="vin")
    private Car car;

    @OneToMany(mappedBy = "fullPurchase", cascade = CascadeType.ALL)
    private List<Payment> payments;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getFullPurId() {
        return fullPurId;
    }

    public void setFullPurchaseId(Long fullPurId) {
        this.fullPurId = fullPurId;
    }
}
