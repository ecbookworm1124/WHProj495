package manager;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UI extends JFrame{
	
	private LinkedHashMap<Integer, Item> inventory;
	private JPanel pane;
	private DB server;
	
	public UI() {
		this.setTitle("Warehouse Inventory Management");
		this.setSize(900, 850);
		this.setLayout(new BorderLayout());
		this.pane = new JPanel();
		
		server = new DB();
		
		setup();
		this.add(pane);	
	}
	
	private void setup() {
		
		//Add inventory panel to ScrollPane
		//
		//products = new JScrollPane();
		//this.add(products, BorderLayout.WEST);
		
		inventory = server.getFullInventory();
		
		InventoryPanel inventoryP = new InventoryPanel(inventory, this);
		
		pane.add(inventoryP);
	}
	
	public void showDetails(int ID) {
		//Show the details of an item in a window using notebook style tabs
		//One tab for details, another tab for history.
		
		Item currentItem = inventory.get(ID);
		Object [] itemData = currentItem.getInfo();
		
		String name = (String) itemData[1];
		int prodID = (Integer) itemData[0];
		long UPC = (long) itemData[2];
		String location = (String) itemData[3];
		int quantity = (Integer) itemData[4];
		
		
		JFrame detailsFrame = new JFrame();
		detailsFrame.setSize(this.getWidth() - 100, this.getHeight() - 150);
		detailsFrame.setTitle("Warehouse Inventory Management");
		
		/*
		 * Functionality for details window goes here
		 * 
		 * */
		
		
		
		detailsFrame.setVisible(true);
		
		
	}
	
	private void newIncoming() {
		// Creates popup window with information for new Incoming orders
		
		// If necessary it can add a new Item to the database
		
		// This method will connect to the DB class object.
		
		//Update historical record in database
		
		
		
		
		JFrame incomingFrame = new JFrame();
		incomingFrame.setSize(this.getWidth() - 100, this.getHeight() - 150);
		incomingFrame.setTitle("Warehouse Inventory Management");
	}
	
	private void newOutgoing() {
		
		//Opens popup for outgoing shipment.
		
		//update historical record in database
		
		JFrame outgoingFrame = new JFrame();
		outgoingFrame.setSize(this.getWidth() - 100, this.getHeight() - 150);
		outgoingFrame.setTitle("Warehouse Inventory Management");
	}
	
	private void changeItemLocation() {
		
		//will be an option in the menu. Maybe under "Edit"
		// This will allow you to change the location for an item.
		// This will then update the database
		
		JFrame changeFrame = new JFrame();
		changeFrame.setSize(this.getWidth() - 100, this.getHeight() - 150);
		changeFrame.setTitle("Warehouse Inventory Management");
	}

}
