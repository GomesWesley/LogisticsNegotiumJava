package inf008.ads.ifba.edu.br.logisticsnegotium.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import inf008.ads.ifba.edu.br.logisticsnegotium.entities.AirVehicle;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.AutomatedVehicles;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.LandVehicle;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.Point;

public class DeliverySQLDAO implements DeliveryDAOIF {
	private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver";
	private static final String DB_URI = "jdbc:hsqldb:hsql://localhost/";
	private static final String DB_USER = "SA";
	private static final String DB_PWD = "";
	private static final String SAVE = "INSERT INTO AUTOMATED_VEHICLES(id, capacity, latitude, longitude, isAirVehicle) VALUES(?, ?, ?, ?, ?)";
	private static final String FIND_ALL = "SELECT id, capacity, latitude, longitude, isAirVehicle FROM AUTOMATED_VEHICLES";
	private static final String UPDATE_POSITION = "UPDATE AUTOMATED_VEHICLES SET latitude = ?, longitude = ? WHERE id = ?";
	
	
	
	
	
	/*public static void main(String[] args) throws SQLException, ClassNotFoundException {
		(new DeliverySQLDAO()).run();
		System.out.println("Finalizando...");
	}
	
	
	public void run() throws SQLException, ClassNotFoundException {
		DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO AUTOMATED_VEHICLES(id, capacity, latitude, longitude, isAirVehicle) VALUES(?, ?, ?, ?, ?)");
		stmt.setInt(1, 3);
		stmt.setDouble(2, 2);
		stmt.setDouble(3, 2);
		stmt.setDouble(4, 4);
		stmt.setBoolean(5, false);
		int linhas = stmt.executeUpdate();
		System.out.println("Linhas afetadas: " + linhas);
		stmt.close();
		conn.close();
	}*/
	
	
	
	
	
	
	public DeliverySQLDAO() throws SQLException, ClassNotFoundException {
		Class.forName(DeliverySQLDAO.DRIVER_NAME);
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DeliverySQLDAO.DB_URI, 
				DeliverySQLDAO.DB_USER, DeliverySQLDAO.DB_PWD);
	}
	
	
	@Override
	public void save(AutomatedVehicles av, Point point) throws Exception {
		PreparedStatement stmt = this.getConnection().prepareStatement(DeliverySQLDAO.SAVE);
		stmt.setInt(1, av.getId());
		stmt.setDouble(2, av.getCapacity());
		stmt.setDouble(3, point.getLatitude());
		stmt.setDouble(4, point.getLongitude());
		if (av.getClass().equals(AirVehicle.class))
			stmt.setBoolean(5, true);
		else
			stmt.setBoolean(5, false);
		stmt.executeUpdate();
		stmt.close();
	}
	
	@Override
	public Collection<AutomatedVehicles> findAll() throws Exception {
		Collection<AutomatedVehicles> avAll = new ArrayList<AutomatedVehicles>();
		PreparedStatement stmt = this.getConnection().prepareStatement(DeliverySQLDAO.FIND_ALL);
		ResultSet rSet = stmt.executeQuery();
		
		while(rSet.next()) {
			if(rSet.getBoolean("isAirVehicle")) {
				AutomatedVehicles av = new AirVehicle(rSet.getInt("id"),
													  rSet.getDouble("capacity"),
											  		  rSet.getDouble("latitude"), 
													  rSet.getDouble("longitude"));
				avAll.add(av);
			}else {
				AutomatedVehicles av = new LandVehicle(rSet.getInt("id"),
													  rSet.getDouble("capacity"),
											  		  rSet.getDouble("latitude"), 
													  rSet.getDouble("longitude"));
				avAll.add(av);
			}	
		}	
		
		rSet.close();
		stmt.close();
		
		return avAll;
	}
	
	@Override
	public List<AutomatedVehicles> findByCapacity(double capacity) throws Exception {
		List<AutomatedVehicles> avByCap = new ArrayList<AutomatedVehicles>();
		PreparedStatement stmt = this.getConnection().prepareStatement(DeliverySQLDAO.FIND_ALL);
		ResultSet rSet = stmt.executeQuery();
		
		while(rSet.next()) {
				if(rSet.getBoolean("isAirVehicle")) {
					AutomatedVehicles av = new AirVehicle(rSet.getInt("id"),
														  rSet.getDouble("capacity"),
												  		  rSet.getDouble("latitude"), 
														  rSet.getDouble("longitude"));
					if(av.getCapacity() >= capacity)
						avByCap.add(av);
				}else {
					AutomatedVehicles av = new LandVehicle(rSet.getInt("id"),
														  rSet.getDouble("capacity"),
												  		  rSet.getDouble("latitude"), 
														  rSet.getDouble("longitude"));
					if(av.getCapacity() >= capacity)
						avByCap.add(av);
				}	
		}	

		rSet.close();
		stmt.close();
		
		return avByCap;
	}

	@Override
	public void updatePosition(AutomatedVehicles av) throws Exception {
		PreparedStatement stmt = this.getConnection().prepareStatement(DeliverySQLDAO.UPDATE_POSITION);
		stmt.setDouble(1, av.getPoint().getLatitude());
		stmt.setDouble(2, av.getPoint().getLongitude());
		stmt.setInt(3, av.getId());
	
		stmt.executeUpdate();
		stmt.close();
	}
}
