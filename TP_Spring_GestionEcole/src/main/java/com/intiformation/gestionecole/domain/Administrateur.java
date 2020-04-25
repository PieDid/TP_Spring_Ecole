package com.intiformation.gestionecole.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="admin")
@DiscriminatorValue("Administrateur")
public class Administrateur extends Personne {

	/* Constructeurs  */
	
	public Administrateur() {
		super();

	}

	public Administrateur(int identifiant, String motDePasse, String nom, String prenom, String email, String role, Adresse adresse) {
		super(identifiant, motDePasse, nom, prenom, email, role, adresse);
	}

	public Administrateur(String motDePasse, String nom, String prenom, String email, String role, Adresse adresse) {
		super(motDePasse, nom, prenom, email, role, adresse);
	}

	@Override
	public String toString() {
		return "Administrateur [" + super.toString() + "]";
	}

	
	
	
	
}//end class
