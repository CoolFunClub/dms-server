package com.coolfunclub.dms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EntityAlreadyExistsException;
import com.coolfunclub.dms.model.Car;
import com.coolfunclub.dms.service.CarService;

import jakarta.persistence.EntityExistsException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CarController {

    @Autowired
    private CarService carService;


    @PostMapping(value="/cars")
    public void addCar(@RequestBody Car car) {
    
        carService.addCar(car); 
        
               
    }
    @GetMapping(value = "/cars")
    public List<Car> getCars(){
        return carService.getAllCars();
    }
    @PutMapping(value = "/cars/{id}")
    public void updateCar(@PathVariable String id,@RequestBody Car car){
        carService.updateCar(car);
    }



    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Object> handleEntityAlreadyExists(EntityAlreadyExistsException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", "Entity already exists");
        errorMap.put("message", ex.getMessage());

        return new ResponseEntity<>(errorMap, HttpStatus.CONFLICT);
    }


}
