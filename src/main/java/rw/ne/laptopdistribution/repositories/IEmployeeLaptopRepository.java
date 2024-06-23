package rw.ne.laptopdistribution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ne.laptopdistribution.models.EmployeeLaptop;
import rw.ne.laptopdistribution.models.Laptop;

import java.util.Optional;
import java.util.UUID;

public interface IEmployeeLaptopRepository extends JpaRepository<EmployeeLaptop, UUID> {

    Optional<EmployeeLaptop> findByLaptop(Laptop laptop);

}
