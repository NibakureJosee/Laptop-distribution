package rw.ne.laptopdistribution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ne.laptopdistribution.models.Laptop;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ILaptopRepository extends JpaRepository<Laptop, UUID> {
    Optional<Laptop> findBySerialNumber(String serialNumber);
}
