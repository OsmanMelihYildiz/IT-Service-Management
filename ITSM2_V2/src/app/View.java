package app;

import java.util.Collection;
import java.util.EventListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class View {

	//attributes
	private Control appControl;
	private Model appModel;
	
	private Label lb_Attraktion = new Label("Attraktion:");
	private TextField tf_Attraktion = new TextField();
	
	private Label lb_Kennzeichnung = new Label("Kennzeichnung:");
	private TextField tf_Kennzeichnung = new TextField();
	
	private Label lb_Komponente = new Label("Komponente:");
	private TextField tf_Komponente = new TextField();
	
	private Label lb_Loesung = new Label("Lösung:");
	private TextField tf_Loesung1 = new TextField();
	private TextField tf_Loesung2 = new TextField();
	private TextField tf_Loesung3 = new TextField();
	private Button btn_SpeichernUndAktualisieren = new Button("Speichern und aktualisieren");
	
	private TableView<Attraktion> table;
	private TableColumn<Attraktion, TableView> tc_attraktion;
	private TableColumn<Attraktion, TableView> tc_eindeutigeKennung;
	private TableColumn<Attraktion, TableView> tc_komponente;
	private TableColumn<Attraktion, TableView> tc_loesung;
	
	//constructors
	public View(Control appControl, Model appModel, Stage firstStage) {
		this.appControl = appControl;
		this.appModel = appModel;
		initStage1(firstStage);
	}
	
	private void initStage1(Stage firstStage) {
		Rectangle2D screen = Screen.getPrimary().getBounds();
		
		firstStage.setTitle("Technical Support - Attraction - Incidents");
		firstStage.setY(0);
		firstStage.setWidth(screen.getWidth()/3);
		firstStage.setX(screen.getWidth() - firstStage.getWidth());
		firstStage.setHeight(screen.getMaxY());
		Scene scene1 = initKomponentsS1(firstStage);
		initEventlistener();
		firstStage.setScene(scene1);
		firstStage.show();
	}
	
	
	private Scene initKomponentsS1(Stage firststage) {
		
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(10));
		vb.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		GridPane gp = new GridPane();
		//gp.setGridLinesVisible(true);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(20));
			
			lb_Attraktion.setTextFill(Color.WHITE);
			lb_Kennzeichnung.setTextFill(Color.WHITE);
			lb_Komponente.setTextFill(Color.WHITE);
			lb_Loesung.setTextFill(Color.WHITE);
		
			gp.add(lb_Attraktion, 0, 0);
			gp.add(tf_Attraktion, 1, 0, 2, 1);

			gp.add(lb_Kennzeichnung, 0, 1);
			gp.add(tf_Kennzeichnung, 1, 1, 2, 1);
			
			gp.add(lb_Komponente, 0, 2);
			gp.add(tf_Komponente, 1, 2,2,1);
			
			gp.add(lb_Loesung, 0, 3);
			gp.add(tf_Loesung1, 1, 3);
			gp.add(tf_Loesung2, 2, 3);
			gp.add(tf_Loesung3, 3, 3);
			
			btn_SpeichernUndAktualisieren.setMaxWidth(Double.MAX_VALUE);
			gp.add(btn_SpeichernUndAktualisieren, 1, 4, 2, 1);
		
			table = new TableView<>();
			table.setPrefHeight(firststage.getHeight()-gp.getHeight()-300);
			tc_attraktion = new TableColumn("Attraktion");
			tc_attraktion.setCellValueFactory(new PropertyValueFactory("attraktionsName"));
			tc_eindeutigeKennung = new TableColumn("eindeutige Kennung");
			tc_eindeutigeKennung.setCellValueFactory(new PropertyValueFactory("interneBezeichnung"));
			tc_komponente = new TableColumn("Komponente");
			tc_komponente.setCellValueFactory(new PropertyValueFactory("komponente"));
			tc_loesung = new TableColumn<>("Lösung");
			tc_loesung.setCellValueFactory(new PropertyValueFactory("loesung"));
			
			tc_attraktion.setPrefWidth(150);
			tc_eindeutigeKennung.setPrefWidth(150);
			tc_komponente.setPrefWidth(150);
			tc_loesung.setPrefWidth(150);
			
			table.getColumns().addAll(tc_attraktion,tc_eindeutigeKennung,tc_komponente,tc_loesung);
			//gp.add(table, 0, 5, 5, 4 );
			
			Label lTitle = new Label("Incidents");
			lTitle.setFont(new Font(30.0));
			lTitle.setAlignment(Pos.CENTER);
			vb.getChildren().add(lTitle);
			vb.getChildren().add(gp);
			vb.getChildren().add(table);

		return new Scene(vb);
	}
	
	public void initEventlistener() {
		btn_SpeichernUndAktualisieren.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				table.getItems().clear();
				appControl.speichernUndAktualisieren(tf_Attraktion.getText(), tf_Kennzeichnung.getText(), tf_Komponente.getText(), tf_Loesung1.getText().toUpperCase() + "-" + tf_Loesung2.getText().toUpperCase() + "-" + tf_Loesung3.getText().toUpperCase() );
				tf_Attraktion.clear();
				tf_Kennzeichnung.clear();
				tf_Komponente.clear();
				tf_Loesung1.clear();
				tf_Loesung2.clear();
				tf_Loesung3.clear();
			}
			
		});
		
		tf_Loesung1.setOnKeyReleased(new EventHandler<Event>() {
			public void handle(Event arg0) {
				if(tf_Loesung1.getText().length() == 3) {
					tf_Loesung2.requestFocus();
				}
			};
		});
		
		tf_Loesung2.setOnKeyReleased(new EventHandler<Event>() {
			public void handle(Event arg0) {
				if(tf_Loesung2.getText().length() == 3) {
					tf_Loesung3.requestFocus();
				}
			};
		});
		
		tf_Loesung3.setOnKeyReleased(new EventHandler<Event>() {
			public void handle(Event arg0) {
				if(tf_Loesung3.getText().length() == 3) {
					btn_SpeichernUndAktualisieren.requestFocus();
				}
			};
		});
	
	}
	
	public void aktualisiereTabellenView(LinkedList<Attraktion> list) {
		for (Attraktion a : list) {
			table.getItems().add(a);
		}
		
		table.getSortOrder().add(tc_attraktion);
		table.getSortOrder().add(tc_eindeutigeKennung);
		table.getSortOrder().add(tc_komponente);
		table.sort();
	}
	
	public void zeigeFehlermeldung(String fehlermeldung) {
		JOptionPane.showMessageDialog(null,"Fehler! " + fehlermeldung,"Fehlermeldung",JOptionPane.ERROR_MESSAGE);
	}

}
