package com.Java.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Java.Project.entity.Employee;
import com.Java.Project.repository.EmployeeRepo;

@RestController
@RequestMapping("/api/v1")
public class MyController {

	@Autowired
	private EmployeeRepo repo;

	@PostMapping(path="/createEmp")
	public ResponseEntity<String> add(@RequestBody Employee emp){
		repo.save(emp);
		return new ResponseEntity<String>("Employee details added successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAll(){	
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{name}")
	public ResponseEntity<Employee> getSpecificEmployee(@PathVariable("name") String name){
		return new ResponseEntity<>(repo.findByFirstName(name), HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{name}")
	public String remove(@PathVariable("name") String name) {
		repo.removeByFirstName(name);
		return new String("Employee details removed successfully");
	}
	
	
}
