import java.util.ArrayList;

public class IceCreamShop extends Workshops{
    void tolid(int packedNumber,Manager manager){
        if(packedNumber>=0 && this.level==1){
            Icecream icecream=new Icecream();
            if(manager.icecreams==null){
                manager.icecreams=new ArrayList<>();
            }
            manager.icecreams.add(icecream)
            ;
            icecream.x_position=0;
            icecream.y_position=5;

        }
        else if(packedNumber==0 && this.level==2 && !this.work2){
            Icecream icecream=new Icecream();
            if(manager.icecreams==null){
                manager.icecreams=new ArrayList<>();
            }
            manager.icecreams.add(icecream)
            ;
            icecream.x_position=0;
            icecream.y_position=5;
        }
        else if(packedNumber>=0 && this.level==2 && this.work2){
            Icecream icecream=new Icecream();
            if(manager.icecreams==null){
                manager.icecreams=new ArrayList<>();
            }
            manager.icecreams.add(icecream)
            ;
            icecream.x_position=0;
            icecream.y_position=5;
            Icecream icecream1=new Icecream();
            if(manager.icecreams==null){
                manager.icecreams=new ArrayList<>();
            }
            manager.icecreams.add(icecream1);
            icecream1.x_position=0;
            icecream1.y_position=5;
        }
    }

    public IceCreamShop() {
        this.work2=false;
        this.level=0;
        this.upgradePrice=600;
        this.makingDuration=7;
        this.makingPrice=550;
    }
}
