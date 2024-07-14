public class Egg {
    int price;
    int capacity;
    int time_canceling;
    int x_position;
    int y_position;
    boolean existing;
    public Egg() {
        this.price = 15;
        this.capacity = 1;
        this.time_canceling = 4;
        this.existing=true;
    }
    public Egg(int x_position, int y_position) {
        this.price = 15;
        this.capacity = 1;
        this.time_canceling = 4;
        this.x_position = x_position;
        this.y_position = y_position;
        this.existing=true;
    }
}
