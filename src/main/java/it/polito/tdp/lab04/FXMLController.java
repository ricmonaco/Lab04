/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorsi"
    private Button btnCercaIscrittiCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="btnRiempimentoAutomatico"
    private Button btnRiempimentoAutomatico; // Value injected by FXMLLoader

    @FXML // fx:id="comboCorsi"
    private ComboBox<Corso> comboCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	
    	if(txtMatricola == null || txtMatricola.getText().equals("")) {
    		txtRisultato.setText("Inserisici una matricola per cercare i corsi!");
    	}
    	
    	int matricola;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Inserisici una matricola numerica!");
    		return;
    	}
    	
    	List<Corso> corsi = new LinkedList<Corso>(model.getCorsiDelloStudente(matricola));    	
    	
    	if(corsi.isEmpty())
    		txtRisultato.setText("Lo studente non è presente nel Database!");
    	
    	for(Corso c : corsi) {
    		txtRisultato.appendText(c.toString() +"\n");
    	}
    	
    	
    }

    @FXML
    void doCercaIscrittiCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	
    	Corso corso = comboCorsi.getValue();
    	
    	if(corso == null) {
    		txtRisultato.setText("ERRORE! Devi selezionare un corso!");
    		return;
    	}
    	
    	List<Studente> studenti = new LinkedList<Studente>(model.getStudentiIscrittiAlCorso(corso));
    	
    	for(Studente s : studenti) {
    		txtRisultato.appendText(s.toString()+"\n");
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	//funge da tasto cerca per il punto 5 
    	txtRisultato.clear();
    	
    	Corso corso = comboCorsi.getValue();
    	
    	if(txtMatricola.getText().isEmpty() || txtMatricola.getText() == null || corso == null) {
    		txtRisultato.setText("Inserisci una matricola e seleziona un corso");
    		return;
    	}
    	
    	int matricola;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
			txtRisultato.setText("Inserisci la matricola nel formato giusto: numerico");
			return;
		}
    	
    	if(model.isStudenteIscrittoAlCorso(corso, matricola))
    		txtRisultato.appendText("Lo studente è iscritto al corso");
    	else
    		txtRisultato.appendText("Lo studente non è iscritto al corso");
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();
    }

    @FXML
    void doRiempimentoAutomatico(ActionEvent event) {
    	
    	if(txtMatricola.getText().isEmpty()) {
    		txtRisultato.setText("Inserisci una matricola"); 
    		return;
    		}
    	
    	int matricola;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch (NumberFormatException e) {
			txtRisultato.setText("Inserisci una matricola numerica!!");
			return;
		}
    	
    	txtNome.setText(model.getStudenteByMatricola(matricola).getNome());
    	txtCognome.setText(model.getStudenteByMatricola(matricola).getCognome());
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorsi != null : "fx:id=\"btnCercaIscrittiCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRiempimentoAutomatico != null : "fx:id=\"btnRiempimentoAutomatico\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }



    
    public void setModel(Model model) {
    	this.model = model;
    	List<Corso> corsi = new LinkedList<Corso>(this.model.getTuttiICorsi());
    	for(Corso c : corsi)
    		comboCorsi.getItems().add(c);
    	
	}

}
