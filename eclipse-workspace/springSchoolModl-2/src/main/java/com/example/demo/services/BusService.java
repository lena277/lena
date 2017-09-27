package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entites.Bus;
import com.example.demo.entites.Bus;
import com.example.demo.repositories.BusRepository;

@Service
public class BusService {
	@Autowired
	private BusRepository repository;

	public List<Bus> list(){
		return repository.findAll();
	}
	
	public Bus findById(Integer id) {
		return repository.findOne(id);
	}
	
	public List<Bus> findDriverName(String name) {
		return repository.findByDriverName(name);
	}
	

    public boolean isBusExis(Bus bus) {
    	return repository.exists(bus.getId());
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


	public void updateById(Bus bus) {
		  
	    repository.save(bus);  
	
	}
}
