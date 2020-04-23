package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Administrateur;

public interface IAdminDao extends IGeneralDao<Administrateur> {
	
	/*	 Administrateur : les employés de l’administration peuvent, après authentification, gérer :
	 les personnes (administrateurs, enseignants, étudiants).
	 les matières (Informatique, Management, Mathématiques...).
	 les promotions et l’affectation des étudiants aux promotions.
	 les cours et l’affectation des cours aux matières.
	 les absences des étudiants aux cours. */
	
	public void addAdmin (Administrateur admin);
	
	public void updateAdmin (Administrateur admin);
	
	
	

}//end interface
