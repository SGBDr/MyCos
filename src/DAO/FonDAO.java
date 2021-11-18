package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Models.Product;
import Models.User;
import Models.Vente;

public class FonDAO {
	
	//fonction de verification du password et du login
	public static int VerifyUser(String user, String pwd, Connection con) {
		int state = -1;
		pwd = sha1(pwd);
		String query = "SELECT state FROM utilisateurs WHERE name='" + user.toString() + "' AND pwd='" + pwd + "'";
		try {
			Statement stat = con.createStatement();
			ResultSet result = stat.executeQuery(query);
			while(result.next()){
				//récupération du state du user dans le cas ou il existe
				state = result.getInt("state"); 
			}	
			result.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//renvoie du state pour Ajourner la page d'accuiel
		return state;
	}
	
	//fonction de récupération des différentes ventes faites via MyCos
	public static List<Vente> GetSellByDay() {
		String query = "SELECT COUNT(pid) , date FROM vente GROUP BY date";
		List<Vente> list = new ArrayList<Vente>();
		try {
			Statement stat = ConDAO.getCon().createStatement();
			ResultSet result = stat.executeQuery(query);
			while(result.next()){
				//instanciation d'un objet de type vente
				Vente v = new Vente(result.getString("date"), result.getInt("COUNT(pid)"));
				//ajout dans la liste
				list.add(v);
			}
			result.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//renvoie de liste
		return list;
	}
	
	//fonction de modification des informations référents a un produit en bd PDI -> (Prix , Description, Image)
	public static void ModifyPDI(int a, String b, String h, String n, int id) {
		Statement stat = null;
		String query = "UPDATE produits SET nom = '" + n + "' AND description = '" + b + "' AND image = '" + h + "' AND prix = " + String.valueOf(a) + " WHERE id = " + String.valueOf(id);
		try {
			stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//fonction d'ajout d'un nouveau produit
	public static void addNewProduct(String nom, String type, String description, String image, int prix, int quantite) {
		String query = "INSERT INTO produits(nom, description, image, prix, quantite, category) VALUES('" + nom + "','" + description + "','" + image + "'," + String.valueOf(prix) + "," + String.valueOf(quantite) + ",'" + type + "')";
		try {
			Statement stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//fonction de modification d'un utilisateur, fait uniquement par un super utilisateur
	public static void ModifyUser(User u) {
		String query = "UPDATE utilisateurs SET name = '" + u.getName() + "' AND state = " + String.valueOf(u.getState()) + (u.getMdp() == "n" ? "WHERE id = " + String.valueOf(u.getId()) : " AND pwd = '" + sha1(u.getMdp()) + "' WHERE id = " + String.valueOf(u.getId()) );
		try {
			Statement stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//fonction de suppression d'un utilisateur 
	public static void DeleteUser(User p) {
		String query = "DELETE FROM utilisateurs WHERE id = " + String.valueOf(p.getId());
		try {
			Statement stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//fonction d'ajout d'un nouveau produit
	public static void newSell(Product p) {
		String query = "INSERT INTO vente VALUES("+ p.getId() + "," + p.getQuantity() + ",'" + getDate() + "')";
		try {
			Statement stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//fonction d'ajout d'un nouvelle utilisateur
	public static void AddUser(User user) {
		String query = "INSERT INTO utilisateurs(name, pwd, state) VALUES('"+ user.getName() + "','" + sha1(user.getMdp()) + "'," + user.getState() + ")";
		try {
			Statement stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//fonction de parsing pour créer un format de date a partir de l'object calendar
	public static String getDate() {
		@SuppressWarnings("deprecation")
		String jj = String.valueOf(Calendar.getInstance().getTime().getDay() + 1);
		@SuppressWarnings("deprecation")
		String mm = String.valueOf(Calendar.getInstance().getTime().getMonth() + 1);
		int i = 5;
		String yy = "";
		for(char b : String.valueOf(Calendar.getInstance().getTime()).toCharArray()) {
			if(i == 0) {
				yy += b;
			}
			if(b == ' ') {
				i--;
			}
		}
		return jj + "/" + mm + "/" + yy;
	}
	
	//fonction de vente, permettant de prendre une quantité exacte de produits en bd
	public static void TakeIt(int id, int nbr) {
		String query = "SELECT quantite FROM produits WHERE id = " + String.valueOf(id);
		int quantite = 0;
		try {
			Statement stat = ConDAO.getCon().createStatement();
			ResultSet result = stat.executeQuery(query);
			while(result.next()){
				quantite = result.getInt("quantite");
			}
			result.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nbr = quantite - nbr;
		query = "UPDATE produits SET quantite = " + String.valueOf(nbr) + " WHERE id = " + String.valueOf(id);
		try {
			Statement stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//fonction de vente, permettant de decrémenter une quatité precise de produits dans la bd
	public static void MakeIt(int id, int nbr) {
		String query = "SELECT quantite FROM produits WHERE id = " + String.valueOf(id);
		int quantite = 0;
		try {
			Statement stat = ConDAO.getCon().createStatement();
			ResultSet result = stat.executeQuery(query);
			while(result.next()){
				quantite = result.getInt("quantite");
			}
			result.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nbr = quantite + nbr;
		query = "UPDATE produits SET quantite = " + String.valueOf(nbr) + " WHERE id = " + String.valueOf(id);
		try {
			Statement stat = ConDAO.getCon().createStatement();
			stat.executeUpdate(query);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//fonction permettant de recuperer tout les produits present en bd
	public static List<Product> getAllProducts(Connection con) {
		List<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM produits WHERE quantite >= 1";
		try {
			Statement stat = con.createStatement();
			ResultSet result = stat.executeQuery(query);
			while(result.next()){
				list.add(new Product(result.getString("image"), result.getString("nom"), result.getString("description"), result.getInt("quantite"), result.getInt("prix"), result.getInt("id")));
			}
			result.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//fonction permettant de recuperer tout les utilisateurs present en bd
	public static List<User> getAllUsers(Connection con) {
		List<User> list = new ArrayList<User>();
		String query = "SELECT * FROM utilisateurs";
		try {
			Statement stat = con.createStatement();
			ResultSet result = stat.executeQuery(query);
			while(result.next()){
				list.add(new User(result.getInt("id"), result.getString("name"), result.getInt("state")));
			}
			result.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//fonctions d'encryptage de mdp avec la norme sha1
	public static String sha1(String pwd) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] result = md.digest(pwd.getBytes());
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < result.length ; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
