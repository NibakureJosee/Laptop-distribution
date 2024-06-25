package com.ne_2024.Java.Repositories;

import com.ne_2024.Java.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
