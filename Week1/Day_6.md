# Day_6

## 메서드 호출과 메모리 : 메서드 스택 프레임2

이번에는 새로운 코드로 T 메모리의 변화를 살펴보자

```java
public class Start4 {
    
    private static int square(int k) {
        int result;
        
        k = (int)Math.pow(k, 2);

				result = k;

        return result;
    } 
    
    public static void main(String[] args) {
        int k = 5;
        int m;

        m = square(4);

        System.out.println(k);  // 5
        System.out.println(m);  // 16
    }
}
```

이 코드는 확실해 여태 살펴봤던 코드와 다르다. 먼저 5번째 줄이 실행되고 끝났을 때 T메모리는 다음과 같다.

<img src="/static/2-27.PNG" width="513px" height="349px"></img>

m = square(4)에서 square() 메서드를 호출 하고 있다. square() 메서드는 인자 값도 있고 반환 값도 있다. 제어 흐름이 square() 메서드가 선언된 int square(int k)가 있는 줄로 이동할 것이다.

k = 25까지 실행이 끝났을 때 T 메모리 SNAPSHOT인 그림을 보면 메서드 호출이 일어나면 무조건 호출되는 메서드의 스택 프레임이 T 메모리 스택 영역에 새로 생성된다. k = 25 라인에 의해 생성되는 square() 메서드 스택 프레임에는 반환 값을 저장할 변수 공간이 맨 아래, 그 다음으로 인자를 저장할 변수 공간, 그리고 마지막으로 메서드의 지역 변수가 자리 잡는다.

<img src="/static/2-28.PNG" width="513px" height="349px"></img>

반환 값이라고 돼 있는 부분은 square() 메서드가 종료 되면서 반환해 줄 값을 갖고 있는 가상의 변수다.

그러면 그 다음 줄을 실행해 보자

<img src="/static/2-29.PNG" width="513px" height="349px"></img>

주목해야 할 것은 main() 메서드가 가진 변수 k와 square() 메서드가 가진 변수 k가 이름만 같이 실제로는 다른 별도의 변수 공간이라는 것이다. **이것을 전문 용어로 Call By Value(값에 의한 호출)** 이라고 한다. 그래서 서로 독립된 환경에 존재하기 때문에 영향을 주지 않는다.

그 다음 줄을 실행하고 나면 result에 16이란 값이 저장된다.  그 후에 return result를 하면서 result에 담긴 값을 반환해 준다.

<img src="/static/2-30.PNG" width="513px" height="349px"></img>

메서드가 중괄호를 만나 끝나게 되면 square()는 메서드 스택 프레임은 스택에서 사라진다. 반환 값은 스택 프레임이 사라지면서 반환해 준다. 

m = square(4);

결국 이 줄은 실행 아래와 같이 된다.

<img src="/static/2-31.PNG" width="513px" height="349px"></img>

반환 값인 16이 m변수에 담겨있다.

그리고 닫는 중괄호를 만나면 역시 main()메서드 또한 사라지면서 모든 프로그램이 종료된다.

여기서 의문점이 생긴다. main() 메서드의 어디서 인가 square() 메서드 내의 지역 변수 result에 직접 접근할 수 있을까? 또는 반대로 square() 메서드의 어디에서 인가 main()메서드의 지역 변수 m에 접근 할 수 있을 까? 절대 접근할 수 없다.

메서드를 블랙 박스화 한다는 말을 들어본적 있을 것이다. 이것은 입력 값들(인자 리스트)과 반환 값에 의해서만 메서드 사이에서 값이 전달될 뿐 서로 내부의 지역 변수를 볼 수 없다는 것을 의미한다.