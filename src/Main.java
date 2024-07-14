import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("LOG IN Or SIGN UP");

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Manager manager = new Manager();
        InputProcessor inputProcessor = new InputProcessor(manager);
        //inputProcessor.run(primaryStage);
        inputProcessor.menu(primaryStage);
    }
}
