import java.util.Random;
public class Chicken extends ProducerAnimal {

    public Chicken() {
        this.name = "Chicken";
        this.price = 100;
        this.life = 100;
        this.capacity = 5;
        this.decreasing_life = 10;
        this.time_producing = 2;
        Random random = new Random();
        this.x_position = random.nextInt() % 6;
        this.y_position = random.nextInt() % 6;
        while (this.x_position < 0) {
            this.x_position += 6;
        }
        while (this.y_position < 0) {
            this.y_position += 6;
        }

        this.x_position=110*(x_position);
        this.y_position=90*(y_position);
        this.width=60;
        this.height=50;
        this.boolProduce = true;
        this.produceNum = 0;
        this.tmpNum=0;
        this.existing=true;
    }

}
