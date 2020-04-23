package com.intiformation.gestionecole.dao;

import java.util.List;

import com.intiformation.gestionecole.domain.Enseignant;

public interface IEnseignantDao {
	
	public void updateEnseignant(Enseignant pEnseignant);
	
	public void deleteEnseignant(Long pIdEnseignant);
		
	public Enseignant getEnseignantById(Long pIdEnseignant);
	
	public List<Enseignant> getAllEnseignant();
	
	
}
