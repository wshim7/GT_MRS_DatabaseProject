import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;


public class RecordVisitPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextArea textArea;
	private Connection con;
	private String licenseNumber,p_name;
	private JPanel panel;
	private ArrayList<String> prescriptions;

	/**
	 * Create the panel.
	 */
	public RecordVisitPanel(final JPanel panel, final Connection con,String p_name, final String d_username,final String licenseNumber) {
		this.con = con; this.licenseNumber = licenseNumber; this.p_name = p_name;
		prescriptions = new ArrayList<String>();
		panel.setBackground(Color.LIGHT_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 85, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);

		JLabel lblRecordAVisit = new JLabel("Record A Visit");
		lblRecordAVisit.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblRecordAVisit = new GridBagConstraints();
		gbc_lblRecordAVisit.insets = new Insets(0, 0, 5, 5);
		gbc_lblRecordAVisit.gridx = 1;
		gbc_lblRecordAVisit.gridy = 0;
		panel.add(lblRecordAVisit, gbc_lblRecordAVisit);


		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException k) {
			// TODO Auto-generated catch block
			k.printStackTrace();
		}

		String sql0 = "SELECT CURRENT_TIMESTAMP";
		ResultSet rs0 = null;
		try {
			rs0 = stmt.executeQuery(sql0);
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		Date dateOfVisit = null;
		try {
			while (rs0.next()) {
				dateOfVisit = rs0.getDate(1);}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		JLabel lblDateOfVisit = new JLabel("Date of Visit");
		GridBagConstraints gbc_lblDateOfVisit = new GridBagConstraints();
		gbc_lblDateOfVisit.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfVisit.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfVisit.gridx = 0;
		gbc_lblDateOfVisit.gridy = 1;
		panel.add(lblDateOfVisit, gbc_lblDateOfVisit);

		textField = new JTextField(String.format("%s",dateOfVisit));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblPatientName = new JLabel("Patient Name");
		GridBagConstraints gbc_lblPatientName = new GridBagConstraints();
		gbc_lblPatientName.anchor = GridBagConstraints.EAST;
		gbc_lblPatientName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientName.gridx = 0;
		gbc_lblPatientName.gridy = 2;
		panel.add(lblPatientName, gbc_lblPatientName);

		textField_1 = new JTextField(p_name);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblBloodPressure = new JLabel("Blood Pressure");
		GridBagConstraints gbc_lblBloodPressure = new GridBagConstraints();
		gbc_lblBloodPressure.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressure.gridx = 0;
		gbc_lblBloodPressure.gridy = 3;
		panel.add(lblBloodPressure, gbc_lblBloodPressure);

		JLabel lblSystolic = new JLabel("Systolic");
		GridBagConstraints gbc_lblSystolic = new GridBagConstraints();
		gbc_lblSystolic.insets = new Insets(0, 0, 5, 5);
		gbc_lblSystolic.anchor = GridBagConstraints.EAST;
		gbc_lblSystolic.gridx = 1;
		gbc_lblSystolic.gridy = 3;
		panel.add(lblSystolic, gbc_lblSystolic);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblDiastolic = new JLabel("Diastolic");
		GridBagConstraints gbc_lblDiastolic = new GridBagConstraints();
		gbc_lblDiastolic.anchor = GridBagConstraints.EAST;
		gbc_lblDiastolic.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiastolic.gridx = 3;
		gbc_lblDiastolic.gridy = 3;
		panel.add(lblDiastolic, gbc_lblDiastolic);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 3;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblDiagnosis = new JLabel("Diagnosis");
		GridBagConstraints gbc_lblDiagnosis = new GridBagConstraints();
		gbc_lblDiagnosis.anchor = GridBagConstraints.EAST;
		gbc_lblDiagnosis.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiagnosis.gridx = 0;
		gbc_lblDiagnosis.gridy = 4;
		panel.add(lblDiagnosis, gbc_lblDiagnosis);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		panel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblPrescribeMedication = new JLabel("Prescribe Medication");
		lblPrescribeMedication.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblPrescribeMedication = new GridBagConstraints();
		gbc_lblPrescribeMedication.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrescribeMedication.gridx = 1;
		gbc_lblPrescribeMedication.gridy = 5;
		panel.add(lblPrescribeMedication, gbc_lblPrescribeMedication);

		JLabel lblDrugName = new JLabel("Drug Name");
		GridBagConstraints gbc_lblDrugName = new GridBagConstraints();
		gbc_lblDrugName.anchor = GridBagConstraints.EAST;
		gbc_lblDrugName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDrugName.gridx = 0;
		gbc_lblDrugName.gridy = 6;
		panel.add(lblDrugName, gbc_lblDrugName);

		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 6;
		panel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);

		JLabel lblDosage = new JLabel("Dosage");
		GridBagConstraints gbc_lblDosage = new GridBagConstraints();
		gbc_lblDosage.anchor = GridBagConstraints.EAST;
		gbc_lblDosage.insets = new Insets(0, 0, 5, 5);
		gbc_lblDosage.gridx = 0;
		gbc_lblDosage.gridy = 7;
		panel.add(lblDosage, gbc_lblDosage);

		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 7;
		panel.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		JLabel lblPerDay = new JLabel("per day");
		GridBagConstraints gbc_lblPerDay = new GridBagConstraints();
		gbc_lblPerDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerDay.gridx = 2;
		gbc_lblPerDay.gridy = 7;
		panel.add(lblPerDay, gbc_lblPerDay);

		JLabel lblDuration = new JLabel("Duration");
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.anchor = GridBagConstraints.EAST;
		gbc_lblDuration.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuration.gridx = 0;
		gbc_lblDuration.gridy = 8;
		panel.add(lblDuration, gbc_lblDuration);

		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 8;
		panel.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

		JLabel lblMonths = new JLabel("months");
		GridBagConstraints gbc_lblMonths = new GridBagConstraints();
		gbc_lblMonths.anchor = GridBagConstraints.EAST;
		gbc_lblMonths.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonths.gridx = 2;
		gbc_lblMonths.gridy = 8;
		panel.add(lblMonths, gbc_lblMonths);

		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 3;
		gbc_textField_8.gridy = 8;
		panel.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);

		JLabel lblDays = new JLabel("days");
		GridBagConstraints gbc_lblDays = new GridBagConstraints();
		gbc_lblDays.insets = new Insets(0, 0, 5, 0);
		gbc_lblDays.gridx = 4;
		gbc_lblDays.gridy = 8;
		panel.add(lblDays, gbc_lblDays);

		JLabel lblNotes = new JLabel("Notes");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.insets = new Insets(0, 0, 0, 5);
		gbc_lblNotes.gridx = 0;
		gbc_lblNotes.gridy = 9;
		panel.add(lblNotes, gbc_lblNotes);

		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 9;
		panel.add(textArea, gbc_textArea);

		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 9;
		panel.add(btnAdd, gbc_btnAdd);
		btnAdd.addActionListener(new AddSubmitPerformer());

		JButton btnGoBack = new JButton("Go Back");
		GridBagConstraints gbc_btnGoBack = new GridBagConstraints();
		gbc_btnGoBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnGoBack.gridx = 3;
		gbc_btnGoBack.gridy = 9;
		panel.add(btnGoBack, gbc_btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				new DoctorMenuPanel(panel,con,d_username);
				panel.validate();
				panel.repaint();
			}
		}
				);

		JButton btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 4;
		gbc_btnSubmit.gridy = 9;
		panel.add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(new AddSubmitPerformer());

	}

	private class AddSubmitPerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String dateOfVisit = textField.getText();
			String patientName = p_name; 
			String bpS = textField_2.getText(); 
			String bpD = textField_3.getText(); 
			String diagnosis = textField_4.getText(); 
			String drugName = textField_5.getText(); 
			String dosage = textField_6.getText(); 
			String duration = textField_7.getText()+"/"+textField_8.getText(); 
			String notes = textArea.getText(); 
			String p_username = null;
			String billing = null;

			Statement stmt = null;
			Statement stmt2 = null;
			try {
				stmt = con.createStatement();
				stmt2 = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}




			
			String sql = "SELECT P_Username FROM Patient WHERE Name = '"+patientName+"'"; //get p_username



			ResultSet rs;
			try {
				rs = stmt.executeQuery(sql);
				rs.next();
				p_username = rs.getString("P_Username");
				String sql1 = "SELECT P_Username FROM Visit WHERE P_Username = '"+ p_username+"' AND D_LicenseNumber = '"+licenseNumber+"'"; //1st time?

				rs = stmt.executeQuery(sql1);
				if (rs.next())
					billing = "150";
				else billing = "200";

				if ((((JButton) e.getSource()).getActionCommand()).equals("Add")){
					String s3 = "INSERT INTO Prescription (D_LicenseNumber, P_Username, DateOfVisit, MedicineName, Dosage, Duration, Notes) VALUES ('"+licenseNumber+"', '"+p_username+"', '"+dateOfVisit+"', '"+drugName+"', '"+dosage+"', '"+duration+"', '"+notes+"')";
					prescriptions.add(s3);
					
				}
				else {

					String sql2 = "INSERT INTO Visit (D_LicenseNumber, P_Username, DateOfVisit, Systolic, Diastolic, BillingAmount) VALUES ('"+licenseNumber+"', '"+p_username+"', '"+dateOfVisit+"', '"+bpS+"','"+bpD+"', '"+billing+"')";
					String sql3 = "INSERT INTO Diagnosis_new (D_LicenseNumber, P_Username, DateOfVisit, Diagnosis) VALUES ('"+licenseNumber+"', '"+p_username+"', '"+dateOfVisit+"', '"+diagnosis+"')";
					String sql4 = "INSERT INTO Prescription (D_LicenseNumber, P_Username, DateOfVisit, MedicineName, Dosage, Duration, Notes) VALUES ('"+licenseNumber+"', '"+p_username+"', '"+dateOfVisit+"', '"+drugName+"', '"+dosage+"', '"+duration+"', '"+notes+"')";
					String sql5 = "DELETE FROM RequestsAppointment WHERE P_Username = '" +p_username+"' AND D_LicenseNumber = '"+licenseNumber+"'";
					con.setAutoCommit(false);
					
					stmt.addBatch(sql2);
					stmt.addBatch(sql3);
					stmt.addBatch(sql4);
					stmt.addBatch(sql5);
					for (int i=0;i<prescriptions.size();i++) {
						stmt.addBatch(prescriptions.get(i));
					}
					stmt.executeBatch();
					con.commit();
					
					
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}


			try {
				stmt.close();
				stmt2.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
