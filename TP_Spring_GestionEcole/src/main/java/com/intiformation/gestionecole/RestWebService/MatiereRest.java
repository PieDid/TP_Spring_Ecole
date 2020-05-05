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

import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.domain.Matiere;

@RestController
@RequestMapping("/matiere-rest")
public class MatiereRest {

	@Autowired
	private IMatiereDao matDao;
	
	public void setMatDao(IMatiereDao matDao) {
		this.matDao = matDao;
	}


	@RequestMapping(value="/matiereList", method=RequestMethod.GET)
	public List<Matiere> listeMat() {
		return matDao.getAllMatiere();
	
	}//end getall	
	
	
	@RequestMapping(value="/matiereAdd", method=RequestMethod.POST)
	public void savePerson(@RequestBody Matiere mat) {
		
		matDao.addMatiere(mat);
		
	}//end save
	
	@RequestMapping(value="/matiere/{libelle}", method=RequestMethod.GET)
	public Matiere getMat(@PathVariable("libelle") String libMat) {
		
		return matDao.getByLibelle(libMat);
		
	}//end get
	
	
	@RequestMapping(value="/matiereUpdate/{libelle}", method=RequestMethod.PUT)
	public void upMat (@PathVariable("libelle") String libMat, @RequestBody Matiere mat) {
		
		matDao.updateMatiere(mat);
		
	}//end update
	
	
	@RequestMapping(value="/matiereDelete/{libelle}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delMat(@PathVariable("libelle") String libMat) {
		
		matDao.deleteMatiere(libMat);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
}
