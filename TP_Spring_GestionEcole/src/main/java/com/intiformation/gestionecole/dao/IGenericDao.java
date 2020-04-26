package com.intiformation.gestionecole.dao;

import java.util.List;

import com.intiformation.gestionecole.domain.Etudiant;

public interface IGenericDao<T> {

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
	
	/**
	 * permet d'ajouter un objet dans la BDD
	 * @param entity
	 */
	public void add(T entity);
	
	/**
	 * permet de modifier un objet dans la BDD
	 * @param entity
	 */
	public void update(T entity);

	public void delete(String pLibelle);

	public T getByLibelle(String libelle);


}
