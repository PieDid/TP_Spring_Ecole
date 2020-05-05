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

import com.intiformation.gestionecole.dao.ICoursDao;
import com.intiformation.gestionecole.domain.Cours;

@RestController
@RequestMapping("/cours-rest")
public class CoursRest {
	
	@Autowired
	private ICoursDao coursDao;

	public void setCoursDao(ICoursDao coursDao) {
		this.coursDao = coursDao;
	}


	@RequestMapping(value="/coursList", method=RequestMethod.GET)
	public List<Cours> listeCours() {
		return coursDao.getAll();
	
	}//end getall	
	
	
	@RequestMapping(value="/coursAdd", method=RequestMethod.POST)
	public void saveCours(@RequestBody Cours cours) {
		
		coursDao.addCours(cours);
		
	}//end save
	
	@RequestMapping(value="/cours/{idCours}", method=RequestMethod.GET)
	public Cours getCours(@PathVariable("idCours") int pIdCours) {
		
		return coursDao.getById(pIdCours);
		
	}//end get
	
	
	@RequestMapping(value="/coursUpdate/{idCours}", method=RequestMethod.PUT)
	public void upCours (@PathVariable("idCours") int pIdCours, @RequestBody Cours cours) {
		
		coursDao.updateCours(cours);
		
	}//end update
	
	
	@RequestMapping(value="/coursDelete/{idCours}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delAide(@PathVariable("idCours") int pIdCours) {
		
		coursDao.delete(pIdCours);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete

}
