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


public class PatientProfilePanel{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JPanel panel;

	private Connection con;
	private String p_username;
	JComboBox comboBox_1;
	JComboBox comboBox;
	private JTextField textField_11;

	/**
	 * Create the panel.
	 */
	public PatientProfilePanel(final JPanel panel, final Connection con, String p_username) {
		this.p_username = p_username;
		this.panel = panel;
		this.con = con;
		panel.setBackground(new Color(240, 230, 140));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblPatientProfile = new JLabel("Patient Profile");
		lblPatientProfile.setFont(new Font("Consolas", Font.BOLD, 25));
		GridBagConstraints gbc_lblPatientProfile = new GridBagConstraints();
		gbc_lblPatientProfile.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientProfile.gridx = 4;
		gbc_lblPatientProfile.gridy = 1;
		panel.add(lblPatientProfile, gbc_lblPatientProfile);

		JLabel lblPatientname = new JLabel("PatientName");
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

		JLabel lblNewLabel = new JLabel("DateOfBirth");
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

		JLabel lblNewLabel_1 = new JLabel("Gender");
		lblNewLabel_1.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 5;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("AR JULIAN", Font.PLAIN, 12));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 4;
		gbc_comboBox_1.gridy = 5;
		panel.add(comboBox_1, gbc_comboBox_1);

		JLabel lblNewLabel_2 = new JLabel("Address");
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

		JLabel lblNewLabel_3 = new JLabel("HomePhone");
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

		JLabel lblNewLabel_4 = new JLabel("WorkPhone");
		lblNewLabel_4.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 8;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 4;
		gbc_textField_5.gridy = 8;
		panel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Weight");
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

		JLabel lblNewLabel_6 = new JLabel("Height");
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

		JLabel lblNewLabel_7 = new JLabel("Annual Income $");
		lblNewLabel_7.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 11;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Aharoni", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"below 25000$", "above 25000$"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 11;
		panel.add(comboBox, gbc_comboBox);

		JLabel lblNewLabel_8 = new JLabel("List of Allergies");
		lblNewLabel_8.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 12;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);

		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 4;
		gbc_textField_8.gridy = 12;
		panel.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Emergency Name");
		lblNewLabel_9.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 13;
		panel.add(lblNewLabel_9, gbc_lblNewLabel_9);

		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 4;
		gbc_textField_9.gridy = 13;
		panel.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("EmergencyPhone");
		lblNewLabel_10.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 14;
		panel.add(lblNewLabel_10, gbc_lblNewLabel_10);

		textField_10 = new JTextField();
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 4;
		gbc_textField_10.gridy = 14;
		panel.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("cardNumber");
		lblNewLabel_11.setFont(new Font("Aharoni", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 2;
		gbc_lblNewLabel_11.gridy = 15;
		panel.add(lblNewLabel_11, gbc_lblNewLabel_11);

		textField_11 = new JTextField();
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 4;
		gbc_textField_11.gridy = 15;
		panel.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Bell MT", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = 5;
		gbc_btnSubmit.gridy = 1;
		panel.add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(new AddPatientProfileSubmitPerformer());
		
		
		
	}
	private class AddPatientProfileSubmitPerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String Pn = textField.getText();
			String Dob = textField_1.getText();
			String Gender = (String)comboBox_1.getSelectedItem();
			String Address = textField_3.getText();
			String HomePhone = textField_4.getText();
			String WorkPhone = textField_5.getText();
			String Height = textField_6.getText();
			String Weight = textField_7.getText();
			String Ai = (String)comboBox.getSelectedItem();
			String Allergies = textField_8.getText();
			String contactN = textField_9.getText();
			String contactP = textField_10.getText();
			String cardNum = textField_11.getText();
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


			sql = "INSERT INTO Patient(P_Username, Name, HomePhone, AnnualIncome, DateofBirth, Gender, Address, WorkPhone, ContactName, ContactPhone, Weight, Height, CardNumber) VALUES ('" + 
					p_username+ "','"+ Pn+ "','" + HomePhone+ "','"+Ai+"','"+Dob+"','"+ Gender+"','"+Address+"','"+WorkPhone+"','"+ contactN +"','" +contactP+"','"+Weight+"','"+Height+"','"+cardNum+"')";
			sql2 = "INSERT INTO Allergies_new(P_Username, Allergies) VALUES ('"+ p_username+"','"+Allergies+"')" ; 
			sql3 = "UPDATE Patient SET Name= '"+ Pn +"', HomePhone = '"+HomePhone+"', AnnualIncome = '"+Ai+"', DateofBirth = '"+Dob+"', Gender = '"+Gender+
					"', Address = '"+Address+"', WorkPhone = '" +WorkPhone+"', ContactName = '"+ contactN+ "', ContactPhone = '" + contactP +"', Weight = '" + Weight +
					"', Height = '" + Height +"', CardNumber = '" + cardNum +"' WHERE P_Username='"+p_username+"'";
			sql4 = "UPDATE Allergies_new SET Allergies= '"+Allergies+"' WHERE P_Username='"+ p_username + "'";
			String sqlCheck = "SELECT P_Username FROM Patient WHERE P_Username = '"+ p_username +"'";

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
			new PatientMenuPanel(panel,con,p_username);
			panel.validate();
			panel.repaint();




		}
	}


}