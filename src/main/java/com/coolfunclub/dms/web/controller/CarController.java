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

@RestController
public class CarController {

    @Autowired
    private CarService carService;
    @PostMapping(value="/cars")
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);        
    }
    @GetMapping(value = "/cars/{id}")
    public Car getCar(@PathVariable String id){
        return carService.getCar(id);
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

    @PostMapping("cars/{carVin}/images/{imageId}")
    public ResponseEntity<?> addImageToCar(@PathVariable String carVin, @PathVariable Long imageId) {
        if(carService.addImageToCar(carVin, imageId)){
            return ResponseEntity.ok("Image added to car successfully");
        } else{ 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error assoc image to car");
        }
        
    }
    


}
