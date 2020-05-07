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

import com.intiformation.gestionecole.dao.AdminDao;
import com.intiformation.gestionecole.dao.EnseignantDao;
import com.intiformation.gestionecole.dao.EtudiantDao;
import com.intiformation.gestionecole.dao.IAdminDao;
import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.dao.IEnseignantDao;
import com.intiformation.gestionecole.dao.IEtudiantDao;
import com.intiformation.gestionecole.dao.IPersonneDao;
import com.intiformation.gestionecole.dao.PersonneDao;
import com.intiformation.gestionecole.domain.Administrateur;
import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.domain.Aide;
import com.intiformation.gestionecole.domain.Enseignant;
import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.Personne;
import com.intiformation.gestionecole.validator.EtudiantValidator;
import com.intiformation.gestionecole.validator.PersonneValidator;


@Controller
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
	
	@Autowired
	private IAdresseDao adresseDao;
	
	@Autowired
	private IAideDao aideDao;
	
	// Validateur
	@Autowired 
	private PersonneValidator personneValid;
	
	@Autowired
	private EtudiantValidator etuValid;
	
	
	// Setters pour injection Spring
	
	public void setPersonneDao(PersonneDao personneDao) {
		this.personneDao =  personneDao;
	}
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void setEnseignantDao(EnseignantDao enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public void setEtuDao(EtudiantDao etuDao) {
		this.etuDao = etuDao;
	}

	public void setPersonneValid(PersonneValidator personneValid) {
		this.personneValid = personneValid;
	}
	
	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}
	
	
	/* Méthodes gestionnaires du Personne Controller */
	
	
	// Récupération de la liste des personnes et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENS','ROLE_ETU')")
	@RequestMapping(value="/personList*" , method = RequestMethod.GET)
	public String generatePersonneList(Model model) {
		
		List<Personne> listePersonnes = java.util.Collections.emptyList();
		listePersonnes = personneDao.getAllPerson();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("personList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listePersonnes", listePersonnes);
		
		return "personList";
	
	}//end ListPersonne
	
	// Récupération de la liste des admin et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENS','ROLE_ETU')")
	@RequestMapping(value="/adminList*" , method = RequestMethod.GET)
	public String generateAdminList(Model model) {
		
		List<Administrateur> listAdmin = java.util.Collections.emptyList();
		listAdmin = adminDao.getAllAdmin();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adminList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAdmin", listAdmin);
		
		return "adminList";
	
	}//end ListAdmin
	
	// Récupération de la liste des enseignants et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENS','ROLE_ETU')")
	@RequestMapping(value="/ensList*" , method = RequestMethod.GET)
	public String generateEnseignantList(Model model) {
		
		List<Enseignant> listeEnseignants = java.util.Collections.emptyList();
		listeEnseignants = enseignantDao.getAllEnseignant();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("enseignantList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeEnseignant", listeEnseignants);
		
		return "enseignantList";
	
	}//end ListEnseignants
	
	// Récupération de la liste des étudiants et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENS','ROLE_ETU')")
	@RequestMapping(value="/etuList*" , method = RequestMethod.GET)
	public String generateEtudiantList(Model model) {
		
		List<Etudiant> listeEtudiants = java.util.Collections.emptyList();
		listeEtudiants = etuDao.getAllEtudiant();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeEtudiant", listeEtudiants);
		
		return "etudiantList";
	
	}//end ListEtudiant
	
	
	
	
	// Suppression d'une personne
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/personDelete/{identifiant}"}, method=RequestMethod.GET)
	public String deletePersonne(@PathVariable("identifiant") int pIdPersonne, ModelMap model) {
		
		personneDao.deletePerson(pIdPersonne);;

		List<Personne> listePersonnes = personneDao.getAllPerson();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("personList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listePersonnes", listePersonnes);
		
		return "personList";
		
	}//end deletePersonne
	
	
	// Suppression d'un admin
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/adminDelete/{identifiant}"}, method=RequestMethod.GET)
	public String deleteAdmin(@PathVariable("identifiant") int pIdAdmin, ModelMap model) {
		
		adminDao.deleteAdmin(pIdAdmin);

		List<Administrateur> listeAdmin = adminDao.getAllAdmin();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adminList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAdmin", listeAdmin);
		
		return "adminList";
		
	}//end deletePersonne
	
	// Suppression d'un enseignant
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/ensDelete/{identifiant}"}, method=RequestMethod.GET)
	public String deleteEnseignant(@PathVariable("identifiant") int pIdEnseignant, ModelMap model) {
		
		enseignantDao.deleteEnseignant(pIdEnseignant);

		List<Enseignant> listeEnseignant = enseignantDao.getAllEnseignant();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("enseignantList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeEnseignant", listeEnseignant);
		
		return "enseignantList";
		
	}//end deletePersonne
	
	// Suppression d'un étudiant
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/etuDelete/{identifiant}","/etudiant/remove/{identifiant}"}, method=RequestMethod.GET)
	public String deleteEtudiant(@PathVariable("identifiant") int pIdEtudiant, ModelMap model) {
		
		etuDao.deleteEtudiant(pIdEtudiant);

		List<Etudiant> listeEtudiant = etuDao.getAllEtudiant();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeEtudiant", listeEtudiant);
		
		return "etudiantList";
		
	}//end deletePersonne
	
	
	
	
	// Modification d'une personne
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/personUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdatePersonne(@RequestParam("identifiant") int pIdPersonne) {
		
		Personne personneUpdate = personneDao.getPersonById(pIdPersonne);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("personUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("personUpdate", "personUpdateCommand", personneUpdate);
		
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
	@RequestMapping(value="/personUpdate-meth", method=RequestMethod.POST)
	public String updatePersonne(@ModelAttribute("personUpdateCommand") Personne pPersonne, ModelMap model) {
		
		personneDao.updatePerson(pPersonne);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("personList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listePersonnes", personneDao.getAllPerson());
		
		return "personList";
	
	}//end update
	
	
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adminUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateAdmin(@PathVariable("identifiant") int pIdAdmin) {
		
		Administrateur adminUpdate = adminDao.getAdminById(pIdAdmin);
		
		//------pour la liste déroulante du formulaire--------------------------------------
		List<Adresse> liste_adresses = adresseDao.getAll();
				
		//Map<String, Object> data = new HashMap<>();
		//data.put("adminUpdateCommand", adminUpdate);
		//data.put("listeAdresses", liste_adresses);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adminUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("adminUpdate", "adminUpdateCommand", adminUpdate);
		mov.addObject("listeAdresses", liste_adresses);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;

		//return new ModelAndView("adminUpdate", data);
		//----------------------------------------------------------------------------------
				
//		return new ModelAndView("adminUpdate", "adminUpdateCommand", adminUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adminUpdate-meth", method=RequestMethod.POST)
	public String updateAdmin(@ModelAttribute("adminUpdateCommand") Administrateur pAdmin, ModelMap model) {
		
		adminDao.updateAdmin(pAdmin);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adminList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAdmin", adminDao.getAllAdmin());
		
		return "adminList";
	
	}//end update
	
	
	
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/etuUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateEtudiant(@PathVariable("identifiant") int pIdEtudiant) {
		
		Etudiant etuUpdate = etuDao.getEudiantById(pIdEtudiant);
		
		//------pour la liste déroulante du formulaire--------------------------------------
		List<Adresse> liste_adresses = adresseDao.getAll();
				
		//Map<String, Object> data = new HashMap<>();
		//data.put("etuUpdateCommand", etuUpdate);
		//data.put("listeAdresses", liste_adresses);

		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("etudiantUpdate", "etuUpdateCommand", etuUpdate);
		mov.addObject("listeAdresses", liste_adresses);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
		//return new ModelAndView("etudiantUpdate", data);
		//----------------------------------------------------------------------------------
		
//		return new ModelAndView("etuUpdate", "etuUpdateCommand", etuUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/etuUpdate-meth", method=RequestMethod.POST)
	public String updateEtudiant(@ModelAttribute("etuUpdateCommand") Etudiant pEtudiant, ModelMap model) {
		
		etuDao.updateEtudiant(pEtudiant);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeEtudiant", etuDao.getAllEtudiant());
		
		return "etudiantList";
	
	}//end update
		
	
	
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/ensUpdate/{identifiant}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateEnseignant(@PathVariable("identifiant") int pIdEnseignant) {
		
		Enseignant ensUpdate = enseignantDao.getEnseignantById(pIdEnseignant);
		
		//------pour la liste déroulante du formulaire--------------------------------------
		List<Adresse> liste_adresses = adresseDao.getAll();
				
		//Map<String, Object> data = new HashMap<>();
		//data.put("ensUpdateCommand", ensUpdate);
		//data.put("listeAdresses", liste_adresses);

		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("enseignantUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("enseignantUpdate","ensUpdateCommand", ensUpdate);
		mov.addObject("listeAdresses", liste_adresses);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
		//return new ModelAndView("enseignantUpdate", data);
		//----------------------------------------------------------------------------------
		
//		return new ModelAndView("ensUpdate", "ensUpdateCommand", ensUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/ensUpdate-meth", method=RequestMethod.POST)
	public String updateEnseignant(@ModelAttribute("ensUpdateCommand") Enseignant pEnseignant, ModelMap model) {
		
		enseignantDao.updateEnseignant(pEnseignant);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("enseignantList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeEnseignant", enseignantDao.getAllEnseignant());
		
		return "enseignantList";
	
	}//end update
	
	
	
	
	
	// Ajout d'une nouvelle personne
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/personAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddPersonne() {
		
		Personne personne = new Personne();
		
		//------pour la liste déroulante du formulaire--------------------------------------
		List<Adresse> liste_adresses = adresseDao.getAll();
		
		//String objetCommandePersonne = "personAddCommand";
		
		//Map<String, Object> data = new HashMap<> ();
		//data.put(objetCommandePersonne, personne);
		//data.put("listeAdresses", liste_adresses);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("personAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("personAdd","personAddCommand", personne);
		mov.addObject("listeAdresses", liste_adresses);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
		//String viewName = "personAdd";
		
		//return new ModelAndView(viewName, data);
		
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

			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("personList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else
			
			model.addAttribute("attribut_listePersonnes", personneDao.getAllPerson());
			
			return "personList";
			
		}//end if
		
	}//end add
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/adminAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddAdmin() {
		
		Administrateur admin = new Administrateur();
		
		//------pour la liste déroulante du formulaire--------------------------------------
		List<Adresse> liste_adresses = adresseDao.getAll();
		
		//String objetCommandeAdmin = "adminAddCommand";
		
		//Map<String, Object> data = new HashMap<> ();
		//data.put(objetCommandeAdmin, admin);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("adminAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("adminAdd","adminAddCommand", admin);
		mov.addObject("listeAdresses", liste_adresses);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
		//String viewName = "adminAdd";
		
		//return new ModelAndView(viewName, data);
		
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

			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("adminList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else
			
			model.addAttribute("attribut_listeAdmin", adminDao.getAllAdmin());
			
			return "adminList";
			
		}//end if
		
	}//end add
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/etuAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddEtudiant() {
		
		Etudiant etudiant = new Etudiant();
		
		//------pour la liste déroulante du formulaire--------------------------------------
		List<Adresse> liste_adresses = adresseDao.getAll();
		
		//String objetCommandeEtudiant = "etuAddCommand";
		
		//Map<String, Object> data = new HashMap<> ();
		//data.put(objetCommandeEtudiant, etudiant);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("etudiantAdd","etuAddCommand", etudiant);
		mov.addObject("listeAdresses", liste_adresses);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
		//String viewName = "etudiantAdd";
		
		//return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/etuAdd-meth", method=RequestMethod.POST)
	public String addEtudiant (@ModelAttribute("etuAddCommand") @Validated Etudiant pEtudiant, ModelMap model, BindingResult result) {
		
		etuValid.validate(pEtudiant, result);
		
		if (result.hasErrors()) {
			return "etudiantAdd";
			
		}else {
			etuDao.addEtudiant(pEtudiant);

			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("etudiantList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else
			
			model.addAttribute("attribut_listeEtudiant", etuDao.getAllEtudiant());
			
			return "etudiantList";
			
		}//end if
		
	}//end add
	
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/ensAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddEnseignant() {
		
		Enseignant enseignant = new Enseignant();
		
		//------pour la liste déroulante du formulaire--------------------------------------
		List<Adresse> liste_adresses = adresseDao.getAll();
		
		//String objetCommandeEnseignant = "ensAddCommand";
		
		//Map<String, Object> data = new HashMap<> ();
		//data.put(objetCommandeEnseignant, enseignant);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("enseignantAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("enseignantAdd","ensAddCommand", enseignant);
		mov.addObject("listeAdresses", liste_adresses);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
		//String viewName = "enseignantAdd";
		
		//return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/ensAdd-meth", method=RequestMethod.POST)
	public String addEnseignant (@ModelAttribute("ensAddCommand") @Validated Enseignant pEnseignant, ModelMap model, BindingResult result) {
		
		personneValid.validate(pEnseignant, result);
		
		if (result.hasErrors()) {
			return "enseignantAdd";
			
		}else {
			enseignantDao.addEnseignant(pEnseignant);

			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("enseignantList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else
			
			model.addAttribute("attribut_listeEnseignant", enseignantDao.getAllEnseignant());
			
			return "enseignantList";
			
		}//end if
		
	}//end add
	
	
}//end class
