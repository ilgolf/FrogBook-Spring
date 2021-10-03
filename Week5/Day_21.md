# Day_21

## OCP - 개방 패쇄 원칙

> 소프트웨어 엔티티(클래스, 모듈, 함수)에 대해서는 열려 있어야 하지만 변경에 대해서는  있어야 한다. - 로버트 C.마틴
> 

위 문장을 조금 더 의역해 보먄 아래와 같은 문장으로 만들 수 있다.

> 자신의 확장에는 열려있고, 주변의 변화에 대해서는 닫혀 있어야 한다.
> 

개방 패쇄 원칙은 다양한 곳에서 다양하게 이야기되고 있다. 예를 통해 이해해 보자.

<img src="/static/5-5.PNG" width="513px" height="349px"></img>

어느 날 한 운전자가 마티즈를 구입했다. 그리고 열심히 마티즈에 적응했다고 해보자. 그리고 훗날 그 운전자에게 쏘나타가 생겼다.

```java
package ocp;

public class Sonata {

		private int energy;
		private int gear;
		private boolean window;

    public Sonata(int energy, int gear, boolean window) {
        this.energy = energy;
        this.gear = gear;
        this.window = window;
    }

    public void autoOpen() {
        if (energy < 0) {
            return;
        }

        energy --;
        window = !window;
    }

    public void autoGear() {
        if (energy < 0) {
            return;
        }

        if (gear > 6) {
            gear = 0;
        } else {
            gear ++;
        }

        energy --;
    }
}
```

```java
package ocp2;

public class Matiz {

		private int energy;
		private int gear;
		private boolean window;

    public Matiz(int energy, int gear, boolean window) {
        this.energy = energy;
        this.gear = gear;
        this.window = window;
    }

    public void manualOpen() {
        window = !window;
    }

    public void manualGear() {
        if (gear > 6) {
            gear = 0;
        } else {
            gear ++;
        }
    }
}
```

그림처럼  창문과 기어가 수동이던 마티즈에서 창문과 기어가 자동인 쏘나타로 차종을 바꾸니 운전자의 행동에도 변화가 온다. 마티즈를 운전할 때 운전자는 마티즈 인스턴스의 기어수동조작 메서드를 사용했는데 갑자기 쏘나타의 기어 자동조작 메서드를 사용해야한다. 운전자는 차량에 따라 습관을 바꿔하는 어려움을 겪게 될 것이다.

현실 세계에서는 어느 정도 변화를 받아들여야겠지만 객체 지향 세계에서는 좀 다르다. 

<img src="/static/5-6.PNG" width="513px" height="349px"></img>

```java
package ocp2;

public class Driver {
    protected int energy;
}
```

```java
package ocp2;

public interface Car {
    void open();
    void operGear();
}
```

```java
package ocp2;

public class Matiz extends Driver implements Car {

    private boolean window;
    private int gear;

    @Override
    public void open() {
        window = !window;
    }

    @Override
    public void operGear() {
        if (gear > 6) {
            gear = 0;
        } else {
            gear ++;
        }
    }
}
```

```java
package ocp3;

public class Sonata extends Driver implements Car {

    private boolean window;
    private int gear;

    @Override
    public void open() {
        if (energy > 0) {
            window = !window;
            energy --;
        }
    }

    @Override
    public void operGear() {
        if (energy > 0) {
            if (gear > 6) {
                gear = 0;
            } else {
                gear ++;
            }

            energy --;
        }
    }
}
```

위 코드처럼 로직을 짜면 운전자는 자동차만 몰면 다른 자동차가 생기더라도 영향을 받지않고 자동차 입장에서의 확장에 개방되어있고, 운전자 입장에서는 주변의 변화에 폐쇄돼 있는 것이라고 볼 수 있다.

혹시라도 데이터베이스 프로그래밍을 경험한 적이 있다면, 개방 폐쇄 원칙의 아주 좋은 예를 이미 알 수 있을 것이다. 

바로 JDBC이다. JDBC를 사용하는 클라이언트는 데이터베이스가 오라클에서 MySQL로 바뀌더라도  Connection을 설정하는 부분 외에는 따로 수정할 필요가 없다. Connection 설정 부분을 별도의 설정 파일로 분리해두면 클라이언트 코드는 단 한 줄도 변경할 필요가 없다. JDBC 뿐만아니라 iBatis, MyBatis, 하이버네이트 등등 데이터베이스 프로그래밍을 지원하는 라이브러리와 프레임워크에서도 개방 폐쇄 원칙의 예를 볼 수 있다.

<img src="/static/5-9.PNG" width="513px" height="349px"></img>

이러한 예는 자바에서도 볼 수  있는데, 자바 개발자는 작성하고 있는 소스 코드가 윈도우에서 구동 될지, 리눅스에서 구동 될지 걱정하지 않아도 된다,  JVM과 목적 파일(.class)이 있기에 개발자는 다양한 구동 환경에 대해서는 걱정하지 않고 본인이 작업하는 개발 PC에 설치된 JVM에서 구동 되는 코드만 작성하면 된다. 개발자가 작성한 소스 코드는 운영체제의 변화에 닫혀있고, 각 운영체제 별 JVM은 확장에 열려 있는 구조가 되는 것이다. 

마지막으로 현실 세계의 개방 폐쇄 원칙의 사례를 한번 생각해 보자. 편의점에서는 일일 삼교대로 직원이 교대한다. 주말에는 다른 아르바이트 직원이 근무하기도 한다. 하지만 직원이 바뀐다고 해서 손님이 구매라는 행위를 하는데 영향이 없다. 편의점 직원 중 한 명은 구매 담당자일 수 있다. 또 다른 담당자는 보안 담당자일 수도 있다. 편의점 직원이 근본적으로 판매라고 하는 행위 자체는 변하지 않는다. 이를 UML 약식 표기법으로 표현해보면 다음과 같다.

<img src="/static/5-15.PNG" width="513px" height="349px"></img>

개방 폐쇄 원칙을 따르지 않는다고 해서 객체 지향 프로그램을 구현하는 것이 불가능한 것은 아니지만 개방 폐쇄 원칙을 무시하고 프로그램을 작성하면 객체 지향 프로그래밍의 가장 큰 장점인 유연성, 재사용성, 유지보수성 등을 얻을 수 없다. 따라서 객체 지향 프로그래밍에서 개방 폐쇄 원칙은 반드시 지켜야 할 원칙이다.
