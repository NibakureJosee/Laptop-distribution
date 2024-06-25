package rw.ne.laptopdistribution.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ne.laptopdistribution.dtos.CustomerDTO;
import rw.ne.laptopdistribution.models.Customer;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    Customer findById(UUID customerId);

    Page<Customer> getCustomersPaginated(Pageable pageable);

    List<Customer> getCustomers();

    Customer addNewCustomer(CustomerDTO dto);
}



