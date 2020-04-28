package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.intiformation.gestionecole.domain.Promotion;

public class PromotionDao implements IPromotionDao {
	
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
	public List<Promotion> getAll() {
		return getSessionFactory().getCurrentSession().createQuery("FROM promotion p").list();
	}

	@Override
	public Promotion getById(int id) {
		return getSessionFactory().getCurrentSession().find(Promotion.class, id);
	}

	@Override
	public void delete(int id) {
		getSessionFactory().getCurrentSession().remove(getById(id));
		
	}

	@Override
	public void addPromotion(Promotion promotion) {
		getSessionFactory().getCurrentSession().save(promotion);
		
	}

	@Override
	public void updatePromotion(Promotion promotion) {
		getSessionFactory().getCurrentSession().update(promotion);



}
	@Override
	public Promotion getByLibelle(String libelle) {
		return getSessionFactory().getCurrentSession().find(Promotion.class, libelle);
	}
	
	@Override
	public void deletePromotion(String libelle) {
		getSessionFactory().getCurrentSession().remove(getByLibelle(libelle));
	
	}
		
	}//end class
