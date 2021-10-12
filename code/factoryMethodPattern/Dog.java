package factoryMethodPattern;

public class Dog extends Animal {

    // 추상 팩토리 메서드 오버라이딩
    @Override
    AnimalToy getToy() {
        return new DogToy();
    }
}
