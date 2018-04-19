package manager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Location extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Location(Item selected, DB server, UI parent, DetailFrame details) {
		
		JPanel panel = new JPanel();
		
		Object[] itemInfo = selected.getInfo();
		String locationInfo = (String)itemInfo[3];
		
		JFrame me = this;
		
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
		
		
		JLabel lblInventoryLocation = new JLabel("Location Transfer");
		panel.add(lblInventoryLocation, "2, 2");
		
		JLabel lblNewLabel = new JLabel("Aisle");
		panel.add(lblNewLabel, "6, 4");
		
		JLabel lblBay = new JLabel("Bay");
		panel.add(lblBay, "10, 4");
		
		JLabel lblShelf = new JLabel("Shelf");
		panel.add(lblShelf, "14, 4");
		
		JLabel lblCurrentLocation = new JLabel("Current Location:");
		panel.add(lblCurrentLocation, "2, 8");
		
		JLabel lblA = new JLabel(locationInfo.substring(0,2));
		panel.add(lblA, "6, 8");
		
		JLabel lblB = new JLabel(locationInfo.substring(2,3));
		panel.add(lblB, "10, 8");
		
		JLabel lblS = new JLabel(locationInfo.substring(3));
		panel.add(lblS, "14, 8");
		
		JLabel lblNewLocation = new JLabel("New Location:");
		panel.add(lblNewLocation, "2, 12");
		
		JComboBox<String> comboBox = new JComboBox<String>(aisle);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox, "6, 12, fill, default");
		
		JComboBox<String> comboBox_1 = new JComboBox<String>(bay);
		comboBox_1.setSelectedIndex(0);
		panel.add(comboBox_1, "10, 12, fill, default");
		
		JComboBox<String> comboBox_2 = new JComboBox<String>(aisle);
		comboBox_2.setSelectedIndex(0);
		panel.add(comboBox_2, "14, 12, fill, default");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selAisle = (String)comboBox.getSelectedItem();
				String selBay = (String)comboBox_1.getSelectedItem();
				String selShelf = (String)comboBox_2.getSelectedItem();
				
				String newLocation = selAisle + selBay + selShelf;
				
				///Code to update server
				
				try {
					server.updateRecords(selected, newLocation);
					parent.refresh();
					me.setVisible(false);
					me.dispose();
					details.setVisible(false);
					details.dispose();
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(me, "Operation Could Not Be Executed", "Error", JOptionPane.ERROR_MESSAGE);
					me.setVisible(false);
					me.dispose();
				}
				
				///Code to refresh program
				
				
				
			}});
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

}
