package richSuperClass;

import java.time.LocalDate;

public class Student extends People {

    private String studentId;

    public Student(String name, LocalDate birth, String number, String studentId) {
        super(name, birth, number);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public void introduce() {
        String sb = "제 학번은 " + studentId + "이고 \n" + "제 주민번호는" + getNumber() + "이고 \n" +
                "제 생일은" + getBirth() + "입니다. \n" +
                "잘 부탁드립니다!";
        System.out.println(sb);
    }
}
