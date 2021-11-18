package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.LoginController;
import DAO.ConDAO;
import DAO.FonDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView implements LoginController{
	
	@FXML
	private TextField users;
	@FXML
	private PasswordField password;
	@FXML
	private BorderPane bg;
	@FXML
	private Button connect;
	@FXML
	private Text error;
	
	private Stage stage = null;
	private Pane root = null;
	
	public LoginView(Stage stage) {
		this.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Login.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {

		}
		root.getStylesheets().add(getClass().getResource("../Css/Login.css").toExternalForm());
		this.stage.setScene(new Scene(root));
		this.stage.show();
		this.stage.setTitle("MyCos Login");
		this.stage.setMinHeight(515);
		this.stage.setMinWidth(771);
		InitStyle();
	}
	
	public Pane getRoot() {
		return this.root;
	}
	
	public void InitStyle() {
		bg.setId("bg");
		connect.setId("connect");
		users.setId("users");
		password.setId("pwd");
	}

	@Override
	public void TestConnexion() {
		if(!users.getText().isEmpty() && !password.getText().isEmpty()) {
			System.out.println(FonDAO.VerifyUser(users.getText(), password.getText(), ConDAO.getCon()));
			if(FonDAO.VerifyUser(users.getText(), password.getText(), ConDAO.getCon()) != -1) {
				int state = FonDAO.VerifyUser(users.getText(), password.getText(), ConDAO.getCon());
				this.stage.close();
				new WelcomeView(state);
			}else {
				error.setText("Ce Compte N'existe Pas");
			}
		}else {
			error.setText("Remplissez les Champs");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
}
