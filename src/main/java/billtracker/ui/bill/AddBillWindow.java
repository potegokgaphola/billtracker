package billtracker.ui.bill;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.data.bill.BillData;
import billtracker.ui.home.HomeWindow;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.Color;

public class AddBillWindow extends JFrame {

	private JTextField amount_field;
	private JRadioButton water_rdbtn = new JRadioButton();
	private JRadioButton electricity_rdbtn = new JRadioButton();
	private JRadioButton food_rdbtn = new JRadioButton();
	private JLabel message_lbl;
	private JDateChooser dateChooser;
	final ButtonGroup bgroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AddBillWindow frame = new AddBillWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddBillWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel addBill_panel = addBillPanel(10, 11, 414, 239, contentPane);

		createBillLabel(addBill_panel);

		createAmountLabel(addBill_panel);

		createAmountField(addBill_panel);

		createDateLabel(addBill_panel);

		createDateField(addBill_panel);

		createBillTypeLabel(addBill_panel, "Type", 93, 46, 27);

		JPanel rgroup_panel = addBillPanel(85, 93, 282, 27, addBill_panel);

		ButtonGroup bgroup = new ButtonGroup();

		addWaterRadioButton(rgroup_panel, bgroup);

		addElectricityRadioButton(rgroup_panel, bgroup);

		addFoodRadioButton(rgroup_panel, bgroup);

		addBackButton(addBill_panel);

		createSubmitButton(addBill_panel);

		addMessageLabel(addBill_panel);
	}

	private void createSubmitButton(final JPanel addBill_panel) {
		JButton submit_btn = new JButton("Submit");
		submit_btn.setBackground(Color.LIGHT_GRAY);
		submit_btn.setForeground(Color.WHITE);
		submit_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		submit_btn.setBounds(207, 184, 123, 23);
		addBill_panel.add(submit_btn);
		submit_btn.addActionListener(e -> createBill());
	}

	private void createBill() {
		if (checkFields()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
			String billDate = simpleDateFormat.format(dateChooser.getDate());
			int billAmount = Integer.parseInt(amount_field.getText());
			String billType = "Electricity";
			if (water_rdbtn.isSelected()) {
				billType = "Water";
			} else if (food_rdbtn.isSelected()) {
				billType = "Food";
			}

			BillData billData = new BillData();
			try {
				int result = billData.addBill(billAmount, billDate, billType);
				if (result == 1) {
					bgroup.clearSelection();
					amount_field.setText("");
					dateChooser.setCalendar(null);
					message_lbl.setForeground(Color.WHITE);
					message_lbl.setText("");
					message_lbl.setText("bill created");
				}
			} catch (SQLException e) {
				if (e.getMessage().contains("Duplicate entry")) {
					message_lbl.setForeground(Color.RED);
					message_lbl.setText("");
					message_lbl.setText("you already have a " + billType.toLowerCase() + " bill for this day");
				} else {
					e.printStackTrace();
				}
			}
		}
	}

	private void createBillTypeLabel(JPanel addBill_panel, String type, int i, int i2, int i3) {
		JLabel type_lbl = new JLabel(type);
		type_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		type_lbl.setForeground(Color.WHITE);
		type_lbl.setBounds(10, i, i2, i3);
		addBill_panel.add(type_lbl);
	}

	private void createDateField(JPanel addBill_panel) {
		dateChooser = new JDateChooser();
		dateChooser.setBounds(163, 62, 204, 20);
		addBill_panel.add(dateChooser);
	}

	private void createDateLabel(JPanel addBill_panel) {
		createBillTypeLabel(addBill_panel, "Date", 62, 116, 20);
	}

	private void createAmountField(JPanel addBill_panel) {
		amount_field = new JTextField();
		amount_field.setBounds(163, 31, 204, 20);
		addBill_panel.add(amount_field);
		amount_field.setColumns(10);
	}

	private void createAmountLabel(JPanel addBill_panel) {
		JLabel amount_lbl = new JLabel("Amount");
		amount_lbl.setForeground(Color.WHITE);
		amount_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		amount_lbl.setBounds(10, 31, 116, 20);
		addBill_panel.add(amount_lbl);
	}

	private void createBillLabel(JPanel addBill_panel) {
		JLabel bill_lbl = new JLabel("Add a New  Bill");
		bill_lbl.setForeground(Color.WHITE);
		bill_lbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		bill_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		bill_lbl.setBounds(10, 0, 357, 20);
		addBill_panel.add(bill_lbl);
	}

	private JPanel addBillPanel(int i, int i2, int i3, int i4, JPanel contentPane) {
		JPanel addBill_panel = new JPanel();
		addBill_panel.setBackground(Color.LIGHT_GRAY);
		addBill_panel.setBounds(i, i2, i3, i4);
		contentPane.add(addBill_panel);
		addBill_panel.setLayout(null);
		return addBill_panel;
	}

	private void addMessageLabel(JPanel createBill_panel) {
		message_lbl = new JLabel("");
		message_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		message_lbl.setForeground(Color.WHITE);
		message_lbl.setBounds(59, 148, 248, 14);
		createBill_panel.add(message_lbl);
	}

	private void addBackButton(JPanel createBill_panel) {
		JButton back_btn = new JButton("Go to Home");
		back_btn.setBackground(Color.LIGHT_GRAY);
		back_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		back_btn.setForeground(Color.WHITE);
		back_btn.addActionListener(e -> {
			HomeWindow homeWindow = new HomeWindow();
			homeWindow.setVisible(true);
			AddBillWindow.this.dispose();
		});
		back_btn.setBounds(37, 184, 116, 23);
		createBill_panel.add(back_btn);
	}

	private void addRadioButton(JPanel rgroup, ButtonGroup bgroup, String bill, int x, int y, int width, int height) {
		JRadioButton jRadioButton = new JRadioButton(bill);
		jRadioButton.setBackground(Color.LIGHT_GRAY);
		jRadioButton.setForeground(Color.WHITE);
		jRadioButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		jRadioButton.setBounds(x , y, width, height);
		rgroup.add(jRadioButton);
		bgroup.add(jRadioButton);
	}

	private void addWaterRadioButton(JPanel rgroup, ButtonGroup bgroup) {
		water_rdbtn.setText("water");
		water_rdbtn.setBackground(Color.LIGHT_GRAY);
		water_rdbtn.setForeground(Color.WHITE);
		water_rdbtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		water_rdbtn.setBounds( 6, 7, 73, 16);
		rgroup.add(water_rdbtn);
		this.bgroup.add(water_rdbtn);
	}

	private void addFoodRadioButton(JPanel rgroup, ButtonGroup bgroup) {
		food_rdbtn.setText("food");
		food_rdbtn.setBackground(Color.LIGHT_GRAY);
		food_rdbtn.setForeground(Color.WHITE);
		food_rdbtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		food_rdbtn.setBounds(198, 7, 56, 16);
		rgroup.add(food_rdbtn);
		this.bgroup.add(food_rdbtn);
	}

	private void addElectricityRadioButton(JPanel rgroup, ButtonGroup bgroup) {
		electricity_rdbtn.setText("electricity");
		electricity_rdbtn.setBackground(Color.LIGHT_GRAY);
		electricity_rdbtn.setForeground(Color.WHITE);
		electricity_rdbtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		electricity_rdbtn.setBounds( 97, 7, 85, 16);
		rgroup.add(electricity_rdbtn);
		this.bgroup.add(electricity_rdbtn);
	}

	/**
	 * checks if all fields are filled
	 * @return true if filled
	 */
	public boolean checkFields() {
		if (!amount_field.getText().isEmpty() || dateChooser.getDate() != null 
				|| water_rdbtn.isSelected() 
				|| food_rdbtn.isSelected() 
				|| electricity_rdbtn.isSelected()) {
			return true;
		}
		
		message_lbl.setForeground(Color.RED);
		message_lbl.setText("");
		message_lbl.setText("All fields are required");
		return false;
	}
}
