package inf008.ads.ifba.edu.br.logisticsnegotium.entities;

public interface AutomatedVehiclesIF {
    public double calculateDistance(Point pointRech, Point pointDely);
    public double calculatePrice(double result, double height);
    public void movePosition(Point pointDely);
}
