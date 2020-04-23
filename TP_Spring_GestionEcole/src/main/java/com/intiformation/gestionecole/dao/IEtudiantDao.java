package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Etudiant;

public interface IEtudiantDao extends IGeneralDao<Etudiant>{

	public void addEtudiant(Etudiant etudiant);
	
	public void updateEtudiant(Etudiant etudiant);
}
