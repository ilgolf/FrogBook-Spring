# Day_20

## SRP - 단일 책임 원칙

> "어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다." - 로버트 C. 마틴
> 

위 글만 보면 무엇을 말하는지 이해가 안될 수 있다. 다음 그림을 보며 생각해보자.

<img src="/static/5-1.PNG" width="513px" height="349px"></img>

남자는 피곤해질 수 밖에 없는 구조이다. 이유는 역할과 책임이 너무 많기 때문이다. 예를 들어 여자 친구가 없어지면 기념일 챙기는 대상 키스할 대상이 없어지기고 힘들어진다. 이 책임을 직장 상사 소대장에게도 영향이 가게 되면 모두가 피곤해지는 현상이 생기는 것이다. 이러한 경우에 역할(책임)을 분리하라는 것이 단일 책임 원칙이다.

다음과 같이 바꾸면 좀 더 편해질 것이다.

<img src="/static/5-2.PNG" width="513px" height="349px"></img>

남자라는 하나의 클래스가 역할과 책임에 따라 네 개의 클래스로 쪼갠 것을 볼 수 있다. 그리고 역할과 클래스명도 떨어지니 이해하기도 좋다. 이제 여자 친구와 이별하더라도 남자 친구만 상처를 입으면 된다. 서로 독립된 영향을 받지 않게 된 것이다. 

사실 객체 지향에 대해서만 얘기했지만 단일 책임 원칙은 속성, 메서드, 패키지, 모듈, 컴포넌트 프레임워크 등에도 적용할 수 있는 개념이다.

단일 책임 원칙은 잘된 경우보다 잘못된 경우를 살펴보는 것이 이해하는 데 좋다. 예를 들어 객체 지향 세계에선 남자는 반드시 군대를 가고 여자는 절대 안 간다고 가정하고 사람 클래스에 군번 속성이 있다면 어떻게 될까?

```java
class People {
	String 군번;
	...
}

...

People 로미오 = new People();
People 줄리엣 = new People();

줄리엣.군번 = "1573042009"; // ??
```

보면 People형 참조 변수 줄리엣이 가진 군번 속성에 값을 할당하거나 읽어오는 코드를 제제할 방법이 없다. 문제가 있는 코드인 것이다.

그러면 이 코드를 좋은 코드로 리팩토링 해보자. 사람 클래스를 남자 클래스와 여자 클래스로 분할하고 남자 클래스에만 군번 속성을 갖게 하면 정상적인 코드로 변모시킬 수 있다. 바로 단일 책입 원칙을 적용하는 것이다. 이때 남자 클래스와 여자 클래스에 공통점이 없다면 사람 클래스에 두고 남자 클래스와 여자 클래스는 사람 클래스를 상속하고 차이점만 각자 구현하면 된다.

```java
package SRP;

public class People {
    private int age;
    private int height;
    private int weight;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
```

```java
package SRP;

public class Man extends People {
    private int armyNum;

    public int getArmyNum() {
        return armyNum;
    }

    public void setArmyNum(int armyNum) {
        this.armyNum = armyNum;
    }
}
```

```java
package SRP;

public class Woman extends People{

}
```

```java
package SRP;

public class Run {

    public static void main(String[] args) {
        Man 로미오 = new Man();
        Woman 줄리엣 = new Woman();

        로미오.setArmyNum(123488);
        // 줄리엣.setArmyNum(479745);

        System.out.println(로미오.getArmyNum());
        // System.out.println(줄리엣.getArmyNum());
    }
}
```

코드를 보면 더 깔끔하게 남자는 남자만 여자는 여자에 대한 책임을 갖기 때문에 로미오만 군번이라는 속성을 갖을 수 있게 된다. 

이때 남자 클래스 여자 클래스에 공통점이 없다면 사람 클래스는 제거하면 되고, 공통점이 많다면 위 코드와 같이 사람 클래스를 상위 클래스로 해서 공통점을 사람 클래스에 묶고 차이점만 따로 구분하여 구현하면 된다.

하나의 속성이 여러 의미를 갖는 경우도 단일 책임 원칙을 지키지 못하는 경우다. 예를 들어 하나의 속성이 여러 의미를 지닌다면 자바 코드에 if문을 여기저기서 사용해야 하기 때문에 불편할 것이다. 그렇기에 테이블을 설계할 때도 단일 책임 원칙을 고려해야 한다. 

그렇다면 메서드가 SRP를 지키지 못하는 경우를 살펴보자 같이 강아지 클래스를 만들고 pee() 메서드를 구현했다고 해보자.

```java
package SRP;

public class Dog {
    private final boolean male = true;
    private final boolean female = false;
    private boolean gender;

    public boolean isMale() {
        return male;
    }

    public boolean isFemale() {
        return female;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    void pee() {
        if(this.gender == male) {
            System.out.println("raise one lag");
        }
        else {
            System.out.println("sit down");
        }
    }
}
```

강아지가 수컷이냐 암컷이냐에 따라 pee() 메서드에서 분기 처리가 진행되는 것을 볼 수 있다. 강아지 클래스의 pee() 메서드가 수컷 강아지의 행위와 암컷 강아지의 행위를 모두 구현하려고 하기에 단일 책임 원칙을 위배하고 있는 것이다. 이런 경우 다음처럼 리펙토링 해줘야 할 필요가 있다.

```java
package SRP;

public abstract class Dog {
    abstract void pee();
}

class MaleDog extends Dog {

    @Override
    void pee() {
        System.out.println("raise one lag");
    }
}

class FemaleDog extends Dog {

    @Override
    void pee() {
        System.out.println("sit down");
    }
}
```

몇 가지 사례들을 통해 단일 책임 원칙이 얼마나 중요한지 알아보았다. 

단일 책임 원칙과 객체 지향 4대 특성은 어떻게 결부돼 있을까? 캡상추다를 다시 상기해 보면 단일 책임 원칙과 가장 관계가 깊은 것은 바로 모델링 과정을 담당하는 추상화임을 알 수 있다. 

애플리케이션의 경계를 정하고 추상화를 통해 클래스를 선별하고 속성과 메서드를 설계할 때 반드시 단일 책임 원칙을 고려하는 습관을 들이자. 또한 리펙토링을 통해 코드를 개선할 때에도 단일 책임 원칙을 적용할 곳이 있는지 꼼꼼히 살펴보자