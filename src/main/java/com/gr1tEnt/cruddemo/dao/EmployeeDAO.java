package com.gr1tEnt.cruddemo.dao;

import com.gr1tEnt.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

}
