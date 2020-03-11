package billtracker.ui.login;

import billtracker.data.user.UserData;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField username_field = new JTextField();
	private JTextField firstname_field = new JTextField();
	private JTextField lastname_field = new JTextField();
	private JPasswordField password_field = new JPasswordField();
	private JPasswordField confirm_password_field = new JPasswordField();
	private JLabel error_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				RegisterWindow frame = new RegisterWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = addRegisterPanel();

		createRegisterLabel(panel);

		createLabel(panel, "Username" , 35, 60, 133, 14);

		createLabel(panel, "First Name" ,35, 85, 133, 14);

		createLabel(panel, "Last Name" ,35, 110, 133, 14);

		createLabel(panel, "Password" ,35, 135, 133, 14);

		createLabel(panel, "Confirm Password" ,35, 160, 133, 14);

		addField(panel, username_field,222, 57, 166, 20);

		addField(panel, firstname_field,222, 82, 166, 20);

		addField(panel, lastname_field,222, 107, 166, 20);

		addPasswordField(panel, password_field, 222, 132, 166, 20);

		addPasswordField(panel, confirm_password_field,222, 157, 166, 20);

		createSubmitButton(panel);

		addErrorLabel(panel);

	}

	private JPanel addRegisterPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(26, 11, 398, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		return panel;
	}

	private void createRegisterLabel(JPanel panel) {
		JLabel register_lbl = new JLabel("Register");
		register_lbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		register_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		register_lbl.setBounds(22, 11, 353, 23);
		panel.add(register_lbl);
	}

	private void createLabel(JPanel panel, String s, int x, int y, int width, int height) {
		JLabel label = new JLabel(s);
		label.setBounds(x, y, width, height);
		panel.add(label);
	}

	private void addField(JPanel panel, JTextField jTextField, int x, int y, int width, int height) {
		jTextField.setBounds(x, y, width, height);
		jTextField.setColumns(10);
		panel.add(jTextField);
	}

	private void addPasswordField(JPanel panel, JPasswordField password_field, int x, int y, int width, int height) {
		password_field.setBounds(x, y, width, height);
		panel.add(password_field);
		password_field.setColumns(10);
	}

	private void createUser(){
		if (checkFields() && checkPasswords()) {
			UserData userData = new UserData();
			try {
				int result = userData.addUser(username_field.getText().trim(), firstname_field.getText().trim(),
						lastname_field.getText().trim(), String.valueOf(password_field.getPassword()).trim());
				if ( result == 1){
					switchToLogin();
				}
			} catch (SQLException ex) {
				if (ex.getMessage().contains("Duplicate entry")) {
					error_lbl.setText("");
					error_lbl.setText("username already exists");
				} else {
					ex.printStackTrace();
				}
			}
		}

	}

	private void createSubmitButton(JPanel panel) {
		JButton submit_btn = new JButton("Submit");
		submit_btn.setBackground(Color.GRAY);
		submit_btn.setForeground(Color.WHITE);
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 11));
		submit_btn.setBounds(100, 205, 195, 23);
		panel.add(submit_btn);
		submit_btn.addActionListener(e -> createUser());
	}

	private void switchToLogin() {
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.setVisible(true);
		RegisterWindow.this.dispose();
	}

	private void addErrorLabel(JPanel panel) {
		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(35, 185, 353, 14);
		panel.add(error_lbl);
	}
	private boolean checkPasswords(){
		if (String.valueOf(password_field.getPassword()).equals( String.valueOf(confirm_password_field.getPassword())))
			return true;
		error_lbl.setText("");
		error_lbl.setText("passwords do not match");
		return false;
	}

	/**
	 * checks if all the fields are filled also checks
	 * @return true if all are filled
	 */
	private boolean checkFields() {
		if(username_field.getText().trim().isEmpty() || firstname_field.getText().trim().isEmpty()
				|| lastname_field.getText().trim().isEmpty()
				|| String.valueOf(password_field).trim().isEmpty()
				|| String.valueOf(confirm_password_field).trim().isEmpty()) {

			error_lbl.setText("");
			error_lbl.setText("all fields are required");
			return false;
		}

		return true;
	}
}
