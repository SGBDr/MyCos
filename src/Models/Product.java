package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
	private StringProperty image, nom, description;
	private IntegerProperty quantity, price, id;
	
	public Product(String image, String nom, String description, int quantity, int price, int id) {
		this.image = new SimpleStringProperty(image);
		this.nom = new SimpleStringProperty(nom);
		this.description = new SimpleStringProperty(description);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.price = new SimpleIntegerProperty(price);
		this.id = new SimpleIntegerProperty(id);
	}

	public final StringProperty imageProperty() {
		return this.image;
	}
	

	public final String getImage() {
		return this.imageProperty().get();
	}
	

	public final void setImage(final String image) {
		this.imageProperty().set(image);
	}
	

	public final StringProperty nomProperty() {
		return this.nom;
	}
	

	public final String getNom() {
		return this.nomProperty().get();
	}
	

	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	

	public final StringProperty descriptionProperty() {
		return this.description;
	}
	

	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	

	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}
	

	public final IntegerProperty quantityProperty() {
		return this.quantity;
	}
	

	public final int getQuantity() {
		return this.quantityProperty().get();
	}
	

	public final void setQuantity(final int quantity) {
		this.quantityProperty().set(quantity);
	}
	

	public final IntegerProperty priceProperty() {
		return this.price;
	}
	

	public final int getPrice() {
		return this.priceProperty().get();
	}
	

	public final void setPrice(final int price) {
		this.priceProperty().set(price);
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
	



	
}
