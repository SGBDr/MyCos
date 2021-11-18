package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private IntegerProperty id, state;
	private StringProperty name, mdp;
	
	public User(int id, String name, int state) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.state = new SimpleIntegerProperty(state);
	}
	
	public User(String name, int state, String mdp) {
		this.mdp = new SimpleStringProperty(mdp);
		this.name = new SimpleStringProperty(name);
		this.state = new SimpleIntegerProperty(state);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

	public final IntegerProperty stateProperty() {
		return this.state;
	}
	

	public final int getState() {
		return this.stateProperty().get();
	}
	

	public final void setState(final int state) {
		this.stateProperty().set(state);
	}
	

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	

	public final StringProperty mdpProperty() {
		return this.mdp;
	}
	

	public final String getMdp() {
		return this.mdpProperty().get();
	}
	

	public final void setMdp(final String mdp) {
		this.mdpProperty().set(mdp);
	}
	
	
}
