package com.coolfunclub.dms.test;

import com.coolfunclub.dms.model.Car;
import com.coolfunclub.dms.repository.CarRepository;
import com.coolfunclub.dms.web.controller.CarController;
import com.coolfunclub.dms.service.CarService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.Optional;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(CarController.class)
public class CarTest {
    @Autowired
    private CarController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService service;

    @Autowired
    private CarService myCarService;

    @Mock
    private CarRepository carRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void serviceReturnsAllCars() throws Exception {
        Car car = new Car("123", "Toyota", "Camry", 2003, 333, "none", "red", 900.5, "in stock");
        List<Car> carList = new LinkedList<Car>(List.of(car));
        service.addCar(car);

		when(service.getAllCars()).thenReturn(carList);

		this.mockMvc.perform(get("/cars")).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void testGetCarByVin() {
        // Arrange
        String vin = "246810108642"; // Replace with a valid VIN from your test data
        Car expectedCar = new Car(vin,
                         "Toyota",
                         "Corolla",
                         2013,
                         75000,
                         "LE",
                         "Red",
                         25000.0,
                          "On Stock");

        // Mock the behavior of carRepository.findById() method
        when(myCarService.getCar(vin)).thenReturn(expectedCar);
        // Act
        Car actualCar = myCarService.getCar(vin);
        // Assert
        assertEquals(expectedCar, actualCar);
    }
    

}

