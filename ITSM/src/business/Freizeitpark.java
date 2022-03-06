package business;

import ownUtil.PlausiException;

public class Freizeitpark {

	//OMY
	private String name;
	private String internBez;
	private String grund;
	private String lösung;
	private int line;
	
	//Konstruktur
	public Freizeitpark(String name, String internBez, String grund, String lösung, int line) throws PlausiException {
		
		this.name = name;
		this.internBez = internBez;
		this.grund = grund;
		this.lösung = pruefeFormat(lösung);
		this.line = line;
	}
	//ABC-DEF-GHI
	public String pruefeFormat(String lösung) {
		if(lösung.substring(3, 4).equals("-") && lösung.substring(7, 8).equals("-")) {
			return lösung;
		}else if(!lösung.contains("-") && lösung.length() == 9){
			lösung = lösung.substring(0, 3) + "-" + lösung.substring(4,7) + "-" + lösung.substring(8, 12);
		}
		return lösung;
	}
	
	public String gibFreizeitparkZurueck(char trenner){
  		return this.getName() + trenner 
  		    + this.getInternBez() + trenner
  		    + this.getGrund() + trenner
  		    + this.getLösung() + "."+"\r\n";
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
	public String getLösung() {
		return lösung;
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
	public void setLösung(String lösung) {
		this.lösung = lösung;
	}
	
}
