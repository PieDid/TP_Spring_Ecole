package com.intiformation.gestionecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="etudiant")
@DiscriminatorValue("Etudiant")
public class Etudiant extends Personne implements Serializable{

	/*_________________ props ________________*/
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="date_de_naissance")
	private String dateDeNaissance;
	
	
	
	//association entre Etudiant et Promotion
	@ManyToOne
	@JoinColumn(name="promotion_id", referencedColumnName="libelle")
	private Promotion promotion;	
	
	
	//association entre Etudiant et EtudiantCours(absences)
	@OneToMany(mappedBy = "etudiant", cascade= CascadeType.ALL)
	private List<EtudiantCours> etudiantCours;

	


	/*_________________ ctors ________________*/
	
	/**
	 * ctor vide
	 */
	public Etudiant() {}
	
	public Etudiant(int identifiant, String motDePasse, String nom, String prenom, String email, String role,
			Adresse adresse, String photo, String dateDeNaissance, Promotion promotion,
			List<EtudiantCours> etudiantCours) {
		super(identifiant, motDePasse, nom, prenom, email, role, adresse);
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
		this.promotion = promotion;
		this.etudiantCours = etudiantCours;
	}

	public Etudiant(String motDePasse, String nom, String prenom, String email, String role, Adresse adresse, String photo,
			String dateDeNaissance, Promotion promotion, List<EtudiantCours> etudiantCours) {
		super(motDePasse, nom, prenom, email, role, adresse);
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
		this.promotion = promotion;
		this.etudiantCours = etudiantCours;
	}

	/*_________________ meths ________________*/

	
	@Override
	public String toString() {
		return "Etudiant [" + super.toString() + ", photo=" + photo + ", dateDeNaissance=" + dateDeNaissance + ", promotion=" + promotion
				+ ", etudiantCours=" + etudiantCours + "]";
	}
	
	
	
	/*_________________ getters|setters ________________*/

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<EtudiantCours> getEtudiantCours() {
		return etudiantCours;
	}

	public void setEtudiantCours(List<EtudiantCours> etudiantCours) {
		this.etudiantCours = etudiantCours;
	}


	
	
}
