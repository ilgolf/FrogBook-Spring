package encapsulation01.packageOne;

public class ClassA {

    private int pri;  // 현재 클래스 내에서만 사용 가능
    int def; // 서브 클래스와 같은 패키지 내에선 접근 가능
    protected int pro; // 서브 클래스만 접근 가능
    public int pub; // 모든 곳에서 접근 가능

    void runSomeThing() {

    }

    static void runStaticSomeThing() {

    }
}
