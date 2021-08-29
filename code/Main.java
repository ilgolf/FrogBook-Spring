public class Main {
    
    public static void main(String[] args) {
        People kimjongmin = new People(19, 80, 182);
        People hanheoju = new People(20, 48, 168);
        People kimyeonah = new People(21, 51, 172);

        kimjongmin.run();
        hanheoju.eat();
        kimyeonah.sleep();

        System.out.println("김종민 = " + kimjongmin.toString());
        System.out.println("김연아 = " + kimyeonah.toString());
        System.out.println("한효주 = " + hanheoju.toString());
    }
}
