import java.util.Random;

public class Bear extends AttackerAnimal{

    public Bear() {
        this.price = 400;
        this.capacity=15;
        this.time_canceling=5;
        this.cageInputNum=0;
        this.caged=false;
        Random random = new Random();
        this.x_position = random.nextInt()%6;
        this.y_position = random.nextInt()%6;
        while (this.x_position < 0) {
            this.x_position += 6;
        }
        while (this.y_position < 0) {
            this.y_position += 6;
        }
        this.x_position=110*(x_position);
        this.y_position=90*(y_position);
        this.width=90;
        this.height=60;
        this.existing=true;
        this.tmpNum=0;
    }
}