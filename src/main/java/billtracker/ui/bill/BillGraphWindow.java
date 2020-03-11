package billtracker.ui.bill;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import billtracker.data.bill.BillData;
import billtracker.model.BillModel;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.ChartFactor
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BillGraphWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BillGraphWindow frame = new BillGraphWindow("" ,"" , "", "", "");
				frame.pack();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BillGraphWindow(String applicationTitle, String chartTitle, String billType, String startDate, String endDate) {
		super(applicationTitle);
		JFreeChart lineChart = null;
		try {
			lineChart = ChartFactory.createLineChart(chartTitle,
					"Date", "Bill Amount",
					createDataset(startDate, endDate, billType),
					PlotOrientation.VERTICAL,
					true, true, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane( chartPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private DefaultCategoryDataset createDataset(String startDate, String endDate, String billType) throws SQLException {

	      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		  BillData billData = new BillData();
	      List<Object> graphData = billData.getGraphData(startDate, endDate, billType);

		for (Object graphDatum : graphData) {
			BillModel bill = (BillModel) graphDatum;
			dataset.addValue(bill.getBillAmount(), billType.toLowerCase(), bill.getDate());
		}

	    return dataset;
	}

}
