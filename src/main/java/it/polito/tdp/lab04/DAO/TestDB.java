package it.polito.tdp.lab04.DAO;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		List<Corso> result = new ArrayList<Corso>(cdao.getTuttiICorsi());
		for(Corso c : result) {
			System.out.println(c);
		}
		
	}

}
