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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;


public class DoctorProfilePanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_2;
	private JTextField txtTo;
	private JTextField txtFrom;
	private String d_username;
	JComboBox comboBox;
	JComboBox comboBox_1;
	JPanel panel;
	Connection con;


	/**
	 * Create the panel.
	 */
	public DoctorProfilePanel(final JPanel panel, final Connection con, final String d_username) {
		this.panel = panel;
		this.con = con;
		this.d_username = d_username;
		panel.setBackground(new Color(255, 160, 122));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JButton backButton = new JButton("Return To Login Page");
		GridBagConstraints gbc_b = new GridBagConstraints();
		gbc_b.insets = new Insets(0, 0, 0, 5);
		gbc_b.gridx = 16;
		gbc_b.gridy = 14;
		panel.add(backButton, gbc_b);
		backButton.addActionListener(new ActionListener() {


		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new LoginPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	});

		JLabel lblPatientProfile = new JLabel("Doctor Profile");
		lblPatientProfile.setFont(new Font("Consolas", Font.BOLD, 25));
		GridBagConstraints gbc_lblPatientProfile = new GridBagConstraints();
		gbc_lblPatientProfile.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientProfile.gridx = 4;
		gbc_lblPatientProfile.gridy = 1;
		panel.add(lblPatientProfile, gbc_lblPatientProfile);

		JLabel lblPatientname = new JLabel("LicenseNumber");
		lblPatientname.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblPatientname = new GridBagConstraints();
		gbc_lblPatientname.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientname.gridx = 2;
		gbc_lblPatientname.gridy = 3;
		panel.add(lblPatientname, gbc_lblPatientname);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Firstname");
		lblNewLabel.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 4;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Lastname");
		lblNewLabel_1.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 5;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 5;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("DateofBirth");
		lblNewLabel_2.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 6;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 6;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("WorkPhone");
		lblNewLabel_3.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 7;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 4;
		gbc_textField_4.gridy = 7;
		panel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Speciality");
		lblNewLabel_4.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 8;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Aharoni", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"General Physician", "Heart Specialist", "Eye physician", "Orthopedics", "Psychiatry", "Gynecologist"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 8;
		panel.add(comboBox, gbc_comboBox);

		JLabel lblNewLabel_5 = new JLabel("RoomNumber");
		lblNewLabel_5.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 9;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 4;
		gbc_textField_6.gridy = 9;
		panel.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("HomeAddress");
		lblNewLabel_6.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 10;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 4;
		gbc_textField_7.gridy = 10;
		panel.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Availability");
		lblNewLabel_7.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 11;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Aharoni", Font.PLAIN, 12));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"24/04/2014", "25/04/2014", "26/04/2014", "27/04/2014", "28/04/2014", "29/04/2014", "30/04/2014","01/05/2014"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 4;
		gbc_comboBox_1.gridy = 11;
		panel.add(comboBox_1, gbc_comboBox_1);

		txtFrom = new JTextField();
		txtFrom.setText("From");
		txtFrom.setFont(new Font("Aharoni", Font.PLAIN, 12));
		GridBagConstraints gbc_txtFrom = new GridBagConstraints();
		gbc_txtFrom.insets = new Insets(0, 0, 5, 5);
		gbc_txtFrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFrom.gridx = 5;
		gbc_txtFrom.gridy = 11;
		panel.add(txtFrom, gbc_txtFrom);
		txtFrom.setColumns(10);

		txtTo = new JTextField();
		txtTo.setFont(new Font("Aharoni", Font.PLAIN, 12));
		txtTo.setText("To");
		GridBagConstraints gbc_txtTo = new GridBagConstraints();
		gbc_txtTo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTo.gridx = 6;
		gbc_txtTo.gridy = 11;
		panel.add(txtTo, gbc_txtTo);
		txtTo.setColumns(10);

		JButton btnNewButton = new JButton("+");
		btnNewButton.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 11;
		panel.add(btnNewButton, gbc_btnNewButton);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Aharoni", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = 5;
		gbc_btnSubmit.gridy = 13;
		panel.add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(new AddDoctorProfileSubmitPerformer());

	}

	private class AddDoctorProfileSubmitPerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String LicenseNumber = textField.getText();
			String FirstName = textField_1.getText();
			String LastName = textField_2.getText();
			String DoB = textField_3.getText();
			String WorkPhone = textField_4.getText();
			String Specialty = (String)comboBox.getSelectedItem();
			String RoomNumber = textField_6.getText();
			String HomeAdd = textField_7.getText();
			String Day = (String)comboBox_1.getSelectedItem();
			String From = txtFrom.getText();
			String To = txtTo.getText();
			Statement stmt = null;
			Statement stmt2 = null;

			try {
				stmt = con.createStatement();
				stmt2 = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}

			String sql = null;
			String sql2 = null;
			String sql3 = null;
			String sql4 = null;


			sql = "INSERT INTO Doctor(D_Username, LicenseNumber, FirstName, LastName, DoB, WorkPhone, Specialty, RoomNumber, HomeAdd) VALUES ('" + 
					d_username+ "','"+ LicenseNumber+ "','" + FirstName+ "','"+LastName+"','"+DoB+"','"+ WorkPhone+"','"+Specialty+"','"+ RoomNumber +"','" +HomeAdd+"')";
			sql2 = "INSERT INTO Availability_new(D_LicenseNumber, Day, FromTime, ToTime) VALUES ('"+ LicenseNumber+"','"+Day+"','"+ From +"','" +To+"')" ; 
			sql3 = "UPDATE Doctor SET LicenseNumber= '"+LicenseNumber+"', FirstName = '"+FirstName+"', Lastname = '"+LastName+"', DoB = '"+DoB+"', FirstName = '"+FirstName+
					"', WorkPhone = '"+WorkPhone+"', Specialty = '" +Specialty+"', RoomNumber = '"+ RoomNumber+ "', HomeAdd = '" + HomeAdd +"' WHERE D_Username='"+d_username+
					"'";
			sql4 = "UPDATE Availability_new SET Day= '"+Day+"', FromTime = '"+From+"', ToTime = '"+To+"' WHERE D_LicenseNumber='"+ LicenseNumber + "'";
			String sqlCheck = "SELECT D_Username FROM Doctor WHERE D_Username = '"+ d_username +"'";

			try {

				ResultSet rsCheck = stmt2.executeQuery(sqlCheck);

				if (!rsCheck.next()){

					con.setAutoCommit(false);
					stmt.addBatch(sql);
					stmt.addBatch(sql2);
					stmt.executeBatch();
					con.commit();}
				else {
					con.setAutoCommit(false);
					stmt.addBatch(sql3);
					stmt.addBatch(sql4);
					stmt.executeBatch();
					con.commit();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}


			//rs.close();
			try {
				stmt.close();
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
