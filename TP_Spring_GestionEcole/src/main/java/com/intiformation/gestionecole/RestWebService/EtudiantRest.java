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

import com.intiformation.gestionecole.dao.IEtudiantDao;
import com.intiformation.gestionecole.domain.Etudiant;


@RestController
@RequestMapping("/etu-rest")
public class EtudiantRest {

	@Autowired
	private IEtudiantDao etuDao;
	
	public void setEtuDao(IEtudiantDao etuDao) {
		this.etuDao = etuDao;
	}


	@RequestMapping(value="/etuList", method=RequestMethod.GET)
	public List<Etudiant> listeEtu() {
		return etuDao.getAllEtudiant();
	
	}//end getall	
	
	
	@RequestMapping(value="/etuAdd", method=RequestMethod.POST)
	public void saveEtu(@RequestBody Etudiant etu) {
		
		etuDao.addEtudiant(etu);
		
	}//end save
	
	@RequestMapping(value="/etudiant/{identifiant}", method=RequestMethod.GET)
	public Etudiant getEtu(@PathVariable("identifiant") int idEtu) {
		
		return etuDao.getEudiantById(idEtu);
		
	}//end get
	
	
	@RequestMapping(value="/etuUpdate/{identifiant}", method=RequestMethod.PUT)
	public void upEtu (@PathVariable("identifiant") int idEtu, @RequestBody Etudiant etu) {
		
		etuDao.updateEtudiant(etu);
		
	}//end update
	
	
	@RequestMapping(value="/etuDelete/{identifiant}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delEtu(@PathVariable("identifiant") int idEtu) {
		
		etuDao.deleteEtudiant(idEtu);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
}
