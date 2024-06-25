package rw.ne.laptopdistribution.models;

import javax.persistence.*;
import lombok.*;
import rw.ne.laptopdistribution.audits.InitiatorAudit;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "saving")
public class Saving extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column
    private String customer;

    @Column
    private String account;


}
