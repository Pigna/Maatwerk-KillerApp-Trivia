package QuizMaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("QuizMaster.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        try
        {
            controller = new Controller(this);
        }
        catch (Exception ex)
        {
            System.out.println("Master: Cannot create MasterController");
            System.out.println("Master: RemoteException: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }


}
