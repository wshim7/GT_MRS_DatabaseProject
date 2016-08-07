import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;


public class PatientVisitReportPanel extends JPanel {

	JPanel panel;
	Connection con;
	String licenseNumber;
	JComboBox comboBox;
	JComboBox comboBox_1;
	
	ArrayList<String> licenses;
	/**
	 * Create the panel.
	 */
	public PatientVisitReportPanel(final JPanel panel, final Connection con) {
		this.panel = panel;
		this.con = con;
		licenses = new ArrayList<String>();
		
		JButton backButton = new JButton("Return To Home Page");
		GridBagConstraints gbc_b = new GridBagConstraints();
		gbc_b.gridx = 0;
		gbc_b.gridy = 9;
		panel.add(backButton, gbc_b);
		backButton.addActionListener(new ActionListener() {


		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new AdminMenuPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	});
		
		setBackground(new Color(60, 179, 113));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel lblPatientVisitReport = new JLabel("Patient Visit Report");
		lblPatientVisitReport.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblPatientVisitReport = new GridBagConstraints();
		gbc_lblPatientVisitReport.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientVisitReport.gridx = 10;
		gbc_lblPatientVisitReport.gridy = 1;
		panel.add(lblPatientVisitReport, gbc_lblPatientVisitReport);
		
		JLabel lblSelectMonth = new JLabel("Select Month:");
		GridBagConstraints gbc_lblSelectMonth = new GridBagConstraints();
		gbc_lblSelectMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectMonth.anchor = GridBagConstraints.EAST;
		gbc_lblSelectMonth.gridx = 9;
		gbc_lblSelectMonth.gridy = 3;
		panel.add(lblSelectMonth, gbc_lblSelectMonth);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy/HH:mm:ss");
		Date dateobj = new Date();
		String date = df.format(dateobj);
		String parts[] = date.split("/");
		int monthInt = Integer.parseInt(parts[1]);
		int yearInt = Integer.parseInt(parts[2]);
		comboBox.setSelectedIndex(monthInt-1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 10;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014"}));
		comboBox_1.setSelectedItem(yearInt+"");
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 11;
		gbc_comboBox_1.gridy = 3;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		JButton search = new JButton("Search");
		GridBagConstraints searchc = new GridBagConstraints();
		searchc.gridx = 12;
		searchc.gridy = 3;
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Doctor Name");
				model.addColumn("# of Patients Seen");
				model.addColumn("# Prescriptions Written");
				model.addColumn("Total Billing");
				
				int month = comboBox.getSelectedIndex()+1;
				String year = (String) comboBox_1.getSelectedItem();
				String visitQuery = "SELECT FirstName,LastName, COUNT(v.D_LicenseNumber) as countVisits, SUM(BillingAmount) as summ, LicenseNumber FROM Doctor LEFT JOIN Visit as v ON LicenseNumber = v.D_LicenseNumber  WHERE DateOfVisit LIKE '%/%" +month+"/" +year +"' GROUP BY v.D_LicenseNumber";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet visitResults = stmt.executeQuery(visitQuery);
					
					String prescriptionQuery = "SELECT COUNT(p.D_Licensenumber), LicenseNumber FROM Doctor LEFT JOIN Prescription as p on licensenumber = p.D_licenseNumber WHERE DateOfVisit LIKE '%/%" +month+"/" +year +"' GROUP BY p.D_LicenseNumber";
					
					while(visitResults.next()){
						Vector<String> vector = new Vector<String>();
						vector.add(visitResults.getString(1) + " " + visitResults.getString(2));
						vector.add(visitResults.getString(3));
						vector.add("0");
						vector.add(visitResults.getString(4));
						
						licenses.add(visitResults.getString(5));
						
						model.addRow(vector);
					}
					
					ResultSet prescriptionResults = stmt.executeQuery(prescriptionQuery);
					while(prescriptionResults.next()){
						for(int i = 0; i < licenses.size(); i++){
							if(licenses.get(i).equals(prescriptionResults.getString(2))){
								model.setValueAt(prescriptionResults.getString(1), i, 2);
								break;
							}
						}
					}
					
					prescriptionResults.first();
					
					JTable table = new JTable(model);
					JScrollPane pane = new JScrollPane(table);
					GridBagConstraints gbc_pane = new GridBagConstraints();
					gbc_pane.gridwidth = 82;
					gbc_pane.gridx = 0;
					gbc_pane.gridy = 6;
					gbc_pane.weightx = 1;
					gbc_pane.weighty = 1;
					gbc_pane.fill = GridBagConstraints.BOTH;
					
					panel.add(pane,gbc_pane);
					
					panel.revalidate();
				
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		panel.add(search,searchc);
		
	}

}
