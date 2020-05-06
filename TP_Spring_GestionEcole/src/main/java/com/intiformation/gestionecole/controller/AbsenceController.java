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
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.gestionecole.dao.AbsenceDao;
import com.intiformation.gestionecole.dao.IAbsenceDao;
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.domain.Aide;
import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.EtudiantCours;
import com.intiformation.gestionecole.validator.AbsenceValidator;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
public class AbsenceController {

	// Couche Dao
	@Autowired
	private IAbsenceDao absDao;
	
	@Autowired
	private IAideDao aideDao;
	
	// Validateur
	@Autowired
	private AbsenceValidator absValid;

	
	// Setters pour Injection Spring
	
	public void setAbsDao(AbsenceDao absDao) {
		this.absDao = absDao;
	}

	public void setAbsValid(AbsenceValidator absValid) {
		this.absValid = absValid;
	}
	
	
	/* Méthodes gestionnaires du Absence Controller */
	
	
	// Récupération de la liste des absences et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/etudiantCoursList" , method = RequestMethod.GET)
	public String generateAbsenceList(Model model) {
		
		List<EtudiantCours> listeAbsences = java.util.Collections.emptyList();
		listeAbsences = absDao.getAll();
		
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
		
		model.addAttribute("attribut_listeEtudiantCours", listeAbsences);
		
		return "etudiantCoursList";
	
	}//end List
	
	
	// Récupération de la liste des absences d'un étudiant 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
	@RequestMapping(value="/etuAbsList*" , method = RequestMethod.GET)
	public String generateAbsenceListEtu(Model model) {
		
		Etudiant pEtudiant = new Etudiant();
		
		List<EtudiantCours> listeAbsencesEtu = java.util.Collections.emptyList();
		listeAbsencesEtu = absDao.getByEtudiant(pEtudiant);
		
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
		
		model.addAttribute("attribut_listeAbsences", listeAbsencesEtu);
		
		return "etuAbsList";
	
	}//end ListByEtu
	
	
	
	// Suppression d'une absence
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value= {"/etudiantCoursDelete/{idEtudiantCours}","/etudiantCours/remove/{idEtudiantCours}"}, method=RequestMethod.GET)
	public String deleteAbsence(@PathVariable("idEtudiantCours") int pIdAbsence, ModelMap model) {
		
		absDao.delete(pIdAbsence);

		List<EtudiantCours> listeAbsences = absDao.getAll();
		
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
		
		model.addAttribute("attribut_listeEtudiantCours", listeAbsences);
		
		return "etudiantCoursList";
		
	}//end delete
	
	
	// Modification d'une absence (utile ?)
	// Formulaire
	
	@RequestMapping(value="/etudiantCoursUpdate/{idEtudiantCours}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateAbsence(@PathVariable("idEtudiantCours") int pIdAbsence) {
		
		EtudiantCours absUpdate = absDao.getById(pIdAbsence);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantCoursUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView ("etudiantCoursUpdate", "etudiantCoursUpdateCommand", absUpdate);
		
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
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/etudiantCoursUpdate-meth", method=RequestMethod.POST)
	public String updateAbsence(@ModelAttribute("etudiantCoursUpdateCommand") EtudiantCours pAbsence, ModelMap model) {
		System.out.println("Il entre dans updateAbsence");
		absDao.update(pAbsence);
		System.out.println("Il entre a passé update");
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantCoursList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeEtudiantCours", absDao.getAll());
		
		return "etudiantCoursList";
	
	}//end update
	
	
	// Ajout d'une absence
	// Formulaire
	
	@RequestMapping(value="/etudiantCoursAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddAbsence() {
		
		EtudiantCours absence = new EtudiantCours();
		
		String objetCommandeAbsence = "etudiantCoursAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandeAbsence, absence);
		
		String viewName = "etudiantCoursAdd";
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("etudiantCoursList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView(viewName, data);
		
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
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/etudiantCoursAdd-meth", method=RequestMethod.POST)
	public String addAbsence (@ModelAttribute("etudiantCoursAddCommand") @Validated EtudiantCours pAbsence, ModelMap model, BindingResult result) {
		
		absValid.validate(pAbsence, result);
		
		if (result.hasErrors()) {
			return "etudiantCoursAdd";
			
		}else {
			absDao.add(pAbsence);
			
			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("etudiantCoursList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else

			model.addAttribute("attribut_listeEtudiantCours", absDao.getAll());
			
			return "etudiantCoursList";
			
		}//end if
		
	}//end add	
	
	
}//end class
