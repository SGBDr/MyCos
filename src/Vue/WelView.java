package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Controller.WelController;
import DAO.ConDAO;
import DAO.FonDAO;
import Models.Product;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WelView implements WelController {
	
	private Pane root;
	@FXML
	private VBox PView;
	@FXML
	private VBox PList;
	
	public WelView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Wel.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<Product> list = FonDAO.getAllProducts(ConDAO.getCon());
		for(Product p : list) {
			ProductLineView l = new ProductLineView(p);
			l.setOnMouseClicked(new EventHandler<Event>(){
				@Override
				public void handle(Event arg) {
					ProductLineView temp = (ProductLineView)arg.getSource();
					PView.getChildren().clear();   
					PView.getChildren().add(new ProduitView(((ProductLineView) temp).getProd()).getRoot());
				}
			});
			PList.getChildren().add(l);
		}
	}
	
	public Pane getRoot() {
		return root;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddProducts() {
		new AddPView();
		System.err.println("ii");
	}

}
