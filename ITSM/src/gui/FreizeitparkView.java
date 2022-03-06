package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import business.Freizeitpark;
import business.FreizeitparkModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.PlausiException;


public class FreizeitparkView{
	
	private FreizeitparkModel parkModel;
	private FreizeitparkControl parkControl;
	
	private int line = 0;
	
	//MainFenster
	private Pane pane = new Pane();

	
	//NeuesEreigniss

	private Label lnewereigniss = new Label("Neues Ereigniss");
	private Label lname = new Label("Name");
	private Label linternBez = new Label("Interne Bez.");
	private Label lgrund = new Label("Grund");
	private Label llösung = new Label("Lösung");
	private TextField txtname = new TextField();
	private TextField txtbez = new TextField();
	private TextField txtgrund = new TextField();
	private TextField txtlösung = new TextField();

	//EreignissSuchen

	private Label lnewsuche = new Label("Ereigniss suchen");
	private TextField txtnames = new TextField();
	private TextField txtbezs = new TextField();
	private TextField txtgrunds = new TextField();
	
	//Button
	private Button banzeigen = new Button("Lösung anzeigen");
	private Button bspeichern = new Button("Ereigniss speichern");
	private Button breset = new Button("Clear");
	
	private TextArea txtAnzeige = new TextArea();
    private MenuBar mnbrMenuLeiste  	= new MenuBar();
    private Menu mnDatei             	= new Menu("Datei");
    private MenuItem mnItmCsvExport 	= new MenuItem("csv-Export");
    private MenuItem mnItmTxtExport 	= new MenuItem("txt-Export");
    
    private Button sucheLösung = new Button("Suche Lösung");
	private Freizeitpark freizeitpark;


	
	HBox hbox = new HBox();
	VBox vbox = new VBox();
	
	public FreizeitparkView(FreizeitparkControl parkControl, Stage PrimaryStage, FreizeitparkModel parkModel) {
		this.parkControl = parkControl;
		this.parkModel = parkModel;
		Scene scene = new Scene(this.pane, 560, 340);
		
    	
		PrimaryStage.setScene(scene);
    	PrimaryStage.setTitle("Verwaltung -- ITSM");
    	PrimaryStage.show();
    	
    	
    	this.initKomponenten();
		this.initListener();
	}
	
	
	
	public void initKomponenten(){
		
		Font font = new Font("Arial", 24);
		Font font2 = new Font("Arial", 12);
		//Titel
		lnewereigniss.setFont(font);
		lnewereigniss.setLayoutX(50);
		lnewereigniss.setLayoutY(30);
		lnewsuche.setFont(font);
		lnewsuche.setLayoutX(330);
		lnewsuche.setLayoutY(30);
		
		//Labels
		lname.setFont(font2);
		lname.setLayoutX(30);
		lname.setLayoutY(70);
		linternBez.setFont(font2);
		linternBez.setLayoutX(30);
		linternBez.setLayoutY(110);
		lgrund.setFont(font2);
		lgrund.setLayoutX(30);
		lgrund.setLayoutY(150);
		llösung.setFont(font2);
		llösung.setLayoutX(30);
		llösung.setLayoutY(190);
		pane.getChildren().addAll(lnewereigniss, lnewsuche, lname, linternBez, lgrund, llösung);
	
		//txtfields
		txtname.setLayoutX(120);
		txtname.setLayoutY(70);
		txtbez.setLayoutX(120);
		txtbez.setLayoutY(110);
		txtgrund.setLayoutX(120);
		txtgrund.setLayoutY(150);
		txtlösung.setLayoutX(120);
		txtlösung.setLayoutY(190);
		txtlösung.setText("format: 123-456-789");
		txtnames.setLayoutX(330);
		txtnames.setLayoutY(70);
		txtbezs.setLayoutX(330);
		txtbezs.setLayoutY(110);
		txtgrunds.setLayoutX(330);
		txtgrunds.setLayoutY(150);
		
		pane.getChildren().addAll(txtname,txtbez,txtgrund,txtlösung
				, txtnames, txtbezs, txtgrunds );
		
		//TextArea
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(330);
    	txtAnzeige.setLayoutY(190);
     	txtAnzeige.setPrefWidth(150);
     	txtAnzeige.setPrefHeight(50);
       	pane.getChildren().add(txtAnzeige);

		//Buttons
		breset.setLayoutX(80);
		breset.setLayoutY(260);
		bspeichern.setLayoutX(140);
		bspeichern.setLayoutY(260);
		banzeigen.setLayoutX(280);
		banzeigen.setLayoutY(260);
		sucheLösung.setLayoutX(400);
		sucheLösung.setLayoutY(260);
		pane.getChildren().addAll(bspeichern, banzeigen, breset, sucheLösung);
		
		// Menu
   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvExport);
  	    this.mnDatei.getItems().add(mnItmTxtExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
	}



	public void initListener() {
			
			bspeichern.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	        	    nehmeProblemAuf(line);
	        	    
	            }
		    });
		    banzeigen.setOnAction(new EventHandler<ActionEvent>() {
		    	@Override
		        public void handle(ActionEvent e) {
		            zeigeFreizeitparkAn();
		        } 
	   	    });  
		    breset.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	        	    allesLoeschen();
	            }
		    });
		    sucheLösung.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	        	    
						try {
							sucheLoesung();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
	            }
		    });
		 mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	        	    schreibeFreizeitparkIndatei("csv");
	        	    line += 1;
	            }
		    });
		 mnItmTxtExport.setOnAction(new EventHandler<ActionEvent>() {
		    	@Override
		        public void handle(ActionEvent e) {
		    		schreibeFreizeitparkIndatei("txt");
		        } 
	   	    });
		    
		 txtlösung.setOnMousePressed(new EventHandler<MouseEvent>() {
		     
		     @Override
		     public void handle(MouseEvent event) {
		         txtlösung.setText("");
		     }
		 });
	
		
	}
	
	public void sucheLoesung() throws IOException{
		FileReader fr = new FileReader("Freizeitbaeder.csv");
	    BufferedReader br = new BufferedReader(fr);

	    String zeile = "";
	    String sucht = txtnames.getText() + ";" + txtbezs.getText() + ";" + txtgrunds.getText();
	   
	    while( (zeile = br.readLine()) != null )
	    {
	    	if(zeile.substring(0, sucht.length()).equals(sucht)){
	    	System.out.println(zeile);
	    	txtAnzeige.setText(zeile.substring(sucht.length()+1));
	    	}else {
	    		txtAnzeige.setText("Noch keine lösung vorhanden!");
	    	}
	    }
	    br.close();
	}
	
	public void allesLoeschen() {
		txtname.setText("");
		txtgrund.setText("");
		txtbez.setText("");
		txtlösung.setText("format: 123-456-789");
		txtnames.setText("");
		txtgrunds.setText("");
		txtbezs.setText("");
		txtAnzeige.setText("");
		
	}
	public void nehmeSucheAuf() {
		txtnames.getText();
		txtbezs.getText();
		txtgrunds.getText();
		zeigeInformationsfensterAn("Suche wird eingeleitet");
		
	}
	public void nehmeProblemAuf(int line) {
		line = line +1;
		try{
    		parkModel.setFreizeitpark(new Freizeitpark(
    			txtname.getText(), 
   	            txtbez.getText(),
   	            txtgrund.getText(),
    		    txtlösung.getText(),
    		    line
    		    ));
    		zeigeInformationsfensterAn("Das Freizeitpark wurde aufgenommen!");
       	}
       	catch(PlausiException exc){
       		zeigeFehlermeldungsfensterAn(exc.getPlausiTyp() + "er ", exc.getMessage());
     	}
		System.out.println("Zeile: " + line);
    }

	

	private void zeigeFreizeitparkAn(){
    	if(parkModel.getFreizeitpark() != null){
    		txtAnzeige.setText(
    			parkModel.getFreizeitpark().gibFreizeitparkZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde kein Freizeitpark aufgenommen!");
    	}
    }	

    void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String fehlertyp, String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	fehlertyp + "Fehler", meldung).zeigeMeldungsfensterAn();
    }
    private void schreibeFreizeitparkIndatei(String typ) {
    	parkControl.schreibeFreizeitparkInDatei(typ);
    }
}
