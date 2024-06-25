package rw.ne.laptopdistribution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ne.laptopdistribution.dtos.CustomerDTO;
import rw.ne.laptopdistribution.exceptions.BadRequestException;
import rw.ne.laptopdistribution.exceptions.ResourceNotFoundException;
import rw.ne.laptopdistribution.models.Customer;
import rw.ne.laptopdistribution.repositories.CustomerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer existingCustomerByEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if (existingCustomerByEmail != null) {
            throw new BadRequestException("Customer with email " + customerDTO.getEmail() + " already exists");
        }

        Customer existingCustomerByMobile = customerRepository.findByMobile(customerDTO.getMobile());
        if (existingCustomerByMobile != null) {
            throw new BadRequestException("Customer with mobile " + customerDTO.getMobile() + " already exists");
        }

        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobile(customerDTO.getMobile());
        customer.setAccount(customerDTO.getAccount());
        customer.setDob(customerDTO.getDob());
        customer.setBalance(customerDTO.getBalance());
        customer.setLastUpdatedDateTime(LocalDateTime.now());

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

    @Override
    public void updateCustomerBalance(UUID customerId, double amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        customer.setBalance(customer.getBalance() + amount);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}
