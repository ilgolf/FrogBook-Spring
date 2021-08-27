package inheritance02;

public class Bat extends Mammalia implements AbleFly {

    public Bat(String myClass) {
        super(myClass);
    }

    @Override
    public void fly() {
        System.out.println(myClass + "flying 슈웅 ~ ");        
    }
    
}
