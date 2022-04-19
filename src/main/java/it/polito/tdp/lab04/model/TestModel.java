package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		for(Corso c : model.getTuttiICorsi())
			System.out.println(c);
		/*
		 * 	Write here your test model
		 */

	}

}
