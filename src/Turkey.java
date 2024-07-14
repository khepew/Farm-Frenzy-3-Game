import java.util.Random;

public class Turkey extends ProducerAnimal{

    public Turkey() {
        this.name="Turkey";
        this.price = 200;
        this.life = 100;
        this.capacity = 5;
        this.decreasing_life = 10;
        this.time_producing = 3;
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
        this.boolProduce=true;
        this.produceNum=0;
        this.width=60;
        this.height=50;
        this.tmpNum=0;
        this.existing=true;
    }
}
