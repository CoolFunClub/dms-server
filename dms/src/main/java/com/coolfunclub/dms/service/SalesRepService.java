package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;


import com.coolfunclub.dms.model.SalesRep;
import com.coolfunclub.dms.repository.SalesRepRepository;



@Service
public class SalesRepService {
    @Autowired
    private SalesRepRepository salesRepRepository;

    public List<SalesRep> getAllManagers(){
        List<SalesRep> salesReps=new LinkedList<>();
        salesRepRepository.findAll().forEach(salesReps::add);
        return salesReps;
    }
    public void addManager(SalesRep Manager){ 
        salesRepRepository.save(Manager);
    }
    public SalesRep getSalesRep(String vin){
        return salesRepRepository.findById(vin).get();
    }
    public void updateSalesRep(SalesRep newSalesRep){
        //ManagerRepository.save(Manager);
   
        String vin = newSalesRep.getpersonID(); 
    
        SalesRep existingSalesRep = salesRepRepository.findById(vin)
                                    .orElseThrow(() -> new EntityNotFoundException("Manager not found"));
        
        existingSalesRep.setFirstName(newSalesRep.getFirstName());
        existingSalesRep.setLastName(newSalesRep.getLastName());
        existingSalesRep.setDateBirth(newSalesRep.getDateBirth());
        existingSalesRep.setGender(newSalesRep.getGender());
        existingSalesRep.setPhone(newSalesRep.getPhone());
        existingSalesRep.setEmail(newSalesRep.getEmail());
        existingSalesRep.setAddress(newSalesRep.getAddress());

        salesRepRepository.save(existingSalesRep);
    }
    public void deleteSalesRep(String vin){
        SalesRep salesRep= getSalesRep(vin);
        if(salesRep!=null){
            salesRepRepository.delete(salesRep);
        }
    }
    
}
