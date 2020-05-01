package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.CoursDao;
import com.intiformation.gestionecole.dao.ICoursDao;
import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.dao.MatiereDao;
import com.intiformation.gestionecole.dao.PromotionDao;
import com.intiformation.gestionecole.domain.Cours;
import com.intiformation.gestionecole.domain.Matiere;
import com.intiformation.gestionecole.domain.Promotion;

/**
 * Disponible à l'adresse 		http://localhost:8081/tp-gestionecole-soap-webservices/cours-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "cours-sws")
@Component
public class CoursSoapWebService {

	/* _________________ props ________________ */

	@Autowired
	private ICoursDao coursDao;

	@Autowired
	private IPromotionDao promotionDao;

	@Autowired
	private IMatiereDao matiereDao;

//	public void setCoursDao(CoursDao coursDao) {
//		this.coursDao = coursDao;
//	}
//
//	public void setPromotionDao(PromotionDao promotionDao) {
//		this.promotionDao = promotionDao;
//	}
//
//	public void setMatiereDao(MatiereDao matiereDao) {
//		this.matiereDao = matiereDao;
//	}
	
	/* _________________ meths ________________ */

	

	@WebMethod
	public List<Cours> recupererListeCours() {
		return coursDao.getAll();
	} // end recupererListeCours()

	@WebMethod
	public Cours recupererCoursParId(@WebParam(name = "id") String pId) {
		return coursDao.getById(Integer.parseInt(pId));
	} // end recupererCoursParId()

	@WebMethod
	public void ajouterCours(@WebParam(name="date") String pDate,
							 @WebParam(name="description") String pDescription,
							 @WebParam(name="duree") String pDuree,
							 @WebParam(name="libelle") String pLibelle,
							 @WebParam(name="matiere") String pMatiere,
							 @WebParam(name="promotion") String pPromotion) {
	
		Promotion promotion = promotionDao.getByLibelle(pPromotion);
		Matiere matiere = matiereDao.getByLibelle(pMatiere);
		
		Cours cours = new Cours(pLibelle, pDate, pDuree, pDescription, promotion, matiere);
		
		coursDao.addCours(cours);
	} // end CoursSoapWebService()

} // end class
