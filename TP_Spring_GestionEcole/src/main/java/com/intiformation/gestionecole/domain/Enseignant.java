package com.intiformation.gestionecole.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="enseignant")
@Table(name="enseignants")
public class Enseignant extends Personne{

	/* Propriétés */
	
	@OneToMany(mappedBy="enseignant", cascade= CascadeType.ALL)
	private List<Enseigne> enseigne;
	
	/* Constructeurs */
	
	public Enseignant() {
		super();
	}

	
	public Enseignant(int identifiant, String motDePasse, String nom, String prenom, String email, Adresse adresse) {
		super(identifiant, motDePasse, nom, prenom, email, adresse);
	}


	public Enseignant(String motDePasse, String nom, String prenom, String email, Adresse adresse) {
		super(motDePasse, nom, prenom, email, adresse);
	}

	public Enseignant(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);
	}


	public Enseignant(String motDePasse, String nom, String prenom, String email) {
		super(motDePasse, nom, prenom, email);
	}

	/* Setter/Getters */
	
	public List<Enseigne> getEnseigne() {
		return enseigne;
	}


	public void setEnseigne(List<Enseigne> enseigne) {
		this.enseigne = enseigne;
	}
	

}//end class
