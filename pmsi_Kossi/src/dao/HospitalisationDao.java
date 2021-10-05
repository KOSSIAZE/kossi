package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.DbConfig;
import model.Hospitalisation;


/**
 * Cette classe permet d'effectuer les requetes SQL concernant les Hospitalisations
 * @author AZIAGBA
 * 
 */

public class HospitalisationDao {
	
	private Connection connection;
	
	public HospitalisationDao() {
	}

	public HospitalisationDao(Connection connection) {
		this.connection = connection;
	}
	
	/**
	    * Cette méthode permet d'enregistrer un nouvelle hospitalisation dans la base de données
	    * @param Hospitalisation  l'hospitalisation à ajouter
	    */
	
	public void addHospitalisation(Hospitalisation hospitalisation) {
		
		try {
			String query = "INSERT INTO tab_hospitalisation ("
					+ "ID_HOSPITALISATION,ID_PATIENT,DATE_ENTREE,DATE_SORTIE) VALUES (?, ?, ?, ? )";
		
			PreparedStatement addHospitalisationStmt = this.connection.prepareStatement(query);
			addHospitalisationStmt.setInt(1, hospitalisation.getIdHospitalisation());
			addHospitalisationStmt.setInt(2, hospitalisation.getIdPatient());
			addHospitalisationStmt.setDate(3, DbConfig.convertDate(hospitalisation.getDateEntree()));
			addHospitalisationStmt.setDate(4, DbConfig.convertDate(hospitalisation.getDateSortie()));
			addHospitalisationStmt.execute();
			addHospitalisationStmt.close();
			System.out.println("Hospitalisation crée avec succès ");

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("L'enregistrement de l'hospitalisation a échoué ");

		}
	}
	
}
