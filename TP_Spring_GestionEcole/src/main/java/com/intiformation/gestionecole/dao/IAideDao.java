package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Aide;

public interface IAideDao extends IGeneralDao<Aide>{
	
	public void add(Aide aide);
	
	public void update(Aide aide);
	
} // end interface
