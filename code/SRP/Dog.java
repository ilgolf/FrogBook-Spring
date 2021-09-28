package SRP;

public abstract class Dog {
    abstract void pee();
}

class MaleDog extends Dog {

    @Override
    void pee() {
        System.out.println("raise one lag");
    }
}

class FemaleDog extends Dog {

    @Override
    void pee() {
        System.out.println("sit down");
    }
}