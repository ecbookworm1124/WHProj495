package manager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class InventoryPanel extends JPanel{
	
	private LinkedHashMap<Integer, Item> inventory;
	
	private JTable table_2;
	private JButton searchBtn;
	private JTextField searchField;
	private DefaultTableModel model;
	
	//Panel to display the full inventory
	
	public InventoryPanel(LinkedHashMap<Integer, Item> inventory, UI myGUI) {
		this.inventory = inventory;
		setLayout(new BorderLayout(0, 0));
		/////////////////////////////////////////////////////////////////////////////////
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		JPanel title = new JPanel();
		JLabel lblInventoryList = new JLabel("Inventory List");
		title.add(lblInventoryList);
		
		
		JPanel search = new JPanel();
		search.setLayout(new BoxLayout(search, BoxLayout.X_AXIS));
		JLabel searchLabel = new JLabel("Inventory ID");
		searchField = new JTextField(10);
		searchBtn = new JButton("Search");
		
		search.add(searchLabel);
		search.add(searchField);
		
		
		top.add(title);
		top.add(search);
			
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,10,100,10);
		
		JButton viewDetails = new JButton("View Details");
		viewDetails.addActionListener(e->getSelected(myGUI));
		viewDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(viewDetails, gbc);
		
		JButton newShipment = new JButton("New Incoming Shipment");
		newShipment.setAlignmentX(Component.CENTER_ALIGNMENT);
		gbc.gridy = 1;	
		panel.add(newShipment, gbc);
		
		JButton outShipment = new JButton("New Outgoing Shipment");
		outShipment.setAlignmentX(Component.CENTER_ALIGNMENT);	
		gbc.gridy=2;
		panel.add(outShipment, gbc);
		
		
		buildTable();
		panel.setBorder(new EmptyBorder(50,50,50,50));
		
		search.add(searchBtn); // Must go last because actionlistener is added in buildTable
		add(top, BorderLayout.NORTH);
		add(panel, BorderLayout.EAST);
	}
	
	private boolean getSelected(UI myGUI) {

		int selectedRow = table_2.getSelectedRow();	
		
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please Select a Row First", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}else{
			myGUI.showDetails((Integer) model.getValueAt(selectedRow, 0));
		}
		return true;
	}
	
	private void buildTable() {
		
		String [] columns = {"Inventory ID", "Item Name", "UPC", "Location", "Quantity"};	
		Object rows [][] = new Object[inventory.size()][columns.length];
		
		int counter = 0;
		for(Map.Entry<Integer, Item> product: inventory.entrySet()) {
			Item item = product.getValue();
			rows[counter] = item.getInfo();
			counter ++;
		}
		
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
		
		table_2 = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
				}
		};
		table_2.setRowHeight(40);
		
		TableRowSorter<TableModel> sortCols = new TableRowSorter<TableModel>(model);
		int locationColumn = 3; // Just whichever column holds the location data
		sortCols.setComparator(locationColumn, (Comparator<String>) new Comparator<String>() {
			@Override
				public int compare(String name1, String name2) {
				
					String[] location1 = name1.replaceAll("\\s+", "").split("-");
					String[] location2 = name2.replaceAll("\\s+","").split("-");
					
					for(int i = 0; i < location1.length; i++) {
						if(i != 1) { //aisle and shelf
							if(Integer.parseInt(location1[i]) > Integer.parseInt(location2[i])) {
								return 1;
							}else if(location1[i].equals(location2[i])) {
								continue;
							}else {
								return -1;
							}
						}else{ //bay
							if(location1[i].charAt(0) < location2[i].charAt(0)){
								return -1;
							}else if (location1[i].equals(location2[i])) {
								continue;
							}else {
								return 1;
							}
						}
					}

					return 0;
				}
			}
		);
		
		table_2.setRowSorter(sortCols);
		
		InventoryPanel holder = this;
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String filter = searchField.getText().trim();
					int testInt = Integer.parseInt(filter);
					sortCols.setRowFilter(RowFilter.regexFilter(filter, 0));
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(holder, "Please input the Inventory ID only", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table_2);
		scrollPane.setBorder(new EmptyBorder(10, 0, 10, 0));
		add(scrollPane, BorderLayout.WEST);
	}
	
	public void addRow(Object[] rowInfo) {
			model.addRow(rowInfo);
	}

	
	
}
