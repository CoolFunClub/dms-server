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

import com.coolfunclub.dms.model.Manager;
import com.coolfunclub.dms.service.ManagerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping(value = "/managers")
    public List<Manager> getManagers(){
        return managerService.getAllManagers();
    }
    @GetMapping(value = "/managers/{id}")
    public Manager getManager(@PathVariable("id") String id ){
        return managerService.getManager(id);
    }

    @PostMapping(value = "/managers")
    public void createManager(@RequestBody Manager manager ){
        managerService.addManager(manager);
    }
    
    @PutMapping(value = "/managers")
    public void updateManager(@RequestBody Manager manager ){
        managerService.updateManager(manager);
    }

    @DeleteMapping(value = "/managers/{id}")
    public void deleteManager(@PathVariable("id") String id ){
        managerService.deleteManager(id);
    }
    
}
