package ocp2;

public class Sonata extends Car implements Manipulable {

    public Sonata(int energy, int gear, boolean window) {
        this.energy = energy;
        this.gear = gear;
        this.window = window;
    }

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

    @Override
    public String toString() {
        return "Sonata{" +
                "gear=" + gear +
                ", window=" + window +
                ", energy=" + energy +
                '}';
    }
}
