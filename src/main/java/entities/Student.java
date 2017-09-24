package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "student")
public class Student {

	
	public Student() {
		
	}
	public Student(String studentName, String studentEmail) {
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		
	}
	
	public Student(String studentName, String studentEmail , Bus bus) {
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		//this.setBus(bus);
	}
	@Id
	@Column(name="Student_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Integer studentId;
	
	@Column(name="Student_Name")
	private String studentName;
	
	@Column(name="Student_Email")
	private String studentEmail;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "Bus_id")
//	private Bus bus;
	
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "Student_id", referencedColumnName = "Student_id"), inverseJoinColumns = @JoinColumn(name = "Teacher_id", referencedColumnName = "Teacher_id"))
	 private List <Teacher> teacher;
	


	
	 
	public List<Teacher> getTeacher() {
		return teacher;
	}
	public void setTeacher_id(List<Teacher> teacher) {
		this.teacher = teacher;
	}
//	public Bus getBus() {
//		return bus;
//	}
//	public void setBus(Bus bus) {
//		this.bus = bus;
//	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	

}
