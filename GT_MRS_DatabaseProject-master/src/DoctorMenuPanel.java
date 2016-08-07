import javax.swing.JPanel; 
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DoctorMenuPanel extends JPanel {

	private Connection con;
	private JPanel panel;
	private String d_username;
	private String d_licenseNumber;
	private JButton backButton;

	/**
	 * Create the panel.
	 */
	public DoctorMenuPanel(JPanel panel, Connection con, String d_username) {
		this.con = con; this.panel = panel; this.d_username = d_username;
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblHomepageForDoctors = new JLabel("Homepage for Doctors");
		lblHomepageForDoctors.setBackground(Color.WHITE);
		lblHomepageForDoctors.setForeground(Color.GRAY);
		lblHomepageForDoctors.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblHomepageForDoctors);


		JButton btnViewAppointmentsCalendar = new JButton("View Appointments Calendar");
		panel.add(btnViewAppointmentsCalendar);
		btnViewAppointmentsCalendar.addActionListener(new AddViewAppointmentsCalendar());

		JButton btnPatientVisits = new JButton("Patient Visits");
		panel.add(btnPatientVisits);
		btnPatientVisits.addActionListener(new AddPatientVisits());

		JButton btnRecordASurgery = new JButton("Record A Surgery");
		panel.add(btnRecordASurgery);
		btnRecordASurgery.addActionListener(new AddRecordASurgery());

		JButton btnCommunicate = new JButton("Communicate");
		panel.add(btnCommunicate);
		btnCommunicate.addActionListener(new AddCommunicate());

		JButton btnEditProfile = new JButton("Edit Profile");
		panel.add(btnEditProfile);
		btnEditProfile.addActionListener(new AddEditProfile());
		
		Statement stmt = null;

		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		String sql = null;

		sql = "SELECT LicenseNumber FROM Doctor WHERE D_username ='"+ d_username +"'";

		try {
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			d_licenseNumber = rs.getString("LicenseNumber");


		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = "SELECT COUNT( * ) FROM (SELECT * FROM Sends_message_to_Doc WHERE status =  'Unread' AND d_licensenumber = '" + d_licenseNumber + "' UNION SELECT * FROM CommunicateWith WHERE  Status =  'Unread' AND Receiver_LicenseNumber = '"+d_licenseNumber+"') AS dt";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			JButton btnMessages = new JButton(rs.getString(1) + " Unread Messages");
			panel.add(btnMessages);
			btnMessages.addActionListener(new AddMessages());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		backButton = new JButton("Log Out");
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

	private class AddViewAppointmentsCalendar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new AppointmentsCalendarPanel(panel,con,d_username, d_licenseNumber);
			panel.validate();
			panel.repaint();
		}
	}
	private class AddPatientVisits implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			
			
			panel.removeAll();
			new PatientVisitHistoryPanel(panel,con,d_username, d_licenseNumber);
			panel.validate();
			panel.repaint();

		}
	}	
	private class AddRecordASurgery implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new RecordSurgeryPanel(panel,con,d_username,d_licenseNumber);
			panel.validate();
			panel.repaint();
		}
	}

	private class AddCommunicate implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new SendMessageToDoctors(panel,con,d_username); 
			panel.validate();
			panel.repaint();
		}
	}	
	private class AddEditProfile implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new DoctorProfilePanel(panel,con,d_username);
			panel.validate();
			panel.repaint();
		}
	}
	
	private class AddMessages implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new MessageInboxDoc(panel,con,d_username,d_licenseNumber);
			panel.validate();
			panel.repaint();
		}
	}
}
