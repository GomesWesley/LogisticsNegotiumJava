package inf008.ads.ifba.edu.br.logisticsnegotium.ui;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import inf008.ads.ifba.edu.br.logisticsnegotium.session.AppDelivery;
import inf008.ads.ifba.edu.br.logisticsnegotium.session.AppDeliveryIF;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.AutomatedVehicles;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JRequestDeliveryFrame implements LogisticsNegotiumUIIF {

	private JFrame frmRequestDelivery;
	private JTextField latitudeReach;
	private JTextField longitudeReach;
	private JTextField weight;
	private JTextField latitudeDely;
	private JTextField longitudeDely;
	private AppDeliveryIF app;
	
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRequestDeliveryFrame window = new JRequestDeliveryFrame();
					window.frmRequestDelivery.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void setLogica(AppDeliveryIF appDelivery) {
		this.app = appDelivery;
		
	}
	
	public JRequestDeliveryFrame() throws ClassNotFoundException, SQLException {
		setLogica(new AppDelivery(null, null));
		initialize();
	}


	private void initialize() {
		frmRequestDelivery = new JFrame();
		frmRequestDelivery.setTitle("Request Delivery");
		frmRequestDelivery.setBounds(100, 100, 450, 300);
		frmRequestDelivery.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRequestDelivery.getContentPane().setLayout(null);
		
		JLabel lblFillTheData = new JLabel("Fill the data");
		lblFillTheData.setBounds(175, 28, 81, 14);
		frmRequestDelivery.getContentPane().add(lblFillTheData);
		
		JLabel lblPointOfRecharge = new JLabel("Point of recharge:");
		lblPointOfRecharge.setBounds(10, 67, 92, 14);
		frmRequestDelivery.getContentPane().add(lblPointOfRecharge);
		
		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setBounds(20, 92, 72, 14);
		frmRequestDelivery.getContentPane().add(lblLatitude);
		
		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setBounds(20, 115, 72, 14);
		frmRequestDelivery.getContentPane().add(lblLongitude);
		
		latitudeReach = new JTextField();
		latitudeReach.setBounds(87, 89, 96, 20);
		frmRequestDelivery.getContentPane().add(latitudeReach);
		latitudeReach.setColumns(10);
		
		longitudeReach = new JTextField();
		longitudeReach.setBounds(87, 112, 96, 20);
		frmRequestDelivery.getContentPane().add(longitudeReach);
		longitudeReach.setColumns(10);
		
		JLabel lblPointOfDelivery = new JLabel("Point of delivery:");
		lblPointOfDelivery.setBounds(229, 67, 101, 14);
		frmRequestDelivery.getContentPane().add(lblPointOfDelivery);
		
		JLabel lblLatitude_1 = new JLabel("Latitude:");
		lblLatitude_1.setBounds(239, 92, 72, 14);
		frmRequestDelivery.getContentPane().add(lblLatitude_1);
		
		JLabel lblLongitude_1 = new JLabel("Longitude:");
		lblLongitude_1.setBounds(239, 115, 72, 14);
		frmRequestDelivery.getContentPane().add(lblLongitude_1);
		
		weight = new JTextField();
		weight.setBounds(59, 152, 96, 20);
		frmRequestDelivery.getContentPane().add(weight);
		weight.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Weight:");
		lblNewLabel.setBounds(10, 155, 48, 14);
		frmRequestDelivery.getContentPane().add(lblNewLabel);
		
		latitudeDely = new JTextField();
		latitudeDely.setBounds(302, 89, 96, 20);
		frmRequestDelivery.getContentPane().add(latitudeDely);
		latitudeDely.setColumns(10);
		
		longitudeDely = new JTextField();
		longitudeDely.setBounds(302, 112, 96, 20);
		frmRequestDelivery.getContentPane().add(longitudeDely);
		longitudeDely.setColumns(10);
		
		JButton btnRequestDeli = new JButton("To get a quote ");
		btnRequestDeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutomatedVehicles av;
				try {
					av = app.listPossibleVehicles(Double.parseDouble(weight.getText()),
												  new Point(Double.parseDouble(latitudeReach.getText()), 
												      Double.parseDouble(longitudeReach.getText())),
											      new Point(Double.parseDouble(latitudeDely.getText()), 
												      Double.parseDouble(longitudeDely.getText()))
												 );
					
					
					Double distance = av.calculateDistance(new Point(Double.parseDouble(latitudeReach.getText()), 
															      Double.parseDouble(longitudeReach.getText())),
														new Point(Double.parseDouble(latitudeDely.getText()), 
															      Double.parseDouble(longitudeDely.getText())));
					
					Double price = av.calculatePrice(distance, Double.parseDouble(weight.getText()));
					String result = String.format("%.2f", price);
					
					int input =JOptionPane.showConfirmDialog(null,"Id Vehicle: " + av.getId() + "\nPrice: $" + result, "Confirm Delivery", JOptionPane.INFORMATION_MESSAGE);
					if(input == JOptionPane.OK_OPTION)
					{
					   av.movePosition(new Point(Double.parseDouble(latitudeDely.getText()), 
										         Double.parseDouble(longitudeDely.getText())));
					   app.attNewPosition(av);
					}
					
						frmRequestDelivery.dispose();
					
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRequestDeli.setBounds(159, 206, 130, 23);
		frmRequestDelivery.getContentPane().add(btnRequestDeli);
	}
	
}
