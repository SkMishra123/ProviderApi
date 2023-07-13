package com.Java.Project.Controller;

import com.Java.Project.Service.EmployeeService;
import com.Java.Project.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class MyControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService service;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private Employee employee;
	
	private List<Employee> emplist;

	@BeforeEach
	public void setUp() {
		employee = new Employee(1L, "firstName4", "lastName4", "email4@mail.com");
		emplist = new ArrayList<Employee>();
		emplist.add(employee);
	}

	@Test 
	public void testAddEmployee() throws Exception {
	  when(service.saveEmployee(employee)).thenReturn("Employee details added successfully");
	  mockMvc.perform(MockMvcRequestBuilders 
			  			.post("/api/v1/createEmp")
			  			.content(objectMapper.writeValueAsString(employee))
			  			.contentType(MediaType.APPLICATION_JSON) 
			  			.accept(MediaType.APPLICATION_JSON))
	  					.andExpect(status().isCreated());
	  					//.andExpect(content().string("Employee details added successfully"));
	  }
	  
	 @Test
    public void testGetSpecificEmployee() throws Exception {
        when(service.getEmployee("firstName4")).thenReturn(employee);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/v1/employee/firstName4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email4@mail.com"));
    }

	@Test
	 public void testGetAll() throws Exception{
	 	when(service.getEmployees()).thenReturn(emplist);
	 	mockMvc.perform(MockMvcRequestBuilders
	 					.get("/api/v1/employees")
	 					.content(objectMapper.writeValueAsString(emplist))
	 					.contentType(MediaType.APPLICATION_JSON)
	 					.accept(MediaType.APPLICATION_JSON))
	 			.andExpect(status().isOk())
	 			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));
	 }

	@Test
	 public void testRemove() throws Exception{
		 when(service.deleteEmployee("firstName4")).thenReturn("Employee details removed successfully");
		 mockMvc.perform(MockMvcRequestBuilders
				 		.delete("/api/v1/employee/firstName4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee details removed successfully"));
	 }

}
