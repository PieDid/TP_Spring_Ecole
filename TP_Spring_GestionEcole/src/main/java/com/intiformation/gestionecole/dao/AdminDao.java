package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Administrateur;
import com.intiformation.gestionecole.encoder.Encoder;



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
	public SessionFactory getSf() {
		return sf;
	}
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	@Override
	public void addAdmin(Administrateur admin) {
		admin.setMotDePasse(Encoder.crypt(admin.getMotDePasse()));
		getSf().getCurrentSession().save(admin);
		
	}//end add


	@Override
	public void updateAdmin(Administrateur admin) {
		admin.setMotDePasse(Encoder.crypt(admin.getMotDePasse()));
		getSf().getCurrentSession().update(admin);
		
	}//end update


	
	
	
	@Override
	public void deleteAdmin(int pIdAdmin) {
		getSf().getCurrentSession().remove(getAdminById(pIdAdmin));
		
	}


	@Override
	public Administrateur getAdminById(int pIdAdmin) {
		return getSf().getCurrentSession().find(Administrateur.class, pIdAdmin);
	}


	@Override
	public List<Administrateur> getAllAdmin() {
		return getSf().getCurrentSession().createQuery("FROM admin a").getResultList();
	}

	
	
}//end class
