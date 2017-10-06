package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import javafx.scene.control.*;

public class Main extends Application {

    Button browseButton;

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //Build Main Window
        primaryStage.setTitle("C++ Unit Tester");

        //Browse Button
        browseButton = new Button();
        browseButton.setText("Browse");
        browseButton.setOnAction(e -> {
            AlertBox.display("ERROR", "Error Message: Click 'Ok' to continue");
        });   //Incomplete: Expecting a Method Call for Browse Functionality

        StackPane layout = new StackPane();
        layout.getChildren().add(browseButton);

        //Makes window visible
        primaryStage.setScene(new Scene(layout, 700, 600));
        primaryStage.show();
    }
}