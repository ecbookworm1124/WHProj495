package manager;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Incoming extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private SpringLayout springLayout;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private HashMap<Integer, Order> orders;
	private DefaultTableModel model;
	private JTable table_2;
	
	private JDatePickerImpl datePicker;
	private JComboBox<Object> comboBox;
	private JTable table;
	private DB server;
	private UI myGUI;
	
	private LinkedHashMap<Integer , Item> inventory;
	
	public Incoming(DB server, LinkedHashMap<Integer, Item> inventory, UI myGUI) {
		
		this.server = server;
		this.setSize(500, 400);
		
		this.inventory = inventory;
		this.myGUI = myGUI;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(new JLabel("New Incoming Shipment"));
		
		orders = new HashMap<Integer, Order>();
		
		JPanel shipmentInfo = new JPanel();
		TitledBorder shipmentBorder = new TitledBorder("Shipment Information");
		shipmentInfo.setBorder(shipmentBorder);
		panel.add(shipmentInfo);
		shipmentInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Order Number");
		shipmentInfo.add(lblNewLabel, "2, 2");
		
		textField = new JTextField();
		shipmentInfo.add(textField, "6, 2, left, default");
		textField.setColumns(20);
		
		JLabel lblTruckNumber_1 = new JLabel("Truck Number");
		shipmentInfo.add(lblTruckNumber_1, "2, 4");
		
		textField_1 = new JTextField();
		shipmentInfo.add(textField_1, "6, 4, left, default");
		textField_1.setColumns(20);
		
		
		/////// Setting up date Box //////////////////
		
		class DatePicker extends AbstractFormatter {
			
			private String datePattern = "yyyy-MM-dd";
		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

			@Override
			public Object stringToValue(String arg0) throws ParseException {
				return dateFormatter.parseObject(arg0);
			}

			@Override
			public String valueToString(Object arg0) throws ParseException {
				if(arg0 == null) {
					return "";
				}
				
				Calendar cal = (Calendar) arg0;
				Date date = new Date(cal.getTimeInMillis());
				return dateFormatter.format(date);
			}}
		
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		JLabel lblNewLabel_2 = new JLabel("Vendor ID");
		shipmentInfo.add(lblNewLabel_2, "2, 6");
		
		textField_3 = new JTextField();
		shipmentInfo.add(textField_3, "6, 6, left, default");
		textField_3.setColumns(20);
		datePicker = new JDatePickerImpl(datePanel, new DatePicker());
		 
		shipmentInfo.add(datePicker, "6, 8, left, default");
		
		JLabel lblDate = new JLabel("Date");
		shipmentInfo.add(lblDate, "2, 8");
		
		JPanel itemsList = new JPanel();
		TitledBorder itemListBorder = new TitledBorder("Item List");
		itemsList.setBorder(itemListBorder);
		panel.add(itemsList);
		itemsList.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("min:grow"),
				ColumnSpec.decode("min:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblPartNumber = new JLabel("Part Number");
		itemsList.add(lblPartNumber, "2, 2, center, default");
		
		JLabel lblNewLabel_1 = new JLabel("QTY");
		itemsList.add(lblNewLabel_1, "4, 2, center, default");
		
		
		Object [] holder = inventory.keySet().toArray();
		int size = holder.length;
		Object [] keys = new Object[size];
		for(int i = 0; i < size-1; i++) {
			keys[i] = holder[i];
		}
		keys[size-1] = "Add New";
		
		comboBox = new JComboBox<Object>(keys);
		itemsList.add(comboBox, "2, 4, fill, default");
		
		class MyItemListener implements ItemListener{
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getItem().getClass().getName().contains("String")) {
					myGUI.addNewItem();
				}
			}};
		
		comboBox.addItemListener(new MyItemListener());
		
		JLabel label = new JLabel("             ");
		itemsList.add(label, "3, 4, right, default");
		
		textField_2 = new JTextField();
		itemsList.add(textField_2, "4, 4");
		textField_2.setColumns(20);
		
		JButton btnAddShipment = new JButton("Add to Shipment");
		btnAddShipment.addActionListener( e-> addToOrder());
		itemsList.add(btnAddShipment, "5, 4, center, default");
		
		JScrollPane scrollPane = new JScrollPane();
		itemsList.add(scrollPane, "2, 6, 4, 1, fill, fill");
		
		buildTable();
		scrollPane.setViewportView(table_2);
		
		JButton btnCreateOrder = new JButton("Create Shipment");
		btnCreateOrder.addActionListener(e->{
			try {
				server.createShipment(orders);
				this.setVisible(false);
				this.dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "Could Not Add Shipment", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		itemsList.add(btnCreateOrder, "4, 8, center, default");
		
		Incoming me = this;
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				me.setVisible(false);
				me.dispose();
			}});
		itemsList.add(btnCancel, "5, 8, center, default");
		
		
		
		getContentPane().add(panel);
		
	}
	
	private void buildTable() {
		
		String [] columns = {"Item ID", "Quantity Ordered"};
		Object rows [][] = new Object[orders.size()][columns.length];

		int counter = 0;
		for(Map.Entry<Integer, Order> order: orders.entrySet()) {
			Order item = order.getValue();
			Object [] newRow = {order.getKey(), item.getInfo()[3]};
			rows[counter] = newRow;
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
	}
	
	private void addToOrder(){
		
		int orderID = Integer.parseInt(textField.getText().trim());
		int qty = Integer.parseInt(textField_2.getText().trim());
		int vendID = Integer.parseInt(textField_3.getText().trim());
		int truckNum = Integer.parseInt(textField_1.getText().trim());
		
		java.util.Date d1 = (java.util.Date) datePicker.getModel().getValue();
		Date date = new Date(d1.getTime());
		
		Order newOrder = new Order(orderID, qty, vendID, truckNum, date);
		
		int itemID = (Integer)comboBox.getSelectedItem();
		
		orders.put(itemID, newOrder);

		Object [] newRow = {itemID, qty};
		model.addRow(newRow);
	}
	
	private void createShipment() {
		//Send the new shipment info to the DB

	}
}
