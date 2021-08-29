package inheritance02;

public class Whale1 extends Mammalia1 implements AbleSwim {

    public Whale1(String myClass) {
        super(myClass);
    }

    @Override
    public void swim() {
        System.out.println(myClass + "Swimming. 어푸 어푸");
    }
}
