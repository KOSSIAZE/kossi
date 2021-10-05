package model;

import java.time.LocalDate;

public class Acte {
	
	 private int idActe;
	    private String libelleActe;
	    private LocalDate dateActe;
	    private int idHospitalisation;
	    private boolean anesth;

	    public Acte() {
	    }

	    public Acte(int idActe, String libelleActe, LocalDate dateActe, int idHospitalisation, boolean anesth) {
	        this.idActe = idActe;
	        this.libelleActe = libelleActe;
	        this.dateActe = dateActe;
	        this.idHospitalisation = idHospitalisation;
	        this.anesth = anesth;
	    }

	    public int getIdActe() {
	        return idActe;
	    }

	    public void setIdActe(int idActe) {
	        this.idActe = idActe;
	    }

	    public String getLibelleActe() {
	        return libelleActe;
	    }

	    public void setLibelleActe(String libelleActe) {
	        this.libelleActe = libelleActe;
	    }

	    public LocalDate getDateActe() {
	        return dateActe;
	    }

	    public void setDateActe(LocalDate dateActe) {
	        this.dateActe = dateActe;
	    }

	    public int getIdHospitalisation() {
	        return idHospitalisation;
	    }

	    public void setIdHospitalisation(int idHospitalisation) {
	        this.idHospitalisation = idHospitalisation;
	    }

	    public boolean isAnesth() {
	        return anesth;
	    }

	    public void setAnesth(boolean anesth) {
	        this.anesth = anesth;
	    }
	    
	    
	    
	    

}
