package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Cours;

public interface ICoursDao extends IGeneralDao<Cours>{
	
	public void addCours(Cours cours);
	
	public void updateCours(Cours cours);

}
