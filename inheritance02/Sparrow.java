package inheritance02;

public class Sparrow extends Birds implements AbleFly {

    public Sparrow(String myClass) {
        super(myClass);
    }

    @Override
    public void fly() {
        System.out.println(myClass + "날고 있음 파닥 파닥");
    }
}
