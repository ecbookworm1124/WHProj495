package manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DB{
	//Connects to database
	
	public DB() {
		
		//Connecting to DB
		//Creating connection object
		
	}
	
	public LinkedHashMap<Integer, Item> getFullInventory(){
		
		//Query the database for all items. Then run that through a for loop which creates a new Item object
		// For each item in the database
		
		
		LinkedHashMap<Integer, Item> productMap = new LinkedHashMap<Integer, Item>();
		
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
