package rw.ne.laptopdistribution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ne.laptopdistribution.models.Message;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {

    // You can define custom query methods here if needed
}
