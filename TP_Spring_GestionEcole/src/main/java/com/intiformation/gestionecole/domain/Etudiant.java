package com.intiformation.gestionecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="etudiant")
@Table(name="etudiants")
public class Etudiant extends Personne implements Serializable{

	/*_________________ props ________________*/
	
	@Column(name="photo")
	private int photo;
	
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

	public Etudiant(int photo, String simpleDateFormat) {
		super();
		this.photo = photo;
		this.dateDeNaissance = simpleDateFormat;
	}

	public Etudiant(int id, int photo, String dateDeNaissance) {
		super();
		setIdentifiant(id);
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
	}

	
	

	/*_________________ meths ________________*/
	
	@Override
	public String toString() {
		return "Etudiant [identifiant=" + getIdentifiant() + ", photo=" + photo + ", dateDeNaissance=" + dateDeNaissance
				+ "]";
	}

	
	
	
	/*_________________ getters|setters ________________*/

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
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
