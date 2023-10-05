package com.coolfunclub.dms.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.coolfunclub.dms.model.Car;
import com.coolfunclub.dms.repository.CarRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    public void CarRepository_SaveAll_ReturnSavedCar(){
        //arrange
        Car car=Car.builder()
                    .vin("1HGCM82633A123456")
                    .manufacturer("Honda")
                    .model("Accord")
                    .carYear(2020)
                    .mileage(15000)
                    .trim("EX-L")
                    .color("Silver")
                    .status("Available")
                    .price(23400)
                    .build();

        
        //act
        Car savedCar=carRepository.save(car);
        //assert
        Assertions.assertNotNull(savedCar);
        Assertions.assertNotNull(savedCar.getVIN());
    }


    
}
