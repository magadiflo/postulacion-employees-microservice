package com.magadiflo.employees.app.resources;

import com.magadiflo.employees.app.entities.Employee;
import com.magadiflo.employees.app.services.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/employees")
public class EmployeeResource {

    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(this.employeeService.employeeById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.saveEmployee(employee));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return ResponseEntity.ok(this.employeeService.updateEmployee(employee, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        this.employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
