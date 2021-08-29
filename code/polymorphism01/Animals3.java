package polymorphism01;

public class Animals3 {

    private String name;

    public Animals3(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void showName() {
        System.out.printf("안녕 나는 %s야. 반가워\n", name);
    }
}
