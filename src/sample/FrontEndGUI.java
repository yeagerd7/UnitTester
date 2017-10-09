package sample;

import java.io.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * Class represents the complete front-end GUI(Graphical User Interface) for the C++ Unit Generator.
 * @author David Yeager
 */
public class FrontEndGUI {

    /**
     * Builds the entire front end GUI that acts as the primary user interface
     * @param primaryStage
     */
    public void mainWindowDisplay(Stage primaryStage) throws FileNotFoundException {

        //Scene Initialization
        BorderPane mainScene = new BorderPane();
        HBox topBorderScene = new HBox();
        VBox bottomBorderScene = new VBox();
        HBox bottomSubSceneA = new HBox();
        HBox bottomSubSceneB = new HBox();
        HBox bottomSubSceneC = new HBox();
        HBox bottomSubSceneD = new HBox();
        HBox centerBorderScene = new HBox();
        ListView centerSubSceneA = new ListView<CheckBox>();
        VBox centerSubSceneB = new VBox();

        //Label Initialization
        Label topLabelA = new Label("    C++ UNIT \n TEST GENERATOR ");
        Label topLabelB = new Label();
        Label destinationLabel = new Label();

        //Region Initialization
        Region topRegion = new Region();
        Region bottomRegion = new Region();
        Region centerRegion = new Region();

        //TextField Initialization
        TextField destinationPath = new TextField();

        //Button Initialization and Formatting
        Button browseButton1 = new Button();         browseButton1.setText("Browse");
        browseButton1.setPrefSize(70, 20);
        Button browseButton2 = new Button();         browseButton2.setText("Browse");
        browseButton2.setPrefSize(70, 20);
        Button previewButton = new Button();        previewButton.setText("Preview");
        previewButton.setPrefSize(75, 20);
        Button generateButton = new Button();       generateButton.setText(("Generate"));
        generateButton.setPrefSize(75, 20);
        Button preferencesButton = new Button();    preferencesButton.setText("Preferences");
        preferencesButton.setPrefSize(80, 20);
        Button helpButton = new Button();           helpButton.setText("Help");
        helpButton.setPrefSize(75, 20);

        //Axolotyl Image
        Image image = new Image("CuteLizard.PNG",100, 100,
                false, false);

        //ShadowEffect for Software Name and Axolotyl Image in Top Layer
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.BLACK);
        topLabelA.setEffect(shadow);
        topLabelB.setEffect(shadow);

        //Populate & Format Main Layout
        mainScene.setTop(topBorderScene);
        mainScene.setBottom(bottomBorderScene);
        mainScene.setCenter(centerBorderScene);
        mainScene.setMinWidth(600);
        mainScene.setMinHeight(360);

        //Populate & Format Top Region of Main Layout
        topBorderScene.setPadding(new Insets(15, 12, 15, 0));
        topLabelB.setGraphic(new ImageView(image));
        topLabelB.setPadding(new Insets(15, 12, 15, 0));
        topLabelA.setTextFill(Color.web("#DED8D8"));
        topLabelA.setFont(Font.font("Courier New", FontWeight.BOLD, 48));
        HBox.setHgrow(topRegion, Priority.ALWAYS);
        topBorderScene.setStyle("-fx-background-color: #373747;");
        topBorderScene.getChildren().addAll(topLabelA, topRegion, topLabelB);

        //Populate & Format Bottom Region of Main Layout
        bottomSubSceneA.setPadding(new Insets(15, 12, 15, 12));
        bottomSubSceneA.setSpacing(10);
        bottomSubSceneA.getChildren().addAll(helpButton);
        bottomSubSceneB.setPadding(new Insets(15, 12, 15, 12));
        bottomSubSceneB.setSpacing(10);
        bottomSubSceneB.getChildren().addAll(generateButton, preferencesButton, previewButton);
        bottomSubSceneC.setPadding(new Insets(0, 12, 0, 12));
        bottomSubSceneC.setSpacing(10);
        bottomSubSceneC.getChildren().addAll(bottomSubSceneA, bottomRegion , bottomSubSceneB);
        HBox.setHgrow(bottomRegion, Priority.ALWAYS);
        destinationLabel.setText("Destination: ");
        destinationLabel.setFont(Font.font("Courier New"));
        destinationLabel.setTextFill(Color.web("#DED8D8"));
        destinationPath.setPrefWidth(300);
        bottomSubSceneD.getChildren().addAll(destinationLabel, destinationPath, browseButton2);
        bottomSubSceneD.setSpacing(25);
        bottomSubSceneD.setPadding(new Insets(0, 12, 0, 12));
        bottomSubSceneD.setAlignment(Pos.CENTER);
        bottomBorderScene.setStyle("-fx-background-color: #373747;");
        bottomBorderScene.getChildren().addAll(bottomSubSceneD, bottomSubSceneC);

        //Populate and Format Center Region of Main Layout
        centerBorderScene.setPadding(new Insets(0, 25, 15, 20));
        centerSubSceneA.setMinWidth(430);
        centerSubSceneA = FilePathCheckBoxList(0);
        centerSubSceneB.getChildren().add(browseButton1);
        centerSubSceneB.setAlignment(Pos.TOP_CENTER);
        centerSubSceneB.setPadding(new Insets(0, 20, 15, 40));
        HBox.setHgrow(centerSubSceneA, Priority.ALWAYS);
        centerBorderScene.setStyle("-fx-background-color: #373747;");
        centerBorderScene.getChildren().addAll(centerSubSceneA, centerRegion, centerSubSceneB);

        //Makes window visible
        primaryStage.getIcons().add(new Image("CuteLizard.PNG"));
        primaryStage.setTitle("AxolotylSWENG:        Powered by Rowan University");
        primaryStage.setScene(new Scene(mainScene, 600, 390));
        primaryStage.show();
    }

    /**
     * Private Static Method that will populate and list the file(s) selected in the file or directory selected.
     * in an int value determining the # of files selected dictating whether its a single file selected or an
     * entire directory (CURRENTLY USED FOR TESTING PURPOSES)
     * @param numberOfFiles
     * @return fileList
     */
    private static ListView<CheckBox> FilePathCheckBoxList(int numberOfFiles) {
        ListView<CheckBox> fileList = new ListView<CheckBox>();
        ObservableList<CheckBox> items = FXCollections.observableArrayList();
        CheckBox box;
        if(numberOfFiles == 0) { //Testing Code for Initial GUI presentation
            for(int i = 0; i < 5; i++) {
                box = new CheckBox("C:\\FILEPATH" + (i + 1));
                box.setSelected(true);
                items.add(box);
            }
            fileList.setItems(items);
        }
        else {

        }
        fileList.setPadding(new Insets(0, 25, 15, 20));
        fileList.setMinWidth(430);
        return fileList;
    }
}
