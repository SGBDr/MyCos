package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProductLineView extends HBox implements Initializable{
	
	
	private Pane root;
	private Product prod;
	
	@FXML
	private Text name;
	@FXML
	private Text quantity;
	@FXML
	private Text price;
	
	public ProductLineView(Product p) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ProductLine.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.prod = p;
		name.setText(prod.getNom());
		quantity.setText(String.valueOf(prod.getQuantity()));
		price.setText(String.valueOf(prod.getPrice()));
		this.getChildren().add(root);
	}
	
	public void setQuantity(int a) {
		this.quantity.setText(String.valueOf(a));
	}
	
	public Pane getRoot() {
		return this.root;
	}
	
	public Product getProd() {
		return this.prod;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
