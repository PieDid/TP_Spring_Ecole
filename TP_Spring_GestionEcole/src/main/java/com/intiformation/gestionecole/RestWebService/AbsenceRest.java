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

import com.intiformation.gestionecole.dao.IAbsenceDao;
import com.intiformation.gestionecole.domain.EtudiantCours;



@RestController
@RequestMapping("/absence-rest")
public class AbsenceRest {

	@Autowired
	private IAbsenceDao absDao;

	public void setAbsDao(IAbsenceDao absDao) {
		this.absDao = absDao;
	}


	@RequestMapping(value="/etudiantCoursList", method=RequestMethod.GET)
	public List<EtudiantCours> listeAbs() {
		return absDao.getAll();
	
	}//end getall	
	
	
	@RequestMapping(value="/etudiantCoursAdd", method=RequestMethod.POST)
	public void saveAbs(@RequestBody EtudiantCours abs) {
		
		absDao.add(abs);
		
	}//end save
	
	@RequestMapping(value="/etudiantCours/{idEtudiantCours}", method=RequestMethod.GET)
	public EtudiantCours getAbs(@PathVariable("idEtudiantCours") int idAbs) {
		
		return absDao.getById(idAbs);
		
	}//end get
	
	
	@RequestMapping(value="/etudiantCoursUpdate/{idEtudiantCours}", method=RequestMethod.PUT)
	public void upAbs (@PathVariable("idEtudiantCours") int idAbs, @RequestBody EtudiantCours abs) {
		
		absDao.update(abs);
		
	}//end update
	
	
	@RequestMapping(value="/etudiantCoursDelete/{idEtudiantCours}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delAbs(@PathVariable("idEtudiantCours") int idAbs) {
		
		absDao.delete(idAbs);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
}
