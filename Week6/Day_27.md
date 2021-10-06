# Day_27

## 데코레이터 패턴

데코레이터는 도장/도배업자를 의미한다. 여기서는 장식자라는 뜻을 가지고 논리를 풀어 보자. 데코레이터 패턴이 원본에 장식을 더하는 패턴이라는 것이 이름에 잘 드러나 있다. 데코레이터 패턴은 프록시 패턴과 구현 방법이 같다. 다만 프록시 패턴은 클라이언트가 최종적으로 돌려 받는 반환값을 조작하지 않고 그대로 전달하는 반면 데코레이터 패턴은 클라이언트가 받는 반환 값에 장식을 덧입힌다.

[제목 없음](https://www.notion.so/ac5f6fa49a0347009869384ebe6bc7fc)

프록시 패턴과 데코레이터 패턴은 클래스/시퀸스 다이어그램이 서로 같으니 코드만 살펴보자

```java
package decoratorPattern;

public interface IService {
     String runSomething();
}
```

```java
package decoratorPattern;

public class Service implements IService {

    @Override
    public String runSomething() {
        return "서비스 짱!!";
    }
}
```

```java
package decoratorPattern;

public class Decoreator implements IService {
    IService iService;

    @Override
    public String runSomething() {
        System.out.println("호출에 대한 장식 주 목적, 클라이언트에게 반환 결과에 장식을 더하여 전달");

        iService = new Service();
        return "정말" + iService.runSomething();
    }
}
```

```java
package decoratorPattern;

public class ClientWithDecolator {

    public static void main(String[] args) {
        IService decoreator = new Decoreator();
        System.out.println(decoreator.runSomething());
    }
}
```

데코레이터 패턴의 중요 포인트를 짚어보자. 반환 값에 장식을 더한다는 것을 빼면 프록시 패턴과 동일하다. 

- 장식자는 실제 서비스와 같은 이름을
- 장식자는 실제 서비스에 대한 참조 변수를 갖는다(합성).
- 장식자는 실제 서비스의 같은 이름을 가진 메서드를 호출하고, 그 반환값에 장식을 더해 클라이언트에게 돌려준다.
- 장식자는 실제 서비스의 메서드 호출 전후에 별도의 로직을 수행할 수 있다.

장식자라는 이름에서 느껴지듯 실제 서비스의 반환 값을 예쁘게 포장하는 패턴이 데코레이터 패턴임을 기억하자. 

> "메서드 호출의 반환 값에 변화를 주기 위해 중간에 장식자를 두는 패턴"
> 

테코레이터 패턴이 프록시 패턴과 동일한 구조를 갖기에 데코레이터 패턴도 개방 폐쇄 원칙과 의존 역전 원칙이 적용된 설계 패턴임을 알 수 있다.