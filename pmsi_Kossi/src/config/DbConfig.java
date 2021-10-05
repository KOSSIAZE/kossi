package config;

import java.time.LocalDate;  
//Connexion de la jdbc à la base de données
public class DbConfig {
	
	    public static String url = "jdbc:mysql://localhost:3306/bd_projet";
	    public static String user = "kossiaze";
	    public static String password = "kossiaze2020";
	    
	    
	    public static java.sql.Date convertDate (LocalDate date_p) {
          
	        java.sql.Date datesql = java.sql.Date.valueOf(date_p);
	    	return datesql;
	    }
	    
	   
}
