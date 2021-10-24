# Day_32

## 템플릿 콜백 패턴(견본 회신 패턴)

탬플릿 콜백 패턴은 전략 패턴의 변형으로, 스프링의 3대 프로그래밍 모델 중 하나인 DI(의존성 주입)에서 사용하는 특별한 형태의 전략 패턴이다. 

템플릿 콜백 패턴은 전략 패턴과 모든 것이 동일한데 전략을 익명 내부 클래스로 정의해서 사용한다는 특징이 있다.

 그렇다면 좀 다르게 이번엔 간단한 자바 프로그램을 통해 알아가보자

```java
package templete;

import java.io.BufferedReader;
import java.io.IOException;

// 콜백 인터페이스
public interface BufferedReaderCallback {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
```

```java
package templete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

		// 템플릿 메소드
    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            return callback.doSomethingWithReader(br);
        }catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if(br != null) {
                try { br. close();}
                catch(IOException e) {System.out.println(e.getMessage());}
            }
        }
    }

		// 템플릿/콜백을 적용한 더하기 메소드
    public Integer calcSum(String filepath) throws IOException {
        BufferedReaderCallback sumCallback = br -> {
            int sum = 0;
            String line;

            while((line = br.readLine()) != null) {
                sum += Integer.parseInt(line);
            }
            return sum;
        };
        return fileReadTemplate(filepath, sumCallback);

    }

		// 템플릿/콜백을 적용한 곱하기 메소드
    public Integer calcMultiply(String filepath) throws IOException {
        BufferedReaderCallback sumCallback = br -> {
            int multiply = 1;
            String line;

            while((line = br.readLine()) != null) {
                multiply *= Integer.parseInt(line);
            }

            return multiply;
        };

        return fileReadTemplate(filepath, sumCallback);
    }
}
```

```java
package templete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;
    String numFilepath;

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = Objects.requireNonNull(getClass().getResource("/numbers.txt")).getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertEquals(calculator.calcSum(this.numFilepath), 10);
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertEquals(calculator.calcMultiply(this.numFilepath), 24);
    }
}
```

내부 클래스와 익명 내부 클래스에 대해서는 다른 자바 입문서를 통해 확실히 이해해 두자. 

스프링은 이러한 형식으로 리펙터링된 템플릿 콜백 패턴을 DI에 적극 활용하고 있다. 따라서 스프링을 이해하고 활용하기 위해서는 전략 패턴과 템플릿 콜백 패턴을 잘 기억해 두자. 

마지막으로 패턴을 한 문장으로 정의하면

> "전략을 위한 익명 내부 클래스로 구현한 전략 패턴"
> 

템플릿 콜백 패턴은 전략 패턴의 일종이므로 당연히 개방 폐쇄 원칙(OCP)와 의존 역전 원칙이 적용된 설계 패턴이다.