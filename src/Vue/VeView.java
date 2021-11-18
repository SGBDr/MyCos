package Vue;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.FonDAO;
import Models.Product;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VeView implements Initializable {
	
	@FXML
	private Text max;
	@FXML
	private TextField nbr;
	@FXML
	private Button valide;
	
	public VeView(final ProductLineView temp) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Va.fxml"));
		loader.setController(this);
		Pane ro = null;
		try {
			ro = loader.load();
			max.setText("");
			final Stage stage = new Stage();
			stage.setScene(new Scene(ro));
			valide.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if(isNum(nbr.getText())) {
						FonDAO.MakeIt(temp.getProd().getId(), Integer.parseInt(nbr.getText()));
						stage.close();
					}else {
						max.setText("Rensignez un Nombre");
					}
				}
			});
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public VeView(final VBox ListS, final ProductLineView temp) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Va.fxml"));
		loader.setController(this);
		Pane ro = null;
		try {
			ro = loader.load();
			final Stage stage = new Stage();
			stage.setScene(new Scene(ro));
			valide.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if(isNum(nbr.getText())) {
						if(Integer.parseInt(nbr.getText()) <= temp.getProd().getQuantity()) {
							ProductLineView ll = new ProductLineView(temp.getProd());
							ll.getProd().setQuantity(Integer.parseInt(nbr.getText()));
							ll.setQuantity(Integer.parseInt(nbr.getText()));
							ListS.getChildren().add(ll);
							ll.setOnMouseClicked(new EventHandler<Event>(){
								@Override
								public void handle(Event arg) {
									ProductLineView tem = (ProductLineView)arg.getSource();
									ListS.getChildren().remove(tem);
								}
							});
							stage.close();
						}else {
							max.setText("Il n'y a que " + temp.getProd().getQuantity() + " en stock");
						}
					}else {
						max.setText("Rensignez un Nombre");
					}
				}
			});
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public VeView(final VBox ListS) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Va.fxml"));
		loader.setController(this);
		Pane ro = null;
		try {
			ro = loader.load();
			final Stage stage = new Stage();
			stage.setScene(new Scene(ro));
			valide.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					String name = nbr.getText();
					if(!name.isEmpty()) {
						File file = new File("Facture/" + name + ".txt");
						OutputStream out = null;
						try {
							 out = new FileOutputStream(file);
							String text = "\t \t \t Facture a l'attention de Msr/Mme " + name + "\n";
							out.write(text.getBytes());
							text = "\t ACHAT DE : \n";
							out.write(text.getBytes());
							int pr = 0;
							for(Object p : ListS.getChildren().toArray()) {
								Product r = ((ProductLineView)p).getProd();
								text = "\t - " + r.getNom() + " Quantité : " + r.getQuantity() + " Prix Unitaire : " + r.getPrice() + " FCFA\n";
								pr += r.getPrice()*r.getQuantity();
								out.write(text.getBytes());
								text = "--------------------------------------------------------------------------------------------\n";
								out.write(text.getBytes());
							}
							text = "\t\t\t\t Prix Total A Payer : " + String.valueOf(pr) + " FCFA Remboursement 0 FCFA";
							out.write(text.getBytes());
							stage.close();
							Desktop d = Desktop.getDesktop();
							d.open(file);
							ListS.getChildren().clear();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
						max.setText("UN NOM DE CLIENT SVP");
					}
				}
			});
			stage.show();
			max.setText("Renseigner le nom du client");
			nbr.setPromptText("Nom Du Client");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public boolean isNum(String h) {
		try {
			Integer.parseInt(h);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
