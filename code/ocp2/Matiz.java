package ocp2;

public class Matiz extends Car implements Manipulable {

    public Matiz(int energy, int gear, boolean window) {
        this.energy = energy;
        this.gear = gear;
        this.window = window;
    }

    @Override
    public void open() {
        if (energy < 0) {
            return;
        }

        energy --;
        window = !window;
    }

    @Override
    public void operGear() {
        if (energy < 0) {
            return;
        }

        if (gear > 6) {
            gear = 0;
        } else {
            gear ++;
        }

        energy --;
    }

    @Override
    public String toString() {
        return "Matiz{" +
                "gear=" + gear +
                ", window=" + window +
                ", energy=" + energy +
                '}';
    }
}
