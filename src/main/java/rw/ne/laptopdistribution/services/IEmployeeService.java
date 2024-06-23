package rw.ne.laptopdistribution.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ne.laptopdistribution.dtos.CreateOrUpdateEmployeeDTO;
import rw.ne.laptopdistribution.models.Employee;


import java.util.List;
import java.util.UUID;

public interface IEmployeeService {

    Employee findById(UUID employeeId);

    Page<Employee> getEmployeesPaginated(Pageable pageable);

    List<Employee> getEmployees();

    Employee addNewEmployee(CreateOrUpdateEmployeeDTO dto);
}
