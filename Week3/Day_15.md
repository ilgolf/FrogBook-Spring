# Day_15

## 다형성: 사용편의성

객체 지향에서 다형성이라고 하면 오버라이딩과 오버로딩이라고 할 수 있다. 물론 상위 클래스와 하위 클래스 사이에서도 다형성을 이야기할 수 있고, 인터페이스와 그것의 구현 클래스 사이에서도 다형성을 이야기할 수 있지만 가장 기본은 오버라이딩과 오버로딩이라고 할 수 있다.

(참고로 오버로딩이 다형성인지 아닌지에 대해서는 이견이 있다.)

오버라이딩? 오버로딩?

다음이 오버라이딩인지 오버로딩인지 한번 맞춰 보자

- 같은 메서드 이름, 같은 인자 목록으로 상위 클래스의 메서드를 재정의
- 같은 메서드 이름, 다른 인자 목록으로 다수의 메서드를 중복 정의

예를 들면 ride = 올라타다, load = 적재하다 라는 관점을 기억하고 다음 글을 보면 

인공위성에서 내려볼 때 오버라이딩(올라타기)된 경우는 맨 위에 올라탄 존재만 보인다. 오버로딩(적재하기)된 경우는 옆으로 적재된 모든 적재물이 다 보인다. 라고 예시를 들 수 있다.

이제 코드를 보자

```java
package polymorphism01;

public class Animals3 {

    private String name;

    public Animals3(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void showName() {
        System.out.printf("안녕 나는 %s야. 반가워\n", name);
    }
}
```

```java
package polymorphism01;

public class Penguin3 extends Animals3 {

    private String habitat;

    public Penguin3(String name, String habitat) {
        super(name);
        this.habitat = habitat;
    }

    public void showHabitat() {
        System.out.printf("%s는 %s에 살아\n", this.getName(), habitat);
    }

    @Override
    public void showName() {
        System.out.println("어머 내이름을 알아서 뭐하게요?");
    }

    public void showName(String yourname) {
        System.out.printf("%s 안녕, 나는 %s라고 해\n", yourname, this.getName());
    }
}
```

```java
package polymorphism01;

public class DriverPol01 {

    public static void main(String[] args) {
        Penguin3 pororo = new Penguin3("뽀로로", "남극");

        pororo.showName();
        pororo.showName("초보 람보");
        pororo.showHabitat();

        Animals3 pingu = new Penguin3("핑구", "EBS");
        pingu.showName();
    }
}
```

실행 내용은 다음과 같다 .

> 어머 내 이름은 알아서 뭐하게요?
초보 람보 안녕, 나는 뽀로로라고 해
뽀로로는 남극에 살아
어머 내 이름은 알아서 뭐하게요?

다형성과 T 메모리

바로 위에 예제에서 보면 

Penguin3 pororo = new Penguin3("뽀로로", "남극"); 라인을 살펴보면 다음 처럼 나온다. 

<img src="/static/3-34.png" width=514px height=355px></img>

그림에서 주목할 점은 Penguin 클래스가 상위 클래스인 Animal 클래스의 showName() 메서드를 오버라이딩(재정의)했다는 것과 showName(yourName : String) 메서드를 오버로딩(중복 정의) 했다는 것이다.

pororo.showName(); 부분을 실행하면 Animals 객체에 있는 showName() 메서드는 Penguin 객체에 있는 showName() 메서드에 의해 재정의, 즉 가려졌기에 Penguin 객체에서 재정의한 showName() 메서드가 showName() 메서드가 호출되어 다음과 같은 결과를 출력한다.

*"어머 내이름은 알아서 뭐하게요?"*

그 다음 줄인 pororo.showName("초보 람보") 부분을 실행하면 문자열 하나를 인자로 받는 중복 정의된 showName(yourname) 메서드를 호출한다.

*"초보 람보 안녕, 나는 뽀로로 라고해"*

<img src="/static/3-35.png" width=514px height=355px></img>

그림에서 주목할 것은 pingu 객체 참조 변수는 타입이 Animal 타입이라는 것이다. 그럼에도 그림에서 보면 Animal 객체의 showName()은  Penguin 객체의 showName()에 의해 가려져 있다. 따라서 pingu.showName() 메서드를 실행하면 Animals 객체에 정의된 showName() 메서드가 아닌 Penguins 객체에 의해 재정의 된 showName()메서드가 실행된다.

*"어머 내이름은 알아서 뭐하게요?"*

상위 클래스 타입의 객체 참조 변수를 사용하더라도 하위 클래스에서 오버라이딩(재정의)한 메서드가 호출된다는 사실을 꼭 기억하자