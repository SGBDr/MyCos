package Vue;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Controller.ModPController;
import DAO.FonDAO;
import Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ModPView implements ModPController{
	
    @FXML
    private ImageView image;
    @FXML
    private Button fermer;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private Button btnimage;
    @FXML
    private TextField nom;
    private Product prod;
    private Stage s;
    private File file = null;
    
    public ModPView(Product p) throws FileNotFoundException {
    	this.prod = p;
    	Pane root = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ModP.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e1) {

		}
		s = new Stage();
		s.setScene(new Scene(root));
		s.show();
		s.setResizable(false);
    	image.setImage(new Image(new FileInputStream(new File(prod.getImage()))));
    	prix.setText(String.valueOf(prod.getPrice()));
    	description.setText(prod.getDescription());
    	nom.setText(prod.getNom());
    }

    @Override
	public void Modifier(ActionEvent event) {
    	if(!nom.getText().isEmpty() && !prix.getText().isEmpty() && !description.getText().isEmpty()) {
    		if(isNum(prix.getText())) {
    			if(file == null)file = new File(prod.getImage());
    			if(Replace(file, "Images/" + file.getName())) {
    				System.err.println("ok2");
    				System.out.println(Integer.parseInt(prix.getText()));
    				FonDAO.ModifyPDI(Integer.parseInt(prix.getText()), description.getText(), "Images/" + file.getName(), nom.getText(), prod.getId());
    				s.close();
    			}
    		}
    	}
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
    
	public boolean isNum(String h) {
		try {
			Integer.parseInt(h);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	boolean in(String b) {
		String[] a = new String[] {"jpg", "jfif", "jpeg" , "png" , "gif" , "web"};
		String x = b.toLowerCase();
		for(String h : a) {
			System.err.println("    " + h + ":    " + x);
			if(b.equals(x))return true;
		}
		return false;
	}
	
	String ext(File file) {
		return splits(file.getName(), '.')[splits(file.getName(), '.').length - 1];
	}
	
	public String[] splits(String a, char r){
		String[] rps = null;
		int i = 0;
		for(char x : a.toCharArray()) {
			if(x == '.')i++;
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
    
    @Override
    public void modImg() {
    	 file = new FileChooser().showOpenDialog(null);
         if (file != null) {
			if(in(ext(file))) {
				try {
					image.setImage(new Image(new FileInputStream(file)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
         }else {
        	 file = new File(prod.getImage());
         }
         
    }
    
    @Override
    public void close() {
    	s.close();
    }
}
