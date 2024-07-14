import java.util.Random;

public class Buffalo extends ProducerAnimal{

    public Buffalo() {
        this.name="Buffalo";
        this.price = 400;
        this.life = 100;
        this.capacity = 5;
        this.decreasing_life = 10;
        this.time_producing = 5;
        Random random = new Random();
        this.x_position = random.nextInt()%6;
        this.y_position = random.nextInt()%6;
        while (this.x_position < 0) {
            this.x_position += 6;
        }
        while (this.y_position < 0) {
            this.y_position += 6;
        }
        this.x_position+=1;
        this.y_position+=1;
        this.x_position=110*(x_position-1);
        this.y_position=90*(y_position-1);
        this.width=70;
        this.height=55;
        this.boolProduce=true;
        this.produceNum=0;
        this.tmpNum=0;
        this.existing=true;
    }
}
