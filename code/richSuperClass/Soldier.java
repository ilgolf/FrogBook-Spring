package richSuperClass;

import java.time.LocalDate;

public class Soldier extends People {

    private String dogNumber;

    public Soldier(String name, LocalDate birth, String number, String dogNumber) {
        super(name, birth, number);
        this.dogNumber = dogNumber;
    }

    public void introduce() {
        String sb = "제 학번은 " + dogNumber + "이고 \n" + "제 주민번호는" + getNumber() + "이고 \n" +
                "제 생일은" + getBirth() + "입니다. \n" +
                "잘 부탁드립니다!";
        System.out.println(sb);
    }
}
