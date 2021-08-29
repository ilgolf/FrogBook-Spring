public class People {
    
    // property(속성)
    private int age;
    private int weight;
    private int height;
    
    public People(int age, int weight, int height) {
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
    
    void run() {
        weight --;
        height ++;
    }

    void eat() {
        weight ++;
    }

    void sleep() {
        weight ++;
        height ++;
    }

    @Override
    public String toString() {
        return "People [age=" + age + ", height=" + height + ", weight=" + weight + "]";
    }
}
