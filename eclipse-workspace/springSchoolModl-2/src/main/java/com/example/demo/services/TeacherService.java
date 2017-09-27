package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entites.Teacher;
import com.example.demo.entites.Teacher;
import com.example.demo.repositories.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository repository;

	public List<Teacher> list(){
		return repository.findAll();
	}
	
	public Teacher findById(Integer id) {
		return repository.findOne(id);
	}
	
	public List<Teacher> findTeachersByName(String name) {
		return repository.findByName(name);
	}
	

    public boolean isTeacherExis(Teacher teacher) {
    	return repository.exists(teacher.getId());
    }
	public Teacher create(Teacher teacher) {
		return repository.save(teacher);
	}
	
	public void deleteById(Integer inttger) {
		 repository.delete(inttger);
	}

	public void deleteAll() {
		 repository.deleteAll();
	}


	public void updateById(Teacher teacher) {
		  
	    repository.save(teacher);  
	
	}}
