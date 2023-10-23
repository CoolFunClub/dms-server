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

@CrossOrigin(origins = {"http://localhost:3000", "http://18.117.76.202:3000"})
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
}
