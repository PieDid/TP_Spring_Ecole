package com.intiformation.gestionecole.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="enseignant")
@DiscriminatorValue("Enseignant")
public class Enseignant extends Personne{

	/* Propriétés */
	
	@OneToMany(mappedBy="enseignant", cascade= CascadeType.ALL, fetch = FetchType.EAGER)
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
	
	

	

	public Enseignant(String motDePasse, String nom, String prenom, String email, String role, Adresse adresse,
			List<Enseigne> enseigne) {
		super(motDePasse, nom, prenom, email, role, adresse);
		this.enseigne = enseigne;
	}


	public Enseignant(int identifiant, String motDePasse, String nom, String prenom, String email, String role,
			Adresse adresse, List<Enseigne> enseigne) {
		super(identifiant, motDePasse, nom, prenom, email, role, adresse);
		this.enseigne = enseigne;
	}


	public Enseignant(String pMotDePasse, String pNom, String pPrenom, String pEmail, String pRole, Adresse adresse) {
		super(pMotDePasse, pNom, pPrenom, pEmail, pRole, adresse);
	}
	
	/* Setter/Getters */
	
	


	@Override
	public String toString() {
		return "Enseignant [" + super.toString() + ",enseigne=" + enseigne + "]";
	}


	public List<Enseigne> getEnseigne() {
		return enseigne;
	}


	public void setEnseigne(List<Enseigne> enseigne) {
		this.enseigne = enseigne;
	}
	

}//end class
