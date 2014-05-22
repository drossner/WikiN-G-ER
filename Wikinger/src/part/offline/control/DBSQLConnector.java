package part.offline.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			selectEntityCounter = con.prepareStatement("SELECT counter from entity where name = \"?\" and entityType = \"?\" limit 1;");
			selectCityID = con.prepareStatement("SELECT id FROM city WHERE name = \"?\";");
			selectEntityID = con.prepareStatement("");
			insertCity = con.prepareStatement("INSERT INTO city (name, latitude, longitude) VALUES (\"?\", \"?\", \"?\");");
			insertEntity = con.prepareStatement("INSERT INTO entity (name, entityType, counter) values (\"?\", \"?\", 1;");
			insertCitEntConnection = con.prepareStatement("");
			updateEntity = con.prepareStatement("UPDATE entity SET counter = ");
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

	public int writeEntity(Entity entity) {
		ResultSet rs;
		
		selectEntityCounter.setString(1, entity.getName());
		selectEntityCounter.setString(2, entity.getType());				//Fehler !!! Type sollte eignetlich int sein
		
		rs = selectEntityCounter.executeQuery();
		
		if(rcArr.length == 0){
			insertEntity.setString(1, entity.getName());
			insertEntity.setString(2, entity.getType());
		}else{
			query.append(rcArr[0]+1);
			query.append(" where name = \"");
			query.append(entity.getName());
			query.append("\" and entityType = \"");
			query.append(entity.getType());
			query.append("\";");
		}
		
		writeInsertCommand(query.toString());
		
		query = new StringBuffer(150);
		query.append("SELECT id from entity where name = \"");
		query.append(entity.getName());
		query.append("\" limit 1;");
		
		rcArr = writeCommand(query.toString());
		
		return rcArr[0];
	}

	public void writeConnection(int cityID, int entityID, int count) {
		StringBuffer query = new StringBuffer(150);
		
		query.append("INSERT INTO cityEntityConnection VALUES (");
		query.append(cityID);
		query.append(",");
		query.append(entityID);
		query.append(",");
		query.append(count);
		query.append(");");
		
		writeInsertCommand(query.toString());
	}

	public int[] writeCommand(String query) {
		Statement stmt;

		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			rs.last();
			int[] rc = new int[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				rc[i++] = rs.getInt(1);
			}

			return rc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	private int writeInsertCommand(String query) {
		Statement stmt;

		try {
			stmt = con.createStatement();

			int rc = stmt.executeUpdate(query);

			return rc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

}