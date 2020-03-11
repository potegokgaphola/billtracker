package billtracker.ui.home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import billtracker.model.UserModel;
import billtracker.ui.bill.BillGraphWindow;
import billtracker.ui.bill.AddBillWindow;
import billtracker.ui.bill.ViewBillsWindow;
import com.toedter.calendar.JDateChooser;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomeWindow extends JFrame {

	private JPanel contentPane;
	JRadioButton food_rdbtn = new JRadioButton();
	JRadioButton electricity_rdbtn = new JRadioButton();
	JRadioButton water_rdbtn = new JRadioButton();
	private JDateChooser dateChooser;
	private JDateChooser dateChooser1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				HomeWindow frame = new HomeWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(23, 57, 101, 182);
		contentPane.add(panel);
		panel.setLayout(null);

		createNewBillButton(panel);

		createGetBillButton(panel);

		JPanel menuPanel = getMenuPanel();


		addWelcomeLabel(menuPanel);

		JPanel graphPanel = getGraphPanel();

		addEndDateField(graphPanel);

		addStartDateField(graphPanel);

		final ButtonGroup bgroup = new ButtonGroup();

		addRadioButton(graphPanel, bgroup, "water", water_rdbtn, 11, 73, 16);

		addRadioButton(graphPanel, bgroup, "electricity", electricity_rdbtn,97, 85, 16);

		addRadioButton(graphPanel, bgroup, "food", food_rdbtn,204, 53, 16);

		createShowGraphButton(graphPanel, bgroup);

		createStartDateLabel(graphPanel);

		createEndLabel(graphPanel);
	}

	private JPanel getMenuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.LIGHT_GRAY);
		menuPanel.setBounds(23, 21, 401, 25);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		return menuPanel;
	}

	private JPanel getGraphPanel() {
		JPanel graphPanel = new JPanel();
		graphPanel.setBackground(Color.LIGHT_GRAY);
		graphPanel.setBounds(134, 57, 290, 182);
		contentPane.add(graphPanel);
		graphPanel.setLayout(null);
		return graphPanel;
	}

	private void addEndDateField(JPanel panel_2) {
		dateChooser = new JDateChooser();
		dateChooser.setBounds(130, 58, 127, 20);
		panel_2.add(dateChooser);
	}

	private void addStartDateField(JPanel panel_2) {
		dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(130, 27, 127, 20);
		panel_2.add(dateChooser1);
	}

	private void createEndLabel(JPanel panel_2) {
		JLabel endDate_lbl = new JLabel("End Date");
		endDate_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		endDate_lbl.setForeground(Color.WHITE);
		endDate_lbl.setBounds(10, 58, 74, 20);
		panel_2.add(endDate_lbl);
	}

	private void createStartDateLabel(JPanel panel_2) {
		JLabel startDate_lbl = new JLabel("Start Date");
		startDate_lbl.setForeground(Color.WHITE);
		startDate_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		startDate_lbl.setBounds(10, 27, 74, 20);
		panel_2.add(startDate_lbl);
	}

	private void createShowGraphButton(JPanel panel_2, ButtonGroup bgroup) {
		JButton showGraph_btn = new JButton("show graph ");
		showGraph_btn.setBounds(56, 131, 176, 23);
		panel_2.add(showGraph_btn);
		showGraph_btn.setBackground(Color.LIGHT_GRAY);
		showGraph_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		showGraph_btn.setForeground(Color.WHITE);
		showGraph_btn.addActionListener(e -> {
			//get dates and bill type
			String bill_type = "Electricity";
			String startDate;
			String endDate;
			if(water_rdbtn.isSelected()) {
				bill_type = "Water";
			} else if(food_rdbtn.isSelected()) {
				bill_type = "Food";
			}

			if (dateChooser.getDate() != null && dateChooser1.getDate() != null) {

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				endDate = format.format(dateChooser.getDate());
				startDate = format.format(dateChooser1.getDate());
				dateChooser.setCalendar(null);
				dateChooser1.setCalendar(null);
				bgroup.clearSelection();

				BillGraphWindow billGraphWindow = new BillGraphWindow(bill_type+" Graph", "Progess for " + bill_type.toLowerCase(), bill_type, startDate, endDate);
				billGraphWindow.pack();
				billGraphWindow.setVisible(true);
			}

		});
	}

	private void addRadioButton(JPanel panel_2, ButtonGroup bgroup, String s, JRadioButton jRadioButton, int x, int width, int height) {
		jRadioButton.setText(s);
		jRadioButton.setBackground(Color.LIGHT_GRAY);
		jRadioButton.setForeground(Color.WHITE);
		jRadioButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		jRadioButton.setBounds(x, 92, width, height);
		bgroup.add(jRadioButton);
		panel_2.add(jRadioButton);
	}

	private void addWelcomeLabel(JPanel panel_1) {
		UserModel user = new UserModel();
		JLabel welcome_lbl = new JLabel("Welcome, "+user.getFirstname());
		welcome_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		welcome_lbl.setForeground(Color.WHITE);
		welcome_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		welcome_lbl.setBounds(257, 11, 134, 14);
		panel_1.add(welcome_lbl);
	}

	private void createGetBillButton(JPanel panel) {
		JButton viewBills_btn = new JButton("view Bill");
		viewBills_btn.setBackground(Color.LIGHT_GRAY);
		viewBills_btn.setForeground(Color.WHITE);
		viewBills_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		viewBills_btn.setBounds(10, 71, 84, 23);
		panel.add(viewBills_btn);
		viewBills_btn.addActionListener(e -> {
			ViewBillsWindow veiwBill = new ViewBillsWindow();
			veiwBill.setVisible(true);
			HomeWindow.this.dispose();
		});
	}

	private void createNewBillButton(JPanel panel) {
		JButton addBill_btn = new JButton("add Bill");
		addBill_btn.setBackground(Color.LIGHT_GRAY);
		addBill_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		addBill_btn.setForeground(Color.WHITE);
		addBill_btn.setBounds(10, 49, 84, 23);
		panel.add(addBill_btn);
		addBill_btn.addActionListener(e -> {
			AddBillWindow addBillWindow = new AddBillWindow();
			addBillWindow.setVisible(true);
			HomeWindow.this.dispose();
		});
	}
}
