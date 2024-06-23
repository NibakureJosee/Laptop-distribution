package rw.ne.laptopdistribution.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ne.laptopdistribution.dtos.CreateOrUpdateEmployeeDTO;
import rw.ne.laptopdistribution.payload.ApiResponse;
import rw.ne.laptopdistribution.services.IEmployeeService;
import rw.ne.laptopdistribution.utils.Constants;


@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeController {
    private final IEmployeeService service;


    @Autowired
    public EmployeeController(IEmployeeService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.getEmployees()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPaginated(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(ApiResponse.success(this.service.getEmployeesPaginated(pageable)));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CreateOrUpdateEmployeeDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(this.service.addNewEmployee(dto)));
    }
}
