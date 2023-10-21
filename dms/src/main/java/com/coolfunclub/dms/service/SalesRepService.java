package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import com.coolfunclub.dms.model.SalesRep;
import com.coolfunclub.dms.repository.SalesRepository;

@Service
public class SalesRepService {

    @Autowired
    SalesRepository salesRepository;

    //Constructor
    public SalesRepService(){

    }

    public void addSalesRep(SalesRep salesRep){
        salesRepository.save(salesRep);
    }

    public List<SalesRep> getAllSalesReps(){
        List<SalesRep> salesReps = new LinkedList<>();
        salesRepository.findAll().forEach(salesReps::add);
        return salesReps;
    }

    public void deleteSalesRep(Long personID){
            salesRepository.deleteById(personID);
    }

    public SalesRep getSalesRepById(Long personID){
        return salesRepository.findById(personID).orElse(null);
    }


    public void updateSalesRep(SalesRep newSalesRep){
        salesRepository.save(newSalesRep);
    }
}
