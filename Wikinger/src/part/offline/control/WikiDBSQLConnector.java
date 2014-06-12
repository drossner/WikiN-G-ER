package part.offline.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WikiDBSQLConnector {
	
	private Connection con;
	private PreparedStatement selectAllEntityID;
	private PreparedStatement updateEntityIDF;
	private PreparedStatement selectEntityCounter;
	
	public void init(String host, int port, String database, String user, String passwd){
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
		try {
			selectAllEntityID = con.prepareStatement("SELECT id FROM entity");
			selectEntityCounter = con.prepareStatement("SELECT counter FROM entity WHERE id = ?");
			updateEntityIDF = con.prepareStatement("UPDATE entity SET idf = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int[] getAllEntityIDs() throws SQLException{
		ResultSet rs;
		int counter = 0;
		int[] rc = null;
		
		rs = selectAllEntityID.executeQuery();
		rs.last();
		rc = new int[rs.getRow()];
		
		if(rs.getRow() == 0) return null;
		
		rs.beforeFirst();
		while(rs.next()){
			rc[counter++] = rs.getInt(1);
		}
		 
		return rc;
	}
	
	public void setEntityIDF(double idf) throws SQLException{
		updateEntityIDF.setDouble(1, idf);
		updateEntityIDF.executeUpdate();
	}
	
	public int getEntityCounter(int id) throws SQLException{
		ResultSet rs;
		
		selectEntityCounter.setInt(1, id);
		rs = selectEntityCounter.executeQuery();
		
		rs.next();
		
		if(rs.getRow() == 0) return -1;
		
		return rs.getInt(1);
	}

}
