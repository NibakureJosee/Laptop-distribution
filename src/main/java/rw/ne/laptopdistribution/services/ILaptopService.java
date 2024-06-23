package rw.ne.laptopdistribution.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ne.laptopdistribution.dtos.CreateOrUpdateLaptopDTO;
import rw.ne.laptopdistribution.models.Laptop;


import java.util.List;
import java.util.UUID;

public interface ILaptopService {

    Page<Laptop> getLaptopsPaginated(Pageable pageable);

    Laptop findById(UUID laptopId);

    List<Laptop> getLaptops();

    Laptop addNewLaptop(CreateOrUpdateLaptopDTO dto);
}
