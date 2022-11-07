package com.glearning.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glearning.employee.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	public List<Employee> findByFirstNameStartingWith(String prefix);
}
