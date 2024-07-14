public class Feather {
    int price;
    int capacity;
    int time_canceling;
    int x_position;
    int y_position;
    boolean existing;
    public Feather() {
        this.price = 20;
        this.capacity = 1;
        this.time_canceling = 4;
        this.existing=true;
    }
    public Feather(int x_position, int y_position) {
        this.price = 20;
        this.capacity = 1;
        this.time_canceling = 4;
        this.x_position = x_position;
        this.y_position = y_position;
        this.existing=true;
    }
}
