public class Start4 {
    
    private static int square(int k) {
        int result;
        
        result = (int)Math.pow(k, 2);

        return result;
    } 
    
    public static void main(String[] args) {
        int k = 5;
        int m;

        m = square(4);

        for(int i=0; i<4; i++) {
            System.out.println(i);
        }

        if(k >= 5) {
            System.out.println(k);
        }

        System.out.println(k);
        System.out.println(m);
    }
}
