package inf008.ads.ifba.edu.br.logisticsnegotium.session;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import inf008.ads.ifba.edu.br.logisticsnegotium.dao.DeliveryDAOIF;
import inf008.ads.ifba.edu.br.logisticsnegotium.dao.DeliverySQLDAO;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.AirVehicle;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.AutomatedVehicles;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.LandVehicle;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.Point;

public class AppDelivery implements AppDeliveryIF{
    private Point pointRech;
    private Point pointDely;
    private DeliveryDAOIF deliveryDAO;

    public AppDelivery(Point pointRech, Point pointDely) throws ClassNotFoundException, SQLException{
        this.setPointRech(pointRech);
        this.setPointDely(pointDely);
        this.setDeliveryDAOIF(new DeliverySQLDAO());
    }


    public Point getPointRech() {
        return pointRech;
    }

    private void setPointRech(Point pointRech) {
        this.pointRech = pointRech;
    }

    public Point getPointDely() {
        return pointDely;
    }

    private void setPointDely(Point pointDely) {
        this.pointDely = pointDely;
    }


	/*@Override
	public void addDelivery(int registration, Point reach, Point dely, double price) throws Exception {
		// TODO Auto-generated method stub
		
	}*/


	@Override
	public void addAirVehicle(int registration, double capacity, double latitude, double longitude) throws Exception {
		AirVehicle av = new AirVehicle(registration, capacity, latitude, longitude);
		Point point = new Point(latitude, longitude);
		
		this.deliveryDAO.save(av, point);
	}
	
	public void addLandVehicle(int registration, double capacity, double latitude, double longitude) throws Exception {
		LandVehicle av = new LandVehicle(registration, capacity, latitude, longitude);
		Point point = new Point(latitude, longitude);
		
		this.deliveryDAO.save(av, point);
	}

	@Override
	public Collection<AutomatedVehicles> listVehicles() throws Exception {
		Collection<AutomatedVehicles> avAll = this.deliveryDAO.findAll();
		  
    	return avAll;
	}


	@Override
	public AutomatedVehicles listPossibleVehicles(double capacity, Point pointReach, Point pointDely) throws Exception {
		List<AutomatedVehicles> avPossible = this.deliveryDAO.findByCapacity(capacity);
		AutomatedVehicles theAv = null;
		double distance = 0;
		double price = 0;
		double finalP = 0;

		distance = avPossible.get(0).calculateDistance(pointReach, pointDely);
		finalP = avPossible.get(0).calculatePrice(distance, capacity);
		
		for(AutomatedVehicles av : avPossible) {
			distance = av.calculateDistance(pointReach, pointDely);
			price = av.calculatePrice(distance, capacity);
			if(finalP > price) {
				finalP = price;
				theAv = av;
			}
		}


    	return theAv;
	}


	@Override
	public void setDeliveryDAOIF(DeliveryDAOIF deliveryDAO) {
		this.deliveryDAO = deliveryDAO;
	}


	@Override
	public void attNewPosition(AutomatedVehicles av) throws Exception {
		this.deliveryDAO.updatePosition(av);
	}

}
