package com.coolfunclub.dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.FullPurchase;
import com.coolfunclub.dms.repository.FullPurchaseRepository;

@Service
public class FullPurchaseService {

    @Autowired
    private FullPurchaseRepository fullPurchaseRepository;

    public List<FullPurchase> getAllFullPurchases() {
        return fullPurchaseRepository.findAll();
    }

    public void addFullPurchase(FullPurchase fullPurchase){
        fullPurchaseRepository.save(fullPurchase);
    }

    public FullPurchase getFullPurchaseById(Long purID) {
        return fullPurchaseRepository.findById(purID).orElse(null);
    }

    public void deleteFullPurchaseById(Long purID) {
        fullPurchaseRepository.deleteById(purID);
    }

}
