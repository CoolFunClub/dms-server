package com.coolfunclub.dms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void addManager(@RequestBody Manager manager ){
        managerService.addManager(manager);
    }

    @GetMapping(value = "managers")
    public List<Manager> getManagers(){
        return managerService.getAllManagers();
    }

    @GetMapping(value = "/managers/{id}")
    public Manager getManagerById(@PathVariable("id") Long id ){
        return managerService.getManagerById(id);
    }

    @DeleteMapping(value = "/managers/{id}")
    public void deleteManager(@PathVariable("id") Long id ){
        managerService.deleteManager(id);
    }

    //Note:
    // Don't pass the PersonID with the JSON attriputes, but instead put it in the link path e.g. localhost:8080/cfc/managers/95
    @PutMapping(value = "/managers/{id}")
    public void updateManager(@PathVariable Long id, @RequestBody Manager manager){
        manager.setId(id);
        managerService.updateManager(manager);
    }
}
