package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Bus;
import com.example.demo.repositories.BusRepository;

@Service
public class BusService {
	@Autowired
	private BusRepository repository;

	
	
	public Iterable<Bus> list(){
		return repository.findAll();
	}
	
	public Bus findById(Integer id) {
		return repository.findOne(id);
	}
	
	public Iterable<Bus> findByDriverName(String name) {
		return repository.findByBusDriverName(name);
	}
	


	public Bus create(Bus bus) {
		return repository.save(bus);
	}
	
	public void deleteById(Integer inttger) {
		 repository.delete(inttger);
	}

	public void deleteAll() {
		 repository.deleteAll();
	}


	public Bus updateById(Bus bus, Integer id) {
		if(repository.findOne(id)!=null) {
		  
			Bus bus1 = new Bus(bus.getBusDriverName());
	        bus1.setBusId(id);
		    repository.save(bus1);  
		
		    return bus1;
		}
		return null;
	}

}
