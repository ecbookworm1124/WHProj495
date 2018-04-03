package manager;

import java.util.ArrayList;

public class DB{
	//Connects to database
	
	public DB() {
		
	}
	
	public ArrayList<Item> getFullInventory(){
		
		//Query the database for all items. Then run that through a for loop which creates a new Item object
		// For each item in the database
		return null;
	}
	
	public boolean addToDB(Item newItem) {
		
		//Adds a new Item to the database, returns true if it was successful, false if not
		
		return false;
	}
	
	public boolean removeFromDB(Item newItem){
		
		// removes an item from the database, returns true if it was successful, false if not.
		
		return false;
	}

}
