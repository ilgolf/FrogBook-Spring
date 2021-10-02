package ocp;

public abstract class Car {
    private boolean open; // true :열림, false : 닫힘
    private int gear;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    abstract void open();
    abstract void HandleGear();
}
