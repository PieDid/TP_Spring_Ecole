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

import com.intiformation.gestionecole.dao.GenericDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.dao.MatiereDao;
import com.intiformation.gestionecole.domain.Cours;
import com.intiformation.gestionecole.domain.Matiere;
import com.intiformation.gestionecole.domain.Promotion;
import com.intiformation.gestionecole.validator.MatiereValidator;


@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class MatiereController {

	
	// Couche Dao
	@Autowired
	private IGenericDao<Matiere> matDao = new GenericDao<Matiere>(Matiere.class);
	
	// Validateur
	@Autowired
	private MatiereValidator matValid;
	
	
	// Setters pour Injection Spring 

	public void setMatDao(IGenericDao<Matiere> matDao) {
		this.matDao = matDao;
	}

	public void setMatValid(MatiereValidator matValid) {
		this.matValid = matValid;
	}
	
	
	/* Méthodes gestionnaires du Matiere Controller */
	
	
	// Récupération de la liste des matieres et affichage 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/matList*" , method = RequestMethod.GET)
	public String generateMatiereList(Model model) {
		
		List<Matiere> listeMatiere = java.util.Collections.emptyList();
		listeMatiere = matDao.getAll();
		
		model.addAttribute("attribut_listeMatiere", listeMatiere);
		
		return "matList";
	
	}//end ListMat
	
	
	// Suppression d'une promotion
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/matiere/delete/{libelle}","/matiere/remove/{libelle}"}, method=RequestMethod.GET)
	public String deleteMatiere(@PathVariable("libelle") String pLibelle, ModelMap model) {
		
		matDao.delete(pLibelle);

		List<Matiere> listeMatiere = matDao.getAll();
		
		model.addAttribute("attribut_listeMatiere", listeMatiere);
		
		return "matList";
		
	}//end delete	
	
	
	// Modification d'une promotion
	// Formulaire
	
	@RequestMapping(value="matUpdate", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateMatiere(@RequestParam("libelle") String pLibelle) {
		
		Matiere matUpdate = matDao.getByLibelle(pLibelle);
		
		return new ModelAndView("matUpdate", "matiereUpdateCommand", matUpdate);
		
	}
	
	// Méthode Update 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/matiere/update", method=RequestMethod.POST)
	public String updatePromotion(@ModelAttribute("matiereUpdateCommand") Matiere pMatiere, ModelMap model) {
		
		((IGenericDao<Matiere>) matDao).update(pMatiere);
		
		model.addAttribute("attribut_listeMatiere", matDao.getAll());
		
		return "matList";
	
	}//end update
	
	
	// Ajout d'une nouvelle matière
	// Formulaire
	
	@RequestMapping(value="/matAdd-form", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddMatiere() {
		
		Matiere matiere = new Matiere();
		
		String objetCommandeMatiere = "matiereAddCommand";
		
		Map<String, Object> data = new HashMap<> ();
		data.put(objetCommandeMatiere, matiere);
		
		String viewName = "matAdd";
		
		return new ModelAndView(viewName, data);
		
	}
	
	// Méthode Add 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/matAdd-meth", method=RequestMethod.GET)
	public String addPromotion (@ModelAttribute("matiereAddCommand") @Validated Matiere pMatiere, ModelMap model, BindingResult result) {
		
		matValid.validate(pMatiere, result);
		
		if (result.hasErrors()) {
			return "matAdd";
			
		}else {
			((IGenericDao<Matiere>) matDao).add(pMatiere);

			model.addAttribute("attribut_listeMatiere", matDao.getAll());
			
			return "matList";
			
		}//end if
		
	}//end add
	
	
}//end class
