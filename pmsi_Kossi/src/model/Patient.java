package model;

import java.time.LocalDate;
/**
 * Cette classe représente un patient
 * @author AZIAGBA
 */
public class Patient {

	 private int idPatient;
	    private String nom;
	    private String prenom;
	    private int sexe;
	    private LocalDate dateNaissance;

	    public Patient() {
	    }
	    
	    
	    public Patient(int idPatient, String nom, String prenom, int sexe, LocalDate dateNaissance) {
	        this.idPatient = idPatient;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.sexe = sexe;
	        this.dateNaissance = dateNaissance;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public String getPrenom() {
	        return prenom;
	    }

	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }

	    public int getSexe() {
	        return sexe;
	    }

	    public void setSexe(int  sexe) {
	        this.sexe = sexe;
	    }

	    public LocalDate getDateNaissance() {
	        return dateNaissance;
	    }

	    public void setDateNaissance(LocalDate dateNaissance) {
	        this.dateNaissance = dateNaissance;
	    }

	    public int getIdPatient() {
	        return idPatient;
	    }

	    public void setIdPatient(int idPatient) {
	        this.idPatient = idPatient;
	    }
	    
}
