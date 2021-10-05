package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Diagnostic;


/**
 * Cette classe permet d'effectuer les requetes SQL concernant les Diagnostics
 * @author AZIAGBA
 * 
 */

public class DiagnosticDao {

	private Connection connection;

	public DiagnosticDao() {
	}

	public DiagnosticDao(Connection connection) {
		this.connection = connection;
	}
	
	public 	List<Diagnostic> mostWomenDiagnostic(){
		
		List<Diagnostic> diagnostics = new ArrayList<>();

		try {

			String query = "SELECT ID_DISGNOCTIC,td.ID_HOSPITALISATION, CODE_CIM10 , DGTYPE ,libelle_cim110 ,sexe,DRANG, count(CODE_CIM10) as freq"
					+ " FROM tab_diagnostic td , tab_hospitalisation th , tab_cim10 tc , tab_patient tp"
					+ " WHERE td.CODE_CIM10 = tc.ID_CIM10"
					+ " AND td.ID_HOSPITALISATION = th.ID_HOSPITALISATION"
					+ " AND th.ID_PATIENT = tp.ID_PATIENT"
					+ " and tp.SEXE = 2"
					+ " GROUP BY CODE_CIM10 "
					+ " ORDER BY freq DESC "
					+ " limit 5";
			
			
			PreparedStatement diagnosticsPsmt = this.connection.prepareStatement(query);
			ResultSet resultats = diagnosticsPsmt.executeQuery();
			
			while(resultats.next()) {
				
				int idDiagnostic = resultats.getInt(1);
				String libelleDiagnostic = resultats.getString(5);
				String typeDiagnostic = resultats.getString(4);	
				int rangDiagnostic = resultats.getInt(7);
				int idHospitalisation = resultats.getInt(2);
				diagnostics.add(new Diagnostic(idDiagnostic,libelleDiagnostic, typeDiagnostic, rangDiagnostic, idHospitalisation));
			}

		}catch (SQLException e) {

			e.printStackTrace();
		}
		return diagnostics;

	}
	
	
public 	List<Diagnostic> mostmenDiagnostic(){
		
		List<Diagnostic> diagnostics = new ArrayList<>();

		try {

			String query = "SELECT ID_DISGNOCTIC,td.ID_HOSPITALISATION, CODE_CIM10 , DGTYPE ,libelle_cim110 ,sexe,DRANG, count(CODE_CIM10) as freq"
					+ " FROM tab_diagnostic td , tab_hospitalisation th , tab_cim10 tc , tab_patient tp"
					+ " WHERE td.CODE_CIM10 = tc.ID_CIM10"
					+ " AND td.ID_HOSPITALISATION = th.ID_HOSPITALISATION"
					+ " AND th.ID_PATIENT = tp.ID_PATIENT"
					+ " and tp.SEXE = 1"
					+ " GROUP BY CODE_CIM10 "
					+ " ORDER BY freq DESC "
					+ " limit 5";
			
			
			PreparedStatement diagnosticsPsmt = this.connection.prepareStatement(query);
			ResultSet resultats = diagnosticsPsmt.executeQuery();
			
			while(resultats.next()) {
				
				int idDiagnostic = resultats.getInt(1);
				String libelleDiagnostic = resultats.getString(5);
				String typeDiagnostic = resultats.getString(4);	
				int rangDiagnostic = resultats.getInt(7);
				int idHospitalisation = resultats.getInt(2);
				diagnostics.add(new Diagnostic(idDiagnostic,libelleDiagnostic, typeDiagnostic, rangDiagnostic, idHospitalisation));
			}

		}catch (SQLException e) {

			e.printStackTrace();
		}
		return diagnostics;

	}

public 	List<Diagnostic> mostWomenDePlus60Diagnostic(){
	
	List<Diagnostic> diagnostics = new ArrayList<>();

	try {

		String query = "SELECT ID_DISGNOCTIC,td.ID_HOSPITALISATION, CODE_CIM10 , DGTYPE ,libelle_cim110 ,sexe,DRANG, count(CODE_CIM10) as freq"
				+ " FROM tab_diagnostic td , tab_hospitalisation th , tab_cim10 tc , tab_patient tp"
				+ " WHERE td.CODE_CIM10 = tc.ID_CIM10"
				+ " AND td.ID_HOSPITALISATION = th.ID_HOSPITALISATION"
				+ " AND th.ID_PATIENT = tp.ID_PATIENT"
				+ " and tp.SEXE = 2"
			    + " AND tp.DATE_NAISSANCE < '1961-12-31'"
				+ " GROUP BY CODE_CIM10 "
				+ " ORDER BY freq DESC "
				+ " limit 5";
		
		
		PreparedStatement diagnosticsPsmt = this.connection.prepareStatement(query);
		ResultSet resultats = diagnosticsPsmt.executeQuery();
		
		while(resultats.next()) {
			
			int idDiagnostic = resultats.getInt(1);
			String libelleDiagnostic = resultats.getString(5);
			String typeDiagnostic = resultats.getString(4);	
			int rangDiagnostic = resultats.getInt(7);
			int idHospitalisation = resultats.getInt(2);
			diagnostics.add(new Diagnostic(idDiagnostic,libelleDiagnostic, typeDiagnostic, rangDiagnostic, idHospitalisation));
		}

	}catch (SQLException e) {

		e.printStackTrace();
	}
	return diagnostics;

}
}


