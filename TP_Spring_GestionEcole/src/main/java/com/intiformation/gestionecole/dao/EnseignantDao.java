package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.HibernateException;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import com.intiformation.gestionecole.domain.Cours;
import com.intiformation.gestionecole.domain.Enseignant;


@Repository
public class EnseignantDao implements IEnseignantDao{

	@Autowired // injection par type du bean de la session factory
	private SessionFactory SessionFactory;
	
	/**
	 * ajout d'un enseignant dans la bdd
	 */
	@Transactional
	public void addEnseignant(Enseignant pEnseignant) {
	
	Session session = SessionFactory.getCurrentSession();
		
		try {
			session.save(pEnseignant);
			
		} catch (HibernateException ex) {
			System.out.println(" ... Erreur lors de l'ajout d'un enseignant... ");

		}// end catch
		
	}// end addEnseignant
	
	
	/**
	 * modification d'un enseignant dans la bdd
	 */
	@Transactional
	public void updateEnseignant(Enseignant pEnseignant) {
		Session session = SessionFactory.getCurrentSession();
		
		try {
			session.update(pEnseignant);

			
		} catch (HibernateException ex) {
			System.out.println(" ... Erreur lors de la modification de l'enseignant... ");
		}// end catch
	}// end updateEmploye

	/**
	 * suppression d'un enseignant dans la bdd
	 */
	@Transactional
	public void deleteEnseignant(Long pIdEnseignant) {
		Session session = SessionFactory.getCurrentSession();
		
		try {
			Enseignant enseignant = session.find(Enseignant.class, pIdEnseignant);
			session.delete(enseignant);
			
		} catch (HibernateException ex) {
			System.out.println(" ... Erreur lors de la modification de l'enseignant... ");
			throw ex;
		}// end catch
	}// end deleteEnseignant()

	/**
	 * récupération d'un enseignant via id dans la bdd
	 */
	@Transactional(readOnly=true) // readOnly = l'optimisation de la transaction
	public Enseignant getEnseignantById(Long pIdEnseignant) {
		Session session = SessionFactory.getCurrentSession();
		Enseignant Enseignant = session.find(Enseignant.class, pIdEnseignant);
		return Enseignant;
	}// end getEmployById

	/**
	 * récupération de la liste des employés dans al bdd
	 */
	@Transactional(readOnly=true) // readOnly = l'optimisation de la transaction
	public List<Enseignant> getAllEnseignant() {
		Session session = SessionFactory.getCurrentSession();
		
		// Query = org.hibernate.query.Query
		
		List<Enseignant> listeEnseignantsBdd = session.createQuery("FROM enseignant en").getResultList();
		
		return listeEnseignantsBdd;
	}// end getAllEnseignants
	
}
