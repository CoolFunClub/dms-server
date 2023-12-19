package com.coolfunclub.dms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.CreditCard;
import com.coolfunclub.dms.model.Payment;
import com.coolfunclub.dms.model.Purchase;
import com.coolfunclub.dms.repository.CreditCardRepository;
import com.coolfunclub.dms.repository.PaymentRepository;
import com.coolfunclub.dms.repository.PurchaseRepository;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public PaymentService() {
    }

    public ResponseEntity<String> addCashPayment(Payment payment, Long purchaseId) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(purchaseId);
        if (purchaseOptional.isPresent()) {
        Purchase purchase = purchaseOptional.get();
        payment.setPurchase(purchase);
        // Save the Payment entity with the associated Purchase
        paymentRepository.save(payment);
        return ResponseEntity.ok("Payment is added successfully");
    }else{
            return ResponseEntity.badRequest().body("Purchase does not exist.");
        }
    }


    public ResponseEntity<String> addCardPayment(Payment payment, Long purchaseId, Long CCNum) {

        Optional<Purchase> purchaseOptional = purchaseRepository.findById(purchaseId);
       if (purchaseOptional.isPresent()) {

        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(CCNum);
        if(creditCardOptional.isPresent() ){ //check the existence of the Credit
            Purchase purchase = purchaseOptional.get();
            payment.setPurchase(purchase);

            CreditCard myCard = creditCardOptional.get();
            payment.setCard(myCard);

            // Save the Payment entity with the associated Purchase
            paymentRepository.save(payment);
            return ResponseEntity.ok("Payment is added successfully");

            }else{
            return ResponseEntity.badRequest().body("Credit/Depit card does not exist.");
            }
        }else{
            return ResponseEntity.badRequest().body("Purchase does not exist.");
        }
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long paymentID) {
        return paymentRepository.findById(paymentID).orElse(null);
    }

    public void deletePaymentById(Long paymentID) {
        paymentRepository.deleteById(paymentID);
    }

}
