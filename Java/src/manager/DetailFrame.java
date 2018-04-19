package manager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DetailFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableModel model;
	private JTable historyTable;
	private Object [] itemInfo;
	
	public DetailFrame(HashMap<Integer, Item> inventory, Item selected, UI parent) {
		
		this.itemInfo = selected.getInfo();
		//// May change the above later, depending on how we implement history
		
		this.setSize(500, 400);
		
		DetailFrame me = this;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		getContentPane().add(panel);
		
		
		//detailsHolder.setPreferredSize(new Dimension(getContentPane().getHeight() - 100, getContentPane().getWidth() -100));
		
		JLabel lblItemDetials = new JLabel("Item Detials");
		panel.add(lblItemDetials);
		
		JPanel detailsHolder = new JPanel(new GridLayout(0,1));
		panel.add(detailsHolder);
		
		TitledBorder border = new TitledBorder("Item Information");
		
		JPanel infoPanel = new JPanel();
		detailsHolder.add(infoPanel);
		
		
		infoPanel.setBorder(border);
		infoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("158px"),
				ColumnSpec.decode("158px"),
				ColumnSpec.decode("158px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),}));
		
		JLabel lblInventoryId = new JLabel("Inventory ID:");
		infoPanel.add(lblInventoryId, "2, 2");
		
		JLabel lblIdNum_1 = new JLabel("" + (Integer)itemInfo[0]);
		infoPanel.add(lblIdNum_1, "3, 2");
		
		JLabel lblInventoryName = new JLabel("Inventory Name:");
		infoPanel.add(lblInventoryName, "2, 4");
		
		JLabel lblName = new JLabel((String)itemInfo[1]);
		infoPanel.add(lblName, "3, 4");
		
		JLabel lblUpc = new JLabel("UPC Code");
		infoPanel.add(lblUpc, "2, 6");
		
		JLabel lblUpc_1 = new JLabel("" + (long) itemInfo[2]);
		infoPanel.add(lblUpc_1, "3, 6");
		
		JLabel lblWarehouseLocation = new JLabel("Warehouse Location");
		infoPanel.add(lblWarehouseLocation, "2, 8");
		
		String [] location = {itemInfo[3].toString().substring(0, 2), itemInfo[3].toString().substring(2,3), itemInfo[3].toString().substring(3)};
		
		JLabel lblLocation = new JLabel(location[0] + " - " + location[1] + " - " + location[2]);
		infoPanel.add(lblLocation, "3, 8");
		
		JButton btnUpdateLocation = new JButton("Update Location");
		btnUpdateLocation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.changeItemLocation(selected, me);
				
			}});
		infoPanel.add(btnUpdateLocation, "4, 8");
		
		JLabel lblQuantityAvailable = new JLabel("Quantity Available");
		infoPanel.add(lblQuantityAvailable, "2, 10");
		
		JLabel lblQuantity = new JLabel("" + (Integer) itemInfo[4]);
		infoPanel.add(lblQuantity, "3, 10");
		
		
		JPanel historyPanel = new JPanel();
		detailsHolder.add(historyPanel);
		
		TitledBorder historyBorder = new TitledBorder("Inventory History");
		historyPanel.setBorder(historyBorder);
		
		//// Building Table ////////////////
		
		String [] columns = {"Order Number", "Order Date", "Truck Number", "QTY"};
		Object rows [][] = new Object[inventory.size()][columns.length];
		
		int counter = 0;
		for(Map.Entry<Integer, Item> product: inventory.entrySet()) {
			Item item = product.getValue();
			rows[counter] = item.getInfo();
			counter ++;
		}
		
		model = new DefaultTableModel(rows, columns) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
				}
		};
		
		historyTable.setRowHeight(40);
		
		JScrollPane scrollPane = new JScrollPane(historyTable);
		historyPanel.add(scrollPane);
		
		this.pack();
		
	}
	
}
