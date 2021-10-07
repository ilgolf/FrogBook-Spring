# Day_28

## 싱글턴 패턴

싱글턴 패턴이란 인스턴스를 하나만 만들어 사용하기 위한 패턴이다. 커넥션 풀, 스레드 풀, 디바이스 설정 객체 등과 같은 경우 인스턴스를 여러 개 만들게 되면 불필요한 자원을 사용하게 되고, 또 프로그램이 예상치 못한 결과를 낳을 수 있다. 싱글턴 패턴은 오직 인스턴스를 하나만 만들고 그것을 계속해서 사용한다.

싱글턴 패턴을 적용할 경우 의미 상 두 개의 객체가 존재할 수 없다. 이를 구현하려면 객체 생성을 위한 new 에 제약을 걸어야 하고, 만들어진 단일 객체를 반환할 수 있는 메서드가 필요하다. 따라서 필요한 요소를 생각해보면 다음 세 가지가 반드시 필요하다.

- new를 실행할 수 없도록 생성자에 private 접근 제어자를 지정한다.
- 유일한 단일 객체를 반환할 수 있는 정적 메서드가 필요하다.
- 유일한 단일 객체를 참조할 정적 참조 변수가 필요하다.

코드를 보며 이해해보자

```java
package singletonPattern;

public class Singleton {
    
    static Singleton singletonObject; // 정적 참조 변수
    
    private Singleton() { } // private 생성자
    
    // 객체 반환 정적 메서드
    public static Singleton getInstance() {
        if (singletonObject == null) {
            singletonObject = new Singleton();
        }
        
        return singletonObject;
    }
}
```

getInstance() 정적 메서드를 보면 정적 참조 변수에 객체가 할당돼 있지 않은 경우에만 new를 통해 객체를 만들고 정적 참조 변수에 할당한다. 그리고 정적 참조 변수에 할당돼 있는 유일한 객체의 참조를 반환한다. 이를 사용하는 테스트 코드를 살펴보자

```java
package singletonPattern;

public class Client {

    public static void main(String[] args) {
        // private 생성자이므로 new를 통해 인스턴스를 생성할 수 없다.
        // Singleton s = new Singleton();

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = Singleton.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        s1 = null;
        s2 = null;
        s3 = null;
    }
}
```

인스턴스를 생성하는 코드를 작성하면 에러가 나는 것을 확인할 수 있다. private 생성자이기에 Singleton 외부에서 new를 이용해 객체를 생성할 수 없기 때문이다. 주석을 풀었을 때 표ㅗ시되는 에러 메시지는 다음과 같다.

The constructor Singleton() is visible

null 처리를 하기 직전의 T 메모리의 스냅샷을 살펴보자

<img src="/static/6-7.PNG" width="419" height="325"></img>

4개의 참조 변수 (s1, s2, s3, singletonObject)가 하나의 단일 객체를 참조하는 것을 볼 수 있다. 이것이 바로 싱글패턴의 힘이다. 단일 객체인 경우 결국 공유 객체로 사용되기 때문에 속성을 갖지 않게 하는 것이 정석이다. 단일 객체가 속성을 갖게 되면 하나의 참조 변수가 변경한 단일 객체의 속성이 다른 참조 변수에 영향을 미치기 때문이다. 이는 전역/공유 변수를 가능한 한 사용하지 말라는 지침과 일맥 상통한다. 다만 읽기 전용 속성을 갖는 것은 문제가 되지 않는다. 

이와 더불어 단일 객체가 다른 단일 객체에 대한 참조를 속성으로 가진 것 또한 문제가 되지 않는다. 이는 나중에 학습하게 될 스프링 싱글턴 Bean이 가져야 할 제약 조건이기도 한다.

위 코드를 실행하면 다음과 같은 결과를 얻을 수 있다.

- singletonPattern.Singleton@49e4cb85
- singletonPattern.Singleton@49e4cb85
- singletonPattern.Singleton@49e4cb85

객체 참조 변수 자체를 System.out.println()을 통해 출력하면 참조하고 있는 객체의 toString() 메서드가 호출 된다. toString() 메서드를 별도로 오버라이딩 하지 않았다면 객체의 고유 값인 hashcode를 반환하는데. 호출할 때마다 같은 값을 출력하는 것을 볼 수 있다.

이는 이 3개의 참조 변수가 같은 객체를 참조하고 있다는 것을 알 수 있다.

기억해 둘 싱글턴 패턴의 특징은 다음과 같다.

- private 생성자를 갖는다.
- 단일 객체 참조 변수를 정적 속성으로 갖는다.
- 단일 객체 참조 변수가 참조하는 단일 객체를 반환하는 getInstance() 정적 메서드를 갖는다.
- 단일 객체는 쓰기 가능한 속성을 갖지 않는 것이 정석이다.

한 문장으로 싱글턴 패턴을 정리해보자

> "클래스의 인스턴스, 즉 객체를 하나만 만들어 사용하는 패턴"
>