package com.magadiflo.employees.app.services;

import com.magadiflo.employees.app.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    List<Employee> listAll();

    Optional<Employee> employeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);

}