package model;

import java.time.LocalDate;

public class Hospitalisation {

	private int idHospitalisation;
    private int idPatient;
    private LocalDate dateEntree;
    private LocalDate dateSortie;

    public Hospitalisation() {
    }

    public Hospitalisation(int idHospitalisation, int idPatient, LocalDate dateEntree, LocalDate dateSortie) {
        this.idHospitalisation = idHospitalisation;
        this.idPatient = idPatient;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
    }

    public int getIdHospitalisation() {
        return idHospitalisation;
    }

    public void setIdHospitalisation(int idHospitalisation) {
        this.idHospitalisation = idHospitalisation;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }
    
    
}
