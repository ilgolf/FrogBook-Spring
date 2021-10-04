# Day_25

## 어댑터 패턴

어댑터를 번역하면 변환기라고 할 수 있다. 변환기의 역할은 서로 다른 두 인터페이스 사이에 통신이 가능하게 하는 것이다. 주변에서 흔히 볼 수 있는 변환기로는 충전기가 있다. 휴대폰 충전기의 경우 휴대폰을 직접 전원 콘센트에 연결할 수 없기 때문에 충전기가 핸드폰과 전원 콘센트사이에서 둘을 연결해주는 변환기의 역할을 수행해 준다.

데이터베이스 관련 프로그램을 작성해 본 독자라면 다양한 데이터베이스 시스템을 공통의 인터페이스인  ODBC 또는 JDBC를 이용해 조작할 수 있다는 사실을 알고 있을 것이다. 바로 이것이 어댑터 패턴을 이용해 다양한 데이터베이스 시스템을 단일한 인터페이스로 조작할 수 있게 해주기 때문이다.

JDBC와 JRE는 개방 폐쇄 원칙을 설명할 때에도 예를 들었다. 즉, 어댑터 패턴은 개방 폐쇄 원칙을 활용한 패턴인 것이다.

JDBC는 데이터베이스와 비즈니스 로직간의 연결해주고 있는 것이라 보면 된다.

어댑터 패턴이 적용되지 않은 코드를 먼저 살펴보자

```java
package adapterPattern;

public class ServiceA {

    void runServiceA() {
        System.out.println("ServiceA");
    }
}
```

```java
package adapterPattern;

public class ServiceB {

    void runServiceB() {
        System.out.println("ServiceB");
    }
}
```

```java
package adapterPattern;

public class ClientWithNoAdapter {

    public static void main(String[] args) {
        ServiceA sa1 = new ServiceA();
        ServiceB sb1 = new ServiceB();

        sa1.runServiceA();
        sb1.runServiceB();
    }
}
```

main() 메서드를 살펴보면 sa1 참조 변수와 sb1 참조 변수를 통해 호출하는 각 메서드가 비슷한 일을 하지만 메서드명이 다른 것을 볼 수 있다. 

그렇다면 이번엔 어댑터 패턴을 적용해 메서드 명을 통일해보자. ServiceA에 대한 변환기와 ServiceB에 대한 변환기를 추가한다. 

```java
package adapterPattern;

public class AdapterServiceA {
    ServiceA sa1 = new ServiceA();

    void runService() {
        sa1.runServiceA();
    }
}
```

```java
package adapterPattern;

public class AdapterServiceB {
    ServiceB sb1 = new ServiceB();

    void runService() {
        sb1.runServiceB();
    }
}
```

위 코드는 기존의 ServiceA와 ServiceB의 메서드를 runService()라고 하는 같은 이름의 메서드로 호출해서 사용할 수 있게 해주는 변환기다. 이제 ClientWithAdapter라는 클래스를 추가하고 main() 메서드에서 이 변환기들을 사용해보자

```java
package adapterPattern;

public class ClientWithAdapter {

    public static void main(String[] args) {
        AdapterServiceA asa1 = new AdapterServiceA();
        AdapterServiceB asb1 = new AdapterServiceB();

        asa1.runService();
        asa1.runService();
    }
}
```

클라이언트가 변환기를 통해  runService()라는 동일한 메서드명으로 두 객체의 메서드를 호출하는 것을 볼 수 있다. 변환기들이 인터페이스를 구현하게 해서 더 개선할 수도 있다. 

```java
package adapterPattern;

public interface Service {
    void runService();
}
```

```java
package adapterPattern;

public class AdapterServiceA implements Service {

    @Override
    public void runService() {
        System.out.println("ServiceA");
    }
}
```

```java
package adapterPattern;

public class AdapterServiceB implements Service {

    @Override
    public void runService() {
        System.out.println("ServiceB");
    }
}
```

```java
package adapterPattern;

public class ClientWithAdapter {

    public static void main(String[] args) {
        AdapterServiceA asa1 = new AdapterServiceA();
        AdapterServiceB asb1 = new AdapterServiceB();

        asa1.runService();
        asb1.runService();
    }
}
```

어댑터 패턴은 합성, 즉 객체를 속성으로 만들어서 참조하는 디자인 패턴으로, 한 문장으로 정리한다면 다음과 같다. 

> "호출당하는 쪽의 메서드를 호출하는 쪽의 코드에 대응하도록 중간에 변환기를 통해 호출하는 패턴"
>