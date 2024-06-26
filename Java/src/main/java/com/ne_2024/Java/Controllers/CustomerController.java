package com.ne_2024.Java.Controllers;

import com.ne_2024.Java.Models.Banking;
import com.ne_2024.Java.Models.Customer;
import com.ne_2024.Java.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

    @PostMapping("/{customerId}/save")
    public Banking saveMoney(@PathVariable Long customerId, @RequestParam double amount) {
        try{
            return customerService.saveMoney(customerId, amount);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @PostMapping("/{customerId}/withdraw")
    public Banking withdrawMoney(@PathVariable Long customerId, @RequestParam double amount) {
        return customerService.withdrawMoney(customerId, amount);
    }

    @PostMapping("/{fromCustomerId}/transfer/{toCustomerId}")
    public Banking transferMoney(@PathVariable Long fromCustomerId, @PathVariable Long toCustomerId, @RequestParam double amount) {
        return customerService.transferMoney(fromCustomerId, toCustomerId, amount);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
