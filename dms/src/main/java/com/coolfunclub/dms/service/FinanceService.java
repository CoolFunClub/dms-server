package com.coolfunclub.dms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolfunclub.dms.model.Finance;
import com.coolfunclub.dms.repository.FinanceRepository;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepository financeRepository;

    public List<Finance> getAllFinance() {
        return financeRepository.findAll();
    }

    public void addFinance(Finance finance){
        financeRepository.save(finance);
    }

    public Finance getFinanceById(Long purID) {
        return financeRepository.findById(purID).orElse(null);
    }

    public void deleteFinanceById(Long purID) {
        financeRepository.deleteById(purID);
    }
}
