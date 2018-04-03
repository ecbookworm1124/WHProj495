package manager;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class InventoryPanel extends JPanel{
	
	ArrayList<Item> inventory;
	
	private TableModel table;
	
	//Panel to display the full inventory
	
	public InventoryPanel(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	private void buildTable() {
		// Building the table of inventory
		
		//NOTE: FOR NOW THIS IS JUST TEST DATA
		
		Object rows [][] = {
				{0, "Screw Driver", "33423", "W3L32"}, //Item: ID, Name, UPC, Location
				{1, "Napkins", "33242", "W2L2"},
				{2, "Lawn Chair", "32253", "E3L4"},
				{3, "Projector", "34523", "E5L2"},
				{4, "Ball Point Pens", "5432", "A2L1"},
				{5, "College Ruled Paper", "3224", "A1L1"}
		};
		
		String [] columns = {"ID", "Name", "UPC", "Location"};
		
		table = new DefaultTableModel(rows, columns) {
			public Class getColumnClass(int column) {
				Class value = null;
				
				if((column>=0) && (column < getColumnCount())) {
					value = getValueAt(0, column).getClass();
					return value;
				}
				else {
					return value;
				}
			}
		};
		
		
	}

}
