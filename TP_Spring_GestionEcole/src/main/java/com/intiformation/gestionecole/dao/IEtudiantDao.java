package com.intiformation.gestionecole.dao;

import java.util.List;

import com.intiformation.gestionecole.domain.Etudiant;

public interface IEtudiantDao extends IGeneralDao<Etudiant>{

	public void addEtudiant(Etudiant etudiant);
	
	public void updateEtudiant(Etudiant etudiant);
	
	public void deleteEtudiant (int pIdEtudiant);
	
	public Etudiant getEudiantById (int pIdEtudiant);
	
	public List<Etudiant> getAllEtudiant();

	
	
}
