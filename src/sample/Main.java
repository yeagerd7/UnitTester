package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import javafx.scene.control.*;

public class Main extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FrontEndGUI mainWindow = new FrontEndGUI();
        mainWindow.mainWindowDisplay(primaryStage);
    }
}