package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByLexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDao<T> implements IGenericDao<T>{

	//props
	protected Class<T> entityClass;

	
	@Autowired
	private SessionFactory sessionFactory;
	

	/*_________________ getters|setters ________________*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	/*_________________ ctor ________________*/
	
	
	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}


	/**
	 * ctor vide pour l'injection spring
	 */
	public GenericDao() {}
	/*_________________ méthodes ________________*/
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		String entityName = (entityClass).getSimpleName().substring(0).toLowerCase();
		String clauseFROM = "FROM " + entityName + " e";
		return sessionFactory.getCurrentSession().createQuery(clauseFROM).getResultList();
	}




	@Override
	public T getById(int id) {
		return sessionFactory.getCurrentSession().find(entityClass, id);
	}

	@Override
	public T getByLibelle(String libelle) {
		return sessionFactory.getCurrentSession().find(entityClass, libelle);
	}


	// Supprime un étudiant
	public void delete(int id) {
		sessionFactory.getCurrentSession().remove(getById(id));
	}
	
	@Override
	public void delete(String pLibelle) {
		sessionFactory.getCurrentSession().remove(getByLibelle(pLibelle));
		
	}
	
	// Ajoute un étudiant
	@Override
	public void add(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}
	
	// Modifie un étudiant
	@Override
	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
	
}
