package inf008.ads.ifba.edu.br.logisticsnegotium.entities;

public abstract class AutomatedVehicles implements AutomatedVehiclesIF {
    private int id;
    private double capacity;
    private Point point;

    public AutomatedVehicles(int id, double capacity, double latitude, double longitude){
        this.setId(id);
        this.setCapacity(capacity);
        this.setPoint(latitude, longitude);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public double getCapacity() {
        return capacity;
    }

    private void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(double latitude, double longitude){
        this.point = new Point(latitude, longitude);
    }
}
