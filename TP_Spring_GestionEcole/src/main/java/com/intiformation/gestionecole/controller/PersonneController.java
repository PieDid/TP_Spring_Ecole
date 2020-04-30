package com.intiformation.gestionecole.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.intiformation.gestionecole.dao.IEnseignantDao;
import com.intiformation.gestionecole.dao.IEtudiantDao;
import com.intiformation.gestionecole.dao.IPersonneDao;
import com.intiformation.gestionecole.domain.Administrateur;
import com.intiformation.gestionecole.domain.Enseignant;
import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.Personne;
import com.intiformation.gestionecole.validator.EtudiantValidator;
import com.intiformation.gestionecole.validator.PersonneValidator;


@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
public class PersonneController {
	
	// Couche Dao
	@Autowired
	private IPersonneDao personneDao;

	@Autowired
	private IAdminDao adminDao; 
	
	@Autowired
	private IEnseignantDao enseignantDao;

	@Autowired
	private IEtudiantDao etuDao;
	

	// Validateur
	@Autowired 
	private PersonneValidator personneValid;
	
	@Autowired
	private EtudiantValidator etuValid;
	
	
	// Setters pour injection Spring
	
	public void setPersonneDao(IPersonneDao personneDao) {
		this.personneDao =  personneDao;
	}
	
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void setEnseignantDao(IEnseignantDao enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public void setEtuDao(IEtudiantDao etuDao) {
		this.etuDao = etuDao;
	}

	public void setPersonneValid(PersonneValidator personneValid) {
		this.personneValid = personneValid;
	}
	
	
	/* Méthodes gestionnaires du Personne Controller */
	
	
	// Récupération de la liste des personnes et affichage 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/personList*" , method = RequestMethod.GET)
	public String generatePersonneList(Model model) {
		
		List<Personne> listePersonnes = java.util.Collections.emptyList();
		listePersonnes = personneDao.getAllPerson();
		
		model.addAttribute("attribut_listePersonnes", listePersonnes);
		
		return "personList";
	
	}//end ListPersonne
	
	// Récupération de la liste des admin et affichage 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adminList*" , method = RequestMethod.GET)
	public String generateAdminList(Model model) {
		
		List<Administrateur> listAdmin = java.util.Collections.emptyList();
		listAdmin = adminDao.getAllAdmin();
		
		model.addAttribute("attribut_listeAdmin", listAdmin);
		
		return "adminList";
	
	}//end ListAdmin
	
	// Récupération de la liste des enseignants et affichage 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/ensList*" , method = RequestMethod.GET)
	public String generateEnseignantList(Model model) {
		
		List<Enseignant> listeEnseignants = java.util.Collections.emptyList();
		listeEnseignants = enseignantDao.getAllEnseignant();
		
		model.addAttribute("attribut_listeEnseignants", listeEnseignants);
		
		return "ensList";
	
	}//end ListEnseignants
	
	// Récupération de la liste des étudiants et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/etuList*" , method = RequestMethod.GET)
	public String generateEtudiantList(Model model) {
		
		List<Etudiant> listeEtudiants = java.util.Collections.emptyList();
		listeEtudiants = etuDao.getAllEtudiant();
		
		model.addAttribute("attribut_listeEtudiants", listeEtudiants);
		
		return "etuList";
	
	}//end ListEtudiant
	
	
	
	
	// Suppression d'une personne
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/personDelete/{identifiant}"}, method=RequestMethod.GET)
	public String deletePersonne(@PathVariable("identifiant") int pIdPersonne, ModelMap model) {
		
		personneDao.deletePerson(pIdPersonne);;

		List<Personne> listePersonnes = personneDao.getAllPerson();
		
		model.addAttribute("attribut_listePersonnes", listePersonnes);
		
		return "personList";
		
	}//end deletePersonne
	
	
	// Suppression d'un admin
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/adminDelete/{identifiant}"}, method=RequestMethod.GET)
	public String deleteAdmin(@PathVariable("identifiant") int pIdAdmin, ModelMap model) {
		
		adminDao.deleteAdmin(pIdAdmin);

		List<Administrateur> listeAdmin = adminDao.getAllAdmin();
		
		model.addAttribute("attribut_listeAdmin", listeAdmin);
		
		return "adminList";
		
	}//end deletePersonne
	
	// Suppression d'un enseignant
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/ensDelete/{identifiant}"}, method=RequestMethod.GET)
	public String deleteEnseignant(@PathVariable("identifiant") int pIdEnseignant, ModelMap model) {
		
		enseignantDao.deleteEnseignant(pIdEnseignant);

		List<Enseignant> listeEnseignant = enseignantDao.getAllEnseignant();
		
		model.addAttribute("attribut_listeEnseignant", listeEnseignant);
		
		return "ensList";
		
	}//end deletePersonne
	
	// Suppression d'un étudiant
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/etuDelete/{identifiant}","/etudiant/remove/{identifiant}"}, method=RequestMethod.GET)
	public String deleteEtudiant(@PathVariable("identifiant") int pIdEtudiant, ModelMap model) {
		
		etuDao.deleteEtudiant(pIdEtudiant);

		List<Etudiant> listeEtudiant = etuDao.getAllEtudiant();
		
		model.addAttribute("attribut_listeEtudiant", listeEtudiant);
		
		return "etuList";
		
	}//end deletePersonne
	
	
	
	
	// Modification d'une personne
	// Formulaire
	
	@RequestMapping(value="/personUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdatePersonne(@RequestParam("identifiant") int pIdPersonne) {
		
		Personne personneUpdate = personneDao.getPersonById(pIdPersonne);
		
		return new ModelAndView("personUpdate", "personUpdateCommand", personneUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/personUpdate-meth", method=RequestMethod.POST)
	public String updatePersonne(@ModelAttribute("personUpdateCommand") Personne pPersonne, ModelMap model) {
		
		personneDao.updatePerson(pPersonne);;
		
		model.addAttribute("attribut_listePersonnes", personneDao.getAllPerson());
		
		return "personList";
	
	}//end update
	
	
	// Formulaire
	
	@RequestMapping(value="/adminUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateAdmin(@RequestParam("identifiant") int pIdAdmin) {
		
		Administrateur adminUpdate = adminDao.getAdminById(pIdAdmin);
		
		return new ModelAndView("adminUpdate", "adminUpdateCommand", adminUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adminUpdate-meth", method=RequestMethod.POST)
	public String updateAdmin(@ModelAttribute("adminUpdateCommand") Administrateur pAdmin, ModelMap model) {
		
		adminDao.updateAdmin(pAdmin);
		
		model.addAttribute("attribut_listeAdmin", adminDao.getAllAdmin());
		
		return "adminList";
	
	}//end update
	
	
	
	// Formulaire
	
	@RequestMapping(value="/etuUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateEtudiant(@RequestParam("identifiant") int pIdEtudiant) {
		
		Etudiant etuUpdate = etuDao.getEudiantById(pIdEtudiant);
		
		return new ModelAndView("etuUpdate", "etuUpdateCommand", etuUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/etuUpdate-meth", method=RequestMethod.POST)
	public String updateEtudiant(@ModelAttribute("etuUpdateCommand") Etudiant pEtudiant, ModelMap model) {
		
		etuDao.updateEtudiant(pEtudiant);
		
		model.addAttribute("attribut_listeEtudiant", etuDao.getAllEtudiant());
		
		return "etuList";
	
	}//end update
		
	
	
	// Formulaire
	
	@RequestMapping(value="/ensUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateEnseignant(@RequestParam("identifiant") int pIdEnseignant) {
		
		Enseignant ensUpdate = enseignantDao.getEnseignantById(pIdEnseignant);
		
		return new ModelAndView("ensUpdate", "ensUpdateCommand", ensUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/ensUpdate-meth", method=RequestMethod.POST)
	public String updateEnseignant(@ModelAttribute("ensUpdateCommand") Enseignant pEnseignant, ModelMap model) {
		
		enseignantDao.updateEnseignant(pEnseignant);
		
		model.addAttribute("attribut_listeEnseignant", enseignantDao.getAllEnseignant());
		
		return "ensList";
	
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
	
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/personAdd-meth", method=RequestMethod.POST)
	public String addPersonne (@ModelAttribute("personAddCommand") @Validated Personne pPersonne, ModelMap model, BindingResult result) {
		
		personneValid.validate(pPersonne, result);
		
		if (result.hasErrors()) {
			return "personAdd";
			
		}else {
			personneDao.addPerson(pPersonne);;

			model.addAttribute("attribut_listePersonnes", personneDao.getAllPerson());
			
			return "personList";
			
		}//end if
		
	}//end add
	
	
	@RequestMapping(value="/adminAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddAdmin() {
		
		Administrateur admin = new Administrateur();
		
		String objetCommandeAdmin = "adminAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandeAdmin, admin);
		
		String viewName = "adminAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adminAdd-meth", method=RequestMethod.POST)
	public String addAdmin (@ModelAttribute("adminAddCommand") @Validated Administrateur pAdmin, ModelMap model, BindingResult result) {
		
		personneValid.validate(pAdmin, result);
		
		if (result.hasErrors()) {
			return "adminAdd";
			
		}else {
			adminDao.addAdmin(pAdmin);

			model.addAttribute("attribut_listeAdmin", adminDao.getAllAdmin());
			
			return "adminList";
			
		}//end if
		
	}//end add
	
	
	
	
	@RequestMapping(value="/etuAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddEtudiant() {
		
		Etudiant etudiant = new Etudiant();
		
		String objetCommandeEtudiant = "etuAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandeEtudiant, etudiant);
		
		String viewName = "etuAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/etuAdd-meth", method=RequestMethod.POST)
	public String addEtudiant (@ModelAttribute("etuAddCommand") @Validated Etudiant pEtudiant, ModelMap model, BindingResult result) {
		
		etuValid.validate(pEtudiant, result);
		
		if (result.hasErrors()) {
			return "etuAdd";
			
		}else {
			etuDao.addEtudiant(pEtudiant);

			model.addAttribute("attribut_listeEtudiant", etuDao.getAllEtudiant());
			
			return "etuList";
			
		}//end if
		
	}//end add
	
	
	
	
	
	@RequestMapping(value="/ensAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddEnseignant() {
		
		Enseignant enseignant = new Enseignant();
		
		String objetCommandeEnseignant = "ensAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandeEnseignant, enseignant);
		
		String viewName = "ensAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/ensAdd-meth", method=RequestMethod.POST)
	public String addEnseignant (@ModelAttribute("ensAddCommand") @Validated Enseignant pEnseignant, ModelMap model, BindingResult result) {
		
		personneValid.validate(pEnseignant, result);
		
		if (result.hasErrors()) {
			return "ensAdd";
			
		}else {
			enseignantDao.addEnseignant(pEnseignant);

			model.addAttribute("attribut_listeEnseignant", enseignantDao.getAllEnseignant());
			
			return "ensList";
			
		}//end if
		
	}//end add
	
	
}//end class
