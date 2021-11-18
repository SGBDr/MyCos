package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Controller.SessionController;
import DAO.ConDAO;
import DAO.FonDAO;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SessionView implements SessionController{
	private Pane root;
	
	@FXML
	VBox users;
	
	private List<User> list;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	public SessionView() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/sesseion.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		list = FonDAO.getAllUsers(ConDAO.getCon());
		for(User s : list) {
			UserView e = new UserView(s, users);
			users.getChildren().add(e.getRoot());
		}
	}
	
	public Pane getRoot() {
		return this.root;
	}

	public void AddUser() {
		new ModUView(0);
	}

}
