package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IAdresseDao;
import com.intiformation.gestionecole.domain.Adresse;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/adresse-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "adresse-sws")
@Component
public class AdresseSoapWebService {

	/*_________________ props ________________*/
	
	@Autowired
	private IAdresseDao adresseDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Adresse> recupererListeAdresse(){
		return adresseDao.getAll();
	} // end RecupererListeAdresse()
	
	@WebMethod
	public Adresse recupererAdresseParId(@WebParam(name="id") String pId) {
		return adresseDao.getById(Integer.parseInt(pId));
	} // end RecupererAdresseParId()
	
	@WebMethod
	public void ajouterAdresse(@WebParam(name="rue") String pRue,
			   				   @WebParam(name="codePostal") String pCodePostal,
			   				   @WebParam(name="ville") String pVille) {
		
		Adresse adresse = new Adresse(pRue, pCodePostal, pVille);
		adresseDao.addAdresse(adresse);
	} // end AjouterAdresse()
	
	@WebMethod
	public void modifierAdresse(@WebParam(name="id") String pId,
								@WebParam(name="rue") String pRue,
								@WebParam(name="codePostal") String pCodePostal,
								@WebParam(name="ville") String pVille) {
		
		Adresse adresse = adresseDao.getById(Integer.parseInt(pId));
		adresse.setRue(pRue);
		adresse.setCodePostal(pCodePostal);
		adresse.setVille(pVille);
		
		adresseDao.updateAdresse(adresse);
	} // end ModifierAdresse()
	
	@WebMethod
	public void supprimerAdresse(@WebParam(name="id") String pId) {
		adresseDao.delete(Integer.parseInt(pId));
	} // end SupprimerAdresse()
	
} // end class
