import java.util.Random;

public class Cat {
    int price;
    int x_position;
    int y_position;
    boolean existing;
    int width;
    int height;
    int capacity;
    public Cat() {
        this.price = 150;
        Random random = new Random();
        this.x_position = random.nextInt() % 6;
        this.y_position = random.nextInt() % 6;
        while (this.x_position <= 0){
            this.x_position += 6;
        }
        while (this.y_position < 0) {
            this.y_position += 6;
        }
        this.x_position += 1;
        this.y_position += 1;
        this.x_position=110*(x_position-1);
        this.y_position=90*(y_position-1);
        this.existing=true;
        this.width=60;
        this.height=50;
        this.capacity=5;
    }
}
