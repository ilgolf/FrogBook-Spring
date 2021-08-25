package inheritance02;

public class Driver {
    
    public static void main(String[] args) {
        AbleFly fly1 = new Bat("박쥐");
        fly1.fly();

        AbleFly fly2 = new Sparrow("참새");
        fly2.fly();

        AbleSwim[] swims = new AbleSwim[2];

        swims[0] = new Whale("고래");
        swims[1] = new Whale("펭귄");

        for(AbleSwim swim : swims) {
            swim.swim();
        }
    }
}
