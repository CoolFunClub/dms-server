package com.coolfunclub.dms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coolfunclub.dms.model.FullPurchase;
import com.coolfunclub.dms.service.FullPurchaseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("cfc/")
public class FullPurchaseController {

    @Autowired
    private FullPurchaseService fullPurchaseService;

    @GetMapping(value = "fullp")
    public List<FullPurchase> getAllFullPurchases() {
        return fullPurchaseService.getAllFullPurchases();
    }

    @GetMapping(value = "fullp/{id}")
    public FullPurchase getFullPurchaseById(@PathVariable("id") Long purID ) {
        return fullPurchaseService.getFullPurchaseById(purID);
    }

    @PostMapping(value = "fullp/{id}")
    public ResponseEntity<String> addFullPurchase(@RequestBody FullPurchase fullPurchase, @PathVariable ("id") Long accountId){
        return fullPurchaseService.addFullPurchase(fullPurchase, accountId);
    }

    @DeleteMapping(value = "fullp/{id}")
    public void deleteFullPurchaseById(@PathVariable("id") Long purID ) {
        fullPurchaseService.deleteFullPurchaseById(purID);
    }

}
