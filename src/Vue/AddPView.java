package Vue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Controller.AddPController;
import DAO.ConDAO;
import DAO.FonDAO;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddPView implements AddPController{
	
    @FXML
    private TextField pPrice;
    @FXML
    private ComboBox<String> pCategory;
    private ObservableList<String> liste = FXCollections.observableArrayList("Maquillage","Parfumerie","Cutané");
    @FXML
    private VBox eProduct;
    @FXML
    private TextField eNom;
    @FXML
    private TextField pNom;
    @FXML
    private TextField pQuantity;
    @FXML
    private TextArea pDescription;
    @FXML
    private Text error;
    @FXML
    private ImageView pImage;
    private List<Product> list;
    private Stage stage;
    private File file;
    
    public AddPView() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/AddP.fxml"));
		loader.setController(this);
		try {
			TabPane root = loader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		list = FonDAO.getAllProducts(ConDAO.getCon());
    }

    @FXML
	public void Close(ActionEvent event) {
    	stage.close();
    }

	@Override
	public void UpdateView(KeyEvent a) {
		String t = eNom.getText().toLowerCase();
		eProduct.getChildren().clear();
		for(Product p : list) {
			if(p.getNom().toLowerCase().indexOf(t) >= 0) {
				ProductLineView pane = new ProductLineView(p);
				eProduct.getChildren().add(pane);
				pane.setOnMouseClicked(new EventHandler<Event>(){
					@Override
					public void handle(Event arg) {
						ProductLineView temp = (ProductLineView)arg.getSource();
						new VeView(temp);
					}
				});
			}
		}
	}
    
    @FXML
    public void ChangeImage(ActionEvent event) {
   	 file = new FileChooser().showOpenDialog(null);
     if(file != null) {
		if(in(ext(file))) {
			try {
				pImage.setImage(new Image(new FileInputStream(file)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			error.setText("Cette Image n'est pas prise en charge par cette application");
		}
     }
    }
    
 	boolean in(String b) {
		String[] a = new String[] {"jpg", "jfif", "jpeg" , "png" , "gif" , "web"};
		String x = b.toLowerCase();
		for(String h : a)if(x.equals(h))return true;
		return false;
	}
	
	String ext(File file) {
		return splits(file.getName(), '.')[splits(file.getName(), '.').length - 1];
	}
	
	public String[] splits(String a, char r){
		String[] rps = null;
		int i = 0;
		for(char x : a.toCharArray()) {
			if(x == r)i++;
		}
		rps = new String[i + 1];
		i = 0;
		String temps = "";
		for(char s : a.toCharArray()) {
			if(s == r) {
				rps[i] = temps;
				i++;
				temps = "";
			}else {
				temps += s;
			}
		}
		rps[i] = temps;
		return rps;
	}

    @FXML
    public void Add(ActionEvent event) {
        if(!pPrice.getText().isEmpty() && !pCategory.getValue().isEmpty() && !pNom.getText().isEmpty() && file != null && !pQuantity.getText().isEmpty() && !pDescription.getText().isEmpty()) {
        	if(isNum(pPrice.getText()) && isNum(pQuantity.getText())) {
        		int pr = Integer.parseInt(pPrice.getText());
        		int qt = Integer.parseInt(pQuantity.getText());
        		String nm = pNom.getText();
        		String desc = pDescription.getText();
        		String cat = pCategory.getValue();
        		if(Replace(file, "Images/" + file.getName())) {
            		FonDAO.addNewProduct(nm, cat, desc, "Images/" + file.getName(), pr, qt);
            		pPrice.setText("");
            		pQuantity.setText("");
            		pNom.setText("");
            		file = null;
            		pDescription.setText("");
            		pImage = new ImageView();
            		stage.close();
        		}else error.setText("Une erreur inconnu s'est produite");
        	}else error.setText("Il y a un probleme avec le Prix ou alors avec la Quantité");
        }else error.setText("Renseigner tous les champs s'il vous plait");
        
    }
    
    //Copier un file positionné dans la machine dans les repertoires de l'appli
    public boolean Replace(File fil, String a) {
    	try {
    		int i = 0;
    		FileInputStream dataIn = new FileInputStream(fil);
    		byte[] buffer = new byte[3600];
    		File fie = new File(a);
    		FileOutputStream fos = new FileOutputStream(fie);
    		while((i = dataIn.read(buffer))!= -1){
    			fos.write(buffer,0,i);
    		}
    		fos.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
    
    boolean isNum(String a) {
    	try {
    		Integer.parseInt(a);
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }

    @FXML
    public void f8f7f7(ActionEvent event) {
    }
    
    @FXML
    public void fcf9f9(ActionEvent event) {
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pCategory.setItems(liste);
	}

}