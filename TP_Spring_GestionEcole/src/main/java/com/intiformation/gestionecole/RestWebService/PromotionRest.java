package com.intiformation.gestionecole.RestWebService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.domain.Promotion;


public class PromotionRest {

	@Autowired
	private IPromotionDao promoDao;

	public void setPromoDao(IPromotionDao promoDao) {
		this.promoDao = promoDao;
	}


	@RequestMapping(value="/promotionList", method=RequestMethod.GET)
	public List<Promotion> listePromo() {
		return promoDao.getAll();
	
	}//end getall	
	
	
	@RequestMapping(value="/promotionAdd", method=RequestMethod.POST)
	public void savePromo(@RequestBody Promotion promo) {
		
		promoDao.addPromotion(promo);
		
	}//end save
	
	@RequestMapping(value="/promotion/{libelle}", method=RequestMethod.GET)
	public Promotion getPromo(@PathVariable("libelle") String libPromo) {
		
		return promoDao.getByLibelle(libPromo);
		
	}//end get
	
	
	@RequestMapping(value="/promotionUpdate/{libelle}", method=RequestMethod.PUT)
	public void upPromo (@PathVariable("libelle") String libPromo, @RequestBody Promotion promo) {
		
		promoDao.updatePromotion(promo);
		
	}//end update
	
	
	@RequestMapping(value="/promotionDelete/{libelle}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delPromo(@PathVariable("libelle") String libPromo) {
		
		promoDao.deletePromotion(libPromo);
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		
	}//end delete
	
	
}
