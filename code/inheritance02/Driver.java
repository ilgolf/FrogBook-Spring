package inheritance02;

public class Driver {
    
    public static void main(String[] args) {
        AbleFly fly1 = new Bat1("박쥐");
        fly1.fly();

        AbleFly fly2 = new Sparrow1("참새");
        fly2.fly();

        AbleSwim[] swims = new AbleSwim[2];

        swims[0] = new Whale1("고래");
        swims[1] = new Whale1("펭귄");

        for(AbleSwim swim : swims) {
            swim.swim();
        }
    }
}
