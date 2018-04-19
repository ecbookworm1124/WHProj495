package manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<Integer, Item> inventory;
	private JPanel pane;
	private DB server;
	
	public UI() {
		this.setTitle("Warehouse Inventory Management");
		this.setPreferredSize(new Dimension(900, 850));
		this.setLayout(new BorderLayout());
		this.pane = new JPanel();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			server = new DB();
			setup();
			this.add(pane);
			this.pack();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Could Not Connect To Server", "Error", JOptionPane.ERROR_MESSAGE);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
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
	
	public void refresh() {
		
		this.setVisible(false);
		pane.removeAll();
		inventory = server.getFullInventory();
		InventoryPanel inventoryP = new InventoryPanel(inventory, this);
		pane.add(inventoryP);
		
		this.setVisible(true);
	}
	
	public void showDetails(int ID) {
		//Show the details of an item in a window using notebook style tabs
		//One tab for details, another tab for history.
		
		Item currentItem = inventory.get(ID);	
		
		JFrame detailsFrame = new DetailFrame(inventory, currentItem, this);
		detailsFrame.setSize(this.getWidth() - 100, this.getHeight() - 150);
		detailsFrame.setTitle("Warehouse Inventory Management");
		
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
	
	public void changeItemLocation(Item selected, DetailFrame details) {
		
		//will be an option in the menu. Maybe under "Edit"
		// This will allow you to change the location for an item.
		// This will then update the database
		
		JFrame loc = new Location(selected, server, this, details);
		
		loc.setSize(this.getWidth() - 100, (this.getHeight() - 150)/2);
		loc.setTitle("Warehouse Inventory Management");
		
		loc.setVisible(true);
	}

}
