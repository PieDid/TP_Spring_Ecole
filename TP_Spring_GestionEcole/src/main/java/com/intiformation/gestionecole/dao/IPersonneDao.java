package com.intiformation.gestionecole.dao;

import java.util.List;

import com.intiformation.gestionecole.domain.Personne;

public interface IPersonneDao{

	public void addPerson (Personne personne);
	
	public void updatePerson (Personne personne);
	
	public void deletePerson (int pIdPersonne);
	
	public Personne getPersonById (int pIdPersonne);
	
	public List<Personne> getAllPerson();
	
	
} // end interface
