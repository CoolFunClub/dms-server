package com.coolfunclub.dms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coolfunclub.dms.model.Finance;
import com.coolfunclub.dms.service.FinanceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cfc/purs")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping(value = "finan")
    public List<Finance> getAllFinance() {
        return financeService.getAllFinance();
    }

    @GetMapping(value = "finan/{id}")
    public Finance getFinanceById(@PathVariable("id") Long purID ) {
        return financeService.getFinanceById(purID);
    }

    @PostMapping(value = "finan")
    public void addFinancePur(@RequestBody Finance finance){
        financeService.addFinance(finance);
    }

    @DeleteMapping(value = "finan/{id}")
    public void deleteFinanceById(@PathVariable("id") Long purID ) {
        financeService.deleteFinanceById(purID);
    }
}
