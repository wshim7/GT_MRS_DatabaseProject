import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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

import java.awt.Insets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class MessageInbox{
	private JTable table;
	JPanel panel;
	Connection con;
	String username;

	public MessageInbox(final JPanel panel, final Connection con, String username) {
		this.panel =panel;
		this.con=con;
		this.username = username;
		
		panel.setLayout(new FlowLayout());
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Status");
		model.addColumn("DateTime");
		model.addColumn("From");
		model.addColumn("Message");
		JButton btnMessages = new JButton("Return To Home");
		panel.add(btnMessages);
		btnMessages.addActionListener(new backButtonListner());
		
		JButton read = new JButton("Read Message");
		read.addActionListener(new ActionListener() {
			
			private JPanel messagePanel;

			@Override
			public void actionPerformed(ActionEvent e) {
				int selI = table.getSelectedRow();
				String content = (String) table.getValueAt(selI, 3);
				
				Component[] components = panel.getComponents();
			    for (Component component : components) {
			    	if(component == messagePanel){
			    		panel.remove(messagePanel);
			    	}
			    }		
				messagePanel = new JPanel();
				messagePanel.setLayout(new FlowLayout());
				JLabel messages = new JLabel("Message:              ");
				JTextArea area = new JTextArea(content);
				
				messagePanel.add(messages);
				messagePanel.add(area);
					
				panel.add(messagePanel);
				
				String query = "UPDATE Sends_message_to_Pat set status = 'Read' WHERE dateTime = '" + table.getValueAt(selI, 1) + "'";
				Statement statement;
				try {
					statement = con.createStatement();
					statement.executeUpdate(query);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				table.setValueAt("Read",selI,0);
				
				panel.revalidate();
				panel.repaint();
			}
		});
		
		panel.add(read);
		panel.revalidate();
		
		String query = "SELECT FirstName, LastName, Status, Datetime, Content FROM Doctor, Sends_message_to_Pat WHERE P_Username ='" + username +"' AND LicenseNumber = D_LicenseNumber ";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString(3));
				vector.add(rs.getString(4));
				vector.add("Dr. " + rs.getString(1)+ " " +rs.getString(2));
				vector.add(rs.getString(5));
				
				model.addRow(vector);
			}
			
			table = new JTable(model);
			JScrollPane pane = new JScrollPane(table);
			panel.add(pane);
			
			panel.revalidate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		
		private class backButtonListner implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			
			panel.removeAll();
			new PatientMenuPanel(panel,con,username);
			panel.validate();
			panel.repaint();

	}
	}


	
}
