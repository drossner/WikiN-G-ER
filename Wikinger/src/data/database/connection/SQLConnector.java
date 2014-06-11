package data.database.connection;

import java.io.UnsupportedEncodingException;
import java.sql.*;

public class SQLConnector {
	
	private Connection con;
	private PreparedStatement prepStmt;
	private PreparedStatement selectAllText;
	private PreparedStatement selectRevID;
	private PreparedStatement selectPageTitle;
	private PreparedStatement selectText;
	
	/**
	 * Init the connection to the given database
	 * @param host
	 * @param port
	 * @param database
	 * @param user
	 * @param passwd
	 */
	public void init(String host, int port, String database, String user, String passwd){
//		prepStmt = new PreparedStatement[threads];
		
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
			prepStmt = con.prepareStatement("SELECT page_id FROM page WHERE page_title = ? OR page_title like ? OR page_title like ?;");
			selectAllText = con.prepareStatement("SELECT old_id from text");
			selectText = con.prepareStatement("SELECT old_text from text where old_id = ? LIMIT 1");
			selectRevID = con.prepareStatement("SELECT rev_page FROM revision WHERE rev_id = ? LIMIT 1");
			selectPageTitle = con.prepareStatement("SELECT page_title FROM page WHERE page_id = ? LIMIT 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
	
	}
	
	public int[] getAllText() throws SQLException{
		ResultSet rs;
		int counter = 0;
		int[] rc = null;
		
		rs = selectAllText.executeQuery();
		rs.last();
		rc = new int[rs.getRow()];
		
		if(rs.getRow() == 0) return null;
		
		rs.beforeFirst();
		while(rs.next()){
			rc[counter++] = rs.getInt(1);
		}
		 
		return rc;
	}
	
	public String getText(int oldTextID) throws SQLException{
		ResultSet rs;
		String rc = null;
		
		selectText.setInt(1, oldTextID);
		rs = selectText.executeQuery();
		rs.next();
		
		if(rs.getRow() == 0) return rc;
		
		rc = rs.getString(1);
		return rc;
	}
	
	public int getRevID(int oldTextID) throws SQLException{
		ResultSet rs;
		int rc = 0;
		
		selectRevID.setInt(1, oldTextID);
		rs = selectRevID.executeQuery();
		rs.next();
		
		if(rs.getRow() == 0) return 0;
		
		rc = rs.getInt(1);
		return rc;
	}
	
	public String getPageTitle(int revID) throws SQLException{
		ResultSet rs;
		String rc = null;
		
		selectPageTitle.setInt(1, revID);
		rs = selectPageTitle.executeQuery();
		rs.next();
		
		if(rs.getRow() == 0) return null;
		
		rc = rs.getString(1);
		return rc;
	}

	/**
	 * Search the page_ids for a given cityname
	 * @param cityName
	 * @param threadID deprecated parameter, if you use threads, use their id, otherwise use something else
	 * @return
	 */
	public int[] getPageIDs(String cityName){
		cityName = cityName.replaceAll(" ", "_");
		cityName = cityName.replaceAll("\'", "\\%");
		try {
			prepStmt.setString(1, cityName);
			prepStmt.setString(2, cityName+"\\_%");
			prepStmt.setString(3, cityName+",\\_%");
			ResultSet rs = prepStmt.executeQuery();
			rs.last();
			
			if(rs.getRow() == 0){
				System.out.println(cityName);
			}
			
			int[] rc = new int[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while(rs.next()){
				rc[i++]=rs.getInt(1);
			}
		
			return rc;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return rev_ids to given page_ids
	 * @param pageIDs
	 * @return
	 */
	public int[] getRevIDs(int[] pageIDs){
		Statement stmt;
		StringBuffer query = new StringBuffer(150);
		query.append("SELECT rev_id FROM revision WHERE rev_page = '"+pageIDs[0]+"'");
		for (int i = 1; i < pageIDs.length; i++) {
			query.append(" OR rev_page = '"+pageIDs[i]+"'");
		}
		query.append(";");
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query.toString());
			
			rs.last();
			int[] rc = new int[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while(rs.next()){
				rc[i++]=rs.getInt(1);
			}
			
			return rc;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns the wikitext to given rev_ids
	 * @param revIDs
	 * @return
	 */
	public String[] getTexts(int[] revIDs){
		Statement stmt;
		StringBuffer query = new StringBuffer(150);
		query.append("SELECT old_text FROM text WHERE old_id = '"+revIDs[0]+"'");
		for (int i = 1; i < revIDs.length; i++) {
			query.append(" OR old_id = '"+revIDs[i]+"'");
		}
		query.append(";");
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query.toString());
			rs.last();
			String[] rc = new String[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while(rs.next()){
				//rc[i++]=rs.getString(1);
				try {
					rc[i++] = new String(rs.getBytes(1), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			rs.close();
			stmt.close();
			return rc;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
