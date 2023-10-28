package com.coolfunclub.dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coolfunclub.dms.model.Finance;

@Repository
public interface FinanceRepository extends PurchaseRepository{

}
/*
public interface FinanceRepository extends JpaRepository<Finance,String>{

}
 */
