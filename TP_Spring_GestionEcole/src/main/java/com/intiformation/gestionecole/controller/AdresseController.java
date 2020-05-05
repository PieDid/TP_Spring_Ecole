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

import com.intiformation.gestionecole.dao.AideDao;
import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.domain.Aide;
import com.intiformation.gestionecole.validator.AdresseValidator;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ENS', 'ROLE_ADMIN')")
public class AdresseController {

	// Couche Dao
	@Autowired
	private IAdresseDao adresseDao;
	
	@Autowired
	private IAideDao aideDao;
	
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
	
	@PreAuthorize("hasAnyRole('ROLE_ENS','ROLE_ADMIN' )")
	@RequestMapping(value="/adresseList" , method = RequestMethod.GET)
	public String generateAdresseList(Model model) {
		
		List<Adresse> listeAdresse = java.util.Collections.emptyList();
		listeAdresse = adresseDao.getAll();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adresseList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		
		model.addAttribute("attribut_listeAdresse", listeAdresse);
		return "adresseList";
		
	}//get all
	
	
	// Suppression d'une adresse
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/adresseDelete/{idAdresse}"}, method=RequestMethod.GET)
	public String deleteAdresse(@PathVariable("idAdresse") int pIdAdresse, ModelMap model) {
		
		adresseDao.delete(pIdAdresse);
		
		List<Adresse> listeAdresse = adresseDao.getAll();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adresseList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAdresse", listeAdresse);
		
		return "adresseList";
		
	}//end delete
	
	
	// Modification d'une promotion
	// Formulaire
	
	@RequestMapping(value="/adresseUpdate/{idAdresse}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateAdresse(@PathVariable("idAdresse") int pIdAdresse) {
		
		Adresse adresseUpdate = adresseDao.getById(pIdAdresse);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adresseUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("adresseUpdate", "adresseUpdateCommand", adresseUpdate);
		
		
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
	@RequestMapping(value="/adresseUpdate-meth", method=RequestMethod.POST)
	public String updateAdresse(@ModelAttribute("adresseUpdateCommand") Adresse pAdresse, ModelMap model) {
			
		adresseDao.updateAdresse(pAdresse);
			
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adresseList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAdresse", adresseDao.getAll());
			
		return "adresseList";
			
	}//end update
		
		
		
	// Ajout d'une nouvelle adresse
	// Formulaire
		
	@RequestMapping(value="/adresseAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddAdresse() {
		
		Adresse adresse = new Adresse();
		
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adresseAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("adresseAdd", "adresseAddCommand", adresse);
		
		
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
	@RequestMapping(value="/adresseAdd-meth", method=RequestMethod.POST)
	public String addAdresse (@ModelAttribute("adresseAddCommand") @Validated Adresse pAdresse, ModelMap model, BindingResult result) {
		
		adresseValid.validate(pAdresse, result);
		
		if (result.hasErrors()) {
			return "adresseAdd";
			
		}else {
			adresseDao.addAdresse(pAdresse);

			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("adresseList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else
			
			model.addAttribute("attribut_listeAdresse", adresseDao.getAll());
			
			return "adresseList";
			
		}//end if
		
	}//end add
		
	
	
}//end class
