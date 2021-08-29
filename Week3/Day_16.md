# Day_16

## 캡슐화: 정보은닉

자바에서 정보 은닉이라고 하면  접근 제어자인 private, [default], protected, public이 생각 날 것이다.

그리고 접근 자 및 설정 자 메서드도 생각 날 거라 생각한다.

접근 제어자가 객체 멤버와 쓰일 때 정적 멤버(클래스 멤버)와 함께 쓰일 때를 비교 해보면

### 객체 멤버의 접근 제어자

너무나 당연한 이야기이지만 자신의 멤버가 아닌 다른 객체 멤버에 접근하는 경우에는 다른 객체를 생성한 후 접근해야 한다. UML 클래스 다이어그램을 살펴 보자

<img src="/static/3-36.PNG" width="126" height="182"></img>

ClassA의 객체 멤버인 pi, def, pro, pub 속성이 보인다. UML 표기법에서 - 표시는 priavte 접근 제어자를 ~ 표시는 [defualt]를 # 표시는 protected 접근 제어자를 + 표시는 public을 뜻한다.

속성이나 메서드 아래에 __(밑줄)을 사용한 경우는 정적 멤버를 나타낸다.

그렇다면 이 ClassA를 코드로 나타내보자

```java
package encapsulation01.packageOne;

public class ClassA {

    private int pri;  // 현재 클래스 내에서만 사용 가능
    int def; // 서브 클래스와 같은 패키지 내에선 접근 가능
    protected int pro; // 같은 패키지 서브 클래스만 접근 가능
    public int pub; // 모든 곳에서 접근 가능

    void runSomeThing() {

    }

    static void runStaticSomeThing() {

    }
}
```

ClassA의 runSomeThing() 메서드에서 접근할 수 있는 범위는 주석으로 달아 놓았다.

하지만 마냥 필자가 적어 놓은 주석처럼 단순하게 정의하기엔 문제가 있다. 특히 객체 멤버에 대한 접근인가, 정적 멤버에 대한 접근 인가에 따라 생각할 것이 많아진다.

그리고 protected가 자신과 상속 관계에 있는 서브 클래스만 접근 가능한 걸로 착각하는 경우가 많은데, 같은 패키지라면 한 집에 산다고 생각하기에 접근이 가능하다는 사실을 꼭 기억하자.

습관적으로 private 아니면 public만 사용하거나, 그냥 아무 표시도 하지 않은 [default]만 사용해 왔다면 반성하자. protected가 같은 패키지 내의 모든 클래스에서 접근이 가능하다고 하면 도 깊이 생각해 볼 문제가 있다. aaa.jar packageOne 패키지가 있고, bbb.jar 파일 안에 같은 이름인 packageOne이 있다고 가정하자 그렇다면 어떤 문제가 생길까?

먼저 bbb.jar 파일 내부의 packageOne 패키지 내 클래스나 객체에서 aaa.jar 파일 내부의 packageOne 패키지 내 클래스나 객체가 가진 public 멤버 뿐만 아니라 [default] 멤버와 protected 멤버에 자유롭게 접근할 수가 있다. 

그렇다면 정적 멤버인 public 경우는 어떻게 접근하는지 알아보자

<img src="/static/3-37.PNG" width="484" height="132"></img>

정적 멤버인 경우 클래스명, 정적멤버 형식으로 접근해야 할 이유가 느껴질 것이다. 바로 일관된 형식으로 접근하기 위해서다. 그리고 객체를 생성한 경우에는 객체 참조변수명. 정적멤버 형태로도 접근할 수도 있다.

### 참조 변수의 복사

기본 자료형 변수를 복사하는 경우 "2.6 메서드 호출과 메모리: 메서드 스택 프레임2"에서 살펴본 대로 Call By Value(값에 의한 호출)에 의해 그 값이 복사 되며 두 개의 변수는 서로에게 영향을 주지 않는다.

```java
package reference;

public class CallByName {

    public static void main(String[] args) {
        int a = 10;
        int b = a;

        b = 20;

        System.out.println(a); // 10
        System.out.println(b); // 20
    }
}
```

위 코드를 보면 변수 a에 10을 대입한 후, 변수에 a가 가진 값이 단순히 a와 v에 복사된 것이고 a와 b는 아무런 관계가 없는 것을 알 수 있다.

그렇다면 기본 자료형이 아닌 객체를 저장하고 있는 객체 참조 변수를 복사하는 경우는 어떨까? Call By Reference(참조에 의한 호출)으로 Call By Value랑은 다르게 설명한다. 밑에 코드를 보면 확연한 차이를 볼 수 있다.

```java
package reference;

class Animal {
    public int age;
}

public class CallByReference {

    public static void main(String[] args) {
        Animal refA = new Animal();
        Animal refB = refA;

        refA.age = 10;
        refB.age = 20;

        System.out.println(refA);
        System.out.println(refB);
    }
}
```

뭔가 큰 차이가 있어 보이지만 본질적으로 차이가 없다. 다만 차이라면 기본 자료형 변수는 저장하고 있는 값을 그 값 자체로 해석하는 반면, 참조 변수는 저장하고 있는 값을 주소로 해석한다는 차이가 있을 뿐이다. 

<img src="/static/3-42.PNG" width="525" height="145"></img>

<img src="/static/3-44.PNG" width="528" height="146"></img>

위 그림을 보면 주소가 둘 다 100번지로 가정했을 때 두 참조 변수는 결국 같은 객체를 참조하게 되고 그렇게 때문에 값이 둘 다 같이 바뀌는 것을 알 수 있다.

하지만 객체에 포커스를 두면  Value에 의해 변수를 복사하든 Reference에 의해 변수를 복사하든 결국은 변수가 가진 값이 그대로 복사 된다는 것을 알 수 있다. 다만 그 값을 값 자체로 해석하느냐 아니면 주소 값으로 해석하느냐의 차이일 뿐이다.

우리는 다음과 같이 Call By Value와  Call By Reference를 다르다고 이해하기 보다는 기본 자료형 변수는 저장하고 있는 값을 그 값 자체로 판단하고, 참조 변수는 저장하고 있는 값을 주소로 판단하고 이해하는 것이 더 쉽다.

- 기본 자료형 변수는 값을 값 자체로 판단한다.
- 참조 자료형 변수는 값을 주소, 즉 포인터로 판단한다.
- 기본 자료형 변수를 복사할 때, 참조 자료형 변수를 복사할 대 일어나는 일은 같다.

      즉, 가지고 있는 값을 그대로 복사해서 넘겨준다.