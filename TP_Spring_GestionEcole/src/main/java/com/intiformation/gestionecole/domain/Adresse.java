package com.intiformation.gestionecole.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="adresse")
@Table(name="adresses")
public class Adresse implements Serializable{

	/*_________________ props ________________*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto-increment
	@Column(name="id_adresse")
	private int idAdresse;
	
	@Column(name="rue")
	private String rue;
	
	@Column(name="codePostal")
	private String codePostal;
	
	@Column(name="ville")
	private String ville;
	
	@OneToOne(mappedBy = "adresse")
	private Personne personne;
	
	/*_________________ ctors ________________*/
	
	/**
	 * ctor vide
	 */
	public Adresse() {}

	public Adresse(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	

	public Adresse(int idAdresse, String rue, String codePostal, String ville, Personne personne) {
		super();
		this.idAdresse = idAdresse;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.personne = personne;
	}

	public Adresse(String rue, String codePostal, String ville, Personne personne) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.personne = personne;
	}

	/*_________________ meths ________________*/
	
	/**
	 * @return the idAdresse
	 */
	public int getIdAdresse() {
		return idAdresse;
	}

	/**
	 * @param idAdresse the idAdresse to set
	 */
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	/**
	 * @return the personne
	 */
	public Personne getPersonne() {
		return personne;
	}

	/**
	 * @param personne the personne to set
	 */
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

	/*__________________ G/S _________________*/
	
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	
} // end class
