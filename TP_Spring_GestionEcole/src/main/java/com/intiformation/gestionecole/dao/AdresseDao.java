package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.domain.Personne;

public class AdresseDao implements IAdresseDao {
	
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
	public List<Adresse> getAll() {
		return getSessionFactory().getCurrentSession().createQuery("FROM adresse a").list();
	}

	@Override
	public Adresse getById(int id) {
		return getSessionFactory().getCurrentSession().find(Adresse.class, id);
	}

	@Override
	public void delete(int id) {
		getSessionFactory().getCurrentSession().remove(getById(id));
	}

	
	
	
	@Override
	public void addAdresse(Adresse adresse) {
		getSessionFactory().getCurrentSession().save(adresse);
	}

	@Override
	public void updateAdresse(Adresse adresse) {
		getSessionFactory().getCurrentSession().update(adresse);
	}

	@Override
	public Adresse getByPersonne(Personne personne) {
		return getSessionFactory().getCurrentSession().find(Adresse.class, personne);
	}



}//end class
