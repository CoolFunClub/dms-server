package com.coolfunclub.dms.web.controller;

import java.util.Date;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coolfunclub.dms.model.Account;
import com.coolfunclub.dms.model.Customer;
import com.coolfunclub.dms.model.Manager;
import com.coolfunclub.dms.model.SalesRep;
import com.coolfunclub.dms.service.CustomerService;
import com.coolfunclub.dms.service.ManagerService;
import com.coolfunclub.dms.service.SalesRepService;
import com.coolfunclub.dtos.AccountDTO;

@RestController
public class AccountController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private SalesRepService salesRepService;
    //each of the post methods delegates account creation to a generic method
    @PostMapping("/account/customer/{dl}")
    public ResponseEntity<?> createCustomerAccount(@PathVariable String dl, @RequestBody AccountDTO accountDto) {
        // from the customer service obj, let the generic method know it will need to call that method
        return createAccount(accountDto, dl, customerService::associateAccountToCustomer);
    }
    @PostMapping("/account/manager/{ssn}")
    public ResponseEntity<?> createManagerAccount(@PathVariable int ssn, @RequestBody AccountDTO accountDto) {
        // from the manager service obj, let the generic method know it will need to call that method
        return createAccount(accountDto, ssn, managerService::associateAccountToManager);
    }
    @PostMapping("/account/salesRep/{ssn}")
    public ResponseEntity<?> createSalesRepAccount(@PathVariable int ssn, @RequestBody AccountDTO accountDto) {
        // from the salesrep service obj, let the generic method know it will need to call that method
        return createAccount(accountDto, ssn, salesRepService::associateAccountToSalesRep);
    }
    // each of the associate methods in the service classes
    private <T, ID> ResponseEntity<?> createAccount(AccountDTO accountDto, ID id, BiFunction<ID, Account, T> serviceMethod) { 
        // it infers T from the service object we pass to it
        // and it infers the diff type of ID (we have two types we use so far)
        System.out.println(accountDto.toString());
        try {
            Account account = new Account();
            // set some of these server side
            account.setCloseDate(null);
            account.setOpenDate(new Date());
            account.setStatus("active");
            account.setPw(accountDto.getPw());
            account.setUserName(accountDto.getUserName());

            T updatedEntity = serviceMethod.apply(id, account);

            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
