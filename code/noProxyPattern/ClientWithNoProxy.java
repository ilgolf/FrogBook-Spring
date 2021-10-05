package noProxyPattern;

public class ClientWithNoProxy {

    public static void main(String[] args) {
        // 프록시를 이용하지 않는 호출
        Service service = new Service();
        System.out.println(service.runSomething());
    }
}
