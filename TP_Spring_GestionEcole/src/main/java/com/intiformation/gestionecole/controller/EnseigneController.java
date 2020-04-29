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

import com.intiformation.gestionecole.dao.GenericDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.domain.Enseigne;
import com.intiformation.gestionecole.validator.EnseigneValidator;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class EnseigneController {

	// Couche Dao
	@Autowired
	private IGenericDao<Enseigne> enseigneDao = new GenericDao<Enseigne>(Enseigne.class);

	// Validateur
	@Autowired
	private EnseigneValidator enseigneValid;

	/**
	 * setter pour injection spring
	 */
	public void setEnseigneDao(GenericDao<Enseigne> enseigneDao) {
		this.enseigneDao = enseigneDao;
	}

	public void setEnseigneValid(EnseigneValidator enseigneValid) {
		this.enseigneValid = enseigneValid;
	}

	/* Méthodes gestionnaires du Enseigne Controller */

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/enseigneList", method = RequestMethod.GET)
	public String generateEnseigneList(Model model) {

		List<Enseigne> listeEnseigne = java.util.Collections.emptyList();
		listeEnseigne = enseigneDao.getAll();

		model.addAttribute("attribut_listeEnseigne", listeEnseigne);

		return "enseigneList";

	}// end generateEnseigneList()

	// Suppression d'une promotion

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = { "/enseigneDelete/{id}", "/enseigne/remove/{id}" }, method = RequestMethod.GET)
	public String deleteEnseigne(@PathVariable("id") int pIdEnseigne, ModelMap model) {

		enseigneDao.delete(pIdEnseigne);

		List<Enseigne> listeEnseigne = ((IGenericDao<Enseigne>) enseigneDao).getAll();

		model.addAttribute("attribut_listeEnseigne", listeEnseigne);

		return "enseigneList";

	}// end deleteEnseigne()

	// Modification d'une promotion
	// Formulaire

	@RequestMapping(value = "enseigneUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateEnseigne(@PathVariable("id") int pIdEnseigne) {

		Enseigne enseigneUpdate = ((IGenericDao<Enseigne>) enseigneDao).getById(pIdEnseigne);

		return new ModelAndView("enseigneUpdate", "enseigneUpdateCommand", enseigneUpdate);

	} // end afficherFormulaireUpdateEnseigne()

	// Méthode Update

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/enseigneUpdate-meth", method = RequestMethod.POST)
	public String updateEnseigne(@ModelAttribute("enseigneUpdateCommand") Enseigne pEnseigne, ModelMap model) {

		((IGenericDao<Enseigne>) enseigneDao).update(pEnseigne);

		model.addAttribute("attribut_listeEnseigne", enseigneDao.getAll());

		return "enseigneList";

	}// end updateEnseigne()

	// Ajout d'une nouvelle matière
	// Formulaire

	@RequestMapping(value = "/enseigneAdd", method = RequestMethod.GET)
	public ModelAndView afficherFormulaireAddEnseigne() {

		Enseigne enseigne = new Enseigne();

		String objetCommandeEnseigne = "enseigneAddCommand";

		Map<String, Object> data = new HashMap<>();
		data.put(objetCommandeEnseigne, enseigne);

		String viewName = "enseigneAdd";

		return new ModelAndView(viewName, data);

	} // end afficherFormulaireAddEnseigne()
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/enseigneAdd-meth", method=RequestMethod.POST)
	public String addEnseigne (@ModelAttribute("enseigneAddCommand") @Validated Enseigne pEnseigne, ModelMap model, BindingResult result) {
		
		enseigneValid.validate(pEnseigne, result);
		
		if (result.hasErrors()) {
			return "enseigneAdd";
			
		}else {
			((IGenericDao<Enseigne>) enseigneDao).add(pEnseigne);

			model.addAttribute("attribut_listeEnseigne", enseigneDao.getAll());
			
			return "enseigneList";
			
		}//end if
		
	}//end addEnseigne()

} // end class
