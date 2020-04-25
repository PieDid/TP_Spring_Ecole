package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.intiformation.gestionecole.domain.Matiere;

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
	public List<Matiere> getAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM matiere m").getResultList();
	}

	@Override
	public Matiere getById(int id) {
		return sessionFactory.getCurrentSession().find(Matiere.class, id);
	}

	@Override
	public void delete(int id) {
		sessionFactory.getCurrentSession().remove(getById(id));
		
	}

	@Override
	public void addMatiere(Matiere matiere) {
		sessionFactory.getCurrentSession().save(matiere);
		
	}

	@Override
	public void updateMatiere(Matiere matiere) {
		sessionFactory.getCurrentSession().update(matiere);
		
	}
	@Override
	public Matiere getByLibelle(String libelle) {
		return sessionFactory.getCurrentSession().find(Matiere.class, libelle);
	}
	@Override
	public void deleteMatiere(String libelle) {
		sessionFactory.getCurrentSession().remove(getByLibelle(libelle));
		
	}

}//end class
