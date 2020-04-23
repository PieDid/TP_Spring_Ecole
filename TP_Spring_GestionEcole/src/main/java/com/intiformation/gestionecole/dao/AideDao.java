package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Aide;

/**
 * Implementation conrete de la DAO de l'aide
 * @author IN-DF-028
 *
 */
@Transactional
@Repository
public class AideDao implements IAideDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*_________________ meths ________________*/

	/**
	 * Récupère la liste de l'aide
	 */
	@Override
	public List<Aide> getAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM aide a").list();
	}

	/**
	 * Récupère une aide via son id
	 */
	@Override
	public Aide getById(int id) {
		return sessionFactory.getCurrentSession().find(Aide.class, id);
	}

	/**
	 * Supprime une aide via son id
	 */
	@Override
	public void delete(int id) {
		sessionFactory.getCurrentSession().delete(getById(id));
	}

	/** 
	 * Ajoute une aide 
	 */
	@Override
	public void add(Aide aide) {
		sessionFactory.getCurrentSession().save(aide);
	}

	/**
	 * Modifie une aide
	 */
	@Override
	public void update(Aide aide) {
		sessionFactory.getCurrentSession().update(aide);
		
	}

} // end class
