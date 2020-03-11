package billtracker.ui.login;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import billtracker.data.user.UserData;
import billtracker.ui.home.HomeWindow;

import java.sql.SQLException;
import java.util.List;

public class LoginWindow extends JFrame {

	private JTextField username_field;
	private JPasswordField password_field = new JPasswordField();
	JLabel error_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginWindow frame = new LoginWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel loginPanel = getLoginPanel();

		JLabel userIcon = getUserIcon();

		JLabel passwordIcon = getPasswordIcon();

		username_field = new JTextField();
		username_field.setColumns(10);

		JButton login_btn = getLoginButton();

		JButton register_btn = getRegisterButton();

		addErrorLabel();

		JPanel headingPanel = getHeading();

		createHeading(headingPanel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 233, 414, 17);
		panel.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_login_panel = new GroupLayout(loginPanel);
		gl_login_panel.setHorizontalGroup(
			gl_login_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(180)
					.addComponent(userIcon)
					.addGap(30)
					.addComponent(username_field, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(180)
					.addComponent(passwordIcon)
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
			gl_login_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_login_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_login_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(userIcon)
						.addGroup(gl_login_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(username_field)))
					.addGap(11)
					.addGroup(gl_login_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(passwordIcon)
						.addGroup(gl_login_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(password_field)))
					.addGap(6)
					.addComponent(error_lbl, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_login_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(register_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(login_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(16))
		);
		loginPanel.setLayout(gl_login_panel);
		contentPane.setLayout(null);
		contentPane.add(loginPanel);
		contentPane.add(panel);
		contentPane.add(headingPanel);


	}

	private void createHeading(JPanel headingPanel) {
		JLabel heading = new JLabel("Bill Tracker");
		heading.setBounds(73, 11, 201, 24);
		heading.setFont(new Font("Tahoma", Font.BOLD, 18));
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		headingPanel.add(heading);
	}

	private JPanel getHeading() {
		JPanel headingPanel = new JPanel();
		headingPanel.setBounds(10, 11, 414, 35);
		headingPanel.setBackground(Color.LIGHT_GRAY);
		headingPanel.setLayout(null);
		return headingPanel;
	}

	private void addErrorLabel() {
		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		error_lbl.setForeground(Color.RED);
	}

	private JButton getLoginButton() {
		JButton login_btn = new JButton("Login");
		login_btn.setForeground(Color.WHITE);
		login_btn.setBackground(Color.LIGHT_GRAY);
		login_btn.addActionListener(e -> {
			// TODO Auto-generated method stub

			if (checkFields()) {
				UserData userData = new UserData();
				try {
					List<Object> user = userData.getUser(username_field.getText().trim(), String.valueOf(password_field.getPassword()).trim());
					if (user.size() == 1){
						switchToHome();
					} else {
						error_lbl.setText("");
						error_lbl.setText("Invalid username or password");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
				error_lbl.setText("");
				error_lbl.setText("Fill all fields");
			}
		});
		return login_btn;
	}

	private void switchToHome() {
		HomeWindow homeWindow = new HomeWindow();
		homeWindow.setVisible(true);
		LoginWindow.this.dispose();
	}

	private JLabel getPasswordIcon() {
		JLabel passwordIcon = new JLabel("");
		Image passwordImg = new ImageIcon( getClass().getClassLoader().getResource("icons/password.png") ).getImage();
		passwordIcon.setIcon(new ImageIcon(passwordImg));
		return passwordIcon;
	}

	private JLabel getUserIcon() {
		JLabel userIcon = new JLabel("");
		Image userImg = new ImageIcon( getClass().getClassLoader().getResource("icons/user.png") ).getImage();
		userIcon.setIcon(new ImageIcon(userImg));
		return userIcon;
	}

	private JPanel getLoginPanel() {
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(10, 57, 414, 156);
		loginPanel.setBackground(Color.LIGHT_GRAY);
		return loginPanel;
	}

	private JButton getRegisterButton() {
		JButton register_btn = new JButton("Register");
		register_btn.setBackground(Color.LIGHT_GRAY);
		register_btn.setForeground(Color.WHITE);
		register_btn.addActionListener(e -> {
			// Go to the register paged
			RegisterWindow registerWindow = new RegisterWindow();
			registerWindow.setVisible(true);
			LoginWindow.this.dispose();
		});
		return register_btn;
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
