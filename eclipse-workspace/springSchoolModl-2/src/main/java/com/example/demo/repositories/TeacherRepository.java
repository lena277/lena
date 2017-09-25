package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entites.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
     
	
	Iterable<Teacher> findByTeacherName(String name);
}
