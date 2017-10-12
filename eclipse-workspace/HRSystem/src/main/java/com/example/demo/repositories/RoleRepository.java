package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
     
	 List <Role> findByName(String name);
	 List <Role> findAll();
	 
	 
}
