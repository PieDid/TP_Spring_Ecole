package com.intiformation.gestionecole.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "enseigne")
@Table(name = "enseignes")
public class Enseigne implements Serializable{

	/* Propriétés */
	
	@Id
	@Column(name = "id_enseigne")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id_enseigne;
	
	@ManyToOne
	@JoinColumn(name="enseignant_id", referencedColumnName="id_personne")
	private Enseignant enseignant;
	
	@ManyToOne
	@JoinColumn(name="promotion_lib", referencedColumnName = "libelle")
	private Promotion promotion;
	
	@ManyToOne
	@JoinColumn(name="matiere_lib", referencedColumnName = "libelle")
	private Matiere matiere;

	/* Constructeur */
	
	public Enseigne() {
		super();
	}
	
	public Enseigne(Enseignant enseignant) {
		super();
		this.enseignant = enseignant;
	}

	public Enseigne(Matiere matiere) {
		super();
		this.matiere = matiere;
	}

	public Enseigne(Promotion promotion) {
		super();
		this.promotion = promotion;
	}

	public Enseigne(Enseignant enseignant, Promotion promotion, Matiere matiere) {
		super();
		this.enseignant = enseignant;
		this.promotion = promotion;
		this.matiere = matiere;
	}

	/* Getters/Setters */
	
	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public int getId_enseigne() {
		return id_enseigne;
	}

	public void setId_enseigne(int id_enseigne) {
		this.id_enseigne = id_enseigne;
	}
	
	
	
	
}//end class
