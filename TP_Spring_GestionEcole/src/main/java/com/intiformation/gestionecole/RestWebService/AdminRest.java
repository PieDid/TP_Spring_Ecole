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

import com.intiformation.gestionecole.dao.IAdminDao;
import com.intiformation.gestionecole.domain.Administrateur;


@RestController
@RequestMapping("/admin-rest")
public class AdminRest {
	
	@Autowired
	private IAdminDao adminDao;

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
	@RequestMapping(value="/adminList", method=RequestMethod.GET)
	public List<Administrateur> listeAdmin() {
		return adminDao.getAllAdmin();
	
	}//end getall	
	
	
	@RequestMapping(value="/adminAdd", method=RequestMethod.POST)
	public void saveAdmin(@RequestBody Administrateur admin) {
		
		adminDao.addAdmin(admin);
		
	}//end save
	
	@RequestMapping(value="/admin/{identifiant}", method=RequestMethod.GET)
	public Administrateur getAdmin(@PathVariable("identifiant") int pIdAdmin) {
		
		return adminDao.getAdminById(pIdAdmin);
		
	}//end get
	
	
	@RequestMapping(value="/adminUpdate/{identifiant}", method=RequestMethod.PUT)
	public void upAdmin (@PathVariable("identifiant") int pIdAdmin, @RequestBody Administrateur pAdmin) {
		
		adminDao.updateAdmin(pAdmin);
		
	}//end update
	
	
	@RequestMapping(value="/adminDelete/{identifiant}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delAdmin(@PathVariable("identifiant") int pIdAdmin) {
		
		adminDao.deleteAdmin(pIdAdmin);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
	

}//end class
