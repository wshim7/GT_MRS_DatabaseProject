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


public class recordsurgery2{
	JPanel panel;
	Connection con;
	String d_username;
	String d_licensenumber;
	String P_name;
	private JTextField txtD;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	String cpt;
	String med;
	String name;
	String surgerytype;
	/**
	 * Create the panel.
	 */
	public recordsurgery2(JPanel panel, Connection con, String d_username, String d_licensenumber, String P_name, String surgerytype) {
		this.panel=panel;this.con=con;this.d_username=d_username;this.d_licensenumber=d_licensenumber;this.P_name=P_name;this.surgerytype=surgerytype;
		
		String sql = "SELECT CPTcode FROM Surgery WHERE SurgeryType = '"+surgerytype+"'";

		try {
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			cpt = rs.getString("CPTcode");
			String sql2 = "SELECT PreoperativeMedication FROM PreoperativeMedications_new WHERE CPTcode = '"+cpt+"'";
			ResultSet rs2 = stmt.executeQuery(sql2);
			rs2.next();
			med = rs2.getString("PreoperativeMedication");
			String sql3 = "SELECT FirstName, LastName From Doctor where LicenseNumber ='" + d_licensenumber + "'";
			ResultSet rs3 = stmt.executeQuery(sql3);
			rs3.next();
			name = rs3.getString("FirstName") + rs3.getString("LastName");
			
		panel.setBackground(new Color(245, 245, 245));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		JLabel label = new JLabel("Patient");
		label.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 2;
		panel.add(label, gbc_label);

		txtD = new JTextField();
		txtD.setText(P_name);
		GridBagConstraints gbc_txtD = new GridBagConstraints();
		gbc_txtD.insets = new Insets(0, 0, 5, 5);
		gbc_txtD.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtD.gridx = 6;
		gbc_txtD.gridy = 2;
		panel.add(txtD, gbc_txtD);
		txtD.setColumns(10);

		JLabel lblSurgeon = new JLabel("surgeon");
		lblSurgeon.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSurgeon = new GridBagConstraints();
		gbc_lblSurgeon.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurgeon.gridx = 2;
		gbc_lblSurgeon.gridy = 3;
		panel.add(lblSurgeon, gbc_lblSurgeon);

		textField_1 = new JTextField();
		textField_1.setText(name);
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 3;
		panel.add(textField_1, gbc_textField_1);
		
		JLabel lblSurgeryType = new JLabel("surgery type");
		lblSurgeryType.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSurgeryType = new GridBagConstraints();
		gbc_lblSurgeryType.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurgeryType.gridx = 2;
		gbc_lblSurgeryType.gridy = 4;
		panel.add(lblSurgeryType, gbc_lblSurgeryType);

		textField_2 = new JTextField();
		textField_2.setText(surgerytype);
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 6;
		gbc_textField_2.gridy = 4;
		panel.add(textField_2, gbc_textField_2);

		JLabel lblCptCode = new JLabel("CPT code");
		lblCptCode.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCptCode = new GridBagConstraints();
		gbc_lblCptCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCptCode.gridx = 2;
		gbc_lblCptCode.gridy = 5;
		panel.add(lblCptCode, gbc_lblCptCode);

		textField_3 = new JTextField();
		textField_3.setText(cpt);
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 6;
		gbc_textField_3.gridy = 5;
		panel.add(textField_3, gbc_textField_3);

		JLabel lblOfAssistants = new JLabel("# of Assistants");
		lblOfAssistants.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblOfAssistants = new GridBagConstraints();
		gbc_lblOfAssistants.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfAssistants.gridx = 2;
		gbc_lblOfAssistants.gridy = 6;
		panel.add(lblOfAssistants, gbc_lblOfAssistants);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 6;
		gbc_textField_4.gridy = 6;
		panel.add(textField_4, gbc_textField_4);

		JLabel lblPreoperativeMedication = new JLabel("pre-operative medication");
		lblPreoperativeMedication.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPreoperativeMedication = new GridBagConstraints();
		gbc_lblPreoperativeMedication.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreoperativeMedication.gridx = 2;
		gbc_lblPreoperativeMedication.gridy = 7;
		panel.add(lblPreoperativeMedication, gbc_lblPreoperativeMedication);

		textField_5 = new JTextField();
		textField_5.setText(med);
		textField_5.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 6;
		gbc_textField_5.gridy = 7;
		panel.add(textField_5, gbc_textField_5);

		JLabel lblAnethetiaStarttime = new JLabel("Anethetia starttime");
		lblAnethetiaStarttime.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAnethetiaStarttime = new GridBagConstraints();
		gbc_lblAnethetiaStarttime.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnethetiaStarttime.gridx = 2;
		gbc_lblAnethetiaStarttime.gridy = 8;
		panel.add(lblAnethetiaStarttime, gbc_lblAnethetiaStarttime);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 6;
		gbc_textField_6.gridy = 8;
		panel.add(textField_6, gbc_textField_6);

		JLabel lblSurgeryStartTime = new JLabel("surgery start time");
		lblSurgeryStartTime.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSurgeryStartTime = new GridBagConstraints();
		gbc_lblSurgeryStartTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurgeryStartTime.gridx = 2;
		gbc_lblSurgeryStartTime.gridy = 9;
		panel.add(lblSurgeryStartTime, gbc_lblSurgeryStartTime);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 6;
		gbc_textField_7.gridy = 9;
		panel.add(textField_7, gbc_textField_7);

		JLabel lblSurgeryCompletionTime = new JLabel("surgery completion time\r\n");
		lblSurgeryCompletionTime.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSurgeryCompletionTime = new GridBagConstraints();
		gbc_lblSurgeryCompletionTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurgeryCompletionTime.gridx = 2;
		gbc_lblSurgeryCompletionTime.gridy = 10;
		panel.add(lblSurgeryCompletionTime, gbc_lblSurgeryCompletionTime);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 6;
		gbc_textField_8.gridy = 10;
		panel.add(textField_8, gbc_textField_8);

		JLabel lblComplication = new JLabel("Complication");
		lblComplication.setFont(new Font("Courier New", Font.PLAIN, 12));
		GridBagConstraints gbc_lblComplication = new GridBagConstraints();
		gbc_lblComplication.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplication.gridx = 2;
		gbc_lblComplication.gridy = 11;
		panel.add(lblComplication, gbc_lblComplication);

		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 6;
		gbc_textField_9.gridy = 11;
		panel.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);

		JButton btnRecord = new JButton("Record");
		btnRecord.setFont(new Font("AR BLANCA", Font.PLAIN, 12));
		GridBagConstraints gbc_btnRecord = new GridBagConstraints();
		gbc_btnRecord.insets = new Insets(0, 0, 0, 5);
		gbc_btnRecord.gridx = 6;
		gbc_btnRecord.gridy = 12;
		panel.add(btnRecord, gbc_btnRecord);
		btnRecord.addActionListener(new RecordButtonListener());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class RecordButtonListener implements ActionListener{  

		public void actionPerformed(ActionEvent e) {
			String ss = textField_7.getText();
			String sc = textField_8.getText();
			String as = textField_6.getText();
			String na = textField_4.getText();
			String co = textField_9.getText();
			String query = "INSERT INTO Performs (D_LicenseNumber, CPTcode, P_Username, NumberOfAssistants, AnesthesiaStartTime, surgerystarttime, surgeryendtime, Complications) VALUES ('"+d_licensenumber+"', '"+cpt+"','"+ P_name+"','" +na+"','" + as+"','" + ss+"','" +sc+"','" +co+"')";
			
			try {
				Statement stmt = con.createStatement();
				stmt.execute(query);
				
		
			} catch (SQLException l) {
				// TODO Auto-generated catch block
				l.printStackTrace();
			}
			
			panel.removeAll();
			new DoctorMenuPanel(panel, con, d_username);
			panel.validate();
			panel.repaint();
		}
	}	
}


