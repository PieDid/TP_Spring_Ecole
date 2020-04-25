package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Matiere;


public interface IMatiereDao extends IGeneralDao<Matiere> {

	public void addMatiere(Matiere matiere);
	
	public void updateMatiere(Matiere matiere);
	
	public Matiere getByLibelle(String libelle);
	
	public void deleteMatiere(String libelle);
	
}//end interface
