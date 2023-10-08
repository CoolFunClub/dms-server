package com.coolfunclub.dms.repository;

import org.springframework.data.repository.CrudRepository;
import com.coolfunclub.dms.model.Manager;

public interface ManagerRespository extends CrudRepository<Manager,String>{
    
}
