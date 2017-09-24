package repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import entities.Student;

public interface StudentRepository extends CrudRepository<Student,Integer>{
	
	

}
