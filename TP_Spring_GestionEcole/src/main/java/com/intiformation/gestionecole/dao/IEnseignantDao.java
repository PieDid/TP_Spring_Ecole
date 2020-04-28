package com.intiformation.gestionecole.dao;

import java.util.List;

import com.intiformation.gestionecole.domain.Enseignant;

public interface IEnseignantDao {
	
	public void addEnseignant(Enseignant pEnseignant);
	
	public void updateEnseignant(Enseignant pEnseignant);
	
	public void deleteEnseignant(int pIdEnseignant);
		
	public Enseignant getEnseignantById(int pIdEnseignant);
	
	public List<Enseignant> getAllEnseignant();
	
	
}
