package adapterPattern;

public class AdapterServiceA implements Service {

    @Override
    public void runService() {
        System.out.println("ServiceA");
    }
}
