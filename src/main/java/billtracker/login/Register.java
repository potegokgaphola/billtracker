package billtracker.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField username_field;
	private JTextField firstname_field;
	private JTextField lastname_field;
	private JPasswordField password_field;
	private JPasswordField confirm_password_field;
	private JLabel error_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 11, 398, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel register_lbl = new JLabel("Register");
		register_lbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		register_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		register_lbl.setBounds(22, 11, 353, 23);
		panel.add(register_lbl);
		
		JLabel username_lbl = new JLabel("Username");
		username_lbl.setBounds(35, 60, 133, 14);
		panel.add(username_lbl);
		
		JLabel firstname_lbl = new JLabel("First Name");
		firstname_lbl.setBounds(35, 85, 133, 14);
		panel.add(firstname_lbl);
		
		JLabel lastname_lbl = new JLabel("Last Name");
		lastname_lbl.setBounds(35, 110, 133, 14);
		panel.add(lastname_lbl);
		
		JLabel password_lbl = new JLabel("Password");
		password_lbl.setBounds(35, 135, 133, 14);
		panel.add(password_lbl);
		
		JLabel confirm_password_lbl = new JLabel("Confirm Password");
		confirm_password_lbl.setBounds(35, 160, 133, 14);
		panel.add(confirm_password_lbl);
		
		username_field = new JTextField();
		username_field.setBounds(222, 57, 166, 20);
		panel.add(username_field);
		username_field.setColumns(10);
		
		firstname_field = new JTextField();
		firstname_field.setBounds(222, 82, 166, 20);
		panel.add(firstname_field);
		firstname_field.setColumns(10);
		
		lastname_field = new JTextField();
		lastname_field.setBounds(222, 107, 166, 20);
		panel.add(lastname_field);
		lastname_field.setColumns(10);
		
		password_field = new JPasswordField();
		password_field.setBounds(222, 132, 166, 20);
		panel.add(password_field);
		password_field.setColumns(10);
		
		confirm_password_field = new JPasswordField();
		confirm_password_field.setBounds(222, 157, 166, 20);
		panel.add(confirm_password_field);
		confirm_password_field.setColumns(10);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.setBackground(Color.GRAY);
		submit_btn.setForeground(Color.WHITE);
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 11));
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username, firstname, lastname, password, confirm_password;
				
				if (checkFields()) {
					//get all user data
					System.out.println("all fields checkout");
					username = username_field.getText();
					firstname = firstname_field.getText();
					lastname = lastname_field.getText();
					password = String.valueOf(password_field.getPassword());
					confirm_password = String.valueOf(confirm_password_field.getPassword());
					
					if ( password.equals(confirm_password)) {
						System.out.println("passwords match");
						if(!checkUser(username) && insertUser(username, firstname, lastname, password)) {
							
							Login login = new Login();
							login.setVisible(true);
							Register.this.dispose();
						}
					} else {
						
						error_lbl.setText("Passwords do not match");
					}
				} else {
					
					error_lbl.setText("All fields are required");
				}
				
			}
		});
		submit_btn.setBounds(100, 205, 195, 23);
		panel.add(submit_btn);
		
		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(35, 185, 353, 14);
		panel.add(error_lbl);
		
	}
	
	/**
	 * Checks user in the database
	 * @param username
	 * @return true if user exists
	 */
	public boolean checkUser(String username) {
		//check username in db
		boolean output = false;
		
		String query = "SELECT * FROM `users` WHERE `username`=?";
		try {
			PreparedStatement statement = My_CNX.getConnection().prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				
				output = true;
				error_lbl.setText("username already exists");
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return output;
	}
	
	/**
	 * Inserts a new user to the database
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param password
	 * @return true if successful
	 */
	public boolean insertUser(String username, String firstname, String lastname, String password) {
		boolean output = false;
		String query_two = "INSERT INTO `users`(`username`, `firstname`, `lastname`, `password`) VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = My_CNX.getConnection().prepareStatement(query_two);
			statement.setString(1, username);
			statement.setString(2, firstname);
			statement.setString(3, lastname);
			statement.setString(4, password);
			
			int result_two = statement.executeUpdate();
			if(result_two == 1) {
				//success
				output = true;
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
		
		if(username_field.getText().trim().isEmpty() || firstname_field.getText().trim().isEmpty() 
				|| lastname_field.getText().trim().isEmpty() 
				|| String.valueOf(password_field).trim().isEmpty()
				|| String.valueOf(confirm_password_field).trim().isEmpty()) {
			output = false;
		}
		
		return output;
	}
}
