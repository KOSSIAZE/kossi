package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import config.DbConfig;
import model.Acte;
import model.Hospitalisation;
import model.Patient;

/**
 * Cette classe permet d'effectuer les requetes SQL concernant le Patient
 * @author AZIAGBA
 * 
 */
public class PatientDao {

	private Connection connection;

	public PatientDao() {
	}

	public PatientDao(Connection connection) {
		this.connection = connection;
	}

	  /**
	    * Cette méthode permet d'ajouter un nouveau patient dans la base de données
	    * @param patient  le patient à ajouter
	    */
	public void addPatient(Patient patient) {

		try {

			String query = "INSERT INTO tab_patient ("

					+ "SEXE," + "DATE_NAISSANCE," + "PRENOM," + "NOM," + "ID_PATIENT) VALUES (" + "?, ?, ?, ?, ?)";

			PreparedStatement addPatientStmt = this.connection.prepareStatement(query);
			addPatientStmt.setInt(1, patient.getSexe());
			addPatientStmt.setDate(2, DbConfig.convertDate(patient.getDateNaissance()));
			addPatientStmt.setString(3, patient.getPrenom());
			addPatientStmt.setString(4, patient.getNom());
			addPatientStmt.setInt(5, patient.getIdPatient());
			addPatientStmt.execute();
			addPatientStmt.close();
			System.out.println("Patient crée avec succès");
		} catch (SQLException e) {

			System.out.println("L'enregistrement du patient a échoué ");
		}

	}

	public Patient getPatient(String nom, String prenom, LocalDate dateNaissance) {

		Patient un_patient = new Patient();
		try {
			String query = "SELECT * FROM tab_patient " + "WHERE tab_patient.NOM = ?" + "AND tab_patient.PRENOM = ?"
					+ "AND tab_patient.DATE_NAISSANCE = ?";

			PreparedStatement recherchePatientPstmt = this.connection.prepareStatement(query);
			recherchePatientPstmt.setString(1, nom);
			recherchePatientPstmt.setString(2, prenom);
			recherchePatientPstmt.setDate(3, DbConfig.convertDate(dateNaissance));
			ResultSet patient = recherchePatientPstmt.executeQuery();
			patient.next();
			un_patient.setIdPatient(patient.getInt(1));
			un_patient.setSexe(patient.getInt(2));
			un_patient.setDateNaissance((patient.getDate(3).toLocalDate()));
			un_patient.setPrenom(patient.getString(4));
			un_patient.setNom(patient.getString(5));

		} catch (SQLException e) {

			e.printStackTrace();

			System.out.println("Aucun patient avec ses informations n'a été trouvé dans la base de données");
			System.out.println();

		}

		return un_patient;

	}

	public List<Hospitalisation> getPatientHospitalisations(Patient patient) {

		List<Hospitalisation> hospitalisations = new ArrayList<>();

		try {

			String query = "SELECT * FROM tab_hospitalisation " + "WHERE tab_hospitalisation.ID_PATIENT = ?";

			PreparedStatement hospitalisationsPsmt = this.connection.prepareStatement(query);
			hospitalisationsPsmt.setInt(1, patient.getIdPatient());
			ResultSet resultats = hospitalisationsPsmt.executeQuery();
			while (resultats.next()) {

				int idHospitalisation = resultats.getInt(1);
				LocalDate dateentree = resultats.getDate(3).toLocalDate();
				LocalDate datesortie = resultats.getDate(4).toLocalDate();
				hospitalisations
						.add(new Hospitalisation(idHospitalisation, patient.getIdPatient(), dateentree, datesortie));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return hospitalisations;

	}

	public List<Acte> getActesBetweenTwoDate(Patient patient, LocalDate dateDebut, LocalDate dateFin) {

		List<Acte> actes = new ArrayList<>();

		try {
			String query = "SELECT tab_acte.ID_AKT, tab_acte.ID_HOSPITALISATION, tab_acte.DATE_ATK ,tab_ccam.LIBELLE_CCAM, tab_acte.ANESTH"
					+ " FROM tab_acte, tab_ccam,tab_patient,tab_hospitalisation"
					+ " WHERE tab_acte.ID_CCAM = tab_ccam.ID_CCAM"
					+ " AND tab_acte.ID_HOSPITALISATION = tab_hospitalisation.ID_HOSPITALISATION"
					+ " AND tab_patient.ID_PATIENT  = tab_hospitalisation.ID_PATIENT"
					+ " AND tab_patient.ID_PATIENT = ?" + " AND tab_acte.DATE_ATK between ? and ?" + " group BY ID_AKT "
					+ " ORDER BY tab_acte.DATE_ATK";
			PreparedStatement actesPsmt = this.connection.prepareStatement(query);
			actesPsmt.setInt(1, patient.getIdPatient());
			actesPsmt.setDate(2, DbConfig.convertDate(dateDebut));
			actesPsmt.setDate(3, DbConfig.convertDate(dateFin));
			ResultSet resultats = actesPsmt.executeQuery();
			while (resultats.next()) {

				int idActe = resultats.getInt(1);
				int idHospitalisation = resultats.getInt(2);
				LocalDate dateActe = resultats.getDate(3).toLocalDate();
				String libelleActe = resultats.getString(4);
				boolean anesth = resultats.getBoolean(5);
				actes.add(new Acte(idActe, libelleActe, dateActe, idHospitalisation, anesth));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actes;

	}
	
	public List<Patient> getNotDesiredPregnantPatient() {
		
		List<Patient> patients = new ArrayList<>();
		try {
			String query = "SELECT tp.ID_PATIENT,NOM, PRENOM,sexe,DATE_NAISSANCE, libelle_cim110"
					+ "    FROM tab_diagnostic td , tab_hospitalisation th , tab_cim10 tc , tab_patient tp"
					+ "    WHERE td.CODE_CIM10 = tc.ID_CIM10"
					+ "    AND td.ID_HOSPITALISATION = th.ID_HOSPITALISATION"
					+ "    AND th.ID_PATIENT = tp.ID_PATIENT "
					+ "    and tp.SEXE = 2"
					+ "    and tc.libelle_cim110 = 'grossesse non desiree' ";

			PreparedStatement patientsPstmt = this.connection.prepareStatement(query);
			ResultSet resultats = patientsPstmt.executeQuery();
			while (resultats.next()) {
				int idPatient = resultats.getInt(1);
				String nom = resultats.getString(2);
				String prenom = resultats.getString(3);
				int sexe = resultats.getInt(4);
				LocalDate dateNaissance = resultats.getDate(5).toLocalDate();
				patients.add(new Patient(idPatient, nom, prenom, sexe, dateNaissance));
				
			}

	      }catch(SQLException e) {
	    	  
				e.printStackTrace();

	      }
		
		    return patients;
	      }
	


}
