package bjNET.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;


public class bjNET_Main extends Application  {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("MainScreenLayout.fxml"));
            primaryStage.setTitle("bjNET");
            primaryStage.setScene(new Scene(root, 1200, 800));
            primaryStage.show();
        }
        catch (IOException e) {
            exit(3);
        }

    }
}
