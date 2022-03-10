package app;

import javafx.stage.Stage;

public class Control {
	
	//attributes
	private Model appModel;
	private View appView;
	
	//constructors
	public Control(Stage firstStage) {
		this.appModel = new Model();
		this.appView = new View(this,appModel,firstStage);
		readDataFromCsv();
		appView.aktualisiereTabellenView(appModel.getList());
	}

	//methods
	public void readDataFromCsv() {
		try {
			appModel.readDataFromCsv();
		}catch (Exception e) {
			appView.zeigeFehlermeldung("Fehler beim einlesen der Incidentlist.csv. Fehlercode: " + e.getMessage());
		}
	} 
	
	public void speichernUndAktualisieren(String attraktion, String interneBezeichnung, String komponente, String loesung) {
		appModel.addNewIncident(attraktion, interneBezeichnung, komponente, loesung);
		appView.aktualisiereTabellenView(appModel.getList());
		
		try {
			appModel.writeDataToCsv();
		} catch (Exception e) {
			appView.zeigeFehlermeldung("Fehler beim abspeichern der neuen Ergebnisse in Incidentlist.csv! Fehlercode: " + e.getMessage());
		}
	}
}
