package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Teacher;
import com.example.demo.repositories.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository repository;

	
	public Iterable<Teacher> list(){
		return repository.findAll();
	}
	
	public Teacher findById(Integer id) {
		return repository.findOne(id);
	}
	
	public Iterable<Teacher> findByaName(String name) {
		return repository.findByTeacherName(name);
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


	public Teacher updateById(Teacher teacher, Integer id) {
		if(repository.findOne(id)!=null) {
		  
			Teacher teacher1 = new Teacher(teacher.getTeacherName(),teacher.getTeacherEmail());
			teacher1.setTeacherID(id);
		    repository.save(teacher1);  
		
		    return teacher1;
		}
		return null;
	}

}
