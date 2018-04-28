package manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;

public class NewItem extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private DB server;
	
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private int size;
	private LinkedHashMap<Integer, Item> inventory;
	private UI myGUI;
	
	public NewItem(DB server, int size, LinkedHashMap<Integer, Item> inventory, UI myGUI) {
		JPanel panel = new JPanel();
		
		this.server = server;
		this.size = size;
		this.inventory = inventory;
		this.myGUI = myGUI;
		
		JFrame me = this;
		
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(37dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"),
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
		
		String [] bay = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String [] aisle = new String [100];
		
		for(int i = 0; i < aisle.length; i++) {
			if(i < 10) {
				aisle[i] = "0"+i;
			}else {
				aisle[i] = ""+i;
			}
		}
		
		
		JLabel title = new JLabel("New Item Information");
		panel.add(title, "2, 2");
		
		JLabel lblInventoryName = new JLabel("Inventory Name:");
		panel.add(lblInventoryName, "2, 4");
		
		textField = new JTextField();
		panel.add(textField, "4, 4, 5, 1, fill, default");
		textField.setColumns(10);
		
		JLabel lblItemUpc = new JLabel("Item UPC:");
		panel.add(lblItemUpc, "2, 6, left, default");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "4, 6, 5, 1, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblQtyAvailable = new JLabel("QTY Available:");
		panel.add(lblQtyAvailable, "2, 8, left, default");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "4, 8, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Aisle");
		panel.add(lblNewLabel, "4, 10, center, default");
		
		JLabel lblBay = new JLabel("Bay");
		panel.add(lblBay, "8, 10, center, default");
		
		JLabel lblShelf = new JLabel("Shelf");
		panel.add(lblShelf, "12, 10, center, default");
		
		
		JLabel lblLocation = new JLabel("Location:");
		panel.add(lblLocation, "2, 12, center, default");
		
		comboBox = new JComboBox<String>(aisle);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox, "4, 12, fill, default");
		
		comboBox_1 = new JComboBox<String>(bay);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox_1, "8, 12, fill, default");
		
		comboBox_2 = new JComboBox<String>(aisle);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox_2, "12, 12, fill, default");
		
		JButton btnSubmit = new JButton("Save");
		btnSubmit.addActionListener(e->saveItem());
		panel.add(btnSubmit, "4, 16");
		
		JLabel label = new JLabel("                   ");
		panel.add(label, "8, 16");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				me.setVisible(false);
				me.dispose();
			}});
		panel.add(btnCancel, "12, 16");
		
	}
	
	
	private void saveItem() {
		
		String aisle = comboBox.getSelectedItem().toString();
		String bay = comboBox_1.getSelectedItem().toString();
		String shelf = comboBox_2.getSelectedItem().toString();
		String location = aisle + bay + shelf;
		
		String name = textField.getText().trim();
		long upc = (long) Integer.parseInt(textField_1.getText().trim());
		int qty = Integer.parseInt(textField_2.getText().trim());
		
		
		size = size + 1; // This will be the item ID
		
		Item myItem = new Item(size, name, upc, location, qty);
		
		try {
			server.addToDB(myItem);
			myGUI.refresh();
			this.setVisible(false);
			this.dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Could Not Add Item", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
}





