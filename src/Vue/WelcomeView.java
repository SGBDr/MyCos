package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.WelcomeController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WelcomeView implements WelcomeController {
	
	private Stage stage;
	private Pane root = null;
	
	@FXML
	private Text title;
	@FXML
	private Button accueil;
	@FXML
	private Button ventes;
	@FXML
	private Button stock;
	@FXML
	private Button sessions;
	@FXML
	private Button stats;
	@FXML
	private HBox container;
	@FXML
	private BorderPane bg;
	
	
	public WelcomeView(int state){
		stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Welcome.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		root.getStylesheets().add(getClass().getResource("../Css/Welcome.css").toExternalForm());
		stage.setScene(new Scene(root));
		stage.show();
		stage.setTitle("MyCos");
		stage.setMinHeight(561);
		stage.setMaxHeight(561);
		stage.setMinWidth(998);
		stage.setMaxWidth(998);
		//reouverture de la page de login lors de la fermeture du stage principale
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				new LoginView(new Stage());
			}
		});
		InitStyle(state);
	}
	
	void InitState(int s) {
		System.out.println("#####################################  " + s);
		if(s == 0) {
			sessions.setDisable(true);
			stock.setDisable(true);
			OpenStock();
		}else if(s == 1) {
			ventes.setDisable(true);
			sessions.setDisable(true);
			OpenSell();
		}
		OpenStat();
	}
	
	public void InitStyle(int state) {
		root.setId("bg");
		InitState(state);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

	@Override
	public boolean OpenSell() {
		title.setText("Ventes");
		container.getChildren().clear();
		container.getChildren().add(new SellView().getRoot());
		return false;
	}

	@Override
	public boolean OpenStock() {
		title.setText("Stock");
		System.out.println("ok");
		container.getChildren().clear();
		container.getChildren().add(new WelView().getRoot());
		return false;
	}

	@Override
	public boolean OpenSession() {
		title.setText("Sessions");
		System.out.println("ok");
		container.getChildren().clear();
		container.getChildren().add(new SessionView().getRoot());
		return false;
	}

	@Override
	public boolean OpenStat() {
		title.setText("Statistiques");
		System.out.println("ok");
		container.getChildren().clear();
		container.getChildren().add(new StatsView().getRoot());
		return false;
	}


}
