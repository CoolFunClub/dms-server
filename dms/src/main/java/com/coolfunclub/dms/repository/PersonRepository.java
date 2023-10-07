package com.coolfunclub.dms.repository;
import org.springframework.data.repository.CrudRepository;
import com.coolfunclub.dms.model.Person;

public interface PersonRepository extends CrudRepository<Person, String>{
    
}
