package manager;

import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;

import java.util.LinkedHashMap;

public class DB{
	//Connects to database
	
	private String userID, passwd, jdbcUrl, query, sqlString;
	private Connection conn;
	private Statement stmt;
	private ResultSet itemResults;
	
	
	public DB() {
		
		//Connecting to DB
		//Creating connection object
		
		jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		userID = "SYSTEM";
		passwd = "123456";
		
		try {
			OracleDataSource ds = new OracleDataSource();
			ds.setURL(jdbcUrl);
			conn=ds.getConnection(userID, passwd);
		} catch (SQLException e) {
			//As of right now we dont need this to do anything.
			System.out.println("Couldnt connect");
		}
		
		
	}
	
	public LinkedHashMap<Integer, Item> getFullInventory(){
		
		//Query the database for all items. Then run that through a for loop which creates a new Item object
		// For each item in the database
		
		////// Implementing SQL connection ///////////////
		
		boolean over = true;
		LinkedHashMap<Integer, Item> productMap = new LinkedHashMap<Integer, Item>();
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			query = "SELECT * FROM item ORDER BY itemID";
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
			
		} catch (Exception e) { //Will change this to SQLException e later
			//Do nothing for right now
			over = false;
		}
		
		if(over) {
			return productMap;
		}
		/////////////////////////////////////////////////
		
		String [] name = {"Screw Driver", "Napkins", "Lawn Chair", "Projector", "Ball Point Pens", "College Ruled Paper"};
		long [] UPC = {33423, 33242, 22352, 452, 9972, 9828994};
		String [] location = {"33-L-32", "33-L-2", "33-A-1", "32-A-1", "12 - B - 31", "3-L-4"};
		int [] quantity = {55, 15, 35, 79, 43, 17};
		for(int i = 0; i < name.length; i++) {
			Item product = new Item(i, name[i], UPC[i], location[i], quantity[i]);
			productMap.put(i, product);
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
	
	public boolean updateRecords() {
		
		
		
		return false;
	}

}
