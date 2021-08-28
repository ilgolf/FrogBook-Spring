package polymorphism01;

public class Penguin3 extends Animals3 {

    private String habitat;

    public Penguin3(String name, String habitat) {
        super(name);
        this.habitat = habitat;
    }

    public void showHabitat() {
        System.out.printf("%s는 %s에 살아\n", this.getName(), habitat);
    }

    @Override
    public void showName() {
        System.out.println("어머 내이름을 알아서 뭐하게요?");
    }

    public void showName(String yourname) {
        System.out.printf("%s 안녕, 나는 %s라고 해\n", yourname, this.getName());
    }
}
