package com.intiformation.gestionecole.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

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

import com.intiformation.gestionecole.dao.EnseignantDao;
import com.intiformation.gestionecole.dao.GenericDao;
import com.intiformation.gestionecole.dao.IEnseignantDao;
import com.intiformation.gestionecole.dao.IEnseigneDao;
import com.intiformation.gestionecole.dao.IGenericDao;
import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.dao.MatiereDao;
import com.intiformation.gestionecole.dao.PromotionDao;
import com.intiformation.gestionecole.domain.Enseignant;
import com.intiformation.gestionecole.domain.Enseigne;
import com.intiformation.gestionecole.domain.Matiere;
import com.intiformation.gestionecole.domain.Personne;
import com.intiformation.gestionecole.domain.Promotion;
import com.intiformation.gestionecole.validator.EnseigneValidator;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class EnseigneController {

	// Couche Dao
	@Autowired
	private IEnseigneDao enseigneDao;
//	private IGenericDao<Enseigne> enseigneDao = new GenericDao<Enseigne>(Enseigne.class);

	@Autowired
	private IPromotionDao promoDao;
	
	@Autowired
	private IMatiereDao matiereDao;
	
	@Autowired
	private IEnseignantDao enseignantDao;
	
	@Autowired
	PersonneController personneController;
	
	
	// Validateur
	@Autowired
	private EnseigneValidator enseigneValid;

	/**
	 * setter pour injection spring
	 */
	public void setEnseigneDao(IEnseigneDao enseigneDao) {
		this.enseigneDao = enseigneDao;
	}

	public void setEnseigneValid(EnseigneValidator enseigneValid) {
		this.enseigneValid = enseigneValid;
	}
	
	public void setPromoDao(PromotionDao promoDao) {
		this.promoDao =  promoDao;
	}
	
	public void setMatiereDao(MatiereDao matiereDao) {
		this.matiereDao =  matiereDao;
	}

	public void setEnseignantDao(EnseignantDao enseignantDao) {
		this.enseignantDao =  enseignantDao;
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

		//pour corriger l'erreur : (remove deleted object from associations)
//		Enseigne enseigneToDelete = enseigneDao.getById(pIdEnseigne);
//		enseigneToDelete.getParent().getChildren().remove(enseigneToDelete);
//		Enseigne enseigneToDelete = enseigneDao.getById(pIdEnseigne);
//		enseigneToDelete.setEnseignant(null);
		
		enseigneDao.delete(pIdEnseigne);

//		List<Enseigne> listeEnseigne = ((IGenericDao<Enseigne>) enseigneDao).getAll();
		List<Enseigne> listeEnseigne = enseigneDao.getAll();

		model.addAttribute("attribut_listeEnseigne", listeEnseigne);

		return "enseigneList";

	}// end deleteEnseigne()

	// Modification d'une promotion
	// Formulaire

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/enseigneUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdateEnseigne(@PathVariable("id") int pIdEnseigne) {

//		Enseigne enseigneUpdate = ((IGenericDao<Enseigne>) enseigneDao).getById(pIdEnseigne);
		Enseigne enseigneUpdate = enseigneDao.getById(pIdEnseigne);
		
//------pour la liste déroulante du formulaire--------------------------------------
		List<Enseignant> liste_enseignants = enseignantDao.getAllEnseignant();
//		List<Integer> liste_enseignantsID = enseignantDao.getAllPk();
		
		Map<String, Object> data = new HashMap<>();
		data.put("enseigneUpdateCommand", enseigneUpdate);
		data.put("listeEnseignants", liste_enseignants);
//		data.put("liste_enseignantsID", liste_enseignants);
		data.put("ens", new Enseignant());

		return new ModelAndView("enseigneUpdate", data);
//----------------------------------------------------------------------------------
		
//		return new ModelAndView("enseigneUpdate", "enseigneUpdateCommand", enseigneUpdate);

	} // end afficherFormulaireUpdateEnseigne()

	// Méthode Update

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/enseigneUpdate-meth", method = RequestMethod.POST)
	public String updateEnseigne(@ModelAttribute("enseigneUpdateCommand") Enseigne pEnseigne, ModelMap model) {

		String promo = pEnseigne.getPromotion().getLibelle();
		if (promoDao.getByLibelle(promo) == null)
			promoDao.addPromotion(new Promotion(promo) );
		String matiere = pEnseigne.getMatiere().getLibelle();
		if (matiereDao.getByLibelle(matiere) == null)
			matiereDao.addMatiere(new Matiere(matiere) );
//		pEnseigne.setEnseignant(pEnseigne.getEns());
		int enseignantID = pEnseigne.getEnseignant().getIdentifiant();
		if (enseignantDao.getEnseignantById(enseignantID) == null) {
//			JOptionPane.showMessageDialog(null, "Veuillez d'abord créer un enseignant");
//			return "enseigneList";
//			enseignantDao.addEnseignant(new Enseignant);
			System.out.println("Dans le if enseignant equals null");
//			personneController.afficherFormulaireAddPersonne();
//			afficherFormulaireAddPersonne();
			return "enseigneUpdate";
		}
		
//		((IGenericDao<Enseigne>) enseigneDao).update(pEnseigne);
		enseigneDao.updateEnseigne(pEnseigne);

		model.addAttribute("attribut_listeEnseigne", enseigneDao.getAll());

		return "enseigneList";

	}// end updateEnseigne()

	// Ajout d'un enseigne
	// Formulaire
	
	//méthode d'André : 
//	@RequestMapping(value = "/enseigneUpdate-meth", method = RequestMethod.GET)
//	public ModelAndView afficherFormulaireAddPersonne() {
//		
//		Personne personne = new Personne();
//		
//		String objetCommandePersonne = "personAddCommand";
//		
//		Map<String, Object> data = new HashMap<> ();
//		data.put(objetCommandePersonne, personne);
//		
//		String viewName = "personAdd";
//		
//		return new ModelAndView(viewName, data);
//		
//	}

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
			
			String promo = pEnseigne.getPromotion().getLibelle();
			if (promoDao.getByLibelle(promo) == null)
				promoDao.addPromotion(new Promotion(promo) );
			String matiere = pEnseigne.getMatiere().getLibelle();
			if (matiereDao.getByLibelle(matiere) == null)
				matiereDao.addMatiere(new Matiere(matiere) );
			int enseignant = pEnseigne.getEnseignant().getIdentifiant();
			if (enseignantDao.getEnseignantById(enseignant) == null) {
				JOptionPane.showMessageDialog(null, "Veuillez d'abord créer un enseignant");
//				model.put("errorMessage", "Veuillez d'abord créer un enseignant");
				/**
				 * return "personAdd";
				 * bon ici j'ai besoin du PersonneController fonctionnel
				 */
//				enseignantDao.addEnseignant(new Enseignant);
			}
			
//			((IGenericDao<Enseigne>) enseigneDao).add(pEnseigne);
			enseigneDao.addEnseigne(pEnseigne);

			model.addAttribute("attribut_listeEnseigne", enseigneDao.getAll());
			
			return "enseigneList";
			
		}//end if
		
	}//end addEnseigne()

} // end class
