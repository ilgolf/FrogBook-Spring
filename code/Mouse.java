public class Mouse {
    private String name;
    private int age;
    private int countOfTail;

    public Mouse(String name, int age, int countOfTail) {
        this.name = name;
        this.age = age;
        this.countOfTail = countOfTail;
    }

    public void sing() {
        System.out.println(name + "찍찍 !!!");
    }
}
