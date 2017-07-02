package it.polito.tdp.libretto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.libretto.model.Esame;

public class EsameDAO {
	//Metodo che ricerca un esame
	public Esame find(String codice) {
		String sql =
				"SELECT codice, titolo, docente, superato, voto, data_superamento "+
				"FROM esame "+
				"WHERE codice = ?" ;
		
		String jdbcURL = "jdbc:mysql://localhost/libretto?user=root&password=root";
		Esame result=null;
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
		
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			
			ResultSet res = st.executeQuery();
			
			if(res.next()){
				
				Esame ex = new Esame(
						res.getString("codice"),
						res.getString("titolo"),
						res.getString("docente")
						); 
				
					result = ex;
				} else {
					result = null;
						}
			
			conn.close();
			return result;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	//Metodo che inserisce un esame
	public boolean create(Esame e) {
		String sql = "INSERT INTO `libretto`.`esame` (`codice`, `titolo`, `docente`) VALUES (?, ?, ?);";
		String jdbcURL = "jdbc:mysql://localhost/libretto?user=root&password=root";

		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
		
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, e.getCodice());
			st.setString(2, e.getTitolo());
			st.setString(3, e.getDocente());
		
			/*
			 * executeUpdate restituisce il valore intero delle
			 * righe che son state modificate
			 */
			int result = st.executeUpdate();
			
			conn.close();
			
			//se il numero di righe modificate è 1 va bene
			if (result==1) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		return false;

		
	}
	
}
