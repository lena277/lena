package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;

	@Autowired
	private TeacherRepository teacherRepository;
	
	public Iterable<Student> list(){
		return repository.findAll();
	}
	
	public Student findById(Integer id) {
		return repository.findOne(id);
	}
	
	public Iterable<Student> findByaName(String name) {
		return repository.findBystudentName(name);
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


	public Student updateById(Student student, Integer id) {
		if(repository.findOne(id)!=null) {
		  
			Student s1 = new Student(student.getStudentName(),student.getStudentEmail());
	        s1.setStudentId(id);
		    repository.save(s1);  
		
		    return s1;
		}
		return null;
	}

	/*	public String saveTeacher() {
	Teacher teacher1 = new Teacher("Majdi" , "Majdi@gmail.com");
	Teacher teacher2 = new Teacher("hassan" , "hassan@gmail.com");
	
Student student1 = new Student("lenaas" , "lenaas@gmail.com");
Student student2 = new Student("lens" , "lens@gmail.com");

List<Teacher> teacher  = new ArrayList<>();
teacher.add(teacher1);
teacher.add(teacher2);


student1.setTeacher_id(teacher);
student2.setTeacher_id(teacher);



List<Student> student  = new ArrayList<>();
student.add(student1);

student.add(student2)
;
//teacher1.setStudent_id(student);
//teacher2.setStudent_id(student);
repository.save(student1);
repository.save(student1);

teacherRepository.save(teacher1);
teacherRepository.save(teacher2);
	

	return "saved";
}*/
	

	/*public String saveBus() {
		Bus bus = new Bus("lelle");
		
		Student student1 = new  Student("lena" , "lena@hotmail.com");
		//student1.setBus(bus);
		repository.save(student1);
		
		Student student2 = new  Student("Sujoud" , "sujoud@hotmail.com");
		//student2.setBus(bus);
		
		repository.save(student2);

		return "saved";
		
	}*/

}
