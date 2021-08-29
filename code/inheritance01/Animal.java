package inheritance01;

public class Animal {
    String myClass;

    public Animal(String myClass) {
        this.myClass = myClass;
    }

    void showMe() {
        System.out.println(myClass);
    }
}
