package com.intiformation.gestionecole.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	//association entre EtudiantCours(absences) et Etudiant
	@ManyToOne
	@JoinColumn(name="Etudiant_id", referencedColumnName="id_personne", updatable=true)
	private Etudiant etudiant;

	
	//association entre EtudiantCours(absences) et Cours
	@ManyToOne
	@JoinColumn(name="Cours_id", referencedColumnName="id_cours", updatable=true)
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
