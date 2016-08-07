import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;


import java.sql.PreparedStatement;
import java.util.ArrayList;


public class ScheduleAppointmentPanel{

	private Connection con;
	private JPanel panel;
	private String user;
	JComboBox<String> specialtyCombo;
	JTable table;
	JScrollPane pane;
	ResultSet doctors;
	
	public ScheduleAppointmentPanel (JPanel panel, Connection con,String user) {
		this.con = con; 
		this.panel = panel;
		this.user=user;
		panel.setLayout(null);
		
		specialtyCombo = new JComboBox<String>();
		specialtyCombo.addItem("General Physician");
		specialtyCombo.addItem("Heart Specialist");
		specialtyCombo.addItem("Eye Physician");
		specialtyCombo.addItem("Orthopedics");
		specialtyCombo.addItem("Psychiatry");
		specialtyCombo.addItem("Gynecologist");
		
		specialtyCombo.setBounds(10,10,150,21);
		
		JButton search = new JButton("Search");
		search.setBounds(180,10,100,21);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getAvailabilities();
				
			}
		});
		
		JButton backButton = new JButton("Return To Home Page");
		backButton.setBounds(180, 30, 200, 21);
		panel.add(backButton);
		backButton.addActionListener(new AddBack());
		
		table = new JTable();
		panel.add(specialtyCombo);
		panel.add(search);
		
		
		

	}

	private class AddBack implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new PatientMenuPanel(panel,con,user);
			panel.validate();
			panel.repaint();
		}
	}
	
	private void getAvailabilities(){
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT LicenseNumber, FirstName, LastName, WorkPhone, RoomNumber,  Day , FromTime, ToTime, AVG(Rating) as rate FROM Availability_new AS a, Doctor LEFT JOIN Rates ON LicenseNumber = Rates.D_LicenseNumber WHERE Specialty =  '" + specialtyCombo.getSelectedItem() + "' AND LicenseNumber = a.D_LicenseNumber GROUP BY LicenseNumber, DAY , FromTime, ToTime");
			doctors = stmt.executeQuery();
			ArrayList<String> data = new ArrayList<String>();
			while(doctors.next()) {
				if(data.contains(doctors.getString("LicenseNumber"))) {
					data.add(" ");
					data.add(" ");
					data.add(" ");
				}
				else{
					data.add(doctors.getString("FirstName") + " " + doctors.getString("LastName"));
					data.add(doctors.getString("WorkPhone"));
					data.add(doctors.getString("RoomNumber"));
				}
				
				data.add(doctors.getString("Day") + ": " + doctors.getString("FromTime") + " - " + doctors.getString("ToTime")); 
				data.add(doctors.getString("rate"));
			
			}
			
			int rowi = 0;
			int coli = 0;
			String[][] rows = new String[data.size() / 5][5];
			for(String d: data) {
				rows[rowi][coli] = d;
				
				if(coli == 4){
					coli = 0;
					if(rows[rowi][0].equals(" ")){
						rows[rowi][4] = " ";
					}
					rowi++;
				}
				else{
					coli++;
				}
			}
			String[] columnNames = {"Doctor Name", "Phone #", "Room #", "Availability", "Avg Rating"};

			table = new JTable(rows,columnNames);
		    table.getColumnModel().getColumn(2).setPreferredWidth(5);
		    table.getColumnModel().getColumn(3).setPreferredWidth(150);

		    Component[] components = panel.getComponents();
		    for (Component component : components) {
		    	if(component == pane){
		    		panel.remove(pane);
		    	}
		    }
			pane = new JScrollPane(table);
			pane.setBounds(0,60,600,200);
			pane.setBackground(panel.getBackground());
			table.setBackground(panel.getBackground());
			
			
			JButton requestButton = new JButton("Request Appointment");
			requestButton.setBounds(50,275,180,21);
			requestButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int selI = table.getSelectedRow();
					
					try {
						doctors.first();
						while(doctors.getRow() != selI+1){
							doctors.next();
						}
						PreparedStatement stmt = con.prepareStatement("SELECT Date,ScheduledTime FROM RequestsAppointment WHERE Date = '" + doctors.getString("Day") + "' and ScheduledTime = '" + doctors.getString("FromTime") +"' AND d_licensenumber = '" + doctors.getString("LicenseNumber")  +"'");
						ResultSet rs = stmt.executeQuery();
						if(rs.next() == true){
							JOptionPane.showMessageDialog(panel, "That time slot is already taken!");
						}
						else{
							String query = "INSERT INTO RequestsAppointment (P_Username, D_LicenseNumber, Date, ScheduledTime) VALUES (\""+user+"\",\""+ doctors.getString("LicenseNumber") + "\",\"" +  doctors.getString("Day") + "\",\"" + doctors.getString("FromTime") + "\")";
							stmt = con.prepareStatement(query);
							stmt.executeUpdate();
							
							JOptionPane.showMessageDialog(panel,"Appointment made!");
							
							panel.removeAll();
							new PatientMenuPanel(panel,con,user);
							panel.revalidate();
							panel.repaint();
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(requestButton);
			panel.add(pane);
			
			panel.revalidate();
			panel.repaint();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
