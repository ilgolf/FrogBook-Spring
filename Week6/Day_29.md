# Day_28

## 템플릿 메서드 패턴

볼트라고 하는 이름의 강아지와 키티라는 이름의 고양이를 키운다고 상상해 보자. 볼트, 키티와 함께 재밌는 시간을 보내는 세계를 프로그램으로 표현한다면 다음과 같은 2개의 클래스가 필요할 것이다.

```java
package templetePattern;

public class Dog {

    public void playWithOwner() {
        System.out.println("귀염둥이 이리온...");
        System.out.println("멍! 멍!");
        System.out.println("꼬리 살랑 살랑 ~");
        System.out.println("잘했어");
    }
}
```

```java
package templetePattern;

public class Cat {

    public void playWithOwner() {
        System.out.println("귀염둥이 이리온...");
        System.out.println("야옹 ~ 야옹 ~");
        System.out.println("꼬리 살랑 살랑 ~");
        System.out.println("잘했어");
    }
}
```

코드안에 playWithOwner() 메서드를 보면 두 번째 출력을 빼고 모든 모두 동일한 것을 볼 수 있다. 

코드를 보고 있으면 객체 지향의 4대 특성 가운데 상속을 통해 동일한 부분은 상위클래스로 달라지는 부분만 하위 클래스로 분할하고 싶은 욕구가 생긴다. 

```java
package templetePattern;

public abstract class Animal {

    public void playWithOwner() {
        System.out.println("귀염둥이 이리온...");
        play();
        runSomething();
        System.out.println("잘했어");
    }

    abstract void play();

    void runSomething() {
        System.out.println("꼬리 살랑 살랑 ~");
    }
}
```

```java
package templetePattern;

public class Dog extends Animal {

    @Override
    // 추상 메서드 오버라이딩
    void play() {
        System.out.println("멍! 멍!");
    }

    @Override
    // Hook(갈고리 메서드 오버라이딩)
    void runSomething() {
        System.out.println("멍! 멍!~ 꼬리 살랑 살랑~");
    }
}
```

```java
package templetePattern;

public class Cat extends Animal {

    @Override
		// 추상 메서드 오버라이딩
    void play() {
        System.out.println("야옹~ 야옹~");
    }

    @Override
		// Hook(갈고리 메서드 오버라이딩)
    void runSomething() {
        System.out.println("야옹~ 야옹~ 꼬리 살랑 살랑~");
    }
}
```

```java
package templetePattern;

public class Driver {

    public static void main(String[] args) {
        Animal bolt = new Dog();
        Animal kitty = new Cat();

        bolt.playWithOwner();

        System.out.println();
        System.out.println();

        kitty.playWithOwner();
    }
}
```

상위 클래스인 Animal에는 템플릿을 제공하는 playWithOwner() 메서드와 하위 클래스에게 구현을 강제하는 play() 추상 메서드, 하위 클래스가 선택적으로 오버라이딩할 수 있는 runSomething() 메서드가 있다. 하위 클래스인 Dog와 Cat은 상위 클래스인 Animal에서 구현을 강제하고 있는 play() 추상메서드를 두는 패턴을 탬플릿 메서드 패턴이라고 한다. 이를 시퀸스 다이어그램으로 이해해보자

<img src="/static/6-9.png" width="350" height="684"></img>

<img src="/static/[cell]6-3.PNG" width="823" height="244"></img>

> "상위 클래스의 견본 메서드에서 하위 클래스가 오버라이딩한 메서드를 호출하는 패턴"
> 

템플릿 메서드 패턴은 의존 역전 원칙을 활용하고 있음을 기억하자