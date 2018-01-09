package QuizClient;

import Shared.IQuestion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("QuizClient.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        try
        {
            controller = new Controller(this);
        }
        catch (Exception ex)
        {
            System.out.println("Client: Cannot create ClientController");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void addQuestion(IQuestion question)
    {
        //TODO: Set the question in the GUI
    }
}
