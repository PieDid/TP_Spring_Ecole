package com.intiformation.gestionecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="matiere")
@Table(name="matieres")
public class Matiere  implements Serializable{

	/* Propriété */
	
	@Id
	@Column(name="libelle")
	private String libelle;
	
	@OneToMany(mappedBy="matiere", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Enseigne> enseigne;
	
	@OneToMany(mappedBy="matiere", cascade= CascadeType.ALL)
	private List<Cours> cours;

	/* Constructeur */
	
	public Matiere(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Matiere(String libelle, List<Enseigne> enseigne) {
	super();
	this.libelle = libelle;
	this.enseigne = enseigne;
}

	public Matiere() {
		super();
	}

	
	/* Setters/Getters */
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Enseigne> getEnseigne() {
		return enseigne;
	}

	public void setEnseigne(List<Enseigne> enseigne) {
		this.enseigne = enseigne;
	}

	
	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	@Override
	public String toString() {
		return "Matiere [libelle=" + libelle + ", enseigne=" + enseigne + "]";
	}


	
}
