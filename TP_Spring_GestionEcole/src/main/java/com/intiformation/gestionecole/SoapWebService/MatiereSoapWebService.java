package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IMatiereDao;
import com.intiformation.gestionecole.domain.Matiere;

/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/matiere-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "matiere-sws")
@Component
public class MatiereSoapWebService {
	
	/* _________________ props ________________ */
	
	@Autowired
	private IMatiereDao matiereDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<Matiere> recupererListeMatiere(){
		return matiereDao.getAllMatiere();
	} // end recupererListeMatiere()
	
	@WebMethod
	public Matiere recupererMatiereParLibelle(@WebParam(name="libelle") String pLibelle) {
		return matiereDao.getByLibelle(pLibelle);
	} // end recupererMatiereParLibelle()
	
	@WebMethod
	public void ajouterMatiere(@WebParam(name="libelle") String pLibelle) {
		Matiere matiere = new Matiere(pLibelle);
	} // end ajouterMatiere()
	
//	public void modifierMatiere(@WebParam(name="libelle") String pLibelle) {
//		Matiere matiere = matiereDao.getByLibelle(pLibelle);
//		
//	}
	
	@WebMethod
	public void supprimerMatiere(@WebParam(name="libelle") String pLibelle) {
		matiereDao.deleteMatiere(pLibelle);
	} // end supprimerMatiere()
	
	

} // end class
