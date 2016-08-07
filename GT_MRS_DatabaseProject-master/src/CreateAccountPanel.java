import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccountPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel;
	private JComboBox comboBox;
	private Connection con;
	private String username;
	private String password;
	private String type;
	private JButton backButton;

	/**
	 * Create the panel.
	 */
	public CreateAccountPanel(JPanel panel, Connection con) {
		this.con = con;
		this.panel = panel;
		panel.setBackground(new Color(173, 216, 230));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{124, 201, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("New User Account");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 23));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 3;
		panel.add(lblUsername, gbc_lblUsername);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 4;
		panel.add(lblPassword, gbc_lblPassword);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		/*
		JLabel lblNewLabel_1 = new JLabel("Confirm Password");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 5;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 5;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		 */
		JLabel lblNewLabel_2 = new JLabel("Type of User");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 6;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Patient", "Administrative Personnel"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;
		panel.add(comboBox, gbc_comboBox);

		JButton btnNewButton = new JButton("Register");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 9;
		panel.add(btnNewButton, gbc_btnNewButton);

		btnNewButton.addActionListener(new AddPatientProfilePerformer());

		backButton = new JButton("Return To Login Page");
		GridBagConstraints gbc_b = new GridBagConstraints();
		gbc_b.gridx = 0;
		gbc_b.gridy = 9;
		panel.add(backButton, gbc_b);
		backButton.addActionListener(new AddBack());

	}

	private class AddBack implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new LoginPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}

	private class AddPatientProfilePerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			username = textField.getText();
			password = textField_1.getText();
			type = (String)comboBox.getSelectedItem();
			Statement stmt = null;


			try {
				stmt = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}

			String sql = null;

			if (type.equals("Patient")) {

				sql = "INSERT INTO User(Username, Password, UserType) VALUES ('"+username+ "','" + password+ "', '"+type+ "')";

				//System.out.println(sql);
				try {
					stmt.execute(sql);

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				//rs.close();
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (type.equals("Doctor")) {

				String sql2 = "INSERT INTO MedicalStaff(M_Username) VALUES ('"+username+"')";
				sql = "INSERT INTO User(Username, Password, UserType) VALUES ('"+username+ "','" + password+ "', '"+type+ "')";

				//System.out.println(sql);
				try {
					con.setAutoCommit(false);
					stmt.addBatch(sql);
					stmt.addBatch(sql2);
					stmt.executeBatch();
					con.commit();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				//rs.close();
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}

			else {
				sql = "INSERT INTO User(Username, Password, UserType) VALUES ('"+username+ "','" + password+ "', '"+type+ "')";
				String sql2 = "INSERT INTO MedicalStaff(M_Username) VALUES ('"+username+ "')";
				String sql3 = "INSERT INTO AdministrativePersonnel(AP_Username) VALUES ('"+username+ "')";
				//System.out.println(sql);
				try {
					stmt.execute(sql);
					stmt.execute(sql2);
					stmt.execute(sql3);

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				//rs.close();
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			panel.removeAll();
			new LoginPanel(panel,con);
			panel.validate();
			panel.repaint();
		}

	}
}
