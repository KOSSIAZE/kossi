package model;

public class Diagnostic {
	
	private int idDiagnostic;
    private String libelleDiagnostic;
    private String typeDiagnostic;
    private int rangDiagnostic;
    private int idHospitalisation;

    public Diagnostic() {
    }

    public Diagnostic(int idDiagnostic, String libelleDiagnostic, String typeDiagnostic, int rangDiagnostic, int idHospitalisation) {
        this.idDiagnostic = idDiagnostic;
        this.libelleDiagnostic = libelleDiagnostic;
        this.typeDiagnostic = typeDiagnostic;
        this.rangDiagnostic = rangDiagnostic;
        this.idHospitalisation = idHospitalisation;
    }

    public int getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(int idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public String getLibelleDiagnostic() {
        return libelleDiagnostic;
    }

    public void setLibelleDiagnostic(String libelleDiagnostic) {
        this.libelleDiagnostic = libelleDiagnostic;
    }

    public String getTypeDiagnostic() {
        return typeDiagnostic;
    }

    public void setTypeDiagnostic(String typeDiagnostic) {
        this.typeDiagnostic = typeDiagnostic;
    }

    public int getRangDiagnostic() {
        return rangDiagnostic;
    }

    public void setRangDiagnostic(int rangDiagnostic) {
        this.rangDiagnostic = rangDiagnostic;
    }

    public int getIdHospitalisation() {
        return idHospitalisation;
    }

    public void setIdHospitalisation(int idHospitalisation) {
        this.idHospitalisation = idHospitalisation;
    }
    
    
    
    

}
