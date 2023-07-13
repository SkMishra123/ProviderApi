package com.Java.Project.Service;

import java.util.List;

import com.Java.Project.entity.Employee;

public interface EmployeeService {
	
	String saveEmployee(Employee emp);
	
	Employee getEmployee(String name);
	
	List<Employee> getEmployees();
	
	String deleteEmployee(String name);

}
