# Day_14

## 상속과 T메모리

메모리를 살펴보기 전에 예제 코드를 작성하고 살펴보자

```java
package inheritance03;

public class Animals2 {
    private String name; // 동물 이름

    public Animals2(String name) {
        this.name = name;
    }

    public void showName() {
        System.out.printf("안녕 나는 %s 야. 반가워\n", name);
    }
}
```

```java
package inheritance03;

public class Penguin2 extends Animals2 {

    private String habitat; // 주거지역

    public Penguin2(String name) {
        super(name);
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void showHabitat() {
        System.out.printf("%s는 %s에 살아\n", habitat);
    }
}
```

```java
package inheritance03;

public class Driver03 {

    public static void main(String[] args) {
        Penguin2 pororo = new Penguin2("뽀로로");

        pororo.setHabitat("남극");

        pororo.showName();
        pororo.showHabitat();

        Animals2 pingu = new Penguin2("핑구");

        // pingu.setHaitat("EBS");

        pingu.showName();
        // pingu.showHabitat();

				// Penguin2 happyfeet = new Animals2();
    }
}
```

이러한 코드가 있다고 가정했을 때 우리는 한번 코드와 메모리 구조를 살펴보아야한다.

Penguin 클래스의 인스턴스만 힙 영역에 생기지 않고 Animal 클래스의 인스턴스도 함께 힙 영역에 생긴 걸 알 수 있다.  하위 클래스의 인스턴스가 생성될 때 상위 클래스의 인스턴스도 함께 생성되는 것이다. 그리고 한 객체가 더 생성된다. 여기선 생략 되어있지만 Object 클래스의 인스턴스도 생성된다. 

<img src="/static/3-31.png" width=514px height=355px></img>
이어서 pingu 객체까지 생성이 되면 T 메모리는 다음과 같은 구조를 띈다.

<img src="/static/3-32.png" width=511px height=411px></img>
힙 영역은 충분히 짐작 했을 것이다. 그런데 pingu 객체 참조 변수가 가리키고 있는 것은 Penguin 인스턴스가 아닌 Animals이다 

이 부분의 코드를 pororo참조변수 코드와 비교해 보면

pororo : Penguin pororo = new Penguin();

pingu : Animal pingu = new Penguin();

위에 보이는 참조 클래스 차이 때문에 pingu는 동물이라고 인식을 하고 showHabitat메서드를 사용할 수 없다. 

Penguin2 happyfeet = new Animals2();이 왜 실행되지 않을까 고민해보자 

책에는 없지만 필자가 고민했을 땐 다음과 같다.

- Penguin 클래스는 Animals2의 하위 클래스이다. 당연히 하위클래스의 객체가 상위 클래스의 객체를 참조할 수는 없는 것이다.

<img src="/static/3-번외.png" width=344px height=332px></img>

ref. TCP 스쿨 상속

위 그림과 같이 자식 클래스는 부모 클래스를 확장한 개념으로 볼 수 있다. 그렇기 때문에 부모 클래스가 자식 클래스를 참조할 수는 있지만 자식 클래스가 부모 클래스를 참조할 수는 없는 것이라 생각한다. 

추가적으로 코드를 인간적인 언어로 번역해서 읽는 다면 예시로 몇 가지를 보여줄 수 있다.

Penguin pororo = new Penguin("뽀로로", "남극");

// 펭귄 한 마리가 태어났고 펭귄 역할을 하는 pororo라 이름을 짓고 pororo의 이름은 뽀로로이고 사는 곳은 남극이다.

pororo.showName() // pororo야 너의 이름을 보여라

pororo.showHabitat() // pororo야 너가 사는 곳을 보여줘라