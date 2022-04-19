package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	//ottengo lo studente associato alla matricola inserita
	
	public Studente getStudenteByMatricola(int matricola) {
		final String sql = "select * from studente where matricola = ?";
		Studente studente;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			rs.next();
			studente = new Studente(rs.getInt("matricola"), 
					rs.getString("nome"), 
					rs.getString("cognome"), 
					rs.getString("CDS"));
			
			rs.close();
			st.close();
			conn.close();
			
			return studente;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore con il DB!");
			return null;
		}
		
	}
	
	public List<Corso> getCorsiDelloStudente(int matricola){
		final String sql = "select c.`codins`, c.`crediti`,c.`nome`, c.`pd` "
				+ "from corso c, iscrizione i "
				+ "where c.`codins` = i.`codins` AND i.`matricola` = ? ;";
		
		List<Corso> result = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
					
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso corso;
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				corso = new Corso(codins, crediti, nome, pd);
				
				result.add(corso);
			}
			
			rs.close();
			st.close();
			conn.close();
			
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore con il DB!");
			return null;
		}
		
	}

public boolean isStudenteIscrittoAlCorso (Corso corso, int matricola) {
		
		final String sql = "select * from iscrizione where matricola = ? AND codins = ? ;";
		
		boolean result = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result = true;
			}
			
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore con il DB!");	
		}

		return result;

	
}
}
