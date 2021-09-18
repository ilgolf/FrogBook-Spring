# Day_17

## abstract키워드 - 추상 메서드와 추상 클래스

추상 메서드를 간단하게 설명하자면 선언 부는 있고, 구현 부가 없는 메서드이다. 추상 메서드를 갖고 있는 클래스는 반드시 추상 클래스로 선언해 줘야한다. 그렇다면 이 구조를 알아보기 위해 코드를 보자

```java
package abstractMethod01;

public class Driver04 {

    public static void main(String[] args) {
        Animal[] animals = new Animal[5];

        animals[0] = new Mouse();
        animals[1] = new Cat();
        animals[2] = new Dog();
        animals[3] = new Cow();
        animals[4] = new Chick();

        for (Animal animal : animals) {
            animal.crying();
        }
    }
}
```

이 코드를 토대로 클래스를 만들어보자

```java
package abstractMethod01;

public class Mouse4 extends Animals4 {

    void crying() {
        System.out.println("나는 찍 찍 찍");
    }
}
```

```java
package abstractMethod01;

public class Cat4 extends Animals4 {

    void crying() {
        System.out.println("나는 애옹! 애옹!");
    }
}
```

```java
package abstractMethod01;

public class Chick4 extends Animals4 {

    void crying() {
        System.out.println("나는 삐약! 삐약!");
    }
}
```

```java
package abstractMethod01;

public class Animals4 {

    void crying() {
        System.out.println("나는 어떻게 울까요?");
    }
}
```

동물 클래스 인스턴스는 어떻게 울어야 할까? 동물은 하나의 분류를 나타낼 뿐 실체 화 할 수 없는 추상적인 개념이다. 그렇기 때문에 Animal에 저 메소드가 실행 되는 것은 적합하지 않다. 그러면 Animal 클래스의 메소드를 살짝 바꿔 보자

```java
package abstractMethod01;

public abstract class Animals4 {

    abstract void crying();
}
```

이렇게 되면 이제 동물들이 운다는 것에 대하여 구현이 되어 있지 않기 때문에 Driver 클래스에선

```java
package abstractMethod01;

public class Driver04 {

    public static void main(String[] args) {
        Animals4[] animals = new Animals4[3];

        animals[0] = new Mouse4();
        animals[1] = new Cat4();
        animals[2] = new Chick4();

        for (Animals4 animal : animals) {
            animal.crying();
        }
        
        // Animals4 animal = new Animals4()
    }
}
```

//Animals4 animal = new Animals4() 주석을 해제할 경우 다음과 같은 오류를 볼 수 있다.

- Cannot instantiate the type Animals4

이 메시지는 "Animals4타입은 인스턴스를 만들 수 없다" 이다. 추성 클래스는 인스턴스, 즉, 객체를 만들 수 없는 클래스가 된다. 

이렇게 하면 두 가지 문제가 해결이 된다.

- 동물 객체는 어떻게 울어야 하지? / 누가 실수로 동물 객체를 만들면 어떡하지?
- 동물 참조 변수 배열로 모든 동물을 울게 하려면 하위 클래스에서 오버라이딩할 crying() 메서드가 동물 클래스에 필요한데...

이 추상 메소드가 존재하는 추상 클래스를 상속 받은 서브 클래스들은 반드시 이 메서드를 구현해야 한다.

```java
package abstractMethod01;

public class Chick4 extends Animals4 {

  //  void crying() {
  //      System.out.println("나는 삐약! 삐약!");
  //  }
}
```

이렇게 주석 처리하면 오류가 날 것이다. 오류를 살펴보면

- The type Cat must implement the inherited abstract method Animals4.crying()

번역해 보면 Cat 타입은 반드시 Animals4.crying() 추상 메서드를 구현해야합니다. 가 된다.

그러면 내용을 정리해보자 

- 추상 클래스는 인스턴스, 즉 객체를 만들 수 없다.  즉, new 를 사용할 수 없다.
- 추상 매소드는 하위 클래스에게 메서드의 구현을 강제한다. 오버라이딩 강제
- 추상 메서드를 포함하는 클래스는 반드시 추상 클래스여야 한다.