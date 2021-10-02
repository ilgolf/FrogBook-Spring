package ocp2;

/**
 *
 * 자동차가 소나타 마티즈가 있다.
 * 마티즈는 창문과 기어를 수동으로 작동시키고 작동시킬 시 운전자 에너지가 줄어든다.
 * 소나타는 창문과 기어를 자동으로 작동시키고 작동 시 운전자 에너지에 변화가 없다.
 *
 */

public class Main {

    public static void main(String[] args) {
        Matiz mDriver = new Matiz(100, 0, false);  //
        Sonata sDriver = new Sonata(120, 2, true);

        mDriver.open();
        mDriver.operGear();

        sDriver.open();
        sDriver.operGear();

        System.out.println(mDriver);
        System.out.println(sDriver);
    }
}
