# Day_34

필자는 본 설명에 들어가기에 앞서 최대한 글을 정리할 때 Spring docs와 토비의 스프링의 책을 이용하여 개념들을 설명하려고 노력했다. 왜냐하면 Spring에 대한 IoC/DI 설명은 잘못된 생각을 갖고 정리하는 글들을 블로그에서 많이 보았고 이러한 잘못된 정보는 오해를 쌓는다. 

그렇기에 최대한 공식 문서와 스프링 개발자라면 누구나 교과서처럼 들고 다닐만한 이 교재를 이용해 정리를 했다.

## IoC(제어의 역전)란?

IoC는 Spring framework의 핵심 개념 중 하나이다. IoC는 객체가 생성자 인수, 팩토리 메서드에 대한 인수 또는 팩토리 메서드에서 생성되거나 반환된 후 객체 인스턴스에 설정된 속성을 통해서만 객체가 종속성(함께 작업하는 다른 객체)을 정의하는 프로세스 이다.

이 프로세스는 근본적으로 클래스의 직접 구성 또는 Service Locator 패턴과 같은 매커니즘을 사용하여 종속성의 인스턴스화 또는 위치를 제어하는 Bean 자체의 반대(제어의 역전)입니다.

Spring에서는 org.springframework.beans 및 org.springframework.context  패키지를 IoC 컨테이너의 기초로 BeanFactory 인터페이스에서 모든 개체를 관리할 수 있는 고급 구성 매커니즘을 제공한다. 

BeanFactory의 하위 인터페이스인 ApplicationContext 는 다음과 같은 기능을 제공한다.

- Spring의 AOP기능과 더 쉽게 통합
- 메시지 리소스 처리(국제화에 사용)
- 이벤트 발생
- WebApplicationContext처럼 웹 애플리케이션을 사용하기 위한 것과 같은 애플리케이션 계층 특정 컨텍스트

Spring IoC 컨테이너에 의해 관리되는 객체를 Bean이라고 하고 Bean객체는 Spring IoC 컨테이너에 의해 인스턴스화, 조립 및 관리되는 객체이다. Bean과 이들 간의 종속성은 컨테이너에서 사용하는 구성 메타데이터에 반영된다.

이러한 Spring IoC 컨테이너에 의해 싱글톤으로 Bean객체를 관리한다. 이유는 간단하다. 사람들이 해당 애플리케이션을 이용할 때마다 무한히 객체를 생성할 것이고 이는 heap메모리에 과부화를 일으킬 수 있기 때문에 사용자는 이러한 객체를 사용하고 다시 IoC 컨테이너에 반납하는 형식으로 동작한다.

하지만, 싱글톤은 안티 패턴으로 분류된다 그 이유는 후에 다시 글로 정리해서 올릴 예정이다.

그렇다면 여기서 나온 BeanFactory의 팩토리와 ApplicationContext에 대해 알아보자

### Factory

전에 팩토리 메소드 패턴에 대해서 배웠지만 다른 개념이니 혼동하지말도록 하자 그렇다면 이 팩토리 메소드는 무엇일까?

이 메소드는 단지 객체를 생성하는 쪽과 생성된 오브젝트를 사용하는 쪽의 역할과 책임을 깔끔하게 분리하려는 목적으로 사용하는 것이다. 

예제를 살펴보면 

```java
public class DaoFactory {
	public UserDao userDao() {
		ConnectionMaker = connectionMaker = new DConnectionMaker(); // D사 DB 커넥션
		UserDao userDao = new UserDao(connectionMaker);  // D사 회원에 대한 연결만 이루어짐
		return userDao;
	}
}
```

DaoFactory의 userDao 메소드를 호출하면 DConnectionMaker를 사용해 DB 커넥션을 가져오도록 이미 설정된 UserDao 객체를 돌려준다. 이렇게 만들면 테스트시 자신의 관심사인 테스트를 위해 활용하기만 하면 그만이다. 이렇게 각각이 자신의 책임에만 충실하도록 역할에 따라 분리하는 작업을 한 것이다.

설계도를 그리면 다음과 같이 나온다.

<img src="/static/IoC-1.PNG" width="728" height="308"></img>

### Application Context

Spring에서는 Bean의 생성과 관계설정 같은 제어를 담당하는 IoC 객체를 Bean Factory라 부른다. 이를 좀 더 확장한 것을 Application Context라고한다. IoC 방식을 따라 만들어진 일종의 Bean Factory라고 생각하면 된다. 앞으로 Bean Factory와 Application Context은 같다고 생각하면 된다.

Bean Factory는 Bean을 생성하고 관계를 설정하는 IoC의 기본기능에 초점을 맞춘 것이고, Application Context는 애플리케이션 전반에 걸쳐 모든 구성요소 제어 작업을 담당하는 IoC 엔진이라는 의미가 좀 더 부각된다고 보면 된다.

### Application Context의 사용

이해하기 쉽게 코드를 먼저 살펴보자.

```java

@Configuration // 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactory {
	public UserDao userDao() {
		ConnectionMaker = connectionMaker = new DConnectionMaker();

		@Bean // 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
		UserDao userDao = new UserDao(connectionMaker);
		return userDao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
```

기존에 DaoFactory 클래스에 빈 팩토리를 위한 객체 설정을 담당하는 클래스라고 인식할 수 있도록 @Configuration이라는 애노테이션을 추가하고 오브젝트를 만들려는 메소드에는 @Bean 애노테이션을 붙여준다. 

자바 코드로 짜여져 있지만 이렇게 애노테이션이 적용된 클래스는 XML과 같은 스프링 전용 설정 정보라고 보는 것이 좋다.

이제 설정정보로 사용하는 애플리케이션 컨텍스트를 만들어보자. 애플리케이션 컨텍스트는 ApplicationContext 타입의 객체이다. 이를 구현한 클래스는 여러 가지가 있는데 DaoFactory 처럼 

@Configuration이 붙은 자바 코드를 설정 정보로 사용하려면 이 ApplicationContext를 이용하면 된다. 이 클래스 내에 getBean()이라는 메소드를 이용해 UserDao의 오브젝트를 갖고 올 수 있다.

Main 코드를 살펴보자

```java
public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		...
}
```

getBean() 메소드는 ApplicationContext가 관리하는 객체를 요청하는 메소드다. getBean()의 파라미터인 애노테이션을 userDao라는 이름의 메소드에 붙였는데, 이 메소드이 바로 Bean이 된다. userDao라는 이름의 Bean을 갖고 온다는 것은 DaoFactory의 userDao() 메소드를 호출해서 그 결과를 갖고온다고 생각하면 된다.

그렇다면 왜 UserDao를 가져오는 메소드는 하나 뿐인데 굳이 이름을 사용할까? 그 이유는 간단하다. 

방식이나 구성을 다르게 가져가는 메소드를 추가할 수 있기 때문이다. 그때는 specialUserDao()라는 메소드로 만들고 getBean("specialUserDao", UserDao.class)로 가져오면 된다.

### Application Context의 장점

- 클라이언트는 구체적인 팩토리 클래스를 알 필요가 없다.

- 애플리케이션 컨텍스트는 종합 IoC 서비스를 제공해준다.

- 애플리케이션 컨텍스트는 빈을 검색하는 다양한 방법을 제공한다.

### IoC의 용어 정리

- Bean : 빈 또는 빈 객체는 스프링이 IoC 방식으로 관리하는 객체라는 뜻이다. 모는 객체들이 다 빈은 아니지만 이 중 스프링이 직접 그 생성과 제어를 담당하는 객체를 Bean이라 부른다.

- Bean Factory : 스프링의 IoC를 담당하는 핵심 컨테이너를 가리킨다. Bean을 등록하고 생성하고 조회하고 돌려주고, 그 외에 부가적인 Bean을 관리하는 기능을 담당한다.

- Application Context : 빈 팩토리를 확장한 IoC 컨테이너다. 빈을 등록하고 관리하는 기본적인 기능은 Bean 팩토리와 동일하나 여기에 스프링이 제공하는 각종 부가 서비스를 추가로 제공한다.

- 설정정보/메타정보 : 스프링의 설정정보란 애플리케이션 컨텍스트 또는 빈 팩토리가 IoC를 적용하기 위해 사용하는 메타정보를 말한다.
- 
- IoC 컨테이너 : IoC 방식으로 빈을 관리한다는 의미에서 애플리케이션 컨텍스트나 빈 팩토리를 IoC 컨테이너라고도 한다.