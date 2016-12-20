package bjNET.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogInScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login_Screen.fxml"));
        primaryStage.setTitle("bjNET");
        primaryStage.setScene(new Scene(root, 250, 175));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
