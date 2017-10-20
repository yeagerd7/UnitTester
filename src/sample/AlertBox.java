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
 * @author Axolotl Development Team
 */
public class AlertBox {

    private Stage window;
    private VBox layout;
    private Label label;
    private Button closeButton;


    /**
     * Constructor for the AlertBox class
     * @param title The title of the alert
     * @param message The message for the body of the alert
     */
    public static void simpleDisplay(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //Doesn't allow user interaction with other windows
        window.setTitle(title); //Sets the window title text desired via the parameter
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(message); //Sets the label text desired via the parameter

        Button closeButton = new Button();
        closeButton.setPrefSize(70, 20);
        closeButton.setText("Ok");
        closeButton.setOnAction(e -> window.close()); //Lambda pointing event handler to close window on click

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        window.setScene(new Scene(layout, 450, 100));
        window.show();
    }
}
