package inf008.ads.ifba.edu.br.logisticsnegotium.entities;

public class Point {
    private double latitude;
    private double longitude;

    public Point(double latitude, double longitude){
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }


    public double getLatitude() {
        return latitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
