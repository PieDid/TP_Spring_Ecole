package com.intiformation.gestionecole.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.gestionecole.dao.GenericDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.dao.PromotionDao;
import com.intiformation.gestionecole.domain.Matiere;
import com.intiformation.gestionecole.domain.Personne;
import com.intiformation.gestionecole.domain.Promotion;
import com.intiformation.gestionecole.validator.PromotionValidator;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PromotionController {
	
	// Couche Dao
	@Autowired
	private IPromotionDao promoDao;
	
	// Validateur
	@Autowired 
	private PromotionValidator promoValid;
	
	// Setters pour injection Spring

	public void setPromoDao(PromotionDao promoDao) {
		this.promoDao =  promoDao;
	}

	public void setPromoValid(PromotionValidator promoValid) {
		this.promoValid = promoValid;
	}
	
	
	/* Méthodes gestionnaires du Promotion Controller */
	
	
	// Récupération de la liste des promotions et affichage 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/promotionList" , method = RequestMethod.GET)
	public String generatePromotionList(Model model) {
		
		List<Promotion> listePromotion = java.util.Collections.emptyList();
		listePromotion = promoDao.getAll();
		
		model.addAttribute("attribut_listePromotion", listePromotion);
		
		return "promotionList";
	
	}//end ListPromo
	
	
	// Suppression d'une promotion
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/promotionDelete/{libelle}","/promotion/remove/{idAide}"}, method=RequestMethod.GET)
	public String deletePromotion(@PathVariable("libelle") String pLibelle, ModelMap model) {
		
		promoDao.deletePromotion(pLibelle);

		List<Promotion> listePromotion = promoDao.getAll();
		
		model.addAttribute("attribut_listePromotion", listePromotion);
		
		return "promotionList";
		
	}//end delete	
	
	
	// Modification d'une promotion
	// Formulaire
	
	@RequestMapping(value="/promotionUpdate/{libelle}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdatePromotion(@PathVariable("libelle") String pLibelle) {
		
		Promotion promoUpdate = promoDao.getByLibelle(pLibelle);
		
		return new ModelAndView("promotionUpdate", "promotionUpdateCommand", promoUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/promotionUpdate-meth", method=RequestMethod.POST)
	public String updatePromotion(@ModelAttribute("promotionUpdateCommand") Promotion pPromotion, ModelMap model) {
		
		promoDao.updatePromotion(pPromotion);
		
		model.addAttribute("attribut_listePromotion", promoDao.getAll());
		
		return "promotionList";
	
	}//end update
	
	
	// Ajout d'une nouvelle promotion
	// Formulaire
	
	@RequestMapping(value="/promotionAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddPromotion() {
		
		Promotion promotion = new Promotion();
		
		String objetCommandePromotion = "promotionAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandePromotion, promotion);
		
		String viewName = "promotionAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/promotionAdd-meth", method=RequestMethod.POST)
	public String addPromotion (@ModelAttribute("promotionAddCommand") @Validated Promotion pPromotion, ModelMap model, BindingResult result) {
		
		promoValid.validate(pPromotion, result);
		
		if (result.hasErrors()) {
			return "promotionAdd";
			
		}else {
			promoDao.addPromotion(pPromotion);

			model.addAttribute("attribut_listePromotion", promoDao.getAll());
			
			return "promotionList";
			
		}//end if
		
	}//end add

}//end class
