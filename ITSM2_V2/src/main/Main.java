package main;
import app.Control;
import gui.FreizeitparkControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		//new FreizeitparkControl(primaryStage);
		new Control(primaryStage);
	}
	
}
