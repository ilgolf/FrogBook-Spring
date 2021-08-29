package inheritance03;

public class Driver03 {

    public static void main(String[] args) {
        Penguin2 pororo = new Penguin2("뽀로로");

        pororo.setHabitat("남극");

        pororo.showName();
        pororo.showHabitat();

        Animals2 pingu = new Penguin2("핑구");

        // pingu.setHaitat("EBS");

        pingu.showName();
        // pingu.showHabitat();

        // Penguin2 happyfit = new Animals2("해피피트");
    }
}
