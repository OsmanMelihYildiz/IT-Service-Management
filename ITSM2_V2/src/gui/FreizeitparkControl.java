package gui;
import java.io.IOException;

import business.FreizeitparkModel;
import business.Freizeitpark;
import javafx.stage.Stage;

public class FreizeitparkControl {

	private FreizeitparkModel parkModel;
	private FreizeitparkView parkView;
	
	public FreizeitparkControl(Stage PrimaryStage) {
		this.parkModel = new FreizeitparkModel();
		this.parkView = new FreizeitparkView(this, PrimaryStage, parkModel);
		
	}
	
	void schreibeFreizeitbaederInDatei (String typ){
		try{
			if("csv".contentEquals(typ)) {
		
			parkModel.schreibeFreizeitparkInCsvDatei();
			
		}else {
			parkView.zeigeInformationsfensterAn("Noch nicht implementiert!");
		}
		}catch (IOException exc) {
			parkView.zeigeFehlermeldungsfensterAn("IO Fehler", typ);
		}
	}
}
