package com.glearning.employee.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.employee.model.Employee;
import com.glearning.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public Set<Employee> fetchAllEmployees() {
		return this.employeeService.fetchAllStudents();
	}	
	
	@GetMapping("/{id}")
	public Employee fetchById(@PathVariable("id") long employeeId) {
		return this.employeeService.fetchStudentById(employeeId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee saveEmployees(@RequestBody Employee employee) {
		return this.employeeService.addStudents(employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployees(@PathVariable("id") long employeeId) {
		this.employeeService.deleteStudentById(employeeId);
		String deletedEmpResponse="Deleted employee id-"+employeeId;
		return deletedEmpResponse;
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployeeById(@PathVariable("id") long employeeId,@RequestBody Employee employee) {
		return this.employeeService.updateEmployeeById(employeeId, employee);
	}
	
	@GetMapping("/prefix")
	public List<Employee> getEmployeeByFirstName(@RequestParam(name="prefix", defaultValue = "gl",required = false) String prefix) {
		return this.employeeService.fetchByNameStartingWith(prefix);
	}
	
	@GetMapping("/sort")
	public List<Employee> getLibrariesCustomSortedByName(@RequestParam(name="order", defaultValue = "asc",required = false) Direction direction) {
		return this.employeeService.getLibrariesCustomSortedByName(direction);
	}
	
	

}
