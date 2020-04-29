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
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.validator.AdresseValidator;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdresseController {

	// Couche Dao
	@Autowired
	private IAdresseDao adresseDao;
	
	// Validator
	@Autowired
	private AdresseValidator adresseValid;
	
	
	// Setters pour injection Spring

	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}

	public void setAdresseValid(AdresseValidator adresseValid) {
		this.adresseValid = adresseValid;
	}
	
	
	/* Méthodes gestionnaires du Promotion Controller */
	
	
	// Récupération de la liste des adresses et affichage 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adresseList" , method = RequestMethod.GET)
	public String generateAdresseList(Model model) {
		
		List<Adresse> listeAdresse = java.util.Collections.emptyList();
		listeAdresse = adresseDao.getAll();
		
		model.addAttribute("attribut_listeAdresse", listeAdresse);
		
		return "adresseList";
		
	}//get all
	
	
	// Suppression d'une adresse
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/adresseDelete/{idAdresse}"}, method=RequestMethod.GET)
	public String deleteAdresse(@PathVariable("idAdresse") int pIdAdresse, ModelMap model) {
		
		adresseDao.delete(pIdAdresse);
		
		List<Adresse> listeAdresse = adresseDao.getAll();
		
		model.addAttribute("attribut_listeAdresse", listeAdresse);
		
		return "adresseList";
		
	}//end delete
	
	
	// Modification d'une promotion
	// Formulaire
	
	@RequestMapping(value="/adresseUpdate/{idAdresse}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateAdresse(@PathVariable("idAdresse") int pIdAdresse) {
		
		Adresse adresseUpdate = adresseDao.getById(pIdAdresse);
		
		return new ModelAndView("adresseUpdate", "adresseUpdateCommand", adresseUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adresseUpdate-meth", method=RequestMethod.POST)
	public String updateAdresse(@ModelAttribute("adresseUpdateCommand") Adresse pAdresse, ModelMap model) {
			
		adresseDao.updateAdresse(pAdresse);
			
		model.addAttribute("attribut_listeAdresse", adresseDao.getAll());
			
		return "adresseList";
			
	}//end update
		
		
		
	// Ajout d'une nouvelle adresse
	// Formulaire
		
	@RequestMapping(value="/adresseAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddAdresse() {
		
		Adresse adresse = new Adresse();
		
		String objetCommandeAdresse = "adresseAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandeAdresse, adresse);
		
		String viewName = "adresseAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adresseAdd-meth", method=RequestMethod.POST)
	public String addAdresse (@ModelAttribute("adresseAddCommand") @Validated Adresse pAdresse, ModelMap model, BindingResult result) {
		
		adresseValid.validate(pAdresse, result);
		
		if (result.hasErrors()) {
			return "promotionAdd";
			
		}else {
			adresseDao.addAdresse(pAdresse);

			model.addAttribute("attribut_listeAdresse", adresseDao.getAll());
			
			return "adresseList";
			
		}//end if
		
	}//end add
		
	
	
}//end class
