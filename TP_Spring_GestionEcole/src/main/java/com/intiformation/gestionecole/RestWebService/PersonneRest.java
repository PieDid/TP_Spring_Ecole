package com.intiformation.gestionecole.RestWebService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intiformation.gestionecole.dao.IPersonneDao;
import com.intiformation.gestionecole.domain.Personne;


@RestController
@RequestMapping("/personne-rest")
public class PersonneRest {

	
	@Autowired
	private IPersonneDao personDao;
	
	public void setPersonDao(IPersonneDao personDao) {
		this.personDao = personDao;
	}


	@RequestMapping(value="/personList", method=RequestMethod.GET)
	public List<Personne> listePersonne() {
		return personDao.getAllPerson();
	
	}//end getall	
	
	
	@RequestMapping(value="/personAdd", method=RequestMethod.POST)
	public void savePerson(@RequestBody Personne personne) {
		
		personDao.addPerson(personne);
		
	}//end save
	
	@RequestMapping(value="/personne/{identifiant}", method=RequestMethod.GET)
	public Personne getPerson(@PathVariable("identifiant") int pIdPersonne) {
		
		return personDao.getPersonById(pIdPersonne);
		
	}//end get
	
	
	@RequestMapping(value="/personUpdate/{identifiant}", method=RequestMethod.PUT)
	public void upPerson (@PathVariable("identifiant") int pIdPersonne, @RequestBody Personne personne) {
		
		personDao.updatePerson(personne);
		
	}//end update
	
	
	@RequestMapping(value="/personDelete/{identifiant}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delPerson(@PathVariable("identifiant") int pIdPersonne) {
		
		personDao.deletePerson(pIdPersonne);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
}
