package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


//Class de connexion a la BD de MyCos
public class ConDAO{ 
	
	private static Connection conn = null;
	private static String BDD = "mycos";
	private static String url = "jdbc:mysql://localhost:3306/" + BDD + "?autoReconnect=false&useSSL=true";
	private static String user = "root";
	private static String passwd = "";
	
	private ConDAO() {}
	
	public static Connection getCon(){
		if(conn == null){
			try {
				//Initialisation du drivers
				Class.forName("com.mysql.jdbc.Driver");
				//recuperation de l'instance de la db
				conn = DriverManager.getConnection(url, user, passwd);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La connexion à la dase de données n'a pas été établie", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
		return conn;
	}
	


}
