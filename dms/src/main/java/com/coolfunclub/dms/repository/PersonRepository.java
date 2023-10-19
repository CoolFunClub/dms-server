package com.coolfunclub.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.coolfunclub.dms.commons.Person;

@NoRepositoryBean
public interface PersonRepository extends CrudRepository<Person, Long>{

}
