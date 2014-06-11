package data.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.City;
import data.Entity;

public class WikiDBSQLConnector {

	private Connection con;
	private PreparedStatement selectAllEntityID;
	private PreparedStatement updateEntityIDF;
	private PreparedStatement selectEntityCounter;
	private PreparedStatement selectEntity;
	private PreparedStatement selectCityIDs;
	private PreparedStatement selectCity;

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
			selectEntityCounter = con.prepareStatement("SELECT counter FROM cityentityconnection WHERE id = ? LIMIT 1");
			selectCityIDs = con.prepareStatement("SELECT cityid FROM cityentityconnection WHERE entityid = ?");
			selectCity = con.prepareStatement("SELECT name, latitude, longitude FROM city WHERE id = ? LIMIT 1");
			selectEntity = con.prepareStatement("SELECT id, counter, idf FROM entity WHERE name = ? AND entityType = ? LIMIT 1");
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
	
	public Entity getEntity(String name, String type) throws SQLException{
		ResultSet rs;
		int id;
		int counter;
		double idf;
		
		selectEntity.setString(1, name);
		selectEntity.setString(2, type);
		rs = selectEntity.executeQuery();
		
		rs.next();
		if(rs.getRow() == 0) return null;
		
		id = rs.getInt(1);
		counter = rs.getInt(2);
		idf = rs.getDouble(3);
		
		return new Entity(id, counter, idf);
	}
	
	public City[] getCities(int entityID) throws SQLException{
		ArrayList<City> temp = new ArrayList<City>();
		int[] cityIDs;
		ResultSet rs;
		String name;
		double latitude, longitude;
		
		selectCityIDs.setInt(1, entityID);
		rs = selectCityIDs.executeQuery();
		rs.afterLast();
		cityIDs = new int[rs.getRow()];
		rs.beforeFirst();
		for(int i = 0; i < cityIDs.length; i++){
			rs.next();
			cityIDs[i] = rs.getInt(1);
		}
		
		for (int i = 0; i < cityIDs.length; i++) {
			selectCity.setInt(1, cityIDs[i]);
			rs = selectCity.executeQuery();
			rs.next();
			name = rs.getString(1);
			latitude = rs.getDouble(2);
			longitude = rs.getDouble(3);
			
			temp.add(new City(name, latitude, longitude));
		}
		
		return temp.toArray(new City[temp.size()]);
	}

}