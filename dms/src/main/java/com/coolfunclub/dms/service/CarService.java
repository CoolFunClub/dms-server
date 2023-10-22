package com.coolfunclub.dms.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EntityAlreadyExistsException;
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

        Optional<Car> existingCar = carRepository.findById(car.getVIN());
        if (existingCar.isPresent()) {
            // Handle the case where the car already exists
            throw new EntityAlreadyExistsException("Car with ID " + car.getVIN() + " already exists.");
        }
        carRepository.save(car);
    }
    public Car getCar(String vin){
        return carRepository.findById(vin).get();
    }
    public void updateCar(Car newCarData){
        //carRepository.save(car);
   
        String vin = newCarData.getVIN(); // Assuming the VIN is what you're using to identify cars
        try { 
            Car existingCar = getCar(vin);
            existingCar.setManufacturer(newCarData.getManufacturer());
            existingCar.setModel(newCarData.getModel());
            existingCar.setcarYear(newCarData.getcarYear());
            existingCar.setMilage(newCarData.getMileage());
            existingCar.setPrice(newCarData.getPrice());
            existingCar.setTrim(newCarData.getTrim());
            existingCar.setColor(newCarData.getColor());
            existingCar.setStatus(newCarData.getStatus());
            carRepository.save(existingCar);
        }
        catch(NoSuchElementException e){
            return;

        }    
    }
    public void deleteCar(String vin){
        Car car= getCar(vin);
        if(car!=null){
            carRepository.delete(car);
        }
    }
}
