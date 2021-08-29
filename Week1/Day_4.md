# Day_4

## 블록 구문과 메모리 : 블록 스택 프레임

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

        // k = m + p;
    }
}
```

##### 위 코드를 실행 후 5번째 줄이 끝났을 때 에는 다음과 같다.

<img src="/static/2-17.PNG" width="513px" height="349px"></img>

##### 다음으로 실행해야 할 부분은 6번째 줄의 if 블록이다. if는 조건에 따라 분기를 일으킬 것이다. i 변수에 저장된 값이 10 인지 물어보고 있는데 비교 결과 값은 true다. 그러면 if ~ case 블록 중 위의 블록 중 위의 블록이 실행될 것이다. 그리고 여는 중괄호를 만나면 스택 프레임이 시작된다고 했는데 여기서 만들어지는 스택 프레임은 메서드의 스택 프레임이 아니라 if문, 그것도 참인 블록의 스택 프레임이다.

<img src="/static/2-18.PNG" width="513px" height="349px"></img>

##### main() 메서드의 스택 프레임 안에 if문의 블록 스택 프레임이 중첩되어 생성된다. 7 번째 줄을 실행하려고 보니 두 개의 명령이 하나의 문장으로 표현돼 있다.

##### 두 개의 명령문으로 분할 해 보면

##### int m;

##### m = k + 5;

<img src="/static/2-19.PNG" width="513px" height="349px"></img>

##### m = k + 5 구문은 if 스택 프레임 안의 변수 m에 값을 할당한다. 이 때 if 스택 프레임 밖에 있으면서 main() 메서드 스택 프레임 안에 있는 k 변수를 연산에 참여 시킨다.

<img src="/static/2-20.PNG" width="513px" height="349px"></img>

##### 9 번째 줄에서 if 블록 중 참 일 때  블록을 종료하는 닫는 중괄호를 만나면 if 블록 스택 프레임은 스택 영역에서 사라진다. 이 때 if 블록 스택 프레임 안에 상주 하던 변수의 저장 공간도 함께 사라진다.

<img src="/static/2-21.PNG" width="513px" height="349px"></img>

##### 9번째 줄에서 12번째 사의의 else 문은 블록 스택 메모리에 등장하지 못한다. 그리고 14번째 줄을 보면 주석 처리가 되어있는데 m 변수와 p 변수를 더해야 하는데 m 변수는 아까 if 블록 스택 프레임이 종료되면서 같이 사라졌다 그렇기 때문에 컴파일 오류가 발생한다

- m cannot be resolved to a variable(m 이라는 변수를 찾을 수 없습니다.)
- p cannot be resolved to a variable(p 라는 변수를 찾을 수 없습니다.)

##### 이제 15번째 줄은 main() 메서드 스택 프레임을 소멸 시키는 닫는 중괄호에 도착했다. 여기 서는 T메모리 소멸, JVM 가동 중지 JRE가 사용했던 시스템 자원을 운영체제에 반납하게 된다.