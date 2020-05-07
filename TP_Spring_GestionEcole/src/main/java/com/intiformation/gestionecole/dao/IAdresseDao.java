package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Adresse;
import com.intiformation.gestionecole.domain.Personne;

public interface IAdresseDao extends IGeneralDao<Adresse> {

	public void addAdresse(Adresse adresse);
	
	public void updateAdresse (Adresse adresse);
	
	public Adresse getByPersonne (Personne personne);
	
	
	
}//end interface
