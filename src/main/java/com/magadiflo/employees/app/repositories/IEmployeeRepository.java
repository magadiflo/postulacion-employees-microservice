package com.magadiflo.employees.app.repositories;

import com.magadiflo.employees.app.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepository extends CrudRepository<Employee, Long> {

}
