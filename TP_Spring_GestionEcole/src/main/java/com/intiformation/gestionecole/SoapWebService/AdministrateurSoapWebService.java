package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IAdminDao;
import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.domain.Administrateur;
import com.intiformation.gestionecole.domain.Adresse;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/administrateur-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "administrateur-sws")
@Component
public class AdministrateurSoapWebService {
	
	/* _________________ props ________________ */
	
	@Autowired
	private IAdminDao adminDao;

	@Autowired
	private IAdresseDao adresseDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Administrateur> recupererListeAdministrateur(){
		return adminDao.getAllAdmin();
	} // end RecupererListeAdministrateur()
	
	@WebMethod
	public Administrateur recupererAdministrateurParId(@WebParam(name="id") String pId) {
		return adminDao.getAdminById(Integer.parseInt(pId));
	} // end RecupererAdministrateurParId()
	
	@WebMethod
	public void ajouterAdministrateur(@WebParam(name="motDePasse") String pMotDePasse,
									  @WebParam(name="nom") String pNom,
									  @WebParam(name="prenom") String pPrenom,
									  @WebParam(name="email") String pEmail,
									  @WebParam(name="adresse_id") String pAdresse) {
		
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		Administrateur administrateur = new Administrateur(pMotDePasse, pNom, pPrenom, pEmail, "ROLE_ADMIN", adresse);
		
		adminDao.addAdmin(administrateur);
	} // end AjouterAdministrateur()
	
	@WebMethod
	public void modifierAdministrateur(@WebParam(name="id") String pId,
									   @WebParam(name="motDePasse") String pMotDePasse,
									   @WebParam(name="nom") String pNom,
									   @WebParam(name="prenom") String pPrenom,
									   @WebParam(name="email") String pEmail,
									   @WebParam(name="adresse_id") String pAdresse) {
		
		Administrateur administrateur = adminDao.getAdminById(Integer.parseInt(pId));
		Adresse adresse = adresseDao.getById(Integer.parseInt(pAdresse));
		administrateur.setAdresse(adresse);
		administrateur.setEmail(pEmail);
		administrateur.setMotDePasse(pMotDePasse);
		administrateur.setPrenom(pPrenom);
		administrateur.setNom(pNom);
		administrateur.setRole("ROLE_ADMIN");
		
		adminDao.updateAdmin(administrateur);
		
	} // end ModifierAdministrateur()
	
	@WebMethod
	public void supprimerAdministrateur(@WebParam(name="id") String pId) {
		adminDao.deleteAdmin(Integer.parseInt(pId));
	} // end SupprimerAdministrateur()
	
} // end AdministrateurSoapWebService()
