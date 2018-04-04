package manager;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UI extends JFrame{
	
	private JScrollPane products;
	private JPanel pane;
	
	public UI() {
		this.setTitle("Inventory Management");
		this.setSize(1500, 950);
		this.setLayout(new BorderLayout());
		this.pane = new JPanel();
		this.add(pane);
		
	}
	
	private void setUp() {
		
		//Add inventory panel to ScrollPane
		//
		products = new JScrollPane();
		this.add(products, BorderLayout.WEST);
	}
	
	private void newIncoming() {
		// Creates popup window with information for new Incoming orders
		
		// If necessary it can add a new Item to the database
		
		// This method will connect to the DB class object.
		
		//Update historical record in database
	}
	
	private void newOutgoing() {
		
		//Opens popup for outgoing shipment.
		
		//update historical record in database
	}
	
	private void changeItemLocation() {
		
		//will be an option in the menu. Maybe under "Edit"
		// This will allow you to change the location for an item.
		// This will then update the database
	}

}
