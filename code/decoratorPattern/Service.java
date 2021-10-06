package decoratorPattern;

public class Service implements IService {

    @Override
    public String runSomething() {
        return "서비스 짱!!";
    }
}
