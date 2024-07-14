import com.sun.org.apache.xerces.internal.utils.XMLLimitAnalyzer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;

public class Manager extends WareHouse{
    public ArrayList<Egg> eggs;
    public ArrayList<Feather> feathers;
    public ArrayList<Milk> milks;
    public ArrayList<Powder> powders;
    public ArrayList<Cloth> cloths;
    public ArrayList<PackedMilk> packedMilks;
    public ArrayList<Bread> breads;
    public ArrayList<Shirt> shirts;
    public ArrayList<Icecream> icecreams;
    public ArrayList<Chicken> chickens;
    public ArrayList<Turkey> turkeys;
    public ArrayList<Buffalo> buffaloes;
    public ArrayList<Lion> lions;
    public ArrayList<Bear> bears;
    public ArrayList<Tiger> tigers;
    public ArrayList<Dog> dogs;
    public ArrayList<Cat> cats;



    ArrayList<Logger> loggers=new ArrayList<>();
    /////////////////////////////////
    public ArrayList<Levels> levels;
    public ArrayList<Users> users;
    public ArrayList<ProducerAnimal> producerAnimals;
    public ArrayList<AttackerAnimal> attackerAnimals;
    /////////////////////////////////////
    public EggPowderPlant eggPowderPlant=new EggPowderPlant();
    public WeavingFactory weavingFactory=new WeavingFactory();
    public MilkPacking milkPacking=new MilkPacking();
    public Bakery bakery=new Bakery();
    public Sewing sewing=new Sewing();
    public IceCreamShop iceCreamShop=new IceCreamShop();
    public JoojeKeshi joojeKeshi= new JoojeKeshi();
    /////////////////////////////////////
    int[][] farm = new int[6][6];
    int coins;
    int coin1=0;
    int well=5;
    Truck truck = new Truck();
    ////////////////////////////////////////////////////////////////////////////////
    Egg egg1 = new Egg();
    Feather feather1 = new Feather();
    Milk milk1 = new Milk();
    Powder powder1 = new Powder();
    Cloth cloth1= new Cloth();
    PackedMilk packedMilk1= new PackedMilk();
    Bread bread1= new Bread();
    Shirt shirt1 = new Shirt();
    Icecream icecream1 = new Icecream();
    Chicken chicken1= new Chicken();
    Turkey turkey1 = new Turkey();
    Buffalo buffalo1 = new Buffalo();
    Lion lion1 = new Lion();
    Bear bear1 = new Bear();
    Tiger tiger1= new Tiger();
    Dog dog1= new Dog();
    Cat cat1 = new Cat();
    Levels level1=new Levels();
//////////////////////////////////////////////////////////////////////////////////
    boolean wellingBool=false;
    int wellingTime;
    boolean truckBool=false;
    int truckTime;
    ///

    boolean workEggPowderPlant=false;
    int eggPowderPlantTime;
    boolean workWeavingFactory=false;
    int weavingFactoryTime;
    boolean workMilkPacking=false;
    int milkPackingTime;
    boolean workBakery=false;
    int bakeryTime;
    boolean workSewing=false;
    int sewingTime;
    boolean workIceCreamShop=false;
    int iceCreamShopTime;
    boolean workJoojeKeshi=false;
    int joojeKeshiTime;
    ///////////////////
    boolean win=false;
    //////////////////
    int max_eggNumber2=0;
    int max_featherNumber2=0;
    int max_milkNumber2=0;
    int max_powderNumber2=0;
    int max_clothNumber2=0;
    int max_packedMilkNumber2=0;
    int max_breadNumber2=0;
    int max_shirtNumber2=0;
    int max_icecreamNumber2=0;
    ///////////////////
    int allChickensNum=0;
    int allTurkeysNum=0;
    int allBuffaloesNum=0;
    int allLionsNum=0;
    int allBearsNum=0;
    int allTigersNum=0;

    public Manager() {
        this.chickens = new ArrayList<>();
        this.eggs = new ArrayList<>();
        this.turkeys=new ArrayList<>();
        this.buffaloes=new ArrayList<>();
        this.dogs=new ArrayList<>();
        this.cats=new ArrayList<>();
        this.users=new ArrayList<>();
        this.levels=new ArrayList<>();
        this.producerAnimals=new ArrayList<>();
        this.attackerAnimals=new ArrayList<>();

    }
    public Manager(ArrayList<Levels> levels, ArrayList<Users> users) {
        this.chickens = new ArrayList<>();
        this.eggs = new ArrayList<>();
        this.turkeys=new ArrayList<>();
        this.buffaloes=new ArrayList<>();
        this.dogs=new ArrayList<>();
        this.cats=new ArrayList<>();
        this.users= (ArrayList<Users>) users.clone();
        this.levels= (ArrayList<Levels>) levels.clone();
        this.producerAnimals=new ArrayList<>();
        this.attackerAnimals=new ArrayList<>();
    }

    public void drawImage(Stage stage, int x, int y, int w, int h, String path, Pane root1) throws FileNotFoundException {
        //Creating an image
        Image image = new Image(new FileInputStream(path));
        //Setting the image view
        ImageView imageView = new ImageView(image);
        //Setting the position of the image
        imageView.setX(x);
        imageView.setY(y);
        //setting the fit height and width of the image view
        imageView.setFitHeight(h);
        imageView.setFitWidth(w);
        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);
        //Adding scene to the stage
        root1.getChildren().addAll(imageView);
        //Displaying the contents of the stage
        stage.show();
    }

    public void buying_animal(String name, Stage primaryStage,Pane root1){
        if(name.equalsIgnoreCase("Chicken")){
            if(coins>=chicken1.price){
                Chicken chicken2 = new Chicken();
                allChickensNum++;
                chicken2.tmpNum=allChickensNum;
                producerAnimals.add(chicken2);
                chickens.add(chicken2);
                coins-=chicken2.price;
                System.out.println("Bought successfully !!!");
                Date date = new Date();
                String s = "Animal Bought";
                logger(date,s,true);
            }
            else {
                System.out.println("Not enough coins !!!");
                Date date = new Date();
                String s = "Cannot Buy";
                logger(date,s,false);
            }
        }
        else if(name.equalsIgnoreCase("Turkey")){
            if(coins>=turkey1.price){
                Turkey turkey2 = new Turkey();
                allTurkeysNum++;
                turkey2.tmpNum=allTurkeysNum;
                producerAnimals.add(turkey2);
                turkeys.add(turkey2);
                coins-=turkey2.price;
                System.out.println("Bought successfully !!!");
                Date date = new Date();
                String s = "Animal Bought";
                logger(date,s,true);

            }
            else {
                System.out.println("Not enough coins !");
                Date date = new Date();
                String s = "Cannot Buy";
                logger(date,s,false);
            }
        }
        else if(name.equalsIgnoreCase("Buffalo")){
            if(coins>=buffalo1.price){
                Buffalo buffalo2 = new Buffalo();
                allBuffaloesNum++;
                buffalo2.tmpNum=allBuffaloesNum;
                producerAnimals.add(buffalo2);
                buffaloes.add(buffalo2);
                coins-=buffalo2.price;
                System.out.println("Bought successfully !!!");
                Date date = new Date();
                String s = "Animal Bought";
                logger(date,s,true);
            }
            else {
                System.out.println("Not enough coins !");
                Date date = new Date();
                String s = "Cannot Buy";
                logger(date,s,false);
            }
        }
        else if(name.equalsIgnoreCase("Dog")){

            if(coins>=dog1.price){
                Dog newDog = new Dog();
                dogs.add(newDog);
                coins-=dog1.price;
                System.out.println("Bought successfully !!!");
                Date date = new Date();
                String s = "Animal Bought";
                logger(date,s,true);
            }
            else {
                System.out.println("Not enough coins !");
                Date date = new Date();
                String s = "Cannot Buy";
                logger(date,s,false);
            }
        }
        else if(name.equalsIgnoreCase("Cat")){

            if(coins>=cat1.price){
                Cat newCat = new Cat();
                cats.add(newCat);
                coins-=cat1.price;
                System.out.println("Bought successfully !!!");
                Date date = new Date();
                String s = "Animal Bought";
                logger(date,s,true);
            }
            else {
                System.out.println("Not enough coins !");
                Date date = new Date();
                String s = "Cannot Buy";
                logger(date,s,false);
            }
        }
        else{
            System.out.println("Buying was not successful !");
            Date date = new Date();
            String s = "Unsuccessful buying";
            logger(date,s,false);
        }
        drawGrass(primaryStage,root1);
        drawAnimal(primaryStage,root1);

    }/// Finished:25/3

    public void pickup_product(int x,int y){
        boolean b=false;
        if(x>=6||y>=6||y<0||x<0){
            System.err.println("Position Out Of Range !!!");
            Date date = new Date();
            String s = "Entered Wrong Position";
            logger(date,s,false);
        }
        else{
            if(eggs!=null&&!b) {
                for (Egg egg : eggs) {
                    if ((egg.x_position)/110 == x && (egg.y_position)/90 == y && (MAX_CAPACITY - stock) >= egg.capacity&& egg.time_canceling!=0 &&egg.existing) {
                        stock += egg.capacity;
                        eggNumber++;
                        if(max_eggNumber>0){
                            max_eggNumber--;
                        }
                        egg.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(feathers!=null&&!b) {
                for (Feather feather : feathers) {
                    if ((feather.x_position)/110 == x && (feather.y_position)/90 == y && (MAX_CAPACITY - stock) >= feather.capacity&& feather.time_canceling!=0&&feather.existing) {
                        stock += feather.capacity;
                        featherNumber++;
                        if(max_featherNumber>0){
                            max_featherNumber--;
                        }
                        feather.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(milks!=null&&!b) {
                for (Milk milk : milks) {
                    if ((milk.x_position)/110 == x && (milk.y_position)/90 == y && (MAX_CAPACITY - stock) >= milk.capacity&& milk.time_canceling!=0&&milk.existing) {
                        stock += milk.capacity;
                        milkNumber++;
                        if(max_milkNumber>0){
                            max_milkNumber--;
                        }
                        milk.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(powders!=null&&!b) {
                for (Powder powder : powders) {
                    if (powder.x_position == x && powder.y_position == y && (MAX_CAPACITY - stock) >= powder.capacity&& powder.time_canceling!=0&&powder.existing) {
                        stock += powder.capacity;
                        powderNumber++;
                        if(max_powderNumber>0){
                            max_powderNumber--;
                        }
                        powder.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(cloths!=null&&!b) {
                for (Cloth cloth : cloths) {
                    if (cloth.x_position == x && cloth.y_position == y && (MAX_CAPACITY - stock) >= cloth.capacity&& cloth.time_canceling!=0&&cloth.existing) {
                        stock += cloth.capacity;
                        clothNumber++;
                        if(max_clothNumber>0){
                           max_clothNumber--;
                        }
                        cloth.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(packedMilks!=null&&!b) {
                for (PackedMilk packedMilk : packedMilks) {
                    if (packedMilk.x_position == x && packedMilk.y_position == y && (MAX_CAPACITY - stock) >= packedMilk.capacity&& packedMilk.time_canceling!=0&&packedMilk.existing) {
                        stock += packedMilk.capacity;
                        packedMilkNumber++;
                        if(max_packedMilkNumber>0){
                            max_packedMilkNumber--;
                        }
                        packedMilk.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(breads!=null&&!b) {
                for (Bread bread : breads) {
                    if (bread.x_position == x && bread.y_position == y && (MAX_CAPACITY - stock) >= bread.capacity&& bread.time_canceling!=0&&bread.existing) {
                        stock += bread.capacity;
                        breadNumber++;
                        if(max_breadNumber>0){
                            max_breadNumber--;
                        }
                        bread.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(shirts!=null&&!b) {
                for (Shirt shirt : shirts) {
                    if (shirt.x_position == x && shirt.y_position == y && (MAX_CAPACITY - stock) >= shirt.capacity&& shirt.time_canceling!=0&&shirt.existing) {
                        stock += shirt.capacity;
                        shirtNumber++;
                        if(max_shirtNumber>0){
                            max_shirtNumber--;
                        }
                        shirt.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(icecreams!=null&&!b) {
                for (Icecream icecream : icecreams) {
                    if (icecream.x_position == x && icecream.y_position == y && (MAX_CAPACITY - stock) >= icecream.capacity&& icecream.time_canceling!=0&&icecream.existing) {
                        stock += icecream.capacity;
                        icecreamNumber++;
                        if(max_icecreamNumber>0){
                            max_icecreamNumber--;
                        }
                        icecream.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(lions!=null&&!b) {
                for (Lion lion : lions) {
                    if ((lion.x_position)/110 == x && (lion.y_position)/90 == y && (MAX_CAPACITY - stock) >= lion.capacity && lion.caged == true&&lion.existing) {
                        stock += lion.capacity;
                        lionNumber++;
                        lion.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(bears!=null&&!b) {
                for (Bear bear : bears) {
                    if ((bear.x_position)/110 == x && (bear.y_position)/90 == y && (MAX_CAPACITY - stock) >= bear.capacity && bear.caged == true&&bear.existing) {
                        stock += bear.capacity;
                        bearNumber++;
                        bear.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(tigers!=null&&!b) {
                for (Tiger tiger : tigers) {
                    if ((tiger.x_position)/110 == x && (tiger.y_position)/90 == y && (MAX_CAPACITY - stock) >= tiger.capacity && tiger.caged == true&&tiger.existing) {
                        stock += tiger.capacity;
                        tigerNumber++;
                        tiger.existing=false;
                        b = true;
                        break;
                    }
                }
            }
            if(b){
                System.out.println("Picking Up was successful !");
                Date date = new Date();
                String s = "PickedUp Successfully";
                logger(date,s,true);

            }
            else{
                System.out.println("Picking Up was not successful !");
                Date date = new Date();
                String s = "Picking Up was not successful";
                logger(date,s,false);
            }
        }
    }///Finished:25/3

    public void welling(int turnings){
        if(well==0){
            wellingBool=true;
            wellingTime=turnings+3;
            System.out.println("Water requested");
            Date date = new Date();
            String s = "Water requested";
            logger(date,s,true);
        }
        else {
            System.err.println("Bucket is not empty! You still have "+well+" more times!");
            Date date = new Date();
            String s = "Bucket has Still Water";
            logger(date,s,false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Bucket is not empty!");
            alert.setContentText("You still have "+well+" more times!");
            alert.showAndWait();
        }
    }/// Finished

    public void gardening(int x,int y) {
        boolean find = false;
        if (x >= 6 || y >= 6 || y < 0 || x < 0) {
            System.err.println("Position Out Of Range !!!");
            Date date = new Date();
            String s = "Entered Wrong Position";
            logger(date, s, false);
        }
        else {
            if (lions != null&&!find) {
                for (Lion lion : lions) {
                    if ((lion.x_position) / 110 == x && (lion.y_position) / 90 == y && (MAX_CAPACITY - stock) >= lion.capacity && lion.caged == true && lion.existing) {
                        find = true;
                        break;
                    }
                    else if((lion.x_position) / 110 == x && (lion.y_position) / 90 == y  && lion.existing){
                        lion.cageInputNum++;
                        if(lion.cageInputNum==4){
                            lion.caged=true;
                        }

                        find = true;

                    }
                }
            }
            if (bears != null&&!find) {
                for (Bear bear : bears) {
                    if ((bear.x_position) / 110 == x && (bear.y_position) / 90 == y && (MAX_CAPACITY - stock) >= bear.capacity && bear.caged == true && bear.existing) {
                        find = true;
                        break;
                    }
                    else if((bear.x_position) / 110 == x && (bear.y_position) / 90 == y  && bear.existing){
                        bear.cageInputNum++;
                        if(bear.cageInputNum==5){
                            bear.caged=true;
                        }

                        find = true;

                    }
                }
            }
            if (tigers != null&&!find) {
                for (Tiger tiger : tigers) {
                    if ((tiger.x_position) / 110 == x && (tiger.y_position) / 90 == y && (MAX_CAPACITY - stock) >= tiger.capacity && tiger.caged == true && tiger.existing) {
                        find = true;
                        break;
                    }
                    else if((tiger.x_position) / 110 == x && (tiger.y_position) / 90 == y  && tiger.existing){
                        tiger.cageInputNum++;
                        if(tiger.cageInputNum==5){
                            tiger.caged=true;
                        }
                        find = true;

                    }
                }
            }
            if (eggs != null&&!find) {
                for (Egg egg : eggs) {
                    if ((egg.x_position) / 110 == x && (egg.y_position) / 90 == y && (MAX_CAPACITY - stock) >= egg.capacity && egg.time_canceling != 0 && egg.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (feathers != null&&!find) {
                for (Feather feather : feathers) {
                    if ((feather.x_position) / 110 == x && (feather.y_position) / 90 == y && (MAX_CAPACITY - stock) >= feather.capacity && feather.time_canceling != 0 && feather.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (milks != null&&!find) {
                for (Milk milk : milks) {
                    if ((milk.x_position) / 110 == x && (milk.y_position) / 90 == y && (MAX_CAPACITY - stock) >= milk.capacity && milk.time_canceling != 0 && milk.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (powders != null&&!find) {
                for (Powder powder : powders) {
                    if (powder.x_position == x && powder.y_position == y && (MAX_CAPACITY - stock) >= powder.capacity && powder.time_canceling != 0 && powder.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (cloths != null&&!find) {
                for (Cloth cloth : cloths) {
                    if (cloth.x_position == x && cloth.y_position == y && (MAX_CAPACITY - stock) >= cloth.capacity && cloth.time_canceling != 0 && cloth.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (packedMilks != null&&!find) {
                for (PackedMilk packedMilk : packedMilks) {
                    if (packedMilk.x_position == x && packedMilk.y_position == y && (MAX_CAPACITY - stock) >= packedMilk.capacity && packedMilk.time_canceling != 0 && packedMilk.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (breads != null&&!find) {
                for (Bread bread : breads) {
                    if (bread.x_position == x && bread.y_position == y && (MAX_CAPACITY - stock) >= bread.capacity && bread.time_canceling != 0 && bread.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (shirts != null&&!find) {
                for (Shirt shirt : shirts) {
                    if (shirt.x_position == x && shirt.y_position == y && (MAX_CAPACITY - stock) >= shirt.capacity && shirt.time_canceling != 0 && shirt.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (icecreams != null&&!find) {
                for (Icecream icecream : icecreams) {
                    if (icecream.x_position == x && icecream.y_position == y && (MAX_CAPACITY - stock) >= icecream.capacity && icecream.time_canceling != 0 && icecream.existing) {
                        find = true;
                        break;
                    }
                }
            }
            if (!find) {
                if (well > 0) {
                    well -= 1;
                    farm[x][y] += 1;
                    System.out.println("Grass Planted " + "[" + x + "," + y + "]");
                    Date date = new Date();
                    String s = "Grass Planted";
                    logger(date, s, true);
                } else {
                    System.err.println("Not Enough Water !!!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Water!!!!");
                    alert.setContentText("You Need to Use Well");
                    alert.showAndWait();
                    Date date = new Date();
                    String s = "Not Enough Water to Plant Grass";
                    logger(date, s, false);
                }
            }
            else if(find){
                pickup_product(x,y);
            }
        }
    }

    /// Finished

    public void working_workshop(String name,int turnings){
        if(name.equalsIgnoreCase("EggPowderPlant")){
           if(eggNumber==0){
               System.err.println("Not Enough Eggs !!");
               Date date = new Date();
               String s = "Not Enough Item";
               logger(date,s,false);
           }
           else if(eggPowderPlant.level==0){
               System.err.println("WorkShop doesn't exist");
               Date date = new Date();
               String s = "WorkShop Level = 0";
               logger(date,s,false);
           }
           else if(eggNumber>=1 && eggPowderPlant.level==1){
               eggNumber--;
               stock--;
               eggPowderPlantTime=turnings+4;
               workEggPowderPlant=true;
               System.out.println("Producing in Progress...");
               Date date = new Date();
               String s = "Producing Started";
               logger(date,s,true);
           }
           else if(eggNumber==1 && eggPowderPlant.level==2){
               eggNumber--;
               stock--;
               eggPowderPlantTime=turnings+2;
               workEggPowderPlant=true;
               System.out.println("Producing in Progress...");
               Date date = new Date();
               String s = "Producing Started";
               logger(date,s,true);
           }
           else if(eggNumber>=2 && eggPowderPlant.level==2){
               eggNumber-=2;
               eggPowderPlant.work2=true;
               stock-=2;
               eggPowderPlantTime=turnings+4;
               workEggPowderPlant=true;
               System.out.println("Producing in Progress...");
               Date date = new Date();
               String s = "Producing Started";
               logger(date,s,true);
           }
        }
        else if(name.equalsIgnoreCase("WeavingFactory")){
            if(featherNumber==0){
                System.err.println("Not Enough Feathers !!");
                Date date = new Date();
                String s = "Not Enough Item";
                logger(date,s,false);
            }
            else if(weavingFactory.level==0){
                System.err.println("WorkShop doesn't exist");
                Date date = new Date();
                String s = "WorkShop Level = 0";
                logger(date,s,false);
            }
            else if(featherNumber>=1 && weavingFactory.level==1){
                featherNumber--;
                stock--;
                weavingFactoryTime=turnings+4;
                workWeavingFactory=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(featherNumber==1 && weavingFactory.level==2){
                featherNumber--;
                stock--;
                weavingFactoryTime=turnings+2;
                workWeavingFactory=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(featherNumber>=2 && weavingFactory.level==2){
                featherNumber-=2;
                stock-=2;
                weavingFactory.work2=true;
                weavingFactoryTime=turnings+4;
                workWeavingFactory=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
        }
        else if(name.equalsIgnoreCase("MilkPacking")){
            if(milkNumber==0){
                System.err.println("Not Enough Milk !!");
                Date date = new Date();
                String s = "Not Enough Item";
                logger(date,s,false);
            }
            else if(milkPacking.level==0){
                System.err.println("WorkShop doesn't exist");
                Date date = new Date();
                String s = "WorkShop Level = 0";
                logger(date,s,false);
            }
            else if(milkNumber>=1 && milkPacking.level==1){
                milkNumber--;
                stock--;
                milkPackingTime=turnings+4;
                workMilkPacking=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(milkNumber==1 && milkPacking.level==2){
                milkNumber--;
                stock--;
                milkPackingTime=turnings+2;
                workMilkPacking=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(milkNumber>=2 && milkPacking.level==2){
                milkNumber-=2;
                stock-=2;
                milkPacking.work2=true;
                milkPackingTime=turnings+4;
                workMilkPacking=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
        }
        else if(name.equalsIgnoreCase("Bakery")){
            if(powderNumber==0){
                System.err.println("Not Enough Powder !!");
                Date date = new Date();
                String s = "Not Enough Item";
                logger(date,s,false);
            }
            else if(bakery.level==0){
                System.err.println("WorkShop doesn't exist");
                Date date = new Date();
                String s = "WorkShop Level = 0";
                logger(date,s,false);
            }
            else if(powderNumber>=1 && bakery.level==1){
                powderNumber--;
                stock-=2;
                bakeryTime=turnings+4;
                workBakery=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(powderNumber==1 && bakery.level==2){
                powderNumber--;
                stock-=2;
                bakeryTime=turnings+2;
                workBakery=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(powderNumber>=2 && bakery.level==2){
                powderNumber-=2;
                stock-=4;
                bakery.work2=true;
                bakeryTime=turnings+4;
                workBakery=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }

        }
        else if(name.equalsIgnoreCase("Sewing")){
            if(clothNumber==0){
                System.err.println("Not Enough Cloth !!");
                Date date = new Date();
                String s = "Not Enough Item";
                logger(date,s,false);
            }
            else if(sewing.level==0){
                System.err.println("WorkShop doesn't exist");
                Date date = new Date();
                String s = "WorkShop Level = 0";
                logger(date,s,false);
            }
            else if(clothNumber>=1 && sewing.level==1){
                clothNumber--;
                stock-=2;
                sewingTime=turnings+4;
                workSewing=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(clothNumber==1 && sewing.level==2){
                clothNumber--;
                stock-=2;
                sewingTime=turnings+2;
                workSewing=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(clothNumber>=2 && sewing.level==2){
                clothNumber-=2;
                stock-=4;
                sewing.work2=true;
                sewingTime=turnings+4;
                workSewing=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }

        }
        else if(name.equalsIgnoreCase("IceCreamShop")){
            if(packedMilkNumber==0){
                System.err.println("Not Enough Packed Milks !!");
                Date date = new Date();
                String s = "Not Enough Item";
                logger(date,s,false);
            }
            else if(iceCreamShop.level==0){
                System.err.println("WorkShop doesn't exist");
                Date date = new Date();
                String s = "WorkShop Level = 0";
                logger(date,s,false);
            }
            else if(packedMilkNumber>=1 && iceCreamShop.level==1){
                packedMilkNumber--;
                stock-=2;
                iceCreamShopTime=turnings+4;
                workIceCreamShop=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(packedMilkNumber==1 && iceCreamShop.level==2){
                packedMilkNumber--;
                stock-=2;
                iceCreamShopTime=turnings+2;
                workIceCreamShop=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
            else if(packedMilkNumber>=2 && iceCreamShop.level==2){
                packedMilkNumber-=2;
                stock-=4;
                iceCreamShop.work2=true;
                iceCreamShopTime=turnings+4;
                workIceCreamShop=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }
        }
        else if(name.equalsIgnoreCase("JoojeKeshi")){
            if(eggNumber==0){
                System.err.println("Not Enough Eggs !!");
                Date date = new Date();
                String s = "Not Enough Item";
                logger(date,s,false);
            }
            else if(eggNumber>=1){
                eggNumber--;
                stock--;
                joojeKeshiTime=turnings+4;
                workJoojeKeshi=true;
                System.out.println("Producing in Progress...");
                Date date = new Date();
                String s = "Producing Started";
                logger(date,s,true);
            }

        }
    }//Finished

    // cage animal by one word
    public void caging_animal(int x,int y){
        if(attackerAnimals!=null) {
            for (AttackerAnimal attackerAnimal1 : attackerAnimals) {
                if (attackerAnimal1.x_position == x && attackerAnimal1.y_position == y) {
                    if(lions!=null) {
                        for (Lion lion1 : lions) {
                            if (lion1.equals(attackerAnimal1) && lion1.existing) {
                                lion1.cageInputNum++;
                                if (lion1.cageInputNum == 3) {
                                    lion1.caged = true;
                                    System.out.println("Lion Caged Successfully.");
                                    Date date = new Date();
                                    String s = "Wild Animal Caged";
                                    logger(date,s,true);
                                }
                            }
                        }
                    }
                    if(bears!=null) {
                        for (Bear bear1 : bears) {
                            if (bear1.equals(attackerAnimal1) && bear1.existing) {
                                bear1.cageInputNum++;
                                if (bear1.cageInputNum == 4) {
                                    bear1.caged = true;
                                    System.out.println("Bear Caged Successfully.");
                                    Date date = new Date();
                                    String s = "Wild Animal Caged";
                                    logger(date,s,true);
                                }
                            }
                        }
                    }
                    if(tigers!=null) {
                        for (Tiger tiger1 : tigers) {
                            if (tiger1.equals(attackerAnimal1) && tiger1.existing) {
                                tiger1.cageInputNum++;
                                if (tiger1.cageInputNum == 4) {
                                    tiger1.caged = true;
                                    System.out.println("Tiger Caged Successfully.");
                                    Date date = new Date();
                                    String s = "Wild Animal Caged";
                                    logger(date,s,true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }/////finished updates:25/3

    // decrease cage's words
    public void decreasingCageNum(){
        if(lions!=null){
            for(Lion lion1 : lions){
                if(lion1.cageInputNum!=0&&lion1.caged==false&&lion1.existing){
                    lion1.cageInputNum--;
                }
            }
        }
        if(bears!=null){
            for(Bear bear1 : bears){
                if(bear1.cageInputNum!=0&&bear1.caged==false&&bear1.existing){
                    bear1.cageInputNum--;
                }
            }
        }
        if(tigers!=null){
            for(Tiger tiger1 : tigers){
                if(tiger1.cageInputNum!=0&&tiger1.caged==false&&tiger1.existing){
                    tiger1.cageInputNum--;
                }
            }
        }
    }//25/3
    // decreasing time canceling
    public void decreasingTimeCanceling(){
        if(eggs!=null) {
            for (Egg egg1 : eggs) {
                if (egg1.time_canceling != 0&&egg1.existing) egg1.time_canceling--;
                else if(egg1.time_canceling==0){
                    egg1.existing=false;
                }
            }
        }
        if(feathers!=null) {
            for (Feather feather1 : feathers) {
                if (feather1.time_canceling != 0&&feather1.existing) feather1.time_canceling--;
                else if(feather1.time_canceling==0){
                    feather1.existing=false;
                }
            }
        }
        if(milks!=null) {
            for (Milk milk1 : milks) {
                if (milk1.time_canceling != 0&&milk1.existing) milk1.time_canceling--;
                else if(milk1.time_canceling==0){
                    milk1.existing=false;
                }
            }
        }
        if(powders!=null) {
            for (Powder powder1 : powders) {
                if (powder1.time_canceling != 0&&powder1.existing) powder1.time_canceling--;
                else if(powder1.time_canceling==0){
                    powder1.existing=false;
                }
            }
        }
        if(cloths!=null) {
            for (Cloth cloth1 : cloths) {
                if (cloth1.time_canceling != 0&&cloth1.existing) cloth1.time_canceling--;
                else if(cloth1.time_canceling==0){
                    cloth1.existing=false;
                }
            }
        }
        if(packedMilks!=null) {
            for (PackedMilk packedMilk1 : packedMilks) {
                if (packedMilk1.time_canceling != 0&&packedMilk1.existing) packedMilk1.time_canceling--;
                else if(packedMilk1.time_canceling==0){
                    packedMilk1.existing=false;
                }
            }
        }
        if(breads!=null) {
            for (Bread bread1 : breads) {
                if (bread1.time_canceling != 0&&bread1.existing) bread1.time_canceling--;
                else if(bread1.time_canceling==0){
                    bread1.existing=false;
                }
            }
        }
        if(shirts!=null) {
            for (Shirt shirt1 : shirts) {
                if (shirt1.time_canceling != 0&&shirt1.existing) shirt1.time_canceling--;
                else if(shirt1.time_canceling==0){
                    shirt1.existing=false;
                }
            }
        }
        if(icecreams!=null) {
            for (Icecream icecream1 : icecreams) {
                if (icecream1.time_canceling != 0&&icecream1.existing) icecream1.time_canceling--;
                else if(icecream1.time_canceling==0){
                    icecream1.existing=false;
                }
            }
        }
        if(chickens!=null) {
            for (Chicken chicken : chickens) {
                if(chicken.life<=20){
                    chicken.existing=false;
                }
            }
        }
        if(turkeys!=null) {
            for (Turkey turkey : turkeys) {
                if(turkey.life<=20){
                    turkey.existing=false;
                }
            }
        }
        if(buffaloes!=null) {
            for (Buffalo buffalo : buffaloes) {
                if(buffalo.life<=20){
                    buffalo.existing=false;
                }
            }
        }
    }//25/3

    // removing animal after time
    public void timeCancelingCage(){
        if(lions!=null){
            for(Lion lion1 : lions){
                if(lion1.caged&&lion1.existing){
                    lion1.time_canceling--;
                    if(lion1.time_canceling==0){
                        lion1.existing=false;
                    }
                }
            }
        }
        if(bears!=null){
            for(Bear bear1 : bears){
                if(bear1.caged&&bear1.existing){
                    bear1.time_canceling--;
                    if(bear1.time_canceling==0){
//                        bears.remove(bear1);
//                        attackerAnimals.remove(bear1);
                        bear1.existing=false;
                    }
                }
            }
        }
        if(tigers!=null){
            for(Tiger tiger1 : tigers){
                if(tiger1.caged&&tiger1.existing){
                    tiger1.time_canceling--;
                    if(tiger1.time_canceling==0){
//                        tigers.remove(tiger1);
//                        attackerAnimals.remove(tiger1);
                        tiger1.existing=false;
                    }
                }
            }
        }

    }/////finished updates

    public void truck_load(String name){
        int counter=0;
        String[] items= {"EGG","FEATHER","MILK","POWDER","CLOTH","PACKEDMILK","BREAD","SHIRT","ICECREAM","LION","BEAR","TIGER","CHICKEN","TURKEY","BUFFALO"};
        for(int i=0;i<15;i++){
            if(!items[i].equalsIgnoreCase(name)) counter++;
        }
        if(counter==15) {
            System.err.println("Invalid Item!!");
            Date date = new Date();
            String s = "Invalid Item for Loading Truck";
            logger(date,s,false);
        }
        else {
            if (name.equalsIgnoreCase(items[0]) && (truck.load + egg1.capacity <= truck.MAX_LOAD)&&eggNumber!=0) {
                eggNumber--;
                coin1 += egg1.price;
                truck.load += egg1.capacity;
                stock -= egg1.capacity;
                truck.truckEggNum++;
                System.out.println(items[0].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[1]) && (truck.load + feather1.capacity <= truck.MAX_LOAD)&&featherNumber!=0) {
                featherNumber--;
                coin1 += feather1.price;
                truck.load += feather1.capacity;
                stock -= feather1.capacity;
                truck.truckFeatherNum++;
                System.out.println(items[1].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[2]) && (truck.load + milk1.capacity <= truck.MAX_LOAD)&&milkNumber!=0) {
                milkNumber--;
                coin1 += milk1.price;
                truck.load += milk1.capacity;
                stock -= milk1.capacity;
                truck.truckMilkNum++;
                System.out.println(items[2].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[3]) && (truck.load + powder1.capacity <= truck.MAX_LOAD)&&powderNumber!=0) {
                powderNumber--;
                coin1 += powder1.price;
                truck.load += powder1.capacity;
                stock -= powder1.capacity;
                truck.truckPowderNum++;
                System.out.println(items[3].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[4]) && (truck.load + cloth1.capacity <= truck.MAX_LOAD)&&clothNumber!=0) {
                clothNumber--;
                coin1 += cloth1.price;
                truck.load += cloth1.capacity;
                stock -= cloth1.capacity;
                truck.truckClothNum++;
                System.out.println(items[4].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[5]) && (truck.load + packedMilk1.capacity <= truck.MAX_LOAD)&&packedMilkNumber!=0) {
                packedMilkNumber--;
                coin1 += packedMilk1.price;
                truck.load += packedMilk1.capacity;
                stock -= packedMilk1.capacity;
                truck.truckPackedMilkNum++;
                System.out.println(items[5].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[6]) && (truck.load + bread1.capacity <= truck.MAX_LOAD)&&breadNumber!=0) {
                breadNumber--;
                coin1 += bread1.price;
                truck.load += bread1.capacity;
                stock -= bread1.capacity;
                truck.truckBreadNum++;
                System.out.println(items[6].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[7]) && (truck.load + shirt1.capacity <= truck.MAX_LOAD)&&shirtNumber!=0) {
                shirtNumber--;
                coin1 += shirt1.price;
                truck.load += shirt1.capacity;
                stock -= shirt1.capacity;
                truck.truckShirtNum++;
                System.out.println(items[7].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[8]) && (truck.load + icecream1.capacity <= truck.MAX_LOAD)&&icecreamNumber!=0) {
                icecreamNumber--;
                coin1 += icecream1.price;
                truck.load += icecream1.capacity;
                stock -= icecream1.capacity;
                truck.truckIceCreamNum++;
                System.out.println(items[8].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            //wild animals
            else if (name.equalsIgnoreCase(items[9]) && (truck.load + lion1.capacity <= truck.MAX_LOAD)&&lionNumber!=0) {
                lionNumber--;
                coin1 += lion1.price;
                truck.load += lion1.capacity;
                stock -= lion1.capacity;
                truck.truckLionNum++;
                System.out.println(items[9].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[10]) && (truck.load + bear1.capacity <= truck.MAX_LOAD)&&bearNumber!=0) {
                bearNumber--;
                coin1 += bear1.price;
                truck.load += bear1.capacity;
                stock -= bear1.capacity;
                truck.truckBearNum++;
                System.out.println(items[10].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[11]) && (truck.load + tiger1.capacity <= truck.MAX_LOAD)&&tigerNumber!=0) {
                tigerNumber--;
                coin1 += tiger1.price;
                truck.load += tiger1.capacity;
                stock -= tiger1.capacity;
                truck.truckTigerNum++;
                System.out.println(items[11].toLowerCase()+" loaded successfully!!");
                Date date = new Date();
                String s = "Item Loaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[12]) && (truck.load + chicken1.capacity <= truck.MAX_LOAD)&& chickens.size()!=0) {
                boolean b=false;
                for(Chicken newChicken: chickens){
                    if(newChicken.existing){
                        b=true;
                        break;
                    }
                }
                if(b){
                    for(Chicken newChicken: chickens){
                        if(newChicken.existing){
                            newChicken.existing=false;
                            break;
                        }
                    }
                    coin1 += chicken1.price;
                    truck.load += chicken1.capacity;
                    truck.truckChickNum++;
                    System.out.println(items[12].toLowerCase()+" loaded successfully!!");
                    Date date = new Date();
                    String s = "Item Loaded";
                    logger(date,s,true);
                }
                else {
                    System.out.println("chicken not found");
                    Date date = new Date();
                    String s = "Item didn't Load";
                    logger(date,s,false);
                }


            }
            else if (name.equalsIgnoreCase(items[13]) && (truck.load + turkey1.capacity <= truck.MAX_LOAD)&& turkeys.size()!=0) {
                boolean b=false;
                for (Turkey turkey:turkeys) {
                    if(turkey.existing){
                        b=true;
                        break;
                    }
                }
                if(b){
                    for (Turkey turkey:turkeys) {
                        if(turkey.existing){
                            turkey.existing=false;
                            break;
                        }
                    }
                    coin1 += turkey1.price;
                    truck.load += turkey1.capacity;
                    truck.truckTurkNum++;
                    System.out.println(items[13].toLowerCase()+" loaded successfully!!");
                    Date date = new Date();
                    String s = "Item Loaded";
                    logger(date,s,true);
                }
                else {
                    System.out.println("turkey not found");
                    Date date = new Date();
                    String s = "Item didn't Load";
                    logger(date,s,false);
                }

            }
            else if (name.equalsIgnoreCase(items[14]) && (truck.load + buffalo1.capacity <= truck.MAX_LOAD)&& buffaloes.size()!=0) {
                boolean b=false;
                for (Buffalo buffalo:buffaloes) {
                    if(buffalo.existing){
                        b=true;
                        break;
                    }
                }
                if(b){
                    for (Buffalo buffalo:buffaloes) {
                        if(buffalo.existing){
                            buffalo.existing=false;
                            break;
                        }
                    }
                    coin1 += buffalo1.price;
                    truck.load += buffalo1.capacity;
                    truck.truckBufNum++;
                    System.out.println(items[14].toLowerCase()+" loaded successfully!!");
                    Date date = new Date();
                    String s = "Item Loaded";
                    logger(date,s,true);
                }
                else {
                    System.out.println("buffalo not found");
                    Date date = new Date();
                    String s = "Item didn't Load";
                    logger(date,s,false);
                }

            }
            else {
                System.err.println("Item cannot be Loaded !!");
                Date date = new Date();
                String s = "Item didn't Load";
                logger(date, s, false);
            }
        }


    }/// 25/3

    public void truck_unload(String name){
        int counter=0;
        String[] items= {"EGG","FEATHER","MILK","POWDER","CLOTH","PACKEDMILK","BREAD","SHIRT","ICECREAM","LION","BEAR","TIGER","CHICKEN","TURKEY","BUFFALO"};
        for(int i=0;i<15;i++){
            if(!items[i].equalsIgnoreCase(name)) counter++;
        }
        if(counter==15) {
            System.err.println("Invalid Item!!");
            Date date = new Date();
            String s = "Invalid Item for unLoading Truck";
            logger(date,s,false);
        }
        else {
            if (name.equalsIgnoreCase(items[0]) && truck.truckEggNum!=0) {
                eggNumber++;
                coin1 -= egg1.price;
                truck.load -= egg1.capacity;
                stock += egg1.capacity;
                truck.truckEggNum--;
                System.out.println(items[0].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[1]) && truck.truckFeatherNum!=0) {
                featherNumber++;
                coin1 -= feather1.price;
                truck.load -= feather1.capacity;
                stock += feather1.capacity;
                truck.truckFeatherNum--;
                System.out.println(items[1].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[2]) && truck.truckMilkNum!=0) {
                milkNumber++;
                coin1 -= milk1.price;
                truck.load -= milk1.capacity;
                stock += milk1.capacity;
                truck.truckMilkNum--;
                System.out.println(items[2].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[3]) && truck.truckPowderNum!=0) {
                powderNumber++;
                coin1 -= powder1.price;
                truck.load -= powder1.capacity;
                stock += powder1.capacity;
                truck.truckPowderNum--;
                System.out.println(items[3].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[4]) && truck.truckClothNum!=0) {
                clothNumber++;
                coin1 -= cloth1.price;
                truck.load -= cloth1.capacity;
                stock += cloth1.capacity;
                truck.truckClothNum--;
                System.out.println(items[4].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[5]) && truck.truckMilkNum!=0) {
                packedMilkNumber++;
                coin1 -= packedMilk1.price;
                truck.load -= packedMilk1.capacity;
                stock += packedMilk1.capacity;
                truck.truckPackedMilkNum--;
                System.out.println(items[5].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[6]) && truck.truckBreadNum!=0 ){
                breadNumber++;
                coin1 -= bread1.price;
                truck.load -= bread1.capacity;
                stock += bread1.capacity;
                truck.truckBreadNum--;
                System.out.println(items[6].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[7]) && truck.truckShirtNum!=0) {
                shirtNumber++;
                coin1 -= shirt1.price;
                truck.load -= shirt1.capacity;
                stock += shirt1.capacity;
                truck.truckShirtNum--;
                System.out.println(items[7].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[8]) && truck.truckIceCreamNum!=0) {
                icecreamNumber++;
                coin1 -= icecream1.price;
                truck.load -= icecream1.capacity;
                stock += icecream1.capacity;
                truck.truckIceCreamNum--;
                System.out.println(items[8].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            //wild animals
            else if (name.equalsIgnoreCase(items[9]) && truck.truckLionNum!=0) {
                lionNumber++;
                coin1 -= lion1.price;
                truck.load -= lion1.capacity;
                stock += lion1.capacity;
                truck.truckLionNum--;
                System.out.println(items[9].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[10]) && truck.truckBearNum!=0) {
                bearNumber++;
                coin1 -= bear1.price;
                truck.load -= bear1.capacity;
                stock += bear1.capacity;
                truck.truckBearNum--;
                System.out.println(items[10].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[11]) && truck.truckTigerNum!=0) {
                tigerNumber++;
                coin1 -= tiger1.price;
                truck.load -= tiger1.capacity;
                stock += tiger1.capacity;
                truck.truckTigerNum--;
                System.out.println(items[11].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[12]) && truck.truckChickNum!=0) {
                coin1 -= chicken1.price;
                truck.load -= chicken1.capacity;
                for(Chicken chicken : chickens){
                    if(chicken.existing==false){
                        chicken.existing=true;
                        break;
                    }
                }
                truck.truckChickNum--;
                System.out.println(items[12].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);

            }
            else if (name.equalsIgnoreCase(items[13]) && truck.truckTurkNum!=0) {
                coin1 -= turkey1.price;
                truck.load -= turkey1.capacity;
                for(Turkey turkey : turkeys){
                    if(turkey.existing==false){
                        turkey.existing=true;
                        break;
                    }
                }
                truck.truckTurkNum--;
                System.out.println(items[13].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else if (name.equalsIgnoreCase(items[14]) && truck.truckBufNum!=0) {
                coin1 -= buffalo1.price;
                truck.load -= buffalo1.capacity;
                for(Buffalo buffalo : buffaloes){
                    if(buffalo.existing==false){
                        buffalo.existing=true;
                        break;
                    }
                }
                truck.truckBufNum--;
                System.out.println(items[14].toLowerCase()+" unloaded successfully!!");
                Date date = new Date();
                String s = "Item Unloaded";
                logger(date,s,true);
            }
            else System.err.println("Truck is Empty!!");
            Date date = new Date();
            String s = "Item Unloaded";
            logger(date,s,false);
        }
    }  // 25/3

    public void truck_go(int turnings){
        if(truck.load!=0) {
            truckBool = true;
            truckTime = turnings + 10;
            System.out.println("Truck is going to Bazaar...");
            Date date = new Date();
            String s = "Truck started going to Bazaar";
            logger(date,s,true);
        }
        else {
            System.err.println("Truck is Empty!!");
            Date date = new Date();
            String s = "Truck is Empty";
            logger(date,s,false);

        }
    }

    public void check_time(int turnings){
        if(turnings==wellingTime&&wellingBool==true){
            wellingBool=false;
            well=5;
            System.out.println("Bucket loaded");
            Date date = new Date();
            String s = "Bucket loaded";
            logger(date,s,true);
        }
        if(turnings==truckTime&&truckBool==true){
            truckBool=false;
            coins+=coin1;
            coin1=0;
            System.out.println("Truck is back to farm");
            Date date = new Date();
            String s = "Truck is back to farm";
            logger(date,s,true);
            truck.load=0;
        }
        if(turnings==eggPowderPlantTime&&workEggPowderPlant==true){
            eggPowderPlant.tolid(eggNumber,this);
            eggPowderPlant.work2=false;
            System.out.println("Your Product Is Produced!");
            workEggPowderPlant=false;
            Date date = new Date();
            String s = "Product Produced";
            logger(date,s,true);
        }
        if(turnings==weavingFactoryTime&&workWeavingFactory==true){
            weavingFactory.tolid(featherNumber,this);
            weavingFactory.work2=false;
            System.out.println("Your Product Is Produced!");
            workWeavingFactory=false;
            Date date = new Date();
            String s = "Product Produced";
            logger(date,s,true);
        }
        if(turnings==milkPackingTime&&workMilkPacking==true){
            milkPacking.tolid(milkNumber,this);
            milkPacking.work2=false;
            System.out.println("Your Product Is Produced!");
            workMilkPacking=false;
            Date date = new Date();
            String s = "Product Produced";
            logger(date,s,true);
        }
        if(turnings==bakeryTime&&workBakery==true){
            bakery.tolid(powderNumber,this);
            bakery.work2=false;
            System.out.println("Your Product Is Produced!");
            workBakery=false;
            Date date = new Date();
            String s = "Product Produced";
            logger(date,s,true);
        }
        if(turnings==sewingTime&&workSewing==true){
            sewing.tolid(clothNumber,this);
            sewing.work2=false;
            System.out.println("Your Product Is Produced!");
            workSewing=false;
            Date date = new Date();
            String s = "Product Produced";
            logger(date,s,true);
        }
        if(turnings==iceCreamShopTime && workIceCreamShop==true){
            iceCreamShop.tolid(packedMilkNumber,this);
            iceCreamShop.work2=false;
            System.out.println("Your Product Is Produced!");
            workIceCreamShop=false;
            Date date = new Date();
            String s = "Product Produced";
            logger(date,s,true);
        }
        if(turnings==joojeKeshiTime && workJoojeKeshi==true){
            joojeKeshi.tolid(eggNumber,this);
            joojeKeshi.work2=false;
            System.out.println("Your Product Is Produced!");
            workJoojeKeshi=false;
            Date date = new Date();
            String s = "Product Produced";
            logger(date,s,true);
        }

        if(max_icecreamNumber<=0&&max_shirtNumber<=0&&max_breadNumber<=0&&max_milkNumber<=0&&max_packedMilkNumber<=0&&max_eggNumber<=0&&max_clothNumber<=0&&max_featherNumber<=0&&max_powderNumber<=0){
            win=true;
        }
    }

    public void producing(Stage primaryStage,Pane root1){
        if(chickens!=null) {
            for (Chicken chicken1 : chickens) {
                if (chicken1.boolProduce && chicken1.existing) {
                    Egg newEgg = new Egg(chicken1.x_position, chicken1.y_position);
                    if(eggs==null) eggs = new ArrayList<>();
                    eggs.add(newEgg);
                }
            }
        }
        if(turkeys!=null) {
            for (Turkey turkey1 : turkeys) {
                if (turkey1.boolProduce && turkey1.existing) {
                    Feather newFeather = new Feather(turkey1.x_position, turkey1.y_position);
                    if(feathers==null) feathers = new ArrayList<>();
                    feathers.add(newFeather);
                }
            }
        }
        if(buffaloes!=null) {
            for (Buffalo buffalo1 : buffaloes) {
                if (buffalo1.boolProduce && turkey1.existing) {
                    Milk newMilk = new Milk(buffalo1.x_position, buffalo1.y_position);
                    if(milks==null) milks= new ArrayList<>();
                    milks.add(newMilk);
                }
            }
        }
        drawProduct(primaryStage,root1);

    }  // 25/3

    public int[] searching(int x,int y){
        int[] near=new int[2];
        double min=100;
        int counter=0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(farm[i][j]!=0){
                    double fasele=Math.sqrt((i-x)*(i-x)+(j-y)*(j-y));
                    if(min>fasele){
                        min=fasele;
                        near[0]=i;
                        near[1]=j;
                    }
                }
                else {
                    counter+=1;
                }
            }
        }
        if(counter==36){
            System.err.println("No Grass In Ground !");
            Date date = new Date();
            String s = "No Grass In Ground";
            logger(date,s,false);
            return null;
        }
        return near;
    }

    public int[] searchingProduct(int x,int y){
        int[] near=new int[3];
        double min=1000;
        int counter=0;
        if(eggs!=null) {
            for (Egg egg : eggs) {
                if(egg.existing ){
                    double s = Math.sqrt(((egg.x_position)/110 - x)*((egg.x_position)/110 - x) + ((egg.y_position)/90 - y)*((egg.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = egg.x_position;
                        near[1] = egg.y_position;
                        near[2] = 0;
                        counter++;
                    }
                }
            }
        }
        if(feathers!=null) {
            for (Feather feather : feathers) {
                if(feather.existing){
                    double s = Math.sqrt(((feather.x_position)/110 - x) * ((feather.x_position)/110 - x) + ((feather.y_position)/90 - y) * ((feather.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = feather.x_position;
                        near[1] = feather.y_position;
                        near[2] = 1;
                        counter++;
                    }
                }
            }
        }
        if(milks!=null) {
            for (Milk milk : milks) {
                if(milk.existing){
                    double s = Math.sqrt(((milk.x_position)/110 - x) * ((milk.x_position)/110 - x) + ((milk.y_position)/90 - y)  *((milk.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = milk.x_position;
                        near[1] = milk.y_position;
                        near[2] = 2;
                        counter++;
                    }
                }
            }
        }
        if(powders !=null) {
            for (Powder powder : powders) {
                if(powder.existing){
                    double s = Math.sqrt(((powder.x_position)/110 - x) * ((powder.x_position)/110 - x) + ((powder.y_position)/90 - y) * ((powder.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = powder.x_position;
                        near[1] = powder.y_position;
                        counter++;
                        near[2] = 3;
                    }
                }
            }
        }
        if(cloths!=null) {
            for (Cloth cloth : cloths) {
                if(cloth.existing){
                    double s = Math.sqrt(((cloth.x_position)/110 - x) * ((cloth.x_position)/110 - x) + ((cloth.y_position)/90 - y) * ((cloth.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = cloth.x_position;
                        near[1] = cloth.y_position;
                        counter++;
                        near[2] = 4;
                    }
                }
            }
        }
        if(packedMilks!=null) {
            for (PackedMilk packedMilk : packedMilks) {
                if(packedMilk.existing){
                    double s = Math.sqrt(((packedMilk.x_position)/110 - x) * ((packedMilk.x_position)/110 - x) + ((packedMilk.y_position)/90 - y) * ((packedMilk.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = packedMilk.x_position;
                        near[1] = packedMilk.y_position;
                        counter++;
                        near[2] = 5;
                    }
                }
            }
        }
        if(breads!=null) {
            for (Bread bread : breads) {
                if(bread.existing){
                    double s = Math.sqrt(((bread.x_position)/110 - x) * ((bread.x_position)/110 - x) + ((bread.y_position)/90 - y) * ((bread.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = bread.x_position;
                        near[1] = bread.y_position;
                        counter++;
                        near[2] = 6;
                    }
                }
            }
        }
        if(shirts!=null) {
            for (Shirt shirt : shirts) {
                if(shirt.existing){
                    double s = Math.sqrt(((shirt.x_position)/110 - x) * ((shirt.x_position)/110 - x) + ((shirt.y_position)/90 - y) * ((shirt.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = shirt.x_position;
                        near[1] = shirt.y_position;
                        counter++;
                        near[2] = 7;
                    }
                }
            }
        }
        if(icecreams!=null) {
            for (Icecream icecream : icecreams) {
                if(icecream.existing){
                    double s = Math.sqrt(((icecream.x_position)/110 - x) * ((icecream.x_position)/110 - x) + ((icecream.y_position)/90 - y) * ((icecream.y_position)/90 - y));
                    if (s < min) {
                        min = s;
                        near[0] = icecream.y_position;
                        near[1] = icecream.y_position;
                        counter++;
                        near[2] = 8;
                    }
                }
            }
        }
        if(counter==0){
            near=null;
        }
        return near;
    }

    // eliminate
    public void eliminating(int x,int y){
        if(producerAnimals!=null) {
            for (ProducerAnimal producerAnimal : producerAnimals) {
                if (producerAnimal.x_position/110 == x/110 && producerAnimal.y_position/90 == y/90) {
                    //producerAnimals.remove(producerAnimal);
                    if(chickens!=null) {
                        for (Chicken chicken : chickens) {
                            if (chicken.x_position/110 == x/110 && chicken.y_position/90 == y/90 && chicken.existing) {
                                chicken.existing = false;
                            }
                        }
                    }
                    if(buffaloes!=null) {
                        for (Buffalo buffalo : buffaloes) {
                            if (buffalo.x_position/110 == x/110 && buffalo.y_position/90 == y/90 && buffalo.existing) {
                                buffalo.existing = false;
                            }
                        }
                    }
                    if(turkeys!=null) {
                        for (Turkey turkey : turkeys) {
                            if (turkey.x_position/110 == x/110 && turkey.y_position/90 == y/90 && turkey.existing) {
                                turkey.existing = false;
                            }
                        }
                    }
                }
            }
        }

        if(eggs!=null) {
            for (Egg egg : eggs) {
                if (egg.x_position/110 == x/110 && egg.y_position/90 == y/90 && egg.existing) {
                    egg.existing = false;
                }
            }
        }
        if(feathers!=null) {
            for (Feather feather : feathers) {
                if (feather.x_position/110 == x/110 && feather.y_position/90 == y/90 && feather.existing) {
                    feather.existing = false;
                }
            }
        }
        if(milks!=null) {
            for (Milk milk : milks) {
                if (milk.x_position/110 == x/110 && milk.y_position/90 == y/90 && milk.existing) {
                    milk.existing = false;
                }
            }
        }
        if(powders!=null) {
            for (Powder powder : powders) {
                if (powder.x_position/110 == x/110 && powder.y_position/90 == y/90 && powder.existing) {
                    powder.existing = false;
                }
            }
        }
        if(cloths!=null) {
            for (Cloth cloth : cloths) {
                if (cloth.x_position/110 == x/110 && cloth.y_position/90 == y/90 && cloth.existing) {
                    cloth.existing = false;
                }
            }
        }
        if(packedMilks!=null) {
            for (PackedMilk packedMilk : packedMilks) {
                if (packedMilk.x_position/110 == x/110 && packedMilk.y_position/90 == y/90 && packedMilk.existing) {
                    packedMilk.existing = false;
                }
            }
        }
        if(breads!=null) {
            for (Bread bread : breads) {
                if (bread.x_position/110 == x/110 && bread.y_position/90 == y/90 && bread.existing) {
                    bread.existing = false;
                }
            }
        }
        if(shirts!=null) {
            for (Shirt shirt : shirts) {
                if (shirt.x_position/110 == x/110 && shirt.y_position/90 == y/90 && shirt.existing) {
                    shirt.existing = false;
                }
            }
        }
        if(icecreams!=null) {
            for (Icecream icecream : icecreams) {
                if (icecream.x_position/110 == x/110 && icecream.y_position/90 == y/90 && icecream.existing) {
                    icecream.existing = false;
                }
            }
        }
    }/////finished updates 25/3

    // moving
    public void moving(){
        if(producerAnimals!=null) {
            for (ProducerAnimal producerAnimal : producerAnimals) {

                if (producerAnimal.existing && producerAnimal.life <= 50 && searching((producerAnimal.x_position)/110, (producerAnimal.y_position)/90) != null) {
                    int x_grass = searching((producerAnimal.x_position)/110, (producerAnimal.y_position)/90)[0];
                    int y_grass = searching((producerAnimal.x_position)/110, (producerAnimal.y_position)/90)[1];
                    if ((producerAnimal.x_position)/110 == x_grass && (producerAnimal.y_position)/90 == y_grass) {
                        // Eating Grass
                        int x = producerAnimal.life;
                        for (ProducerAnimal producerAnimal1 : producerAnimals) {
                            if ((producerAnimal1.x_position)/110 == x_grass && (producerAnimal1.y_position)/90==y_grass && producerAnimal1.life < producerAnimal.life) {
                                if (producerAnimal1.life < x) x = producerAnimal1.life;
                            }
                        }
                        for (ProducerAnimal producerAnimal1 : producerAnimals) {
                            if ((producerAnimal1.x_position)/110 == x_grass && (producerAnimal1.y_position)/90==y_grass && producerAnimal1.life == x) {
                                producerAnimal1.life = 100;
                                farm[x_grass][y_grass] -= 1;
                                break;
                            }
                        }
                    }
                    else {
                        Random random = new Random();
                        int move = random.nextInt() % 2;
                        if (move < 0) {
                            move += 2;
                        }
                        if (move == 0 && x_grass != (producerAnimal.x_position)/110) {
                            if (x_grass > (producerAnimal.x_position)/110) {
                                producerAnimal.x_position += 110;
                            } else {
                                producerAnimal.x_position -= 110;
                            }
                        } else if (move == 1 && y_grass != (producerAnimal.y_position)/90) {
                            if (y_grass > producerAnimal.y_position/90) {
                                producerAnimal.y_position += 90;
                            } else {
                                producerAnimal.y_position -= 90;
                            }
                        } else if (move == 0 && x_grass == (producerAnimal.x_position)/110) {
                            if (y_grass > producerAnimal.y_position/90) {
                                producerAnimal.y_position += 90;
                            } else {
                                producerAnimal.y_position -= 90;
                            }
                        } else if (move == 1 && y_grass == (producerAnimal.y_position)/90) {
                            if (x_grass > producerAnimal.x_position/110) {
                                producerAnimal.x_position += 110;
                            } else {
                                producerAnimal.x_position -= 110;
                            }
                        }
                    }
                }//changed

                if (producerAnimal.existing && (producerAnimal.life > 50 || (producerAnimal.life <= 50 )&& searching((producerAnimal.x_position-110)/110, (producerAnimal.y_position-60)/90) == null)) {
                    if (producerAnimal.life <= 50 && searching((producerAnimal.x_position+20)/110, (producerAnimal.y_position)/90) == null) {
                        System.err.println("NO FOOD!!!!! ANIMALS ARE DYING!!!!!!!!!!");
                        Date date = new Date();
                        String s = "Warning! No Food for Animals! ";
                        logger(date,s,false);
                    }
                    Random random = new Random();
                    int x = random.nextInt() % 4;
                    if (x < 0) {
                        x += 4;
                    }
                    if (x == 0) {
                        if (producerAnimal.x_position < 240) {
                            producerAnimal.x_position+=110;
                        } else {
                            producerAnimal.x_position-=110;
                        }
                    }
                    else if (x == 1) {
                        if (producerAnimal.y_position < 150) {
                            producerAnimal.y_position+=90;
                        } else {
                            producerAnimal.y_position-=90;
                        }
                    }
                    else if (x == 2) {
                        if (producerAnimal.x_position > 460) {
                            producerAnimal.x_position-=110;
                        } else {
                            producerAnimal.x_position+=110;
                        }
                    }
                    else if (x == 3) {
                        if (producerAnimal.y_position > 430) {
                            producerAnimal.y_position-=90;
                        } else {
                            producerAnimal.y_position+=90;
                        }
                    }

                }

            }
        }
        if(attackerAnimals!=null) {
            for (AttackerAnimal attackerAnimal : attackerAnimals) {
                if (attackerAnimal.caged == false && attackerAnimal.existing) {
                    System.out.println(attackerAnimal.y_position);
                    Random random = new Random();
                    int x = random.nextInt() % 4;
                    if (x < 0) {
                        x += 4;
                    }
                    if (x == 0) {
                        if (attackerAnimal.x_position < 240) {
                            attackerAnimal.x_position+=110;
                        } else {
                            attackerAnimal.x_position-=110;
                        }
                    }
                    else if (x == 1) {
                        if (attackerAnimal.y_position < 150) {
                            attackerAnimal.y_position+=90;
                        } else {
                            attackerAnimal.y_position-=90;
                        }
                    }
                    else if (x == 2) {
                        if (attackerAnimal.x_position > 460) {
                            attackerAnimal.x_position-=110;
                        } else {
                            attackerAnimal.x_position+=110;
                        }
                    }
                    else if (x == 3) {
                        if (attackerAnimal.y_position > 410) {
                            attackerAnimal.y_position-=90;
                        } else {
                            attackerAnimal.y_position+=90;
                        }
                    }
                }
            }
        }
        if(attackerAnimals!=null) {
            for (AttackerAnimal attackerAnimal : attackerAnimals) {
                if (attackerAnimal.caged == false && attackerAnimal.existing) {
                    eliminating(attackerAnimal.x_position, attackerAnimal.y_position);
                }
            }
        }
        if(cats!=null) {
            for (Cat cat : cats) {
                if (cat.existing && searchingProduct(cat.x_position, cat.y_position) != null) {
                    int x_product = searchingProduct(cat.x_position, cat.y_position)[0];
                    int y_product = searchingProduct(cat.x_position, cat.y_position)[1];
                    System.out.println(cat.x_position+"plpl"+cat.y_position);
                    int z = searchingProduct(cat.x_position, cat.y_position)[2];
                    if ((cat.x_position)/110 == x_product/110 && (cat.y_position)/90 == y_product/90) {
                        // pickup product
                        pickup_product((cat.x_position)/110,(cat.y_position)/90);
                    }

                    Random random = new Random();
                    int move = random.nextInt() % 2;
                    if (move < 0) {
                        move += 2;
                    }
                    if (move == 0 && (x_product)/110 != (cat.x_position)/110) {
                        if ((x_product)/110 > (cat.x_position)/110) {
                            cat.x_position += 110;
                        } else {
                            cat.x_position -= 110;
                        }
                    } else if (move == 1 && (y_product)/90 != (cat.y_position)/90) {
                        if ((y_product)/90 > (cat.y_position)/90) {
                            cat.y_position += 90;
                        } else {
                            cat.y_position -= 90;
                        }
                    } else if (move == 0 && (x_product)/110 == (cat.x_position)/110) {
                        if ((y_product)/90 > (cat.y_position)/90) {
                            cat.y_position += 90;
                        } else {
                            cat.y_position -= 90;
                        }
                    } else if (move == 1 && (y_product)/90 == (cat.y_position)/90) {
                        if ((x_product)/110 > (cat.x_position)/110) {
                            cat.x_position += 110;
                        } else {
                            cat.x_position -= 110;
                        }
                    }

                }

                else if (cat.existing && searchingProduct(cat.x_position, cat.y_position) == null) {
                    System.err.println("NO Products!!!!!!!!!!");
                    Random random = new Random();
                    int x = random.nextInt() % 4;
                    if (x < 0) {
                        x += 4;
                    }
                    if (x == 0) {
                        if (cat.x_position < 240) {
                            cat.x_position+=110;
                        } else {
                            cat.x_position-=110;
                        }
                    } else if (x == 1) {
                        if (cat.y_position < 150) {
                            cat.y_position+=90;
                        } else {
                            cat.y_position-=90;
                        }
                    } else if (x == 2) {
                        if (cat.x_position > 460) {
                            cat.x_position-=110;
                        } else {
                            cat.x_position+=110;
                        }
                    } else if (x == 3) {
                        if (cat.y_position > 430) {
                            cat.y_position-=90;
                        } else {
                            cat.y_position+=90;
                        }
                    }
                }

            }
        }
        if(dogs!=null) {
            for (Dog dog : dogs) {
                if (dog.existing) {
                    Random random = new Random();
                    int x = random.nextInt() % 4;
                    if (x < 0) {
                        x += 4;
                    }
                    if (x == 0) {
                        if (dog.x_position < 240) {
                            dog.x_position+=110;
                        } else {
                            dog.x_position-=110;
                        }
                    } else if (x == 1) {
                        if (dog.y_position < 150) {
                            dog.y_position+=90;
                        } else {
                            dog.y_position-=90;
                        }
                    } else if (x == 2) {
                        if (dog.x_position > 460) {
                            dog.x_position-=110;
                        } else {
                            dog.x_position+=110;
                        }
                    } else if (x == 3) {
                        if (dog.y_position > 430) {
                            dog.y_position-=90;
                        } else {
                            dog.y_position+=90;
                        }
                    }
                    if(lions!=null) {
                        for (Lion lion : lions) {

                            if (lion.x_position/110 == dog.x_position/110 && lion.y_position/90 == dog.y_position/90) {
                                System.out.println("lion{"+lion.x_position/110+","+lion.y_position/90+"}");
                                System.out.println("dog{"+dog.x_position/110+","+dog.y_position/90+"}");
                                lion.existing = false;
                                dog.existing = false;
                                break;
                            }
                        }
                    }
                    if(tigers!=null) {
                        for (Tiger tiger : tigers) {
                            if (tiger.x_position/110 == dog.x_position/110 && tiger.y_position/90 == dog.y_position/90) {
                                tiger.existing = false;
                                dog.existing = false;
                                break;
                            }
                        }
                    }
                    if(bears!=null) {
                        for (Bear bear : bears) {
                            if (bear.x_position/110 == dog.x_position/110 && bear.y_position/90 == dog.y_position/90) {
                                bear.existing = false;
                                dog.existing = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(tigers!=null){
            for(Tiger tiger : tigers){
                if(tiger.caged==false&&tiger.existing){
                    Random random = new Random();
                    int x= random.nextInt()%4;
                    if(x<0){
                        x+=4;
                    }
                    if(x==0){
                        if(tiger.x_position/110==0){
                            tiger.x_position+=110;
                        }
                        else {
                            tiger.x_position-=110;
                        }
                    }
                    else if(x==1){
                        if(tiger.y_position/90==0){
                            tiger.y_position+=90;
                        }
                        else {
                            tiger.y_position-=90;
                        }
                    }
                    else if(x==2){
                        if(tiger.x_position/110==5){
                            tiger.x_position-=110;
                        }
                        else {
                            tiger.x_position+=110;
                        }
                    }
                    else if(x==3){
                        if(tiger.y_position/90==5){
                            tiger.y_position-=90;
                        }
                        else {
                            tiger.y_position+=90;
                        }
                    }
                }
            }
        }
        if(tigers!=null){
            for(Tiger tiger1 : tigers) {
                if (tiger1.caged == false && tiger1.existing) {
                    eliminating(tiger1.x_position, tiger1.y_position);
                }
            }
        }


    }

    // showing farm
    public void showingFarm(){
        for (int i = 0; i < 8; i++) {
            if(i==0){
                System.out.print("   ");
            }
            else if(i==1){
                System.out.print("EP:");
            }
            else if(i==2){
                System.out.print("IC:");
            }
            else if(i==3){
                System.out.print("MP:");
            }
            else if(i==4){
                System.out.print("SE:");
            }
            else if(i==5){
                System.out.print("WF:");
            }
            else if(i==6){
                System.out.print("BA:");
            }
            else if(i==7){
                System.out.print("   ");
            }
            for (int j = 0; j < 8; j++) {
                if(i==0||i==7){
                    System.out.print(" * ");
                }
                else if(j==0){
                    System.out.print(" #");
                }
                else if(j==7){
                    System.out.print("  *");
                }
                else {
                    if(farm[i][j]!=0){
                        System.out.print("  "+farm[i][j]);
                    }
                    else {
                        System.out.print("  0");
                    }
                }
            }
            System.out.println();
        }
    }

    // building workshops
    public void building(String name){
        String[] workName={"EggPowderPlant","WeavingFactory","MilkPacking","Bakery","Sewing","IceCreamShop"};
        int counter=0;
        for(int i=0;i<6;i++){
            if(!workName[i].equalsIgnoreCase(name)){
                counter++;
            }
        }
        if(counter==6){
            System.err.println("Invalid WorkShop Name");
            Date date = new Date();
            String s = "Invalid WorkShop Name Entered";
            logger(date,s,false);
        }
        else {
            if(name.equalsIgnoreCase("EggPowderPlant")&&coins>=eggPowderPlant.makingPrice){
                eggPowderPlant.level=1;
                System.out.println(name.toLowerCase()+" built successfully!!");
                coins-=eggPowderPlant.makingPrice;
                Date date = new Date();
                String s = "Workshop Built";
                logger(date,s,true);
            }
            else if(name.equalsIgnoreCase("WeavingFactory")&&coins>=weavingFactory.makingPrice){
                weavingFactory.level=1;
                System.out.println(name.toLowerCase()+" built successfully!!");
                coins-=weavingFactory.makingPrice;
                Date date = new Date();
                String s = "Workshop Built";
                logger(date,s,true);
            }
            else if(name.equalsIgnoreCase("MilkPacking")&&coins>=milkPacking.makingPrice){
                milkPacking.level=1;
                System.out.println(name.toLowerCase()+" built successfully!!");
                coins-=milkPacking.makingPrice;
                Date date = new Date();
                String s = "Workshop Built";
                logger(date,s,true);
            }
            else if(name.equalsIgnoreCase("Bakery")&&coins>=bakery.makingPrice){
                bakery.level=1;
                System.out.println(name.toLowerCase()+" built successfully!!");
                coins-=bakery.makingPrice;
                Date date = new Date();
                String s = "Workshop Built";
                logger(date,s,true);
            }
            else if(name.equalsIgnoreCase("Sewing")&&coins>=sewing.makingPrice){
                sewing.level=1;
                System.out.println(name.toLowerCase()+" built successfully!!");
                coins-=sewing.makingPrice;
                Date date = new Date();
                String s = "Workshop Built";
                logger(date,s,true);
            }
            else if(name.equalsIgnoreCase("IceCreamShop")&&coins>=iceCreamShop.makingPrice){
                iceCreamShop.level=1;
                System.out.println(name.toLowerCase()+" built successfully!!");
                coins-=iceCreamShop.makingPrice;
                Date date = new Date();
                String s = "Workshop Built";
                logger(date,s,true);
            }
            else {
                System.err.println("Not Enough Coins For Building WorkShop");
                Date date = new Date();
                String s = "WorkShop Building Price is More Than Coins";
                logger(date,s,false);
            }
        }

    }

    // upgrading workshops
    public void upgrading(String name){
        if(name.equalsIgnoreCase("EggPowderPlant")){
            eggPowderPlant.level=2;
            System.out.println(name.toLowerCase()+" has been upgraded!!");
            Date date = new Date();
            String s = "Workshop Upgraded";
            logger(date,s,true);
        }
        else if(name.equalsIgnoreCase("WeavingFactory")){
            weavingFactory.level=2;
            System.out.println(name.toLowerCase()+" has been upgraded!!");
            Date date = new Date();
            String s = "Workshop Upgraded";
            logger(date,s,true);
        }
        else if(name.equalsIgnoreCase("MilkPacking")){
            milkPacking.level=2;
            System.out.println(name.toLowerCase()+" has been upgraded!!");
            Date date = new Date();
            String s = "Workshop Upgraded";
            logger(date,s,true);
        }
        else if(name.equalsIgnoreCase("Bakery")){
            bakery.level=2;
            System.out.println(name.toLowerCase()+" has been upgraded!!");
            Date date = new Date();
            String s = "Workshop Upgraded";
            logger(date,s,true);
        }
        else if(name.equalsIgnoreCase("Sewing")){
            sewing.level=2;
            System.out.println(name.toLowerCase()+" has been upgraded!!");
            Date date = new Date();
            String s = "Workshop Upgraded";
            logger(date,s,true);
        }
        else if(name.equalsIgnoreCase("IceCreamShop")){
            iceCreamShop.level=2;
            System.out.println(name.toLowerCase()+" has been upgraded!!");
            Date date = new Date();
            String s = "Workshop Upgraded";
            logger(date,s,true);
        }
    }// price okay shavad

    public void leveling(int k){
        for (Levels level:levels) {
            if(level.number==k){
                level1=level;
            }
        }
        max_eggNumber=level1.egg;
        max_featherNumber=level1.feather;
        max_milkNumber=level1.milk;

        max_powderNumber=level1.powder;
        max_clothNumber=level1.cloth;
        max_packedMilkNumber=level1.packedMilk;

        max_breadNumber=level1.bread;
        max_shirtNumber=level1.shirt;
        max_icecreamNumber=level1.iceCream;

        coins = level1.coins;

        //COPY//
         max_eggNumber2=level1.egg;
         max_featherNumber2=level1.feather;
         max_milkNumber2=level1.milk;
         max_powderNumber2=level1.powder;
         max_clothNumber2=level1.cloth;
         max_packedMilkNumber2=level1.packedMilk;
         max_breadNumber2=level1.bread;
         max_shirtNumber2=level1.shirt;
         max_icecreamNumber2=level1.iceCream;
    }

    public void showing_Info() {
        //ANIMALS
        System.out.println("Animals That Are Currently In Your Farm : ");
        if(chickens!=null) {
            for (Chicken chicken2 : chickens) {
                if(chicken2.existing){
                    System.out.println("Chicken " + chicken2.tmpNum+" "+chicken2.life+"% ["+ chicken2.x_position + "," + chicken2.y_position + "]");
                }
            }
        }
        if(turkeys!=null) {
            for (Turkey turkey2 : turkeys) {
                if(turkey2.existing){
                    System.out.println("Turkey " + turkey2.tmpNum+" "+turkey2.life+"% ["+ turkey2.x_position + "," + turkey2.y_position + "]");
                }
            }
        }
        if(buffaloes!=null) {
            for (Buffalo buffalo2 : buffaloes) {
                if(buffalo2.existing) {
                    System.out.println("Buffalo " + buffalo2.tmpNum + " " + buffalo2.life + "% [" + buffalo2.x_position + "," + buffalo2.y_position + "]");
                }
            }
        }
        if(cats!=null){
            for(Cat cat2 : cats){
                if(cat2.existing){
                    System.out.println("Cat ["+cat2.x_position+","+cat2.y_position+"]");
                }

            }
        }
        if(dogs!=null){
            for(Dog dog2 : dogs){
                if(dog2.existing){
                    System.out.println("Dog ["+dog2.x_position+","+dog2.y_position+"]");
                }
            }
        }
        if(lions!=null){
            for(Lion lion2 : lions){
                if(lion2.existing){
                    System.out.println("Lion "+lion2.tmpNum+" ["+lion2.x_position+","+lion2.y_position+"]");
                }
            }
        }
        if(bears!=null){
            for(Bear bear2 : bears){
                if(bear2.existing){
                    System.out.println("Bear "+bear2.tmpNum+" ["+bear2.x_position+","+bear2.y_position+"]");
                }
            }
        }
        if(tigers!=null){
            for(Tiger tiger2 : tigers){
                if(tiger2.existing){
                    System.out.println("Tiger "+tiger2.tmpNum+" ["+tiger2.x_position+","+tiger2.y_position+"]");
                }
            }
        }
        //PRODUCTS
        System.out.println("Products That Are Ready To Be Picked Up : ");
        if(eggs!=null) {
            for (Egg egg1 : eggs) {
               if(egg1.time_canceling!=0&&egg1.existing)
                   System.out.println("Egg " + "[" + egg1.x_position + "," + egg1.y_position + "]");
            }
        }
        if(feathers!=null) {
            for (Feather feather1 : feathers) {
                if(feather1.time_canceling!=0&&feather1.existing)
                    System.out.println("Feather " + "[" + feather1.x_position + "," + feather1.y_position + "]");
            }
        }
        if(milks!=null) {
            for (Milk milk1 : milks) {
                if(milk1.time_canceling!=0&&milk1.existing)
                    System.out.println("Milk " + "[" + milk1.x_position + "," + milk1.y_position + "]");
            }
        }
        if(powders!=null) {
            for (Powder powder1 : powders) {
                if(powder1.time_canceling!=0&&powder1.existing)
                    System.out.println("Powder " + "[" + powder1.x_position + "," + powder1.y_position + "]");
            }
        }
        if(cloths!=null) {
            for (Cloth cloth1 : cloths) {
                if(cloth1.time_canceling!=0&&cloth1.existing)
                    System.out.println("Cloth " + "[" + cloth1.x_position + "," + cloth1.y_position + "]");
            }
        }
        if(packedMilks!=null){
            for (PackedMilk packedMilk1 : packedMilks) {
                if(packedMilk1.time_canceling!=0&&packedMilk1.existing)
                    System.out.println("PackedMilk " + "[" + packedMilk1.x_position + "," + packedMilk1.y_position + "]");
            }
        }
        if(breads!=null) {
            for (Bread bread1 : breads) {
                if(bread1.time_canceling!=0&&bread1.existing)
                    System.out.println("Bread " + "[" + bread1.x_position + "," + bread1.y_position + "]");
            }
        }
        if(shirts!=null) {
            for (Shirt shirt1 : shirts) {
                if(shirt1.time_canceling!=0&&shirt1.existing)
                    System.out.println("Shirt " + "[" + shirt1.x_position + "," + shirt1.y_position + "]");
            }
        }
        if(icecreams!=null) {
            for (Icecream icecream1 : icecreams) {
                if(icecream1.time_canceling!=0&&icecream1.existing)
                    System.out.println("Icecream " + "[" + icecream1.x_position + "," + icecream1.y_position + "]");
            }
        }
        System.out.println("Remaining Tasks : ");
        if(max_eggNumber2!=0) System.out.println("Egg: "+ (max_eggNumber2-max_eggNumber) +"/"+max_eggNumber2);
        if(max_featherNumber2!=0) System.out.println("Feather: "+ (max_featherNumber2-max_featherNumber) +"/"+max_featherNumber2);
        if(max_milkNumber2!=0) System.out.println("Milk: "+ (max_milkNumber2-max_milkNumber) +"/"+max_milkNumber2);
        if(max_powderNumber2!=0) System.out.println("Powder: "+ (max_powderNumber2-max_powderNumber) +"/"+max_powderNumber2);
        if(max_clothNumber2!=0) System.out.println("Cloth: "+ (max_clothNumber2-max_clothNumber) +"/"+max_clothNumber2);
        if(max_packedMilkNumber2!=0) System.out.println("PackedMilk: "+ (max_packedMilkNumber2-max_packedMilkNumber) +"/"+max_packedMilkNumber2);
        if(max_breadNumber2!=0) System.out.println("Bread: "+ (max_breadNumber2-max_breadNumber) +"/"+max_breadNumber2);
        if(max_shirtNumber2!=0) System.out.println("Shirt: "+ (max_shirtNumber2-max_shirtNumber) +"/"+max_shirtNumber2);
        if(max_icecreamNumber2!=0) System.out.println("IceCream: "+ (max_icecreamNumber2-max_icecreamNumber) +"/"+max_icecreamNumber2);
        System.out.println(" ");
        System.out.println("Remaining Coins : "+coins);
        for (int i = 0; i < 26; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    public void attacking(Levels level1,int turnings){
        if(level1!=null){
            if(level1.attackerName.equalsIgnoreCase("lion")){
                if(turnings%level1.timeAttacking==5) {
                    Lion newLion = new Lion();
                    allLionsNum++;
                    newLion.tmpNum=allLionsNum;
                    if(lions==null) lions = new ArrayList<>();
                    lions.add(newLion);
                    if(attackerAnimals==null) attackerAnimals= new ArrayList<>();
                    attackerAnimals.add(newLion);
                }
            }
            if(level1.attackerName.equalsIgnoreCase("bear")){
                if(turnings%level1.timeAttacking==5) {
                    Bear newBear = new Bear();
                    allBearsNum++;
                    newBear.tmpNum=allBearsNum;
                    if(bears==null) bears = new ArrayList<>();
                    bears.add(newBear);
                    if(attackerAnimals==null) attackerAnimals= new ArrayList<>();
                    attackerAnimals.add(newBear);
                }
            }
            if(level1.attackerName.equalsIgnoreCase("tiger")){
                if(turnings%level1.timeAttacking==5) {
                    Tiger newTiger = new Tiger();
                    allTigersNum++;
                    newTiger.tmpNum=allTigersNum;
                    if(tigers==null) tigers = new ArrayList<>();
                    tigers.add(newTiger);
                    if(attackerAnimals==null) attackerAnimals= new ArrayList<>();
                    attackerAnimals.add(newTiger);
                }
            }
        }
    }

    public void logger(Date date,String s,boolean check){
        String monthName="";
        if(date.getMonth()==12) monthName="January";
        else if (date.getMonth()==1) monthName="February";
        else if (date.getMonth()==2) monthName="March";
        else if (date.getMonth()==3) monthName="April";
        else if (date.getMonth()==4) monthName="May";
        else if (date.getMonth()==5) monthName="June";
        else if (date.getMonth()==6) monthName="July";
        else if (date.getMonth()==7) monthName="August";
        else if (date.getMonth()==9) monthName="September";
        else if (date.getMonth()==10) monthName="October";
        else if (date.getMonth()==11) monthName="November";
        else if (date.getMonth()==12) monthName="December";
        String tarikh =String.valueOf(date.getDay()+13) + "/" +monthName+ "/" + String.valueOf(date.getYear()+1900);
        String zaman = String.valueOf(date.getHours()) + ":" +String.valueOf(date.getMinutes())+ ":" + String.valueOf(date.getSeconds());
        try {
            File file1 = new File(".\\log.txt");
            FileWriter myWriter = new FileWriter(file1,true);
            BufferedWriter buffy = new BufferedWriter(myWriter);
            if(check) buffy.write("[Info] , "+tarikh+" - "+zaman+" , "+s);
            else buffy.write("[Error] , "+tarikh+" - "+zaman+" , "+s);
            buffy.newLine();
            buffy.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR");
        }
    }

    public void drawAnimal(Stage primaryStage,Pane root1){
        if(chickens!=null){
            int count=0;
            for(Chicken gchicken : chickens){
                if(gchicken.existing) {
                    try {
                        drawImage(primaryStage,110+count+gchicken.x_position,60+count+gchicken.y_position,gchicken.width,gchicken.height,".\\chicken.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(turkeys!=null){
            int count=0;
            for(Turkey turkey : turkeys){
                if(turkey.existing) {
                    try {
                        drawImage(primaryStage,130+count+turkey.x_position,60+count+turkey.y_position,turkey.width,turkey.height,".\\turkey1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(buffaloes!=null){
            int count=0;
            for(Buffalo buffalo : buffaloes){
                if(buffalo.existing) {
                    try {
                        drawImage(primaryStage,130+count+buffalo.x_position,60+count+buffalo.y_position,buffalo.width,buffalo.height,".\\buffalo1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(lions!=null){
            int count=0;
            for(Lion lion : lions){
                if(lion.existing) {
                    try {
                        drawImage(primaryStage,130+count+lion.x_position,60+count+lion.y_position,lion.width,lion.height,".\\lion1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(lion.cageInputNum!=0){
                        String s=".\\cage3.png";
                        if(lion.cageInputNum==1){
                            s=".\\cage1.png";
                        }
                        else if(lion.cageInputNum==2){
                            s=".\\cage2.png";
                        }
                        else if(lion.cageInputNum==3){
                            s=".\\cage3.png";
                            lion.caged=true;
                        }
                        try {
                            drawImage(primaryStage,130+count+lion.x_position,60+count+lion.y_position,lion.width,lion.height,s,root1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    count+=1;
                }
            }
        }
        if(bears!=null){
            int count=0;
            for(Bear bear : bears){
                if(bear.existing) {
                    try {
                        drawImage(primaryStage,130+count+bear.x_position,60+count+bear.y_position,bear.width,bear.height,".\\bear1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(bear.cageInputNum!=0){
                        String s=".\\cage3.png";
                        if(bear.cageInputNum==1){
                            s=".\\cage1.png";
                        }
                        else if(bear.cageInputNum==2){
                            s=".\\cage2.png";
                        }
                        else if(bear.cageInputNum==3){
                            s=".\\cage3.png";
                        }
                        else if (bear.cageInputNum==4){
                            s=".\\cage4.png";
                            bear.caged=true;
                        }
                        try {
                            drawImage(primaryStage,130+count+bear.x_position,60+count+bear.y_position,bear.width,bear.height,s,root1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    count+=1;
                }
            }
        }
        if(tigers!=null){
            int count=0;
            for(Tiger tiger : tigers){
                if(tiger.existing) {
                    try {
                        drawImage(primaryStage,130+count+tiger.x_position,60+count+tiger.y_position,tiger.width,tiger.height,".\\tiger1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(tiger.cageInputNum!=0){
                        String s=".\\cage3.png";
                        if(tiger.cageInputNum==1){
                            s=".\\cage1.png";
                        }
                        else if(tiger.cageInputNum==2){
                            s=".\\cage2.png";
                        }
                        else if(tiger.cageInputNum==3){
                            s=".\\cage3.png";
                        }
                        else if (tiger.cageInputNum==4){
                            s=".\\cage4.png";
                            tiger.caged=true;
                        }
                        try {
                            drawImage(primaryStage,130+count+tiger.x_position,60+count+tiger.y_position,tiger.width,tiger.height,s,root1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    count+=1;
                }
            }
        }
        if(cats!=null){
            int count=0;
            for(Cat cat : cats){
                if(cat.existing) {
                    try {
                        drawImage(primaryStage,130+count+cat.x_position,60+count+cat.y_position,cat.width,cat.height,".\\cat1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(dogs!=null){
            int count=0;
            for(Dog dog : dogs){
                if(dog.existing) {
                    try {
                        drawImage(primaryStage,130+count+dog.x_position,60+count+dog.y_position,dog.width,dog.height,".\\dog1.png",root1);
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                count+=2;
            }
        }
    }

    public void drawGrass(Stage primaryStage,Pane root1){
      for(int i=0;i<6;i++){
          for(int j=0;j<6;j++){
              if(farm[i][j]!=0){
                  try {
                      drawImage(primaryStage,110+(i*110),60+(j*85),110,85,".\\grass1.png",root1);
                  } catch (FileNotFoundException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
      drawAnimal(primaryStage,root1);
      drawProduct(primaryStage,root1);



    }

    public void drawProduct(Stage primaryStage,Pane root1){
        if(eggs!=null){
            int count=0;
            for(Egg egg : eggs){
                if(egg.existing){
                    try {
                        drawImage(primaryStage,130+count+egg.x_position+5,60+count+egg.y_position,20,30,".\\egg.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(feathers!=null){
            int count=0;
            for(Feather feather : feathers){
                if(feather.existing){
                    try {
                        drawImage(primaryStage,130+count+feather.x_position+5,60+count+feather.y_position,20,30,".\\feather1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(milks!=null){
            int count=0;
            for(Milk milk : milks){
                if(milk.existing){
                    try {
                        drawImage(primaryStage,130+count+milk.x_position+5,60+count+milk.y_position,30,40,".\\milk1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(powders!=null){
            int count=0;
            for(Powder powder : powders){
                if(powder.existing){

                    try {
                        drawImage(primaryStage,130+powder.x_position+count+5,60+powder.y_position+count,50,50,".\\powder1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(cloths!=null){
            int count=0;
            for(Cloth cloth : cloths){
                if(cloth.existing){
                    try {
                        drawImage(primaryStage,130+cloth.x_position+count+5,60+90+cloth.y_position+count,40,40,".\\cloth1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(packedMilks!=null){
            int count=0;
            for(PackedMilk packedMilk : packedMilks){
                if(packedMilk.existing){
                    try {
                        drawImage(primaryStage,130+packedMilk.x_position+count+5,60+90*2+count+packedMilk.y_position,30,40,".\\packedMilk1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(breads!=null){
            int count=0;
            for(Bread bread : breads){
                if(bread.existing){
                    try {
                        drawImage(primaryStage,130+bread.x_position+count+5,60+90*3+count+bread.y_position,50,40,".\\bread1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(shirts!=null){
            int count=0;
            for(Shirt shirt : shirts){
                if(shirt.existing){
                    try {
                        drawImage(primaryStage,130+shirt.x_position+count+5,60+90*4+count+shirt.y_position,40,30,".\\shirt1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
        if(icecreams!=null){
            int count=0;
            for(Icecream icecream : icecreams){
                if(icecream.existing){
                    try {
                        drawImage(primaryStage,130+count+icecream.x_position+5,60+90*5+count+icecream.y_position,30,45,".\\icecream1.png",root1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    count+=2;
                }
            }
        }
    }
    public Label abel0 = new Label();
    public Label abel1 = new Label();
    public Label abel2 = new Label();
    public Label abel3 = new Label();
    public Label abel4 = new Label();
    public Label abel5 = new Label();
    public Label abel6 = new Label();
    public Label abel7 = new Label();
    public Label abel8 = new Label();
    public void addTasks(int level,Pane root){
        int x=815;
        int y=445;
        int step = 25;
        ArrayList<Integer> task=new ArrayList<>();
        for (Levels level2:levels) {
            if(level2.number==level){
                level1=level2;
            }
        }
        if(level1.egg!=0){
            abel0.setText("Egg : "+(max_eggNumber2-max_eggNumber)+"/"+level1.egg);
            abel0.setLayoutX(x);
            abel0.setLayoutY(y);
            abel0.setFont(Font.font("Comic Sans MS", 19));
           // root.getChildren().add(label0);
            y+=step;
        }
        if(level1.feather!=0){
            abel1.setText("Feather : "+(max_featherNumber2-max_featherNumber)+"/"+level1.feather);
            abel1.setLayoutX(x);
            abel1.setLayoutY(y);
            abel1.setFont(Font.font("Comic Sans MS",19));
            ///root.getChildren().add(label1);
            y+=step;
        }
        if(level1.milk!=0){
            abel2.setText("Milk : "+(max_milkNumber2-max_milkNumber)+"/"+level1.milk);
            abel2.setLayoutX(x);
            abel2.setLayoutY(y);
            abel2.setFont(Font.font("Comic Sans MS", 19));
            //root.getChildren().add(label2);
            y+=step;
        }
        if(level1.powder!=0){
            abel3.setText("Powder : "+(max_powderNumber2-max_powderNumber)+"/"+level1.powder);
            abel3.setLayoutX(x);
            abel3.setLayoutY(y);
            abel3.setFont(Font.font("Comic Sans MS", 19));
            //root.getChildren().add(label3);
            y+=step;
        }
        if(level1.cloth!=0){
            abel4.setText("Cloth : "+(max_clothNumber2-max_clothNumber)+"/"+level1.cloth);
            abel4.setLayoutX(x);
            abel4.setLayoutY(y);
            abel4.setFont(Font.font("Comic Sans MS", 19));
            //root.getChildren().add(label4);
            y+=step;
        }
        if(level1.packedMilk!=0){
            abel5.setText("PackedMilk : "+(max_packedMilkNumber2-max_packedMilkNumber)+"/"+level1.packedMilk);
            abel5.setLayoutX(x);
            abel5.setLayoutY(y);
            abel5.setFont(Font.font("Comic Sans MS", 19));
            //root.getChildren().add(label5);
            y+=step;
        }
        if(level1.bread!=0){
            abel6.setText("Bread : "+(max_breadNumber2-max_breadNumber)+"/"+level1.bread);
            abel6.setLayoutX(x);
            abel6.setLayoutY(y);
            abel6.setFont(Font.font("Comic Sans MS", 19));
            //root.getChildren().add(label6);
            y+=step;
        }
        if(level1.shirt!=0){
            abel7.setText("Shirt : "+(max_shirtNumber2-max_shirtNumber)+"/"+level1.shirt);
            abel7.setLayoutX(x);
            abel7.setLayoutY(y);
            abel7.setFont(Font.font("Comic Sans MS", 19));
            //root.getChildren().add(label7);
            y+=step;
        }
        if(level1.iceCream!=0){
            abel8.setText("IceCream : "+(max_icecreamNumber2-max_icecreamNumber)+"/"+level1.iceCream);
            abel8.setLayoutX(x);
            abel8.setLayoutY(y);
            abel8.setFont(Font.font("Comic Sans MS", 19));
            //root.getChildren().add(label8);
        }


    }
    public void reseting(){
        eggs=new ArrayList<>();
        feathers=new ArrayList<>();
        milks=new ArrayList<>();
        powders=new ArrayList<>();
        cloths=new ArrayList<>();
        packedMilks=new ArrayList<>();
        breads=new ArrayList<>();
        shirts=new ArrayList<>();
        icecreams=new ArrayList<>();
        chickens=new ArrayList<>();
        turkeys=new ArrayList<>();
        buffaloes=new ArrayList<>();
        lions=new ArrayList<>();
        bears=new ArrayList<>();
        tigers=new ArrayList<>();
        dogs=new ArrayList<>();
        cats=new ArrayList<>();
        /////////////////////////////////
        producerAnimals=new ArrayList<>();
        attackerAnimals=new ArrayList<>();
        /////////////////////////////////////
        eggPowderPlant=new EggPowderPlant();
        weavingFactory=new WeavingFactory();
        milkPacking=new MilkPacking();
        bakery=new Bakery();
        sewing=new Sewing();
        iceCreamShop=new IceCreamShop();
        /////////////////////////////////////
        farm = new int[6][6];
        coins=0;
        coin1=0;
        well=5;
        truck = new Truck();
        stock = 0;
        ////////////////////////////////////////////////////////////////////////////////
        egg1 = new Egg();
        feather1 = new Feather();
        milk1 = new Milk();
        powder1 = new Powder();
        cloth1= new Cloth();
        packedMilk1= new PackedMilk();
        bread1= new Bread();
        shirt1 = new Shirt();
        icecream1 = new Icecream();
        chicken1= new Chicken();
        turkey1 = new Turkey();
        buffalo1 = new Buffalo();
        lion1 = new Lion();
        bear1 = new Bear();
        tiger1= new Tiger();
        dog1= new Dog();
        cat1 = new Cat();
        level1=new Levels();
//////////////////////////////////////////////////////////////////////////////////
        wellingBool=false;
        wellingTime=0;
        truckBool=false;
        truckTime=0;
        ///

        workEggPowderPlant=false;
        eggPowderPlantTime=0;
        workWeavingFactory=false;
        weavingFactoryTime=0;
        workMilkPacking=false;
        milkPackingTime=0;
        workBakery=false;
        bakeryTime=0;
        workSewing=false;
        sewingTime=0;
        workIceCreamShop=false;
        iceCreamShopTime=0;
        ///////////////////
        win=false;
        //////////////////
        max_eggNumber2=0;
        max_featherNumber2=0;
        max_milkNumber2=0;
        max_powderNumber2=0;
        max_clothNumber2=0;
        max_packedMilkNumber2=0;
        max_breadNumber2=0;
        max_shirtNumber2=0;
        max_icecreamNumber2=0;
        ///////////////////
        allChickensNum=0;
        allTurkeysNum=0;
        allBuffaloesNum=0;
        allLionsNum=0;
        allBearsNum=0;
        allTigersNum=0;
        //////////////////////
        max_eggNumber=0;
        max_featherNumber=0;
        max_milkNumber=0;
        max_powderNumber=0;
        max_clothNumber=0;
        max_packedMilkNumber=0;
        max_breadNumber=0;
        max_shirtNumber=0;
        max_icecreamNumber=0;
        //////////////////
        eggNumber=0;
        featherNumber=0;
        milkNumber=0;
        powderNumber=0;
        clothNumber=0;
        packedMilkNumber=0;
        breadNumber=0;
        shirtNumber=0;
        icecreamNumber=0;
        ////////////////
        lionNumber=0;
        bearNumber=0;
        tigerNumber=0;

    }

    //logger
    //file missions
    //khar karia
}
