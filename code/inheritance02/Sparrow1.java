package inheritance02;

public class Sparrow1 extends Birds1 implements AbleFly {

    public Sparrow1(String myClass) {
        super(myClass);
    }

    @Override
    public void fly() {
        System.out.println(myClass + "날고 있음 파닥 파닥");
    }
}
