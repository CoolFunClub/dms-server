package com.coolfunclub.dms.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.ToString;

@ToString
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    private Date paymentDate;
    private double paymentAmount;
    private String paymentMethod;

/*     @ManyToOne
    @JoinColumn(name = "purID",referencedColumnName ="purID")
    private Purchase purchase; */
    @ManyToOne
    @JoinColumn(name = "fullPurID",referencedColumnName ="fullPurID")
    private FullPurchase fullPurchase;


    public FullPurchase getFullPurchase() {
        return fullPurchase;
    }

    public void setFullPurchase(FullPurchase fullPurchase) {
        this.fullPurchase = fullPurchase;
    }

    @ManyToOne
    @JoinColumn(name = "financeID",referencedColumnName ="financeID")
    private Finance finance;


    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }


    public CreditCard getCreditCard(){
        return creditCard;
    }

    public void setCard(CreditCard myCard) {
        this.creditCard = myCard;
    }

    @ManyToOne
    @JoinColumn(name = "creditCardNum")
    private CreditCard creditCard;


    public Payment(){

    }

    public Payment(Date paymentDate, double paymentAmount, String paymentMethod) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
    }

    //Getters and Setters
    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


}
