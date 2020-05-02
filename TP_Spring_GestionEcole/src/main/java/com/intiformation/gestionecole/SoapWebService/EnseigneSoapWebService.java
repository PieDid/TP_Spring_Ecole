package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IEnseignantDao;
import com.intiformation.gestionecole.dao.IEnseigneDao;
import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.domain.Enseignant;
import com.intiformation.gestionecole.domain.Enseigne;
import com.intiformation.gestionecole.domain.Matiere;
import com.intiformation.gestionecole.domain.Promotion;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/enseigne-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "enseigne-sws")
@Component
public class EnseigneSoapWebService {

	/*_________________ props ________________*/
	
	@Autowired
	private IEnseigneDao enseigneDao;
	
	@Autowired
	private IEnseignantDao enseignantDao;
	
	@Autowired
	private IMatiereDao matiereDao;
	
	@Autowired
	private IPromotionDao promotionDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Enseigne> recupererListeEnseigne() {
		return enseigneDao.getAll();
	} // end RecupererListeEnseigne()
	
	@WebMethod
	public Enseigne recupererEnseigneParId(@WebParam(name="id") String pId) {
		return enseigneDao.getById(Integer.parseInt(pId));
	} // end RecupererEnseigneParId()
	
	@WebMethod
	public void ajouterEnseigne(@WebParam(name="enseignant_id") String pEnseignant,
								@WebParam(name="matiere_id") String pMatiere,
								@WebParam(name="promotion_id") String pPromotion) {
		
		Enseignant enseignant = enseignantDao.getEnseignantById(Integer.parseInt(pEnseignant));
		Matiere matiere = matiereDao.getByLibelle(pMatiere);
		Promotion promotion = promotionDao.getByLibelle(pPromotion);
		
		Enseigne enseigne = new Enseigne(enseignant, promotion, matiere);
		enseigneDao.addEnseigne(enseigne);
		
	} // end ajouterEnseigne()
	
	@WebMethod
	public void modifierEnseigne(@WebParam(name="id") String pId,
								 @WebParam(name="enseignant_id") String pEnseignant,
								 @WebParam(name="matiere_id") String pMatiere,
								 @WebParam(name="promotion_id") String pPromotion) {
		
		Enseigne enseigne = enseigneDao.getById(Integer.parseInt(pId));
		Enseignant enseignant = enseignantDao.getEnseignantById(Integer.parseInt(pEnseignant));
		Matiere matiere = matiereDao.getByLibelle(pMatiere);
		Promotion promotion = promotionDao.getByLibelle(pPromotion);
		
		enseigne.setEnseignant(enseignant);
		enseigne.setMatiere(matiere);
		enseigne.setPromotion(promotion);
		
		enseigneDao.updateEnseigne(enseigne);
	} // end modifierEnseigne()
	
	@WebMethod
	public void supprimerEnseigne(@WebParam(name="id") String pId) {
		enseigneDao.delete(Integer.parseInt(pId));
	} // end supprimerEnseigne()
	
} // end class
