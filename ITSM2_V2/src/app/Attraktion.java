package app;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attraktion {
	// Created by OsmanYildiz
	// 13.11.2021  13:34:58

	//attributes
	 private final StringProperty stringDate = new SimpleStringProperty();
	
	private static int attraktionsNr;
	
	private String attraktionsName;
	private String interneBezeichnung;
	private String komponente;
	private String loesung;
	
	//constructors
	public Attraktion(String attraktionsName, String interneBezeichnung, String komponente, String loesung) {
		this.attraktionsNr++;
		this.attraktionsName = attraktionsName;
		this.interneBezeichnung = interneBezeichnung;
		this.komponente = komponente;
		this.loesung = loesung;
	}

	
	
	//get- and set-methods
	public String getAttraktionsName() {
		return attraktionsName;
	}

	public void setAttraktionsName(String attraktionsName) {
		this.attraktionsName = attraktionsName;
	}

	public String getInterneBezeichnung() {
		return interneBezeichnung;
	}

	public void setInterneBezeichnung(String interneBezeichnung) {
		this.interneBezeichnung = interneBezeichnung;
	}

	public String getKomponente() {
		return komponente;
	}

	public void setKomponente(String komponente) {
		this.komponente = komponente;
	}

	public String getLoesung() {
		return loesung;
	}

	public void setLoesung(String loesung) {
		this.loesung = loesung;
	}
	
	public final StringProperty stringDateProperty() {
		return stringDate; 
	}
	 
	public final String getStringDate() { 
		return stringDate.get(); 
	}
	
	public final void setStringDate(String s) {
		stringDate.set(s); 
	}
}
