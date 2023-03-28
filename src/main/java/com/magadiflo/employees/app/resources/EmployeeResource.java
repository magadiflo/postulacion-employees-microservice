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
        return this.employeeService.employeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.saveEmployee(employee));
    }

    @PostMapping(path = "/list")
    public ResponseEntity<List<Employee>> saveAllEmployee(@RequestBody List<Employee> employees) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.saveAllEmployees(employees));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return this.employeeService.updateEmployee(employee, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return this.employeeService.employeeById(id)
                .map(employee -> {
                    this.employeeService.deleteEmployee(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
