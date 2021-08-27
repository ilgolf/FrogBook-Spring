package inheritance02;

public class Whale extends Mammalia implements AbleSwim {

    public Whale(String myClass) {
        super(myClass);
    }

    @Override
    public void swim() {
        System.out.println(myClass + "Swimming. 어푸 어푸");
    }
}
