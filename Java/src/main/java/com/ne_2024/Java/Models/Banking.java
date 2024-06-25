package com.ne_2024.Java.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Banking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String account;
    private double amount;
    private String type; // "saving", "withdraw", "transfer"
    private LocalDateTime bankingDateTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getBankingDateTime() {
        return bankingDateTime;
    }

    public void setBankingDateTime(LocalDateTime bankingDateTime) {
        this.bankingDateTime = bankingDateTime;
    }
}
