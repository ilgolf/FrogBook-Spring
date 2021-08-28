package inheritance03;

public class Penguin2 extends Animals2 {

    private String habitat; // 주거지역

    public Penguin2(String name) {
        super(name);
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void showHabitat() {
        System.out.printf("%s는 %s에 살아\n", habitat);
    }
}
