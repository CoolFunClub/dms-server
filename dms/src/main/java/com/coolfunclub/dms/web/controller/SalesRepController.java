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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SalesRepController {

    @Autowired
    private SalesRepService salesRepService;

    @GetMapping(value = "/rep")
    public List<SalesRep> getSalesReps(){
        return salesRepService.getAllSalesReps();
    }
    @GetMapping(value = "/rep/{id}")
    public SalesRep getSalesRep(@PathVariable("id") String id ){
        return salesRepService.getSalesRep(id);
    }

    @PostMapping(value = "/rep")
    public void createSalesRep(@RequestBody SalesRep salesRep ){
        salesRepService.addSalesRep(salesRep);
    }
    
    @PutMapping(value = "/rep")
    public void updateSalesRep(@RequestBody SalesRep salesRep ){
        salesRepService.updateSalesRep(salesRep);
    }

    @DeleteMapping(value = "/rep/{id}")
    public void deleteManager(@PathVariable("id") String id ){
        salesRepService.deleteSalesRep(id);
    }
    
}
