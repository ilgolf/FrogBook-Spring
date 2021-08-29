package inheritance02;

public class Bat1 extends Mammalia1 implements AbleFly {

    public Bat1(String myClass) {
        super(myClass);
    }

    @Override
    public void fly() {
        System.out.println(myClass + "flying 슈웅 ~ ");        
    }
    
}
