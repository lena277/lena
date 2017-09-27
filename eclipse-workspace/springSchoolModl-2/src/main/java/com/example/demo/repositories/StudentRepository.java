package com.example.demo.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entites.Bus;
import com.example.demo.entites.Student;

public interface StudentRepository extends CrudRepository<Student,Integer>{
	
    List <Student> findByName(String name);
    
	List <Student> findAll();
	
	

}
