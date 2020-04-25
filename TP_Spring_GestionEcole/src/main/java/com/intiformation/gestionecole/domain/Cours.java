package com.intiformation.gestionecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="cours")
@Table(name="cours")
public class Cours implements Serializable{

	/*_________________ props ________________*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cours")
	private int idCours;
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="date")
	private String date;
	
	@Column(name="duree")
	private String duree;
	
	@Column(name="description")
	private String description;
	
	
	//association entre Cours et Promotion
	@ManyToOne
	@JoinColumn(name="promotion_id", referencedColumnName="libelle")
	private Promotion promotion;
	
	//association entre Cours et Matiere
	@ManyToOne
	@JoinColumn(name="matiere_id", referencedColumnName="libelle")
	private Matiere matiere;
	
	//association entre Cours et EtudiantCours	
	@OneToMany(mappedBy="cours", cascade= CascadeType.ALL)
	private List<EtudiantCours> etudiantCours;

	

	
	/*_________________ méthodes ________________*/
	@Override
	public String toString() {
		return "Cours [libelle=" + libelle + ", date=" + date + ", duree=" + duree + ", description=" + description
				+ "]";
	}


	/*_________________ ctors ________________*/
	public Cours() {
		super();
	}
	public Cours(String libelle, String date, String duree, String description) {
		super();
		this.libelle = libelle;
		this.date = date;
		this.duree = duree;
		this.description = description;
	}


	
	/*_________________ getters|setters ________________*/
	
	public int getIdCours() {
		return idCours;
	}
	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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


	public List<EtudiantCours> getEtudiantCours() {
		return etudiantCours;
	}


	public void setEtudiantCours(List<EtudiantCours> etudiantCours) {
		this.etudiantCours = etudiantCours;
	}




	
}
