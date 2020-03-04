package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.login.CurrentUser;
import billtracker.login.My_CNX;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillsGraph extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillsGraph frame = new BillsGraph();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BillsGraph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//to be deleted
		String query = "SELECT * FROM `users` WHERE `user_id`=?";
		try {
			CurrentUser user = new CurrentUser();
			PreparedStatement st = My_CNX.getConnection().prepareStatement(query);
			st.setLong(1, user.getId());
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				System.out.println("firstname: " + rs.getString("firstname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//to be deleted
		
		JLabel lblNewLabel = new JLabel("HOME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(97, 95, 150, 34);
		contentPane.add(lblNewLabel);
	}

}
