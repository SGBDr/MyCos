package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.FonDAO;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ModUView implements Initializable{
    @FXML
    private TextField name;
    @FXML
    private TextField mdp;
    @FXML
    private TextField state;
    
    private User user;
    private Stage stage;
    public int a = 12;
    
    public ModUView(User user) {
    	this.user = user;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ModUser.fxml"));
		loader.setController(this);
		try {
			Pane root = loader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			name.setText(user.getName());
			state.setText(String.valueOf(user.getState()));
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    public ModUView(int a) {
    	this.a = a;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ModUser.fxml"));
		loader.setController(this);
		try {
			Pane root = loader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void Valider(ActionEvent event) {
    	if(Sta(state.getText()) && a == 12) {
        	user.setName(name.getText().isEmpty() ? user.getName() : name.getText());
        	user.setState(Integer.parseInt(state.getText()));
        	user.setMdp(!mdp.getText().isEmpty() ? mdp.getText() : "n");
        	FonDAO.ModifyUser(user);
        	stage.close();
    	}
    	
    	if(a == 0 && !name.getText().isEmpty() && !state.getText().isEmpty() && !mdp.getText().isEmpty()) {
    		User user = new User("", 0, "");
        	user.setName(name.getText());
        	user.setState(Integer.parseInt(state.getText()));
        	user.setMdp(mdp.getText());
        	FonDAO.AddUser(user);
        	stage.close();
    	}

    }
    
    boolean Sta(String a) {
    	return a.equals("0") || a.equals("1") || a.equals("2") ;
    }

    @FXML
    void Exit(ActionEvent event) {
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
