package com.intiformation.gestionecole.dao;

import java.util.List;

import com.intiformation.gestionecole.domain.Etudiant;
import com.intiformation.gestionecole.domain.EtudiantCours;

public interface IAbsenceDao extends IGeneralDao<EtudiantCours>  {

	public List<EtudiantCours> getByEtudiant (Etudiant etudiant);
	
}//end interface
