package business;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import gui.FreizeitparkControl;
import gui.FreizeitparkView;

public class FreizeitparkModel {

	private FreizeitparkControl parkControl;
	private FreizeitparkView parkView;
	
	
	private Freizeitpark freizeitpark;
	
	public Freizeitpark getFreizeitpark() {
		return freizeitpark;
	}
	public void setFreizeitpark(Freizeitpark freizeitpark) {
		this.freizeitpark = freizeitpark;
	}
	
	
	public FreizeitparkModel() {
		
	}
	
		public void schreibeFreizeitparkInCsvDatei() throws IOException{
		
		BufferedWriter aus = new BufferedWriter(new FileWriter("Freizeitpark.csv", true));
		aus.write(this.getFreizeitpark().gibFreizeitparkZurueck(';'));
		aus.close();
	}
}
