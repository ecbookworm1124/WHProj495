package manager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.SQLException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginFrame extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private DB server;
	private UI myGUI;

	public LoginFrame(UI myGUI, DB server) {
		
		this.server = server;
		this.myGUI = myGUI;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
		
		
		getContentPane().add(panel);
		
		JLabel lblHost = new JLabel("Host: ");
		panel.add(lblHost, "2, 3");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "6, 3, fill, default");
		textField_1.setColumns(25);
		
		JLabel lblUsername = new JLabel("Username: ");
		panel.add(lblUsername, "2, 5");
		
		textField = new JTextField();
		panel.add(textField, "6, 5, fill, default");
		textField.setColumns(25);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword, "2, 7");
		
		passwordField = new JPasswordField();
		panel.add(passwordField, "6, 7, fill, default");
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(e->connect());
		panel.add(btnConnect, "4, 11");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e->myGUI.close());
		panel.add(btnCancel, "6, 11, center, default");
	}
	
	private void connect() {
		String host = textField_1.getText();
		
		if(host.contains(":")) {
			host = "jdbc:oracle:thin:@" + host + ":XE";
		}else {
			host = "jdbc:oracle:thin:@"+ host +":1521:XE";
		}
		String username = textField.getText();
		String password = new String(passwordField.getPassword());//.toString();
		
		try {
			server.setup(host, username, password);
			myGUI.connect();
			this.setVisible(false);
			this.dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Could Not Connect To Server", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
