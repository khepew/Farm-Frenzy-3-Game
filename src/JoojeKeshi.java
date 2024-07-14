import java.util.ArrayList;

public class JoojeKeshi extends Workshops{
    void tolid(int eggNumber,Manager manager){
        if(eggNumber>=1){
            Chicken chicken=new Chicken();
            if(manager.chickens==null){
                manager.chickens=new ArrayList<>();
            }
            chicken.x_position=550;
            chicken.y_position=0;
            manager.chickens.add(chicken);
            manager.producerAnimals.add(chicken);


        }
    }

    public JoojeKeshi() {
        this.work2=false;
        this.level=1;
        this.makingDuration=4;
    }
}

