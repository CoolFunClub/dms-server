package com.coolfunclub.dms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coolfunclub.dms.model.FullPurchase;

@Repository
public interface FullPurchaseRepository extends JpaRepository<FullPurchase,Long> {

}

