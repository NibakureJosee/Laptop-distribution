package rw.ne.laptopdistribution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ne.laptopdistribution.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Custom queries if needed
}
