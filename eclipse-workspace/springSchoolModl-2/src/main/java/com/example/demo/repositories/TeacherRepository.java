package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entites.Student;
import com.example.demo.entites.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
     
	 List <Teacher> findByName(String name);
	   
	 List <Teacher> findAll();
}
