package adapterPattern;

public class AdapterServiceB implements Service {

    @Override
    public void runService() {
        System.out.println("ServiceB");
    }
}
