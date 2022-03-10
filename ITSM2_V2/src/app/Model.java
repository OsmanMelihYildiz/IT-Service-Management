package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Scanner;

public class Model {

	//attributes
	private LinkedList<Attraktion> list;
	
	//constructors
	
	public Model() {
		list = new LinkedList<Attraktion>();
	}
	
	//get- and set-methods
	public LinkedList<Attraktion> getList() {
		return list;
	}

	public void setList(LinkedList<Attraktion> list) {
		this.list = list;
	}

	//methods
	public void readDataFromCsv() throws Exception {
		File file = new File("Incidentlist.csv");
		
		if(!file.exists()) {
			file.createNewFile();
		}else{
			BufferedReader br = new BufferedReader(new FileReader(new File("Incidentlist.csv")));
			String readedLine;
			
			while((readedLine = br.readLine()) != null ){
				Scanner sc = new Scanner(readedLine);
				sc.useDelimiter(";");
				String attraktion = sc.next();
				String intereBez = sc.next();
				String komponente = sc.next();
				String loesung = sc.next();
				
				Attraktion attrakion = new Attraktion(attraktion, intereBez, komponente, loesung);
				list.add(attrakion);
				sc.close();
			}
			
			br.close();
		}
	}
	
	public void writeDataToCsv() throws Exception {
		File file = new File("Incidentlist.csv");
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		String s="";
		int z = list.size();
		int c = z;
		for (Attraktion a : list) {
			if(c > 0 && c != z) {
				s+= "\n";
			}
			s += a.getAttraktionsName() + ";" + a.getInterneBezeichnung() + ";" + a.getKomponente() + ";" + a.getLoesung();
			c--;
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(s);
		bw.flush();
		bw.close();	
	}
	
	public void addNewIncident(String attraktion, String interneBezeichnung, String komponente, String loesung) {
		list.add(new Attraktion(attraktion, interneBezeichnung, komponente, loesung));
	}

}
