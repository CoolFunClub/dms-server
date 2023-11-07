package com.coolfunclub.dms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coolfunclub.dms.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
