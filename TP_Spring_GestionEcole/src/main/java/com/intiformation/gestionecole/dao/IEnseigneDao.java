package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Enseigne;

public interface IEnseigneDao extends IGeneralDao<Enseigne> {

	public void addEnseigne(Enseigne enseigne);
	
	public void updateEnseigne(Enseigne enseigne);
			
}
