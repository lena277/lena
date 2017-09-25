package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entites.Bus;

public interface BusRepository extends CrudRepository<Bus, Integer> {

	Iterable <Bus> findByBusDriverName(String name);
}
