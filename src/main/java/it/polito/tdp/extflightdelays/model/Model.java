package it.polito.tdp.extflightdelays.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
	}
	
	public SimpleWeightedGraph<Airport, DefaultWeightedEdge> getAirpot(int dm){
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		List <Airport> vertex = this.dao.loadAllAirports();
		Graphs.addAllVertices(this.grafo, vertex);
		this.dao.creaGrafo(this.grafo, dm);
		System.out.println("GRAFO CREATO!");
		System.out.println("# VERTICI: " + grafo.vertexSet().size());
		System.out.println("# ARCHI: " + grafo.edgeSet().size());
		
		return this.grafo;
	}
}
