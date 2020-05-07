package com.intiformation.gestionecole.dao;

import java.util.List;

import com.intiformation.gestionecole.domain.Matiere;


public interface IMatiereDao {

	public void addMatiere(Matiere matiere);
	
	public void updateMatiere(Matiere matiere);
	
	public Matiere getByLibelle(String libelle);
	
	public void deleteMatiere(String libelle);
	
	public List<Matiere> getAllMatiere();
	
}//end interface
