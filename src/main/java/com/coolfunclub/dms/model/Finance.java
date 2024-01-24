package com.coolfunclub.dms.model;

import java.sql.Date;
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
public class Finance extends Purchase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long financeId;

    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName ="id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_vin",referencedColumnName ="vin")
    private Car car;

    @OneToMany(mappedBy = "finance", cascade = CascadeType.ALL)
    private List<Payment> payments;


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

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

}
