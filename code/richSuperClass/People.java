package richSuperClass;

import java.time.LocalDate;

public abstract class People {
    private String name;
    private LocalDate birth;
    private String number;

    public People(String name, LocalDate birth, String number) {
        this.name = name;
        this.birth = birth;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void sleep() {
        System.out.println(getName() + "쿨쿨 자는 중");
    }

    public void eat() { System.out.println("냠냠"); }
}
