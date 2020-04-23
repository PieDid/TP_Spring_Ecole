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

import com.intiformation.gestionecole.dao.IAdminDao;
import com.intiformation.gestionecole.dao.IPersonneDao;
import com.intiformation.gestionecole.domain.Administrateur;
import com.intiformation.gestionecole.domain.Personne;
import com.intiformation.gestionecole.validator.PersonneValidator;


@Controller
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ANONYMOUS')")
public class PersonneController {
	
	// Couche Dao
	@Autowired
	private IPersonneDao personneDao;
	
	@Autowired
	private IAdminDao adminDao;
	
	@Autowired
	public PersonneController(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	@Autowired
	public PersonneController(IPersonneDao personneDao) {
		this.personneDao = personneDao;
	}

	
	// Validateur
	@Autowired 
	private PersonneValidator personneValid;
	
	
	// Setters pour injection Spring
	
	public void setPersonneDao(IPersonneDao personneDao) {
		this.personneDao = personneDao;
	}

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void setPersonneValid(PersonneValidator personneValid) {
		this.personneValid = personneValid;
	}
	
	
	/* Méthodes gestionnaires du Personne Controller */
	
	
	// Récupération de la liste des personnes et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_ANONYMOUS')")
	@RequestMapping(value="/personList*" , method = RequestMethod.GET)
	public String generatePersonneList(Model model) {
		
		List<Personne> listePersonnes = java.util.Collections.emptyList();
		listePersonnes = personneDao.getAll();
		
		model.addAttribute("attribut_listePersonnes", listePersonnes);
		
		return "personList";
	
	}//end List
	
	
	// Suppression d'une personne
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/personne/delete/{identifiant}","/personne/remove/{identifiant}"}, method=RequestMethod.GET)
	public String deletePersonne(@PathVariable("identifiant") int pIdPersonne, ModelMap model) {
		
		personneDao.delete(pIdPersonne);

		List<Personne> listePersonnes = personneDao.getAll();
		
		model.addAttribute("attribut_listePersonnes", listePersonnes);
		
		return "personList";
		
	}//end delete
	
	
	// Modification d'une personne
	// Formulaire
	
	@RequestMapping(value="/personUpdate", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdatePersonne(@RequestParam("identifiant") int pIdPersonne) {
		
		Personne personneUpdate = personneDao.getById(pIdPersonne);
		
		return new ModelAndView("personUpdate", "personUpdateCommand", personneUpdate);
		
	}
	
	// Méthode Update (Administrateur)
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/personne/update", method=RequestMethod.POST)
	public String updatePersonne(@ModelAttribute("personUpdateCommand") Administrateur pAdmin, ModelMap model) {
		
		adminDao.updateAdmin(pAdmin);
		
		model.addAttribute("attribut_listePersonnes", personneDao.getAll());
		
		return "personList";
	
	}//end update
	
	
	
	// Ajout d'une nouvelle personne
	// Formulaire
	
	@RequestMapping(value="/personAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddPersonne() {
		
		Personne personne = new Personne();
		
		String objetCommandePersonne = "personAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandePersonne, personne);
		
		String viewName = "personAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add (Administrateur)
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/personAdd", method=RequestMethod.GET)
	public String addPersonne (@ModelAttribute("personAddCommand") @Validated Administrateur pAdmin, ModelMap model, BindingResult result) {
		
		personneValid.validate(pAdmin, result);
		
		if (result.hasErrors()) {
			return "personAdd";
			
		}else {
			adminDao.addAdmin(pAdmin);

			model.addAttribute("attribut_listePersonnes", personneDao.getAll());
			
			return "personList";
			
		}//end if
		
	}//end add

}//end class
