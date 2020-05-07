package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Matiere;

@Transactional
@Repository
public class MatiereDao implements IMatiereDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Méthodes */
	

	@Override
	public void addMatiere(Matiere matiere) {
		getSessionFactory().getCurrentSession().save(matiere);
		
	}

	@Override
	public void updateMatiere(Matiere matiere) {
		getSessionFactory().getCurrentSession().update(matiere);
		
	}
	@Override
	public Matiere getByLibelle(String libelle) {
		return getSessionFactory().getCurrentSession().find(Matiere.class, libelle);
	}
	@Override
	public void deleteMatiere(String libelle) {
		getSessionFactory().getCurrentSession().remove(getByLibelle(libelle));
		
	}
	@Override
	public List<Matiere> getAllMatiere() {
		return getSessionFactory().getCurrentSession().createQuery("FROM matiere m").list();
	}

}//end class
