package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entites.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;

	
	public List<Student> list(){
		return repository.findAll();
	}
	
	public Student findById(Integer id) {
		return repository.findOne(id);
	}
	
	public List<Student> findStudentsByName(String name) {
		return repository.findByName(name);
	}
	

    public boolean isStudentExis(Student student) {
    	return repository.exists(student.getId());
    }
	public Student create(Student student) {
		return repository.save(student);
	}
	
	public void deleteById(Integer inttger) {
		 repository.delete(inttger);
	}

	public void deleteAll() {
		 repository.deleteAll();
	}


	public void updateById(Student student) {
		  
	    repository.save(student);  
	
	}

}