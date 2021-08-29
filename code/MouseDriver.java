public class MouseDriver {
    
    public static void main(String[] args) {
        Mouse mickey = new Mouse("미키", 85, 1);

        mickey.sing();

        mickey = null;

        Mouse jerry = new Mouse("제리", 73, 1);

        jerry.sing();
    }
}
