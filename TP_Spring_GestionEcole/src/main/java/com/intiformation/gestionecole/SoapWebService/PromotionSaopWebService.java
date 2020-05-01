package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.domain.Promotion;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/promotion-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "promotion-sws")
@Component
public class PromotionSaopWebService {

	/* _________________ props ________________ */

	@Autowired
	private IPromotionDao promotionDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Promotion> recupererListePromotion(){
		return promotionDao.getAll();
	} // end recupererListeMatiere()
	
	@WebMethod
	public Promotion recupererPromotionParLibelle(@WebParam(name="libelle") String pLibelle) {
		return promotionDao.getByLibelle(pLibelle);
	} // end recupererMatiereParLibelle()
	
	@WebMethod
	public void ajouterPromotion(@WebParam(name="libelle") String pLibelle) {
		Promotion promotion = new Promotion(pLibelle);
	} // ajouterPromotion()
	
//	public void modifierMatiere(@WebParam(name="libelle") String pLibelle) {
//		Matiere matiere = matiereDao.getByLibelle(pLibelle);
//		
//	}
	
	@WebMethod
	public void supprimerPromotion(@WebParam(name="libelle") String pLibelle) {
		promotionDao.deletePromotion(pLibelle);
	} // end supprimerPromotion()
	
}


