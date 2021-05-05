/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	int dm =0;
    	try {
    		dm = Integer.parseInt(this.distanzaMinima.getText());
    	}catch(NumberFormatException nfe) {
    		this.txtResult.setText("il parametro inserito non é un numero");
    		return;
    	}
    	String s="";
    	SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo = this.model.getAirpot(dm);
    	this.txtResult.setText("il grafo possiede:\n"+"# VERTICI: " + grafo.vertexSet().size()+"\n# ARCHI: " + grafo.edgeSet().size()+"\n");
    	Set <DefaultWeightedEdge> airp = grafo.edgeSet();
    	for(DefaultWeightedEdge edge : airp) {
    		s+=grafo.getEdgeSource(edge).getAirportName();
    		s+="\t";
    		s+=grafo.getEdgeTarget(edge).getAirportName();
    		s+="\t";
    		s+=grafo.getEdgeWeight(edge);
    		s+="\n";
    		this.txtResult.appendText(s);
    		s="";
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
