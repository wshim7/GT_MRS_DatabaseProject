import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PatientMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private Connection con;
	private JPanel panel;
	private String p_username;
	private JButton backButton;

	public PatientMenuPanel(JPanel panel, Connection con, String p_username) {
		this.con = con; this.panel = panel; this.p_username = p_username;
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label = new JLabel ("Homepage for Patients");
		label.setBackground(Color.WHITE);
		label.setForeground(Color.GRAY);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		JButton btnMakeAppointments = new JButton("Make Appointments");
		panel.add(btnMakeAppointments);
		btnMakeAppointments.addActionListener(new AddMakeAppointments());

		JButton btnViewVisitHistory = new JButton("View Visit History");
		panel.add(btnViewVisitHistory);
		btnViewVisitHistory.addActionListener(new AddViewVisitHistory());

		JButton btnOrderMedication = new JButton("Order Medication");
		panel.add(btnOrderMedication);
		btnOrderMedication.addActionListener(new AddOrderMedication());

		JButton btnCommunicate = new JButton("Communicate");
		panel.add(btnCommunicate);
		btnCommunicate.addActionListener(new AddCommunicate());

		JButton btnRateADoctor = new JButton("Rate A Doctor");
		panel.add(btnRateADoctor);
		btnRateADoctor.addActionListener(new AddRateADoctor());

		JButton btnEditProfile = new JButton("Edit Profile");
		panel.add(btnEditProfile);
		btnEditProfile.addActionListener(new AddEditProfile());

		
		String query = "SELECT Count(Status) From Sends_message_to_Pat where Status = 'Unread' AND P_username = '"+p_username+"'";
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
		panel.add(backButton);
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

	private class AddMakeAppointments implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new ScheduleAppointmentPanel(panel,con,p_username);
			panel.validate();
			panel.repaint();
		}
	}
	private class AddViewVisitHistory implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new ViewVisitHistoryPanel(panel,con,p_username);
			panel.validate();
			panel.repaint();
		}
	}
	private class AddOrderMedication implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new OrderMedicationPanel(panel,con,p_username);
			panel.validate();
			panel.repaint();
		}
	}
	private class AddCommunicate implements ActionListener{   //won goo

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new SendMessageToDoctor(panel, con, p_username);
			panel.validate();
			panel.repaint();
		}
	}
	private class AddRateADoctor implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new RatePanel(panel,con,p_username);
			panel.validate();
			panel.repaint();
		}
	}
	private class AddEditProfile implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new PatientProfilePanel(panel,con,p_username);
			panel.validate();
			panel.repaint();
		}
	}	
	private class AddMessages implements ActionListener{ 

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new MessageInbox(panel,con,p_username);
			panel.validate();
			panel.repaint();
		}
	}
	
}
