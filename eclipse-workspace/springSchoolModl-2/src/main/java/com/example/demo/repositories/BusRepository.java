package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entites.Bus;

public interface BusRepository extends CrudRepository<Bus, Integer> {

	List <Bus> findByDriverName(String name);
	
	List <Bus> findAll();
}
