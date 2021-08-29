package polymorphism01;

public class DriverPol01 {

    public static void main(String[] args) {
        Penguin3 pororo = new Penguin3("뽀로로", "남극");

        pororo.showName();
        pororo.showName("초보 람보");
        pororo.showHabitat();

        Animals3 pingu = new Penguin3("핑구", "EBS");
        pingu.showName();
    }
}
