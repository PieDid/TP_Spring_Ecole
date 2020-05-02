package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.EtudiantCours;

@Repository
@Transactional
public class AbsenceDao implements IAbsenceDao {
	
	
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
	public List<EtudiantCours> getAll() {
		return getSessionFactory().getCurrentSession().createQuery("FROM etudiantCours a").list();
	}

	@Override
	public EtudiantCours getById(int id) {
		return getSessionFactory().getCurrentSession().find(EtudiantCours.class, id);
	}

	@Override
	public void delete(int id) {
		getSessionFactory().getCurrentSession().remove(getById(id));
		
	}

	
	
	@Override
	public List<EtudiantCours> getByEtudiant(Etudiant etudiant) {
		return getSessionFactory().getCurrentSession().createQuery("FROM etudiantCours a WHERE a.etudiant_id = :etudiant").list();
	}
	@Override
	public void add(EtudiantCours pAbsence) {
		getSessionFactory().getCurrentSession().save(pAbsence);
		
	}
	@Override
	public void update(EtudiantCours pAbsence) {
		getSessionFactory().getCurrentSession().update(pAbsence);
	}

	
	
}//end class
