package abstractMethod01;

public class Driver04 {

    public static void main(String[] args) {
        Animals4[] animals = new Animals4[3];

        animals[0] = new Mouse4();
        animals[1] = new Cat4();
        animals[2] = new Chick4();

        for (Animals4 animal : animals) {
            animal.crying();
        }

        // Animals4 animal = new Animals4()
    }
}
