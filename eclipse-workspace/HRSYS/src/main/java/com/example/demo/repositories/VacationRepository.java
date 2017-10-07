package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.entities.Vacation;
import com.example.demo.entities.VacationType1;

@Transactional
public interface VacationRepository extends VacationBaseRepository<Vacation>{
 List<Vacation> findAll();
 List<Vacation>findByVacationType(String type);
}
