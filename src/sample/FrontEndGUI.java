package sample;

//Import Statements
import java.io.*;
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
 * @author Axolotl Development Team
 */
public class FrontEndGUI {

    //Button Field Declarations
    private Button browseButton1;
    private Button browseButton2;
    private Button preferencesButton;
    private Button helpButton;
    private Button generateButton;
    private Button previewButton;
    private Button refreshButton;

    //Main Layout Field Declaration
    private BorderPane mainScene;

    //Bottom Border Scene Field Declarations
    private VBox bottomBorderScene;
    private HBox bottomSubSceneA;
    private HBox bottomSubSceneB;
    private HBox bottomSubSceneC;
    private HBox bottomSubSceneD;
    private HBox bottomSubSceneF;

    //Top Border Scene Field Declaration
    private HBox topBorderScene;

    //Center Border Scene Field Declarations
    private HBox centerBorderScene;
    private ListView<CheckBox> centerSubSceneA;
    private VBox centerSubSceneB;
    private HBox centerSubSceneC;
    private HBox centerSubSceneD;

    //Label Field Declarations
    private Label topLabelA;
    private Label topLabelB;
    private Label destinationLabel;

    //Region Field Declarations
    private Region topRegion;
    private Region centerRegion;
    private Region bottomRegion;

    //TextField  Field Declaration
    private  TextField destinationPath;

    //Image Field Declaration
    private Image image;

    //Controller Field Declaration
    private Controller controller;

    /**
     * Constructor that initializes all the attributes(fields) of the FrontEndGUI class
     */
    public FrontEndGUI() {
        mainScene = new BorderPane();
        topBorderScene = new HBox();
        bottomBorderScene = new VBox();
        bottomSubSceneA = new HBox();
        bottomSubSceneB = new HBox();
        bottomSubSceneC = new HBox();
        bottomSubSceneD = new HBox();
        bottomSubSceneF = new HBox();
        centerBorderScene = new HBox();
        centerSubSceneA = new ListView<>();
        centerSubSceneB = new VBox();
        centerSubSceneC = new HBox();
        centerSubSceneD = new HBox();

        //Label Initialization
        topLabelA = new Label("    C++ UNIT \n TEST GENERATOR ");
        topLabelB = new Label();
        destinationLabel = new Label();

        //Region Initialization
        topRegion = new Region();
        centerRegion = new Region();
        bottomRegion = new Region();

        //TextField Initialization
        destinationPath = new TextField();

        //Button Initialization
        browseButton1 = new Button();
        browseButton2 = new Button();
        previewButton = new Button();
        generateButton = new Button();
        preferencesButton = new Button();
        helpButton = new Button();
        refreshButton = new Button();

        //Axolotyl Image Initialization and Formatting
        image = new Image("CuteLizard.PNG",100, 100,
                false, false);

        //Controller Initialization (Singleton)
        controller = Controller.getInstance();
    }

    /**
     * Builds and formats the entire front end GUI (Graphical User Interface) that interacts with the user
     * @param primaryStage Main window of program
     */
    public void mainWindowDisplay(Stage primaryStage) throws FileNotFoundException {
        //Button Formatting
        browseButton1.setText("Browse");
        browseButton1.setPrefSize(70, 20);
        browseButton2.setText("Browse");
        browseButton2.setPrefSize(70, 20);
        helpButton.setText("Help");
        helpButton.setPrefSize(75, 20);
        previewButton.setText("Preview");
        previewButton.setPrefSize(75, 20);
        preferencesButton.setText("Preferences");
        preferencesButton.setPrefSize(80, 20);
        generateButton.setText("Generate");
        generateButton.setPrefSize(75, 20);
        refreshButton.setText("Refresh");
        refreshButton.setPrefSize(70, 20);

        //ShadowEffect for Program Name and Axolotyl Image in Top Layer
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
        bottomSubSceneC.getChildren().addAll(bottomSubSceneA, bottomRegion, bottomSubSceneB);
        HBox.setHgrow(bottomRegion, Priority.ALWAYS);
        destinationLabel.setText("Destination: ");
        destinationLabel.setFont(Font.font("Courier New"));
        destinationLabel.setTextFill(Color.web("#DED8D8"));
        //destinationPath.setEditable(false);
        HBox.setHgrow(destinationPath, Priority.ALWAYS);
        bottomSubSceneF.getChildren().add(browseButton2);
        bottomSubSceneF.setPadding(new Insets(0, 33, 0,13));
        bottomSubSceneF.setAlignment(Pos.CENTER);
        bottomSubSceneD.getChildren().addAll(destinationLabel, destinationPath, bottomSubSceneF);
        bottomSubSceneD.setSpacing(25);
        bottomSubSceneD.setPadding(new Insets(0, 12, 0, 20));
        bottomSubSceneD.setAlignment(Pos.CENTER);
        bottomBorderScene.setStyle("-fx-background-color: #373747;");
        bottomBorderScene.getChildren().addAll(bottomSubSceneD, bottomSubSceneC);

        //Populate and Format Center Region of Main Layout
        centerBorderScene.setPadding(new Insets(0, 25, 15, 20));
        centerSubSceneA.setPrefWidth(460);
        centerSubSceneA.setMinWidth(460);
        centerSubSceneC.getChildren().add(browseButton1);
        centerSubSceneC.setAlignment((Pos.CENTER));
        centerSubSceneC.setPadding(new Insets(0, 0, 10, 0));
        centerSubSceneD.getChildren().add(refreshButton);
        centerSubSceneD.setAlignment((Pos.CENTER));
        centerSubSceneD.setPadding(new Insets(10, 0, 0, 0));
        centerSubSceneB.getChildren().addAll(centerSubSceneC, centerSubSceneD);
        centerSubSceneB.setAlignment(Pos.TOP_CENTER);
        centerSubSceneB.setPadding(new Insets(0, 20, 15, 40));
        HBox.setHgrow(centerSubSceneA, Priority.ALWAYS);
        centerBorderScene.setStyle("-fx-background-color: #373747;");
        centerBorderScene.getChildren().addAll(centerSubSceneA, centerRegion, centerSubSceneB);

        //Makes window visible
        primaryStage.getIcons().add(new Image("CuteLizard.PNG"));
        primaryStage.setTitle("AxolotylSWENG:        Powered by Rowan University");
        primaryStage.setScene(new Scene(mainScene, 630, 390));
        primaryStage.show();

        Main.LOGGER.info("Front End User Interface built and displayed");

        //Internal Method call to handle all action listeners
        handleButtons();
    }

    /**
     * Private Internal Method that handles all Action Listeners for clicks on all buttons on Front End GIU (Graphical
     * User Interface)
     */
    private void handleButtons() {
        /*
        Action Listener for the 'Browse' Button that makes a call to sourceBrowse() in the Controller class that
        populates CenterSubSceneA with .cpp files selected by the user.  Each file is given a checkbox that also
        displays the file's specific filepath.
         */
        browseButton1.setOnAction(event -> {
            centerSubSceneA = controller.sourceBrowse(centerSubSceneA);
            centerSubSceneA.setPadding(new Insets(0, 0, 0, 10));
            centerSubSceneA.setPrefWidth(430);
        });

        /*
        Action Listener for the 'Browse' button that makes a external method call to destinationBrowse() in the Controller
        class that populates the destinationPath with the specific filepath of the directory/folder selected by the user
        for output.
         */
        browseButton2.setOnAction(event -> {
            File destination = controller.destinationBrowse();
            if(destination != null) {
                destinationPath.setText(destination.getAbsolutePath());
            }
        } );
        /*
        Action Listener for the 'Refresh' button that makes a external method call to refreshSourceFiles in the
        Controller class that will remove the files deselected by the user as well as updating the Controller's
        sourceFile set instance variable
         */
        refreshButton.setOnAction(event -> {
            if(centerSubSceneA.getItems().isEmpty()) {
                //TEST
                AlertBox.simpleDisplay("Alert", "Nothing to refresh!");
            }
            else {
                centerSubSceneA = controller.refreshSourceFiles(centerSubSceneA);
                centerSubSceneA.setPadding(new Insets(0, 0, 0, 10));
                centerSubSceneA.setPrefWidth(430);
            }
        });
        /*
        Action Listener for the 'Generate' button. Does not fully generate the files yet, but ather tests the file
        parsing functionality
         */
        generateButton.setOnAction(event -> {
            File[] parsingFiles = new File[controller.getSourceFiles().size()];
            controller.getSourceFiles().toArray(parsingFiles);
            try {
                FileParser.parseFiles("Test", parsingFiles);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
