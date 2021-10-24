package templeteCallback;

public class Soldier {

    void runContext(String weaponSound) {
        System.out.println("전투 시작");
        executeWeapon(weaponSound).runStrategy();
        System.out.println("전투 종료");
    }

    private Templete executeWeapon(final String weaponSound) {
        return () -> System.out.println(weaponSound);
    }
}
