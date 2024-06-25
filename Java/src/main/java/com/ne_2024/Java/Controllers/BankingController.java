package com.ne_2024.Java.Controllers;

import com.ne_2024.Java.Models.Banking;
import com.ne_2024.Java.Services.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    @Autowired
    private BankingService bankingService;

    @GetMapping("/all")
    public List<Banking> getAllBanking(){
        return bankingService.findAll();
    }

    @GetMapping("/delete/{id}")
    public Banking deleteBankingById(@PathVariable Long id){
        return bankingService.deleteById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Banking> getBankingByCustomerId(@PathVariable Long customerId){
        try {
            return bankingService.findByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Customer not found");
        }
    }

}
