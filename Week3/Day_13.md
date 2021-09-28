# Day_13

## 상속 : 확장 + 재사용

객체 지향에서의 상속은 영어 단어를 그대로 옮기면서 생긴 오해이다. 그 오해가 받아들여지면서 더욱 많은 오해를 낳았다. 객체 지향에서의 상속은 재사용과 확장으로 이해 해야한다.

객체 지향에서의 상속은 상위 클래스의 특성을 하위 클래스에서 상속은 상위 클래스의 특성을 하위 클래스에서 상속하고(특성 상속) 거기에 더해 필요한 특성을 추가, 즉 확장해서 사용할 수 있다는 의미이다.

그래서 부모 클래스 자식클래스의 관계라고 표현하기보다 상위 클래스 하위 클래스 또는 슈퍼 클래스 서브 클래스로 표현해보자

<img src="/static/3-25.PNG" width="513px" height="323px"></img>

위 분류도를 확인해보면 동물의 특성을 확장한 것을 포유류 포유류의 특성을 확장한 것을 고래 박쥐 등으로 표현 할 수 있고 그 반대로 올라가면 추상화의 개념으로 설명할 수 있다. 우리는 이것이 조직도가 아닌 분류도라는 사실을 기억하자

상속 관계에서 반드시 만족해야 할 문장이 있다.

- 하위 클래스는 상위 클래스다.

객체 지향 상속에 있어서 아주 아주 중요한 문장이니 기억해야 한다. 

이제 개발자 코드로 표현해서 설명해 보자

아버지 영희아빠 = new 딸();

// 딸을 낳으니 아버지 역할을 하는 영희아빠라 이름 지었다? 

동물 뽀로로 =  new 펭귄();

// 펭귄을 낳으니 동물 역할을 하는 뽀로로라 이름을 지었다.

문장이 매끄럽진 않지만 밑에 내용이 충분히 더 말이 된다.  여기에 더해 한 가지 재미있는 것은 자바 언어에서 inheritance라는 키워드는 존재하지 않는 것이다. 대신 extends가 존재한다. 자바에서도 이 객체 지향을 완벽히 이해하고 있는 것이다.

## 상속의 강력함

객체에 특성은 유일무이한 것이다.  클래스명은 클래스명답게, 객체 참조 변수 명은 객체 답게 지어야 한다. 이걸 풀어서 설명하자면 클래스명은 클래스답게, 객체 참조 변수 명은 유일무이한 사물처럼 클래스명은 분류스럽게 작명해야한다.

```java
package inheritance01;

public class Animals {
    String myClass;

    public Animal(String myClass) {
        this.myClass = myClass;
    }

    void showMe() {
        System.out.println(myClass);
    }
}
```

```java
package inheritance01;

public class Birds extends Animals {

    public Bird(String myClass) {
        super("bird");
    }
}
```

```java
package inheritance01;

public class Mammalia extends Animals {
    
    public Mammalia() {
        super("mammalia");
    }
}
```

```java
package inheritance01;

public class Whale extends Mammalia {
    
    Whale() {
        myClass = "Whale";
    }
}
```

```java
package inheritance01;

public class Bat extends Mammalia {

    public Bat() {
        myClass = "Bat";
    }
}
```

```java
package inheritance01;

public class Sparrow extends Birds {

    public Sparrow(String myClass) {
        super("Sparrow");
    }
}
```

```java
package inheritance01;

public class Penguin extends Birds {

    public Penguin(String myClass) {
        super("penguin");
    }
}
```

객체 참조 변수명은 객체스럽게, 클래스명은 클래스명 답게 정하는 습관을 위 코드 처럼 습관을 들이길 강조한다.

Birds bird = new Birds();  // 잘못된 예시이다. 

bird는 단어가 분류에 가깝기 때문에 절대 이런 방식으로 객체를 생성해선 안된다. 

> 조류의 나이는? 답할 수 있겠는가? 그래서 클래스다 !
bird의 나이는 ? 역시 답하기 어렵다. 그래서 클래스다 !!

이어서 그러면 상속의 강력함을 보여줄 첫 예제 코드를 확인하자

```java
package inheritance01;

public class Driver01 {
    
    public static void main(String[] args) {
        Animals animal = new Animals("동물");
        Mammalia mammalia = new Mammalia("포유류");
        Birds bird = new Birds("새");
        Whale whale = new Whale("고래");
        Bat bat = new Bat("박쥐");
        Sparrow sparrow = new Sparrow("참새");
        Penguin penguin = new Penguin("펭귄");

        animal.showMe();
        mammalia.showMe();
        bird.showMe();
        whale.showMe();
        bat.showMe();
        sparrow.showMe();
        penguin.showMe();
    }
}
```

위 코드를 실행시키면 알 수 있을 것이다. 상위 클래스에서만 showMe() 메서드를 구현했지만 모든 하위 클래스 객체에서 showMe() 메서드를 사용할 수 있다. 상속한다는 것이 이렇게 상위 클래스의 특성을 상속한다는 의미이지 부모-자식 관계는 아니라고 할 수 있다.

상속을 받음으로써 하위 클래스에서 더 이상 showMe() 메서드를 다시 작성할 필요가 없는 것이다!

```java
package inheritance01;

public class Driver02 {
    
    public static void main(String[] args) {
        Animals animal = new Animals("동물");
        Animals mammalia = new Mammalia("포유류");
        Animals bird = new Birds("새");
        Animals whale = new Whale("고래");
        Animals bat = new Bat("박쥐");
        Animals sparrow = new Sparrow("참새");
        Animals penguin = new Penguin("펭귄");

        animal.showMe();
        mammalia.showMe();
        bird.showMe();
        whale.showMe();
        bat.showMe();
        sparrow.showMe();
        penguin.showMe();
    }
}
```

실행 결과는 Driver01 코드와 같다. 하위 클래스는 상위 클래스다. 즉, 하위 분류는 상위 분류다 라는 말이 코드에서 어떻게 표현되는지 유심히 살펴보자. 고래 한 마리를 포유류 또는 동물, 포유류 한 마리를 동물이라고 하는데 우리는 이견이 없을 것이다. 즉, 이처럼 객체 지향은 현실 세계를, 인간의 논리를 그대로 코드로 옮길 수 있는 힘이 있다.

```java
package inheritance01;

public class Driver03 {
    
    public static void main(String[] args) {
        Animal[] animals = new Animal[7];
        
        animals[0] = new Animal("동물");
        animals[1] = new Mammalia("포유류");
        animals[2] = new Bird("새");
        animals[3] = new Whale("고래");
        animals[4] = new Bat("박쥐");
        animals[5] = new Sparrow("참새");
        animals[6] = new Penguin("펭귄");

        for(Animal animal : animals) {
            animal.showMe();            
        }
    }
}
```

## 상속은 is a 관계를 만족해야 한다.

여기서 상속에 대한 오해가 한 가지 더 있다.  다음 문장을 확인해 보자

펭귄 is a 동물

"펭귄은 한 마리의 동물이다." 하지만 이 글을 보면 한 마리의 동물이 클래스일까? 의문이 든다. 정답은 아니다. 한 마리의 동물은 객체이다. 그렇기 때문에 우리는 수정할 필요가 있다.

하위 클래스 is a 상위 클래스

번역하면 하위 클래스는 하나의 상위 클래스이다? 하위 클래스는 분류/집단이다. 상위 클래스도 분류/집단이다. 그러나 하나의 상위 클래스는 하나의 객체다.  삼단 논법에 의거하면 하위 클래스는 하나의 객체다 라는 결론에 도달해버린다. 결국 상속관계는 is a 관계로 많이 설명되고 있지만 명확한 표현이 있다.

바로 is a kind of라는 표현이다. 

- 하위 클래스 is a kind of 상위 클래스
- 펭귄 is a kind of 조류 → 펭귄은 조류의 한 분류다.
- 고래 is a kind of 동물 → 고래는 동물의 한 분류다.

좀 더 좋은 표현이 완성 되었다.

- 객체 지향의 상속은 상위 클래스의 특성을 재사용하는 것이다.
- 객체 지향의 상속은 상위 클래스의 특성을 확장하는 것이다.
- 객체 지향의 상속은 is a kind of 관계를 만족해야만 한다.

이 세 문장 만큼은 꼭 기억하자

## 상속과 인터페이스

자바는 extends를 통한 다중 상속을 지원하지 않는다 이유는 다 알 것이라고 생각하고 진행하겠다.

하지만 다중 상속의 이점을 포기할 수 없는 자바에서는 클래스가 아닌 Interface를 통하여 이 다중 상속을 지원한다. 

그렇다면 상속과 같이 Interface또한 상속과 같이 is a kind of 일까? 사실 그렇게 생각해도 무관하지만 인터페이스는 상속과 다르게 쓰는 것이 유용하다는 결론에 닿을 수 있다.

- 인터페이스 : 구현 클래스 is able to 인터페이스
- 해석 : 구현 클래스는 인터페이스 할 수 있다.
- 예제 : 고래는 헤엄칠 수 있다.

인터페이스는 be able to, 즉 "무엇이든 할 수 있는" 이라는 표현 형태로 만드는 것이좋다. 자바 API를 살펴봐도 이런 형식의 인터페이스를 볼 수 있다.

- Serializable : 직렬화 할 수 있는
- Cloneable : 복제할 수 있는
- Comparable : 비교할 수 있는
- Runnable : 실행할 수 있는

여기서 클래스가 "무엇을 할 수 있다." 라고 하는 기능을 구현하도록 강제하게 된다. 그렇다면 생각해보자 

- 상위 클래스는 하위 클래스에게 물려줄 특성이 많을수록 좋을까? 적을수록 좋을까?
- 인터페이스는 구현을 강제할 메서드가 많을수록 좋을까? 적을수록 좋을까?

정답은 상위 클래스는 물려줄 특성이 풍성할수록 좋고, 인터페이스는 구현을 강제할 메서드의 개수가 작을 수록 좋다는 결론에 도달할 수 있다. 나중에 이건 SOLID에서 더 다뤄볼 예정이다.

```java
package inheritance02;

public interface AbleFly {
    void fly();
}
```

```java
package inheritance02;

public interface AbleSwim {
    void swim();
}
```

```java
package inheritance02;

public class Whale extends Mammalia implements AbleSwim {

    public Whale(String myClass) {
        super(myClass);
    }

    @Override
    public void swim() {
        System.out.println(myClass + "Swimming. 어푸 어푸");
    }
}
```

```java
package inheritance02;

public class Sparrow extends Birds implements AbleFly {

    public Sparrow(String myClass) {
        super(myClass);
    }

    @Override
    public void fly() {
        System.out.println(myClass + "날고 있음 파닥 파닥");
    }
}
```

```java
package inheritance02;

public class Penguin extends Birds implements AbleSwim {

    public Penguin(String myClass) {
        super(myClass);
    }

    @Override
    public void swim() {
        System.out.println(myClass + "헤엄치고 있음 푸악 푸악");        
    }
}
```

```java
package inheritance02;

public class Bat extends Mammalia implements AbleFly {

    public Bat(String myClass) {
        super(myClass);
    }

    @Override
    public void fly() {
        System.out.println(myClass + "flying 슈웅 ~ ");        
    }
    
}
```

```java
package inheritance02;

public class Driver {
    
    public static void main(String[] args) {
        AbleFly fly1 = new Bat("박쥐");
        fly1.fly();

        AbleFly fly2 = new Sparrow("참새");
        fly2.fly();

        AbleSwim[] swims = new AbleSwim[2];

        swims[0] = new Whale("고래");
        swims[1] = new Whale("펭귄");

        for(AbleSwim swim : swims) {
            swim.swim();
        }
    }
}
```

기존 코드에 interface를 추가하여 할 수 있는 행동들을 추가해주었다. 또 한 구현을 통해 다중 상속이 되어 고래는 헤엄을 치는 행위를 포함하여 각 객체 들 또한 행위를 부여받은 것을 확인할 수 있다.