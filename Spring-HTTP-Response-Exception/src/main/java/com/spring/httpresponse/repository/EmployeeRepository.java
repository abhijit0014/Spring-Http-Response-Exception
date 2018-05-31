package com.spring.httpresponse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.httpresponse.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
