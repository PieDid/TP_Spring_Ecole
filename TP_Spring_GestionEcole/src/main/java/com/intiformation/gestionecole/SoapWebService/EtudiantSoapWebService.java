package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.dao.IEtudiantDao;
import com.intiformation.gestionecole.dao.IPromotionDao;
import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.Promotion;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/etudiant-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "etudiant-sws")
@Component
public class EtudiantSoapWebService {

	//public Etudiant(String motDePasse, String nom, String prenom, String email, String role, Adresse adresse, String photo,
	//String dateDeNaissance, Promotion promotion, List<EtudiantCours> etudiantCours)
	
	/* _________________ props ________________ */
	
	@Autowired
	private IEtudiantDao etudiantDao;
	
	@Autowired
	private IPromotionDao promotionDao;
	
	@Autowired
	private IAdresseDao adresseDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Etudiant> recupererListeEtudiant(){
		return etudiantDao.getAllEtudiant();
	} // end recupererListeEtudiant()
	
	@WebMethod
	public Etudiant recupererEtudiantParId(@WebParam(name="id") String pId) {
		return etudiantDao.getEudiantById(Integer.parseInt(pId));
	} // end recupererEtudiantParId()
	
	@WebMethod
	public void ajouterEtudiant(@WebParam(name="motDePasse") String pMotDePasse,
								@WebParam(name="nom") String pNom,
								@WebParam(name="prenom") String pPrenom,
								@WebParam(name="email") String pEmail,
								@WebParam(name="adresse_id") String pAdresse,
								@WebParam(name="photo") String pPhoto,
								@WebParam(name="dateDeNaissance") String pDateDeNaissance,
								@WebParam(name="promotion_id") String pPromotion) {
		Promotion promotion = promotionDao.getById(Integer.parseInt(pPromotion));
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		Etudiant etudiant = new Etudiant(pMotDePasse, pNom, pPrenom, pEmail, "ROLE_ETU", adresse, pPhoto, pDateDeNaissance, promotion);

		etudiantDao.addEtudiant(etudiant);
	} // end ajouterEtudiant()
	
	@WebMethod
	public void modifierEtudiant(@WebParam(name="id") String pId,
								 @WebParam(name="motDePasse") String pMotDePasse,
								 @WebParam(name="nom") String pNom,
								 @WebParam(name="prenom") String pPrenom,
								 @WebParam(name="email") String pEmail,
								 @WebParam(name="adresse_id") String pAdresse,
								 @WebParam(name="photo") String pPhoto,
								 @WebParam(name="dateDeNaissance") String pDateDeNaissance,
								 @WebParam(name="promotion_id") String pPromotion) {
		
		Etudiant etudiant = etudiantDao.getEudiantById(Integer.parseInt(pId));
		Promotion promotion = promotionDao.getById(Integer.parseInt(pPromotion));
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		etudiant.setAdresse(adresse);
		etudiant.setDateDeNaissance(pDateDeNaissance);
		etudiant.setEmail(pEmail);
		etudiant.setMotDePasse(pMotDePasse);
		etudiant.setNom(pNom);
		etudiant.setPrenom(pPrenom);
		etudiant.setPhoto(pPhoto);
		etudiant.setPromotion(promotion);
		etudiant.setRole("ROLE_ETU");
		
		etudiantDao.updateEtudiant(etudiant); 
		
	} // end modifierEtudiant()
	
	
	@WebMethod
	public void supprimerEtudiant(@WebParam(name="id") String pId) {
		etudiantDao.deleteEtudiant(Integer.parseInt(pId));
	} // end supprimerEtudiant()
	
	
	
}
