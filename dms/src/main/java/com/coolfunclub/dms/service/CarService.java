package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolfunclub.dms.model.Car;
import com.coolfunclub.dms.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars(){
        List<Car> cars=new LinkedList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }
    public void addCar(Car car){ 
        carRepository.save(car);
    }
    public Car getCar(String vin){
        return carRepository.findById(vin).get();
    }
    public void updateCar(Car car){
        carRepository.save(car);
    }
}
