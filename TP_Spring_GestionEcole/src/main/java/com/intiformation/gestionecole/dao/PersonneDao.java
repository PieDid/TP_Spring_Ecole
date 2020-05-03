package com.intiformation.gestionecole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestionecole.domain.Personne;

@Transactional
@Repository
public class PersonneDao implements IPersonneDao {

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
	public void addPerson(Personne personne) {
		
		getSf().getCurrentSession().save(personne);
		
	}//end add

	@Override
	public void updatePerson(Personne personne) {
		
		getSf().getCurrentSession().update(personne);
		
	}//end update

	
	@Override
	public void deletePerson(int pIdPersonne) {
		
		getSf().getCurrentSession().remove(getPersonById(pIdPersonne));
		
	}//end delete

	
	@Override
	public Personne getPersonById(int pIdPersonne) {

		return getSf().getCurrentSession().find(Personne.class, pIdPersonne);
		
	}//end get
	

	@Override
	public List<Personne> getAllPerson() {

		return getSf().getCurrentSession().createQuery("FROM personne p").getResultList();
		
	}//end list
	
	

}//end class
