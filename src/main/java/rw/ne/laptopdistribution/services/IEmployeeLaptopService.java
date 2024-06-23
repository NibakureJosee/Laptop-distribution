package rw.ne.laptopdistribution.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ne.laptopdistribution.dtos.NewEmployeeLaptopDTO;
import rw.ne.laptopdistribution.models.EmployeeLaptop;


public interface IEmployeeLaptopService {

    Page<EmployeeLaptop> getEmployeeLaptops(Pageable pageable);

    EmployeeLaptop addNewEmployeeLaptop(NewEmployeeLaptopDTO dto);

}
