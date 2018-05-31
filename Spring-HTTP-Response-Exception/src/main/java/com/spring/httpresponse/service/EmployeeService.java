package com.spring.httpresponse.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.httpresponse.entity.Employee;
import com.spring.httpresponse.repository.EmployeeRepository;


@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee add(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}
	
	public Optional<Employee> getByID(Long id) {
		return employeeRepository.findById(id);
	}	
	
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}		
}
