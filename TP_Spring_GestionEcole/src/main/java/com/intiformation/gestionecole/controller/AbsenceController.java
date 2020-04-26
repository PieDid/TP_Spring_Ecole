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

import com.intiformation.gestionecole.dao.GenericDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.EtudiantCours;
import com.intiformation.gestionecole.domain.Personne;
import com.intiformation.gestionecole.validator.AbsenceValidator;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
public class AbsenceController {

	// Couche Dao
	@Autowired
	private IGenericDao<EtudiantCours> absDao = new GenericDao<EtudiantCours>(EtudiantCours.class);
	
	// Validateur
	@Autowired
	private AbsenceValidator absValid;

	
	// Setters pour Injection Spring
	
	public void setAbsDao(IGenericDao<EtudiantCours> absDao) {
		this.absDao = absDao;
	}

	public void setAbsValid(AbsenceValidator absValid) {
		this.absValid = absValid;
	}
	
	
	/* Méthodes gestionnaires du Absence Controller */
	
	
	// Récupération de la liste des absences et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/absList*" , method = RequestMethod.GET)
	public String generateAbsenceList(Model model) {
		
		List<EtudiantCours> listeAbsences = java.util.Collections.emptyList();
		listeAbsences = absDao.getAll();
		
		model.addAttribute("attribut_listeAbsences", listeAbsences);
		
		return "absList";
	
	}//end List
	
	
	// Récupération de la liste des absences d'un étudiant 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
	@RequestMapping(value="/etuAbsList*" , method = RequestMethod.GET)
	public String generateAbsenceListEtu(Model model) {
		
		Etudiant pEtudiant = new Etudiant();
		
		List<EtudiantCours> listeAbsencesEtu = java.util.Collections.emptyList();
		listeAbsencesEtu = absDao.getByEtudiant(pEtudiant);
		
		model.addAttribute("attribut_listeAbsences", listeAbsencesEtu);
		
		return "etuAbsList";
	
	}//end ListByEtu
	
	
	
	// Suppression d'une absence
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value= {"/etudiantCours/delete/{idEtudiantCours}","/etudiantCours/remove/{idEtudiantCours}"}, method=RequestMethod.GET)
	public String deleteAbsence(@PathVariable("idEtudiantCours") int pIdAbsence, ModelMap model) {
		
		absDao.delete(pIdAbsence);

		List<EtudiantCours> listeAbsences = absDao.getAll();
		
		model.addAttribute("attribut_listeAbsences", listeAbsences);
		
		return "absList";
		
	}//end delete
	
	
	// Modification d'une absence (utile ?)
	// Formulaire
	
	@RequestMapping(value="/absUpdate-form", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateAbsence(@RequestParam("idEtudiantCours") int pIdAbsence) {
		
		EtudiantCours absUpdate = absDao.getById(pIdAbsence);
		
		return new ModelAndView("absUpdate", "absenceUpdateCommand", absUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/etudiantCours/update", method=RequestMethod.POST)
	public String updateAbsence(@ModelAttribute("absenceUpdateCommand") EtudiantCours pAbsence, ModelMap model) {
		
		absDao.update(pAbsence);
		
		model.addAttribute("attribut_listeAbsences", absDao.getAll());
		
		return "absList";
	
	}//end update
	
	
	// Ajout d'une absence
	// Formulaire
	
	@RequestMapping(value="/absAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddAbsence() {
		
		EtudiantCours absence = new EtudiantCours();
		
		String objetCommandeAbsence = "absenceAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandeAbsence, absence);
		
		String viewName = "absAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/absAdd-meth", method=RequestMethod.POST)
	public String addAbsence (@ModelAttribute("absenceAddCommand") @Validated EtudiantCours pAbsence, ModelMap model, BindingResult result) {
		
		absValid.validate(pAbsence, result);
		
		if (result.hasErrors()) {
			return "absdd";
			
		}else {
			absDao.add(pAbsence);

			model.addAttribute("attribut_listeAbsences", absDao.getAll());
			
			return "absList";
			
		}//end if
		
	}//end add	
	
	
}//end class
