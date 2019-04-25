package inf008.ads.ifba.edu.br.logisticsnegotium.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class JLogisticsNegotiumFrame {

	private JFrame frmLogisticsNegotium;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogisticsNegotiumFrame window = new JLogisticsNegotiumFrame();
					window.frmLogisticsNegotium.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JLogisticsNegotiumFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogisticsNegotium = new JFrame();
		frmLogisticsNegotium.setTitle("Logistics Negotium");
		frmLogisticsNegotium.setBounds(100, 100, 450, 300);
		frmLogisticsNegotium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogisticsNegotium.getContentPane().setLayout(null);
		
		JButton btnAddVehicle = new JButton("Add Vehicle");
		btnAddVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JAddVehicleFrame.main();
			}
		});
		btnAddVehicle.setBounds(44, 198, 134, 23);
		frmLogisticsNegotium.getContentPane().add(btnAddVehicle);
		
		JButton btnRequestDelivery = new JButton("Request Delivery");
		btnRequestDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRequestDeliveryFrame.main();
			}
		});
		btnRequestDelivery.setBounds(258, 198, 134, 23);
		frmLogisticsNegotium.getContentPane().add(btnRequestDelivery);
		
		JLabel lblChooseBetweenThe = new JLabel("Choose between the options");
		lblChooseBetweenThe.setBounds(143, 100, 181, 14);
		frmLogisticsNegotium.getContentPane().add(lblChooseBetweenThe);
	}
}
