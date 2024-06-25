package com.ne_2024.Java.Services;

import com.ne_2024.Java.Models.Banking;
import com.ne_2024.Java.Models.Customer;
import com.ne_2024.Java.Repositories.IBankingRepository;
import com.ne_2024.Java.Repositories.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BankingService {
    @Autowired
    private  IBankingRepository bankingRepository;
    @Autowired
    private ICustomerRepository customerRepository;

    public List<Banking> findByCustomerId(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return bankingRepository.findByCustomer(customer);
        }else{
            throw new RuntimeException("Customer not found");
        }
    }

    public List<Banking> findAll() {
        return bankingRepository.findAll();
    }

    public Banking deleteById(Long id) {
        try {
            Optional<Banking> bankingOptional = bankingRepository.findById(id);
            if (bankingOptional.isPresent()) {
                Banking banking = bankingOptional.get();
                bankingRepository.deleteById(id);
                return banking;
            } else {
                throw new RuntimeException("Banking not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Banking not found");
        }
    }



}
