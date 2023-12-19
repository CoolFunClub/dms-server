package com.coolfunclub.dms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.Account;
import com.coolfunclub.dms.model.FullPurchase;
import com.coolfunclub.dms.repository.AccountRepository;
import com.coolfunclub.dms.repository.FullPurchaseRepository;

@Service
public class FullPurchaseService {

    @Autowired
    private FullPurchaseRepository fullPurchaseRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<String> addFullPurchase(FullPurchase fullPurchase, Long accountId){
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if(accountOptional.isPresent()){
            Account myAccount = accountOptional.get();
            fullPurchase.setAccount(myAccount);
            fullPurchaseRepository.save(fullPurchase);
            return ResponseEntity.ok("FullPurchase is added successfully");
        }else{
            return ResponseEntity.badRequest().body("Can't add the FullPurchase because No Account associated with the provided Account Number.");
        }
    }

    public List<FullPurchase> getAllFullPurchases() {
        return fullPurchaseRepository.findAll();
    }

    public FullPurchase getFullPurchaseById(Long purID) {
        return fullPurchaseRepository.findById(purID).orElse(null);
    }

    public void deleteFullPurchaseById(Long purID) {
        fullPurchaseRepository.deleteById(purID);
    }

}
