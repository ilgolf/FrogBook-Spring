# Day_33

## AOP(관점 지향 프로그래밍)

AOP는 Aspect Oriented Programming의 약자로 관점 지향 프로그래밍이라고 불린다. 쉽게 말해 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 각각 모듈화하겠다는 것이다. 여기서 모듈화란 어떤 공통된 로직이나 기능을 하나의 단위로 묶는 것을 말한다.

예로 들어 핵심적인 관점은 결국 우리가 적용하고자 하는 핵심 비즈니스 로직이 된다. 또한 부가 적인 관점은 핵심 로직을 실행하기 위해서는 행해지는 데이터베이스 연결, 로깅, 파일 입출력 등을 예로 들 수 있다.

AOP에서 각 관점을 기준으로 로직을 모듈화한다는 것은 코드들을 부분적으로 나누어서 모듈화 하겠다는 의미다. 이때, 소스 코드 상에서 다른 부분에 계속 반복해서 쓰는 코드들을 발견할 수 있는 데 이것은 흩어진 관심사라고 부른다.

<img src="/static/AOP-1.png" width="590" height="436"></img>

위와 같이 흩어진 관심사를 Aspect로 모듈화하고 핵심적인 비즈니스 로직에서 분리하여 재사용 하겠다는 것이 AOP의 취지이다.

### AOP 주요 개념

- Aspect : 위에서 설명한 흩어진 관심사를 모듈화 한 것. 주로 부가 기능을 모듈화함.
- Target : Aspect를 적용하는 곳 (클래스, 메서드...)
- Advice : 실질적으로 어떤 일을 해야할 지에 대한 것, 실질적인 부가 기능을 담은 구현체
- JoinPoint : Advice가 적용될 위치, 끼어들 수 있는 지점. 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 다양한 시점에 적용 가능
- PointCut : JointPoint의 상세한 스펙을 정의한 것. 'A란 메서드 진입 시점에 호출할 것' 과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음

### AOP 특징

- 프록시 패턴 기반의 AOP 구현체, 프록시 객체를 쓰는 이유는 접근 제어 및 부가기능을 추가하기 위해서
- 스프링 Bean에만 AOP를 적용 가능
- 모든 AOP기능을 제공하는 것이 아닌 스프링 IoC와 연동하여 엔터프라이즈 애플리케이션에서 가장 흔한 문제(중복코드, 프록시 클래스 작성의 번거로움, 객체들 간 관계 복잡도 증가 ...)에 대한 해결책을 지원하는 것이 목적

### AOP 프록시

Spring AOP는 프록시 기반이다. 프록시 패턴은 저번 시간에 배워서 다들 잘 알거라 생각한다. 자신의 Aspect를 작성하거나 Spring Framework와 함께 제공되는 Spring AOP 기반 Aspect를 사용하기전에 마지막 문이 실제로 의미하는 바를 파악하는 것이 중요하다.

첫 번째로 바닐라 식물 다음 코드에서 볼 수 있듯이 바닐라, 프록시 처리되지 않은, 객체 참조가 있는 상황을 고려해보자

```java
public class SimplePojo implements Pojo {

    public void foo() {
        this.bar();
    }

    public void bar() {
        // some logic...
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Pojo pojo = new SimplePojo();
        pojo.foo();
    }
}
```

객체를 참조해서 메서드를 호출한다면 다음과 그림과 같이 메서드가 해당 객체에서 직접 호출되는 것을 볼 수 있다.

<img src="/static/AOP-2.png" width="527" height="290"></img>

클라이언트 코드에 있는 참조가 프록시인 경우엔 좀 다르다. 다음 다이어그램과 코드를 살펴보자

<img src="/static/AOP-3.png" width="414" height="186"></img>

```java
public class Main {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new RetryAdvice());

        Pojo pojo = (Pojo) factory.getProxy();
        pojo.foo();
    }
}
```

여기서 핵심은 main() aptjemsms Main 클래스의 메서드 내부에 있는 클라이언트 코드에 프록시에 대한 참조가 있다는 것이다. 해당 개체 참조에 대한 메서드 호출이 프록시에 대한 호출을 의미한다. 결과적으로 프록시는 특정 메서드 호출과 관련된 모든 인터셉터를 대신 할 수 있다.

그러나 호출이 최종적으로 대상 개체(Simplepojo에 경우 참조)에 도달하면 this.bar() 또는 자체적으로 수행할 수 있는 모든 메소드 호출이 프록시가 아닌 참조 this.foo()에 대해 호출됩니다. 

this는 중요한 의미를 갖고 있습니다. 이는 자체 호출로 인해 메소드 호출과 관련된 어드바이스가 실행 기회를 얻지 못하는 것을 의미한다.

그렇다면 어떻게 해야 할까? 가장 좋은 접근 방식은 자체 호출이 발생하지 않도록 코드를 리팩터링 하는 것이다. 이것은 약간의 작업을 수반하지만 가장 좋은 접근 방식이다. 다음 예제와 같이 클래스 내의 로직을 Spring AOP에 완전히 묶을 수 있다.

```java
public class SimplePojo implements Pojo {

    public void foo() {
        ((Pojo) AopContext.currentProxy()).bar();
    }

    public void bar() {
        // some logic...
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new RetryAdvice());
        factory.setExposeProxy(true);

        Pojo pojo = (Pojo) factory.getProxy();
        // this is a method call on the proxy!
        pojo.foo();
    }
}
```

마지막으로 AspectJ는 프록시 기반 AOP 프레임워크가 아니기 때문에 이러한 자체 호출 문제가 없다는 점에 유의해야 한다.

### Spring AOP의 사용

먼저 의존성을 추가해 줘야한다. 

```java
// gradle
implementation 'org.springframework.boot:spring-boot-starter-aop'
```

```xml
// maven
<dependency> 
	<groupId>org.aspectj</groupId> 
	<artifactId>aspectjrt</artifactId> 
	<version>1.8.6</version>
</dependency> 
<dependency> 
	<groupId>org.aspectj</groupId> 
	<artifactId>aspectjweaver</artifactId> 
	<version>1.8.6</version> 
</dependency>
```

### @Aspect 적용 클래스 생성

AspectJ는 신규  클래스에 @Aspect 어노테이션만 적용해주면 쉽게 사용 가능하다.

URL 진입처리를 할 Controller class 1개와 aspectJ를 적용할 class 1개를 아래와 같이 생성한다.

TestController는 [localhost:8080/test에](http://localhost:8080/test에) 접근하여 HelloWorld를 출력하는 Get호출형식의 메소드를 정의했다.

```java
package org.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String hello() { return "Hello World"; }
}
```

LogAopHelperCLS 클래스에는 @Aspect 어노테이션과 @Component 어노테이션을 추가하여 Bean등록을 해준다.

```java
package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAopHelperCLS {

    private static final Logger logger = LoggerFactory.getLogger(LogAopHelperCLS.class);

    /**
     * @GetMapping 설정된 메소드 또는 클래스 설정
     * GetMapping 이 설정된 특정 클래스 /메소드에만 AspectJ가 적용됨
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping() {}

    /**
     * @param joinPoint
     */
    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        logger.info("=====================AspectJ TEST  : Before Logging Start=====================");
        logger.info("=====================AspectJ TEST  : Before Logging End=======================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        logger.info("=====================AspectJ TEST  : AfterReturning Logging End=======================");
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("GetMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("=====================AspectJ TEST  : Around Logging Start=====================");

        try {
            Object result = joinPoint.proceed();
            logger.info("=====================AspectJ TEST  : Around Logging Start=====================");
            return result;
        } catch (Exception e) {
            logger.error("=====================AspectJ Around Exception=====================");
            logger.error(e.toString());
            return null;
        }
    }
}
```

### 코드 설명

먼저 설명  @AspectJ어노테이션에 선언된 클래스의 메소드에 선언해주면, 특정 함수 실행 전후의 특정 처리가 가능합니다.

@Pointcut : aspectJ를 적용할 타겟을 정의해준다. 전체 컨트롤러의 함수대상, 특정 어노테이션을 설정한 함수대장, 특정 메소드 대상 등 개발자가 적용하길 원하는 범위를 정의하는 어노테이션

@Before : aspectJ를 적용할 타겟 메소드가 실행되기 '전' 수행됨

@AfterReturning : aspectJ를 적용할 타겟 메소드가 실행된 '후' 수행됨

@Around : aspectJ를 적용할 타겟 메소드 실행 전, 후 처리를 모두 할 수 있음

@Pointcut 어노테이션은 GetMapping 어노테이션이 선언된 메소드에만 aspectj가 적용되도록 제한하고 있습니다. 

그리고, @Before @AfterRunning, @Around 어노테이션에서는 파라미터로 GetMapping()함수를 선언해 해당 범위에 적용할 수 있도록 환경을 만들어 주었다.

서버 빌드 후 실행을 해보았다.

<img src="/static/AOP-4.PNG" width="1369" height="372">

testurl이 입력되면 LogAOPHelperCLS에서 정의한 Log를 콘솔에서 확인할 수 있다.

@Around시작 → @Before시작, 종료 → @AfterReturning시작, 종료 → @Around종료  순서로 실행되는 것을 확인할 수 있다.

@Around어노테이션의 시작 종료는 Object result = joinPoin.proceed(); 전후로 나뉜다.

위 로직을 응용하면 , 특정 url 진입시 로그 삽입, 암호화된 파라미터가 있는 url에 대해 복호화 처리를 @aspect가 선언된 클래스에서 처리할  수 있어 효율적인 설계가 가능할 것 같다.