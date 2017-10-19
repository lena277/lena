package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Vacation;
import com.example.demo.entities.Role;
import com.example.demo.entities.SickVacation;

@Transactional
public interface VacationRepository  extends CrudRepository<Vacation, Integer>{
 List<Vacation> findAll();
 Vacation findOne(Integer id);
 List<Vacation>findByVacationType(String type);
 List<Vacation> findByIsValidate(boolean isValidate);
}
