package proxyPattern;

public class ClientWithProxy {
    // 프록시를 이용한 호출
    public static void main(String[] args) {
        IService proxy = new Proxy();
        System.out.println(proxy.runSometing());
    }
}
