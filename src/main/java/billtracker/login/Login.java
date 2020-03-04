package billtracker.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.home.Home;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username_field;
	private JPasswordField password_field;
	JLabel error_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel login_panel = new JPanel();
		login_panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel user_icon = new JLabel("");
		Image user_img = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		user_icon.setIcon(new ImageIcon(user_img));
		
		JLabel password_icon = new JLabel("");
		Image password_img = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		password_icon.setIcon(new ImageIcon(password_img));
		
		username_field = new JTextField();
		username_field.setColumns(10);
		
		JButton login_btn = new JButton("Login");
		login_btn.setForeground(Color.WHITE);
		login_btn.setBackground(Color.LIGHT_GRAY);
		login_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (checkFields()) {
					if( checkUser(username_field.getText(), String.valueOf(password_field.getPassword()))) {
						Home home = new Home();
						home.setVisible(true);
						Login.this.dispose();
					}
				} else {
					error_lbl.setText("Fill all fields");
				}
				

			}
			
		});
		
		JButton register_btn = new JButton("Register");
		register_btn.setBackground(Color.LIGHT_GRAY);
		register_btn.setForeground(Color.WHITE);
		register_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Go to the register paged
				Register register = new Register();
				register.setVisible(true);
				Login.this.dispose();
			}
			
		});
		
		password_field = new JPasswordField();
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		error_lbl.setForeground(Color.RED);
		
		JPanel heading_panel = new JPanel();
		heading_panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel heading_lbl = new JLabel("Bill Tracker");
		heading_lbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		heading_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(login_panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(heading_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(heading_panel, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(login_panel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addGap(20)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
					.addGap(6))
		);
		GroupLayout gl_login_panel = new GroupLayout(login_panel);
		gl_login_panel.setHorizontalGroup(
			gl_login_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(180)
					.addComponent(user_icon)
					.addGap(30)
					.addComponent(username_field, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(180)
					.addComponent(password_icon)
					.addGap(30)
					.addComponent(password_field, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(173)
					.addComponent(error_lbl, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(173)
					.addComponent(register_btn, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(9)
					.addComponent(login_btn, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_login_panel.setVerticalGroup(
			gl_login_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_login_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(user_icon)
						.addGroup(gl_login_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(username_field)))
					.addGap(11)
					.addGroup(gl_login_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(password_icon)
						.addGroup(gl_login_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(password_field)))
					.addGap(6)
					.addComponent(error_lbl, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_login_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(register_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(login_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(16))
		);
		login_panel.setLayout(gl_login_panel);
		GroupLayout gl_heading_panel = new GroupLayout(heading_panel);
		gl_heading_panel.setHorizontalGroup(
			gl_heading_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_heading_panel.createSequentialGroup()
					.addGap(73)
					.addComponent(heading_lbl, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(140))
		);
		gl_heading_panel.setVerticalGroup(
			gl_heading_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_heading_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(heading_lbl, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
		);
		heading_panel.setLayout(gl_heading_panel);
		contentPane.setLayout(gl_contentPane);
		
		
	}
	
	/**
	 * checks the user in the database 
	 * @param username
	 * @param password
	 * @return true if the user exist
	 */
	public boolean checkUser(String username, String password) {
		
		boolean output = false;
		String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";
		
		try {
			PreparedStatement st = My_CNX.getConnection().prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				
				User user = new CurrentUser();
				user.setId(Integer.parseInt( rs.getString("user_id")));
				user.setFirstname(rs.getString("firstname"));
				output = true;
			} else {
				//login error
				error_lbl.setText("Invald username or password");
			}
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return output;
	}
	
	/**
	 * checks if all the fields are filled
	 * @return true if all are filled
	 */
	public boolean checkFields() {
		boolean output = true;
		
		if(username_field.getText().trim().isEmpty() || String.valueOf(password_field.getPassword()).trim().isEmpty()) {
			output = false;
		}
		
		return output;
	}
}
