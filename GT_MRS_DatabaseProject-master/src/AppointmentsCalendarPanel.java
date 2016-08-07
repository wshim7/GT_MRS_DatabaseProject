import javax.swing.JPanel;  

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


public class AppointmentsCalendarPanel extends JPanel {
	private JTextField textField;
	private JPanel panel;
	private Connection con;
	String licenseNumber;
	JPanel calendar;
	JTable table;
	JScrollPane pane;

	/**
	 * Create the panel.
	 */
	public AppointmentsCalendarPanel(final JPanel panel,final Connection con,final String d_username, final String licenseNumber) {
		this.panel = panel;
		this.con = con;
		this.licenseNumber = licenseNumber;

		
		
		setBackground(new Color(60, 179, 113));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 82, 1, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel lblAppointmentsCalendar = new JLabel("Appointments Calendar");
		lblAppointmentsCalendar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblAppointmentsCalendar = new GridBagConstraints();
		gbc_lblAppointmentsCalendar.anchor = GridBagConstraints.EAST;
		gbc_lblAppointmentsCalendar.gridwidth = 5;
		gbc_lblAppointmentsCalendar.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppointmentsCalendar.gridx = 3;
		gbc_lblAppointmentsCalendar.gridy = 1;
		panel.add(lblAppointmentsCalendar, gbc_lblAppointmentsCalendar);
		
		JLabel lblSelectDate = new JLabel("Select Date:");
		lblSelectDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblSelectDate = new GridBagConstraints();
		gbc_lblSelectDate.anchor = GridBagConstraints.EAST;
		gbc_lblSelectDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectDate.gridx = 0;
		gbc_lblSelectDate.gridy = 3;
		panel.add(lblSelectDate, gbc_lblSelectDate);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy/HH:mm:ss");
		Date dateobj = new Date();
		String date = df.format(dateobj);
		String parts[] = date.split("/");
		int month = Integer.parseInt(parts[1]);
		int day = Integer.parseInt(parts[0]);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox.setSelectedIndex(day);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox_1.setSelectedIndex(month-1);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 3;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton viewAppts = new JButton("View");
		GridBagConstraints gbc_viewAppts = new GridBagConstraints();
		gbc_viewAppts.gridx = 5;
		gbc_viewAppts.gridy = 3;
		viewAppts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem() != " "){
					String date = comboBox.getSelectedItem() + "/" + comboBox_1.getSelectedItem()+"/"+textField.getText();
					try {
						PreparedStatement stmt = con.prepareStatement("SELECT Name, HomePhone, ScheduledTime FROM RequestsAppointment AS ra, Patient AS p WHERE DATE =  \"" + date + "\" AND ra.P_UserName = p.P_UserName AND d_licensenumber = '" + licenseNumber + "' ORDER BY ScheduledTime ASC");
						ResultSet rs = stmt.executeQuery();
						
						DefaultTableModel model = new DefaultTableModel();
						model.addColumn("Name");
						model.addColumn("Phone Number");
						model.addColumn("Time");
						
						while(rs.next()){
							Vector<String> vector = new Vector<String>();
							vector.add(rs.getString(1));
							vector.add(rs.getString(2));
							vector.add(rs.getString(3));
							model.addRow(vector);
						}
						
						table = new JTable(model);
						
						Component[] components = panel.getComponents();
						for(Component component: components) {
							if(component == calendar || component == pane){
								panel.remove(component);
							}
						}
						
						pane = new JScrollPane(table);
						GridBagConstraints gbc_pane = new GridBagConstraints();
						gbc_pane.gridwidth = 82;
						gbc_pane.gridx = 0;
						gbc_pane.gridy = 6;
						gbc_pane.weightx = 1;
						gbc_pane.weighty = 1;
						gbc_pane.fill = GridBagConstraints.BOTH;
						pane.setBackground(panel.getBackground());
						panel.add(pane,gbc_pane);
						
						panel.revalidate();
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else{
					Component[] components = panel.getComponents();
					for(Component component: components) {
						if(component == calendar || component == pane){
							panel.remove(component);
						}
					}
					calendar = new JPanel();
					calendar.setLayout(new GridLayout());
					for(int i = 1; i < 32; i++){
						JPanel block = new JPanel();
						block.setLayout(new BorderLayout());
						if(i == 21){
							String test = "";
						}
						JLabel day = new JLabel(i+"");
						block.add(day,BorderLayout.NORTH);
						
						try {
							String month = (String) comboBox_1.getSelectedItem();
							String query = "SELECT Count(*) FROM RequestsAppointment WHERE D_LicenseNumber = '" + licenseNumber + "' AND Date like '%"+i+"/" + month + "/%"+textField.getText() + "'";
							System.out.println(query);
							PreparedStatement statement = con.prepareStatement(query);
							ResultSet rs = statement.executeQuery();
							rs.next();
							
							if(!rs.getString(1).equals("0")){
								JLabel count = new JLabel(rs.getString(1));
								count.setForeground(Color.RED);
								count.setFont(new Font("Verdana", Font.BOLD, 15));
								block.add(count,BorderLayout.CENTER);
							}
							
							block.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							calendar.add(block);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
					
					
					GridBagConstraints gbc_calendar = new GridBagConstraints();
					gbc_calendar.gridwidth = 82;
					gbc_calendar.gridx = 0;
					gbc_calendar.gridy = 6;
					gbc_calendar.weightx = 1;
					gbc_calendar.weighty = 1;
					gbc_calendar.fill = GridBagConstraints.BOTH;
					panel.add(calendar,gbc_calendar);
					
					panel.revalidate();

				}
			}
		});
		panel.add(viewAppts, gbc_viewAppts);
		
		
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
		

	}

}
