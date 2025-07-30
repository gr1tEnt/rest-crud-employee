package com.gr1tEnt.cruddemo.dao;

import com.gr1tEnt.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
