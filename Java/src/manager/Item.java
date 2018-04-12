package manager;

public class Item {
	//Each item in database will be translated into one of these objects for the main display
	
	private int ID;
	private String Name;
	private long UPC;
	private String location;
	private int quantity;
	//Quantity will be determined by adding all items of the same name to an arrayList. Then the size of that list is the quantity
	//private int Quantity;
	
	
	public Item(int ID, String Name, long UPC, String location, int quantity) {
		this.ID = ID;
		this.Name = Name;
		this.UPC = UPC;
		this.location = location;
		this.quantity = quantity;
	}
	
	public Object [] getInfo() {
		Object [] data = {ID, Name, UPC, location, quantity};
		return data;
	}
	
	public String toString() {
		return "ID: " + ID + "\nName: " + Name + "\n";
	}

}
