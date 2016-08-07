import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class AdminMenuPanel extends JPanel {
	private JPanel panel;
	private Connection con;
	/**
	 * Create the panel.
	 */
	public AdminMenuPanel(final JPanel panel, final Connection con) {
		this.panel = panel; this.con = con;
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		

		
		JLabel lblAdminHome = new JLabel("Administrative Home");
		lblAdminHome.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAdminHome);
		
		JButton btnNewButton = new JButton("Billing");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new AddBilling());
		
		JButton btnDoctorperformancereport = new JButton("Doctor Performance Report");
		panel.add(btnDoctorperformancereport);
		btnDoctorperformancereport.addActionListener(new AddDoctorPerformanceReport());
		
		JButton btnSurgeryReport = new JButton("Surgery Report");
		panel.add(btnSurgeryReport);
		btnSurgeryReport.addActionListener(new AddSurgeryReport());
		
		JButton btnPatientVisitReport = new JButton("Patient Visit Report");
		btnPatientVisitReport.setBackground(new Color(240, 255, 255));
		panel.add(btnPatientVisitReport);
		btnPatientVisitReport.addActionListener(new AddPatientVisitReport());
		
		JButton backButton = new JButton("Log Out");
		panel.add(backButton);
		backButton.addActionListener(new ActionListener() {


		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new LoginPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	});
		
	}
	
	private class AddBilling implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new BillingPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}
	
	
	private class AddDoctorPerformanceReport implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new DoctorPerformancePanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}
	
	private class AddSurgeryReport implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new SurgeriesPerformedPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}
	

	private class AddPatientVisitReport implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new PatientVisitReportPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}

}
