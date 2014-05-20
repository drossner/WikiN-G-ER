package part.offline.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.City;
import data.Entity;

public class DBSQLConnector {

	private Connection con;

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

	}

	public int writeCity(City city) {
		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO city (name, latitude, longitude) VALUES (\"");
		query.append(city.getName());
		query.append("\", ");
		query.append(city.getLati());
		query.append(", ");
		query.append(city.getLongi());
		query.append(");");
		
		writeInsertCommand(query.toString());
		
		query = new StringBuffer();
		
		query.append("SELECT id FROM city WHERE name = \"");
		query.append(city.getName());
		query.append("\";");
		
		return writeCommand(query.toString())[0];
	}

	public int writeEntity(Entity entity) {
		int[] rcArr;
		StringBuffer query = new StringBuffer(250);
		
		query.append("SELECT counter from entity where name = \"");
		query.append(entity.getName());
		query.append("\" and entityType = \"");
		query.append(entity.getType());
		query.append("\" limit 1;");
		
		System.out.println(query.toString());
		
		rcArr = writeCommand(query.toString());
		query = new StringBuffer(150);
		
		if(rcArr.length == 0){
			query.append("insert into entity (name, entityType, counter) values (\"");
			query.append(entity.getName());
			query.append("\", \"");
			query.append(entity.getType());
			query.append("\", ");
			query.append(" 1);");
		}else{
			query.append("update entity set counter = ");
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