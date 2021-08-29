# Day_9

## 객체 지향은 인간 지향이다

기존의 언어 어셈블리어, C, C ++은 모두 인간을 배려하기 위한 언어의 발전에 과정이었다. 그렇지만  절차적/구조적 프로그래밍까지의 과정은 인간이 기계를 이해하려는 노력에서 크게 벗어나지 못했다.

그렇다면 우리가 왜 기계 종속적인 개발을 해야하는가? 라는 의문이 생긴다. 우리는 좀 더 현실 세계와 가까운 프로그래밍을 원한다.

### 객체 지향 프로그래밍

객체 지향은 위 고민에서 탄생한 프로그래밍 언어로 현실 세계를 반영한다는 개념으로 탄생한 프로그래밍이다. 그렇다면 현실 세계를 반영했다는 증거는 무엇일까? 바로 객체(Object) 이다.

### 객체(Object)

 기존의 구조적 프로그래밍 언어에서 가장 중요한 것은 "함수"였다. 함수는 코드를 논리적인 단위로 구분하고 분할해서 정복하자는 것이다. 그렇지만 이보다 더 파격적인 제안이 나왔다 바로 객체이다. 현실 세계를 둘러보면 모든 것은 하나의 객체부터 시작하여 객체들이 모여 만들어 진 것처럼 보인다. 그리하여 이 모든 주변을 인지하는 것처럼 프로그래밍을 해보자 라고 시작 된게 객체 지향이고 그래서 객체 지향은 직관적이다.

객체 지향을 이해하기 위해 큰 그림을 그려보자

- 세상에 존재하는 모든 것은 사물, 즉 객체(Object)다.
- 각각의 사물은 고유하다.
- 사물은 속성을 갖는다.
- 사물은 행위를 한다.

그리고 사물을 하나하나 이해하기 보다는 사물을 분류(class)해서 이해하는 것이 인간의 인지법이다.

- 직립보행을 하며 말을하는 존재를 사람이라 분류한다.
- 연미복, 짧은다리, 날지못하는 새를 펭귄이라고 분류한다.
- 밤하늘에 반짝이는 사물들을 별이라고 분류한다.

예를 들어 김종민(object), 한효주(object), 김연아(object)라고 하는 존재는 사람이라는 분류에 속한다. 그리고 사람이라는 분류 안의 객체(object) 들은 나이, 몸무게, 키 등의 속성(property)과 먹다, 자다, 뛰다 등의 행위(method)를 갖고 있다.

```java
public class People {
    
    // property(속성)
    private int age;
    private int weight;
    private int height;
    
    public People(int age, int weight, int height) {
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
    
		// 행위(동작)
    void run() {   
        weight --;
        height ++;
    }

    void eat() {
        weight ++;
    }

    void sleep() {
        weight ++;
        height ++;
    }

    @Override
    public String toString() {
        return "People [age=" + age + ", height=" + height + ", weight=" + weight + "]";
    }
}
```

```java
public class Main {
    
    public static void main(String[] args) {
        People kimjongmin = new People(19, 80, 182);  // 각 객체생성
        People hanheoju = new People(20, 48, 168);
        People kimyeonah = new People(21, 51, 172);

        kimjongmin.run();  // 클래스는 설계도일 뿐! 객체가 생성되어 동작해야 실제 동작이 된다!
        hanheoju.eat();
        kimyeonah.sleep();

        System.out.println("김종민 = " + kimjongmin.toString());
        System.out.println("김연아 = " + kimyeonah.toString());
        System.out.println("한효주 = " + hanheoju.toString());
    }
}
```

위 코드는 실제 객체 지향적인 방식으로 만든 예제 코드이다 객체 지향에서는 우리가 주변에서 실제 사물을 인지 및 사고하는 방식대로 객체 단위의 프로그래밍이 가능하다. 객체 지향은 인간의 인지 및 사고 방식까지 프로그래밍에 접목하는 인간(개발자) 지향을 실천하고 있다고 볼  수 있다.