package com.intiformation.gestionecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * classe mère de Administrateur, Eleve et Enseignant
 * @author IN-DF-028
 *
 */
@Entity(name="personne")
@Table(name="personnes")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // une seule table pour toutes les personnes
@DiscriminatorColumn(name = "type_personne", discriminatorType = DiscriminatorType.STRING) // colonne discriminante
public class Personne implements Serializable{

	/*_________________ props ________________*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto-increment
	@Column(name="id_personne")
	private int identifiant;
	
	@Column(name="mot_de_passe")
	private String motDePasse;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="email")
	private String email;
	
	@Column(name="role")
	private String role;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "adresse_id", referencedColumnName = "id_adresse") 
	private Adresse adresse;

	/*_________________ ctors ________________*/
	
	/**
	 * ctor vide
	 */
	public Personne() {}

	public Personne(String motDePasse, String nom, String prenom, String email) {
		super();
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Personne(int identifiant, String motDePasse, String nom, String prenom, String email) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Personne(int identifiant, String motDePasse, String nom, String prenom, String email, Adresse adresse) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
	}
	
	public Personne(String motDePasse, String nom, String prenom, String email, Adresse adresse) {
		super();
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
	}

	public Personne(String motDePasse, String nom, String prenom, String email, String role, Adresse adresse) {
		super();
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
		this.adresse = adresse;
	}

	public Personne(int identifiant, String motDePasse, String nom, String prenom, String email, String role,
			Adresse adresse) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
		this.adresse = adresse;
	}

	/*_________________ meths ________________*/


	
	@Override
	public String toString() {
		return "Personne [identifiant=" + identifiant + ", motDePasse=" + motDePasse + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", role=" + role + ", adresse=" + adresse + "]";
	}
	

	/*__________________ G/S _________________*/
	
	
	/**
	 * @return the identifiant
	 */
	public int getIdentifiant() {
		return identifiant;
	}	

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
} // end class
