package com.magadiflo.employees.app.services.impl;

import com.magadiflo.employees.app.entities.Employee;
import com.magadiflo.employees.app.repositories.IEmployeeRepository;
import com.magadiflo.employees.app.services.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> listAll() {
        return (List<Employee>) this.employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> employeeById(Long id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveAllEmployees(List<Employee> employees) {
        return (List<Employee>) this.employeeRepository.saveAll(employees);
    }

    @Override
    @Transactional
    public Optional<Employee> updateEmployee(Employee employee, Long id) {
        return this.employeeRepository.findById(id)
                .map(employeeBD -> {
                    employeeBD.setName(employee.getName());
                    employeeBD.setLastName(employee.getLastName());
                    employeeBD.setEmail(employee.getEmail());
                    employeeBD.setMobilePhone(employee.getMobilePhone());
                    employeeBD.setSalary(employee.getSalary());
                    return Optional.of(this.employeeRepository.save(employeeBD));
                })
                .orElseGet(Optional::empty);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        this.employeeRepository.deleteById(id);
    }
}
