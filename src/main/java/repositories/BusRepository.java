package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Bus;

public interface BusRepository extends CrudRepository<Bus, Integer> {

}
