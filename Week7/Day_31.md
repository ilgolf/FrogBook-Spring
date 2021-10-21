# Day_31

## 전략 패턴

디자인 패턴의 꽃이라고 하는 전략 패턴을 알아보자. 전략 패턴을 구성하는 세 요소는 꼭 기억해 둬야한다.

- 전략 메서드를 가진 전략 객체
- 전략 객체를 사용하는 컨텍스트(전략 객체의 사용자/소비자)
- 전략 객체를 생성해 컨텍스트에 주입하는 클라이언트(제 3자, 전략 객체의 공급자)

<img src="/static/6-12.PNG" width="518" height="291"></img>

클라이언트는 다양한 전략 중 하나를 선택해 생성한 후 컨텍스트에 주입한다.

군인이 있다고 상상해 보자. 그리고 그 군인이 사용할 무기가 있다고 하자. 보급 장교가 무기를 군인에게 지급해 주면 군인은 주어진 무기에 따라 전투를 수행하게 된다. 이 이야기를 전략 패턴에 따라 구분해 보면 무기는 전략이 되고, 군인은 컨텍스트, 보급 장교는 제 3자, 즉 클라이언트가 된다. 이를 자바 코드로 구현해보자. 먼저 다양한 전략을 공통된 방식으로 사용하기 위해 인터페이스를 정의한다.

```java
package strategyPattern;

public interface Strategy {
    void runStrategy();
}
```

```java
package strategyPattern;

public class StrategyGun implements Strategy {

    @Override
    public void runStrategy() {
        System.out.println("탕, 타당, 타다당");
    }
}
```

```java
package strategyPattern;

public class StrategySword implements Strategy {

    @Override
    public void runStrategy() {
        System.out.println("챙.. 채쟁챙 챙챙");
    }
}
```

```java
package strategyPattern;

public class StrategyBow implements Strategy {

    @Override
    public void runStrategy() {
        System.out.println("슝.. 쐐액.. 쉑, 최종병기");
    }
}
```

```java
package strategyPattern;

public class Soldier {

    void runContext(Strategy strategy) {
        System.out.println("전투 시작");
        strategy.runStrategy();
        System.out.println("전투 종료");
    }
}
```

```java
package strategyPattern;

public class Client {

    public static void main(String[] args) {
        Strategy strategy = null;
        Soldier rambo = new Soldier();

        // 총을 람보에게 전달해서 전투를 수행하게 한다.
        strategy = new StrategyGun();
        rambo.runContext(strategy);

        System.out.println();

        // 검을 람보에게 전달해서 전투를 수행하게 한다.
        strategy = new StrategySword();
        rambo.runContext(strategy);

        System.out.println();

        // 활을 람보에게 전달해서 전투를 수행하게 한다.
        strategy = new StrategyBow();
        rambo.runContext(strategy);
    }
}
```

Client를 실행하면 결과는 다음과 같다.

전투 시작
탕, 타당, 타다당
전투 종료

전투 시작
챙.. 채쟁챙 챙챙
전투 종료

전투 시작
슝.. 쐐액.. 쉑, 최종병기
전투 종료

위 코드처럼 전략을 다양하게 변경하면서 컨텍스트를 실행할 수 있다. 전략 패턴은 이미 밝힌대로 디자인 패턴의 꽃이라고 할 정도로 다양한 곳에서 다양한 문제 상황의 해결책으로 사용된다. 그리고 혹시나 템플릿 메서드 패턴과 유사하다는 느낌이 든다면, 제대로 본 것이다. 같은 문제의 해결책으로 상속을 이용하는 템플릿 메서드 패턴과 객체 주입을 통한 전략 패턴 중에서 선택/적용할 수 있다.

단일 상속만이 가능한 자바 언어에서는 상속이라는 제한이 있는 템플릿 메서드 패턴 보다는 전략 패턴이 더 많이 활용된다.

시퀀스 다이어그램을 살펴보자.

<img src="/static/6-14.png" width="345" height="420"></img>

전략 패턴을 한 문장으로 정리해 보자.

> "클라이언트가 전략을 생성해 전략을 실행할 컨텍스트에 주입하는 패턴"
> 

전략 패턴의 클래스 다이어 그램을 보면 개방 폐쇄 원칙(OCP)과 의존 역전 원칙(DIP)이 적용된 것을 짐작할 수 있을 것이다.