package com.coolfunclub.dms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coolfunclub.dms.model.Account;
import com.coolfunclub.dms.model.Finance;
import com.coolfunclub.dms.repository.AccountRepository;
import com.coolfunclub.dms.repository.FinanceRepository;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepository financeRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<String> addFinance(Finance finance, Long accountId){
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if(accountOptional.isPresent()){
            Account myAccount = accountOptional.get();
            finance.setAccount(myAccount);
            financeRepository.save(finance);
            return ResponseEntity.ok("Finance is added successfully");
        }else{
            return ResponseEntity.badRequest().body("Can't add the Finance because No Account associated with the provided Account Number.");
        }
    }

    public List<Finance> getAllFinance() {
        return financeRepository.findAll();
    }

    public Finance getFinanceById(Long purID) {
        return financeRepository.findById(purID).orElse(null);
    }

    public void deleteFinanceById(Long purID) {
        financeRepository.deleteById(purID);
    }
}
