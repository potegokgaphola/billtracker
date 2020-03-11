package billtracker.ui.bill;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import billtracker.data.bill.BillData;
import billtracker.model.BillModel;
import billtracker.ui.home.HomeWindow;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

public class ViewBillsWindow extends JFrame {

	private JPanel contentPane;
	JLabel electricitySpending= new JLabel("");
	JLabel foodSpending = new JLabel("");
	JLabel waterSpending = new JLabel("");
	JLabel no_eRecord_lbl = new JLabel("No records found");
	JLabel no_wRecord_lbl = new JLabel("No records found");
	JLabel no_fRecord_lbl = new JLabel("No records found");
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewBillsWindow frame = new ViewBillsWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewBillsWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel electricityBillPanel = getElectricityPanel();

		JPanel waterBillPanel = getWaterPanel();

		JPanel foodBillPanel = getFoodPanel();

		noRecordLabel(electricityBillPanel, no_eRecord_lbl);

		noRecordLabel(waterBillPanel, no_wRecord_lbl);

		noRecordLabel(foodBillPanel, no_fRecord_lbl);

		addSpendingLabel(electricityBillPanel, electricitySpending);

		addSpendingLabel(waterBillPanel, waterSpending);

		addSpendingLabel(foodBillPanel, foodSpending);

		JPanel inputPanel = createInputPanel();

		addElectricityLabel(inputPanel, "Select the date of the bills you want", Font.BOLD | Font.ITALIC, 7, 223, 22);

		addDateChooser(inputPanel);

		createShowBillsButton(inputPanel);

		createBillLabel(waterBillPanel, "Water bill");

		createBillLabel(foodBillPanel, "Food bill");

		createBillLabel(electricityBillPanel,"Electricity bill");

		JPanel panel_3 = getPanel3();

		createHomeButton(panel_3);
	}

	private void createHomeButton(JPanel panel_3) {
		JButton home_btn = new JButton("Go to home");
		home_btn.setBackground(Color.LIGHT_GRAY);
		home_btn.setForeground(Color.WHITE);
		home_btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		home_btn.addActionListener(e -> {
			HomeWindow home = new HomeWindow();
			home.setVisible(true);
			ViewBillsWindow.this.dispose();
		});
		home_btn.setBounds(34, 11, 108, 20);
		panel_3.add(home_btn);
	}

	private JPanel getPanel3() {
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(10, 215, 414, 35);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		return panel_3;
	}

	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.LIGHT_GRAY);
		inputPanel.setBounds(10, 11, 414, 74);
		contentPane.add(inputPanel);
		inputPanel.setLayout(null);
		return inputPanel;
	}

	private void createBillLabel(JPanel waterBillPanel, String s) {
		JLabel waterBill_lbl = new JLabel(s);
		waterBill_lbl.setForeground(Color.WHITE);
		waterBill_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		waterBill_lbl.setBounds(10, 11, 97, 14);
		waterBillPanel.add(waterBill_lbl);
	}

	private void createShowBillsButton(JPanel inputPanel) {
		JButton showBills_btn = new JButton("Show Bills");
		showBills_btn.setForeground(Color.WHITE);
		showBills_btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		showBills_btn.setBackground(Color.LIGHT_GRAY);
		showBills_btn.setBounds(94, 40, 223, 23);
		inputPanel.add(showBills_btn);
		showBills_btn.addActionListener(e -> populateBills());
	}

	private void populateBills() {
		if (dateChooser.getDate() != null ) {
			BillData billData = new BillData();
			boolean electricity = false;
			boolean food = false;
			boolean water = false;
			try {
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
				List<Object> bills = billData.getBills(format.format(dateChooser.getDate()));
				for (Object o : bills) {
					BillModel bill = (BillModel) o;
					if (bill.getBillType().equals("Water")) {
						waterSpending.setText("You spent R" + bill.getBillAmount());
						no_wRecord_lbl.setVisible(false);
						water = true;
					}
					if (bill.getBillType().equals("Food")) {
						foodSpending.setText("You spent R" + bill.getBillAmount());
						no_fRecord_lbl.setVisible(false);
						food = true;
					}
					if (bill.getBillType().equals("Electricity")) {
						electricitySpending.setText("You spent R" + bill.getBillAmount());
						no_eRecord_lbl.setVisible(false);
						electricity = true;
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			if (!electricity) {
				electricitySpending.setText("");
				no_eRecord_lbl.setVisible(true);
			}
			if (!food) {
				foodSpending.setText("");
				no_fRecord_lbl.setVisible(true);
			}
			if (!water) {
				waterSpending.setText("");
				no_wRecord_lbl.setVisible(true);
			}
		}
	}

	private void addDateChooser(JPanel input_panel) {
		dateChooser = new JDateChooser();
		dateChooser.setBounds(243, 11, 161, 20);
		input_panel.add(dateChooser);
	}

	private void addSpendingLabel(JPanel electricityBillPanel, JLabel label) {
		label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label.setForeground(Color.WHITE);
		label.setBounds(0, 36, 117, 14);
		electricityBillPanel.add(label);
	}

	private JPanel getFoodPanel() {
		JPanel food_panel = new JPanel();
		food_panel.setBackground(Color.LIGHT_GRAY);
		food_panel.setBounds(307, 96, 117, 100);
		contentPane.add(food_panel);
		food_panel.setLayout(null);
		return food_panel;
	}

	private JPanel getWaterPanel() {
		JPanel waterBill_panel = new JPanel();
		waterBill_panel.setBackground(Color.LIGHT_GRAY);
		waterBill_panel.setBounds(156, 96, 123, 100);
		contentPane.add(waterBill_panel);
		waterBill_panel.setLayout(null);
		return waterBill_panel;
	}

	private void noRecordLabel(JPanel billPanel, JLabel label) {
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label.setBounds(10, 75, 97, 14);
		label.setVisible(false);
		billPanel.add(label);
	}

	private JPanel getElectricityPanel() {
		final JPanel electricityBill_panel = new JPanel();
		electricityBill_panel.setBackground(Color.LIGHT_GRAY);
		electricityBill_panel.setBounds(10, 96, 117, 100);
		contentPane.add(electricityBill_panel);
		electricityBill_panel.setLayout(null);
		return electricityBill_panel;
	}

	private void addElectricityLabel(JPanel electricityBill_panel, String s, int italic, int i, int i2, int i3) {
		JLabel electricity_lbl = new JLabel(s);
		electricity_lbl.setFont(new Font("Tahoma", italic, 11));
		electricity_lbl.setForeground(Color.WHITE);
		electricity_lbl.setBounds(10, i, i2, i3);
		electricityBill_panel.add(electricity_lbl);
	}
}
