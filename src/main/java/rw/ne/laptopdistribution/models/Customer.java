package rw.ne.laptopdistribution.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "mobile", unique = true, nullable = false)
    private String mobile;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "last_updated_date_time", nullable = false)
    private LocalDateTime lastUpdatedDateTime;
}
