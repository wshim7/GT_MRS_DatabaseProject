import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.sql.*;

public class main {
	public static void main (String args[]) throws SQLException { 
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Group_58", "cs4400_Group_58", "AosiKF9X");
			if(!con.isClosed()) System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} 
		
		
		
		JFrame frame = new JFrame("GTMRS ");
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);
		new LoginPanel(panel,con);
		
	} 
}


