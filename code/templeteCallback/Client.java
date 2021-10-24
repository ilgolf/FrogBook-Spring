package templeteCallback;

public class Client {

    public static void main(String[] args) {
        Soldier rambo = new Soldier();

        rambo.runContext("총! 총총총 총! 총!");

        System.out.println();

        rambo.runContext("칼! 칼칼 칼!");

        System.out.println();

        rambo.runContext("도끼! 퍽퍽퍽");
    }
}
