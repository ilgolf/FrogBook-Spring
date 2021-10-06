package decoratorPattern;

public class Decoreator implements IService {
    IService iService;

    @Override
    public String runSomething() {
        System.out.println("호출에 대한 장식 주 목적, 클라이언트에게 반환 결과에 장식을 더하여 전달");

        iService = new Service();
        return "정말" + iService.runSomething();
    }
}
