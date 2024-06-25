package com.ne_2024.Java.Repositories;

import com.ne_2024.Java.Models.Banking;
import com.ne_2024.Java.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBankingRepository  extends JpaRepository<Banking, Long> {
    List<Banking> findByCustomer(Customer customer);
}
