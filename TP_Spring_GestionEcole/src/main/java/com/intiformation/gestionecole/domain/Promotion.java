package com.intiformation.gestionecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="promotion")
@Table(name="promotions")
public class Promotion  implements Serializable{

	/* Propriétés */
	
	@Id	
	@Column(name="libelle")
	private String libelle;

	@OneToMany(mappedBy="promotion", cascade= CascadeType.ALL)
	private List<Enseigne> enseigne;

	@OneToMany(mappedBy="promotion", cascade= CascadeType.ALL)
	private List<Etudiant> etudiant;
//	@ManyToOne
//	@JoinColumn(name="etudiant_id", referencedColumnName = "id_personne")
//	private Etudiant etudiant;

	
	/* Constructeurs */
	public Promotion(String libelle) {
		super();
		this.libelle = libelle;
	}

	
	public String getLibelle() {
		return libelle;
	}


	public Promotion(String libelle, List<Enseigne> enseigne) {
		super();
		this.libelle = libelle;
		this.enseigne = enseigne;
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


//	public Etudiant getEtudiant() {
//		return etudiant;
//	}
//
//	public void setEtudiant(Etudiant etudiant) {
//		this.etudiant = etudiant;
//	}
	
	public List<Etudiant> getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(List<Etudiant> etudiant) {
		this.etudiant = etudiant;
	}


	
	
	@Override
	public String toString() {
		return "Promotion [libelle=" + libelle + "]";
	}


	
	
	
	
	
	
	
}
