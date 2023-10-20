package com.coolfunclub.dms.web.controller;

import com.coolfunclub.dms.model.SalesRep;
import com.coolfunclub.dms.service.SalesRepService;

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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("cfc/")
public class SalesRepController {

    @Autowired
    SalesRepService salesRepService;

    @PostMapping(value = "addrep")
    public void addSalesRep(@RequestBody SalesRep salesRep ){
        salesRepService.addSalesRep(salesRep);
    }

    @GetMapping(value = "rep")
    public List<SalesRep> getSalesReps(){
        return salesRepService.getAllSalesReps();
    }

    @GetMapping(value = "/rep/{id}")
    public SalesRep getSalesRep(@PathVariable("id") Long id ){
        return salesRepService.getSalesRepById(id);
    }

    @DeleteMapping(value = "/rep/{id}")
    public void deleteManagerById(@PathVariable("id") Long id ){
        salesRepService.deleteSalesRep(id);
    }

    //Note:
    // Don't pass the PersonID with the JSON attriputes, but instead put it in the link path e.g. localhost:8080/cfc/rep/95
    @PutMapping(value = "/rep/{id}")
    public void updateSalesRep(@PathVariable Long id, @RequestBody SalesRep salesRep ){
        salesRep.setId(id);
        salesRepService.updateSalesRep(salesRep);
    }
}
