package inf008.ads.ifba.edu.br.logisticsnegotium.session;

import java.util.Collection;

import inf008.ads.ifba.edu.br.logisticsnegotium.dao.DeliveryDAOIF;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.AutomatedVehicles;
import inf008.ads.ifba.edu.br.logisticsnegotium.entities.Point;

public interface AppDeliveryIF{
	public void addAirVehicle(int registration, double capacity, double latitude, double longitude) throws Exception;
	public void addLandVehicle(int registration, double capacity, double latitude, double longitude) throws Exception;
    public Collection<AutomatedVehicles> listVehicles() throws Exception;
	public AutomatedVehicles listPossibleVehicles(double capacity, Point pointReach, Point pointDely) throws Exception;    	
	public void setDeliveryDAOIF(DeliveryDAOIF deliveryDAO);
	public void attNewPosition(AutomatedVehicles av) throws Exception;
}
