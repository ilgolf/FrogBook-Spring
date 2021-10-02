package ocp3;

public class Matiz2 extends Driver implements Car2 {

    private boolean window;
    private int gear;

    @Override
    public void open() {
        window = !window;
    }

    @Override
    public void operGear() {
        if (gear > 6) {
            gear = 0;
        } else {
            gear ++;
        }
    }
}
