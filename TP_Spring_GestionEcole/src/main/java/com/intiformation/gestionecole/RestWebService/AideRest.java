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

import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.domain.Aide;


@RestController
@RequestMapping("/aide-rest")
public class AideRest {
	
	@Autowired
	private IAideDao aideDao;

	public void setAideDao(IAideDao aideDao) {
		this.aideDao = aideDao;
	}


	@RequestMapping(value="/aideList", method=RequestMethod.GET)
	public List<Aide> listeAide() {
		return aideDao.getAll();
	
	}//end getall	
	
	
	@RequestMapping(value="/aideAdd", method=RequestMethod.POST)
	public void saveAide(@RequestBody Aide aide) {
		
		aideDao.add(aide);
		
	}//end save
	
	@RequestMapping(value="/aide/{idAide}", method=RequestMethod.GET)
	public Aide getAide(@PathVariable("idAide") int pIdAide) {
		
		return aideDao.getById(pIdAide);
		
	}//end get
	
	
	@RequestMapping(value="/aideUpdate/{idAide}", method=RequestMethod.PUT)
	public void upAide (@PathVariable("idAide") int pIdAide, @RequestBody Aide aide) {
		
		aideDao.update(aide);
		
	}//end update
	
	
	@RequestMapping(value="/aideDelete/{idAide}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delAide(@PathVariable("idAide") int pIdAide) {
		
		aideDao.delete(pIdAide);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete

}
