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


public class SendMessageToDoctors{
	private JTextField textField;
	private JTextField txtDdddddddddddddddddddd;
	private JPanel panel;
	Connection con;
	private String d_username;
	ArrayList<String> arrayList2 = new ArrayList<String>();
	ArrayList<String> arrayList4 = new ArrayList<String>();
	JComboBox comboBox;
	JComboBox comboBox_1;
	/**
	 * Create the panel.
	 */
	public SendMessageToDoctors(final JPanel panel, final Connection con, final String d_username) 
	{

		this.panel = panel;
		this.con = con;
		this.d_username = d_username;
		
		Statement stmt = null;
		Statement stmt2 = null;
		try {
			stmt = con.createStatement();
			stmt2 = con.createStatement();
		} catch (SQLException k) {
			// TODO Auto-generated catch block
			k.printStackTrace();
		}

		
		String sql = "SELECT LicenseNumber, LastName, FirstName FROM Doctor";
		String sql2 = "SELECT P_Username, Name FROM Patient";
		//System.out.println(sql);
		try {
			//rs = stmt.executeQuery(begin);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSet rs2 = stmt2.executeQuery(sql2);
			ArrayList<String> arrayList = new ArrayList<String>();
			ArrayList<String> arrayList3 = new ArrayList<String>();
			while (rs.next()) {
				arrayList.add(rs.getString("FirstName") + rs.getString("LastName"));
				arrayList2.add(rs.getString("LicenseNumber"));
			}
			while(rs2.next()) {
				arrayList3.add(rs2.getString("Name"));
				arrayList4.add(rs2.getString("P_Username"));
			}
		
		panel.setBackground(new Color(255, 153, 204));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Send Message to Docs/Patients");
		lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 10;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Doctor");
		lblNewLabel_1.setFont(new Font("Adobe Gurmukhi", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBox = new JComboBox();
		String[] a = new String[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			a[i] = arrayList.get(i);
		}
		comboBox.setModel(new DefaultComboBoxModel(a));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 7;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Select Patient");
		lblNewLabel_3.setFont(new Font("Bodoni MT Black", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 9;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		comboBox_1 = new JComboBox();
		String[] b = new String[arrayList3.size()];
		for (int i = 0; i < arrayList3.size(); i++) {
			b[i] = arrayList3.get(i);
		}
		comboBox_1.setModel(new DefaultComboBoxModel(b));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 5;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 10;
		gbc_comboBox_1.gridy = 3;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblNewLabel_2 = new JLabel("Message");
		lblNewLabel_2.setFont(new Font("Ebrima", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 4;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Adobe Gurmukhi", Font.BOLD, 17));
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.anchor = GridBagConstraints.EAST;
		gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblMessage.gridx = 9;
		gbc_lblMessage.gridy = 4;
		panel.add(lblMessage, gbc_lblMessage);
		
		txtDdddddddddddddddddddd = new JTextField();
		GridBagConstraints gbc_txtDdddddddddddddddddddd = new GridBagConstraints();
		gbc_txtDdddddddddddddddddddd.gridwidth = 5;
		gbc_txtDdddddddddddddddddddd.insets = new Insets(0, 0, 5, 0);
		gbc_txtDdddddddddddddddddddd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDdddddddddddddddddddd.gridx = 10;
		gbc_txtDdddddddddddddddddddd.gridy = 4;
		panel.add(txtDdddddddddddddddddddd, gbc_txtDdddddddddddddddddddd);
		txtDdddddddddddddddddddd.setColumns(10);
		
		JButton backButton = new JButton("Return To Home Page");
		GridBagConstraints gbc_b = new GridBagConstraints();
		gbc_b.insets = new Insets(0, 0, 0, 5);
		gbc_b.gridx = 7;
		gbc_b.gridy = 14;
		panel.add(backButton, gbc_b);
		backButton.addActionListener(new ActionListener() {


		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new DoctorMenuPanel(panel,con,d_username);
			panel.validate();
			panel.repaint();
		}
	});
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Bodoni MT", Font.BOLD, 14));
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 14;
		panel.add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(new SendMessagePerformer());
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
			int j = comboBox_1.getSelectedIndex();
			String R_licenseNumber = arrayList2.get(i);
			String P_Username = arrayList4.get(j);
			String D_status = "Unread";
			String P_status = "Unread";
			String D_content = textField.getText();
			String P_content = txtDdddddddddddddddddddd.getText();
			Statement stmt = null;
			Statement stmt2 = null;
			Statement stmt3 = null;
			String sql2 = "SELECT LicenseNumber FROM Doctor WHERE D_Username = '" +  d_username + "'";
			System.out.println("Creating statement...");
			try {
				stmt = con.createStatement();
				stmt2 = con.createStatement();
				stmt3 = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}
			
			

			try {
				ResultSet rs = stmt3.executeQuery(sql2);
				rs.next();
				String d_licenseNumber = rs.getString("LicenseNumber");
				Calendar calendar = Calendar.getInstance();
				Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
				
				String sql = "INSERT INTO CommunicateWith(Sender_LicenseNumber, Receiver_LicenseNumber, Content, Status, DateTime) VALUES ('" + 
						d_licenseNumber+ "','"+ R_licenseNumber+ "','" + D_content+ "','"+D_status+"','" +currentTimestamp+"')";
				String sql3 = "INSERT INTO Sends_message_to_Pat(D_LicenseNumber, P_Username, Content, Status, DateTime) VALUES ('" + 
						d_licenseNumber+ "','"+ P_Username+ "','" + P_content+ "','"+P_status+"','" +currentTimestamp+"')";
				stmt.execute(sql);
				stmt2.execute(sql3);
			} catch (SQLException m) {

				m.printStackTrace();
			} 


			//rs.close();
			try {
				stmt.close();
				stmt2.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			panel.removeAll();
			new DoctorMenuPanel(panel,con,d_username);
			panel.validate();
			panel.repaint();

	}
	}

}
