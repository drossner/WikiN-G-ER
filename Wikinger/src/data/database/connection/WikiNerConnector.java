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
import data.EntityType;

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
	private PreparedStatement countCities;
	private PreparedStatement countEntityConn;
	private PreparedStatement maximumEntityCountCity;
	private PreparedStatement selectEntityTypes;

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
			selectEntity = con.prepareStatement("SELECT id, counter, idf FROM entity WHERE name = ? AND entityType = ? LIMIT 1");
			selectCityID = con.prepareStatement("SELECT id FROM city WHERE name = ? ");
			selectEntityTypes = con.prepareStatement("SELECT id, name FROM entitytype");
			updateEntityIDF = con.prepareStatement("UPDATE entity SET idf = ? WHERE id = ?");
			insertCity = con.prepareStatement("INSERT INTO city (name, latitude, longitude) VALUES (?, ?, ?)");
			insertEntity = con.prepareStatement("INSERT INTO entity (name, entityType, counter) VALUES (?, (SELECT id FROM entitytype WHERE name = ?), ?)");
			updateEntity = con.prepareStatement("UPDATE entity SET counter = ? WHERE id = ?");
			insertCityEntityCon = con.prepareStatement("INSERT INTO cityentityconnection (cityid, entityid, counter) VALUES (?, ?, ?)");
			countCities = con.prepareStatement("SELECT COUNT(id) FROM city");
			countEntityConn = con.prepareStatement("SELECT COUNT(entityid) FROM cityentityconnection WHERE entityid = ?");
			maximumEntityCountCity = con.prepareStatement("SELECT MAX(counter) FROM cityentityconnection WHERE cityid = ?");
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

	public Entity getEntity(String name, int typeID) throws SQLException {
		ResultSet rs;
		int id;
		int counter;
		double idf;

		selectEntity.setString(1, name);
		selectEntity.setInt(2, typeID);
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
		ArrayList<Integer> cityIDs = new ArrayList<Integer>();
		ResultSet rs = null;
		String name;
		double latitude, longitude;

		
		selectCityIDs.setInt(1, entityID);
		rs = selectCityIDs.executeQuery();
		rs.beforeFirst();
		while(rs.next()) {
			cityIDs.add(rs.getInt(1));
		}

		for (int i = 0; i < cityIDs.size(); i++) {
			selectCity.setInt(1, cityIDs.get(i));
			rs = selectCity.executeQuery();
			rs.next();
			name = rs.getString(1);
			latitude = rs.getDouble(2);
			longitude = rs.getDouble(3);

			temp.add(new City(cityIDs.get(i), name, latitude, longitude));
		}
		
		City[] rc = temp.toArray(new City[temp.size()]) ;
		return rc;
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

	public int getCityEntityCounter(int cityID, int entityID) throws SQLException {
		ResultSet rs;
		
		selectCityEntCounter.setInt(1, cityID);
		selectCityEntCounter.setInt(2, entityID);
		rs = selectCityEntCounter.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	public int getCityCount() throws SQLException {
		ResultSet rs;
		
		rs = countCities.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}

	public int getEntityConnCount(int entityID) throws SQLException {
		ResultSet rs;
		
		countEntityConn.setInt(1, entityID);
		rs = countEntityConn.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}

	public int getMaxEntity(int i) throws SQLException {
		ResultSet rs;
		
		maximumEntityCountCity.setInt(1, i);
		rs = maximumEntityCountCity.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}

	public EntityType[] getEntityTypes() throws SQLException {
		ArrayList<EntityType> rc = new ArrayList<EntityType>();
		ResultSet rs;
		
		rs = selectEntityTypes.executeQuery();
		rs.afterLast();
		rs.beforeFirst();
		while(rs.next()){
			rc.add(new EntityType(rs.getInt(1), rs.getString(2)));
		}
		return rc.toArray(new EntityType[rc.size()]);
	}

}