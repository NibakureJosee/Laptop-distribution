package com.ne_2024.Java.Services;

import com.ne_2024.Java.Models.Banking;
import com.ne_2024.Java.Models.Customer;
import com.ne_2024.Java.Models.Message;
import com.ne_2024.Java.Repositories.IBankingRepository;
import com.ne_2024.Java.Repositories.ICustomerRepository;
import com.ne_2024.Java.Repositories.IMessagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IBankingRepository bankingRepository;

    @Autowired
    private IMessagRepository messageRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Customer registerCustomer(Customer customer) {
        customer.setLastUpdateDateTime(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Transactional
    public Banking saveMoney(Long customerId, double amount) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setBalance(customer.getBalance() + amount);
            customer.setLastUpdateDateTime(LocalDateTime.now());
            customerRepository.save(customer);

            Banking banking = new Banking();
            banking.setCustomer(customer);
            banking.setAccount(customer.getAccount());
            banking.setAmount(amount);
            banking.setType("saving");
            banking.setBankingDateTime(LocalDateTime.now());
            bankingRepository.save(banking);

            String messageText = "Dear " + customer.getFirstName() + " " + customer.getLastName() +
                    ", your saving of " + amount + " on your account " + customer.getAccount() + " has been completed successfully.";
            emailService.sendEmail(customer.getEmail(), "Transaction Alert", messageText);

            Message message = new Message();
            message.setCustomer(customer);
            message.setMessage(messageText);
            message.setDateTime(LocalDateTime.now());
            messageRepository.save(message);

            return banking;
        }
        throw new RuntimeException("Customer not found");
    }

    @Transactional
    public Banking withdrawMoney(Long customerId, double amount) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }
            customer.setBalance(customer.getBalance() - amount);
            customer.setLastUpdateDateTime(LocalDateTime.now());
            customerRepository.save(customer);

            Banking banking = new Banking();
            banking.setCustomer(customer);
            banking.setAccount(customer.getAccount());
            banking.setAmount(amount);
            banking.setType("withdraw");
            banking.setBankingDateTime(LocalDateTime.now());
            bankingRepository.save(banking);

            String messageText = "Dear " + customer.getFirstName() + " " + customer.getLastName() +
                    ", your withdraw of " + amount + " from your account " + customer.getAccount() + " has been completed successfully.";
            emailService.sendEmail(customer.getEmail(), "Transaction Alert", messageText);

            Message message = new Message();
            message.setCustomer(customer);
            message.setMessage(messageText);
            message.setDateTime(LocalDateTime.now());
            messageRepository.save(message);

            return banking;
        }
        throw new RuntimeException("Customer not found");
    }

    @Transactional
    public Banking transferMoney(Long fromCustomerId, Long toCustomerId, double amount) {
        Optional<Customer> fromCustomerOptional = customerRepository.findById(fromCustomerId);
        Optional<Customer> toCustomerOptional = customerRepository.findById(toCustomerId);

        if (fromCustomerOptional.isPresent() && toCustomerOptional.isPresent()) {
            Customer fromCustomer = fromCustomerOptional.get();
            Customer toCustomer = toCustomerOptional.get();

            if (fromCustomer.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            fromCustomer.setBalance(fromCustomer.getBalance() - amount);
            fromCustomer.setLastUpdateDateTime(LocalDateTime.now());
            customerRepository.save(fromCustomer);

            toCustomer.setBalance(toCustomer.getBalance() + amount);
            toCustomer.setLastUpdateDateTime(LocalDateTime.now());
            customerRepository.save(toCustomer);

            Banking banking = new Banking();
            banking.setCustomer(fromCustomer);
            banking.setAccount(fromCustomer.getAccount());
            banking.setAmount(amount);
            banking.setType("transfer");
            banking.setBankingDateTime(LocalDateTime.now());
            bankingRepository.save(banking);

            String messageText = "Dear " + fromCustomer.getFirstName() + " " + fromCustomer.getLastName() +
                    ", your transfer of " + amount + " to account " + toCustomer.getAccount() + " has been completed successfully.";
            emailService.sendEmail(fromCustomer.getEmail(), "Transaction Alert", messageText);

            Message message = new Message();
            message.setCustomer(fromCustomer);
            message.setMessage(messageText);
            message.setDateTime(LocalDateTime.now());
            messageRepository.save(message);

            return banking;
        }
        throw new RuntimeException("Customer(s) not found");
    }

    @Transactional
    public List<Customer> getAllCustomers(){
        try{
            List<Customer> customers= customerRepository.findAll();
            return customers;
        }catch (Exception e){
            throw new RuntimeException("Internal server error");
        }
    }
}

