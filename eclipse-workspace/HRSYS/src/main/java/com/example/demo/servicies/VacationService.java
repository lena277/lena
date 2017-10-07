package com.example.demo.servicies;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Vacation;
import com.example.demo.entities.VacationType1;
import com.example.demo.entities.VacationType2;
import com.example.demo.repositories.VacationRepository;

@Service
public class VacationService {


  @Autowired
  private VacationRepository repository;



  public Vacation create(Vacation type) {
	   
			 return repository.save(type);
	 
		
  }


  public List<Vacation> findAll() {
		 return repository.findAll();
					
			
	  }

  
 
  public void deleteById(Integer id) {
	   repository.delete(id);
   
  }
  public Vacation findById(Integer id ) {
	  return repository.findOne(id);
  }
  

  public boolean isExis(Vacation vacation) {
  	return repository.exists(vacation.getId());
  }

	

	public void deleteAll() {
		repository.deleteAll();
	}


	public Vacation updateById(Vacation vacation) {
		  
					 return repository.save((VacationType2)vacation);
			 
	}
  
   public List<Vacation> findByType(String type){
	   return repository.findByVacationType(type);
   }
 
  
}