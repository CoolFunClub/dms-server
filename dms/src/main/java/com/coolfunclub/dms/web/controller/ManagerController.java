package com.coolfunclub.dms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coolfunclub.dms.model.Manager;
import com.coolfunclub.dms.service.ManagerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("cfc/")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping(value = "addmanagers")
    public ResponseEntity<String> addManager(@RequestBody Manager manager){
        return managerService.addManager(manager);
    }

    @GetMapping(value = "managers")
    public List<Manager> getManagers(){
        return managerService.getAllManagers();
    }

    @GetMapping(value = "/managers/{ssn}")
    public Manager getManagerById(@PathVariable("ssn") int ssn ){
        return managerService.getManagerById(ssn);
    }

    @DeleteMapping(value = "/managers/{ssn}")
    public void deleteManager(@PathVariable("ssn") int ssn ){
        managerService.deleteManager(ssn);
    }

    @PutMapping(value = "/managers/{ssn}")
    public void updateManager(@PathVariable int ssn, @RequestBody Manager manager){
        manager.setSSN(ssn);
        managerService.updateManager(manager);
    }

    @PostMapping("manager/{ssn}/associate-account")
    public ResponseEntity<Manager> associateAccount(@PathVariable int ssn, @RequestBody AccountDTO accountDto) {
        System.out.println(accountDto.toString());
        try {
            // Transform DTO to Entity
            Account account = new Account();
            account.setCloseDate(accountDto.getCloseDate());
            account.setOpenDate(accountDto.getOpenDate());
            account.setStatus(accountDto.getStatus());

            // Call the service to perform the association
            Manager updatedManager = managerService.associateAccountToManager(ssn, account);

            return new ResponseEntity<>(updatedManager, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
