package inheritance02;

public class Penguin1 extends Birds1 implements AbleSwim {

    public Penguin1(String myClass) {
        super(myClass);
    }

    @Override
    public void swim() {
        System.out.println(myClass + "헤엄치고 있음 푸악 푸악");        
    }
}
