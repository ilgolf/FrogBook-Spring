package inheritance03;

public class Animals2 {
    private String name; // 동물 이름

    public Animals2(String name) {
        this.name = name;
    }

    public void showName() {
        System.out.printf("안녕 나는 %s 야. 반가워\n", name);
    }
}