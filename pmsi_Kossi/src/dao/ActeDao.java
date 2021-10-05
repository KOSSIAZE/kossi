package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Acte;

/**
 * Cette classe permet d'effectuer les requetes SQL concernant les Actes
 */

public class ActeDao {

	private Connection connection;

	public ActeDao(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getLibelleActe(String idCCAM) {

		try {

			PreparedStatement recherchelibelleCCAM = this.connection
					.prepareStatement("select ID_CCAM, LIBELLE_CCAM from tab_ccam where ID_CCAM = ?");
			recherchelibelleCCAM.setString(1, idCCAM);
			ResultSet ccam = recherchelibelleCCAM.executeQuery();
			ccam.next();
			return ccam.getString(2);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	public List<Acte> getAll() {

		List<Acte> actes = new ArrayList<>();
		try {

			Statement statement = this.connection.createStatement();
			ResultSet rs_tab_acte = statement.executeQuery("select * from tab_acte");

			while (rs_tab_acte.next()) {

				int idPatient = rs_tab_acte.getInt("ID_AKT");
				String libelleActe = getLibelleActe(rs_tab_acte.getString("ID_CCAM"));
				LocalDate dateActe = rs_tab_acte.getDate("DATE_ATK").toLocalDate();
				int idHospitalisation = rs_tab_acte.getInt("ID_HOSPITALISATION");
				boolean anesth = rs_tab_acte.getBoolean("ANESTH");
				actes.add(new Acte(idPatient, libelleActe, dateActe, idHospitalisation, anesth));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return actes;

	}

}
