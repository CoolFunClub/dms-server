package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.Manager;
import com.coolfunclub.dms.repository.ManagerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public List<Manager> getAllManagers(){
        List<Manager> Managers=new LinkedList<>();
        managerRepository.findAll().forEach(Managers::add);
        return Managers;
    }
    public void addManager(Manager Manager){ 
        managerRepository.save(Manager);
    }
    public Manager getManager(String vin){
        return managerRepository.findById(vin).get();
    }
    public void updateManager(Manager newManager){
        //ManagerRepository.save(Manager);
   
        String vin = newManager.getpersonID(); 
    
        Manager existingManager = managerRepository.findById(vin)
                                    .orElseThrow(() -> new EntityNotFoundException("Manager not found"));
        
        existingManager.setFirstName(newManager.getFirstName());
        existingManager.setLastName(newManager.getLastName());
        existingManager.setDateBirth(newManager.getDateBirth());
        existingManager.setGender(newManager.getGender());
        existingManager.setPhone(newManager.getPhone());
        existingManager.setEmail(newManager.getEmail());
        existingManager.setAddress(newManager.getAddress());

        managerRepository.save(existingManager);
    }
    public void deleteManager(String vin){
        Manager Manager= getManager(vin);
        if(Manager!=null){
            managerRepository.delete(Manager);
        }
    }
}