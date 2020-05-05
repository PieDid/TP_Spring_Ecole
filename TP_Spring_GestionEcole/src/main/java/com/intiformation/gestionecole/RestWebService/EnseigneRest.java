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

import com.intiformation.gestionecole.dao.IEnseigneDao;
import com.intiformation.gestionecole.domain.Enseigne;


@RestController
@RequestMapping("/enseigne-rest")
public class EnseigneRest {
	
	@Autowired
	private IEnseigneDao enseigneDao;

	public void setEnseigneDao(IEnseigneDao enseigneDao) {
		this.enseigneDao = enseigneDao;
	}
	
	@RequestMapping(value="/enseigneList", method=RequestMethod.GET)
	public List<Enseigne> listeEnseigne() {
		return enseigneDao.getAll();
	
	}//end getall	
	
	
	@RequestMapping(value="/enseigneAdd", method=RequestMethod.POST)
	public void saveEnseigne(@RequestBody Enseigne enseigne) {
		
		enseigneDao.addEnseigne(enseigne);
		
	}//end save
	
	@RequestMapping(value="/enseigne/{id_enseigne}", method=RequestMethod.GET)
	public Enseigne getEnseigne(@PathVariable("id_enseigne") int pIdEnseigne) {
		
		return enseigneDao.getById(pIdEnseigne);
		
	}//end get
	
	
	@RequestMapping(value="/enseigneUpdate/{id_enseigne}", method=RequestMethod.PUT)
	public void upEnseigne (@PathVariable("id_enseigne") int pIdEnseigne, @RequestBody Enseigne enseigne) {
		
		enseigneDao.updateEnseigne(enseigne);
		
	}//end update
	
	@RequestMapping(value="/enseigneDelete/{id_enseigne}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delPerson(@PathVariable("id_enseigne") int pIdEnseigne) {
		
		enseigneDao.delete(pIdEnseigne);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
	

}
