# Day_26

## 프록시 패턴

프록시는 대리자. 대변인이라는 뜻을 가진 단어다. 대리자/대변인 이라고 하면 다른 누군가를 대신해 그 역할을 수행하는 존재를 말한다. 청와대 대변인 백악관 대변인 등 같은 디자인 패턴에서도 이러한 대변인이 등장한다. 

그렇다면 대리자를 사용하지 않고 직접 호출하는 구조를 살펴보자

<img src="/static/6-4.png" width="366px" height="270px"></img>

```java
package proxyPattern;

public class Service {

    public String runSomething() {
        return "서비스 짱!!!";
    }
}
```

```java
package proxyPattern;

public class ClientWithNoProxy {

    public static void main(String[] args) {
        // 프록시를 이용하지 않는 호출
        Service service = new Service();
        System.out.println(service.runSomething());
    }
}
```

그렇다면 프록시 패턴이 적용된 경우를 살펴보자. 프록시 패턴의 경우 실제 서비스 객체가 가진 메서드와 같은 이름의 메서드를 사용하는데, 이를 위해 인터페이스를 사용한다. 

인터페이스를 사용하면서 서비스 객체가 들어갈 자리에 대리자 객체를 대신 투입해 클라이언트 쪽에서는 실제 서비스 객체를 통해 메서드를 호출하고 반환 값을 받는지, 대리자 객체를 통해 메서드를 호출하고 반환 값을 받는지 전혀 모르게 처리할 수도 있다.

그렇다면 프록시 패턴을 이용한 경우를 살펴보자

<img src="/static/6-6.png" width="366px" height="270px"></img>

```java
package proxyPattern;

public interface IService {
    String runSometing();
}
```

```java
package proxyPattern;

public class Service2 implements IService {

    @Override
    public String runSometing() {
        return "서비스 짱!!";
    }
}
```

```java
package proxyPattern;

public class Proxy implements IService {
    IService service1;

    @Override
    public String runSometing() {
        // System.out.println("호출에 대한 흐름 제어가 주목적, 반환 결과를 그대로 전달");

        service1 = new Service2();
        return service1.runSometing();
    }
}
```

```java
package proxyPattern;

public class ClientWithProxy {
    // 프록시를 이용한 호출
    public static void main(String[] args) {
        IService proxy = new Proxy();
        System.out.println(proxy.runSometing());
    }
}
```

코드들을 살펴 보았다 이를 바탕으로 프록시 패턴의 중요 포인트를 짚어보자

- 대리자는 실제 서비스와 같은 이름의 메서드를 구현한다. 이 때 인터페이스를 사용한다.
- 대리자는 실제 서비스에 대한 참조 변수를 갖는다.
- 대리자는 실제 서비스의 같은 이름을 가진 메서드를 호출하고 그 값을 클라이언트에게 돌려준다.
- 대리자는 실제 서비스의 메서드 호출 전 후에도 별도의 로직을 수행할 수 있다.

여기서 대리자/대변인이라는 이름에 주목해보자 대변인은 해당 기관의 입장을 대변할 뿐 자신의 입장이 없다. 프록시 패턴은 실제 서비스 메서드의 반환 값에 가감하는 것을 목적으로 하지 않고 제어의 흐름을 변경하거나 다른 로직을 수행하기 위해 사용한다. 

한 문장으로 정리해보면

> "제어 흐름을 조정하기 위한 목적으로 중간에 대리자를 두는 패턴"
> 

위의 예제에서 Service와 Proxy 그리고 IService 사이의 구조를 살펴보자 개방 폐쇄 원칙에서 예로 들었던 스노우타이어와 일반 타이어, 광폭 타이어를 서로 교체해 주어도 영향을 받지 않았던 자동차를 예로 들던 의존 역전 원칙도 생각날 것이다. 예제에서 살펴본 프록시 패턴이 개방 폐쇄 원칙과 의존 역전 원칙이 적용된 설계 패턴이기 때문이다.