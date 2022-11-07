package com.glearning.employee.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.glearning.employee.model.Employee;
import com.glearning.employee.model.User;
import com.glearning.employee.repository.EmployeeRepository;
import com.glearning.employee.repository.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// method to add new employees
	public Employee addStudents(Employee employee) {
		System.out.println("Saving the employee to the set");
		Employee savedEmployee = this.employeeRepository.save(employee);
		return savedEmployee;
	}

	// method to return all the employees
	public Set<Employee> fetchAllStudents() {
		return new HashSet<>(this.employeeRepository.findAll());
	}
		

	// method to fetch employee using their id
	public Employee fetchStudentById(long employeeId) {
		return this.employeeRepository.findById(employeeId)
				.orElseThrow(() -> new IllegalArgumentException("Inavlid employee id passed.."));
	}

	// method to update employee's details using their Id
	public Employee updateEmployeeById(long employeeId, Employee updatedEmployee) {
		System.out.println("Passed in employee is->" + updatedEmployee);
		System.out.println("Employee's id is->" + employeeId);
		Employee employeeToBeUpdated = this.fetchStudentById(employeeId);
		employeeToBeUpdated.setFirstName(updatedEmployee.getFirstName());
		employeeToBeUpdated.setLastName(updatedEmployee.getLastName());
		employeeToBeUpdated.setEmail(updatedEmployee.getEmail());
		this.employeeRepository.save(employeeToBeUpdated);
		return employeeToBeUpdated;
	}

	// method to remove employee's details using their Id
	public void deleteStudentById(long employeeId) throws EmptyResultDataAccessException{
		 this.employeeRepository.deleteById(employeeId);
		
	}

	// fetch an employee using their first name
	public List<Employee> fetchByNameStartingWith(String prefix) {
		return this.employeeRepository.findByFirstNameStartingWith(prefix);
	}

	// sorting the employees
	public List<Employee> getLibrariesCustomSortedByName(Direction direction) {

		return this.employeeRepository.findAll(Sort.by(direction, "firstName"));

	}

}
