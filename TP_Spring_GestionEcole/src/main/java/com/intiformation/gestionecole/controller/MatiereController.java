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
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.dao.MatiereDao;
import com.intiformation.gestionecole.domain.Aide;
import com.intiformation.gestionecole.domain.Cours;
import com.intiformation.gestionecole.domain.Matiere;
import com.intiformation.gestionecole.domain.Promotion;
import com.intiformation.gestionecole.validator.MatiereValidator;


@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class MatiereController {

	
	// Couche Dao
	@Autowired
	private IMatiereDao matDao;
	
	@Autowired
	private IAideDao aideDao;
	
	// Validateur
	@Autowired
	private MatiereValidator matValid;
	
	
	// Setters pour Injection Spring 

	public void setMatDao(MatiereDao matDao) {
		this.matDao = matDao;
	}

	public void setMatValid(MatiereValidator matValid) {
		this.matValid = matValid;
	}
	
	
	/* Méthodes gestionnaires du Matiere Controller */
	
	
	// Récupération de la liste des matieres et affichage 

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENS', 'ROLE_ETU')")
	@RequestMapping(value="/matiereList" , method = RequestMethod.GET)
	public String generateMatiereList(Model model) {
		
		List<Matiere> listeMatiere = java.util.Collections.emptyList();
		listeMatiere = matDao.getAllMatiere();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("matiereList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeMatiere", listeMatiere);
		
		return "matiereList";
	
	}//end ListMat
	
	
	// Suppression d'une promotion
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value= {"/matiereDelete/{libelle}"}, method=RequestMethod.GET)
	public String deleteMatiere(@PathVariable("libelle") String pLibelle, ModelMap model) {
		
		matDao.deleteMatiere(pLibelle);;

		List<Matiere> listeMatiere = matDao.getAllMatiere();
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("matiereList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeMatiere", listeMatiere);
		
		return "matiereList";
		
	}//end delete	
	
	
	// Modification d'une promotion
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="matiereUpdate/{libelle}", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateMatiere(@PathVariable("libelle") String pLibelle) {
		
		Matiere matUpdate = matDao.getByLibelle(pLibelle);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("matiereUpdate")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("matiereUpdate", "matiereUpdateCommand", matUpdate);
		
		
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
	@RequestMapping(value="/matiereUpdate-meth", method=RequestMethod.POST)
	public String updateMatiere(@ModelAttribute("matiereUpdateCommand") Matiere pMatiere, ModelMap model) {
		
		matDao.updateMatiere(pMatiere);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("matiereList")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		if (isAide != null) {
			model.addAttribute("attribut_aide", isAide);
		} else {
			model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
		} // end else
		
		model.addAttribute("attribut_listeMatiere", matDao.getAllMatiere());
		
		return "matiereList";
	
	}//end update
	
	
	// Ajout d'une nouvelle matière
	// Formulaire
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/matiereAdd", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAddMatiere() {
		
		Matiere matiere = new Matiere();
		
		Map<String, Object> data = new HashMap<> ();
		data.put("matiereAddCommand", matiere);
		
		List<Aide> listeAide = aideDao.getAll();
		String isAide = null;
		for (Aide aide : listeAide) {
			if (aide.getPage().equals("matiereAdd")){
				isAide =  aide.getContenu();
			} // end if
		} // end for
		
		ModelAndView mov = new ModelAndView("matiereAdd", data);
		
		
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
	@RequestMapping(value="/matiereAdd-meth", method=RequestMethod.POST)
	public String addMatiere (@ModelAttribute("matiereAddCommand") @Validated Matiere pMatiere, ModelMap model, BindingResult result) {
		
		matValid.validate(pMatiere, result);
		
		if (result.hasErrors()) {
			return "matiereAdd";
			
		}else {
			matDao.addMatiere(pMatiere);
			
			List<Aide> listeAide = aideDao.getAll();
			String isAide = null;
			for (Aide aide : listeAide) {
				if (aide.getPage().equals("matiereList")){
					isAide =  aide.getContenu();
				} // end if
			} // end for
			
			if (isAide != null) {
				model.addAttribute("attribut_aide", isAide);
			} else {
				model.addAttribute("attribut_aide", "Il n'y a pas d'aide existante pour cette page.");
			} // end else

			model.addAttribute("attribut_listeMatiere", matDao.getAllMatiere());
			
			return "matiereList";
			
		}//end if
		
	}//end add
	
	
}//end class
