package com.intiformation.gestionecole.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="admin")
@Table(name="administrateurs")
public class Administrateur extends Personne {

	/* Constructeurs  */
	
	public Administrateur() {
		super();

	}

	public Administrateur(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);

	}
	
	
	
}//end class
