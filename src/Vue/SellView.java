package Vue;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Controller.SellController;
import DAO.ConDAO;
import DAO.FonDAO;
import Models.Product;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SellView implements SellController {
	
	@FXML
	private VBox ListP;
	@FXML
	private VBox ListS;
	@FXML
	private TextField search;
	@FXML
	private TextField nbr;
	@FXML
	private Text max;
	
	private List<Product> list;
	
	private Pane root;
	
	private ProductLineView temp;
	
	public SellView() {
		list = FonDAO.getAllProducts(ConDAO.getCon());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SellProduct.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public Pane getRoot() {
		return this.root;
	}
	
	@Override
	public boolean Sell() {
		for(Object p : ListS.getChildren().toArray()) {
			ProductLineView l = (ProductLineView)p;
			FonDAO.TakeIt(l.getProd().getId(), l.getProd().getQuantity());
			FonDAO.newSell(l.getProd());
			new VeView(ListS);
			search.setText("");
		}
		return false;
	}
	
	@Override
	public boolean UpdateView(KeyEvent e) {
		String t = search.getText().toLowerCase();
		ListP.getChildren().clear();
		for(Product p : list) {
			if(p.getNom().toLowerCase().indexOf(t) != -1) {
				ProductLineView pane = new ProductLineView(p);
				ListP.getChildren().add(pane);
				pane.setOnMouseClicked(new EventHandler<Event>(){
					@Override
					public void handle(Event arg) {
						temp = (ProductLineView)arg.getSource();
						new VeView(ListS, temp);
					}
				});
			}
		}
		for(Object p : ListS.getChildren().toArray()) {
			ProductLineView l = (ProductLineView)p;
			System.out.println(l.getProd().getDescription());
			l.setOnMouseClicked(new EventHandler<Event>(){
				@Override
				public void handle(Event arg) {
					ListS.getChildren().remove((Object)arg.getSource());
				}
			});
		}
		return false;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
}
