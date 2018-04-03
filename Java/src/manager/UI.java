package manager;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class UI extends JFrame{
	
	private JScrollPane products;
	
	public UI() {
		this.setTitle("Inventory Management");
		this.setSize(1500, 950);
		this.setLayout(new BorderLayout());
		
	}
	
	private void setUp() {
		
		products = new JScrollPane();
		this.add(products, BorderLayout.WEST);
	}

}
