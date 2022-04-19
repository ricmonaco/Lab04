package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;

	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}



	public List<Corso> getTuttiICorsi(){
		
		List<Corso> corsi = new LinkedList<Corso>(corsoDao.getTuttiICorsi());
		return corsi;
	}
	
	public Studente getStudenteByMatricola(int matricola) {
		return studenteDao.getStudenteByMatricola(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso (Corso corso){
		List<Studente> result = new LinkedList<Studente>(corsoDao.getStudentiIscrittiAlCorso(corso));
		return result;
	}
	
	public List<Corso> getCorsiDelloStudente(int matricola){
		List<Corso> result = new LinkedList<Corso>(studenteDao.getCorsiDelloStudente(matricola));
		return result;
	}
	
	public boolean isStudenteIscrittoAlCorso(Corso corso, int matricola) {
		return studenteDao.isStudenteIscrittoAlCorso(corso, matricola);
	}
}
