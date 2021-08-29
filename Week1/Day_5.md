# Day_5

## 지역 변수와 메모리 : 스택 프레임에 갇혔어요!

##### 지역 변수는 스택 프레임 안에서만 존재한다. 그래서 스택 프레임이 사라지면 함께 사라진다.

##### 클래스 멤버 변수는 스태틱 영역에서 존재한다. 한번 자리를 잡으면 JVM이 종료 될 때까지 고정된 상태로 존재한다.

##### 객체 멤버 변수는 힙에서 일생을 보낸다. 객체 멤버 변수들은 객체와 함께 Garbage Collector라고 하는 힙 메모리 회수기에 의해 일생을 마치게 된다.

##### 지역 변수에 대해 살펴 보기 위해 저번에 사용했던 Start3.java 코드를 살펴보자

```java
public class Start3 {
    public static void main(String[] args) {
        int i = 10;
        int k = 20;
				
				// error : m cannot be resolved to a variable
				System.out.println(m);

        if(i == 10) {
            int m = k + 5;
            k = m;
        } else {
            int p = k + 10;
            k = p;
        }

        // k = m + p;
    }
}
```

##### 위 코드 코드를 실행 시킨다면 어떻게 될까? 코드 위 주석처럼 m cannot be resolved to a variable 오류가 발생한다. 메모리 상에 존재하지 않은 변수이기 때문이다. 

<img src="/static/2-17.PNG" width="513px" height="349px"></img>

##### 스택 프레임 안에는 변수 m이 존재하지 않는다.

##### 그렇다면 아까 실행시킬 코드들을 줄을 주석 처리 하고 다음 코드 처럼 변경 했다고 가정해 본다.

##### 이번엔 에러가 있을까? 다음 코드를 살펴보자

```java
public class Start3 {
    public static void main(String[] args) {
        int i = 10;
        int k = 20;

        if(i == 10) {
            int m = k + 5;
            k = m;
        } else {
            int p = k + 10;
            k = p;
        }

        // error : m cannot be resolved to a variable
				System.out.println(m);
    }
}
```

##### 마찬가지로 같은 오류가 난다 왜 일까? 답은 밑에 사진에서 알 수 있다.

<img src="/static/2-21.PNG" width="513px" height="349px"></img>

##### 마찬가지로 m이 스택 프레임에 존재 하지 않는다. 이유는 간단하다 if문이 끝나면서 if 스택 프레임이 사라지면서 if 스택 프레임에 있는 변수가 같이 사라졌기 때문이다. 그렇다면 코드 위치를 바꿔보자

```java
public class Start3 {
    public static void main(String[] args) {
        int i = 10;
        int k = 20;

        if(i == 10) {
            int m = k + 5;
            k = m;
						System.out.println(m);
        } else {
            int p = k + 10;
            k = p;
        }
    }
}
```

##### 코드에 int m  = k + 5를 주목하자 if 블록 스택 프레임을 수행 하는 중에 if 블록 스택 프레임 외부에 존재하는 변수 k는 접근이 가능하다는 것이다. 메모리 상에 변수 k가 존재하니 어찌 보면 당연하다. 

##### 다른 책에서 "외부 블록에서 내부 블록의 변수에는 접근할 수 없지만 내부 블록에서 외부 블록의 변수에는 접근하는 것은 가능하다" 또는 이와 비슷한 내용을 본 적이 있을 것이다. 이는 일부러 그렇게 만든 것이 아니라 시간의 흐름, 즉 코드 진행에 따른 T 메모리의 변화를 보면 그럴 수 밖에 없다는 결론을 얻을 수 있다. 이 점을 기억해 두길 바란다.

> *"외부 스택 프레임에서 내부 스택 프레임의 변수에 접근하는 것은 불가능하나 그 역은               가능하다."*

##### 그래서 스택 메모리 내의 스택 프레임 안의 변수를 지역 변수라고 한다. 그 지역(스택 프레임)에서 만 사용할 수 있고 외부에서는 사용할 수 없기 때문이다. 또한 그 지역이 사라지면 지역 변수도 메모리에서 사라진다.