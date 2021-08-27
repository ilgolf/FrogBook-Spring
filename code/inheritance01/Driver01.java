package inheritance01;

public class Driver01 {
    
    public static void main(String[] args) {
        Animal animal = new Animal("동물");
        Mammalias mammalia = new Mammalias("포유류");
        Birds bird = new Birds("새");
        Whale whale = new Whale("고래");
        Bat bat = new Bat("박쥐");
        Sparrow sparrow = new Sparrow("참새");
        Penguin penguin = new Penguin("펭귄");

        animal.showMe();
        mammalia.showMe();
        bird.showMe();
        whale.showMe();
        bat.showMe();
        sparrow.showMe();
        penguin.showMe();
    }
}
