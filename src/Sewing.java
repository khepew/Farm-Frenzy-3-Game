import java.util.ArrayList;

public class Sewing extends Workshops {
    void tolid(int clothNumber,Manager manager){
        if(clothNumber>=0 && this.level==1){
            Shirt shirt=new Shirt();
            if(manager.shirts==null){
                manager.shirts=new ArrayList<>();
            }
            manager.shirts.add(shirt);
            shirt.x_position=0;
            shirt.y_position=4;

        }
        else if(clothNumber==0 && this.level==2 && !this.work2){
            Shirt shirt=new Shirt();
            if(manager.shirts==null){
                manager.shirts=new ArrayList<>();
            }
            manager.shirts.add(shirt);
            shirt.x_position=0;
            shirt.y_position=4;
        }
        else if(clothNumber>=0 && this.level==2 && this.work2){
            Shirt shirt=new Shirt();
            if(manager.shirts==null){
                manager.shirts=new ArrayList<>();
            }
            manager.shirts.add(shirt);
            shirt.x_position=0;
            shirt.y_position=4;
            Shirt shirt1=new Shirt();
            if(manager.shirts==null){
                manager.shirts=new ArrayList<>();
            }
            manager.shirts.add(shirt1);
            shirt1.x_position=0;
            shirt1.y_position=4;
        }
    }

    public Sewing() {
        this.work2=false;
        this.level=0;
        this.upgradePrice=450;
        this.makingDuration=6;
        this.makingPrice=400;
    }
}
