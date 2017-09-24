package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
	

}
