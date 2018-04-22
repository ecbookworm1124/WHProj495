package manager;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

import java.util.LinkedHashMap;

public class DB{
	//Connects to database
	
	private String userID, passwd, jdbcUrl;
	private Connection conn;
	private Statement stmt;
	private ResultSet itemResults;
	
	
	public DB() throws SQLException{
		
		//Connecting to DB
		//Creating connection object
		
		jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		userID = "SYSTEM";
		passwd = "123456";

		OracleDataSource ds = new OracleDataSource();
		ds.setURL(jdbcUrl);
		conn=ds.getConnection(userID, passwd);
	}
	
	public LinkedHashMap<Integer, Item> getFullInventory(){
		
		//Query the database for all items. Then run that through a for loop which creates a new Item object
		// For each item in the database
		
		////// Implementing SQL connection ///////////////
		LinkedHashMap<Integer, Item> productMap = new LinkedHashMap<Integer, Item>();
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM item ORDER BY itemID";
			itemResults = stmt.executeQuery(query);
			
			while(itemResults.next()) {
				int ID = itemResults.getInt(1);
				String name = itemResults.getString(2);
				long UPC = itemResults.getLong(3);
				int quant = itemResults.getInt(4);
				String locID = itemResults.getString(5);
				
				Item product = new Item(ID, name, UPC, locID, quant);
				productMap.put(ID, product);
			}
			
		} catch (SQLException e) {
			//Do nothing for right now
		}
		
		return productMap;
	}
	
	public boolean addToDB(Item newItem) {
		
		//Adds a new Item to the database, returns true if it was successful, false if not
		
		return false;
	}
	
	public boolean removeFromDB(Item newItem){
		
		// removes an item from the database, returns true if it was successful, false if not.
		
		return false;
	}
	
	public LinkedHashMap<Integer, Order> getHistory(int itemID) {
		
		LinkedHashMap<Integer, Order> currentItem = new LinkedHashMap<Integer, Order>();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM import_export WHERE itemID = "+itemID+" ORDER BY orderID";
			itemResults = stmt.executeQuery(query);
			
			while(itemResults.next()) {
				int orderID = itemResults.getInt(1);
				int itemQTY = itemResults.getInt(3);
				int vendID = itemResults.getInt(4);
				int truckNum = itemResults.getInt(5);
				Date date = itemResults.getDate(6);
				
				System.out.println(itemResults);
				
				Order order = new Order(orderID, itemQTY, vendID, truckNum, date);
				currentItem.put(orderID, order);
			}
			
		} catch (SQLException e) {
			//Do nothing for right now
			return null;
		}
		
		
		return currentItem;
	}
	
	public boolean updateRecords(Item selectedItem, String newLocation) throws SQLException {
		
		int ID = (int) selectedItem.getInfo()[0];
		
		try{
			String query = "INSERT INTO location(locID,loc_aisle, loc_bay, loc_shelf)" + 
					" VALUES ('"+newLocation+"','"+newLocation.substring(0,2)+"','"+newLocation.substring(2,3)+"','"+newLocation.substring(3)+"' )";
			stmt.executeQuery(query);
		}catch(SQLException e){
			// do nothing with this one
		}
		
		String query = "UPDATE item SET locID = '" + newLocation + "' WHERE itemID = " + ID;
		stmt.executeQuery(query);
		
		return false;
	}

}
