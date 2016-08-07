import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Component;
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
import javax.swing.table.DefaultTableModel;


public class PatientVisitHistoryPanel extends JPanel {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox_1;
	private JTextField nameField;
	private JTextField phoneField;

	private JPanel panel;
	private Connection con;
	private String licenseNumber;
	ArrayList<String> usernames;
	ArrayList<JButton> dateButtons;

	String username;
	/**
	 * Create the panel.
	 */
	public PatientVisitHistoryPanel(final JPanel panel, final Connection con,final String d_username, final String licenseNumber) {
		this.panel = panel;
		this.con = con;
		this.licenseNumber = licenseNumber;
		usernames = new ArrayList<String>();
		dateButtons = new ArrayList<JButton>();
		setBackground(new Color(102, 205, 170));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);


		JLabel name = new JLabel("Name: ");
		JLabel phone = new JLabel("Phone: ");
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {

			private JScrollPane patientPane;

			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model2 = new DefaultTableModel();
				model2.addColumn("Name");
				model2.addColumn("Phone");

				try {
					usernames.clear();
					Statement stmt2 = con.createStatement();
					String searchQuery = "SELECT DISTINCT Name, HomePhone, p.P_Username FROM Patient AS p INNER JOIN Visit AS v ON p.P_UserName = v.P_UserName WHERE D_LicenseNumber = '" + licenseNumber + "' AND ( Name =  '" + nameField.getText() + "' OR HomePhone = '" + phoneField.getText() + "')";
					ResultSet searchResults = stmt2.executeQuery(searchQuery);

					while(searchResults.next()) {
						Vector<String> vector = new Vector<String>();
						vector.add(searchResults.getString(1));
						vector.add(searchResults.getString(2));
						usernames.add(searchResults.getString(3));

						model2.addRow(vector);
					}

					final JTable patientTable = new JTable(model2);
					Component[] components = panel.getComponents();
					for (Component component : components) {
						if(component == patientPane){
							panel.remove(component);
						}
					}
					patientPane = new JScrollPane(patientTable);

					GridBagConstraints patientPanec = new GridBagConstraints();
					patientPanec.gridwidth = 70;
					patientPanec.gridx = 0;
					patientPanec.gridy = 1;
					patientPanec.weightx = 1;
					patientPanec.weighty = 1;
					patientPanec.fill = GridBagConstraints.BOTH;

					JButton create = new JButton("Create Records");
					GridBagConstraints createc = new GridBagConstraints();
					createc.gridx = 2;
					createc.gridy = 4;
					create.addActionListener(new ActionListener() {


						public void actionPerformed(ActionEvent e) {
							panel.removeAll();
							new RecordVisitPanel(panel,con,nameField.getText(),d_username,licenseNumber);
							panel.validate();
							panel.repaint();
						}
					});


					JButton view = new JButton("View");
					GridBagConstraints viewc = new GridBagConstraints();
					viewc.gridx = 0;
					viewc.gridy = 4;
					view.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							int selI = patientTable.getSelectedRow();
							username = usernames.get(selI);
							try {
								final Statement stmt = con.createStatement();
								String query = "SELECT DateOfVisit From Visit WHERE P_Username = '" + username +"' AND D_LicenseNumber = '" + licenseNumber +"'";
								ResultSet rs = stmt.executeQuery(query);

								int y = 6;
								Component[] components = panel.getComponents();
								for(Component component: components){
									if(component instanceof JButton) {
										if(((JButton) component).getText().contains("/")){
											panel.remove(component);
										}
									}
								}
								
								panel.revalidate();
								panel.repaint();
								while(rs.next()){
									JButton button = new JButton(rs.getString(1));
									GridBagConstraints buttonc = new GridBagConstraints();
									buttonc.gridx = 0;
									buttonc.gridy = y++;
									buttonc.gridwidth = 2;
									dateButtons.add(button);

									button.addActionListener(new ActionListener() {

										private JScrollPane pane;

										@Override
										public void actionPerformed(ActionEvent e) {
											JButton caller = (JButton)e.getSource();
											String date = caller.getText();

											int callerIndex = dateButtons.indexOf(caller);
											String infoQuery = "SELECT Diastolic, Systolic FROM Doctor , Visit  WHERE LicenseNumber = '" + licenseNumber + "' AND DateOfVisit = '" + date + "' AND LicenseNumber = D_LicenseNumber AND P_Username = '" + username + "'";
											try {
												ResultSet info = stmt.executeQuery(infoQuery);
												info.next();
												textField_2.setText(info.getString(2));
												textField_3.setText(info.getString(1));

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
												panec.gridy = 10;
												panec.weightx = 1;
												panec.weighty = 1;
												panec.fill = GridBagConstraints.BOTH;

												Component[] components = panel.getComponents();
												for (Component component : components) {
													if(component == pane){
														panel.remove(pane);
													}
												}
												pane = new JScrollPane(table);
												panel.add(pane, panec);

												panel.revalidate();

											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}

										}
									});

									panel.add(button,buttonc);

									panel.revalidate();
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}

					});
					panel.add(view, viewc);
					panel.add(create, createc);

					panel.add(patientPane,patientPanec);

					panel.revalidate();


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton backButton = new JButton("Return To Home Page");
		GridBagConstraints gbc_b = new GridBagConstraints();
		gbc_b.insets = new Insets(0, 0, 0, 5);
		gbc_b.gridx = 16;
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

		nameField = new JTextField();
		GridBagConstraints nameFieldc = new GridBagConstraints();
		nameFieldc.gridwidth = 2;
		nameFieldc.insets = new Insets(0, 0, 5, 5);
		nameFieldc.fill = GridBagConstraints.HORIZONTAL;
		nameFieldc.gridx = 1;
		nameFieldc.gridy = 0;

		phoneField = new JTextField();
		GridBagConstraints phoneFieldc = new GridBagConstraints();
		phoneFieldc.gridwidth = 2;
		phoneFieldc.insets = new Insets(0, 0, 5, 5);
		phoneFieldc.fill = GridBagConstraints.HORIZONTAL;
		phoneFieldc.gridx = 4;
		phoneFieldc.gridy = 0;

		panel.add(name);
		panel.add(nameField, nameFieldc);
		panel.add(phone);
		panel.add(phoneField, phoneFieldc);
		panel.add(search);

		JLabel lblViewVisitHistory = new JLabel("View Visit History");
		lblViewVisitHistory.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblViewVisitHistory = new GridBagConstraints();
		gbc_lblViewVisitHistory.gridwidth = 7;
		gbc_lblViewVisitHistory.insets = new Insets(0, 0, 5, 5);
		gbc_lblViewVisitHistory.gridx = 4;
		gbc_lblViewVisitHistory.gridy = 4;
		panel.add(lblViewVisitHistory, gbc_lblViewVisitHistory);

		JLabel lblDatesOfVisits = new JLabel("Dates of Visits");
		lblDatesOfVisits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDatesOfVisits = new GridBagConstraints();
		gbc_lblDatesOfVisits.gridwidth = 2;
		gbc_lblDatesOfVisits.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatesOfVisits.gridx = 0;
		gbc_lblDatesOfVisits.gridy = 5;
		panel.add(lblDatesOfVisits, gbc_lblDatesOfVisits);

		JLabel lblBloodPressure = new JLabel("Blood Pressure:");
		lblBloodPressure.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblBloodPressure = new GridBagConstraints();
		gbc_lblBloodPressure.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressure.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressure.gridx = 2;
		gbc_lblBloodPressure.gridy = 6;
		panel.add(lblBloodPressure, gbc_lblBloodPressure);

		JLabel lblSystolic = new JLabel("Systolic:");
		lblSystolic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblSystolic = new GridBagConstraints();
		gbc_lblSystolic.insets = new Insets(0, 0, 5, 5);
		gbc_lblSystolic.gridx = 3;
		gbc_lblSystolic.gridy = 6;
		panel.add(lblSystolic, gbc_lblSystolic);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 1;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 6;
		panel.add(textField_2, gbc_textField_2);
		//textField_2.setColumns(10);

		JLabel lblDiastolic = new JLabel("Diastolic:");
		lblDiastolic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDiastolic = new GridBagConstraints();
		gbc_lblDiastolic.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiastolic.gridx = 5;
		gbc_lblDiastolic.gridy = 6;
		panel.add(lblDiastolic, gbc_lblDiastolic);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 1;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 6;
		gbc_textField_3.gridy = 6;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblDiagnosis = new JLabel("Diagnosis:");
		lblDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDiagnosis = new GridBagConstraints();
		gbc_lblDiagnosis.anchor = GridBagConstraints.EAST;
		gbc_lblDiagnosis.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiagnosis.gridx = 4;
		gbc_lblDiagnosis.gridy = 7;
		panel.add(lblDiagnosis, gbc_lblDiagnosis);

		comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 8;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 5;
		gbc_comboBox_1.gridy = 7;
		panel.add(comboBox_1, gbc_comboBox_1);



	}

}
