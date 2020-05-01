package com.intiformation.gestionecole.RestWebService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.domain.Adresse;


public class AdresseRest {

	@Autowired
	private IAdresseDao adressDao;

	public void setAdressDao(IAdresseDao adressDao) {
		this.adressDao = adressDao;
	}


	@RequestMapping(value="/adresseList", method=RequestMethod.GET)
	public List<Adresse> listeAdress() {
		return adressDao.getAll();
	
	}//end getall	
	
	
	@RequestMapping(value="/adresseAdd", method=RequestMethod.POST)
	public void saveAdress(@RequestBody Adresse adress) {
		
		adressDao.addAdresse(adress);
		
	}//end save
	
	@RequestMapping(value="/adresse/{idAdresse}", method=RequestMethod.GET)
	public Adresse getAdress(@PathVariable("idAdresse") int pIdAdress) {
		
		return adressDao.getById(pIdAdress);
		
	}//end get
	
	
	@RequestMapping(value="/adresseUpdate/{idAdresse}", method=RequestMethod.PUT)
	public void upPerson (@PathVariable("idAdresse") int pIdAdress, @RequestBody Adresse adress) {
		
		adressDao.updateAdresse(adress);
		
	}//end update
	
	
	@RequestMapping(value="/adresseDelete/{idAdresse}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delPerson(@PathVariable("idAdresse") int pIdAdress) {
		
		adressDao.delete(pIdAdress);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
}
