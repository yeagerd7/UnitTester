package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.control.*;

/**
 * Builds a GUI acting as an Alert Box that doesn't allow user interaction on other windows until
 * user acknowledges the message by clicking 'Ok'
 * @author Axolotl Development Team
 */
public class AlertBox {

    /**
     * Static Method that builds an Alert Box that handles most of the error/warning scenarios that can be caused
     * by the user
     * @param message The message for the body of the alert
     */
    public static void simpleDisplay(String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //Doesn't allow user interaction with other windows
        window.setTitle("Error"); //Sets the window title text desired via the parameter
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

        window.setScene(new Scene(layout, 475, 75));
        window.setResizable(false);
        window.getIcons().add(new Image("warning.png"));
        window.show();
    }

    /**
     * Static method that displays an error message showing the user the list of files that are duplicates and that
     * they will not be processed.
     * @param duplicates List of Strings representing the duplicate files
     */
    public static void duplicateSourceFilesErrorDisplay(ListView<String> duplicates) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //Doesn't allow user interaction with other windows
        window.setTitle("Error: Duplicate Files Detected"); //Sets the window title text desired via the parameter
        window.setMinWidth(300);

        Label label = new Label();
        label.setText("The following files are duplicates and will not be processed");

        duplicates.setFocusTraversable(false);

        Button closeButton = new Button();
        closeButton.setPrefSize(70, 20);
        closeButton.setText("Ok");
        closeButton.setOnAction(e -> window.close()); //Lambda pointing event handler to close window on click

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, duplicates, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,20,10,20));

        window.setScene(new Scene(layout, 450, 300));
        window.setResizable(false);
        window.getIcons().add(new Image("warning.png"));
        window.show();
    }
}