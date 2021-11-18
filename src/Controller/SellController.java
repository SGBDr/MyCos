package Controller;

import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

public interface SellController extends Initializable {
	public boolean UpdateView(KeyEvent e);
	public boolean Sell();
}
