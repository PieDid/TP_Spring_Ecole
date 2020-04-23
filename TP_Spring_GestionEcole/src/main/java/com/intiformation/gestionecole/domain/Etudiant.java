package com.intiformation.gestionecole.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	//association entre Etudiant et EtudiantCours
//	@ManyToOne
//	@JoinColumn(name="etudiantCours_id", referencedColumnName="id_etudiantCours", updatable=true)
//	private EtudiantCours etudiantCours;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "etudiantCours_id", referencedColumnName = "id_etudiantCours") 
	private EtudiantCours etudiantCours;
	


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

	public EtudiantCours getEtudiantCours() {
		return etudiantCours;
	}

	public void setEtudiantCours(EtudiantCours etudiantCours) {
		this.etudiantCours = etudiantCours;
	}
	
	
}
