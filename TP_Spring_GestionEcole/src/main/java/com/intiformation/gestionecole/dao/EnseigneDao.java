package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Enseigne;

@Transactional
@Repository
public class EnseigneDao implements IEnseigneDao{

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
	// Récup tous les cours de la bdd	
	@Override
	public List<Enseigne> getAll() {
		return getSessionFactory().getCurrentSession().createQuery("FROM enseigne e").list();
	}

	// Récup un cours par son id
	@Override
	public Enseigne getById(int id) {
		return getSessionFactory().getCurrentSession().find(Enseigne.class, id);
	}

	// Supprime un cours
	@Override
	public void delete(int id) {
		getSessionFactory().getCurrentSession().remove(getById(id));
	}

	// Ajoute un cours
	@Override
	public void addEnseigne(Enseigne enseigne) {
		getSessionFactory().getCurrentSession().save(enseigne);
	}

	// Modifie un cours
	@Override
	public void updateEnseigne(Enseigne enseigne) {
		getSessionFactory().getCurrentSession().update(enseigne);
	}

}
