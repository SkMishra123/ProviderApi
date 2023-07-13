package com.Java.Project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Java.Project.entity.Employee;
import com.Java.Project.repository.EmployeeRepo;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepo repo;

	@Override
	public String saveEmployee(Employee emp) {
		repo.save(emp);
		return new String("Employee details added successfully");
	}

	@Override
	public Employee getEmployee(String name) {
		return repo.findByFirstName(name);
	}

	@Override
	public List<Employee> getEmployees() {
		return repo.findAll();
	}

	@Override
	public String deleteEmployee(String name) {
		repo.removeByFirstName(name);
		return new String("Employee details removed successfully");
	}

}
