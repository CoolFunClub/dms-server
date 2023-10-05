package com.coolfunclub.dms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.coolfunclub.dms.model.Car;
import com.coolfunclub.dms.service.CarService;

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
}
