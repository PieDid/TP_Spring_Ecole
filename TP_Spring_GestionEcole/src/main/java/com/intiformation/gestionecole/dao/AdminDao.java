package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Administrateur;



@Transactional
@Repository
public class AdminDao implements IAdminDao {
	

/*	 Administrateur : les employés de l’administration peuvent, après authentification, gérer :
		 les personnes (administrateurs, enseignants, étudiants).
		 les matières (Informatique, Management, Mathématiques...).
		 les promotions et l’affectation des étudiants aux promotions.
		 les cours et l’affectation des cours aux matières.
		 les absences des étudiants aux cours. */
	
	@Autowired
	private SessionFactory sf;

	
	// Session Factory
	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}


	@Override
	public List<Administrateur> getAll() {
		
		return (List<Administrateur>) sf.getCurrentSession()
						.createQuery("SELECT a FROM Administrateur a")
						.getResultList();
		

	}//end getAll


	@Override
	public Administrateur getById(int pIdAdmin) {
		
		return (Administrateur) sf.getCurrentSession().createQuery("SELECT a FROM Adminitrateur a WHERE a.id_personne = :identifiant").setParameter("identifiant", pIdAdmin).getSingleResult();

	}//end getById


	@Override
	public void delete(int pIdAdmin) {
		
		sf.getCurrentSession().delete(pIdAdmin);
		
	}//end delete


	@Override
	public void addAdmin(Administrateur admin) {
		
		sf.getCurrentSession().save(admin);
		
	}//end add


	@Override
	public void updateAdmin(Administrateur admin) {
		
		sf.getCurrentSession().update(admin);
		
	}//end update

	
	
}//end class
