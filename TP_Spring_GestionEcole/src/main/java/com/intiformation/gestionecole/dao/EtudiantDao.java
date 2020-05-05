package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.encoder.Encoder;

@Repository
@Transactional
public class EtudiantDao implements IEtudiantDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	

	/*_________________ getters|setters ________________*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	/*_________________ methodes ________________*/


	
	// Ajoute un étudiant
	@Override
	public void addEtudiant(Etudiant etudiant) {
		etudiant.setMotDePasse(Encoder.crypt(etudiant.getMotDePasse()));
		getSessionFactory().getCurrentSession().save(etudiant);
	}
	
	// Modifie un étudiant
	@Override
	public void updateEtudiant(Etudiant etudiant) {
		etudiant.setMotDePasse(Encoder.crypt(etudiant.getMotDePasse()));
		getSessionFactory().getCurrentSession().update(etudiant);
	}
	
	
	
	@Override
	public Etudiant getEudiantById(int pIdEtudiant) {
		return getSessionFactory().getCurrentSession().find(Etudiant.class, pIdEtudiant);
	}
	
	@Override
	public void deleteEtudiant(int pIdEtudiant) {
		getSessionFactory().getCurrentSession().remove(getEudiantById(pIdEtudiant));
		
	}

	@Override
	public List<Etudiant> getAllEtudiant() {
		return getSessionFactory().getCurrentSession().createQuery("FROM etudiant e").getResultList();
	}
	
	


}
