import java.util.ArrayList;

public class EggPowderPlant extends Workshops{
    void tolid(int eggNumber,Manager manager){
        if(eggNumber>=0 && this.level==1){
            //manager.eggNumber-=1;
            Powder powder=new Powder();
            if(manager.powders==null){
                manager.powders=new ArrayList<>();
            }
            manager.powders.add(powder);
            powder.x_position=0;
            powder.y_position=0;

        }
        else if(eggNumber==0 && this.level==2 && !this.work2){
            //manager.eggNumber-=1;
            Powder powder=new Powder();
            if(manager.powders==null){
                manager.powders=new ArrayList<>();
            }
            manager.powders.add(powder);
            powder.x_position=0;
            powder.y_position=0;

        }
        else if(eggNumber>=0 && this.level==2 && this.work2){
            //manager.eggNumber-=2;
            Powder powder=new Powder();
            if(manager.powders==null){
                manager.powders=new ArrayList<>();
            }
            manager.powders.add(powder);
            powder.x_position=0;
            powder.y_position=0;

            Powder powder1=new Powder();
            if(manager.powders==null){
                manager.powders=new ArrayList<>();
            }
            manager.powders.add(powder1);
            powder1.x_position=0;
            powder1.y_position=0;

        }
    }

    public EggPowderPlant() {
        this.work2=false;
        this.level=0;
        this.upgradePrice=200;
        this.makingDuration=4;
        this.makingPrice=150;
    }
}
