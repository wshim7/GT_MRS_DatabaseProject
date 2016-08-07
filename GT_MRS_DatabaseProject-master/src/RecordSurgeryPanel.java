import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class RecordSurgeryPanel{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	JComboBox comboBox;
	JComboBox comboBox_1;
	JPanel panel;
	Connection con;
	String d_username;
	String d_licensenumber;
	/**
	 * Create the panel.
	 */
	public RecordSurgeryPanel(JPanel panel, Connection con, String d_username, String d_licensenumber) {
		this.panel=panel;this.con=con;this.d_username=d_username;this.d_licensenumber=d_licensenumber;
		String sql = "SELECT Name FROM Patient";

		ArrayList<String> arrayList = new ArrayList<String>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				arrayList.add(rs.getString("Name"));
			}
			panel.setBackground(new Color(245, 245, 245));
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gridBagLayout);
			
			JLabel lblNewLabel = new JLabel("SurgeryRecord");
			lblNewLabel.setFont(new Font("Bell MT", Font.BOLD, 16));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 6;
			gbc_lblNewLabel.gridy = 0;
			panel.add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblPatient = new JLabel("Patient search");
			lblPatient.setFont(new Font("Courier New", Font.PLAIN, 12));
			GridBagConstraints gbc_lblPatient = new GridBagConstraints();
			gbc_lblPatient.insets = new Insets(0, 0, 5, 5);
			gbc_lblPatient.gridx = 2;
			gbc_lblPatient.gridy = 1;
			panel.add(lblPatient, gbc_lblPatient);
			
			comboBox = new JComboBox();
			String[] a = new String[arrayList.size()];
			for (int i = 0; i < arrayList.size(); i++) {
				a[i] = arrayList.get(i);
			}
			comboBox.setModel(new DefaultComboBoxModel(a));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = 6;
			gbc_comboBox.gridy = 1;
			panel.add(comboBox, gbc_comboBox);
			
			JButton btnNewButton = new JButton("submit");
			btnNewButton.setFont(new Font("Adobe Hebrew", Font.PLAIN, 12));
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 7;
			gbc_btnNewButton.gridy = 1;
			panel.add(btnNewButton, gbc_btnNewButton);
			btnNewButton.addActionListener(new SearchPatientPerformer());
			
			JLabel lblSurgeryType = new JLabel("surgery type");
			lblSurgeryType.setFont(new Font("Courier New", Font.PLAIN, 12));
			GridBagConstraints gbc_lblSurgeryType = new GridBagConstraints();
			gbc_lblSurgeryType.insets = new Insets(0, 0, 5, 5);
			gbc_lblSurgeryType.gridx = 2;
			gbc_lblSurgeryType.gridy = 4;
			panel.add(lblSurgeryType, gbc_lblSurgeryType);

			comboBox_1 = new JComboBox();
			
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Knee Surgery", "Elbow Surgery", "Laser Eye Surgery","Tonsil Removal","Open Heart Surgery","Brain Surgery","Ovarian Surgery"}));
			GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
			gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_1.gridx = 6;
			gbc_comboBox_1.gridy = 4;
			panel.add(comboBox_1, gbc_comboBox_1);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		private class SearchPatientPerformer implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				String p_name = (String)comboBox.getSelectedItem();
				String surgeryType = (String)comboBox_1.getSelectedItem();
				panel.removeAll();
				new recordsurgery2(panel,con,d_username,d_licensenumber,p_name,surgeryType);
				panel.validate();
				panel.repaint();

		}
	}
}
	

