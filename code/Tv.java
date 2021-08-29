public class Tv {
    
    private int channel;
    private int volume;
    

    void channelUp() {
        channel ++;
    }

    void volumeUp() {
        volume ++;
    }

    public static void main(String[] args) {
        
        Tv samsungTv = new Tv();
        Tv lgTv = new Tv();
        Tv otherTv = new Tv();
    }
}
