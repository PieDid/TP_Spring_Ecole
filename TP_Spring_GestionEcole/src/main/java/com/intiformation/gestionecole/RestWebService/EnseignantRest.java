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

import com.intiformation.gestionecole.dao.IEnseignantDao;
import com.intiformation.gestionecole.domain.Enseignant;

@RestController
@RequestMapping("/ens-rest")
public class EnseignantRest {
	

	@Autowired
	private IEnseignantDao ensDao;

	public void setEnsDao(IEnseignantDao ensDao) {
		this.ensDao = ensDao;
	}


	@RequestMapping(value="/ensList", method=RequestMethod.GET)
	public List<Enseignant> listeEns() {
		
		return ensDao.getAllEnseignant();
	
	}//end getall	
	
	
	@RequestMapping(value="/ensAdd", method=RequestMethod.POST)
	public void saveEnseignant(@RequestBody Enseignant ens) {
		
		ensDao.addEnseignant(ens);
		
	}//end save
	
	@RequestMapping(value="/enseignant/{identifiant}", method=RequestMethod.GET)
	public Enseignant getEns(@PathVariable("identifiant") int pIdEns) {
		
		return ensDao.getEnseignantById(pIdEns);
		
	}//end get
	
	
	@RequestMapping(value="/ensUpdate/{identifiant}", method=RequestMethod.PUT)
	public void upEns (@PathVariable("identifiant") int pIdEns, @RequestBody Enseignant ens) {
		
		ensDao.updateEnseignant(ens);
		
	}//end update
	
	
	@RequestMapping(value="/ensDelete/{identifiant}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delEns(@PathVariable("identifiant") int pIdEns) {
		
		ensDao.deleteEnseignant(pIdEns);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
	
}
