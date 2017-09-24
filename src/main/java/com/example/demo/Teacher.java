package com.example.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
	
	@Id
	@Column(name ="Teacher_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teacherID;
	
	@Column(name ="Teacher_Name")
	private String teacherName;
	
	@Column(name ="Teacher_Email")
	private String teacherEmail;
	
    @ManyToMany(mappedBy = "teacher")
    private List <Student> student;
    

    

	public Teacher(String teacherName, String teacherEmail) {
		super();
		this.teacherName = teacherName;
		this.teacherEmail = teacherEmail;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent_id(List<Student> student) {
		this.student= student;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}


	

}
