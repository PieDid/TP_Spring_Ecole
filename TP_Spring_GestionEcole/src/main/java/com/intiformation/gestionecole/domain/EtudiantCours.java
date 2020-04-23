package com.intiformation.gestionecole.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity(name="etudiantCours")
@Table(name="etudiantCours")
public class EtudiantCours implements Serializable {
	
	/*_________________ props ________________*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_etudiantCours")
	private int idEtudiantCours;
	
	@Column(name="absence")
	private boolean absence;
	
	@Column(name="motif")
	private String motif;
	
	//association entre EtudiantCours et Etudiant
//	@OneToMany(cascade=CascadeType.ALL, mappedBy = "etudiantC", fetch = FetchType.EAGER)
//	List<Etudiant> etudiant = new ArrayList<Etudiant>();
	
	@OneToOne(mappedBy = "etudiantCours")
	private Etudiant etudiant;
	
	
	//association entre EtudiantCours et Cours
//	@OneToMany(cascade=CascadeType.ALL, mappedBy = "eCours", fetch = FetchType.EAGER)
//	List<Cours> cours = new ArrayList<Cours>();
	
	@OneToOne(mappedBy = "etudiantCours")
	private Cours cours;

	
	
//	Cours cours;
//	Etudiant etudiant;

	
	
	
	/*_________________ ctors ________________*/
	public EtudiantCours() {
		super();
	}
	public EtudiantCours(boolean absence, String motif) {
		super();
		this.absence = absence;
		this.motif = motif;
	}
	
	
	
	/*_________________ méthodes ________________*/
	@Override
	public String toString() {
		return "EtudiantCours [absence=" + absence + ", motif=" + motif + "]";
	}	
	
	
	
	/*_________________ getters|setters ________________*/
	
	public boolean isAbsence() {
		return absence;
	}
	public void setAbsence(boolean absence) {
		this.absence = absence;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public int getIdEtudiantCours() {
		return idEtudiantCours;
	}
	public void setIdEtudiantCours(int idEtudiantCours) {
		this.idEtudiantCours = idEtudiantCours;
	}
	
	
	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
//	public void setEtudiant(List<Etudiant> etudiant) {
//		this.etudiant = etudiant;
//	}
//	public void setCours(List<Cours> cours) {
//		this.cours = cours;
//	}
	
	
	
}
