package billtracker.home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import billtracker.login.CurrentUser;
import billtracker.login.User;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	private JPanel contentPane;
	JRadioButton food_rdbtn;
	JRadioButton electricity_rdbtn;
	JRadioButton water_rdbtn;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
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
		
		JButton addBill_btn = new JButton("add Bill");
		addBill_btn.setBackground(Color.LIGHT_GRAY);
		addBill_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		addBill_btn.setForeground(Color.WHITE);
		addBill_btn.setBounds(10, 49, 84, 23);
		panel.add(addBill_btn);
		
		JButton viewBills_btn = new JButton("view Bill");
		viewBills_btn.setBackground(Color.LIGHT_GRAY);
		viewBills_btn.setForeground(Color.WHITE);
		viewBills_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		viewBills_btn.setBounds(10, 71, 84, 23);
		panel.add(viewBills_btn);
		viewBills_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBills veiwBill = new ViewBills();
				veiwBill.setVisible(true);
				Home.this.dispose();
			}
		});
		addBill_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBill addBill = new AddBill();
				addBill.setVisible(true);
				Home.this.dispose();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(23, 21, 401, 25);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		User user = new CurrentUser();
		JLabel welcome_lbl = new JLabel("Welcome, "+user.getFirstname());
		welcome_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		welcome_lbl.setForeground(Color.WHITE);
		welcome_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		welcome_lbl.setBounds(257, 11, 134, 14);
		panel_1.add(welcome_lbl);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(134, 57, 290, 182);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(130, 58, 127, 20);
		panel_2.add(dateChooser);
		
		dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(130, 27, 127, 20);
		panel_2.add(dateChooser1);
		
		final ButtonGroup bgroup = new ButtonGroup();
		
		water_rdbtn = new JRadioButton("water");
		water_rdbtn.setBackground(Color.LIGHT_GRAY);
		water_rdbtn.setForeground(Color.WHITE);
		water_rdbtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		water_rdbtn.setBounds(11, 92, 73, 16);
		bgroup.add(water_rdbtn);
		panel_2.add(water_rdbtn);
		
		electricity_rdbtn = new JRadioButton("Electricity");
		electricity_rdbtn.setBackground(Color.LIGHT_GRAY);
		electricity_rdbtn.setForeground(Color.WHITE);
		electricity_rdbtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		electricity_rdbtn.setBounds(97, 92, 85, 16);
		bgroup.add(electricity_rdbtn);
		panel_2.add(electricity_rdbtn);
		
		food_rdbtn = new JRadioButton("Food");
		food_rdbtn.setBackground(Color.LIGHT_GRAY);
		food_rdbtn.setForeground(Color.WHITE);
		food_rdbtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		food_rdbtn.setBounds(204, 92, 53, 16);
		bgroup.add(food_rdbtn);
		panel_2.add(food_rdbtn);
		
		JButton showGraph_btn = new JButton("show graph ");
		showGraph_btn.setBounds(56, 131, 176, 23);
		panel_2.add(showGraph_btn);
		showGraph_btn.setBackground(Color.LIGHT_GRAY);
		showGraph_btn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		showGraph_btn.setForeground(Color.WHITE);
		showGraph_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get dates and bill type
				String bill_type = null;
				String startDate = null;
				String endDate = null;
				if(water_rdbtn.isSelected()) {
					bill_type = "Water";
				} else if(food_rdbtn.isSelected()) {
					bill_type = "Food";
				} else {
					bill_type = "Electricity";
				}
				
				if (dateChooser.getDate() != null && dateChooser1.getDate() != null) {
					
					SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
					endDate = format.format(dateChooser.getDate());
					startDate = format.format(dateChooser1.getDate());
					dateChooser.setCalendar(null);
					dateChooser1.setCalendar(null);
					bgroup.clearSelection();
					
					BillGraph billGraph = new BillGraph(bill_type+" Graph", "Progess for " + bill_type.toLowerCase(), bill_type, startDate, endDate);
					billGraph.pack();
					billGraph.setVisible(true);
				}
				
			}
		});
		
		JLabel startDate_lbl = new JLabel("Start Date");
		startDate_lbl.setForeground(Color.WHITE);
		startDate_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		startDate_lbl.setBounds(10, 27, 74, 20);
		panel_2.add(startDate_lbl);
		
		JLabel endDate_lbl = new JLabel("End Date");
		endDate_lbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		endDate_lbl.setForeground(Color.WHITE);
		endDate_lbl.setBounds(10, 58, 74, 20);
		panel_2.add(endDate_lbl);
	}
}
