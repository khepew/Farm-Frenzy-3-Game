import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sun.javafx.iio.gif.GIFImageLoader2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InputProcessor {
    final static int BONUS_LEVEL_1=20;
    final static int BONUS_LEVEL_2=50;
    final static int BONUS_LEVEL_3=80;
    final static int BONUS_LEVEL_4=110;
    final static int BONUS_LEVEL_5=150;
    private Manager manager = new Manager();
    private Scanner scanner = new Scanner(System.in);
    File file = new File(".\\Users.txt");
    File file1 = new File(".\\Levels.txt");
    File file2 = new File(".\\log.txt");
    private TableView<Logger> table = new TableView<>();

    public InputProcessor(Manager manager) { this.manager = manager; }
    /// buy animal
    private void buyingAnimal(String[] split){
       // manager.buying_animal(split[1]);
    }
    /// pick up products
    private void pickingUp(String[] split){
        manager.pickup_product(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
    }

    private void working(String[] split,int turnings){
        manager.working_workshop(split[1],turnings);
    }

    private void caging(String[] split){
        manager.caging_animal(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
    }

    private void planting(String[] split){
       // manager.gardening(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
    }

    private void truckLoading(String[] split){
        manager.truck_load(split[2]);
    }

    private void truckUnloading(String[] split){
        manager.truck_unload(split[2]);
    }

    private void readingFile(){
        try{
            FileReader freader = new FileReader(file);
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(freader);
            manager.users=gson.fromJson(reader, new TypeToken<List<Users>>(){}.getType());
            FileReader freader1 = new FileReader(file1);
            Gson gson1 = new Gson();
            JsonReader reader1 = new JsonReader(freader1);
            manager.levels=gson1.fromJson(reader1, new TypeToken<List<Levels>>(){}.getType());
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    private void savingFile(){
        try {
            FileWriter fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            String s = gson.toJson(manager.users);
            fileWriter.write(s);
            fileWriter.close();
            FileWriter fileWriter1 = new FileWriter(file1);
            Gson gson1 = new Gson();
            String s1 = gson1.toJson(manager.levels);
            fileWriter1.write(s1);
            fileWriter1.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    //      Finding User By UserName
    private Users findingUserByUserName(String userName){
        for (Users user1:manager.users) {
            if(user1.userName.equals(userName)){
                return user1;
            }
        }
        return null;
    }

    private void build(String[] split){
        manager.building(split[1]);
    }

    private void upgrade(String[] split){
        manager.upgrading(split[1]);
    }

    private void showingInfo(int turnings) {
        System.out.println("The Time Passed Since You Started The Game : "+turnings);
        manager.showingFarm();
        manager.showing_Info();
    }

    private Levels findLevelByNum(int a){
        if(manager.levels!=null){
            for(Levels level : manager.levels){
                if(level.number==a) return level;
            }
        }
        return null;
    }

    public void drawImage(Stage stage,int x,int y,int w,int h,String path,Pane root1) throws FileNotFoundException {
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

    public void drawRect(Pane root,int x,int y,int heigth,int width,Color color){
        Rectangle rect=new Rectangle(); //instantiating Rectangle
        rect.setX(x); //setting the X coordinate of upper left //corner of rectangle
        rect.setY(y); //setting the Y coordinate of upper left //corner of rectangle
        rect.setWidth(width); //setting the width of rectangle
        rect.setHeight(heigth);// setting the height of rectangle
        rect.setArcHeight(25);
        rect.setArcWidth(25);
        rect.setFill(color);
        root.getChildren().addAll(rect); //adding rectangle to the //group
    }

    public void menu(Stage primaryStage){
        readingFile();
        Pane root = new Pane();
        primaryStage.setTitle("MENU");
        Button button = new Button();
        button.setText("Sign Up");
        button.setFont(Font.font("Comic Sans MS",29));
        button.setTextFill(Color.CYAN);
        button.setOnAction(e -> {
            this.signUp(primaryStage);
        });
        button.setLayoutX(300);
        button.setLayoutY(175);
        button.setPrefSize(210,55);
        button.setShape(new Rectangle());
        Button button1 = new Button();
        button1.setText("Log In");
        button1.setFont(Font.font("Comic Sans MS",29));
        button1.setTextFill(Color.CYAN);
        button1.setOnAction(e -> {
            this.logIn(primaryStage);
        });
        button1.setLayoutX(300);
        button1.setLayoutY(270);
        button1.setPrefSize(210,55);
        button1.setShape(new Rectangle());
        Button button2=new Button();
        button2.setText("Exit");
        button2.setFont(Font.font("Comic Sans MS",29));
        button2.setTextFill(Color.CYAN);
        button2.setOnAction(e -> {
            this.exit(primaryStage);
        });
        button2.setLayoutX(300);
        button2.setLayoutY(453);
        button2.setPrefSize(210,55);
        button2.setShape(new Rectangle());
        Button button3 = new Button();
        button3.setText("Logger");
        button3.setFont(Font.font("Comic Sans MS",29));
        button3.setTextFill(Color.CYAN);
        button3.setOnAction(e -> {
            this.logger(primaryStage,root);

        });
        button3.setLayoutX(300);
        button3.setLayoutY(362);
        button3.setPrefSize(210,55);
        button3.setShape(new Rectangle());
        try {
            drawImage(primaryStage,0,0,800,800,".\\menubg2.jpg",root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root.getChildren().addAll(button,button1,button2,button3);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setX(400);
        primaryStage.setY(200);
        primaryStage.show();
        savingFile();
    }

    public void signUp(Stage primaryStage){
        readingFile();
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(new Scene(new Pane(),800,500));
        Pane root1 = new Pane();
        if(manager.users==null){
            manager.users=new ArrayList<>();
        }
        Label user_id=new Label("Username : ");
        Label password = new Label("Password : ");
        user_id.setFont(Font.font("Comic Sans MS",18));
        password.setFont(Font.font("Comic Sans MS",18));
        user_id.setTextFill(Color.WHITESMOKE);
        password.setTextFill(Color.WHITESMOKE);
        user_id.setLayoutX(225);
        user_id.setLayoutY(220);
        password.setLayoutX(230);
        password.setLayoutY(253);


        TextField tf1=new TextField();
        PasswordField tf2=new PasswordField();
        tf1.setLayoutX(323);
        tf1.setLayoutY(219);
        tf2.setLayoutX(323);
        tf2.setLayoutY(254);



        Button button = new Button();
        button.setText("Enter");
        button.setLayoutX(370);
        button.setLayoutY(290);
        button.setFont(Font.font("Comic Sans MS",15));
        button.setOnAction(e -> {
            String userName=tf1.getText(),pass=tf2.getText();
            System.out.println(userName);
            System.out.println("User Name :");;
            Users user1=findingUserByUserName(userName);
            if (user1!=null){
                System.err.println("UserName Already Exists !");
                Date date = new Date();
                String s = "UserName Has been Entered Before!";
                manager.logger(date,s,false);
            }
            else{
                Date date = new Date();
                String s = "UserName Entered correctly";
                manager.logger(date,s,true);
                System.out.println("UserName Entered correctly");
                System.out.println("Password :");
                user1=new Users(userName,pass);
                if(manager.users==null){
                    manager.users=new ArrayList<>();
                }
                System.out.println("Account Created!");
                date = new Date();
                s = "Account Created";
                manager.users.add(user1);
                manager.logger(date,s,true);
                manager.leveling(1);
                System.out.println("Your Level:"+user1.levelNum);
                try {
                    this.start1(primaryStage,user1,1);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            try {
                FileWriter myWriter = new FileWriter(file2,true);
                BufferedWriter buffy = new BufferedWriter(myWriter);
                buffy.write(userName);
                buffy.newLine();
                buffy.close();
            }
            catch (Exception e1) {
                e1.printStackTrace();
                System.err.println("ERROR");
            }

            savingFile();
        });
        Button button1 = new Button();
        button1.setText("Back");
        button1.setLayoutX(370);
        button1.setLayoutY(325);
        button1.setTextFill(Color.CRIMSON);
        button1.setFont(Font.font("Comic Sans MS",15));
        button1.setOnAction(e -> {
           menu(primaryStage);
        });
        FileInputStream input = null;
        try {
            input = new FileInputStream("userName&pass.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // create a image
        Image image = new Image(input);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);
        root1.setBackground(background);
        root1.getChildren().addAll(button,user_id,password,tf2,tf1,button1);

        Scene scene=new Scene(root1,800,500);
        primaryStage.setScene(scene);
    }

    public void logIn(Stage primaryStage){
        readingFile();
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(new Pane(),800,500));
        Pane root1 = new Pane();

        if(manager.users==null){
            manager.users=new ArrayList<>();
        }

        Label user_id=new Label("Username : ");
        Label password = new Label("Password : ");
        user_id.setFont(Font.font("Comic Sans MS",18));
        password.setFont(Font.font("Comic Sans MS",18));
        user_id.setTextFill(Color.WHITESMOKE);
        password.setTextFill(Color.WHITESMOKE);
        user_id.setLayoutX(225);
        user_id.setLayoutY(220);
        password.setLayoutX(230);
        password.setLayoutY(253);

        TextField tf1=new TextField();
        PasswordField tf2=new PasswordField();
        tf1.setLayoutX(323);
        tf1.setLayoutY(219);
        tf2.setLayoutX(323);
        tf2.setLayoutY(254);

        Button button = new Button();
        button.setText("Enter");
        button.setLayoutX(370);
        button.setLayoutY(290);
        button.setFont(Font.font("Comic Sans MS",15));
        button.setOnAction(e -> {
            String userName=tf1.getText(),pass=tf2.getText();
            Users user1=findingUserByUserName(userName);
            if (user1==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("This Username doesn't Exist!!!");
                alert.showAndWait();
            }
            else{
                boolean newFlag = false;
                for(Users newUser : manager.users){
                    if(newUser.pass.equals(pass)&&newUser.userName.equals(userName)){
                        showLevel(newUser,primaryStage);
                        Date date = new Date();
                        String s = "UserName&Password Entered correctly";
                        manager.logger(date,s,true);
                        newFlag = true;
                    }
                }
                if(!newFlag){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Password is Wrong!!!");
                    alert.showAndWait();
                }
            }
            try {
                FileWriter myWriter = new FileWriter(file2,true);
                BufferedWriter buffy = new BufferedWriter(myWriter);
                buffy.write(userName);
                buffy.newLine();
                buffy.close();
            }
            catch (Exception e1) {
                e1.printStackTrace();
                System.err.println("ERROR");
            }
            savingFile();
        });
        //
        Button button1 = new Button();
        button1.setText("Back");
        button1.setTextFill(Color.CRIMSON);
        button1.setFont(Font.font("Comic Sans MS",15));
        button1.setLayoutX(370);
        button1.setLayoutY(325);
        button1.setTextFill(Color.CRIMSON);
        button1.setFont(Font.font("Comic Sans MS",15));
        button1.setOnAction(e -> {
            menu(primaryStage);
        });
        FileInputStream input = null;
        try {
            input = new FileInputStream("userName&pass.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // create a image
        Image image = new Image(input);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);
        root1.setBackground(background);
        root1.getChildren().addAll(button,user_id,password,tf2,tf1,button1);

        Scene scene=new Scene(root1,800,500);
        primaryStage.setScene(scene);
    }

    public void logger(Stage primaryStage,Pane root){
        try {
            Button backButton = new Button();
            backButton.setText("Back");
            backButton.setFont(Font.font("Comic Sans MS",20));
            backButton.setOnAction(event -> {
                menu(primaryStage);
            });
            Label label = new Label("");
            Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
            label.setFont(font);
            File myObj = new File("log.txt");
            Scanner myReader = new Scanner(myObj);
            String log="";
            while (myReader.hasNextLine()) {
                log = log+myReader.nextLine();
                Logger logger=new Logger(log);
                manager.loggers.add(logger);
                log="";
            }
            TableColumn<Logger,String> name = new TableColumn<>("Logger");
            name.setCellValueFactory(new PropertyValueFactory<>("log"));
            ObservableList<Logger> list = FXCollections.observableArrayList();
            table.setItems(list);
            table.getColumns().add(name);
            table.setLayoutX(0);
            table.setLayoutY(0);
            table.setPrefSize(500,250);
            for (Logger logger : manager.loggers) {
                table.getItems().add(logger);
            }
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(50, 50, 0, 50));
            vbox.getChildren().addAll(label, table,backButton);
            Scene scene = new Scene(new Group(vbox), 600, 500);
            primaryStage.setTitle("Logger History");
            primaryStage.setScene(scene);
            primaryStage.show();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void exit(Stage primaryStage){
        primaryStage.close();
    }

    private int counter = 0;
    private Label label1 = new Label(manager.well+"/5");
    private Label labelCoins = new Label(manager.coins+"");
    private Label storage = new Label("STORAGE : "+manager.stock+" / 30");
    private Label userLevelShow = new Label();
    AtomicInteger turnings= new AtomicInteger();

    public void start1(Stage primaryStage,Users user,int levelNum) throws Exception{
        turnings.set(0);
        int bonus=0;
        if(levelNum==2){
            bonus=user.remainingCoins2;
        }
        else if(levelNum==3){
            bonus=user.remainingCoins3;
        }
        else if(levelNum==4){
            bonus=user.remainingCoins4;
        }
        else if(levelNum==5){
            bonus=user.remainingCoins5;
        }
        manager.well=5;
        manager.leveling(user.levelNum);
        manager.reseting();
        counter=0;
        manager.abel0.setText("");
        manager.abel1.setText("");
        manager.abel2.setText("");
        manager.abel3.setText("");
        manager.abel4.setText("");
        manager.abel5.setText("");
        manager.abel6.setText("");
        manager.abel7.setText("");
        manager.abel8.setText("");
        Pane root1 = new Pane();
        manager.leveling(user.levelNum);
        manager.reseting();
        try {
            drawImage(primaryStage,780,350,210,350,".\\tasks2.png",root1);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        manager.addTasks(levelNum,root1);
        System.out.println(levelNum);

        manager.well=5;
        counter=0;
        manager.leveling(levelNum);
        Label label =  new Label();
        manager.coins+=bonus;
        labelCoins = new Label(manager.coins+"");
        Scene scene=new Scene(root1,1000,704);
        userLevelShow.setText("Level : "+levelNum);
        int xCoins = 94;
        int yCoins=592;
        labelCoins.relocate(xCoins,yCoins);
        labelCoins.setFont(new Font(24));
        labelCoins.setTextFill(Color.YELLOW);
        primaryStage.setTitle("Farm Frenzy III");
        FileInputStream input=new FileInputStream(".\\chickenBuy.png");
        Image image = new Image(input);
        ImageView img=new ImageView(image);
        Button chicken = new Button("",img);
        chicken.setLayoutX(130);
        chicken.setLayoutY(0);
        chicken.setPrefSize(45,45);
        chicken.setOnAction(e -> {
            manager.buying_animal("Chicken",primaryStage,root1);
            labelCoins.setText(manager.coins+"");
        });
        FileInputStream input1=new FileInputStream(".\\turkeyBuy.png");
        Image image1 = new Image(input1);
        ImageView img1=new ImageView(image1);
        Button turkey = new Button("",img1);
        turkey.setLayoutX(200);
        turkey.setLayoutY(0);
        turkey.setPrefSize(45,45);
        turkey.setOnAction(e -> {
            manager.buying_animal("Turkey",primaryStage,root1);
            labelCoins.setText(manager.coins+"");
        });
        FileInputStream input2=new FileInputStream(".\\buffaloBuy.png");
        Image image2 = new Image(input2);
        ImageView img2=new ImageView(image2);
        Button buffalo = new Button("",img2);
        buffalo.setLayoutX(270);
        buffalo.setLayoutY(0);
        buffalo.setPrefSize(45,45);
        buffalo.setOnAction(e -> {
            manager.buying_animal("Buffalo",primaryStage,root1);
            labelCoins.setText(manager.coins+"");
        });
        FileInputStream input3=new FileInputStream(".\\dogBuy.png");
        Image image3 = new Image(input3);
        ImageView img3=new ImageView(image3);
        Button dog = new Button("",img3);
        dog.setLayoutX(340);
        dog.setLayoutY(0);
        dog.setPrefSize(45,45);
        dog.setOnAction(e -> {
            manager.buying_animal("Dog",primaryStage,root1);
            labelCoins.setText(manager.coins+"");
        });
        FileInputStream input4=new FileInputStream(".\\catBuy.png");
        Image image4 = new Image(input4);
        ImageView img4=new ImageView(image4);
        Button cat = new Button("",img4);
        cat.setLayoutX(410);
        cat.setLayoutY(0);
        cat.setPrefSize(45,45);
        cat.setOnAction(e -> {
            manager.buying_animal("Cat",primaryStage,root1);
            labelCoins.setText(manager.coins+"");
        });
        ////////////////////////////***WORKSHOPSSSSSSSSSSSSSS***/////////////////////////
        FileInputStream input55=new FileInputStream(".\\buildep.png");
        Image image55 = new Image(input55);
        ImageView img55=new ImageView(image55);
        Button buildEp=new Button("",img55);
        buildEp.setLayoutX(5);
        buildEp.setLayoutY(63);
        buildEp.setPrefSize(80,70);
        buildEp.setOnAction(s -> {
            manager.building("EggPowderPlant");
            FileInputStream input5= null;
            if(manager.eggPowderPlant.level==1){
                try {
                    input5 = new FileInputStream(".\\EP2.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image5 = new Image(input5);
                ImageView img5=new ImageView(image5);
                Button ep = new Button("",img5);
                ep.setLayoutX(5);
                ep.setLayoutY(63);
                ep.setPrefSize(80,70);
                ep.setOnAction(e -> {
                    manager.working_workshop("EggPowderPlant",turnings.get());
                });
                FileInputStream input16= null;
                try {
                    input16 = new FileInputStream(".\\upgradeSign.png");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                Image image16 = new Image(input16);
                ImageView img16=new ImageView(image16);
                Button epUp = new Button("",img16);
                epUp.setLayoutX(8);
                epUp.setLayoutY(65);
                double r=15;
                epUp.setShape(new Circle(r));
                epUp.setMinSize(2*r, 2*r);
                epUp.setMaxSize(2*r, 2*r);
                epUp.setOnAction(d -> {
                    manager.eggPowderPlant.level=2;
                    root1.getChildren().remove(epUp);
                    System.out.println("Workshop Upgraded!");
                });
                root1.getChildren().addAll(ep,epUp);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not Enough Coins to Build this Workshop!!");
                alert.setContentText("You need "+(manager.eggPowderPlant.makingPrice-manager.coins)+" more Coins to Build EggPowderPlant!");

                alert.showAndWait();
            }

            labelCoins.setText(manager.coins+"");
        });
        //////
        FileInputStream input66=new FileInputStream(".\\buildwf.png");
        Image image66 = new Image(input66);
        ImageView img66=new ImageView(image66);
        Button buildWf=new Button("",img66);
        buildWf.setLayoutX(5);
        buildWf.setLayoutY(148);
        buildWf.setPrefSize(80,70);
        buildWf.setOnAction(s ->{
            manager.building("WeavingFactory");
            FileInputStream input6= null;
            if(manager.weavingFactory.level==1){
                try {
                    input6 = new FileInputStream(".\\WF2.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image6 = new Image(input6);
                ImageView img6=new ImageView(image6);
                Button wf = new Button("",img6);
                wf.setLayoutX(5);
                wf.setLayoutY(148);
                wf.setPrefSize(80,70);
                wf.setOnAction(e -> {
                    manager.working_workshop("WeavingFactory",turnings.get());
                });
                FileInputStream input17= null;
                try {
                    input17 = new FileInputStream(".\\upgradeSign.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image17 = new Image(input17);
                ImageView img17=new ImageView(image17);
                Button wfUp = new Button("",img17);
                wfUp.setLayoutX(8);
                wfUp.setLayoutY(150);
                double r=15;
                wfUp.setShape(new Circle(r));
                wfUp.setMinSize(2*r, 2*r);
                wfUp.setMaxSize(2*r, 2*r);
                wfUp.setOnAction(e -> {
                    manager.weavingFactory.level=1;
                    root1.getChildren().remove(wfUp);
                    System.out.println("Workshop Upgraded!");
                });
                root1.getChildren().addAll(wf,wfUp);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not Enough Coins to Build this Workshop!!");
                alert.setContentText("You need "+(manager.weavingFactory.makingPrice-manager.coins)+" more Coins to Build WeavingFactory!");

                alert.showAndWait();
            }
            labelCoins.setText(manager.coins+"");
        });
        ///////////////
        FileInputStream input77=new FileInputStream(".\\buildmp.png");
        Image image77 = new Image(input77);
        ImageView img77=new ImageView(image77);
        Button buildMp=new Button("",img77);
        buildMp.setLayoutX(5);
        buildMp.setLayoutY(233);
        buildMp.setOnAction(s ->{
            manager.building("MilkPacking");
            FileInputStream input7= null;
            if(manager.milkPacking.level==1){
                try {
                    input7 = new FileInputStream(".\\MP2.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image7 = new Image(input7);
                ImageView img7=new ImageView(image7);
                Button mp = new Button("",img7);
                mp.setLayoutX(5);
                mp.setLayoutY(233);
                mp.setPrefSize(80,70);
                mp.setOnAction(e -> {
                    manager.working_workshop("MilkPacking",turnings.get());

                });
                double r=15;
                FileInputStream input18= null;
                try {
                    input18 = new FileInputStream(".\\upgradeSign.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image18 = new Image(input18);
                ImageView img18=new ImageView(image18);
                Button mpUp = new Button("",img18);
                mpUp.setLayoutX(8);
                mpUp.setLayoutY(235);
                mpUp.setShape(new Circle(r));
                mpUp.setMinSize(2*r, 2*r);
                mpUp.setMaxSize(2*r, 2*r);
                mpUp.setOnAction(e -> {
                    manager.milkPacking.level=2;
                    root1.getChildren().remove(mpUp);
                    System.out.println("Workshop Upgraded!");
                });
                root1.getChildren().addAll(mp,mpUp);

            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not Enough Coins to Build this Workshop!!");
                alert.setContentText("You need "+(manager.milkPacking.makingPrice-manager.coins)+" more Coins to Build MilkPacking!");
                alert.showAndWait();
            }
            labelCoins.setText(manager.coins+"");
        });
        //////////////
        FileInputStream input88=new FileInputStream(".\\buildlba.png");
        Image image88 = new Image(input88);
        ImageView img88=new ImageView(image88);
        Button buildBa=new Button("",img88);
        buildBa.setLayoutX(5);
        buildBa.setLayoutY(318);
        buildBa.setOnAction(s ->{
            manager.building("BAKERY");
            FileInputStream input8= null;
            if(manager.bakery.level==1){
                try {
                    input8 = new FileInputStream(".\\BA2.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image8 = new Image(input8);
                ImageView img8=new ImageView(image8);
                Button ba = new Button("",img8);
                ba.setLayoutX(5);
                ba.setLayoutY(318);
                ba.setPrefSize(80,70);
                ba.setOnAction(e -> {
                    manager.working_workshop("Bakery",turnings.get());

                });
                double r=15;
                FileInputStream input19= null;
                try {
                    input19 = new FileInputStream(".\\upgradeSign.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image19 = new Image(input19);
                ImageView img19=new ImageView(image19);
                Button baUp = new Button("",img19);
                baUp.setLayoutX(8);
                baUp.setLayoutY(320);
                baUp.setShape(new Circle(r));
                baUp.setMinSize(2*r, 2*r);
                baUp.setMaxSize(2*r, 2*r);
                baUp.setOnAction(e -> {
                    manager.bakery.level=2;
                    System.out.println("Workshop Upgraded!");
                    root1.getChildren().remove(baUp);
                });
                root1.getChildren().addAll(ba,baUp);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not Enough Coins to Build this Workshop!!");
                alert.setContentText("You need "+(manager.bakery.makingPrice-manager.coins)+" more Coins to Build Bakery!");

                alert.showAndWait();
            }
            labelCoins.setText(manager.coins+"");
        });
        //////////
        FileInputStream input99=new FileInputStream(".\\buildse.png");
        Image image99 = new Image(input99);
        ImageView img99=new ImageView(image99);
        Button buildSe=new Button("",img99);
        buildSe.setLayoutX(5);
        buildSe.setLayoutY(403);
        buildSe.setOnAction(s ->{
            manager.building("Sewing");
            FileInputStream input9= null;
            if(manager.sewing.level==1){
                try {
                    input9 = new FileInputStream(".\\SE2.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image9 = new Image(input9);
                ImageView img9=new ImageView(image9);
                Button se = new Button("",img9);
                se.setLayoutX(5);
                se.setLayoutY(403);
                se.setPrefSize(80,70);
                se.setOnAction(e -> {
                    manager.working_workshop("Sewing",turnings.get());
                });
                double r=15;
                FileInputStream input20= null;
                try {
                    input20 = new FileInputStream(".\\upgradeSign.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image20 = new Image(input20);
                ImageView img20=new ImageView(image20);
                Button seUp = new Button("",img20);
                seUp.setLayoutX(8);
                seUp.setLayoutY(405);
                seUp.setShape(new Circle(r));
                seUp.setMinSize(2*r, 2*r);
                seUp.setMaxSize(2*r, 2*r);
                seUp.setOnAction(e -> {
                    manager.sewing.level=2;
                    root1.getChildren().remove(seUp);
                    System.out.println("Workshop Upgraded!");
                });
                root1.getChildren().addAll(se,seUp);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not Enough Coins to Build this Workshop!!");
                alert.setContentText("You need "+(manager.sewing.makingPrice-manager.coins)+" more Coins to Build Sewing!");
                alert.showAndWait();
            }
            labelCoins.setText(manager.coins+"");
        });
        /////////
        FileInputStream input1010=new FileInputStream(".\\buildic.png");
        Image image1010 = new Image(input1010);
        ImageView img1010=new ImageView(image1010);
        Button buildIc=new Button("",img1010);
        buildIc.setLayoutX(5);
        buildIc.setLayoutY(488);
        buildIc.setOnAction(s ->{
            manager.building("IceCreamShop");
            FileInputStream input10= null;
            if(manager.iceCreamShop.level==1){
                try {
                    input10 = new FileInputStream(".\\IC2.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image10 = new Image(input10);
                ImageView img10=new ImageView(image10);
                Button ic = new Button("",img10);
                ic.setLayoutX(5);
                ic.setLayoutY(488);
                ic.setPrefSize(80,70);
                ic.setOnAction(e -> {
                    manager.working_workshop("IceCreamShop",turnings.get());
                });
                double r=15;
                FileInputStream input21= null;
                try {
                    input21 = new FileInputStream(".\\upgradeSign.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image21 = new Image(input21);
                ImageView img21=new ImageView(image21);
                Button icUp = new Button("",img21);
                icUp.setLayoutX(8);
                icUp.setLayoutY(490);
                icUp.setShape(new Circle(r));
                icUp.setMinSize(2*r, 2*r);
                icUp.setMaxSize(2*r, 2*r);
                icUp.setOnAction(e -> {
                    manager.iceCreamShop.level=2;
                    root1.getChildren().remove(icUp);
                    System.out.println("Workshop Upgraded!");
                });
                root1.getChildren().addAll(ic,icUp);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not Enough Coins to Build this Workshop!!");
                alert.setContentText("You need "+(manager.iceCreamShop.makingPrice-manager.coins)+" more Coins to Build IceCreamShop!");
                alert.showAndWait();
            }
            labelCoins.setText(manager.coins+"");
        });
///////////////////////////////////////////////////////////////////////////////////////////
        Button turn = new Button("TURN");
        turn.setLayoutX(786);
        turn.setLayoutY(210);
        turn.setPrefSize(200,25);
        turn.setTextFill(Color.DARKCYAN);
        turn.setFont(Font.font("Comic Sans MS",22));
        turn.setOnAction(e -> {
            int k=turnings.get();
            k++;
            turnings.set(k);
            manager.check_time(turnings.get());
            label1.setText(""+manager.well+"/5");
            label1.setFont(Font.font("Comic Sans MS",22));
            label1.setTextFill(Color.PEACHPUFF);
            System.out.println("turn 1");
            counter();
            label.setText("Turns Passed : "+counter);
            label.setFont(Font.font("Comic Sans MS",20));
            labelCoins.setText(manager.coins+"");
            manager.moving();
            try {
                drawImage(primaryStage,110,60,663,520,".\\farm.png",root1);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            readingFile();
            manager.attacking(findLevelByNum(levelNum),turnings.get());
            for (Chicken chicken1:manager.chickens) {
                int x=chicken1.life/10;
                chicken1.life=chicken1.life-x;
                chicken1.produceNum++;
                if(chicken1.produceNum%2==0){
                    chicken1.boolProduce = true;
                }
                else chicken1.boolProduce = false;
            }
            for (Turkey turkey1:manager.turkeys) {
                int x=turkey1.life/10;
                turkey1.life=turkey1.life-x;
                turkey1.produceNum++;
                if(turkey1.produceNum%3==0){
                    turkey1.boolProduce = true;
                }
                else turkey1.boolProduce = false;
            }
            for (Buffalo buffalo1:manager.buffaloes) {
                int x=buffalo1.life/10;
                buffalo1.life=buffalo1.life-x;
                buffalo1.produceNum++;
                if(buffalo1.produceNum%5==0){
                    buffalo1.boolProduce = true;
                }
                else buffalo1.boolProduce = false;
            }
            for (Chicken chicken1:manager.chickens) {
                System.out.println(chicken1.life);
            }
            manager.decreasingCageNum();
            manager.timeCancelingCage();
            manager.decreasingTimeCanceling();
            manager.drawGrass(primaryStage,root1);
            manager.drawAnimal(primaryStage,root1);
            manager.producing(primaryStage,root1);
            for(Chicken chickenTrack: manager.chickens){
                if(chickenTrack.existing){
                    System.out.println("["+(chickenTrack.x_position+110)+","+(chickenTrack.y_position+60)+"]");
                }
            }




            labelCoins.setText(manager.coins+"");


        });
        label.relocate(522,13);
        label.setFont(new Font(23));
        Text text1  = new Text();
        text1.setText("Coins : ");
        text1.setLayoutX(20);
        text1.setLayoutY(618);
        text1.setFont(Font.font("Comic Sans MS", FontPosture.REGULAR, 23));
        text1.setFill(Color.YELLOW);
        //
        Text text2  = new Text();
        text2.setText("Level Tasks :");
        text2.setLayoutX(814);
        text2.setLayoutY(436);
        text2.setFont(Font.font("Comic Sans MS", FontPosture.REGULAR, 24));
        text2.setFill(Color.NAVY);
        //truck
        FileInputStream input11=new FileInputStream(".\\truck.png");
        Image image11 = new Image(input11);
        ImageView img11=new ImageView(image11);
        Button truck = new Button("",img11);
        truck.setLayoutX(380);
        truck.setLayoutY(573);
        truck.setShape(new Rectangle());
        truck.setPrefSize(210,100);
        truck.setOnAction(e -> {
            labelCoins.setText(manager.coins+"");
            System.out.println("Truck selected");
            Stage stage=new Stage();
            Pane root = new Pane();
            Button unLoad=new Button();
            unLoad.setText("UNLOAD");
            unLoad.setLayoutX(60);
            unLoad.setLayoutY(50);
            unLoad.setFont(Font.font("Comic Sans MS",18));
            double r=60;
            unLoad.setShape(new Circle(r));
            unLoad.setMinSize(2*r, 2*r);
            unLoad.setMaxSize(2*r, 2*r);
            //unLoad.setPrefSize(150,90);
            unLoad.setTextFill(Color.FORESTGREEN);
            unLoad.setOnAction(s ->{
                Stage stage_1=new Stage();
                Pane root_1 = new Pane();
                stage_1.setTitle("UnLoading");
                Label label0 = new Label();
                Label label1 = new Label();
                Label label2 = new Label();
                Label label3 = new Label();
                Label label4 = new Label();
                Label label5 = new Label();
                Label label6 = new Label();
                Label label7 = new Label();
                Label label8 = new Label();
                Label label9 = new Label();
                Label label10 = new Label();
                Label label11 = new Label();
                Label label12 = new Label();
                Label label13 = new Label();
                Label label14 = new Label();
                int x=10;
                int y=20;
                int xButton=100;
                int yButton=20;
                int xstep=100;
                int ystep=40;

                if(manager.truck.truckEggNum!=0){
                    label0.setText("Egg : "+manager.truck.truckEggNum);
                    label0.setFont(Font.font("Comic Sans MS",15));
                    label0.setTextFill(Color.WHITESMOKE);
                    label0.setLayoutX(x);
                    label0.setLayoutY(y);
                    Button eggUn = new Button("Unload");
                    eggUn.setFont(Font.font("Comic Sans MS",15));
                    eggUn.setTextFill(Color.BLUEVIOLET);
                    eggUn.setLayoutX(x+xstep);
                    eggUn.setLayoutY(y);
                    eggUn.setPrefSize(xButton,yButton);
                    eggUn.setOnAction(e1 ->{
                        manager.truck_unload("Egg");
                        label0.setText("Egg : "+manager.truck.truckEggNum);
                    });
                    root_1.getChildren().addAll(label0,eggUn);
                    y+=ystep;
                }
                if(manager.truck.truckFeatherNum!=0){
                    label1.setText("Feather : "+manager.truck.truckFeatherNum);
                    label1.setFont(Font.font("Comic Sans MS",15));
                    label1.setTextFill(Color.WHITESMOKE);
                    label1.setLayoutX(x);
                    label1.setLayoutY(y);
                    Button featherUn = new Button("Unload");
                    featherUn.setFont(Font.font("Comic Sans MS",15));
                    featherUn.setTextFill(Color.BLUEVIOLET);
                    featherUn.setLayoutX(x+xstep);
                    featherUn.setLayoutY(y);
                    featherUn.setPrefSize(xButton,yButton);
                    featherUn.setOnAction(e1 ->{
                        manager.truck_unload("Feather");
                        label1.setText("Feather : "+manager.truck.truckFeatherNum);
                    });
                    root_1.getChildren().addAll(label1,featherUn);
                    y+=ystep;
                }
                if(manager.truck.truckMilkNum!=0){
                    label2.setText("Milk : "+manager.truck.truckMilkNum);
                    label2.setFont(Font.font("Comic Sans MS",15));
                    label2.setTextFill(Color.WHITESMOKE);
                    label2.setLayoutX(x);
                    label2.setLayoutY(y);
                    Button milkUn = new Button("Unload");
                    milkUn.setFont(Font.font("Comic Sans MS",15));
                    milkUn.setTextFill(Color.BLUEVIOLET);
                    milkUn.setLayoutX(x+xstep);
                    milkUn.setLayoutY(y);
                    milkUn.setPrefSize(xButton,yButton);
                    milkUn.setOnAction(e1 ->{
                        manager.truck_unload("Milk");
                        label1.setText("Feather : "+manager.truck.truckFeatherNum);
                    });
                    root_1.getChildren().addAll(label2,milkUn);
                    y+=ystep;
                }
                if(manager.truck.truckPowderNum!=0){
                    label3.setText("Powder : "+manager.truck.truckPowderNum);
                    label3.setFont(Font.font("Comic Sans MS",15));
                    label3.setTextFill(Color.WHITESMOKE);
                    label3.setLayoutX(x);
                    label3.setLayoutY(y);
                    Button powderUn = new Button("Unload");
                    powderUn.setFont(Font.font("Comic Sans MS",15));
                    powderUn.setTextFill(Color.BLUEVIOLET);
                    powderUn.setLayoutX(x+xstep);
                    powderUn.setLayoutY(y);
                    powderUn.setPrefSize(xButton,yButton);
                    powderUn.setOnAction(e1 ->{
                        manager.truck_unload("Powder");
                        label3.setText("Powder : "+manager.truck.truckPowderNum);
                    });
                    root_1.getChildren().addAll(label3,powderUn);
                    y+=ystep;
                }
                if(manager.truck.truckClothNum!=0){
                    label4.setText("Cloth : "+manager.truck.truckClothNum);
                    label4.setFont(Font.font("Comic Sans MS",15));
                    label4.setTextFill(Color.WHITESMOKE);
                    label4.setLayoutX(x);
                    label4.setLayoutY(y);
                    Button clothUn = new Button("Unload");
                    clothUn.setFont(Font.font("Comic Sans MS",15));
                    clothUn.setTextFill(Color.BLUEVIOLET);
                    clothUn.setLayoutX(x+xstep);
                    clothUn.setLayoutY(y);
                    clothUn.setPrefSize(xButton,yButton);
                    clothUn.setOnAction(e1 ->{
                        manager.truck_unload("Cloth");
                        label4.setText("Cloth : "+manager.truck.truckClothNum);
                    });
                    root_1.getChildren().addAll(label4,clothUn);
                    y+=ystep;
                }
                if(manager.truck.truckPackedMilkNum!=0){
                    label5.setText("PackedMilk : "+manager.truck.truckPackedMilkNum);
                    label5.setFont(Font.font("Comic Sans MS",15));
                    label5.setTextFill(Color.WHITESMOKE);
                    label5.setLayoutX(x);
                    label5.setLayoutY(y);
                    Button packedMilkUn = new Button("Unload");
                    packedMilkUn.setFont(Font.font("Comic Sans MS",15));
                    packedMilkUn.setTextFill(Color.BLUEVIOLET);
                    packedMilkUn.setLayoutX(x+xstep);
                    packedMilkUn.setLayoutY(y);
                    packedMilkUn.setPrefSize(xButton,yButton);
                    packedMilkUn.setOnAction(e1 ->{
                        manager.truck_unload("PackedMilk");
                        label5.setText("PackedMilk : "+manager.truck.truckPackedMilkNum);
                    });
                    root_1.getChildren().addAll(label5,packedMilkUn);
                    y+=ystep;
                }
                if(manager.truck.truckBreadNum!=0){
                    label6.setText("Bread : "+manager.truck.truckBreadNum);
                    label6.setFont(Font.font("Comic Sans MS",15));
                    label6.setTextFill(Color.WHITESMOKE);
                    label6.setLayoutX(x);
                    label6.setLayoutY(y);
                    Button breadUn = new Button("Unload");
                    breadUn.setFont(Font.font("Comic Sans MS",15));
                    breadUn.setTextFill(Color.BLUEVIOLET);
                    breadUn.setLayoutX(x+xstep);
                    breadUn.setLayoutY(y);
                    breadUn.setPrefSize(xButton,yButton);
                    breadUn.setOnAction(e1 ->{
                        manager.truck_unload("Bread");
                        label6.setText("Bread : "+manager.truck.truckBreadNum);
                    });
                    root_1.getChildren().addAll(label6,breadUn);
                    y+=ystep;
                }
                if(manager.truck.truckShirtNum!=0){
                    label7.setText("Shirt : "+manager.truck.truckShirtNum);
                    label7.setFont(Font.font("Comic Sans MS",15));
                    label7.setTextFill(Color.WHITESMOKE);
                    label7.setLayoutX(x);
                    label7.setLayoutY(y);
                    Button shirtUn = new Button("Unload");
                    shirtUn.setFont(Font.font("Comic Sans MS",15));
                    shirtUn.setTextFill(Color.BLUEVIOLET);
                    shirtUn.setLayoutX(x+xstep);
                    shirtUn.setLayoutY(y);
                    shirtUn.setPrefSize(xButton,yButton);
                    shirtUn.setOnAction(e1 ->{
                        manager.truck_unload("Shirt");
                        label7.setText("Shirt : "+manager.truck.truckShirtNum);
                    });
                    root_1.getChildren().addAll(label7,shirtUn);
                    y+=ystep;
                }
                if(manager.truck.truckIceCreamNum!=0){
                    label8.setText("IceCream : "+manager.truck.truckIceCreamNum);
                    label8.setFont(Font.font("Comic Sans MS",15));
                    label8.setTextFill(Color.WHITESMOKE);
                    label8.setLayoutX(x);
                    label8.setLayoutY(y);
                    Button iceCreamUn = new Button("Unload");
                    iceCreamUn.setFont(Font.font("Comic Sans MS",15));
                    iceCreamUn.setTextFill(Color.BLUEVIOLET);
                    iceCreamUn.setLayoutX(x+xstep);
                    iceCreamUn.setLayoutY(y);
                    iceCreamUn.setPrefSize(xButton,yButton);
                    iceCreamUn.setOnAction(e1 ->{
                        manager.truck_unload("IceCream");
                        label8.setText("IceCream : "+manager.truck.truckIceCreamNum);
                    });
                    root_1.getChildren().addAll(label8,iceCreamUn);
                    y+=ystep;
                }
                if(manager.truck.truckLionNum!=0){
                    label9.setText("Lion : "+manager.truck.truckLionNum);
                    label9.setFont(Font.font("Comic Sans MS",15));
                    label9.setTextFill(Color.WHITESMOKE);
                    label9.setLayoutX(x);
                    label9.setLayoutY(y);
                    Button lionUn = new Button("Unload");
                    lionUn.setFont(Font.font("Comic Sans MS",15));
                    lionUn.setTextFill(Color.BLUEVIOLET);
                    lionUn.setLayoutX(x+xstep);
                    lionUn.setLayoutY(y);
                    lionUn.setPrefSize(xButton,yButton);
                    lionUn.setOnAction(e1 ->{
                        manager.truck_unload("Lion");
                        label9.setText("Lion : "+manager.truck.truckLionNum);
                    });
                    root_1.getChildren().addAll(label9,lionUn);
                    y+=ystep;

                }
                if(manager.truck.truckTigerNum!=0){
                    label10.setText("Tiger : "+manager.truck.truckTigerNum);
                    label10.setFont(Font.font("Comic Sans MS",15));
                    label10.setTextFill(Color.WHITESMOKE);
                    label10.setLayoutX(x);
                    label10.setLayoutY(y);
                    Button tigerUn = new Button("Unload");
                    tigerUn.setFont(Font.font("Comic Sans MS",15));
                    tigerUn.setTextFill(Color.BLUEVIOLET);
                    tigerUn.setLayoutX(x+xstep);
                    tigerUn.setLayoutY(y);
                    tigerUn.setPrefSize(xButton,yButton);
                    tigerUn.setOnAction(e1 ->{
                        manager.truck_unload("Tiger");
                        label10.setText("Tiger : "+manager.truck.truckLionNum);
                    });
                    root_1.getChildren().addAll(label10,tigerUn);
                    y+=ystep;

                }
                if(manager.truck.truckBearNum!=0){
                    label11.setText("Bear : "+manager.truck.truckBearNum);
                    label11.setFont(Font.font("Comic Sans MS",15));
                    label11.setTextFill(Color.WHITESMOKE);
                    label11.setLayoutX(x);
                    label11.setLayoutY(y);
                    Button bearUn = new Button("Unload");
                    bearUn.setFont(Font.font("Comic Sans MS",15));
                    bearUn.setTextFill(Color.BLUEVIOLET);
                    bearUn.setLayoutX(x+xstep);
                    bearUn.setLayoutY(y);
                    bearUn.setPrefSize(xButton,yButton);
                    bearUn.setOnAction(e1 ->{
                        manager.truck_unload("Bear");
                        label11.setText("Bear : "+manager.truck.truckBearNum);
                    });
                    root_1.getChildren().addAll(label11,bearUn);
                    y+=ystep;

                }
                if(manager.truck.truckChickNum!=0){
                    label12.setText("Chicken : "+manager.truck.truckChickNum);
                    label12.setFont(Font.font("Comic Sans MS",15));
                    label12.setTextFill(Color.WHITESMOKE);
                    label12.setLayoutX(x);
                    label12.setLayoutY(y);
                    Button chickenUn = new Button("Unload");
                    chickenUn.setFont(Font.font("Comic Sans MS",15));
                    chickenUn.setTextFill(Color.BLUEVIOLET);
                    chickenUn.setLayoutX(x+xstep);
                    chickenUn.setLayoutY(y);
                    chickenUn.setPrefSize(xButton,yButton);
                    chickenUn.setOnAction(e1 ->{
                        manager.truck_unload("Chicken");
                        label12.setText("Chicken : "+manager.truck.truckChickNum);
                    });
                    root_1.getChildren().addAll(label12,chickenUn);
                    y+=ystep;

                }
                if(manager.truck.truckBufNum!=0){
                    label13.setText("Buffalo : "+manager.truck.truckBufNum);
                    label13.setFont(Font.font("Comic Sans MS",15));
                    label13.setTextFill(Color.WHITESMOKE);
                    label13.setLayoutX(x);
                    label13.setLayoutY(y);
                    Button bufUn = new Button("Unload");
                    bufUn.setFont(Font.font("Comic Sans MS",15));
                    bufUn.setTextFill(Color.BLUEVIOLET);
                    bufUn.setLayoutX(x+xstep);
                    bufUn.setLayoutY(y);
                    bufUn.setPrefSize(xButton,yButton);
                    bufUn.setOnAction(e1 ->{
                        manager.truck_unload("Buffalo");
                        label13.setText("Buffalo : "+manager.truck.truckBufNum);
                    });
                    root_1.getChildren().addAll(label13,bufUn);
                    y+=ystep;

                }
                if(manager.truck.truckTurkNum!=0){
                    label14.setText("Turkey : "+manager.truck.truckTurkNum);
                    label14.setFont(Font.font("Comic Sans MS",15));
                    label14.setTextFill(Color.WHITESMOKE);
                    label14.setLayoutX(x);
                    label14.setLayoutY(y);
                    Button turkUn = new Button("Unload");
                    turkUn.setFont(Font.font("Comic Sans MS",15));
                    turkUn.setTextFill(Color.BLUEVIOLET);
                    turkUn.setLayoutX(x+xstep);
                    turkUn.setLayoutY(y);
                    turkUn.setPrefSize(xButton,yButton);
                    turkUn.setOnAction(e1 ->{
                        manager.truck_unload("Turkey");
                        label14.setText("Turkey : "+manager.truck.truckTurkNum);
                    });
                    root_1.getChildren().addAll(label14,turkUn);
                    y+=ystep;

                }
                //
                FileInputStream inputt = null;
                try {
                    inputt = new FileInputStream("unloadbg.jpg");
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }

                // create a image
                Image imagee = new Image(inputt);

                // create a background image
                BackgroundImage backgroundimage = new BackgroundImage(imagee,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);

                // create Background
                Background background = new Background(backgroundimage);
                root_1.setBackground(background);

                Button closeBut = new Button("Close");
                closeBut.setFont(Font.font("Comic Sans MS",20));
                closeBut.setTextFill(Color.CRIMSON);
                closeBut.setOnAction(event -> {
                    stage_1.close();
                });
                closeBut.setLayoutX(115);
                closeBut.setLayoutY(440);
                root_1.getChildren().add(closeBut);
                stage_1.setScene(new Scene(root_1, 300, 500));
                stage_1.show();
            });
            Button truckGo=new Button("TRUCK GO");
            truckGo.setLayoutX(240);
            truckGo.setLayoutY(50);
            truckGo.setOnAction(s ->{
                manager.truck_go(turnings.get());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Truck Started Going to Bazaar...");
                alert.showAndWait();
            });
            truckGo.setFont(Font.font("Comic Sans MS",18));
            truckGo.setShape(new Circle(r));
            truckGo.setMinSize(2*r, 2*r);
            truckGo.setMaxSize(2*r, 2*r);
            //truckGo.setPrefSize(130,50);
            truckGo.setTextFill(Color.CORAL);
            Button exit=new Button("EXIT");
            exit.setLayoutX(420);
            exit.setLayoutY(50);
            exit.setOnAction(s ->{
                stage.close();
            });
            exit.setFont(Font.font("Comic Sans MS",20));
            exit.setShape(new Circle(r));
            exit.setMinSize(2*r, 2*r);
            exit.setMaxSize(2*r, 2*r);
            //truckGo.setPrefSize(130,50);
            exit.setTextFill(Color.RED);
            stage.setScene(new Scene(root, 600, 400));
            try {
                drawImage(stage,0,0,600,400,".\\truckunloadbg.jpg",root);
            }
            catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            root.getChildren().addAll(unLoad,truckGo,exit);
            stage.show();
        });
        //Well
        FileInputStream input12=new FileInputStream(".\\well.png");
        Image image12 = new Image(input12);
        ImageView img12=new ImageView(image12);
        Button well = new Button("",img12);
        well.setLayoutX(250);
        well.setLayoutY(565);
        well.setShape(new Rectangle());
        well.setOnAction(e -> {
            manager.welling(turnings.get());
            labelCoins.setText(manager.coins+"");
        });
        label1.relocate(200,589);
        label1.setFont(new Font(21));
        label1.setTextFill(Color.SADDLEBROWN);
        //jooje keshi
        FileInputStream input13=new FileInputStream(".\\JK2.png");
        Image image13 = new Image(input13);
        ImageView img13=new ImageView(image13);
        Button jk = new Button("",img13);
        jk.setLayoutX(775);
        jk.setLayoutY(63);
        jk.setPrefSize(80,70);
        jk.setOnAction(e -> {
            if(manager.eggNumber==0){

            }
            else {
                manager.working_workshop("JoojeKeshi",turnings.get());
            }
            labelCoins.setText(manager.coins+"");
        });
        storage.setText("STORAGE : "+manager.stock+" / 30");
        //WareHouse
        FileInputStream input14=new FileInputStream(".\\warehouse.png");
        Image image14 = new Image(input14);
        ImageView img14=new ImageView(image14);
        Button warehouse = new Button("",img14);
        warehouse.setLayoutX(635);
        warehouse.setLayoutY(570);
        warehouse.setShape(new Rectangle());
        warehouse.setOnAction(e -> {
            Stage stage=new Stage();
            int w=1520;
            int h=780;
            Pane root = new Pane();
            stage.setTitle("Ware House");
            try {
                drawImage(stage,0,0,w,h,".\\WAREHOUSE1.png",root);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            try {
                ware(stage,root);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            stage.setScene(new Scene(root,w,h));
            stage.show();
            System.out.println("WareHouse Selected");
            labelCoins.setText(manager.coins+"");
        });
        //pauseButton
        double r0=20;
        FileInputStream input15=new FileInputStream(".\\pause.png");
        Image image15 = new Image(input15);
        ImageView img15=new ImageView(image15);
        Button pause = new Button("",img15);
        pause.setLayoutX(5);
        pause.setLayoutY(3);
        pause.setShape(new Circle(r0));
        pause.setMinSize(2*r0, 2*r0);
        pause.setMaxSize(2*r0, 2*r0);
        pause.setOnAction(e -> {
            labelCoins.setText(manager.coins+"");
            Stage newStage=new Stage();
            Pane newRoot = new Pane();
            Button cont = new Button("CONTINUE");
            cont.setFont(Font.font("Comic Sans MS",25));
            cont.setTextFill(Color.DARKCYAN);
            cont.setLayoutX(75);
            cont.setLayoutY(55);
            cont.setPrefSize(200,90);
            cont.setOnAction(e1->{
                newStage.close();
            });
            Button reset = new Button("RESTART");
            reset.setFont(Font.font("Comic Sans MS",25));
            reset.setTextFill(Color.DARKCYAN);
            reset.setLayoutX(75);
            reset.setLayoutY(255);
            reset.setPrefSize(200,90);
            reset.setOnAction(e1->{
                newStage.close();
                manager.reseting();
                manager.stock=0;
                try {
                    start1(primaryStage,user,levelNum);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            Button quit = new Button("QUIT");
            quit.setFont(Font.font("Comic Sans MS",25));
            quit.setTextFill(Color.DARKCYAN);
            quit.setLayoutX(75);
            quit.setLayoutY(455);
            quit.setPrefSize(200,90);
            quit.setOnAction(e1->{
                newStage.close();
                menu(primaryStage);
            });

            try {
                drawImage(newStage,0,0,350,600,".\\pausebg.jpg",newRoot);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            newRoot.getChildren().addAll(cont,reset,quit);
            newStage.setScene(new Scene(newRoot, 350, 600));
            newStage.setX(700);
            newStage.setY(300);
            newStage.show();
        });
        //upgradeButton

        //plant
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int)(mouseEvent.getX()-110)/110;
                int y = (int)(mouseEvent.getY()-60)/85;
                if(mouseEvent.getX()>=110&&mouseEvent.getY()>=60&&mouseEvent.getY()<=577&&mouseEvent.getX()<=770){
                    manager.gardening(x,y);
                }
                try {
                    drawImage(primaryStage,110,60,663,520,".\\farm.png",root1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                manager.drawGrass(primaryStage,root1);
                label1.setText(""+manager.well+"/5");
                manager.drawAnimal(primaryStage,root1);
                manager.drawProduct(primaryStage,root1);
                manager.addTasks(levelNum,root1);
                boolean checklevBool2 = checkLevel(primaryStage,root1,user,levelNum);
                if(checklevBool2) {
                    manager.well=5;
                    counter=0;
                    manager.abel0.setText("");
                    manager.abel1.setText("");
                    manager.abel2.setText("");
                    manager.abel3.setText("");
                    manager.abel4.setText("");
                    manager.abel5.setText("");
                    manager.abel6.setText("");
                    manager.abel7.setText("");
                    manager.abel8.setText("");
                }
            }

        });
        drawImage(primaryStage,0,0,1000,704,".\\background.jpg",root1);
        drawImage(primaryStage,110,60,663,520,".\\farm.png",root1);
        drawImage(primaryStage,510,5,200,45,".\\turnings.png",root1);
        drawImage(primaryStage,10,575,150,70,".\\coinsButton.png",root1);
        drawImage(primaryStage,780,350,210,350,".\\tasks2.png",root1);
        drawImage(primaryStage,785,280,200,50,".\\levShowLable.png",root1);
        drawImage(primaryStage,179,575,80,70,".\\royalFlag.png",root1);
        userLevelShow.setFont(Font.font("Comic Sans MS",27));
        userLevelShow.setTextFill(Color.CYAN);
        userLevelShow.setLayoutX(831);
        userLevelShow.setLayoutY(285);
        root1.getChildren().add(userLevelShow);
        label.setText("Turns Passed : "+counter);
        label.setFont(Font.font("Comic Sans MS",20));
        label1.setFont(Font.font("Comic Sans MS",22));
        label1.setTextFill(Color.PEACHPUFF);
        root1.getChildren().addAll(chicken,turkey,buffalo,dog,cat,buildEp,buildWf,buildMp,buildBa,buildSe,buildIc,jk,text1,turn,text2,truck,well,warehouse,pause,label,label1,labelCoins);
       // manager.addTasks(levelNum,root1);
        root1.getChildren().addAll(manager.abel0,manager.abel1,manager.abel2,manager.abel3,manager.abel4,manager.abel5,manager.abel6,manager.abel7,manager.abel8);

        primaryStage.setScene(scene);
    }






    private void counter() {
        counter++;
    }

    public void ware(Stage primaryStage,Pane root) throws FileNotFoundException {
        storage.setText("STORAGE : "+manager.stock+" / 30");
        storage.setLayoutX(1175);
        storage.setLayoutY(709);
        storage.setFont(Font.font("Comic Sans MS",33));
        storage.setTextFill(Color.GOLDENROD);

        int x=370;
        int step_x=500;
        int y=58;
        int step_y=77;
        Label eggNum=new Label();
        FileInputStream input=new FileInputStream(".\\ware_truck1.png");
        Image image = new Image(input);
        ImageView img=new ImageView(image);

        Button ware_egg=new Button("",img);
        ware_egg.setLayoutX(x);
        ware_egg.setLayoutY(y);
        ware_egg.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.eggNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.eggNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.egg1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.egg1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("egg");
                        eggNum.setText(" X "+String.valueOf(manager.eggNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Eggs!");
                    alert.showAndWait();

                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_egg.setPrefSize(60,30);
        eggNum.setText(" X "+String.valueOf(manager.eggNumber));
        eggNum.setLayoutX(x-120);
        eggNum.setLayoutY(y-5);
        eggNum.setFont(Font.font("Comic Sans MS",35));
        eggNum.setTextFill(Color.CYAN);
        //
        Label featherNum=new Label();
        FileInputStream input1=new FileInputStream(".\\ware_truck1.png");
        Image image1 = new Image(input1);
        ImageView img1=new ImageView(image1);
        y+=step_y;
        Button ware_feather=new Button("",img1);
        ware_feather.setLayoutX(x);
        ware_feather.setLayoutY(y);
        ware_feather.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.featherNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.featherNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.feather1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.feather1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("feather");
                        featherNum.setText(" X "+String.valueOf(manager.featherNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Feathers!");
                    alert.showAndWait();

                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_feather.setPrefSize(50,20);
        featherNum.setText(" X "+String.valueOf(manager.featherNumber));
        featherNum.setLayoutX(x-120);
        featherNum.setLayoutY(y-3);
        featherNum.setFont(Font.font("Comic Sans MS",35));
        featherNum.setTextFill(Color.CYAN);
        //
        Label milkNum=new Label();
        FileInputStream input2=new FileInputStream(".\\ware_truck1.png");
        Image image2 = new Image(input2);
        ImageView img2=new ImageView(image2);
        y+=step_y;
        Button ware_milk=new Button("",img2);
        ware_milk.setLayoutX(x);
        ware_milk.setLayoutY(y);
        ware_milk.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.milkNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.milkNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.milk1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.milk1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("milk");
                        milkNum.setText(" X "+String.valueOf(manager.milkNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Milks!");
                    alert.showAndWait();

                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_milk.setPrefSize(50,20);
        milkNum.setText(" X "+String.valueOf(manager.milkNumber));
        milkNum.setLayoutX(x-120);
        milkNum.setLayoutY(y-3);
        milkNum.setFont(Font.font("Comic Sans MS",35));
        milkNum.setTextFill(Color.CYAN);
        //
        Label chickenNum=new Label();
        FileInputStream input3=new FileInputStream(".\\ware_truck1.png");
        Image image3 = new Image(input3);
        ImageView img3=new ImageView(image3);
        y+=step_y;
        y+=step_y;
        Button ware_chicken=new Button("",img3);
        ware_chicken.setLayoutX(x);
        ware_chicken.setLayoutY(y);
        int newCounter=0;
        if(manager.chickens!=null){
            for(Chicken chicken : manager.chickens){
                if(chicken.existing) newCounter++;
            }
        }
        chickenNum.setText(" X "+newCounter);
        ware_chicken.setOnAction(s ->{
            int newCounter3= 0;
            if(manager.chickens!=null){
                for(Chicken chicken : manager.chickens){
                    if(chicken.existing) newCounter3++;
                }
            }
            chickenNum.setText(" X "+newCounter3);

            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+newCounter3);
            text.setFont(Font.font("Comic Sans MS",15));
            text.setFont(new Font(16));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setFont(Font.font("Comic Sans MS",18));
            button1.setTextFill(Color.FORESTGREEN);
            Label bottonLable = new Label("");
            root1.addRow(3, bottonLable, button1);
            int finalNewCounter = newCounter3;
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<= finalNewCounter){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.chicken1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.chicken1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("chicken");
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Chickens!");
                    alert.showAndWait();

                }

                //
                int counterNew=0;
                if(manager.chickens!=null){
                    for(Chicken chicken : manager.chickens){
                        if(chicken.existing) counterNew++;
                    }
                }
                chickenNum.setText(" X "+counterNew);
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(4, bottonLable2, button2);
            button2.setOnAction(m -> {
               stage.close();
            });

            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);
            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_chicken.setPrefSize(50,20);
        newCounter=0;
        if(manager.chickens!=null){
            for(Chicken chicken : manager.chickens){
                if(chicken.existing) newCounter++;
            }
        }
        chickenNum.setText(" X "+newCounter);
        chickenNum.setLayoutX(x-120);
        chickenNum.setLayoutY(y);
        chickenNum.setFont(Font.font("Comic Sans MS",35));
        chickenNum.setTextFill(Color.CYAN);
        //
        Label dogNum=new Label();
        FileInputStream input4=new FileInputStream(".\\ware_truck1.png");
        Image image4 = new Image(input4);
        ImageView img4=new ImageView(image4);
        y+=step_y;
        newCounter=0;
        if(manager.dogs!=null){
            for(Dog dog : manager.dogs){
                if(dog.existing) newCounter++;
            }
        }
        dogNum.setText(" X "+newCounter);
        newCounter=0;
        if(manager.dogs!=null){
            for(Dog dog : manager.dogs){
                if(dog.existing) newCounter++;
            }
        }
        dogNum.setText(" X "+newCounter);
        dogNum.setLayoutX(x-120);
        dogNum.setLayoutY(y+3);
        dogNum.setFont(Font.font("Comic Sans MS",35));
        dogNum.setTextFill(Color.CYAN);
        //
        Label lionNum=new Label();
        FileInputStream input5=new FileInputStream(".\\ware_truck1.png");
        Image image5 = new Image(input5);
        ImageView img5=new ImageView(image5);
        y+=step_y;
        y+=step_y;
        Button ware_lion=new Button("",img5);
        ware_lion.setLayoutX(x);
        ware_lion.setLayoutY(y+35);
        ware_lion.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.lionNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.lionNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.lion1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.lion1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("lion");
                        lionNum.setText(" X "+String.valueOf(manager.lionNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Lions!");
                    alert.showAndWait();
                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_lion.setPrefSize(50,20);
        lionNum.setText(" X "+String.valueOf(manager.lionNumber));
        lionNum.setLayoutX(x-120);
        lionNum.setLayoutY(y+32);
        lionNum.setFont(Font.font("Comic Sans MS",35));
        lionNum.setTextFill(Color.CYAN);
        //
        Label bearNum=new Label();
        FileInputStream input6=new FileInputStream(".\\ware_truck1.png");
        Image image6 = new Image(input6);
        ImageView img6=new ImageView(image6);
        x+=step_x;
        Button ware_bear=new Button("",img6);
        ware_bear.setLayoutX(x-26);
        ware_bear.setLayoutY(y+35);
        ware_bear.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.bearNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.bearNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.bear1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.bear1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("bear");
                        bearNum.setText(" X "+String.valueOf(manager.bearNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Bears!");
                    alert.showAndWait();

                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_bear.setPrefSize(50,20);
        bearNum.setText(" X "+String.valueOf(manager.bearNumber));
        bearNum.setLayoutX(x-146);
        bearNum.setLayoutY(y+35);
        bearNum.setFont(Font.font("Comic Sans MS",35));
        bearNum.setTextFill(Color.CYAN);
        //
        Label catNum=new Label();
        FileInputStream input7=new FileInputStream(".\\ware_truck1.png");
        Image image7 = new Image(input7);
        ImageView img7=new ImageView(image7);
        y-=step_y;
        y-=step_y;
        Button ware_cat=new Button("",img7);
        ware_cat.setLayoutX(x-26);
        ware_cat.setLayoutY(y+3);
        newCounter=0;
        if(manager.cats!=null){
            for(Cat cat : manager.cats){
                if(cat.existing) newCounter++;
            }
        }
        catNum.setText(" X "+newCounter);
        ware_cat.setOnAction(s ->{
            int newCounter3= 0;
            if(manager.cats!=null){
                for(Cat cat : manager.cats){
                    if(cat.existing) newCounter3++;
                }
            }
            catNum.setText(" X "+newCounter3);

            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+newCounter3);
            text.setFont(Font.font("Comic Sans MS",15));
            text.setFont(new Font(16));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setFont(Font.font("Comic Sans MS",18));
            button1.setTextFill(Color.FORESTGREEN);
            Label bottonLable = new Label("");
            root1.addRow(3, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                for (int i = 0; i < number; i++) {
                    if(manager.truck.load+manager.cat1.capacity>manager.truck.MAX_LOAD){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Truck is Full !!!");
                        alert.setContentText("You need "+(number-i)*(manager.cat1.capacity)+" more Capacity to Load this Item.");
                        alert.showAndWait();
                        break;
                    }
                    manager.truck_load("cat");
                }
                //
                int counterNew=0;
                if(manager.cats!=null){
                    for(Cat cat : manager.cats){
                        if(cat.existing) counterNew++;
                    }
                }
                catNum.setText(" X "+counterNew);
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(4, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });

            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);
            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_cat.setPrefSize(50,20);
        newCounter=0;
        if(manager.cats!=null){
            for(Cat cat : manager.cats){
                if(cat.existing) newCounter++;
            }
        }
        catNum.setText(" X "+newCounter);
        catNum.setLayoutX(x-146);
        catNum.setLayoutY(y+3);
        catNum.setFont(Font.font("Comic Sans MS",35));
        catNum.setTextFill(Color.CYAN);
        //
        Label turkeyNum=new Label();
        FileInputStream input8=new FileInputStream(".\\ware_truck1.png");
        Image image8 = new Image(input8);
        ImageView img8=new ImageView(image8);
        y-=step_y;
        Button ware_turkey=new Button("",img8);
        ware_turkey.setLayoutX(x-26);
        ware_turkey.setLayoutY(y);
        newCounter=0;
        if(manager.turkeys!=null){
            for(Turkey turkey : manager.turkeys){
                if(turkey.existing) newCounter++;
            }
        }
        turkeyNum.setText(" X "+newCounter);
        ware_turkey.setOnAction(s ->{
            int newCounter3= 0;
            if(manager.turkeys!=null){
                for(Turkey turkey : manager.turkeys){
                    if(turkey.existing) newCounter3++;
                }
            }
            turkeyNum.setText(" X "+newCounter3);

            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+newCounter3);
            text.setFont(Font.font("Comic Sans MS",15));
            text.setFont(new Font(16));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setFont(Font.font("Comic Sans MS",18));
            button1.setTextFill(Color.FORESTGREEN);
            Label bottonLable = new Label("");
            root1.addRow(3, bottonLable, button1);
            int finalNewCounter = newCounter3;
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number< finalNewCounter){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.turkey1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.turkey1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("turkey");
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Turkeys!");
                    alert.showAndWait();
                }

                //
                int counterNew=0;
                if(manager.turkeys!=null){
                    for(Turkey turkey : manager.turkeys){
                        if(turkey.existing) counterNew++;
                    }
                }
                turkeyNum.setText(" X "+counterNew);
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(4, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });

            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);
            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_turkey.setPrefSize(50,20);
        newCounter=0;
        if(manager.turkeys!=null){
            for(Turkey turkey : manager.turkeys){
                if(turkey.existing) newCounter++;
            }
        }
        turkeyNum.setText(" X "+newCounter);
        turkeyNum.setLayoutX(x-146);
        turkeyNum.setLayoutY(y);
        turkeyNum.setFont(Font.font("Comic Sans MS",35));
        turkeyNum.setTextFill(Color.CYAN);
        //
        Label packedMilkNum=new Label();
        FileInputStream input9=new FileInputStream(".\\ware_truck1.png");
        Image image9 = new Image(input9);
        ImageView img9=new ImageView(image9);
        y-=step_y;
        y-=step_y;
        Button ware_packMilk=new Button("",img9);
        ware_packMilk.setLayoutX(x-26);
        ware_packMilk.setLayoutY(y);
        ware_packMilk.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.packedMilkNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.packedMilkNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.packedMilk1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.packedMilk1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("packedmilk");
                        packedMilkNum.setText(" X "+String.valueOf(manager.packedMilkNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough PackedMilks!");
                    alert.showAndWait();
                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_packMilk.setPrefSize(50,20);
        packedMilkNum.setText(" X "+String.valueOf(manager.packedMilkNumber));
        packedMilkNum.setLayoutX(x-146);
        packedMilkNum.setLayoutY(y);
        packedMilkNum.setFont(Font.font("Comic Sans MS",35));
        packedMilkNum.setTextFill(Color.CYAN);
        //
        Label clothNum=new Label();
        FileInputStream input10=new FileInputStream(".\\ware_truck1.png");
        Image image10 = new Image(input10);
        ImageView img10=new ImageView(image10);
        y-=step_y;
        Button ware_cloth=new Button("",img10);
        ware_cloth.setLayoutX(x-26);
        ware_cloth.setLayoutY(y);
        ware_cloth.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.clothNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.clothNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.cloth1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.cloth1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("cloth");
                        clothNum.setText(" X "+String.valueOf(manager.clothNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Cloths!");
                    alert.showAndWait();

                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_cloth.setPrefSize(50,20);
        clothNum.setText(" X "+String.valueOf(manager.clothNumber));
        clothNum.setLayoutX(x-146);
        clothNum.setLayoutY(y);
        clothNum.setFont(Font.font("Comic Sans MS",35));
        clothNum.setTextFill(Color.CYAN);
        //
        Label powderNum=new Label();
        FileInputStream input11=new FileInputStream(".\\ware_truck1.png");
        Image image11 = new Image(input11);
        ImageView img11=new ImageView(image11);
        y-=step_y;
        Button ware_powder=new Button("",img11);
        ware_powder.setLayoutX(x-26);
        ware_powder.setLayoutY(y);
        ware_powder.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.powderNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.powderNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.powder1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.powder1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("powder");
                        powderNum.setText(" X "+String.valueOf(manager.powderNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Powders!");
                    alert.showAndWait();
                }
                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_powder.setPrefSize(50,20);
        powderNum.setText(" X "+String.valueOf(manager.powderNumber));
        powderNum.setLayoutX(x-146);
        powderNum.setLayoutY(y);
        powderNum.setFont(Font.font("Comic Sans MS",35));
        powderNum.setTextFill(Color.CYAN);
        //
        Label breadNum=new Label();
        FileInputStream input12=new FileInputStream(".\\ware_truck1.png");
        Image image12 = new Image(input12);
        ImageView img12=new ImageView(image12);
        x+=step_x;
        Button ware_bread=new Button("",img12);
        ware_bread.setLayoutX(x-50);
        ware_bread.setLayoutY(y);
        ware_bread.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.breadNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.breadNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.bread1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.bread1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("bread");
                        breadNum.setText(" X "+String.valueOf(manager.breadNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Breads!");
                    alert.showAndWait();
                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_bread.setPrefSize(50,20);
        breadNum.setText(" X "+String.valueOf(manager.breadNumber));
        breadNum.setLayoutX(x-170);
        breadNum.setLayoutY(y);
        breadNum.setFont(Font.font("Comic Sans MS",35));
        breadNum.setTextFill(Color.CYAN);
        //
        Label shirtNum=new Label();
        FileInputStream input13=new FileInputStream(".\\ware_truck1.png");
        Image image13 = new Image(input13);
        ImageView img13=new ImageView(image13);
        y+=step_y;
        Button ware_shirt=new Button("",img13);
        ware_shirt.setLayoutX(x-50);
        ware_shirt.setLayoutY(y);
        ware_shirt.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.shirtNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.shirtNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.shirt1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.shirt1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("shirt");
                        shirtNum.setText(" X "+String.valueOf(manager.shirtNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Shirts!");
                    alert.showAndWait();
                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_shirt.setPrefSize(50,20);
        shirtNum.setText(" X "+String.valueOf(manager.shirtNumber));
        shirtNum.setLayoutX(x-170);
        shirtNum.setLayoutY(y);
        shirtNum.setFont(Font.font("Comic Sans MS",35));
        shirtNum.setTextFill(Color.CYAN);
        //
        Label iceCreamNum=new Label();
        FileInputStream input14=new FileInputStream(".\\ware_truck1.png");
        Image image14 = new Image(input14);
        ImageView img14=new ImageView(image14);
        y+=step_y;
        Button ware_iceCream=new Button("",img14);
        ware_iceCream.setLayoutX(x-50);
        ware_iceCream.setLayoutY(y);
        ware_iceCream.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.icecreamNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.icecreamNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.icecream1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.icecream1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("icecream");
                        iceCreamNum.setText(" X "+String.valueOf(manager.icecreamNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough IceCreams!");
                    alert.showAndWait();
                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_iceCream.setPrefSize(50,20);
        iceCreamNum.setText(" X "+String.valueOf(manager.icecreamNumber));
        iceCreamNum.setLayoutX(x-170);
        iceCreamNum.setLayoutY(y);
        iceCreamNum.setFont(Font.font("Comic Sans MS",35));
        iceCreamNum.setTextFill(Color.CYAN);
        //
        Label buffaloNum=new Label();
        FileInputStream input15=new FileInputStream(".\\ware_truck1.png");
        Image image15 = new Image(input15);
        ImageView img15=new ImageView(image15);
        y+=step_y;
        y+=step_y;
        Button ware_buffalo=new Button("",img15);
        ware_buffalo.setLayoutX(x-50);
        ware_buffalo.setLayoutY(y);
        newCounter=0;
        if(manager.buffaloes!=null){
            for(Buffalo buffalo : manager.buffaloes){
                if(buffalo.existing) newCounter++;
            }
        }
        buffaloNum.setText(" X "+newCounter);
        ware_buffalo.setOnAction(s ->{
            int newCounter3= 0;
            if(manager.buffaloes!=null){
                for(Buffalo buffalo : manager.buffaloes){
                    if(buffalo.existing) newCounter3++;
                }
            }
            buffaloNum.setText(" X "+newCounter3);

            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+newCounter3);
            text.setFont(Font.font("Comic Sans MS",15));
            text.setFont(new Font(16));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setFont(Font.font("Comic Sans MS",18));
            button1.setTextFill(Color.FORESTGREEN);
            Label bottonLable = new Label("");
            root1.addRow(3, bottonLable, button1);
            int finalNewCounter = newCounter3;
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<= finalNewCounter){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.buffalo1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.buffalo1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("buffalo");
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Buffaloes!");
                    alert.showAndWait();
                }

                //
                int counterNew=0;
                if(manager.buffaloes!=null){
                    for(Buffalo buffalo : manager.buffaloes){
                        if(buffalo.existing) counterNew++;
                    }
                }
                buffaloNum.setText(" X "+counterNew);
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(4, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });

            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);
            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_buffalo.setPrefSize(50,20);
        newCounter=0;
        if(manager.buffaloes!=null){
            for(Buffalo buffalo : manager.buffaloes){
                if(buffalo.existing) newCounter++;
            }
        }
        buffaloNum.setText(" X "+newCounter);
        buffaloNum.setLayoutX(x-170);
        buffaloNum.setLayoutY(y);
        buffaloNum.setFont(Font.font("Comic Sans MS",35));
        buffaloNum.setTextFill(Color.CYAN);
        //
        Label tigerNum=new Label();
        FileInputStream input16=new FileInputStream(".\\ware_truck1.png");
        Image image16 = new Image(input16);
        ImageView img16=new ImageView(image16);
        y+=step_y;
        y+=step_y;
        y+=step_y;
        Button ware_tiger=new Button("",img16);
        ware_tiger.setLayoutX(x-50);
        ware_tiger.setLayoutY(y+35);
        ware_tiger.setOnAction(s ->{
            Stage stage=new Stage();
            GridPane root1 = new GridPane();
            stage.setTitle("Loading Good");

            Label num = new Label(" Number : ");
            num.setFont(Font.font("Comic Sans MS",15));
            TextField tf1 = new TextField();
            root1.addRow(1, num, tf1);

            Text text=new Text("Max Possible : "+manager.tigerNumber);
            text.setFont(Font.font("Comic Sans MS",15));
            root1.addRow(2,new Label(""),text);

            Button button1 = new Button();
            button1.setText(" Load ");
            button1.setTextFill(Color.FORESTGREEN);
            button1.setFont(Font.font("Comic Sans MS",18));
            Label bottonLable = new Label("");
            root1.addRow(4, bottonLable, button1);
            button1.setOnAction(m -> {
                readingFile();
                int number=Integer.parseInt(tf1.getText());
                if(number<=manager.tigerNumber){
                    for (int i = 0; i < number; i++) {
                        if(manager.truck.load+manager.tiger1.capacity>manager.truck.MAX_LOAD){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Truck is Full !!!");
                            alert.setContentText("You need "+(number-i)*(manager.tiger1.capacity)+" more Capacity to Load this Item.");
                            alert.showAndWait();
                            break;
                        }
                        manager.truck_load("tiger");
                        tigerNum.setText(" X "+String.valueOf(manager.tigerNumber));
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not Enough Tigers!");
                    alert.showAndWait();
                }

                storage.setText("STORAGE : "+manager.stock+" / 30");
                stage.close();
            });

            Button button2 = new Button();
            button2.setText(" Cancel ");
            button2.setFont(Font.font("Comic Sans MS",18));
            button2.setTextFill(Color.CRIMSON);
            Label bottonLable2 = new Label("");
            root1.addRow(5, bottonLable2, button2);
            button2.setOnAction(m -> {
                stage.close();
            });
            /////
            FileInputStream inputt = null;
            try {
                inputt = new FileInputStream("mainload.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagee = new Image(inputt);
            BackgroundImage backgroundimage = new BackgroundImage(imagee,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root1.setBackground(background);

            Scene scene = new Scene(root1, 350, 250);
            stage.setScene(scene);
            stage.show();
        });
        ware_tiger.setPrefSize(50,20);
        tigerNum.setText(" X "+String.valueOf(manager.tigerNumber));
        tigerNum.setLayoutX(x-170);
        tigerNum.setLayoutY(y+35);
        tigerNum.setFont(Font.font("Comic Sans MS",35));
        tigerNum.setTextFill(Color.CYAN);
        ////
        FileInputStream input17=new FileInputStream(".\\backbutton.png");
        Image image17 = new Image(input17);
        ImageView img17=new ImageView(image17);
        double r = 35;
        Button backButton=new Button("",img17);
        backButton.setLayoutX(100);
        backButton.setLayoutY(y+104);
        backButton.setOnAction(s ->{
            primaryStage.close();
        });
        backButton.setFont(Font.font("Comic Sans MS",20));
        backButton.setShape(new Circle(r));
        backButton.setMinSize(2*r, 2*r);
        backButton.setMaxSize(2*r, 2*r);
        backButton.setTextFill(Color.RED);

        root.getChildren().addAll(ware_egg,ware_feather,ware_milk,ware_chicken,ware_lion,ware_powder,ware_cloth,ware_packMilk,ware_turkey,ware_tiger,ware_bread,ware_shirt,ware_iceCream,ware_buffalo,ware_bear,eggNum,featherNum,milkNum,chickenNum,dogNum,lionNum,bearNum,catNum,turkeyNum,packedMilkNum,clothNum,powderNum,tigerNum,buffaloNum,iceCreamNum,shirtNum,breadNum,backButton,storage);
    }

    public void menuLevel(Stage primaryStage,Users user1){
        primaryStage.setTitle("Levels");
        Button level1But = new Button("LEVEL 1");
        level1But.setOnAction(e -> {
            manager.leveling(5);
            try {
               // start1(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            labelCoins.setText(manager.coins+"");
        });

        Button level2But = new Button("LEVEL 2");
        level2But.setOnAction(e -> {
            if(user1.levelNum>=2){
                manager.leveling(2);
                try {
                    //start1(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            labelCoins.setText(manager.coins+"");
        });

        Button level3But = new Button("LEVEL 3");
        level3But.setOnAction(e -> {
            if(user1.levelNum>=3) {
                manager.leveling(3);
                try {
                   // start1(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            labelCoins.setText(manager.coins+"");

        });

        Button level4But = new Button("LEVEL 4");
        level4But.setOnAction(e -> {
            if(user1.levelNum>=4){
                manager.leveling(4);
                try {
                   // start1(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            labelCoins.setText(manager.coins+"");
        });

        Button level5But = new Button("LEVEL 5");
        level5But.setOnAction(e -> {
            if(user1.levelNum>=5){
                manager.leveling(5);
                try {
                  //  start1(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            labelCoins.setText(manager.coins+"");
        });


        Pane root1 = new Pane();
        root1.getChildren().addAll(level1But,level2But,level3But,level4But,level5But);
        Scene scene=new Scene(root1,1000,704);
        primaryStage.setScene(scene);
    }
    public void run(Stage primaryStage) {
        //menu(primaryStage);
        int lev=1;
        String input;
        input="";
        boolean vorod=false;
        boolean vorod2= false;
        boolean bonus = false;
        int turnings=0;
        Users user1=new Users();
        while (!input.equals("EXIT")){
            readingFile();
            input=scanner.nextLine();
            input= input.toUpperCase();
            if(input.equalsIgnoreCase("SIGN UP")){

                if(manager.users==null){
                    manager.users=new ArrayList<>();
                }
                String userName,pass;
                System.out.println("User Name :");
                userName=scanner.nextLine();
                user1=findingUserByUserName(userName);
                while (user1!=null){
                    System.err.println("UserName Already Exists !");
                    Date date = new Date();
                    String s = "UserName Has been Entered Before!";
                    manager.logger(date,s,false);
                    userName=scanner.nextLine();
                    user1=findingUserByUserName(userName);
                }
                try {
                    FileWriter myWriter = new FileWriter(file2,true);
                    BufferedWriter buffy = new BufferedWriter(myWriter);
                    buffy.write(userName);
                    buffy.newLine();
                    buffy.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("ERROR");
                }
                /////
                Date date = new Date();
                String s = "UserName Entered correctly";
                manager.logger(date,s,true);
                System.out.println("UserName Entered correctly");
                //
                //
                System.out.println("Password :");
                pass=scanner.nextLine();
                user1=new Users(userName,pass);

                if(manager.users==null){
                    manager.users=new ArrayList<>();
                }
                System.out.println("Account Created!");
                //
                date = new Date();
                s = "Account Created";
                manager.logger(date,s,true);
                //
                manager.leveling(user1.levelNum);
                System.out.println("Your Level:"+user1.levelNum);
                vorod=true;
                lev=1;
            }//finish
            else if(input.equalsIgnoreCase("LOG IN")){
                String userName;
                System.out.println("Enter Your UserName :");
                userName=scanner.nextLine();
                user1=findingUserByUserName(userName);
                while(user1==null){
                    System.err.println("UserName Not Found! Sign Up!");
                    Date date = new Date();
                    String s = "Wrong UserName";
                    manager.logger(date,s,false);
                    System.out.println("Enter Your UserName :");
                    userName=scanner.nextLine();
                    user1=findingUserByUserName(userName);
                }
                try {
                    FileWriter myWriter = new FileWriter(file2,true);
                    BufferedWriter buffy = new BufferedWriter(myWriter);
                    buffy.write(userName);
                    buffy.newLine();
                    buffy.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("ERROR");
                }
                if(manager.users!=null){
                    String pass="";
                    while (!pass.equals(user1.pass)){
                        if(!pass.equals(""))  System.err.println("Password is not correct!!");
                        Date date = new Date();
                        String s = "Wrong Password";
                        manager.logger(date,s,false);
                        System.out.println("Enter Your Password :");
                        pass=scanner.nextLine();
                    }
                    System.out.println("Password is correct");
                    //
                    Date date = new Date();
                    String s = "Password entered correctly";
                    manager.logger(date,s,true);
                    //
                    //Enter Menu
                    System.out.println("**** MENU ****");
                    System.out.println("START");
                    System.out.println("LOG OUT");
                    System.out.println("SETTINGS");
                    System.out.println("Enter your Command : ");
                    vorod2=true;
                }
                else {
                    System.out.println("UserName doesn't exist : Please Sign Up!");
                    Date date = new Date();
                    String s = "Invalid UserName";
                    manager.logger(date,s,false);
                }
            }//finish
            else if (vorod==false&&vorod2==false) {
                if(!input.equalsIgnoreCase("exit"))
                    System.err.println("Invalid Input!!!!");
                Date date = new Date();
                String s = "Invalid Input Entered";
                manager.logger(date,s,false);
            }

            else if(input.startsWith("CAGE")&&vorod){
                caging(input.split("\\s"));
            }

            else if(input.startsWith("TRUCK LOAD")&&vorod){
                truckLoading(input.split("\\s"));
            }
            else if(input.startsWith("TRUCK UNLOAD")&&vorod){
                truckUnloading(input.split("\\s"));
            }
            else if(input.startsWith("TURN")&&vorod){
                String[] s=input.split("\\s");
                int turn1 = Integer.parseInt(s[1]);

                for (int i = 0; i < turn1; i++) {
                    manager.attacking(findLevelByNum(lev),turnings);
                    manager.decreasingTimeCanceling();
                    manager.decreasingCageNum();
                    manager.timeCancelingCage();
                    turnings+=1;
                    manager.moving();
                    manager.check_time(turnings);
                    label1.setText(""+manager.well+"/5");
                    // decreasing life producing animals

                   // manager.producing();

                }
                showingInfo(turnings);
            }
            else if(input.startsWith("TRUCK GO")&&vorod){
                manager.truck_go(turnings);
            }
            else if(input.startsWith("LOG OUT")&&vorod){
                vorod=false;
                vorod2=false;
                System.out.println("LOG IN Or SIGN UP");
            }
            else if(input.startsWith("START")&&vorod2){
                String[] splitInput = input.split("\\s");
                int level = Integer.parseInt(splitInput[1]);
                if(level<=user1.levelNum)  {
                    lev = level;
                    vorod=true;
                    manager.leveling(lev);
                    System.out.println("LEVEL "+lev+" :");
                    if(bonus){
                        if(lev==2){
                            manager.coins+=BONUS_LEVEL_1;
                            user1.remainingCoins1+=BONUS_LEVEL_1;
                        }
                        else if(lev==3){
                            manager.coins+=BONUS_LEVEL_2;
                            user1.remainingCoins2+=BONUS_LEVEL_2;
                        }
                        else if(lev==4){
                            manager.coins+=BONUS_LEVEL_3;
                            user1.remainingCoins3+=BONUS_LEVEL_3;
                        }
                        else if(lev==5){
                            manager.coins+=BONUS_LEVEL_4;
                            user1.remainingCoins4+=BONUS_LEVEL_4;
                        }
                    }
                    bonus=false;
                    //manager.coins+=remainingCoins;
                    if(lev==2){
                        manager.coins+=user1.remainingCoins1;
                    }
                    else if(lev==3){
                        manager.coins+=user1.remainingCoins2;
                    }
                    else if(lev==4){
                        manager.coins+=user1.remainingCoins3;
                    }
                    else if(lev==5){
                        manager.coins+=user1.remainingCoins4;
                    }
                }
                else{
                    System.err.println("You cannot access this Level!!");
                    Date date = new Date();
                    String s = "Access Denied";
                    manager.logger(date,s,false);
                }

            }
            else if (input.startsWith("INQUIRY")&&vorod){
                showingInfo(turnings);
            }
            else{
                if(!input.equalsIgnoreCase("exit"))
                    System.err.println("Invalid Input!!!!");
                Date date = new Date();
                String s = "Invalid Input Entered";
                manager.logger(date,s,false);

            }
            if(manager.win==true&&vorod){
                user1.levelNum++;
                if(user1.levelNum==6) {
                    System.err.println("You Completed the Game!!");
                    System.out.println("Technical Directors :");
                    System.out.println("  Parsa Rahimzadeh");
                    System.out.println("  Ali KhosraviPour");
                    System.out.println("'Farm Frenzy KhepRahim Version'");
                    System.err.println("**************Spring 2021**************");
                    Date date = new Date();
                    String s = "PLAYER HAS FINISHED THE GAME";
                    manager.logger(date,s,true);
                    return;
                }
                int remainingCoins=manager.coins;
                if(user1.levelNum==2) user1.remainingCoins1+=remainingCoins;
                else if(user1.levelNum==3) user1.remainingCoins2+=remainingCoins;
                else if(user1.levelNum==4) user1.remainingCoins3+=remainingCoins;
                else if(user1.levelNum==5) user1.remainingCoins4+=remainingCoins;
                if(turnings<=findLevelByNum(lev).bonusTime){
                    System.out.println("Congrats!!! You will Get Bonus Reward!!");
                    Date date = new Date();
                    String s = "Player Got Bonus Reward";
                    manager.logger(date,s,true);
                    bonus = true;
                }
                System.out.println("You Completed Level "+lev);
                if(vorod){
                    if(lev>user1.levelNum){
                        user1.levelNum=lev;
                    }
                    manager.users.add(user1);
                    if(manager.users.size()!=1) {
                        Users users10 = manager.users.get(manager.users.size()-2);
                        Users users11 = manager.users.get(manager.users.size()-1);
                        if(users10.userName.equals(users11.userName)){
                            manager.users.remove(manager.users.size()-2);
                        }
                    }
                }
                input= scanner.nextLine();
                input=input.toUpperCase();
                while(!input.equalsIgnoreCase("MENU")){
                    System.err.println("Invalid Input! Enter Menu");
                    input= scanner.nextLine();
                    input=input.toUpperCase();
                }
                System.out.println("**** MENU ****");
                System.out.println("START");
                System.out.println("LOG OUT");
                System.out.println("SETTINGS");
                System.out.println("Enter your Command : ");
                input=scanner.nextLine();
                input=input.toUpperCase();
                while(!input.startsWith("START")&&!input.equalsIgnoreCase("LOG OUT")&&!input.equalsIgnoreCase("SETTINGS")){
                    System.err.println("Invalid Input!");
                    input= scanner.nextLine();
                    input=input.toUpperCase();
                }
                if(input.startsWith("START")){
                    String[] newSplitInput = input.split("\\s");
                    int level = Integer.parseInt(newSplitInput[1]);
                    if(level<=user1.levelNum)  {
                        lev = level;
                        vorod=true;
                        manager=new Manager(manager.levels,manager.users);
                        manager.leveling(lev);
                        System.out.println("LEVEL "+lev+" :");
                        if(bonus){
                            if(lev==2){
                                manager.coins+=BONUS_LEVEL_1;
                                user1.remainingCoins1+=BONUS_LEVEL_1;
                            }
                            else if(lev==3){
                                manager.coins+=BONUS_LEVEL_2;
                                user1.remainingCoins2+=BONUS_LEVEL_2;
                            }
                            else if(lev==4){
                                manager.coins+=BONUS_LEVEL_3;
                                user1.remainingCoins3+=BONUS_LEVEL_3;
                            }
                            else if(lev==5){
                                manager.coins+=BONUS_LEVEL_4;
                                user1.remainingCoins4+=BONUS_LEVEL_4;
                            }
                        }
                        bonus=false;
                        //manager.coins+=remainingCoins;
                        if(lev==2){
                            manager.coins+=user1.remainingCoins1;
                        }
                        else if(lev==3){
                            manager.coins+=user1.remainingCoins2;
                        }
                        else if(lev==4){
                            manager.coins+=user1.remainingCoins3;
                        }
                        else if(lev==5){
                            manager.coins+=user1.remainingCoins4;
                        }
                    }
                    else{
                        System.err.println("You cannot access this Level!!");
                        Date date = new Date();
                        String s = "Access Denied";
                        manager.logger(date,s,false);
                    }
                }
                else if (input.equalsIgnoreCase("LOG OUT")){
                    vorod=false;
                    vorod2=false;
                    System.out.println("LOG IN Or SIGN UP");
                }
                manager.win=false;
                turnings=0;
            }
            if(vorod){
                if(lev>user1.levelNum){
                    user1.levelNum=lev;
                }
                manager.users.add(user1);
                if(manager.users.size()!=1) {
                    Users users10 = manager.users.get(manager.users.size()-2);
                    Users users11 = manager.users.get(manager.users.size()-1);
                    if(users10.userName.equals(users11.userName)){
                        manager.users.remove(manager.users.size()-2);
                    }
                }
            }
            savingFile();
        }
        try {
            FileWriter myWriter = new FileWriter(file2,true);
            BufferedWriter buffy = new BufferedWriter(myWriter);
            buffy.write("[Info] , Player Left The Game");
            buffy.newLine();
            for(int i=0;i<80;i++){
                buffy.write("*");
            }
            buffy.newLine();
            buffy.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR");
        }

    }
    public Levels enterLevel(Users users){
        for (Levels levels:manager.levels) {
            if(levels.number==users.levelNum){
                return levels;
            }
        }
        return null;
    }

    public boolean checkLevel(Stage primaryStage,Pane root,Users user,int levelNum){
        boolean checklevBool = false;
        Levels level=findLevelByNum(levelNum);
        int k=0;
        if(manager.max_eggNumber2-manager.max_eggNumber==level.egg){
            k++;
        }
        if(manager.max_featherNumber2-manager.max_featherNumber==level.feather){
            k++;
        }
        if(manager.max_milkNumber2-manager.max_milkNumber==level.milk){
            k++;
        }
        if(manager.max_powderNumber2-manager.max_powderNumber==level.powder){
            k++;
        }
        if(manager.max_clothNumber2-manager.max_clothNumber==level.cloth){
            k++;
        }
        if(manager.max_packedMilkNumber2-manager.max_packedMilkNumber==level.packedMilk){
            k++;
        }
        if(manager.max_breadNumber2-manager.max_breadNumber==level.bread){
            k++;
        }
        if(manager.max_shirtNumber2-manager.max_shirtNumber==level.shirt){
            k++;
        }
        if(manager.max_icecreamNumber2-manager.max_icecreamNumber==level.iceCream){
            k++;
        }
        if(k==9){
            showLevel(user,primaryStage);

            System.out.println("You Won");
            if(user.levelNum==levelNum){
                readingFile();
                user.levelNum++;
                if(user.levelNum==2){
                    if(turnings.get()<=9){
                        user.remainingCoins2+=BONUS_LEVEL_1;
                    }
                }
                else if(user.levelNum==3){
                    if(turnings.get()<=25){
                        user.remainingCoins3+=BONUS_LEVEL_2;
                    }
                }
                else if(user.levelNum==4){
                    if(turnings.get()<=30){
                        user.remainingCoins4+=BONUS_LEVEL_3;
                    }
                }
                else if(user.levelNum==5){
                    if(turnings.get()<=40){
                        user.remainingCoins5+=BONUS_LEVEL_4;
                    }
                }
                if(user.levelNum==6){
                    primaryStage.close();
                    Stage newStage = new Stage();
                    Pane newRoot = new Pane();


                    try {
                        drawImage(newStage,0,0,663,520,".\\winner.png",newRoot);
                    } catch (FileNotFoundException exception) {
                        exception.printStackTrace();
                    }
                    Scene scene=new Scene(newRoot,663,520);
                    newStage.setScene(scene);
                    newStage.show();
                }
                else{
                    int k1=0;
                    for (Users users:manager.users) {
                        if(users.userName.equals(user.userName)){
                            break;
                        }
                        k1++;
                    }
                    Users user1=new Users(user.userName,user.pass,user.levelNum);
                    manager.users.remove(k1);
                    manager.users.add(user1);
                    savingFile();
                    manager.stock=0;
                    manager.well=5;
                    manager.leveling(user.levelNum);
                    manager.reseting();
                    manager.addTasks(user.levelNum,root);
                }

            }
            checklevBool=true;
            showLevel(user,primaryStage);
        }
        return checklevBool;

    }
    public void showLevel(Users user,Stage primaryStage){
        primaryStage.setScene(new Scene(new Pane(),900,600));
        if(user.levelNum!=6){
            Pane gridPane=new Pane();
            try {
                drawImage(primaryStage,0,0,900,600,".\\levMapBg.png",gridPane);
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
            int level=user.levelNum;
            Button button1=new Button();
            button1.setText("1");
            button1.setFont(Font.font("Comic Sans MS",20));
            button1.setTextFill(Color.YELLOW);
            button1.setLayoutX(95);
            button1.setLayoutY(399);
            button1.setShape(new Rectangle());
            button1.setOnAction(e1 -> {
                try {
                    if(level>=1){
                        start1(primaryStage,user,1);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Access Denied!");
                        alert.setContentText("You Haven't Completed the Previous Levels yet!!");

                        alert.showAndWait();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            //
            Button button2=new Button();
            button2.setText("2");
            button2.setFont(Font.font("Comic Sans MS",20));
            button2.setTextFill(Color.YELLOW);
            button2.setLayoutX(234);
            button2.setLayoutY(401);
            button2.setShape(new Rectangle());
            button2.setOnAction(e2 -> {
                try {
                    if(level>=2){
                        start1(primaryStage,user,2);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Access Denied!");
                        alert.setContentText("You Haven't Completed the Previous Levels yet!!");

                        alert.showAndWait();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            //
            Button button3=new Button();
            button3.setText("3");
            button3.setFont(Font.font("Comic Sans MS",20));
            button3.setTextFill(Color.YELLOW);
            button3.setLayoutX(535);
            button3.setLayoutY(381);
            button3.setShape(new Rectangle());
            button3.setOnAction(e3 -> {
                try {
                    if(level>=3){
                        start1(primaryStage,user,3);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Access Denied!");
                        alert.setContentText("You Haven't Completed the Previous Levels yet!!");

                        alert.showAndWait();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            Button button4=new Button();
            button4.setText("4");
            button4.setFont(Font.font("Comic Sans MS",20));
            button4.setTextFill(Color.YELLOW);
            button4.setLayoutX(545);
            button4.setLayoutY(454);
            button4.setShape(new Rectangle());
            button4.setOnAction(e4 -> {
                try {
                    if(level>=4){
                        start1(primaryStage,user,4);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Access Denied!");
                        alert.setContentText("You Haven't Completed the Previous Levels yet!!");

                        alert.showAndWait();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

            Button button5=new Button();
            button5.setText("5");
            button5.setFont(Font.font("Comic Sans MS",20));
            button5.setTextFill(Color.YELLOW);
            button5.setLayoutX(703);
            button5.setLayoutY(479);
            button5.setShape(new Rectangle());
            button5.setOnAction(e5 -> {
                try {
                    if(level>=5){
                        start1(primaryStage,user,5);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Access Denied!");
                        alert.setContentText("You Haven't Completed the Previous Levels yet!!");
                        alert.showAndWait();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

            /////////// Shape Design

            gridPane.getChildren().addAll(button1,button2,button3,button4,button5);
            Scene scene=new Scene(gridPane,900,600);
            primaryStage.setScene(scene);
        }

    }
}

