package com.spring.httpresponse.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.httpresponse.entity.Employee;
import com.spring.httpresponse.exception.NotFoundException;
import com.spring.httpresponse.service.EmployeeService;

@RestController
public class RootController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/user/add")
	public ResponseEntity<Object> add(@RequestBody Employee employee,HttpServletRequest request) {
    	
		Employee empEntity = employeeService.add(employee);
    	
        String baseUrl = String.format("%s://%s:%d",request.getScheme(),  
        		request.getServerName(), request.getServerPort());
        
        URI location = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/user/{id}")
                .buildAndExpand(empEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/user/delete/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		employeeService.delete(id);
		if (employeeService.getByID(id)==null) 
			return new ResponseEntity(HttpStatus.ACCEPTED);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/user/getAll")
	public List<Employee> getAll() {
		List<Employee> employeList = employeeService.getAll();
		if (employeList.isEmpty()) throw new NotFoundException();
		return employeList;
	}
	
	@GetMapping("/user/getById/{id}")
	public Employee getById(@PathVariable Long id) {
		Employee employee =  employeeService.getByID(id).get();
		if (employee!=null) throw new NotFoundException();
		return employee;
	}	
}
