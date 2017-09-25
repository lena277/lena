package com.example.demo.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entites.Student;

public interface StudentRepository extends CrudRepository<Student,Integer>{
	Iterable<Student> findBystudentName(String name);
	
	
	
	

}
