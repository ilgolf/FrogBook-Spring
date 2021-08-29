package reference;

class Animal {
    public int age;
}

public class CallByReference {

    public static void main(String[] args) {
        Animal refA = new Animal();
        Animal refB = refA;

        refA.age = 10;
        refB.age = 20;

        System.out.println(refA);
        System.out.println(refB);
    }
}
