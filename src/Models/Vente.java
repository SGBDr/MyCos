package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vente {
	private StringProperty date;
	private IntegerProperty num;
	
	public Vente(String date, int num) {
		this.date = new SimpleStringProperty(date);
		this.num = new SimpleIntegerProperty(num);
	}

	public final StringProperty dateProperty() {
		return this.date;
	}
	

	public final String getDate() {
		return this.dateProperty().get();
	}
	

	public final void setDate(final String date) {
		this.dateProperty().set(date);
	}
	

	public final IntegerProperty numProperty() {
		return this.num;
	}
	

	public final int getNum() {
		return this.numProperty().get();
	}
	

	public final void setNum(final int num) {
		this.numProperty().set(num);
	}
	
	
}
