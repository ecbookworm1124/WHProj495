package manager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class HoldingFrame extends JFrame{
	
	private HashMap<Integer, Item> inventory;
	private TableModel model;
	private JTable historyTable;
	
	public HoldingFrame(HashMap<Integer, Item> inventory) {
		
		this.inventory = inventory;
		//// May change the above later, depending on how we implement history
		
		this.setSize(500, 700);
		JPanel panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		getContentPane().add(panel);
		
		
		//panel.setPreferredSize(new Dimension(getContentPane().getHeight() - 100, getContentPane().getWidth() -100));
		
		JLabel lblItemDetials = new JLabel("Item Detials");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblItemDetials, gbc);
		
		TitledBorder border = new TitledBorder("Item Information");
		
		JLabel lblNewLabel = new JLabel("");
		gbc.gridy = 1;
		panel.add(lblNewLabel, gbc);
		
		JPanel infoPanel = new JPanel();
		gbc.gridy = 2;
		panel.add(infoPanel, gbc);
		
		///// Quick calculations to get panel sizes /////////
		
		int height = this.getHeight()/3;
		int width = this.getWidth()-15;
		
		infoPanel.setPreferredSize(new Dimension(width, height));
		
		System.out.println(height + "\n" + width);
		
		infoPanel.setBorder(border);
		infoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("158px"),
				ColumnSpec.decode("158px"),
				ColumnSpec.decode("158px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblInventoryId = new JLabel("Inventory ID:");
		infoPanel.add(lblInventoryId, "2, 2");
		
		JLabel lblIdNum_1 = new JLabel("ID NUM");
		infoPanel.add(lblIdNum_1, "3, 2");
		
		JLabel lblInventoryName = new JLabel("Inventory Name:");
		infoPanel.add(lblInventoryName, "2, 4");
		
		JLabel lblName = new JLabel("Name");
		infoPanel.add(lblName, "3, 4");
		
		JLabel lblUpc = new JLabel("UPC Code");
		infoPanel.add(lblUpc, "2, 6");
		
		JLabel lblUpc_1 = new JLabel("UPC");
		infoPanel.add(lblUpc_1, "3, 6");
		
		JLabel lblWarehouseLocation = new JLabel("Warehouse Location");
		infoPanel.add(lblWarehouseLocation, "2, 8");
		
		JLabel lblLocation = new JLabel("Location");
		infoPanel.add(lblLocation, "3, 8");
		
		JButton btnUpdateLocation = new JButton("Update Location");
		infoPanel.add(btnUpdateLocation, "4, 8");
		
		JLabel lblQuantityAvailable = new JLabel("Quantity Available");
		infoPanel.add(lblQuantityAvailable, "2, 10");
		
		JLabel lblQuantity = new JLabel("Quantity");
		infoPanel.add(lblQuantity, "3, 10");
		
		
		JPanel historyPanel = new JPanel();
		gbc.gridy = 3;
		panel.add(historyPanel, gbc);
		
		TitledBorder historyBorder = new TitledBorder("Inventory History");
		historyPanel.setBorder(historyBorder);
		
		JScrollPane scrollPane = new JScrollPane();
		historyPanel.add(scrollPane);
		
		
		//// Building Table ////////////////
		
		String [] columns = {"Order Number", "Order Date", "Truck Number", "QTY"};
		Object rows [][] = new Object[inventory.size()][columns.length];
		
		
		model = new DefaultTableModel(rows, columns) {
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
		
		historyTable = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
				}
		};
		
		historyTable.setRowHeight(40);
		
		scrollPane.add(historyTable);
		
		historyPanel.add(new JLabel("Place Holder"));
		
		this.pack();
	}
	
	public void build() {
		
	}

}
