package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.Manager;
import com.coolfunclub.dms.repository.ManagerRepository;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    //Constructor
    public ManagerService(){

    }

    public void addManager(Manager manager){
       managerRepository.save(manager);
    }

    public List<Manager> getAllManagers(){
        List<Manager> Managers = new LinkedList<>();
        managerRepository.findAll().forEach(Managers::add);
        return Managers;
    }

    public void deleteManager(Long personID){
        managerRepository.deleteById(personID);
    }

    public Manager getManagerById(Long personID){
        return managerRepository.findById(personID).orElse(null);
    }

    public void updateManager(Manager newManager){
        managerRepository.save(newManager);
    }
}
