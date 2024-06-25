package rw.ne.laptopdistribution.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.ne.laptopdistribution.models.Banking;

public interface IBankingRepository extends JpaRepository<Banking, Long> {

    // You can add custom query methods here if needed
}
