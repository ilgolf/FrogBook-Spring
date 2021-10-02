# Day_23

## ISP - 인터페이스 분리 원칙

> "클라이언트는 자신이 사용하지 않는 메서드에 의존 관계를 맺으면 안된다."   - 로버트 C. 마틴
> 

단일 책임 원칙(SRP) 예제를 다시 살펴보자. 단일 책임 원칙을 적용하기 전 남자 클래스는 다음과 같다.

<img src="/static/5-10.png" width="579px" height="361px"></img>

단일 책임 원칙을 적용한 후에는 좀 더 책임을 가는 클래스로 나뉘었다.

여기서 제시한 해결책은 남자 클래스를 토막내서 하나의 역할만 하는 다수의 클래스로 분할하는 것 이었다. 그렇다면 다른 방법은 없을까? 

있다. 그것이 바로 ISP 즉, 인터페이스 분할 원칙이다. 인터페이스 분할 원칙을 제하는 해결책을 알아보자

남자 클래스를 토막내는 것이 아니라 다중 인격화 시켜 여자친구를 만날 때는 남자친구 역할만 하게 하고, 어머니와 있을 때는 아들 인터페이스로 제한하고, 직장 상사 앞에서는 사원 인터페이스로 제한 하는 등 이것이 바로 인터페이스 분할 원칙의 핵심이다.

```java
package icp;

public class Man {
    protected int money;
    protected int energy;
}
```

```java
package icp;

public interface BoyFriend {
    void anniversary(); // 기념일 챙기기
    void doKiss(); // 키스하기
}
```

```java
package icp;

public interface Son {
    void filialPiety(); // 효도하기
    void massage(); // 안마 해드리기
}
```

```java
package icp;

public interface OfficeWorker {
    void goWork(); // 출근
    void flatter(); // 퇴근
}
```

```java
package icp;

public interface ArmyMember {
    void shoot(); // 사격
    void run(); // 구보
}
```

이런 식으로 코드를 짜서 좀 더 남자는 속성만 갖고 있고 인터페이스를 나눠 행위를 분류하자!

결론적으로 단일 책임 원칙과 인터페이스 분할 원칙은 같은 문제에 대한 두 가지 다른 해결책이라 볼 수 있다. 프로젝트 요구 사항과 설계자의 취향에 따라 단일 책임 원칙이나 인터페이스 분할 원칙 중 하나를 선택해서 설계할 수 있다. 하지만 특별한 경우가 아니라면 단일 책임 원칙을 적용하는 것이 더 좋은 해결책이라고 할 수 있다.

인터페이스 분할 원칙을 이야기할 때 항상 함께 등장하는 원칙 중 하나로 인터페이스 최소주의 원칙이라는 것이 있다. 인터페이스를 통해 메서드를 외부에 제공할 때는 최소한의 메서드만 제공하라는 것이다. ArmyMember인터페이스에 anniversary라는 기념일을 챙기는 행위는 필요하지 않다. 그렇기에 제공해서는 안되는 메서드이다. 그렇기에 상위 클래스는 풍성할수록 좋고, 인터페이스는 작을 수록 좋다. 

<img src="/static/5-11.png" width="513px" height="349px"></img>

왼) 빈약한 상위 클래스                    오) 풍성한 상위 클래스

빈약한 상위 클래스인 경우 하위 클래스인 학생 클래스와 군인 클래스는 같은 속성인 생일, 주민등록번호와 같은 메서드인 자다()와 소개하다()를 공통적으로 갖고 있는 것을 볼 수 있다. 

풍성한 상위 클래스인 경우에는 상위 클래스가 하위 클래스들이 공통으로 가질 수 있는 속성과 메서드를 상속해주고 있다. 빈약한 상위 클래스일 경우의 예제를 살펴보자

```java
package poorSuperClass;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Student kimStudent = new Student("kim-gil-do", LocalDate.of(2000, 1, 1), "20000101-1234567",
                "20190001");

        People leeSoldier = new Soldier("lee-gil-do", LocalDate.of(1998, 12, 31), "19981231-1234567",
                "19-12345678");

        System.out.println(kimStudent.getBirth());
        // System.out.println(leeSoldier.getBirth()); 사용 불가

        System.out.println(((Soldier) leeSoldier).getBirth()); // 캐스팅 필요

        kimStudent.eat();
        leeSoldier.eat();

        kimStudent.sleep();
        // leeSoldier.sleep();

        ((Soldier) leeSoldier).sleep(); // 캐스팅 필요

        kimStudent.study();
        // leeSoldier.tranning(); 캐스팅 필요
    }
}
```

빈약한 상위 클래스를 이용한 경우 여기저기 형 변환이 발생하면서 상속의 혜택을 누리지 못하고 있다. 변수나 메서드를 사용할 때 일일이 객체 참조 변수에 형 변환을 하고 있는 불편함을 느끼고 있고, 저 클래스를 하나하나 까봤을 때에는 중복되는 코드들이 보이면서 눈살이 찌뿌려진다.

```java
package richSuperClass;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        People kimStudent = new Student("kim-gil-do", LocalDate.of(2000, 1, 1), "20000101-1234567",
                "20190001");

        People leeSoldier = new Soldier("lee-gil-do", LocalDate.of(1998, 12, 31), "19981231-1234567",
                "19-12345678");

        System.out.println(kimStudent.getName());
        System.out.println(leeSoldier.getName());

        System.out.println(kimStudent.getBirth());
        System.out.println(leeSoldier.getBirth());

        kimStudent.eat();
        leeSoldier.eat();

        kimStudent.sleep();
        leeSoldier.sleep();
    }
}
```

클래스를 하나하나 리펙토링을 하여 위 오른쪽 그림과 같은 형태로 바꾸었다. 전과 다르게 계속해서 형 변환을 하지 않아도 편리하게 사용할 수 있다. 

그렇다면 인터페이스 최소 주의가 가진 장점을 살펴보자. 인터페이스 분리 원칙을 적용한 남자 클래스를 다시 보고, 여자친구가 회사 상사일 경우에 업무 시간엔 상사 역할만 해야 하고, 업무 시간 외에는 상사 역할을 해선 안된다. 객체 지향은 이걸 완벽히 분류해 낼 수 있다. 그 역할에 충실한 최소한의 기능만 공개해야만 한다. 인터페이스는 ~ 할 수 있는 이라는 기준으로 만드는 것이 정석이란 것을 명심하고 설계를 해야하는 것을 명심하자.