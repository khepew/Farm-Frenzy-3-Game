import java.util.ArrayList;

public class Bakery extends Workshops{
    void tolid(int powderNumber,Manager manager){
        if(powderNumber>=0 && this.level==1){
            Bread bread=new Bread();
            if(manager.breads==null){
                manager.breads=new ArrayList<>();
            }
            manager.breads.add(bread);
            bread.x_position=0;
            bread.y_position=3;

        }
        else if(powderNumber==0 && this.level==2 && !this.work2){
            Bread bread=new Bread();
            if(manager.breads==null){
                manager.breads=new ArrayList<>();
            }
            manager.breads.add(bread);
            bread.x_position=0;
            bread.y_position=3;

        }
        else if(powderNumber>=0 && this.level==2 && this.work2){
            Bread bread=new Bread();
            if(manager.breads==null){
                manager.breads=new ArrayList<>();
            }
            manager.breads.add(bread);
            bread.x_position=0;
            bread.y_position=3;
            Bread bread1=new Bread();
            if(manager.breads==null){
                manager.breads=new ArrayList<>();
            }
            manager.breads.add(bread1);
            bread1.x_position=0;
            bread1.y_position=3;
        }
    }

    public Bakery() {
        this.work2=false;
        this.level=0;
        this.upgradePrice=300;
        this.makingDuration=5;
        this.makingPrice=250;
    }

}
