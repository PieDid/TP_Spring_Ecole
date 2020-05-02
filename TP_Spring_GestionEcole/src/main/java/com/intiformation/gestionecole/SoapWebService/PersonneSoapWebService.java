package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.dao.IPersonneDao;
import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.domain.Personne;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/personne-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "personne-sws")
@Component
public class PersonneSoapWebService {
	
	/*_________________ props ________________*/
	
	@Autowired
	private IPersonneDao personneDao;
	
	@Autowired
	private IAdresseDao adresseDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Personne> recupererListePersonne(){
		return personneDao.getAllPerson();
	} // end recupererListePersonne()
	
	@WebMethod
	public Personne recupererPersonneParId(@WebParam(name="id") String pId) {
		return personneDao.getPersonById(Integer.parseInt(pId));
	} // end recupererPersonneParId()
	
	@WebMethod
	public void ajouterPersonne(@WebParam(name="motDePasse") String pMotDePasse,
								@WebParam(name="nom") String pNom,
								@WebParam(name="prenom") String pPrenom,
								@WebParam(name="email") String pEmail,
								@WebParam(name="role") String pRole,
								@WebParam(name="adresse_id") String pAdresse) {
		
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		Personne personne = new Personne(pMotDePasse, pNom, pPrenom, pEmail, pRole, adresse);
		
		personneDao.addPerson(personne);
		
	} // end ajouterPersonne
	
	@WebMethod
	public void modifierPersonne(@WebParam(name="id") String pId,
								 @WebParam(name="motDePasse") String pMotDePasse,
								 @WebParam(name="nom") String pNom,
								 @WebParam(name="prenom") String pPrenom,
								 @WebParam(name="email") String pEmail,
								 @WebParam(name="role") String pRole,
								 @WebParam(name="adresse_id") String pAdresse) {
		
		Personne personne = personneDao.getPersonById(Integer.parseInt(pId));
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		
		personne.setAdresse(adresse);
		personne.setEmail(pEmail);
		personne.setMotDePasse(pMotDePasse);
		personne.setPrenom(pPrenom);
		personne.setNom(pNom);
		personne.setRole(pRole);
		
		personneDao.updatePerson(personne);
		
	} // end modifierPersonne()
	
	@WebMethod
	public void supprimerPersonne(@WebParam(name="id") String pId) {
		personneDao.deletePerson(Integer.parseInt(pId));
	} // end supprimerPersonne()
	

}
