package part.offline.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import data.City;
import data.Entity;

public class DBSQLConnector {

	private Connection con;
	private PreparedStatement selectEntityCounter;
	private PreparedStatement selectCityID;
	private PreparedStatement selectEntityID;
	private PreparedStatement insertEntity;
	private PreparedStatement insertCity;
	private PreparedStatement insertCitEntConnection;
	private PreparedStatement updateEntity;
	

	/**
	 * Init the connection to the given database
	 * 
	 * @param host
	 * @param port
	 * @param database
	 * @param user
	 * @param passwd
	 */
	public void init(String host, int port, String database, String user,
			String passwd) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver couldn�t be loaded: "
					+ cnfe.getMessage());
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":"
					+ port + "/" + database, user, passwd);

		} catch (SQLException sqle) {
			System.out.println("Verbindung ist fehlgeschlagen: "
					+ sqle.getMessage());
		}
		
		try {
			selectEntityCounter = con.prepareStatement("SELECT counter from entity where name = ? and entityType = ? limit 1");
			selectCityID = con.prepareStatement("SELECT id FROM city WHERE name = ?");
			selectEntityID = con.prepareStatement("SELECT id from entity where name = ? and entityType = ? limit 1");
			insertCity = con.prepareStatement("INSERT INTO city (name, latitude, longitude) VALUES (?, ?, ?)");
			insertEntity = con.prepareStatement("INSERT INTO entity (name, entityType, counter) values (?, ?, 1)");
			insertCitEntConnection = con.prepareStatement("INSERT INTO cityEntityConnection VALUES (?, ?, ?)");
			updateEntity = con.prepareStatement("UPDATE entity SET counter = ? where name = ? and entityType = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int writeCity(City city) throws SQLException {
		ResultSet rs;
		
		insertCity.setString(1, city.getName());
		insertCity.setDouble(2, city.getLati());
		insertCity.setDouble(3, city.getLongi());
		
		insertCity.executeUpdate();
		
		selectCityID.setString(1, city.getName());
		rs = selectCityID.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}

	public int writeEntity(Entity entity) throws SQLException {
		ResultSet rs;
		
		selectEntityCounter.setString(1, entity.getName());
		selectEntityCounter.setString(2, entity.getType());				//Fehler !!! Type sollte eignetlich int sein
		
		rs = selectEntityCounter.executeQuery();
		rs.last();
		if(rs.getRow() == 0){
			insertEntity.setString(1, entity.getName());
			insertEntity.setString(2, entity.getType());
			
			insertEntity.executeUpdate();
		}else{
			rs.beforeFirst();
			rs.next();
			updateEntity.setInt(1, rs.getInt(1));
			updateEntity.setString(2, entity.getName());
			updateEntity.setString(3, entity.getType());
			
			updateEntity.executeUpdate();
		}
		
		selectEntityID.setString(1, entity.getName());
		selectEntityID.setString(2, entity.getType());
		
		rs = selectEntityID.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}

	public void writeConnection(int cityID, int entityID, int count) throws SQLException {
		
		insertCitEntConnection.setInt(1, cityID);
		insertCitEntConnection.setInt(2, entityID);
		insertCitEntConnection.setInt(3, count);
		
		insertCitEntConnection.executeUpdate();
	}

}