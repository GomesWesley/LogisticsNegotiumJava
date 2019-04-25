package inf008.ads.ifba.edu.br.logisticsnegotium.entities;

public class AirVehicle extends AutomatedVehicles {

    public AirVehicle(int registration, double capacity, double latitude, double longitude) {
        super(registration, capacity, latitude, longitude);
    }

    @Override
    public double calculateDistance(Point pointRech, Point pointDely) {
        Point point = this.getPoint();
        double result;
        result = Math.sqrt( Math.pow ((Math.abs(point.getLatitude() - pointRech.getLatitude()) +
                            Math.abs(point.getLongitude() - pointRech.getLongitude())), 2) +
                            Math.pow (((Math.abs(pointRech.getLongitude() - pointDely.getLongitude()) +
                            Math.abs(pointRech.getLatitude() - pointDely.getLatitude()))), 2));
        return result;
    }

    @Override
    public double calculatePrice(double result, double height) {
        double calculated = (result * height * 1.25);
        if(calculated < 40){
            return 40;
        } else {
            return calculated;
        }
    }

    @Override
    public void movePosition(Point pointDely) {
        this.setPoint(pointDely.getLatitude(), pointDely.getLatitude());
    }
}
