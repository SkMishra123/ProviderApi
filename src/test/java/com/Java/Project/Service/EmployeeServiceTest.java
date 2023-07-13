package com.Java.Project.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Java.Project.entity.Employee;
import com.Java.Project.repository.EmployeeRepo;

@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeServiceImpl service;
	
	@MockBean
	private EmployeeRepo repo;
	
	private Employee emp;
	private List<Employee> emplist = new ArrayList<Employee>();
	
	@BeforeEach
	public void setUp() {
		emp = new  Employee(1L, "firstName4", "lastName4", "email4@mail.com");
		emplist.add(emp);
	}
	
	@Test
	public void testSaveEmployee() {
		when(repo.save(any(Employee.class))).thenReturn(emp);
		String savedEmp = service.saveEmployee(emp);
		assertThat(savedEmp.equalsIgnoreCase("Employee details added successfully"));
	}
	
	@Test
	public void testGetEmployee() {
		String name = "firstName4";
		when(repo.findByFirstName(name)).thenReturn(emp);
		Employee getEmp = service.getEmployee(name);
		assertThat(getEmp.getFirstName()).isEqualTo("firstName4");
		assertThat(getEmp.getLastName()).isEqualTo("lastName4");
		assertThat(getEmp.getEmail()).isEqualTo("email4@mail.com");
	}
	
	@Test
	public void testGetEmployees() {
		when(repo.findAll()).thenReturn(emplist);
		List<Employee> getAll = service.getEmployees();
		assertThat(getAll.get(0).getFirstName().equalsIgnoreCase("firstName4"));
	}
	
	@Test
	public void testDeleteEmployee() {
		doNothing().when(repo).removeByFirstName(anyString());
		String deleteEmp = service.deleteEmployee("firstName4");
		assertThat(deleteEmp.equalsIgnoreCase("Employee details removed successfully"));
	}
}
