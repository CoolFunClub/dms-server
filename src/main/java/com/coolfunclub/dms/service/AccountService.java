package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EntityAlreadyExistsException;
import com.coolfunclub.dms.repository.AccountRepository;
import com.coolfunclub.dms.model.Account;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllaccounts(){
        List<Account> accounts=new LinkedList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }
    public Account addAccount(Account account){
        System.out.println(account.toString());
        if(account.getId()!=null){
            Optional<Account> existingAccount = accountRepository.findById(account.getId());
            if (existingAccount.isPresent()) {
                // Handle the case where the account already exists
                throw new EntityAlreadyExistsException("account with ID " + account.getId() + " already exists.");
            }
        }
        String hasedPassword=BCrypt.hashpw(account.getPw(), BCrypt.gensalt());
        account.setPw(hasedPassword);
        return accountRepository.save(account);
    }
    public Account getAccount(Long id){
        return accountRepository.findById(id).get();
    }
    public void updateAccount(Account newAccountData){
        //accountRepository.save(account);

        Long id = newAccountData.getId(); // Assuming the VIN is what you're using to identify accounts
        try {
            Account existingAccount = getAccount(id);
            existingAccount.setStatus(newAccountData.getStatus());
            existingAccount.setCloseDate(newAccountData.getCloseDate());
            existingAccount.setOpenDate(newAccountData.getOpenDate());
            accountRepository.save(existingAccount);
        }
        catch(NoSuchElementException e){
            return;
        }
    }
    public void deleteAccount(Long id){
        try{
            Account account= getAccount(id);
            if(account!=null){
                accountRepository.delete(account);
            }
        }catch (NoSuchElementException e){
            return;
        }

    }



}
