package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.entities.Vacation;

@NoRepositoryBean
public interface VacationBaseRepository<T extends Vacation> 
extends CrudRepository<T, Integer> {

  public T findByLongOfVacation(Integer longOfVacation);
  
} 