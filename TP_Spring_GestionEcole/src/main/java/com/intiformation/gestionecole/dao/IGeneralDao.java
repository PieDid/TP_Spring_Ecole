package com.intiformation.gestionecole.dao;

import java.util.List;

public interface IGeneralDao<T> {
	
	/**
	 * permet de récuperer la liste des objets  de la BDD
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * permet de récuperer un objet via son id de la BDD
	 * @param id
	 * @return
	 */
	public T getById(int id);
	
	/**
	 * permet de supprimer un objet via son id (PK) dans la BDD
	 * @param id
	 * @return
	 */
	public void delete(int id);

} // end interface
