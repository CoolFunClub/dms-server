package com.coolfunclub.dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coolfunclub.dms.model.Account;



public interface AccountRepository extends JpaRepository<Account,Long> {
    
}
