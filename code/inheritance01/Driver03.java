package inheritance01;

public class Driver03 {
    
    public static void main(String[] args) {
        Animal[] animals = new Animal[7];
        
        animals[0] = new Animal("동물");
        animals[1] = new Mammalia("포유류");
        animals[2] = new Bird("새");
        animals[3] = new Whale("고래");
        animals[4] = new Bat("박쥐");
        animals[5] = new Sparrow("참새");
        animals[6] = new Penguin("펭귄");

        for(Animal animal : animals) {
            animal.showMe();            
        }
    }
}
