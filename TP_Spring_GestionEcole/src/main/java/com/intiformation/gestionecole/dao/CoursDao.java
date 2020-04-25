package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Cours;

@Transactional
@Repository
public class CoursDao implements ICoursDao{

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
	public List<Cours> getAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM cours c").getResultList();
	}

	// Récup un cours par son id
	@Override
	public Cours getById(int id) {
		return sessionFactory.getCurrentSession().find(Cours.class, id);
	}

	// Supprime un cours
	@Override
	public void delete(int id) {
		sessionFactory.getCurrentSession().remove(getById(id));
	}

	// Ajoute un cours
	@Override
	public void addCours(Cours cours) {
		sessionFactory.getCurrentSession().save(cours);
	}

	// Modifie un cours
	@Override
	public void updateCours(Cours cours) {
		sessionFactory.getCurrentSession().update(cours);
	}

	
}
