import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginPanel{
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel;
	private Connection con;
	private String username;
	private String password;
	private JLabel label;


	/**
	 * Create the panel.
	 */
	public LoginPanel(JPanel panel, Connection con) {
		this.panel = panel;
		this.con = con;
		panel.setBackground(new Color(152, 251, 152));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Rockwell", Font.BOLD, 20));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 6;
		gbc_lblLogin.gridy = 0;
		panel.add(lblLogin, gbc_lblLogin);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JButton createAccountButton = new JButton("Create Account");
		GridBagConstraints gbc_createAccountButton = new GridBagConstraints();
		gbc_createAccountButton.insets = new Insets(0, 0, 0, 5);
		gbc_createAccountButton.gridx = 6;
		gbc_createAccountButton.gridy = 9;
		panel.add(createAccountButton, gbc_createAccountButton);

		JButton btnNewButton = new JButton("Login");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 9;
		panel.add(btnNewButton, gbc_btnNewButton);

		label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 6;
		gbc_label.gridy = 10;
		panel.add(label, gbc_label);

		createAccountButton.addActionListener(new AddCreatePerformer());
		btnNewButton.addActionListener(new AddLoginPerformer());
		
		panel.revalidate();
	}


	private class AddLoginPerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			username = textField.getText();
			password = textField_1.getText();

			Statement stmt = null;
			Statement stmt2 = null;
			try {
				stmt = con.createStatement();
				stmt2 = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}

			String sql = "SELECT Username, Password, UserType FROM User";
			String sql2 = "SELECT P_Username FROM Patient";
			String sql3 = "SELECT D_Username FROM Doctor";
			try {
				//rs = stmt.executeQuery(begin);
				ResultSet rs = stmt.executeQuery(sql);
				ResultSet rs2;
				while(rs.next()) {
					
					String user = rs.getString("Username");
					String pass = rs.getString("Password");
					String type = rs.getString("UserType");
					if (user.equals(username) && pass.equals(password) && type.equals("Patient")) {	  // make if user exists, it goes straight to home
						rs2 = stmt2.executeQuery(sql2);
						boolean goToMenu = false;
						while (rs2.next()){
							if (rs2.getString("P_Username").equals(username)) {
								goToMenu = true;
								break;}
						}
						if (goToMenu){
							panel.removeAll();
							new PatientMenuPanel(panel,con,username);
							panel.validate();
							panel.repaint();}
						else {
							panel.removeAll();
							new PatientProfilePanel(panel,con,username);
							panel.validate();
							panel.repaint();
						}
					} else if (user.equals(username) && pass.equals(password) && type.equals("Doctor")) {
						rs2 = stmt2.executeQuery(sql3);
						boolean goToMenu = false;
						while (rs2.next()){
							if (rs2.getString("D_Username").equals(username)) {
								goToMenu = true;
								break;}
						}
						if (goToMenu){
							panel.removeAll();
							new DoctorMenuPanel(panel,con,username);
							panel.validate();
							panel.repaint();}
						else {
							panel.removeAll();
							new DoctorProfilePanel(panel,con,username);
							panel.validate();
							panel.repaint();
						}
					}
					else if (user.equals(username) && pass.equals(password) && type.equals("Administrative Personnel")){
						panel.removeAll();
						new AdminMenuPanel(panel,con);
						panel.validate();
						panel.repaint();
					}
					else label.setText("Incorrect username or password.");
				}
			} catch (SQLException m) {

				m.printStackTrace();
			} 



			try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
	}

	private class AddCreatePerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new CreateAccountPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}

}
