package business;

import ownUtil.PlausiException;

public class Freizeitpark {

	//OMY
	private String name;
	private String internBez;
	private String grund;
	private String l�sung;
	private int line;
	
	//Konstruktur
	public Freizeitpark(String name, String internBez, String grund, String l�sung, int line) throws PlausiException {
		
		this.name = name;
		this.internBez = internBez;
		this.grund = grund;
		this.l�sung = pruefeFormat(l�sung);
		this.line = line;
	}
	//ABC-DEF-GHI
	public String pruefeFormat(String l�sung) {
		if(l�sung.substring(3, 4).equals("-") && l�sung.substring(7, 8).equals("-")) {
			return l�sung;
		}else if(!l�sung.contains("-") && l�sung.length() == 9){
			l�sung = l�sung.substring(0, 3) + "-" + l�sung.substring(4,7) + "-" + l�sung.substring(8, 12);
		}
		return l�sung;
	}
	
	public String gibFreizeitparkZurueck(char trenner){
  		return this.getName() + trenner 
  		    + this.getInternBez() + trenner
  		    + this.getGrund() + trenner
  		    + this.getL�sung() + "."+"\r\n";
  	}
	
	
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	//Getter
	public String getName() {
		return name;
	}
	public String getInternBez() {
		return internBez;
	}
	public String getGrund() {
		return grund;	
	}
	public String getL�sung() {
		return l�sung;
	}
	
	//Setter
	public void setName(String name) {
		this.name = name;
	}
	public void setInternBez(String internBez) {
		this.internBez = internBez;
	}
	public void setGrund(String grund) {
		this.grund = grund;
	}
	public void setL�sung(String l�sung) {
		this.l�sung = l�sung;
	}
	
}
