package application;
	
import Vue.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @version 1.0.5
 * @category Gestion Stock
 * @since 10/10/2020
 * @author Group 6
 */


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Lancement de la page de login pour connexion
			new LoginView(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
