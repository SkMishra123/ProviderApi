package com.Java.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.Java.Project.entity.Employee;

import jakarta.transaction.Transactional;
@Component
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
	public Employee findByFirstName(String name);
	
	@Transactional
	public void removeByFirstName(String name);

}
