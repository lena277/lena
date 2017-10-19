package com.example.demo.servicies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public List<Employee> list(){
		return repository.findAll();
	}
	
	public Employee findById(String id) {
		return repository.findOne(id);
	}
	
	public List<Employee> findEmployeesByName(String name) {
		return repository.findByName(name);
	}
	

   
	public Employee create(Employee employee) {
		return repository.save(employee);
	}
	
	public void deleteById(String inttger) {
		 repository.delete(inttger);
	}

	public void deleteAll() {
		 repository.deleteAll();
	}


	public void updateById(Employee employee) {
		  
	    repository.save(employee);  
	
	}}
