package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	
		
		
	

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "select s.`matricola`, s.`cognome`, s.`nome`, s.`CDS` "
				+ "from studente s, iscrizione i "
				+ "where s.`matricola` = i.`matricola` AND i.`codins` = ?;"; 
		
		List<Studente> result = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente studente;
				
				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String CDS = rs.getString("CDS");
				
				studente = new Studente(matricola, nome, cognome, CDS);
				result.add(studente);
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return result;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
		
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
