package poorSuperClass;

import java.time.LocalDate;

public class Soldier extends People {
    private LocalDate birth;
    private String number;
    private String dogNumber;

    public Soldier(String name, LocalDate birth, String number, String dogNumber) {
        super(name);
        this.birth = birth;
        this.number = number;
        this.dogNumber = dogNumber;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDogNumber() {
        return dogNumber;
    }

    public void setDogNumber(String dogNumber) {
        this.dogNumber = dogNumber;
    }

    public void sleep() {
        System.out.println(getName() + "쿨쿨 자는 중");
    }

    public void introduce() {
        String sb = "제 군번은 " + dogNumber + "이고 \n" + "제 주민번호는" + number + "이고 \n" +
                "제 생일은" + birth + "입니다. \n" +
                "잘 부탁드립니다!";
        System.out.println(sb);
    }

    public void tranning() {
        System.out.println("훈련 중 으쌰 으쌰");
    }
}
