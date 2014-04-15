package data.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLConnector {
	
	private Connection con;
	private PreparedStatement prepStmt;
	
	public SQLConnector(String host, String user, String passwd, String database, int port) {
		try 
		{ 
		    Class.forName("org.gjt.mm.mysql.Driver"); 
		} 
		catch(ClassNotFoundException cnfe) 
		{ 
		    System.out.println("Driver couldn´t be loaded: "+cnfe.getMessage()); 
		}
		
		try 
		{ 
		    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, passwd);

		} 
		catch(SQLException sqle) 
		{ 
		    System.out.println("Verbindung ist fehlgeschlagen: " + sqle.getMessage()); 
		}
	}

}
