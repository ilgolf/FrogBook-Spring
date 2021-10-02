package icp;

public class GirlFriend extends Man implements BoyFriend {

    private boolean happy;

    @Override
    public void anniversary() {
        if (energy > 0 && money > 0) {
            happy = true;
            energy --;
            money --;
            System.out.println("여자친구가 좋아합니다.");
        } else {
            happy = false;
            System.out.println("여자친구가 슬퍼합니다.");
        }
    }

    @Override
    public void doKiss() {
        happy = true;
        energy ++;
        System.out.println("당신과 여자친구가 좋아합니다.");
    }
}
