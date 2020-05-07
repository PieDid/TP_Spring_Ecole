package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.AideDao;
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.domain.Aide;


/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/aide-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName="aide-sws")
@Component
public class AideSoapWebService {

	/*_________________ props ________________*/
	
	@Autowired
	private IAideDao aideDao;
	
	
	/*_________________ meths ________________*/

	@WebMethod
	public List<Aide> recupererListeAide(){
		return aideDao.getAll();
	} // end recupererListeAide()
	
	@WebMethod
	public Aide recupererAideParId(@WebParam(name="id") String pId) {
		return aideDao.getById(Integer.parseInt(pId));
	} // end recupererAideParId()
	
	@WebMethod
	public void ajouterAide( @WebParam(name="page") String pPage, @WebParam(name="contenu") String pContenu) {
		Aide aide = new Aide(pPage, pContenu);
		aideDao.add(aide);
	} // end recupererListeAide()
	
	@WebMethod
	public void modifierAide(@WebParam(name="id") String pId, @WebParam(name="page") String pPage, @WebParam(name="contenu") String pContenu) {
		Aide aide = aideDao.getById(Integer.parseInt(pId));
		aide.setPage(pPage);
		aide.setContenu(pContenu);
		aideDao.update(aide);
	}
	
	@WebMethod
	public void supprimerAide(@WebParam(name="id") String pId) {
		aideDao.delete(Integer.parseInt(pId));
	} // supprimerAide()
	
} // end class