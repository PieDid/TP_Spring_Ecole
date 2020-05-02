package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.dao.IEnseignantDao;
import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.domain.Enseignant;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/enseignant-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "enseignant-sws")
@Component
public class EnseignantSoapWebService {

	/* _________________ props ________________ */
	
	@Autowired
	private IEnseignantDao enseignantDao;
	
	@Autowired
	private IAdresseDao adresseDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Enseignant> recupererListeEnseignant(){
		return enseignantDao.getAllEnseignant();
	} // end recupererListeEnseignant()
	
	@WebMethod
	public Enseignant recupererEnseignantParId(@WebParam(name="id") String pId) {
		return enseignantDao.getEnseignantById(Integer.parseInt(pId));
	} // end recupererEnseignantParId()
	
	@WebMethod
	public void ajouterEnseignant(@WebParam(name="motDePasse") String pMotDePasse,
								  @WebParam(name="nom") String pNom,
								  @WebParam(name="prenom") String pPrenom,
								  @WebParam(name="email") String pEmail,
								  @WebParam(name="adresse_id") String pAdresse) {
		
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		Enseignant enseignant = new Enseignant(pMotDePasse, pNom, pPrenom, pEmail, "ROLE_ENS", adresse);
		
		enseignantDao.addEnseignant(enseignant);
	} // end AjouterEnseignant()
	
	@WebMethod
	public void modifierEnseignant(@WebParam(name="id") String pId,
								   @WebParam(name="motDePasse") String pMotDePasse,
								   @WebParam(name="nom") String pNom,
								   @WebParam(name="prenom") String pPrenom,
								   @WebParam(name="email") String pEmail,
								   @WebParam(name="adresse_id") String pAdresse) {
		
		Enseignant enseignant = enseignantDao.getEnseignantById(Integer.parseInt(pId));
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		
		enseignant.setAdresse(adresse);
		enseignant.setEmail(pEmail);
		enseignant.setMotDePasse(pMotDePasse);
		enseignant.setPrenom(pPrenom);
		enseignant.setNom(pNom);
		enseignant.setRole("ROLE_ENS");
		
		enseignantDao.updateEnseignant(enseignant);
		
	} // end ModifierEnseignant()
	
	@WebMethod
	public void supprimerEnseignant(@WebParam(name="id") String pId) {
		enseignantDao.deleteEnseignant(Integer.parseInt(pId));
	} // end SupprimerEnseignant()
	
} // end class
