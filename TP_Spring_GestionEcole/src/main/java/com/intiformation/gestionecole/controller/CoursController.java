package com.intiformation.gestionecole.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
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

import com.intiformation.gestionecole.dao.CoursDao;
import com.intiformation.gestionecole.dao.GenericDao;
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.dao.ICoursDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.dao.MatiereDao;
import com.intiformation.gestionecole.dao.PromotionDao;
import com.intiformation.gestionecole.domain.Aide;
import com.intiformation.gestionecole.domain.Cours;
import com.intiformation.gestionecole.domain.Matiere;
import com.intiformation.gestionecole.domain.Promotion;
import com.intiformation.gestionecole.validator.CoursValidator;

//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
@Controller
public class CoursController {

	// Couche Dao
	@Autowired
	private ICoursDao coursDao;
//	private IGenericDao<Cours> coursDao = new GenericDao<Cours>(Cours.class);
//	private GenericDao<Cours> coursDao;
	
	@Autowired
	private IPromotionDao promoDao;
	
	@Autowired
	private IMatiereDao matiereDao;
	
	@Autowired
	private IAideDao aideDao;
	
	
	// Validateur
	@Autowired
	private CoursValidator coursValid;
	
	
	// Setters pour injection Spring

//	public void setCoursDao(GenericDao<Cours> coursDao) {
	public void setCoursDao(CoursDao coursDao) {
		this.coursDao = coursDao;
	}

	public void setCoursValid(CoursValidator coursValid) {
		this.coursValid = coursValid;
	}
	
	public void setPromoDao(PromotionDao promoDao) {
		this.promoDao =  promoDao;
	}
	
	public void setMatiereDao(MatiereDao matiereDao) {
		this.matiereDao =  matiereDao;
	}

	
	
	/* Méthodes gestionnaires du Cours Controller */
	
	
	// Récupération de la liste des cours et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
	@RequestMapping(value="/coursList" , method = RequestMethod.GET)
	public String generateCoursList(Model model) {
		
		List<Cours> listeCours = java.util.Collections.emptyList();
		listeCours = coursDao.getAll();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("coursList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeCours", listeCours);
		
		return "coursList";
	
	}//end ListCours
	
	
	// Suppression d'un cours
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/coursDelete/{idCours}","/cours/remove/{idCours}"}, method=RequestMethod.GET)
	public String deleteCours(@PathVariable("idCours") int pIdCours, ModelMap model) {
		
		coursDao.delete(pIdCours);

		List<Cours> listeCours = coursDao.getAll();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("coursList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeCours", listeCours);
		
		return "coursList";
		
	}//end delete
	
	
	// Modification d'un cours
	// Formulaire
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/coursUpdate/{idCours}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateCours(@PathVariable("idCours") int pIdCours) {
		
		Cours coursUpdate = coursDao.getById(pIdCours);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("coursUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("coursUpdate", "coursUpdateCommand", coursUpdate);
		
		
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
	@RequestMapping(value="/coursUpdate-meth", method=RequestMethod.POST)
	public String updateCours(@ModelAttribute("coursUpdateCommand") Cours pCours, ModelMap model) {
		
		String promo = pCours.getPromotion().getLibelle();
		if (promoDao.getByLibelle(promo) == null)
			promoDao.addPromotion(new Promotion(promo) );
		String matiere = pCours.getMatiere().getLibelle();
		if (matiereDao.getByLibelle(matiere) == null)
			matiereDao.addMatiere(new Matiere(matiere) );
		
//		coursDao.update(pCours);
		coursDao.updateCours(pCours);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("coursList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeCours", coursDao.getAll());
		
		return "coursList";
	
	}//end update	
	
	
	// Ajout d'un nouveau cours
	// Formulaire
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS')")
	@RequestMapping(value="/coursAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddCours() {
		
		Cours cours = new Cours();
		
		Map<String, Object> data = new HashMap<> ();
		data.put("coursAddCommand", cours);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("coursAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("coursAdd", data);
		
		
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
	@RequestMapping(value="/coursAdd-meth", method=RequestMethod.POST)
	public String addCours (@ModelAttribute("coursAddCommand") @Validated Cours pCours, ModelMap model, BindingResult result) {
		
		coursValid.validate(pCours, result);
		
		if (result.hasErrors()) {
			return "coursAdd";
			
		}else {
			
			String promo = pCours.getPromotion().getLibelle();
			if (promoDao.getByLibelle(promo) == null)
				promoDao.addPromotion(new Promotion(promo) );
			String matiere = pCours.getMatiere().getLibelle();
			if (matiereDao.getByLibelle(matiere) == null)
				matiereDao.addMatiere(new Matiere(matiere) );
			
//			coursDao.add(pCours);
			coursDao.addCours(pCours);
			
			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("coursList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else

			model.addAttribute("attribut_listeCours", coursDao.getAll());
			
			return "coursList";
			
		}//end if
		
	}//end add

}//end class
