import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;


public class SendMessageToDoctor{
	private JTextField textField;
	private Connection con;
	private JPanel panel;
	private String p_username;
	JComboBox comboBox;
	ArrayList<String> arrayList2 = new ArrayList<String>();
	/**
	 * Create the panel.
	 */
	public SendMessageToDoctor(final JPanel panel, final Connection con, final String p_username) {
		this.panel = panel;
		this.con = con;
		this.p_username = p_username;
		
		
		JButton backButton = new JButton("Return To Home Page");
		GridBagConstraints gbc_b = new GridBagConstraints();
		gbc_b.gridx = 0;
		gbc_b.gridy = 9;
		panel.add(backButton, gbc_b);
		backButton.addActionListener(new ActionListener() {


		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new PatientMenuPanel(panel,con,p_username);
			panel.validate();
			panel.repaint();
		}
	});
		
		
		Statement stmt = null;

		System.out.println("Creating statement...");
		try {
			stmt = con.createStatement();
		} catch (SQLException k) {
			// TODO Auto-generated catch block
			k.printStackTrace();
		}

		
		String sql = "SELECT LicenseNumber, LastName, FirstName FROM Doctor";
		//String sql2 = "SELECT D_LicenseNumber FROM Doctor";
		//System.out.println(sql);
		try {
			//rs = stmt.executeQuery(begin);
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<String> arrayList = new ArrayList<String>();
			
			while (rs.next()) {
				arrayList.add(rs.getString("FirstName") + rs.getString("LastName"));
				arrayList2.add(rs.getString("LicenseNumber"));
			}
		
		
		panel.setBackground(new Color(153, 204, 153));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{34, 382, 0};
		gridBagLayout.rowHeights = new int[]{39, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Send Message To Doctor");
		lblNewLabel.setFont(new Font("Euphemia", Font.BOLD, 25));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Calibri Light", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBox = new JComboBox();
		String[] a = new String[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			a[i] = arrayList.get(i);
		}
		comboBox.setModel(new DefaultComboBoxModel(a));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Content");
		lblNewLabel_2.setFont(new Font("Book Antiqua", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send Message");
		btnNewButton.setFont(new Font("Adobe Gurmukhi", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new SendMessagePerformer());
		} catch (SQLException m) {

			m.printStackTrace();
		}
		//rs.close();
		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private class SendMessagePerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			int i = comboBox.getSelectedIndex();
			String licenseNumber = arrayList2.get(i);
			String status = "Unread";
			String content = textField.getText();
			Statement stmt = null;
			Statement stmt2 = null;
			try {
				stmt = con.createStatement();
				stmt2 = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}
			
			

			try {
				//ResultSet rs = stmt2.executeQuery(sql2);
				//rs.next();
				//String d_licenseNumber = rs.getString("LicenseNumber");
				Calendar calendar = Calendar.getInstance();
				Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
				String sql = "INSERT INTO Sends_message_to_Doc(D_LicenseNumber, P_Username, Content, Status, DateTime) VALUES ('" + 
						licenseNumber+ "','"+ p_username+ "','" + content+ "','"+status+"','" +currentTimestamp+"')";
				stmt.execute(sql);
			} catch (SQLException m) {

				m.printStackTrace();
			} 


			//rs.close();
			try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			panel.removeAll();
			new PatientMenuPanel(panel,con,p_username);
			panel.validate();
			panel.repaint();

	}
	}
}
