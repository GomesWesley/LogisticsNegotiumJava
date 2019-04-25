package inf008.ads.ifba.edu.br.logisticsnegotium.entities;

public class LandVehicle extends AutomatedVehicles {

    public LandVehicle(int registration, double capacity, double latitude, double longitude) {
        super(registration, capacity, latitude, longitude);
    }

    @Override
    public double calculateDistance(Point pointRech, Point pointDely) {
        Point point = this.getPoint();
        double result;
        result = (Math.abs(point.getLatitude() - pointRech.getLatitude()) +
                 Math.abs(point.getLongitude() - pointRech.getLongitude())) +
                (Math.abs(pointRech.getLongitude() - pointDely.getLongitude()) +
                 Math.abs(pointRech.getLatitude() - pointDely.getLatitude()));
        return result;
    }

    @Override
    public double calculatePrice(double result, double height) {
        double calculated = (result * height);
        if(calculated < 30){
            return 30;
        } else {
            return calculated;
        }
    }

    @Override
    public void movePosition(Point pointDely) {
        this.setPoint(pointDely.getLatitude(), pointDely.getLatitude());
    }
}
