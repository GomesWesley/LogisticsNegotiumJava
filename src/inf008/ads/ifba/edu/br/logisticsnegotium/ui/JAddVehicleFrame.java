package inf008.ads.ifba.edu.br.logisticsnegotium.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import inf008.ads.ifba.edu.br.logisticsnegotium.session.AppDelivery;
import inf008.ads.ifba.edu.br.logisticsnegotium.session.AppDeliveryIF;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class JAddVehicleFrame implements LogisticsNegotiumUIIF {

	private JFrame frmAddVehicle;
	private JTextField Registration;
	private JTextField Capacity;
	private JTextField Latitude;
	private JTextField Longitude;
	private AppDeliveryIF app;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAddVehicleFrame window = new JAddVehicleFrame();
					window.frmAddVehicle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void setLogica(AppDeliveryIF appDelivery) {
		this.app = appDelivery;
	}

	public JAddVehicleFrame() throws ClassNotFoundException, SQLException {
		setLogica(new AppDelivery(null, null));
		initialize();
	}

	
	private void initialize() {
		frmAddVehicle = new JFrame();
		frmAddVehicle.setTitle("Add Vehicle");
		frmAddVehicle.setBounds(100, 100, 450, 300);
		frmAddVehicle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddVehicle.getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("Registration:");
		lblRegistration.setBounds(10, 95, 70, 14);
		frmAddVehicle.getContentPane().add(lblRegistration);
		
		JLabel lblNewLabel = new JLabel("Latitude:");
		lblNewLabel.setBounds(10, 120, 70, 14);
		frmAddVehicle.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Longitude:");
		lblNewLabel_1.setBounds(10, 145, 70, 14);
		frmAddVehicle.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Capacity:");
		lblNewLabel_2.setBounds(10, 170, 70, 14);
		frmAddVehicle.getContentPane().add(lblNewLabel_2);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(262, 92, 48, 14);
		frmAddVehicle.getContentPane().add(lblType);
		
		Registration = new JTextField();
		Registration.setBounds(77, 92, 108, 20);
		frmAddVehicle.getContentPane().add(Registration);
		Registration.setColumns(10);
		
		Capacity = new JTextField();
		Capacity.setBounds(77, 167, 108, 20);
		frmAddVehicle.getContentPane().add(Capacity);
		Capacity.setColumns(10);
		
		Latitude = new JTextField();
		Latitude.setBounds(77, 117, 108, 20);
		frmAddVehicle.getContentPane().add(Latitude);
		Latitude.setColumns(10);
		
		Longitude = new JTextField();
		Longitude.setBounds(77, 142, 108, 20);
		frmAddVehicle.getContentPane().add(Longitude);
		Longitude.setColumns(10);
		
		JRadioButton rdbtnAirVehicle = new JRadioButton("Air Vehicle");
		buttonGroup.add(rdbtnAirVehicle);
		rdbtnAirVehicle.setBounds(299, 91, 109, 23);
		frmAddVehicle.getContentPane().add(rdbtnAirVehicle);
		
		JRadioButton rdbtnLandVehicle = new JRadioButton("Land Vehicle");
		buttonGroup.add(rdbtnLandVehicle);
		rdbtnLandVehicle.setBounds(299, 116, 109, 23);
		frmAddVehicle.getContentPane().add(rdbtnLandVehicle);
		
		JButton btnAddVehicle = new JButton("Add Vehicle");
		btnAddVehicle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(rdbtnAirVehicle.isSelected()) {
					try {
						app.addAirVehicle(Integer.parseInt(Registration.getText()),	
										  Double.parseDouble(Capacity.getText()), 
										  Double.parseDouble(Latitude.getText()), 
										  Double.parseDouble(Longitude.getText()));
						frmAddVehicle.dispose();
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
					try {
						app.addLandVehicle(Integer.parseInt(Registration.getText()),	
										  Double.parseDouble(Capacity.getText()), 
										  Double.parseDouble(Latitude.getText()), 
										  Double.parseDouble(Longitude.getText()));
						frmAddVehicle.dispose();
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnAddVehicle.setBounds(166, 212, 108, 23);
		frmAddVehicle.getContentPane().add(btnAddVehicle);
		
		JLabel lblFillTheData = new JLabel("Fill the data");
		lblFillTheData.setBounds(183, 32, 91, 14);
		frmAddVehicle.getContentPane().add(lblFillTheData);
	}
}
