package poorSuperClass;

import java.time.LocalDate;

public class Student extends People {

    private LocalDate birth;
    private String number; // 주민번호
    private String studentId; // 학번

    public Student(String name, LocalDate birth, String number, String studentId) {
        super(name);
        this.birth = birth;
        this.number = number;
        this.studentId = studentId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void sleep() {
        System.out.println(getName() + "쿨쿨 자는 중");
    }

    public void introduce() {
        String sb = "제 학번은 " + studentId + "이고 \n" + "제 주민번호는" + number + "이고 \n" +
                "제 생일은" + birth + "입니다. \n" +
                "잘 부탁드립니다!";
        System.out.println(sb);
    }

    public void study() {
        System.out.println(getName() + "은 공부 중");
    }
}
