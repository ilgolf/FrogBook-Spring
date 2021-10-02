package poorSuperClass;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Student kimStudent = new Student("kim-gil-do", LocalDate.of(2000, 1, 1), "20000101-1234567",
                "20190001");

        People leeSoldier = new Soldier("lee-gil-do", LocalDate.of(1998, 12, 31), "19981231-1234567",
                "19-12345678");

        System.out.println(kimStudent.getBirth());
        // System.out.println(leeSoldier.getBirth()); 사용 불가

        System.out.println(((Soldier) leeSoldier).getBirth()); // 캐스팅 필요

        kimStudent.eat();
        leeSoldier.eat();

        kimStudent.sleep();
        // leeSoldier.sleep();

        ((Soldier) leeSoldier).sleep(); // 캐스팅 필요

        kimStudent.study();
        // leeSoldier.tranning(); 캐스팅 필요
    }
}
