# Day_30

## 팩토리 메서드 패턴

팩토리는 공장을 의미한다. 공장은 물건을 생산하는데 객체 지향에서 팩토리는 객체를 생산한다. 결국 팩토리 메서드는 객체를 생성 반환하는 메서드를 말한다. 여기에 패턴이 붙으면 하위 클래스에서 팩토리 메서드를 오버라이딩해서 객체를 반환하게 하는 것을 의미한다.

그렇다면 코드로 더 알아가 보자

```java
package factoryMethodPattern;

public abstract class Animal {
    // 추상 팩토리 메서드
    abstract AnimalToy getToy();
}
```

```java
package factoryMethodPattern;

// 팩토리 메서드가 생성할 객체의 상위 클래스
public abstract class AnimalToy {
    abstract void identify();
}
```

```java
package factoryMethodPattern;

public class Dog extends Animal {

    // 추상 팩토리 메서드 오버라이딩
    @Override
    AnimalToy getToy() {
        return new DogToy();
    }
}
```

```java
package factoryMethodPattern;

// 팩토리 메서드가 생성할 객체
public class DogToy extends AnimalToy {

    @Override
    public void identify() {
        System.out.println("나는 테니스공!! 강아지의 친구");
    }
}
```

```java
package factoryMethodPattern;

public class Cat extends Animal {

    @Override
    AnimalToy getToy() {
        return new CatToy();
    }
}
```

```java
package factoryMethodPattern;

public class CatToy extends AnimalToy {

    @Override
    void identify() {
        System.out.println("나는 캣 타워 !! 고양이 친구");
    }
}
```

```java
package factoryMethodPattern;

public class Driver {

    public static void main(String[] args) {
        // 팩토리 메서드를 보유한 객체들 생성
        Animal bolt = new Dog();
        Animal kitty = new Cat();

        // 팩토리 메서드가 반환하는 객체들
        AnimalToy boltBall = bolt.getToy();
        AnimalToy kittyTower = kitty.getToy();

        // 팩토리 메서드가 반환한 객체들을 사용
        boltBall.identify();
        kittyTower.identify();
    }
}
```

팩토리 메서드는 다음 문장만 기억하면 된다.

> "오버라이드된 메서드가 객체를 반환하는 패턴"
> 

클래스 다이어 그램을 위와 아래 반반씩 유심히 살펴보면 팩터리 메서드 패턴이 의존 역선 원칙을 활용하고 있음을 알 수 있다.