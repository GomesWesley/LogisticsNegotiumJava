package inf008.ads.ifba.edu.br.logisticsnegotium.entities;

public class Test {

    public static void main(String[] args){
        LandVehicle lv = new LandVehicle(1, 10, 5, 5);
        AirVehicle av = new AirVehicle(2, 10, 5, 5);
        Point pointRech = new Point(3, 2);
        Point pointDely = new Point(7, 7);
        System.out.println(lv.calculateDistance(pointRech, pointDely));
        System.out.println(av.calculateDistance(pointRech, pointDely));

    }

}
