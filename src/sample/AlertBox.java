package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.control.*;

/**
 * Builds an GUI acting as an Alert Box that doesn't allow user interaction on other windows until
 * user acknowledges the message by clicking 'Ok'
 * @param title
 * @param message
 * @author David Yeager
 */
public class AlertBox {

    public static void simpleDisplay(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //Doesn't allow user interaction with other windows
        window.setTitle(title); //Sets the window title text desired via the parameter
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(message); //Sets the label text desired via the parameter

        Button closeButton = new Button();
        closeButton.setText("Ok");
        closeButton.setOnAction(e -> window.close()); //Lambda pointing event handler to close window on click

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        window.setScene(new Scene(layout, 300, 100));
        window.show();
    }
}
