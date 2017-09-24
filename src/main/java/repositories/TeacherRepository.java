package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

}
