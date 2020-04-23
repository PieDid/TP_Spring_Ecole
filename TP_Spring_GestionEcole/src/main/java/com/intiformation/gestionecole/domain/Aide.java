package com.intiformation.gestionecole.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * classe d'aide
 * @author IN-DF-028
 *
 */
@Entity(name="aide")
@Table(name="aides")
public class Aide {

	/*_________________ props ________________*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_aide")
	private int idAide;
	
	@Column(name="page")
	private String page;

	@Column(name="contenu")
	private String contenu;
	
	/*_________________ ctors ________________*/
	
	public Aide() {}

	public Aide(String page, String contenu) {
		super();
		this.page = page;
		this.contenu = contenu;
	}

	public Aide(int idAide, String page, String contenu) {
		super();
		this.idAide = idAide;
		this.page = page;
		this.contenu = contenu;
	}

	/*_________________ meths ________________*/
	
	@Override
	public String toString() {
		return "Aide [idAide=" + idAide + ", page=" + page + ", contenu=" + contenu + "]";
	}

	
	/*__________________ G/S _________________*/
	
	/**
	 * @return the idAide
	 */
	public int getIdAide() {
		return idAide;
	}

	/**
	 * @param idAide the idAide to set
	 */
	public void setIdAide(int idAide) {
		this.idAide = idAide;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

} // end class
