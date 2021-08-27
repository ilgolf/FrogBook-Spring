package inheritance02;

public class Penguin extends Birds implements AbleSwim {

    public Penguin(String myClass) {
        super(myClass);
    }

    @Override
    public void swim() {
        System.out.println(myClass + "헤엄치고 있음 푸악 푸악");        
    }
}
