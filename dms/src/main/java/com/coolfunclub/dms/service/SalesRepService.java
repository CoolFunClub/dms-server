package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.SalesRep;
import com.coolfunclub.dms.repository.SalesRepository;

@Service
public class SalesRepService {

    @Autowired
    SalesRepository salesRepository;

    //Constructor
    public SalesRepService(){

    }

    public ResponseEntity<String> addSalesRep(SalesRep salesRep){
        Optional <SalesRep> existingSalesRep = salesRepository.findById(salesRep.getSSN());
        if (existingSalesRep.isPresent()){
            return ResponseEntity.badRequest().body("SalesRep already exists with the provided SSN.");
        } else{

         salesRepository.save(salesRep);
         return ResponseEntity.ok("SalesRep added successfully");
        }
    }

    public List<SalesRep> getAllSalesReps(){
        List<SalesRep> salesReps = new LinkedList<>();
        salesRepository.findAll().forEach(salesReps::add);
        return salesReps;
    }

    public void deleteSalesRep(int ssn){
            salesRepository.deleteById(ssn);
    }

    public SalesRep getSalesRepById(int ssn){
        return salesRepository.findById(ssn).orElse(null);
    }


    public void updateSalesRep(SalesRep newSalesRep){
        salesRepository.save(newSalesRep);
    }
}
