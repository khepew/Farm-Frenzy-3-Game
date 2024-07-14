import java.util.ArrayList;

public class MilkPacking extends Workshops{
    void tolid(int milkNumber,Manager manager){
        if(milkNumber>=0 && this.level==1){
            PackedMilk packedMilk=new PackedMilk();
            if(manager.packedMilks==null){
                manager.packedMilks=new ArrayList<>();
            }
            manager.packedMilks.add(packedMilk);
            packedMilk.x_position=0;
            packedMilk.y_position=2;

        }
        else if(milkNumber==0 && this.level==2 && !this.work2){
            PackedMilk packedMilk=new PackedMilk();
            if(manager.packedMilks==null){
                manager.packedMilks=new ArrayList<>();
            }
            manager.packedMilks.add(packedMilk);
            packedMilk.x_position=0;
            packedMilk.y_position=2;

        }
        else if(milkNumber>=0 && this.level==2&& this.work2){
            PackedMilk packedMilk=new PackedMilk();
            if(manager.packedMilks==null){
                manager.packedMilks=new ArrayList<>();
            }
            manager.packedMilks.add(packedMilk);
            packedMilk.x_position=0;
            packedMilk.y_position=2;
            PackedMilk packedMilk1=new PackedMilk();
            if(manager.packedMilks==null){
                manager.packedMilks=new ArrayList<>();
            }
            manager.packedMilks.add(packedMilk1);
            packedMilk1.x_position=0;
            packedMilk1.y_position=2;
        }
    }

    public MilkPacking() {
        this.work2=false;
        this.level=0;
        this.upgradePrice=450;
        this.makingDuration=6;
        this.makingPrice=400;
    }
}
