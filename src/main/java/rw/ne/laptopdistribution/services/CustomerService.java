package rw.ne.laptopdistribution.services;

import rw.ne.laptopdistribution.dtos.CustomerDTO;
import rw.ne.laptopdistribution.models.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer createCustomer(CustomerDTO customerDTO);

    List<Customer> getAllCustomers();

    Customer getCustomerById(UUID id);

    void updateCustomerBalance(UUID customerId, double amount);

    void deleteCustomer(UUID id);
}
