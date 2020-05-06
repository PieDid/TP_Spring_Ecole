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
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.dao.PromotionDao;
import com.intiformation.gestionecole.domain.Aide;
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
	
	@Autowired
	private IAideDao aideDao;
	
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

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
	@RequestMapping(value="/promotionList" , method = RequestMethod.GET)
	public String generatePromotionList(Model model) {
		
		List<Promotion> listePromotion = java.util.Collections.emptyList();
		listePromotion = promoDao.getAll();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("promotionList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listePromotion", listePromotion);
		
		return "promotionList";
	
	}//end ListPromo
	
	
	// Suppression d'une promotion
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/promotionDelete/{libelle}","/promotion/remove/{libelle}"}, method=RequestMethod.GET)
	public String deletePromotion(@PathVariable("libelle") String pLibelle, ModelMap model) {
		
		promoDao.deletePromotion(pLibelle);

		List<Promotion> listePromotion = promoDao.getAll();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("promotionList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listePromotion", listePromotion);
		
		return "promotionList";
		
	}//end delete	
	
	
	// Modification d'une promotion
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/promotionUpdate/{libelle}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdatePromotion(@PathVariable("libelle") String pLibelle) {
		
		Promotion promoUpdate = promoDao.getByLibelle(pLibelle);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("promotionUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("promotionUpdate", "promotionUpdateCommand", promoUpdate);
		
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
	}
	
	// Méthode Update 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/promotionUpdate-meth", method=RequestMethod.POST)
	public String updatePromotion(@ModelAttribute("promotionUpdateCommand") Promotion pPromotion, ModelMap model) {
		
		promoDao.updatePromotion(pPromotion);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("promotionList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listePromotion", promoDao.getAll());
		
		return "promotionList";
	
	}//end update
	
	
	// Ajout d'une nouvelle promotion
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/promotionAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddPromotion() {
		
		Promotion promotion = new Promotion();
		
		Map<String, Object> data = new HashMap<> ();
		data.put("promotionAddCommand", promotion);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("promotionAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("promotionAdd", data);
		
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else

		return mov;
		
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
			
			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("promotionList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else

			model.addAttribute("attribut_listePromotion", promoDao.getAll());
			
			return "promotionList";
			
		}//end if
		
	}//end add

}//end class
