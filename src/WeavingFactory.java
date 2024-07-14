import java.util.ArrayList;

public class WeavingFactory extends Workshops{
    void tolid(int featherNumber,Manager manager){
        if(featherNumber>=0 && this.level==1){
            Cloth cloth=new Cloth();
            if(manager.cloths==null){
                manager.cloths=new ArrayList<>();
            }
            manager.cloths.add(cloth);
            cloth.x_position=0;
            cloth.y_position=1;

        }
        else if(featherNumber==0 && this.level==2 && !this.work2){
            Cloth cloth=new Cloth();
            if(manager.cloths==null){
                manager.cloths=new ArrayList<>();
            }
            manager.cloths.add(cloth);
            cloth.x_position=0;
            cloth.y_position=1;
        }
        else if(featherNumber>=0 && this.level==2 && this.work2){
            Cloth cloth=new Cloth();
            if(manager.cloths==null){
                manager.cloths=new ArrayList<>();
            }
            manager.cloths.add(cloth);
            cloth.x_position=0;
            cloth.y_position=1;
            Cloth cloth1=new Cloth();
            if(manager.cloths==null){
                manager.cloths=new ArrayList<>();
            }
            manager.cloths.add(cloth1);
            cloth1.x_position=0;
            cloth1.y_position=1;
        }
    }

    public WeavingFactory() {
        this.work2=false;
        this.level=0;
        this.upgradePrice=300;
        this.makingDuration=5;
        this.makingPrice=250;
    }
}
