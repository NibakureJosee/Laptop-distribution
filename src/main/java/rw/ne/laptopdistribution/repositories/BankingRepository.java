package rw.ne.laptopdistribution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ne.laptopdistribution.models.Banking;

@Repository
public interface BankingRepository extends JpaRepository<Banking, Long> {

    // Custom queries if needed
}
