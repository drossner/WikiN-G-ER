package data.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.City;
import data.Entity;

public class WikiNerConnector {

	private Connection con;
	private PreparedStatement selectAllEntityID;
	private PreparedStatement selectEntityCounter;
	private PreparedStatement selectCityIDs;
	private PreparedStatement selectCity;
	private PreparedStatement selectEntity;
	private PreparedStatement updateEntityIDF;
	private PreparedStatement insertCity;
	private PreparedStatement selectCityID;
	private PreparedStatement insertEntity;
	private PreparedStatement updateEntity;
	private PreparedStatement insertCityEntityCon;
	private PreparedStatement selectCityEntCounter;

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
			System.out.println("Driver couldn´t be loaded: "
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
			selectAllEntityID = con.prepareStatement("SELECT id FROM entity");
			selectEntityCounter = con.prepareStatement("SELECT counter FROM entity WHERE id = ? LIMIT 1");
			selectCityIDs = con.prepareStatement("SELECT cityid FROM cityentityconnection WHERE entityid = ?");
			selectCity = con.prepareStatement("SELECT name, latitude, longitude FROM city WHERE id = ? LIMIT 1");
			selectCityEntCounter = con.prepareStatement("SELECT counter FROM cityentityconnection WHERE cityid = ? AND entityid = ? LIMIT 1");
			selectEntity = con.prepareStatement("SELECT id, counter, idf FROM entity WHERE name = ? AND entityType = (SELECT id FROM entitytype WHERE name = ?) LIMIT 1");
			selectCityID = con.prepareStatement("SELECT id FROM city WHERE name = ? LIMIT 1");
			updateEntityIDF = con.prepareStatement("UPDATE entity SET idf = ? WHERE id = ?");
			insertCity = con.prepareStatement("INSERT INTO city (name, latitude, longitude) VALUES (?, ?, ?)");
			insertEntity = con.prepareStatement("INSERT INTO entity (name, entityType, counter) VALUES (?, (SELECT id FROM entitytype WHERE name = ?), ?)");
			updateEntity = con.prepareStatement("UPDATE entity SET counter = ? WHERE id = ?");
			insertCityEntityCon = con.prepareStatement("INSERT INTO cityentityconnection (cityid, entityid, counter) VALUES (?, ?, ?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int[] getAllEntityIDs() throws SQLException {
		ResultSet rs;
		int counter = 0;
		int[] rc = null;

		rs = selectAllEntityID.executeQuery();
		rs.last();
		rc = new int[rs.getRow()];

		if (rs.getRow() == 0)
			return null;

		rs.beforeFirst();
		while (rs.next()) {
			rc[counter++] = rs.getInt(1);
		}

		return rc;
	}

	public void setEntityIDF(double idf, int id) throws SQLException {
		updateEntityIDF.setDouble(1, idf);
		updateEntityIDF.setInt(2, id);
		updateEntityIDF.executeUpdate();
	}

	public int getEntityCounter(int id) throws SQLException {
		ResultSet rs;

		selectEntityCounter.setInt(1, id);
		rs = selectEntityCounter.executeQuery();

		rs.next();

		if (rs.getRow() == 0)
			return -1;

		return rs.getInt(1);
	}

	public Entity getEntity(String name, String type) throws SQLException {
		ResultSet rs;
		int id;
		int counter;
		double idf;

		selectEntity.setString(1, name);
		selectEntity.setString(2, type);
		rs = selectEntity.executeQuery();

		rs.next();
		if (rs.getRow() == 0)
			return null;

		id = rs.getInt(1);
		counter = rs.getInt(2);
		idf = rs.getDouble(3);

		return new Entity(id, counter, idf);
	}

	public City[] getCities(int entityID) throws SQLException {
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
		for (int i = 0; i < cityIDs.length; i++) {
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
		int counter;

		selectEntity.setString(1, entity.getName());
		selectEntity.setString(2, entity.getType());
		
		rs = selectEntity.executeQuery();
		rs.last();

		if (rs.getRow() == 0) {
			insertEntity.setString(1, entity.getName());
			insertEntity.setString(2, entity.getType());
			insertEntity.setInt(3, 1);
			insertEntity.executeUpdate();
			
			selectEntity.setString(1, entity.getName());
			selectEntity.setString(2, entity.getType());
			
			rs = selectEntity.executeQuery();
			rs.beforeFirst();
			rs.next();
			return rs.getInt(1);
		} else {
			rs.beforeFirst();
			rs.next();
			counter = rs.getInt(2);
			updateEntity.setInt(1, ++counter);
			updateEntity.setInt(2, rs.getInt(1));
			updateEntity.executeUpdate();
			
			return rs.getInt(1);
		}
	}

	public void writeConnection(int cityID, int entityID, int count) throws SQLException {
		insertCityEntityCon.setInt(1, cityID);
		insertCityEntityCon.setInt(2, entityID);
		insertCityEntityCon.setInt(3, count);

		insertCityEntityCon.executeUpdate();
	}

	public int getCityEntityCounter(String name, int entityid) throws SQLException {
		ResultSet rs;
		
		selectCity.setString(1, name);
		rs = selectCityID.executeQuery();
		rs.next();
		
		selectCityEntCounter.setInt(1, rs.getInt(1));
		selectCityEntCounter.setInt(2, entityid);
		rs = selectCityEntCounter.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

}