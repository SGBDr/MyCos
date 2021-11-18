package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAO.FonDAO;
import DAO.ConDAO;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserView implements Initializable {
	
    @FXML
    private Text state;

    @FXML
    private Text user;
    private User us;
    private Pane root;
    
    private VBox use;
    
    public UserView(User a, VBox use) {
    	this.us = a;
    	this.use = use;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/UserLine.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		user.setText(us.getName());
		state.setText(us.getState() == 0 ? "Ventes" : us.getState() == 1 ? "Stock" : "Super Utilisateur");
    }

    @FXML
    void ModifyUser(ActionEvent event) {
    	new ModUView(us);
    }
    
    public Pane getRoot() {
    	return this.root;
    }

    @FXML
    void DeleteUser(ActionEvent event) {
    	FonDAO.DeleteUser(us);
    	List<User> list = FonDAO.getAllUsers(ConDAO.getCon());
    	use.getChildren().clear();
		for(User s : list){
			UserView e = new UserView(s, use);
			use.getChildren().add(e.getRoot());
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
