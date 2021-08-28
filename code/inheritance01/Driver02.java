package inheritance01;

public class Driver02 {
    
    public static void main(String[] args) {
        Animal animal = new Animal("동물");
        Animal mammalias = new Mammalias("포유류");
        Animal bird = new Birds("새");
        Animal whale = new Whale("고래");
        Animal bat = new Bat("박쥐");
        Animal sparrow = new Sparrow("참새");
        Animal penguin = new Penguin("펭귄");

        animal.showMe();
        mammalias.showMe();
        bird.showMe();
        whale.showMe();
        bat.showMe();
        sparrow.showMe();
        penguin.showMe();
    }
}
