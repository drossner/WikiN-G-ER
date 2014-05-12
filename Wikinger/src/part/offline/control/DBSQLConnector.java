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
		StringBuffer query = new StringBuffer(150);
		
		query.append("INSERT INTO TABLE city VALUES (");
		query.append(city.getName());
		query.append(",");
		query.append(city.getLongi());
		query.append(",");
		query.append(city.getLati());
		query.append(");");
		
		writeCommand(query.toString());
		
		query = new StringBuffer(150);
		
		query.append("SELECT id FROM city WHERE name = ");
		query.append(city.getName());
		query.append(";");
		
		return writeCommand(query.toString())[0];
	}

	public int writeEntity(Entity entity) {
		int[] rcArr;
		StringBuffer query = new StringBuffer(150);
		
		query.append("SELECT entityToDatabase(");
		query.append(entity.getName());
		query.append(",");
		query.append(entity.getType());
		query.append(");");
		
		rcArr = writeCommand(query.toString());
		return rcArr[0];
	}

	public void writeConnection(int cityID, int entityID, int count) {
		StringBuffer query = new StringBuffer(150);
		
		query.append("INSERT INTO TABLE city VALUES (");
		query.append(cityID);
		query.append(",");
		query.append(entityID);
		query.append(",");
		query.append(count);
		query.append(");");
		
		writeCommand(query.toString());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
