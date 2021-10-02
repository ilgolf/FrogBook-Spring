package ocp3;

public class Sonata2 extends Driver implements Car2 {

    private boolean window;
    private int gear;

    @Override
    public void open() {
        if (energy > 0) {
            window = !window;
            energy --;
        }
    }

    @Override
    public void operGear() {
        if (energy > 0) {
            if (gear > 6) {
                gear = 0;
            } else {
                gear ++;
            }

            energy --;
        }
    }
}
