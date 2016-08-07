import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class BillingPanel {
	
	private ArrayList<String> usernames;
	private ArrayList<String> incomes;
	
	public BillingPanel(final JPanel panel, final Connection con){
		usernames = new ArrayList<String>();
		incomes = new ArrayList<String>();
		
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
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		
		panel.setLayout(gridBagLayout);
		
		JLabel nameLabel = new JLabel("Patient Name:   ");
		final JTextField nameField = new JTextField();
		GridBagConstraints nameFieldc = new GridBagConstraints();
		nameFieldc.gridwidth = 2;
		nameFieldc.insets = new Insets(0, 0, 5, 5);
		nameFieldc.fill = GridBagConstraints.HORIZONTAL;
		nameFieldc.gridx = 2;
		nameFieldc.gridy = 0;
		
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			
			private JScrollPane namePane;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					usernames.clear();
					incomes.clear();
					final Statement stmt = con.createStatement();
					String nameQuery = "SELECT Name, HomePhone, P_username, AnnualIncome FROM Patient where Name = '" + nameField.getText() + "'";
					ResultSet nameResults = stmt.executeQuery(nameQuery);
					
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Patient Name");
					model.addColumn("Phone Number");
					
					while(nameResults.next()){
						Vector<String> vector = new Vector<String>();
						vector.add(nameResults.getString(1));
						vector.add(nameResults.getString(2));
						
						model.addRow(vector);
						usernames.add(nameResults.getString(3));
						incomes.add(nameResults.getString(4));
					}
					
					final JTable nameTable = new JTable(model);
					
					Component[] components = panel.getComponents();
				    for (Component component : components) {
				    	if(component == namePane){
				    		panel.remove(namePane);
				    	}
				    }
					namePane = new JScrollPane(nameTable);
					
					JButton create = new JButton("Create Bill");
					GridBagConstraints createc = new GridBagConstraints();
					createc.gridx = 0;
					createc.gridy = 2;
					create.addActionListener(new ActionListener() {
						
						private JScrollPane visitsPane;

						@Override
						public void actionPerformed(ActionEvent e) {
							int selI = nameTable.getSelectedRow();
							String username = usernames.get(selI);
							String visitsQuery = "SELECT DateOfVisit, BillingAmount, P_username FROM Visit where P_Username = '" + username + "'";
							DefaultTableModel model2 = new DefaultTableModel();
							model2.addColumn("Date of Visit");
							model2.addColumn("Billing Amount");
							
							int total = 0;
							
							try {
								ResultSet visitsResult = stmt.executeQuery(visitsQuery);
								
								while(visitsResult.next()) {
									Vector<String> vector2 = new Vector<String>();
									vector2.add(visitsResult.getString(1));
									
									int index = usernames.indexOf(visitsResult.getString(3));
									String income = incomes.get(index);
									
									long  amount;
									if(income.equals("below 25000$")){
									 amount = Math.round(Integer.parseInt(visitsResult.getString(2)) * .8);
									}
									else{
										amount = Integer.parseInt(visitsResult.getString(2));
									}
									
									vector2.add( amount + "");
									total += amount;
									model2.addRow(vector2);
								};
								
								String surgeryQuery = "SELECT SurgeryType, CostOfSurgery, P_Username FROM Surgery as s, Performs as p where P_Username = '" + username + "' AND s.CPTcode = p.CPTcode";
								ResultSet surgeryResult = stmt.executeQuery(surgeryQuery);
								
								Vector<String> surgeryVector = new Vector<String>();
								surgeryVector.add("<html><b>Surgery</b></html>");
								surgeryVector.add(" ");
								model2.addRow(surgeryVector);
								
								while(surgeryResult.next()) {
									Vector<String> vector2 = new Vector<String>();
									vector2.add(surgeryResult.getString(1));
									int index = usernames.indexOf(surgeryResult.getString(3));
									String income = incomes.get(index);
									
									long amount;
									if(income.equals("below 25000$")){
										amount = Math.round(Integer.parseInt(surgeryResult.getString(2)) * .5);
									}
									else{
										amount = Integer.parseInt(surgeryResult.getString(2));
									}
									vector2.add(amount+"");
									total += amount;
									model2.addRow(vector2);
								}

								Vector<String> totalVector = new Vector<String>();
								totalVector.add("<html><b>Total</b></html>");
								totalVector.add("<html><b>" + total + "</b></html> ");
								model2.addRow(totalVector);
								
								
								JTable visitsTable = new JTable(model2);
								Component[] components = panel.getComponents();
							    for (Component component : components) {
							    	if(component == visitsPane){
							    		panel.remove(visitsPane);
							    	}
							    }
								visitsPane = new JScrollPane(visitsTable);
								
								GridBagConstraints visitsPanec = new GridBagConstraints();
								visitsPanec.gridwidth = 82;
								visitsPanec.gridx = 0;
								visitsPanec.gridy = 3;
								visitsPanec.weightx = 1;
								visitsPanec.weighty = 1;
								visitsPanec.fill = GridBagConstraints.BOTH;		
								
								panel.add(visitsPane, visitsPanec);
								
								panel.revalidate();
								
								
								

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					
					
					
					GridBagConstraints namePanec = new GridBagConstraints();
					namePanec.gridwidth = 82;
					namePanec.gridx = 0;
					namePanec.gridy = 1;
					namePanec.weightx = 1;
					namePanec.weighty = 1;
					namePanec.fill = GridBagConstraints.BOTH;
					
					panel.add(create, createc);
					panel.add(namePane, namePanec);
					
					panel.revalidate();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		panel.add(nameLabel);
		panel.add(nameField,nameFieldc);
		panel.add(search);
	}
}
