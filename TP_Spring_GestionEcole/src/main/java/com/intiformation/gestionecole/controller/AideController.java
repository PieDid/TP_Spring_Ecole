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

import com.intiformation.gestionecole.dao.AideDao;
import com.intiformation.gestionecole.dao.GenericDao;
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.domain.Aide;
import com.intiformation.gestionecole.validator.AideValidator;


@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
@Controller
public class AideController {

	// DAO
	@Autowired
	private IAideDao aideDao;
	
	// Validateur
	@Autowired
	private AideValidator aideValid;
	
	
	// setters pr l'injection de Spring
	
	public void setAideDao(AideDao aideDao) {
		this.aideDao = aideDao;
	}


	public void setAideValid(AideValidator aideValid) {
		this.aideValid = aideValid;
	}

	/*----------- methodes ---------------*/

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
	@RequestMapping(value="/aideList" , method = RequestMethod.GET)
	public String generateAideList(Model model) {
		
		List<Aide> listeAide  = java.util.Collections.emptyList();
		listeAide = aideDao.getAll();
		
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("aideList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAide",listeAide);
		
		return "aideList";
		
	} // end generateAideList()
	
	
	// Suppression d'une Aide
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/aideDelete/{idAide}","/aide/remove/{idAide}"}, method=RequestMethod.GET)
	public String deleteAide(@PathVariable("idAide") int pIdAide, ModelMap model) {
		
		aideDao.delete(pIdAide);
		
		List<Aide> listeAide = aideDao.getAll();
		
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("aideList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAide",listeAide);
		
		return "aideList";
		
	} // end deleteAide()
	
	
	// Modification d'une aide
	// Formulaire
	@RequestMapping(value="/aideUpdate/{idAide}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateAide(@PathVariable("idAide") int pIdAide) {
		
		Aide aideUpdate = aideDao.getById(pIdAide);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("aideUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("aideUpdate", "aideUpdateCommand",aideUpdate);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
	} // end afficherFormulaireUpdateAide()
		
	
	// Méthode Update 
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value="aideUpdate-meth", method=RequestMethod.POST)
	public String updateAide(@ModelAttribute("aideUpdateCommand") Aide pAide, ModelMap model) {
			
		aideDao.update(pAide);
		
		List<Aide> listeAide  = java.util.Collections.emptyList();
		listeAide = aideDao.getAll();
		
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("aideList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeAide",listeAide);
		
		return "aideList";
		
	} // end updateAide()
	
	
	// Ajout d'un nouveau cours
	// Formulaire
		
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value="/aideAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddAide() {
		
		Aide aide = new Aide();
		
		//String objetCommandeAide = "aideAddCommand";
		
		//Map<String, Object> data = new HashMap<> ();
		//data.put(objetCommandeAide, aide);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide a : listeAide) {
			if (a.getPage().equals("aideAdd")){
				isAide =  a.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("aideAdd", "aideAddCommand",aide);
		
		if (isAide != null) {
			mov.addObject("attribut_aide", isAide);
			System.out.println("Il y a une aide");
		} else {
			mov.addObject("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			System.out.println("Il y a pas aide");
		} // end else
		
		return mov;
		
		
		//String viewName = "aideAdd";
		
		//return new ModelAndView(viewName, data);
		
	} // aide afficherFormulaireAddAide()
	
	// Méthode Add 
	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/aideAdd-meth", method=RequestMethod.POST)
	public String addAide (@ModelAttribute("aideAddCommand") @Validated Aide pAide, ModelMap model, BindingResult result) {
		
		aideValid.validate(pAide, result);
		
		if (result.hasErrors()) {
			return "aideAdd";
			
		}else {
			aideDao.add(pAide);
			
			List<Aide> listeAide  = java.util.Collections.emptyList();
			listeAide = aideDao.getAll();
			
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("aideList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else

			model.addAttribute("attribut_listeAide", listeAide);
			
			return "aideList";
			
		}//end if
		
	}//end addAide()
	
}
