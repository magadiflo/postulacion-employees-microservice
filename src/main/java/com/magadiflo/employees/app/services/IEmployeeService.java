package com.magadiflo.employees.app.services;

import com.magadiflo.employees.app.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    List<Employee> listAll();

    Optional<Employee> employeeById(Long id);

    Employee saveEmployee(Employee employee);

    Optional<Employee> updateEmployee(Employee employee, Long id);

    void deleteEmployee(Long id);

}