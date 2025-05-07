package parameta.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parameta.gateway.service.EmployeeService;
import parameta.gateway.dto.request.EmployeeRequest;
import parameta.gateway.dto.response.EmployeeResponse;

@RestController
@RequestMapping(path = "/api/employee")
@Tag(name = "Employee", description = "Operations related to employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    @Operation(summary = "Register new Employee", description = "Register a new employee in the system.")
    @ApiResponse(responseCode = "201", description = "The day was closed successfully")
    public ResponseEntity<EmployeeResponse> registerEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse response = employeeService.registerEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
