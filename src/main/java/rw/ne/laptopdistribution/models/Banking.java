package rw.ne.laptopdistribution.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bankings")
public class Banking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "account")
    private String account;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private String type; // saving, withdraw, transfer

    @Column(name = "banking_date_time")
    private LocalDateTime bankingDateTime;
}
