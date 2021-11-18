package Vue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.PController;
import Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProduitView implements PController {
	
	@FXML 
	private ImageView image;
	@FXML
	private Text name;
	@FXML
	private Text quantity;
	@FXML
	private Text price;
	@FXML
	private Text description;
	
	private Pane root;
	//private int id = 0;
	private Product pro;
	
	public ProduitView(Product a) {
		pro = a;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ProduitView.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			image.setImage(new Image(new FileInputStream(new File(a.getImage()))));
			name.setText(a.getNom());
			quantity.setText(String.valueOf(a.getQuantity()));
			price.setText(String.valueOf(a.getPrice()));
			description.setText(a.getDescription());
		//	id = a.getId();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pane getRoot() {
		return this.root;
	}
	
	@Override
	public void EditProduct() {
		try {
			new ModPView(pro);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
