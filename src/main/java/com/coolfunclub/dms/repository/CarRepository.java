package com.coolfunclub.dms.repository;
import org.springframework.data.repository.CrudRepository;
import com.coolfunclub.dms.model.Car;

public interface CarRepository extends CrudRepository<Car, String> {
    
}
