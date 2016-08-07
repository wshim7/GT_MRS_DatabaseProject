import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;




public class ViewVisitHistoryPanel extends JPanel {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox_1;
	
	private JPanel panel;
	private static Connection con;
	private String username;
	ArrayList<String> licenses;
	ArrayList<JButton> dateButtons;
	/**
	 * Create the panel.
	 */
	public ViewVisitHistoryPanel(final JPanel panel, final Connection con, final String username) {
		this.panel = panel;
		this.con = con;
		this.username = username;
		licenses = new ArrayList<String>();
		dateButtons = new ArrayList<JButton>();
		setBackground(new Color(102, 205, 170));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel lblViewVisitHistory = new JLabel("View Visit History");
		lblViewVisitHistory.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblViewVisitHistory = new GridBagConstraints();
		gbc_lblViewVisitHistory.gridwidth = 7;
		gbc_lblViewVisitHistory.insets = new Insets(0, 0, 5, 5);
		gbc_lblViewVisitHistory.gridx = 4;
		gbc_lblViewVisitHistory.gridy = 1;
		panel.add(lblViewVisitHistory, gbc_lblViewVisitHistory);
		
		JLabel lblDatesOfVisits = new JLabel("Dates of Visits");
		lblDatesOfVisits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDatesOfVisits = new GridBagConstraints();
		gbc_lblDatesOfVisits.gridwidth = 2;
		gbc_lblDatesOfVisits.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatesOfVisits.gridx = 1;
		gbc_lblDatesOfVisits.gridy = 2;
		panel.add(lblDatesOfVisits, gbc_lblDatesOfVisits);
		

		JButton backButton = new JButton("Return To Home Page");
		GridBagConstraints gbc_b = new GridBagConstraints();
		gbc_b.gridx = 0;
		gbc_b.gridy = 9;
		panel.add(backButton, gbc_b);
		backButton.addActionListener(new ActionListener() {


		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new PatientMenuPanel(panel,con,username);
			panel.validate();
			panel.repaint();
		}
	});
		
		try {
			final Statement stmt = con.createStatement();
			String query = "SELECT DateOfVisit, D_LicenseNumber From Visit WHERE P_Username = '" + username +"'";
			ResultSet rs = stmt.executeQuery(query);
			
			int y = 3;
			while(rs.next()){
				JButton button = new JButton(rs.getString(1));
				GridBagConstraints buttonc = new GridBagConstraints();
				buttonc.gridx = 0;
				buttonc.gridy = y++;
				buttonc.gridwidth = 2;
				licenses.add(rs.getString(2));
				dateButtons.add(button);

				
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton caller = (JButton)e.getSource();
						String date = caller.getText();
						
						int callerIndex = dateButtons.indexOf(caller);
						String licenseNumber = licenses.get(callerIndex);
						String infoQuery = "SELECT FirstName, LastName, BillingAmount, Diastolic, Systolic FROM Doctor , Visit  WHERE LicenseNumber = '" + licenseNumber + "' AND DateOfVisit = '" + date + "' AND LicenseNumber = D_LicenseNumber AND P_Username = '" + username + "'";
						try {
							ResultSet info = stmt.executeQuery(infoQuery);
							info.next();
							textField_1.setText(info.getString(1) + " " + info.getString(2));
							textField_2.setText(info.getString(5));
							textField_3.setText(info.getString(4));
							
							String diagQuery = "SELECT Diagnosis FROM Diagnosis_new WHERE D_LicenseNumber = '" + licenseNumber +"' AND DateOfVisit = '" + date + "' AND P_Username = '" + username + "'";
							ResultSet diag = stmt.executeQuery(diagQuery);
							comboBox_1.removeAllItems();
							while(diag.next()) {
								comboBox_1.addItem(diag.getString(1));
							}
							
							String medQuery = "SELECT MedicineName, Dosage, Duration, Notes FROM Prescription WHERE D_LicenseNumber = '" + licenseNumber + "' AND DateOfVisit = '" + date + "' AND P_Username = '" + username + "'";
							ResultSet med = stmt.executeQuery(medQuery);
							
							DefaultTableModel model = new DefaultTableModel();
							model.addColumn("Medicine Name");
							model.addColumn("Dosage");
							model.addColumn("Duration");
							model.addColumn("Notes");
							
							while(med.next()){
								Vector<String> vector = new Vector<String>();
								vector.add(med.getString(1));
								vector.add(med.getString(2));
								vector.add(med.getString(3));
								vector.add(med.getString(4));
								
								model.addRow(vector);
							
							}
							
							JTable table = new JTable(model);
							GridBagConstraints panec = new GridBagConstraints();
							panec.gridwidth = 82;
							panec.gridx = 0;
							panec.gridy = 13;
							panec.weightx = 1;
							panec.weighty = 1;
							panec.fill = GridBagConstraints.BOTH;
							JScrollPane pane = new JScrollPane(table);
							panel.add(pane, panec);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
				panel.add(button,buttonc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblConsultingDoctor = new JLabel("Consulting doctor:");
		lblConsultingDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblConsultingDoctor = new GridBagConstraints();
		gbc_lblConsultingDoctor.anchor = GridBagConstraints.EAST;
		gbc_lblConsultingDoctor.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsultingDoctor.gridx = 4;
		gbc_lblConsultingDoctor.gridy = 4;
		panel.add(lblConsultingDoctor, gbc_lblConsultingDoctor);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 4;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBloodPressure = new JLabel("Blood Pressure:");
		lblBloodPressure.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblBloodPressure = new GridBagConstraints();
		gbc_lblBloodPressure.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressure.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressure.gridx = 4;
		gbc_lblBloodPressure.gridy = 5;
		panel.add(lblBloodPressure, gbc_lblBloodPressure);
		
		JLabel lblSystolic = new JLabel("Systolic:");
		lblSystolic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblSystolic = new GridBagConstraints();
		gbc_lblSystolic.insets = new Insets(0, 0, 5, 5);
		gbc_lblSystolic.gridx = 5;
		gbc_lblSystolic.gridy = 5;
		panel.add(lblSystolic, gbc_lblSystolic);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 3;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 6;
		gbc_textField_2.gridy = 5;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDiastolic = new JLabel("Diastolic:");
		lblDiastolic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDiastolic = new GridBagConstraints();
		gbc_lblDiastolic.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiastolic.gridx = 9;
		gbc_lblDiastolic.gridy = 5;
		panel.add(lblDiastolic, gbc_lblDiastolic);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 10;
		gbc_textField_3.gridy = 5;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDiagnosis = new JLabel("Diagnosis:");
		lblDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDiagnosis = new GridBagConstraints();
		gbc_lblDiagnosis.anchor = GridBagConstraints.EAST;
		gbc_lblDiagnosis.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiagnosis.gridx = 4;
		gbc_lblDiagnosis.gridy = 6;
		panel.add(lblDiagnosis, gbc_lblDiagnosis);
		
		comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 8;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 5;
		gbc_comboBox_1.gridy = 6;
		panel.add(comboBox_1, gbc_comboBox_1);

	}

}
