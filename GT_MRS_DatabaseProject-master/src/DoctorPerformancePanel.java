import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DoctorPerformancePanel extends JPanel {
	private JPanel panel;
	private Connection con;
	private ArrayList<String> specialties;
	
	public DoctorPerformancePanel(final JPanel panel, final Connection con){
		this.panel = panel;
		this.con = con;
		panel.setLayout(new FlowLayout());
		specialties = new ArrayList<String>();
		
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
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Specialty");
		model.addColumn("Average Rating");
		model.addColumn("# of Surgeries");
		
		String rateQuery = "SELECT Specialty, AVG(Rating) FROM Doctor as d LEFT JOIN Rates as r ON LicenseNumber = r.D_licenseNumber GROUP BY Specialty"; 
		String surgeryQuery = "SELECT Specialty, COUNT(d_licensenumber) FROM Doctor,Performs WHERE LicenseNumber = D_licenseNumber GROUP BY Specialty";
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rates = stmt.executeQuery(rateQuery);
			
			while(rates.next()){
				Vector<String> vector = new Vector<String>();
				vector.add(rates.getString(1));
				vector.add(rates.getString(2));
				vector.add("0");
				specialties.add(rates.getString(1));
				
				model.addRow(vector);
			}
			
			ResultSet surgeries = stmt.executeQuery(surgeryQuery);
			while(surgeries.next()){
				int i = specialties.indexOf(surgeries.getString(1));
				String test = surgeries.getString(1);
				model.setValueAt(surgeries.getString(2), i, 2);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JTable table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		
		panel.add(pane);
		panel.repaint();
	}
}