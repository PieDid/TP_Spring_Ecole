package com.intiformation.gestionecole.SoapWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestionecole.dao.IAbsenceDao;
import com.intiformation.gestionecole.dao.ICoursDao;
import com.intiformation.gestionecole.dao.IEtudiantDao;
import com.intiformation.gestionecole.domain.Cours;
import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.EtudiantCours;


/**
 * Disponible à l'adresse		http://localhost:8081/tp-gestionecole-soap-webservices/absence-sws?wsdl
 * @author IN-DF-028
 *
 */
@WebService(serviceName = "absence-sws")
@Component
public class AbsenceSoapWebService {
	
	/*_________________ props ________________*/

	@Autowired
	private IAbsenceDao absenceDao;
	
	@Autowired
	private IEtudiantDao etudiantDao;
	
	@Autowired
	private ICoursDao coursDao;
	
	/* _________________ meths ________________ */
	
	@WebMethod
	public List<EtudiantCours> recupererListeAbsence(){
		return absenceDao.getAll();
	} // end RecupererListeAbsence()
	
	@WebMethod
	public EtudiantCours recupererAbsenceParId(@WebParam(name="id") String pId) {
		return absenceDao.getById(Integer.parseInt(pId));
	} // end RecupererAbsenceParId()
	
	@WebMethod
	public void ajouterAbsence(@WebParam(name="absence") String pAbscence,
							   @WebParam(name="motif") String pMotif,
							   @WebParam(name="etudiant_id") String pEtudiant,
							   @WebParam(name="cours_id") String pCours) {
		
		Etudiant etudiant = etudiantDao.getEudiantById(Integer.parseInt(pEtudiant));
		Cours cours = coursDao.getById(Integer.parseInt(pCours));
		
		EtudiantCours etudiantCours = new EtudiantCours(Boolean.parseBoolean(pAbscence), pMotif, etudiant, cours);
		
		absenceDao.add(etudiantCours);
		
	} // end AjouterAbsence
	
	@WebMethod
	public void modifierAbsence (@WebParam(name="id") String pId,
								 @WebParam(name="absence") String pAbscence,
								 @WebParam(name="motif") String pMotif,
								 @WebParam(name="etudiant_id") String pEtudiant,
								 @WebParam(name="cours_id") String pCours) {
		
		Etudiant etudiant = etudiantDao.getEudiantById(Integer.parseInt(pEtudiant));
		Cours cours = coursDao.getById(Integer.parseInt(pCours));
		EtudiantCours etudiantCours = absenceDao.getById(Integer.parseInt(pId));
		
		etudiantCours.setAbsence(Boolean.parseBoolean(pAbscence));
		etudiantCours.setMotif(pMotif);
		etudiantCours.setEtudiant(etudiant);
		etudiantCours.setCours(cours);
		
		absenceDao.update(etudiantCours);
	} // end ModifierAbsence()
	
	@WebMethod
	public void supprimerAbsence(@WebParam(name="id") String pId) {
		absenceDao.delete(Integer.parseInt(pId));
	} // end SupprimerAbsence()
	
} // end class
