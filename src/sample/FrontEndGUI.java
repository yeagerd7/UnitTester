package sample;

//Import Statements
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.FXCollections;
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
    //Stage Field Declaration
    private Stage window;

    //Button Field Declarations
    private Button browseButton1;
    private Button browseButton2;
    private Button preferencesButton;
    private Button helpButton;
    private Button generateButton;
    private Button previewButton;
    private Button refreshButton;
    private Button selectAllButton;
    private Button deselectAllButton;
    private Button defaultsButton;

    //Main Layout Field Declaration
    private BorderPane mainScene;

    //Bottom Border Scene Field Declarations
    private VBox bottomBorderScene;
    private HBox bottomSubSceneA;
    private HBox bottomSubSceneB;
    private HBox bottomSubSceneC;
    private HBox bottomSubSceneD;
    private VBox bottomSubSceneF;

    //Top Border Scene Field Declaration
    private HBox topBorderScene;

    //Center Border Scene Field Declarations
    private HBox centerBorderScene;
    private ListView<CheckBox> centerSubSceneA;
    private VBox centerSubSceneB;
    private HBox centerSubSceneC;
    private HBox centerSubSceneD;
    private HBox centerSubSceneE;
    private HBox centerSubSceneF;

    //Label Field Declarations
    private Label topLabelA;
    private Label topLabelB;
    private Label destinationLabel;

    //Region Field Declarations
    private Region topRegionA;
    private Region topRegionB;
    private Region centerRegion;
    private Region bottomRegion;

    //TextField  Field Declaration
    private  TextField destinationPath;

    //Image Field Declaration
    private Image image;

    //ToolTip Field Declaration
    private Tooltip sourceBrowseTip;
    private Tooltip destinationBrowseTip;
    private Tooltip refreshTip;

    //Shadow Effect Field Declaration
    private DropShadow shadow;

    //Default Path Choice Box Field Declaration
    private ChoiceBox<String> defaultPaths;

    //Serialized Default Preference Field Declaration;
    private ArrayList<ChoiceBox<String>> preferences;

    //Toggles field declaration/initialization
    private ArrayList<Boolean> toggles;

    //manageSubSceneA field declaration/initialization
    private ListView<CheckBox> manageSubSceneA;

    //Controller Field Declaration
    private Controller controller;

    /**
     * Constructor that initializes all the attributes(fields) of the FrontEndGUI class
     */
    public FrontEndGUI() {
        //Controller Initialization (Singleton)
        controller = Controller.getInstance();

        //Scene initializations
        mainScene = new BorderPane();
        topBorderScene = new HBox();
        bottomBorderScene = new VBox();
        bottomSubSceneA = new HBox();
        bottomSubSceneB = new HBox();
        bottomSubSceneC = new HBox();
        bottomSubSceneD = new HBox();
        bottomSubSceneF = new VBox();
        centerBorderScene = new HBox();
        centerSubSceneA = new ListView<>();
        centerSubSceneB = new VBox();
        centerSubSceneC = new HBox();
        centerSubSceneD = new HBox();
        centerSubSceneE = new HBox();
        centerSubSceneF = new HBox();

        //Label Initialization
        topLabelA = new Label("    C++ UNIT \n TEST GENERATOR ");
        topLabelB = new Label();
        destinationLabel = new Label();

        //Region Initialization
        topRegionA = new Region();
        topRegionB = new Region();
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
        selectAllButton = new Button();
        deselectAllButton = new Button();
        defaultsButton = new Button();

        //Axolotl Image Initialization and Formatting
        image = new Image("CuteLizard.PNG", 100, 100,
                false, false);

        //ToolTip Initialization/Formatting
        sourceBrowseTip = new Tooltip();
        destinationBrowseTip = new Tooltip();
        refreshTip = new Tooltip();

        /*
         * Initializes the ListView object and defaultPaths  that represents the list of preferred destination paths
         * and loads the check list coinciding with the serialized user preference settings defined in the Preferences
         * class
         */
        manageSubSceneA = new ListView<>();
        defaultPaths = new ChoiceBox<>();
        Iterator<File> itty = controller.setPreferredDestinations().iterator();
        while(itty.hasNext()) {
            File destination = itty.next();
            CheckBox box = new CheckBox();
            box.setText(destination.getAbsolutePath());
            box.setSelected(true);
            manageSubSceneA.getItems().add(box);
            defaultPaths.getItems().add(destination.getAbsolutePath());
            defaultPaths.getSelectionModel().selectFirst();
        }

        /*
         * Initializes the ArrayList object that represents the list of preferences in the for of booleans which
         * determine if a preference is turned on or off and loads the check toggle switches (Off/On) coinciding list
         * coinciding with the serialized user preference settings defined in the Preferences class.
         */
        preferences = new ArrayList<>();
        toggles = controller.setToggles();
        for (int i = 0; i < 7; i++) {
            ChoiceBox<String> preference = new ChoiceBox<>(FXCollections.observableArrayList("Off", "On"));
            preferences.add(preference);
            if(toggles.get(i) == false) {
                preferences.get(i).getSelectionModel().selectFirst();
            }
            else{
                preferences.get(i).getSelectionModel().selectLast();
            }
        }

        //Shadow Effect initialization and formatting
        shadow = new DropShadow();
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.BLACK);
    }


    /**
     * Builds and formats the entire front end GUI (Graphical User Interface) that interacts with the user
     * @param primaryStage Main window of program
     */
    public void mainWindowDisplay(Stage primaryStage) throws FileNotFoundException {
        window = primaryStage;
        //Button Formatting
        browseButton1.setText("Browse");
        browseButton1.setPrefSize(78, 20);
        browseButton2.setText("Browse");
        browseButton2.setPrefSize(78, 20);
        helpButton.setText("Help");
        helpButton.setPrefSize(78, 20);
        previewButton.setText("Preview");
        previewButton.setPrefSize(78, 20);
        preferencesButton.setText("Preferences");
        preferencesButton.setPrefSize(80, 20);
        generateButton.setText("Generate");
        generateButton.setPrefSize(78, 20);
        refreshButton.setText("Refresh");
        refreshButton.setPrefSize(78, 20);
        selectAllButton.setText("Select All");
        selectAllButton.setPrefSize(78, 20);
        deselectAllButton.setText("Deselect All");
        deselectAllButton.setPrefSize(78, 20);
        defaultsButton.setText("Defaults");
        defaultsButton.setPrefSize(78, 20);

        //ToolTip insertion
        sourceBrowseTip.setText("Search for .cpp \nand .h source files");
        browseButton1.setTooltip(sourceBrowseTip);
        destinationBrowseTip.setText("Search for or enter in \nyour destination directory");
        browseButton2.setTooltip(destinationBrowseTip);
        refreshTip.setText("Remove all \nunselected files");
        refreshButton.setTooltip(refreshTip);

        //ShadowEffect for Program Name and Axolotyl Image in Top Layer
        topLabelA.setEffect(shadow);
        topLabelB.setEffect(shadow);

        //Populate & Format Main Layout
        mainScene.setTop(topBorderScene);
        mainScene.setBottom(bottomBorderScene);
        mainScene.setCenter(centerBorderScene);

        //Populate & Format Top Region of Main Layout
        topBorderScene.setPadding(new Insets(15, 12, 15, 0));
        topLabelB.setGraphic(new ImageView(image));
        topLabelB.setPadding(new Insets(15, 12, 15, 0));
        topLabelA.setTextFill(Color.web("#DED8D8"));
        topLabelA.setFont(Font.font("Courier New", FontWeight.BOLD, 60));
        HBox.setHgrow(topRegionB, Priority.ALWAYS);
        HBox.setHgrow(topRegionA, Priority.ALWAYS);
        topBorderScene.setStyle("-fx-background-color: #373747;");
        topBorderScene.getChildren().addAll(topRegionB, topLabelA, topRegionA, topLabelB);

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
        destinationLabel.setText("Destination:");
        destinationLabel.setFont(Font.font("Courier New"));
        destinationLabel.setTextFill(Color.web("#DED8D8"));
        destinationPath.setFocusTraversable(false);
        HBox.setHgrow(destinationPath, Priority.ALWAYS);
        bottomSubSceneF.getChildren().addAll(browseButton2, defaultsButton);
        bottomSubSceneF.setSpacing(10);
        bottomSubSceneF.setPadding(new Insets(0, 12, 0,35));
        bottomSubSceneF.setAlignment(Pos.CENTER);
        bottomSubSceneD.getChildren().addAll(destinationLabel, destinationPath, bottomSubSceneF);
        bottomSubSceneD.setSpacing(5);
        bottomSubSceneD.setPadding(new Insets(0, 12, 0, 20));
        bottomSubSceneD.setAlignment(Pos.CENTER);
        bottomBorderScene.setStyle("-fx-background-color: #373747;");
        bottomBorderScene.getChildren().addAll(bottomSubSceneD, bottomSubSceneC);

        //Populate and Format Center Region of Main Layout
        centerBorderScene.setPadding(new Insets(0, 25, 15, 20));
        centerSubSceneA.setPrefWidth(455);
        centerSubSceneA.setMinWidth(455);
        centerSubSceneA.setFocusTraversable(false);
        centerSubSceneC.getChildren().add(browseButton1);
        centerSubSceneC.setAlignment((Pos.CENTER));
        centerSubSceneC.setPadding(new Insets(0, 0, 10, 0));
        centerSubSceneD.getChildren().add(refreshButton);
        centerSubSceneD.setAlignment((Pos.CENTER));
        centerSubSceneD.setPadding(new Insets(15, 0, 10, 0));
        centerSubSceneE.getChildren().add(selectAllButton);
        centerSubSceneE.setAlignment((Pos.CENTER));
        centerSubSceneE.setPadding(new Insets(0, 0, 10, 0));
        centerSubSceneF.getChildren().add(deselectAllButton);
        centerSubSceneF.setAlignment((Pos.CENTER));
        centerSubSceneF.setPadding(new Insets(0, 0, 0, 0));
        centerSubSceneB.getChildren().addAll(centerSubSceneC, centerSubSceneD, centerSubSceneE, centerSubSceneF);
        centerSubSceneB.setAlignment(Pos.TOP_CENTER);
        centerSubSceneB.setPadding(new Insets(0, 0, 15, 40));
        HBox.setHgrow(centerSubSceneA, Priority.ALWAYS);
        centerBorderScene.setStyle("-fx-background-color: #373747;");
        centerBorderScene.getChildren().addAll(centerSubSceneA, centerRegion, centerSubSceneB);

        //Makes window visible
        window.getIcons().add(new Image("CuteLizard.PNG"));
        window.setTitle("AxolotlSWENG:        Powered by Rowan University");
        window.setScene(new Scene(mainScene, 800, 500));
        mainScene.setMinWidth(700);
        mainScene.setMinHeight(430);
        window.show();

        Main.LOGGER.info("Front End User Interface built and displayed");

        //Internal Method call to handle all action listeners
        handleButtons();

        /*
        Serializes the preferences so they can me loaded up by default the next time the program loads up
         */
        window.setOnCloseRequest(event -> {
            controller.serializePreference();
        });
    }

    /**
     * Private display method tha displays the 'Help' Window that will assist users if they have any problems or
     * questions regarding the program.
     */
    private void buildHelpWindow() {
        BorderPane browseWindow1 = new BorderPane();
        TabPane tabpane = new TabPane();
        HBox browseTopScene = new HBox();

        //Declarations for the tabs
        Tab buttonTab = new Tab();
        Tab makeTab = new Tab();
        Tab unitTab = new Tab();
        Tab testTab = new Tab();
        Tab walkthroughTab = new Tab();

        walkthroughTab.setText("Program Walkthrough");
        buttonTab.setText("Buttons");
        makeTab.setText("Makefile");
        unitTab.setText("Unit Test File");
        testTab.setText("Test Fixture");
        tabpane.getTabs().addAll(walkthroughTab, buttonTab, makeTab, unitTab, testTab);
        tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        /*
         * Code for the buttons tab
         */
        //Separate HBoxes were declared for each button, so each VBox element has an HBox
        HBox upperBrowseBox = new HBox();
        upperBrowseBox.setPadding(new Insets(15, 12, 15, 12));
        upperBrowseBox.setSpacing(10);
        HBox generateBox = new HBox();
        generateBox.setPadding(new Insets(15, 12, 15, 12));
        generateBox.setSpacing(10);
        HBox preferencesBox = new HBox();
        preferencesBox.setPadding(new Insets(15, 12, 15, 12));
        preferencesBox.setSpacing(10);
        HBox previewButtonBox = new HBox();
        previewButtonBox.setPadding(new Insets(15, 12, 15, 12));
        previewButtonBox.setSpacing(10);
        HBox refreshButtonBox = new HBox();
        refreshButtonBox.setPadding(new Insets(15, 12, 15, 12));
        refreshButtonBox.setSpacing(10);
        VBox buttonBox = new VBox();

        //Images used in the button tab
        ImageView bImage = new ImageView();
        Image browseImage = new Image("Browse.JPG");
        ImageView pImage = new ImageView();
        Image previewImage = new Image("preview.JPG");
        ImageView rImage = new ImageView();
        Image refreshImage = new Image("refresh.JPG");
        ImageView gImage = new ImageView();
        Image generateImage = new Image("generate.JPG");
        ImageView prImage = new ImageView();
        Image preferencesImage = new Image("preferences.JPG");
        prImage.setImage(preferencesImage);
        pImage.setImage(previewImage);
        bImage.setImage(browseImage);
        rImage.setImage(refreshImage);
        gImage.setImage(generateImage);

        //Labels for each button
        Label upperBrowse = new Label("Upper Browse Button");
        upperBrowse.setWrapText(true);
        Label browseInfo = new Label(  "Browse Buttons: " + "\n" + "With the upper browse button, you " +
                "can select " + "filepaths/files that are to be tested." + " It should pull up a typical browse " +
                "window. With the lower browse button, " + "you can select where you like to save the generated " +
                "file.  ");
        upperBrowseBox.getChildren().addAll(bImage, browseInfo);
        Label generateInfo = new Label("Generate Button:" + "\n" + "Button to select when the user is ready " +
                "to run the unit test.");
        Label preferencesInfo = new Label( "Preferences Button:" + "\n" + "The user will have options they can" +
                " opt to use for the unit test. " );
        Label previewInfo = new Label("Preview Button:" + "\n" + "The user will be able to preview information" +
                " they have entered into the program. " );
        Label refreshInfo = new Label("Refresh Button: " + "\n" + "Selecting this button will effectively " +
                "clear the files the user had loaded into the program. The refresh button does not clear the " +
                "information loaded for the destination folder.");

        //Wraps text so if the window is resized, the label will work with the new window dimensions
        browseInfo.setWrapText(true);
        previewInfo.setWrapText(true);
        generateInfo.setWrapText(true);
        preferencesInfo.setWrapText(true);
        refreshInfo.setWrapText(true);

        previewButtonBox.getChildren().addAll(pImage, previewInfo);
        generateBox.getChildren().addAll(gImage, generateInfo);
        preferencesBox.getChildren().addAll(prImage, preferencesInfo);
        refreshButtonBox.getChildren().addAll(rImage,refreshInfo);
        ScrollPane buttonScroll = new ScrollPane();
        buttonBox.getChildren().addAll(upperBrowseBox, generateBox, preferencesBox, previewButtonBox, refreshButtonBox);

        buttonBox.setPadding(new Insets(15, 12, 15, 12));
        buttonScroll.setContent(buttonBox);
        buttonScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        buttonScroll.setFitToWidth(true);
        buttonTab.setContent(buttonScroll);
        browseTopScene.setStyle("-fx-background-color: #373747;");
        browseWindow1.setCenter(tabpane);

        Stage stage = new Stage();
        stage.setTitle("Help");
        stage.setScene(new Scene(browseWindow1, 600, 400));
        stage.show();

        /*
         * Code for the makefile tab
         */
        ScrollPane makeScroll = new ScrollPane();

        VBox makefileBox = new VBox();
        makefileBox.setPadding(new Insets(15, 12, 15, 12));
        makefileBox.setSpacing(10);

        Label makeFileInfo = new Label("We use makefiles to tell the compiler which sources files to " +
                "compile; it can also name executable files and send them to a specific location." + "\n" + "\n" +
                "Cpp files tend to have a corresponding .h file, which has function prototypes, class declarations, " +
                "and external variables. Cpp files consist of method implementations, standalone functions, and " +
                "global variables." + "\n" + "\n" + "There will be more supporting information here involving the " +
                "specific information for our makefiles.");
        Label makeFileSyntax = new Label("Makefile Syntax:" + "\n" + "--");
        makeFileInfo.setWrapText(true);
        makeFileSyntax.setWrapText(true);
        makeScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        makeScroll.setFitToWidth(true);

        makefileBox.getChildren().addAll(makeFileInfo, makeFileSyntax);
        makeScroll.setContent(makefileBox);
        makeTab.setContent(makeScroll);

        /*
         * Code for unit test tab. TAB >> scrollpane >> vbox >> components
         */
        ScrollPane unitScroll = new ScrollPane();
        VBox unitFileBox = new VBox();
        unitFileBox.setPadding(new Insets(15, 12, 15, 12));
        unitFileBox.setSpacing(10);

        Label unitFileInfo = new Label("This is the tab for the unit test file information. It's pretty " +
                "straight forward, will include what parameters the test file will need to have in order to " +
                "function properly. Using the browse button, you will be able to see that it only searches for" +
                ".Cpp files. ");
        unitFileInfo.setWrapText(true);
        unitFileBox.getChildren().addAll(unitFileInfo);
        unitScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        unitScroll.setFitToWidth(true);
        unitScroll.setContent(unitFileBox);
        unitTab.setContent(unitScroll);

        /*
         * Code for test fixture tab
         */
        ScrollPane testScroll = new ScrollPane();
        VBox testfileBox = new VBox();
        testfileBox.setPadding(new Insets(15, 12, 15, 12));
        testfileBox.setSpacing(10);
        Label testFixtureInfo = new Label("Tab that will contain the complete test fixture information." +
                "Will be updated periodically as we complete the project.");
        testFixtureInfo.setWrapText(true);
        testfileBox.getChildren().addAll(testFixtureInfo);
        testScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        testScroll.setFitToWidth(true);
        testScroll.setContent(testfileBox);
        testTab.setContent(testScroll);

        /*
         * Code for program walkthrough tab
         */
        ScrollPane walkScroll = new ScrollPane();
        ImageView gMenu = new ImageView();
        Image generatorMenu = new Image("generator.JPG");
        gMenu.setImage(generatorMenu);
        gMenu.setFitHeight(220);
        gMenu.setFitWidth(290);
        HBox walkthroughGenerator = new HBox();
        VBox walkBox = new VBox();
        walkBox.setPadding(new Insets(15, 12, 15, 12));
        walkBox.setSpacing(10);
        Label walkthroughInfo1 = new Label("The walkthrough tab will consist of the steps necessary to" +
                " complete a run of the program with a better understanding of what does what. The user can start" +
                "by hitting the browse button to locate a .Cpp file (or files), and once that is selected it should " +
                "appear " + "in the white box. ");
        Label walkthroughInfo2 = new Label("Next, a destination folder must be selected. The destination box " +
                "IS editable," + " so if you had a specific location already in mind you would be able to key it in. " +
                "If there" + " isn't a selected folder, the program currently will open an alert box. The refresh " +
                "button will" + "clear the white box of selected files but not the destination folder." + "\n" + "\n" +
                "(key notes on preferences)" + "\n" + "\n" + "Finally, when the correct files are selected and the " +
                "preferences are set, the user can hit generate. ");
        walkthroughInfo1.setWrapText(true);
        walkthroughInfo2.setWrapText(true);
        walkthroughGenerator.getChildren().addAll(walkthroughInfo1, gMenu);
        walkBox.getChildren().addAll(walkthroughGenerator, walkthroughInfo2);
        walkScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        walkScroll.setFitToWidth(true);
        walkScroll.setContent(walkBox);
        walkthroughTab.setContent(walkScroll);
    }

    /**
     * Builds the 'Generate' Window which alerts the user that the program has completed execution and that output
     * files are in their selected directory.  The user is given the choice to either return to the main menu, or close
     * the program, completely finishing program execution
     */
    private void buildGenerateWindow() {
        //Generate window layout declaration and initialization
        BorderPane generateLayout = new BorderPane();

        //Generate window scene declarations and initializations
        HBox generateTopScene = new HBox();
        VBox generateTopSceneA = new VBox();
        HBox generateTopSceneB = new HBox();
        HBox generateTopSceneC = new HBox();
        HBox generateCenterScene = new HBox();
        HBox generateCenterSubSceneA = new HBox();
        HBox generateCenterSubSceneB = new HBox();

        //Generate window label declarations and initializations
        Label topSceneLabelA = new Label();
        Label topSceneLabelB = new Label();

        //Generate window region declaration and initialization
        Region topRegion = new Region();

        //Generate window button declarations and initializations
        Button closeButton = new Button();
        Button mainMenuButton = new Button();
        Button destinationOutput = new Button();

        //Generate window image declaration and initialization
        Image partyLizard = new Image("HappyLizard.JPG",200, 200,
                false, false);

        //Generate Window button formatting
        mainMenuButton.setText("Main Menu");
        mainMenuButton.setPrefSize(80, 20);
        closeButton.setText("Close");
        closeButton.setPrefSize(80, 20);
        destinationOutput.setText("Destination");
        destinationOutput.setPrefSize(80, 20);

        //ShadowEffect for Program Name and Axolotl Image in Top Layer
        topSceneLabelB.setEffect(shadow);

        //Top Scene formatting
        topSceneLabelA.setText("      Success! \n\n Output files have \n been sent to your \n selected directory!");
        topSceneLabelA.setTextFill(Color.web("#DED8D8"));
        topSceneLabelA.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        topSceneLabelA.setPadding(new Insets(25, 0, 15, 20));
        topSceneLabelB.setGraphic(new ImageView(partyLizard));
        topSceneLabelB.setPadding(new Insets(25, 20, 15, 20));
        generateTopSceneC.getChildren().add(destinationOutput);
        generateTopSceneC.setPadding(new Insets(15, 0, 15, 15));
        generateTopSceneC.setAlignment(Pos.CENTER);
        generateTopSceneB.getChildren().add(topSceneLabelB);
        generateTopSceneB.setPadding(new Insets(0, 10, 0, 0));
        generateTopSceneA.getChildren().addAll(topSceneLabelA, generateTopSceneC);
        generateTopSceneA.setAlignment(Pos.CENTER);
        generateTopSceneA.setPadding(new Insets(0, 0, 0, 20));
        HBox.setHgrow(topRegion, Priority.ALWAYS);
        generateTopScene.getChildren().addAll(generateTopSceneA, topRegion,generateTopSceneB);

        //Center Scene formatting
        generateCenterSubSceneA.getChildren().add(mainMenuButton);
        generateCenterSubSceneA.setPadding(new Insets(15, 12, 15, 12));
        generateCenterSubSceneB.getChildren().add(closeButton);
        generateCenterSubSceneB.setPadding(new Insets(15, 12, 15, 0));
        generateCenterScene.getChildren().addAll(generateCenterSubSceneA, generateCenterSubSceneB);
        generateCenterScene.setAlignment(Pos.CENTER);
        generateCenterScene.setStyle("-fx-background-color: #373747;");
        generateTopScene.setStyle("-fx-background-color: #373747;");

        //Generate window layout formatting
        generateLayout.setTop(generateTopScene);
        generateLayout.setCenter(generateCenterScene);

        //Generate window formatting
        Stage stage = new Stage();
        stage.setTitle("AxolotlSWENG:        Powered by Rowan University");
        stage.setScene(new Scene(generateLayout, 530, 300));
        stage.setResizable(false);
        stage.getIcons().add(new Image("CuteLizard.PNG"));
        stage.show();
        /*
        Action Listener for the Generate window that closes this window and ends program execution
         */
        closeButton.setOnAction(event -> {
            controller.serializePreference();
            stage.close();
            window.close();
        });
        /*
        Action Listener for the Generate window that closes this window and returns the user back to the main menu
         */
        mainMenuButton.setOnAction(event -> {
            stage.close();
            controller.clearController();
            controller.deselectAllSourceFiles(centerSubSceneA);
            controller.refreshSourceFiles(centerSubSceneA);
            destinationPath.clear();
            window.show();
        });
         /*
        Action Listener for the Generate window that takes the user to their destination directory
         */
        destinationOutput.setOnAction(event -> {
            controller.openDestinationDirectory();
        });
    }

    /**
     * Builds the preview window that allows the user to see the resulting makefile, unit test and test fixture
     * resulting from a small, simple program run.
     */
    private void buildPreviewWindow() {
        //Preview window BorderPane layout declaration and initialization
        BorderPane previewLayout = new BorderPane();

        //Preview window bottom scene/sub scene declaration/initialization and formatting
        HBox bottomPreviewScene = new HBox();
        bottomPreviewScene.setPadding(new Insets(15, 20, 15, 20));
        bottomPreviewScene.setStyle("-fx-background-color: #373747;");
        HBox bottomPreviewSubSceneA = new HBox();
        HBox bottomPreviewSubSceneB = new HBox();

        Region previewRegion= new Region();

        //Preview window bottom label declaration/initialization and formatting
        Label bottomPreviewLabel = new Label();
        bottomPreviewLabel.setText("Sample File:  ");
        bottomPreviewLabel.setFont(Font.font("Courier New"));
        bottomPreviewLabel.setTextFill(Color.web("#DED8D8"));

        Button sampleFileButton = new Button();
        sampleFileButton.setText("Assignment.cpp");
        sampleFileButton.setPrefSize(120, 20);
        Button previewCloseButton = new Button();
        previewCloseButton.setText("Close");
        previewCloseButton.setPrefSize(80, 20);

        //Populating Bottom Preview Window Scene
        HBox.setHgrow(previewRegion, Priority.ALWAYS);
        bottomPreviewSubSceneA.getChildren().addAll(bottomPreviewLabel, sampleFileButton);
        bottomPreviewSubSceneB.getChildren().addAll(previewCloseButton);
        bottomPreviewScene.getChildren().addAll(bottomPreviewSubSceneA, previewRegion, bottomPreviewSubSceneB);

        //Preview window Tab/Tab Pane declaration/initialization and formatting
        Tab makefileTab = new Tab();
        Tab unitTestTab = new Tab();
        Tab testFixtureTab = new Tab();
        TabPane previewTabPane = new TabPane();
        makefileTab.setText("Makefile");
        unitTestTab.setText("Unit Test");
        testFixtureTab.setText("Test Fixture");
        previewTabPane.getTabs().addAll(makefileTab, unitTestTab, testFixtureTab);
        previewTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //Populating Makefile Tab
        HBox makefileScene = new HBox();
        Image exampleMakefile = new Image("ExampleMakefile.png");
        Label makefileLabel = new Label();
        makefileLabel.setGraphic(new ImageView(exampleMakefile));
        makefileLabel.setPadding(new Insets(20, 20, 20,20));
        makefileScene.getChildren().addAll(makefileLabel);
        makefileScene.setAlignment(Pos.CENTER);
        makefileTab.setContent(makefileScene);

        //Populating Unit Test Tab

        //Populating Test Fixture Tab

        //Populate main preview window and display
        previewLayout.setCenter(previewTabPane);
        previewLayout.setBottom(bottomPreviewScene);
        Stage stageA = new Stage();
        stageA.initModality(Modality.APPLICATION_MODAL);
        stageA.setTitle("AxolotlSWENG:        Powered by Rowan University");
        stageA.setScene(new Scene(previewLayout, 925, 525));
        stageA.setResizable(false);
        stageA.getIcons().add(new Image("CuteLizard.PNG"));
        stageA.show();

        //Populate Sample File header window
        Stage stageB = new Stage();
        HBox sampleScene = new HBox();
        Image sampleFileImage = new Image("cppHeader.png");
        Label sampleFileLabel = new Label();
        sampleFileLabel.setGraphic(new ImageView(sampleFileImage));
        sampleScene.getChildren().add(sampleFileLabel);
        stageB.setTitle("AxolotlSWENG:        Powered by Rowan University");
        stageB.setScene(new Scene(sampleScene, 500, 350));
        stageB.setResizable(false);
        stageB.getIcons().add(new Image("CuteLizard.PNG"));
        /*
        Action Listener for the Preview window that closes the preview window and sample file window
         */
        previewCloseButton.setOnAction(event -> {
            stageA.close();
            stageB.close();
        });
        /*
        Action Listener for the Preview window that shows the sample file window.
         */
        sampleFileButton.setOnAction(event -> {
            stageB.show();
        });
    }

    /**
     * Private method that builds the 'Preferences' window that allows the user to modify output files and store
     * predefined paths selected by the user.
     */
    private void buildPreferencesWindow() {
        //Preferences window BorderPane layout declaration and initialization
        BorderPane preferencesLayout = new BorderPane();

        //Preference Main Scene and sub scene declaration and initialization
        VBox preferencesTopScene = new VBox();
        HBox preferencesCenterScene = new HBox();
        HBox preferencesBottomScene = new HBox();
        HBox preferencesSceneA = new HBox();
        HBox preferencesSubSceneA = new HBox();
        HBox preferencesSubSceneB = new HBox();
        HBox preferencesSceneB = new HBox();
        HBox preferencesSubSceneC = new HBox();
        HBox preferencesSubSceneD = new HBox();
        HBox preferencesSceneC = new HBox();
        HBox preferencesSubSceneE = new HBox();
        HBox preferencesSubSceneF = new HBox();
        HBox preferencesSceneD = new HBox();
        HBox preferencesSubSceneG = new HBox();
        HBox preferencesSubSceneH = new HBox();
        HBox preferencesSceneE = new HBox();
        HBox preferencesSubSceneI = new HBox();
        HBox preferencesSubSceneJ = new HBox();
        HBox preferencesSceneF = new HBox();
        HBox preferencesSubSceneK = new HBox();
        HBox preferencesSubSceneL = new HBox();
        HBox preferencesSceneG = new HBox();
        HBox preferencesSubSceneM = new HBox();
        HBox preferencesSubSceneN = new HBox();
        HBox preferencesSceneH = new HBox();
        HBox preferencesSubSceneO = new HBox();
        VBox preferencesSubSceneP = new VBox();
        HBox preferencesSubSceneQ = new HBox();
        HBox preferencesSubSceneR = new HBox();
        HBox preferencesSceneI = new HBox();
        HBox preferencesSubSceneS = new HBox();
        HBox preferencesSubSceneT = new HBox();

        //Preferences window button declaration/initialization and formatting.
        Button saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setPrefSize(78, 20);
        Button applyButton = new Button();
        applyButton.setText("Apply");
        applyButton.setPrefSize(78, 20);
        Button closeButton = new Button();
        closeButton.setText("Cancel");
        closeButton.setPrefSize(78, 20);
        Button browseButton = new Button();
        browseButton.setText("Browse");
        browseButton.setPrefSize(78, 20);
        Button manageButton = new Button();
        manageButton.setText("Manage");
        manageButton.setPrefSize(78, 20);

        //TextField declaration/initialization
        TextField preferredDestinationPath = new TextField();

        //Bottom Region Declaration/Initialization
        Region bottomRegion = new Region();

        //Preferences window label declaration/initialization and formatting
        Label preferenceLabelA = new Label();
        preferenceLabelA.setText("Placeholder for preference 1:                         ");
        preferenceLabelA.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelA.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelB = new Label();
        preferenceLabelB.setText("Placeholder for preference 2:                         ");
        preferenceLabelB.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelB.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelC = new Label();
        preferenceLabelC.setText("Placeholder for preference 3:                         ");
        preferenceLabelC.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelC.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelD = new Label();
        preferenceLabelD.setText("Placeholder for preference 4:                         ");
        preferenceLabelD.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelD.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelE = new Label();
        preferenceLabelE.setText("Placeholder for preference 5:                         ");
        preferenceLabelE.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelE.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelF = new Label();
        preferenceLabelF.setText("Placeholder for preference 6:                         ");
        preferenceLabelF.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelF.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelG = new Label();
        preferenceLabelG.setText("Placeholder for preference 7:                         ");
        preferenceLabelG.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelG.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelH = new Label();
        preferenceLabelH.setText("Update Preferred Destinations:  ");
        preferenceLabelH.setFont(Font.font("Courier New", FontWeight.BOLD, 14));

        //Preference #1 Populating and Formatting
        preferencesSubSceneA.getChildren().add(preferenceLabelA);
        preferencesSubSceneA.setAlignment(Pos.CENTER_LEFT);
        preferencesSubSceneB.getChildren().addAll(preferences.get(0));
        preferencesSubSceneB.setAlignment(Pos.CENTER);
        preferencesSceneA.getChildren().addAll(preferencesSubSceneA, preferencesSubSceneB);
        preferencesSceneA.setPadding(new Insets(15,15,15,20));

        //Preference #2 Populating and Formatting
        preferencesSubSceneC.getChildren().add(preferenceLabelB);
        preferencesSubSceneC.setAlignment(Pos.CENTER_LEFT);
        preferencesSubSceneD.getChildren().addAll(preferences.get(1));
        preferencesSubSceneD.setAlignment(Pos.CENTER);
        preferencesSceneB.getChildren().addAll(preferencesSubSceneC, preferencesSubSceneD);
        preferencesSceneB.setPadding(new Insets(15,15,15,20));

        //Preference #3 Populating and Formatting
        preferencesSubSceneE.getChildren().add(preferenceLabelC);
        preferencesSubSceneE.setAlignment(Pos.CENTER_LEFT);
        preferencesSubSceneF.getChildren().addAll(preferences.get(2));
        preferencesSubSceneF.setAlignment(Pos.CENTER);
        preferencesSceneC.getChildren().addAll(preferencesSubSceneE, preferencesSubSceneF);
        preferencesSceneC.setPadding(new Insets(15,15,15,20));

        //Preference #4 Populating and Formatting
        preferencesSubSceneG.getChildren().add(preferenceLabelD);
        preferencesSubSceneG.setAlignment(Pos.CENTER_LEFT);
        preferencesSubSceneH.getChildren().addAll(preferences.get(3));
        preferencesSubSceneH.setAlignment(Pos.CENTER);
        preferencesSceneD.getChildren().addAll(preferencesSubSceneG, preferencesSubSceneH);
        preferencesSceneD.setPadding(new Insets(15,15,15,20));

        //Preference #5 Populating and Formatting
        preferencesSubSceneI.getChildren().add(preferenceLabelE);
        preferencesSubSceneI.setAlignment(Pos.CENTER_LEFT);
        preferencesSubSceneJ.getChildren().addAll(preferences.get(4));
        preferencesSubSceneJ.setAlignment(Pos.CENTER);
        preferencesSceneE.getChildren().addAll(preferencesSubSceneI, preferencesSubSceneJ);
        preferencesSceneE.setPadding(new Insets(15,15,15,20));

        //Preference #6 Populating and Formatting
        preferencesSubSceneK.getChildren().add(preferenceLabelF);
        preferencesSubSceneK.setAlignment(Pos.CENTER_LEFT);
        preferencesSubSceneL.getChildren().addAll(preferences.get(5));
        preferencesSubSceneL.setAlignment(Pos.CENTER);
        preferencesSceneF.getChildren().addAll(preferencesSubSceneK, preferencesSubSceneL);
        preferencesSceneF.setPadding(new Insets(15,15,15,20));

        //Preference #7 Populating and Formatting
        preferencesSubSceneM.getChildren().add(preferenceLabelG);
        preferencesSubSceneM.setAlignment(Pos.CENTER_LEFT);
        preferencesSubSceneN.getChildren().addAll(preferences.get(6));
        preferencesSubSceneN.setAlignment(Pos.CENTER);
        preferencesSceneG.getChildren().addAll(preferencesSubSceneM, preferencesSubSceneN);
        preferencesSceneG.setPadding(new Insets(15,15,15,20));

        //Finish populating and formatting the top border scene of the Preferences window
        preferencesTopScene.getChildren().addAll(preferencesSceneA, preferencesSceneB, preferencesSceneC,
                preferencesSceneD, preferencesSceneE, preferencesSceneF, preferencesSceneG);
        preferencesTopScene.setStyle("-fx-background-color: #373747;");

        //Building and populating Center Section of the Preferences Window
        preferencesSubSceneO.getChildren().add(preferredDestinationPath);
        preferencesSubSceneO.setPadding(new Insets(0,30,0,0));
        preferredDestinationPath.setMinWidth(410);
        preferencesSubSceneQ.setPadding(new Insets(0,0,10,0));
        preferencesSubSceneQ.getChildren().add(browseButton);
        preferencesSubSceneR.setPadding(new Insets(0,0,10,0));
        preferencesSubSceneR.getChildren().add(saveButton);
        preferencesSubSceneP.getChildren().addAll(preferencesSubSceneQ, preferencesSubSceneR);
        preferencesSubSceneP.setAlignment(Pos.TOP_CENTER);
        preferredDestinationPath.setFocusTraversable(false);
        preferencesSceneH.getChildren().addAll(preferencesSubSceneO, preferencesSubSceneP);
        preferencesSceneH.setPadding(new Insets(15,15,0,20));
        preferencesCenterScene.setStyle("-fx-background-color: #373747;");
        preferencesCenterScene.getChildren().addAll(preferencesSceneH);

        //Building and populating Bottom Section of the Preferences Window
        preferencesSubSceneS.getChildren().add(manageButton);
        preferencesSubSceneT.getChildren().addAll(applyButton, closeButton);
        preferencesSubSceneT.setSpacing(10);
        preferencesSceneI.getChildren().addAll(preferencesSubSceneS, bottomRegion, preferencesSubSceneT);
        bottomRegion.setMinWidth(275);
        //preferencesBottomScene.setHgrow(bottomRegion, Priority.ALWAYS);
        preferencesSceneI.setPadding(new Insets(0,15,15,20));
        preferencesBottomScene.setStyle("-fx-background-color: #373747;");
        preferencesBottomScene.getChildren().add(preferencesSceneI);

        //Populate the main preferences window layout, as well as format and display main preferences window
        preferencesLayout.setTop(preferencesTopScene);
        preferencesLayout.setCenter(preferencesCenterScene);
        preferencesLayout.setBottom(preferencesBottomScene);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("AxolotlSWENG:        Powered by Rowan University");
        stage.setScene(new Scene(preferencesLayout, 560, 550));
        stage.setResizable(false);
        stage.getIcons().add(new Image("CuteLizard.PNG"));
        stage.show();
        /*
        Action Listener for the 'Cancel' button that closes the preferences window/menu
        */
        closeButton.setOnAction(event -> stage.close());
        /*
        Action Listener for the 'Browse' button that makes a external method call to destinationBrowse() in the Controller
        class that populates the preferredDestinationPath text field with the specific filepath of the directory/folder
        selected by the user for output to eventually by stored as a preferred default destination path.
         */
        browseButton.setOnAction(event -> {
            File destination = controller.destinationBrowse();
            if(destination != null) {
                preferredDestinationPath.setText(destination.getAbsolutePath());
            }
        });

        /*
        Action Listener for the 'Save' button that calls a method in the Controller class that will add (save) the
        potential destination into the predefined destination path check list (FrontEndGUI) and HashSet list in the
        controller upon making a check that the isn "bad" (doesn't exist on the system and/or isn't a directory)
         */
        saveButton.setOnAction(event -> {
            manageSubSceneA = controller.saveBrowse(manageSubSceneA, preferredDestinationPath);
            defaultPaths = controller.updateDefaultPaths(defaultPaths, preferredDestinationPath);
        });

        /*
        Action Listener for the 'Apply' button that updates the preferences altered by the user
         */
        applyButton.setOnAction(event -> controller.updatePreferences(preferences));

        /*
        Action Listener for the 'Manage' button that opens up a new window that will contain a ListView<CheckBox>
        representing the list of serialized user-preferred destination directories.  the button will also allow the
        user to select all items, deselect all items and refresh(remove) all unselected paths from the check list.
         */
        manageButton.setOnAction(event -> {
            //Manage Window main layout declaration and initialization
            BorderPane manageWindowLayout = new BorderPane();

            //Manage window scene declaration and initialization
            HBox mainManageScene = new HBox();
            VBox manageSubSceneB = new VBox();

            //Manage window region declaration/initialization and formatting
            Region manageRegionA = new Region();
            manageRegionA.setMinWidth(20);
            Region manageRegionB = new Region();
            manageRegionB.setMinHeight(50);

            //Manage window button declaration/initialization and formatting
            Button manageRefreshButton = new Button();
            manageRefreshButton.setText("Refresh");
            manageRefreshButton.setPrefSize(78, 20);
            Button manageSelectAllButton = new Button();
            manageSelectAllButton.setText("Select All");
            manageSelectAllButton.setPrefSize(78, 20);
            Button manageDeselectAllButton = new Button();
            manageDeselectAllButton.setText("Deselect All");
            manageDeselectAllButton.setPrefSize(78, 20);
            Button manageCloseButton = new Button();
            manageCloseButton.setText("Close");
            manageCloseButton.setPrefSize(78, 20);

            //Manage window scene formatting
            manageSubSceneA.setPrefWidth(455);
            manageSubSceneA.setFocusTraversable(false);
            manageSubSceneB.getChildren().addAll(manageRefreshButton, manageSelectAllButton, manageDeselectAllButton,
                    manageRegionB, manageCloseButton);
            manageSubSceneB.setSpacing(10);
            manageSubSceneB.setAlignment(Pos.TOP_CENTER);
            mainManageScene.setPadding(new Insets(25, 20, 25, 20));
            mainManageScene.setStyle("-fx-background-color: #373747;");
            mainManageScene.getChildren().addAll(manageSubSceneA, manageRegionA, manageSubSceneB);

            //Populate and display Manager window
            manageWindowLayout.setCenter(mainManageScene);
            Stage manageStage = new Stage();
            manageStage.initModality(Modality.APPLICATION_MODAL);
            manageStage.setTitle("AxolotlSWENG:        Powered by Rowan University");
            manageStage.setScene(new Scene(manageWindowLayout, 590, 250));
            manageStage.setResizable(false);
            manageStage.getIcons().add(new Image("CuteLizard.PNG"));
            manageStage.show();
            /*
            Action Listener for the 'refresh' button that makes a call to the controller to refresh the the ListView
            checklist (remove all unselected files) but first checking if it is empty first and throwing
             */
            manageRefreshButton.setOnAction(eventA -> {
                if(!manageSubSceneA.getItems().isEmpty() ||
                        !controller.checkThatAllDesiredFilesAreSelected(manageSubSceneA)) {
                    manageSubSceneA = controller.refreshPreferredDestinationFiles(manageSubSceneA);
                    defaultPaths = controller.updateDefaultPaths(defaultPaths, preferredDestinationPath);
                }
                else {
                    AlertBox.simpleDisplay("Nothing to refresh... Everything seems up to date!");
                }
            });
            /*
            Action Listener for the 'Select All' button in the Manage window that selects all files in check list if
            they're not already all selected or its not empty
             */
            manageSelectAllButton.setOnAction(eventA -> {
                if(!manageSubSceneA.getItems().isEmpty()) {
                    manageSubSceneA = controller.selectAllSourceFiles(manageSubSceneA);
                }
                else {
                    AlertBox.simpleDisplay("Nothing to select!  Everything seems to be up to date");
                }
            });
            /*
            Action Listener for the 'Deselect All' button in the Manage window that deselects all files in check list if
            its not empty
             */
            manageDeselectAllButton.setOnAction(eventA -> {
                if(!manageSubSceneA.getItems().isEmpty()) {
                    manageSubSceneA = controller.deselectAllSourceFiles(manageSubSceneA);
                }
                else {
                    AlertBox.simpleDisplay("Nothing to deselect!  Everything seems to be up to date");
                }
            });
            /*
            Action Listener for the 'Close' button in the Manage window that closes the Manage window
             */
            manageCloseButton.setOnAction(eventA -> {
                manageStage.close();
            });

        });

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
            if (destination != null) {
                destinationPath.setText(destination.getAbsolutePath());
            }
        });
        /*
        Action Listener for the 'Refresh' button that makes a external method call to refreshSourceFiles in the
        Controller class that will remove the files deselected by the user as well as updating the Controller's
        sourceFile set instance variable
         */
        refreshButton.setOnAction(event -> {
            if (centerSubSceneA.getItems().isEmpty()) {
                //TEST
                AlertBox.simpleDisplay("Nothing to refresh... No source files selected!");
            } else if (controller.checkThatAllDesiredFilesAreSelected(centerSubSceneA)) {
                AlertBox.simpleDisplay("Nothing to refresh... All files are selected and up to date!");
            } else {
                centerSubSceneA = controller.refreshSourceFiles(centerSubSceneA);
                centerSubSceneA.setPadding(new Insets(0, 0, 0, 10));
                centerSubSceneA.setPrefWidth(430);
            }
        });
        /*
         Action Listener for the "Select All" button that will first check to see if the source files checklist on the
        GUI is empty.  If it is empty, the user is prompted with an error message.  If its not empty, it makes another
        check to see if all the source files are already selected, if they are not, it will make a call to the
        controller to select all source files in the checklist on the GUI.
        */
        selectAllButton.setOnAction(event -> {
            if (centerSubSceneA.getItems().isEmpty()) {
                AlertBox.simpleDisplay("\"No source files selected.  Search for source files by clicking " +
                        "the 'browse' button!");
            } else {
                if (!controller.checkThatAllDesiredFilesAreSelected(centerSubSceneA)) {
                    centerSubSceneA = controller.selectAllSourceFiles(centerSubSceneA);
                } else {
                    AlertBox.simpleDisplay("Nothing to select... all desired source files are up to date!");
                }
            }
        });

        /*
        Action Listener for the "Deselect All" button that will first check to see if the source files checklist on the
        GUI is empty.  If it is empty, the user is prompted with an error message.  If its not empty, it makes a call
        to the controller to deselect all source files in the checklist.
         */
        deselectAllButton.setOnAction(event -> {
            if (centerSubSceneA.getItems().isEmpty()) {
                AlertBox.simpleDisplay("No source files selected.  Search for source files by clicking " +
                        "the 'browse' button!");
            } else {
                centerSubSceneA = controller.deselectAllSourceFiles(centerSubSceneA);
            }
        });

        /*
        Action Listener for the 'Generate' button. Does not fully generate the files yet, but other tests the file
        parsing functionality and error handling capabilities
         */
        generateButton.setOnAction(event -> {
            File destination = new File(destinationPath.getText());
            String errorMessage = "";
            boolean error = false;
            if (centerSubSceneA.getItems().isEmpty() && !controller.checkDestinationPath(destination)) {
                error = true;
                errorMessage = "No source files selected and the destination selected doesn't exist on this system!";
            } else if (centerSubSceneA.getItems().isEmpty()) {
                error = true;
                errorMessage = "No source files selected.  Search for source files by clicking the 'browse' button!";
            } else if (!controller.checkThatAllDesiredFilesAreSelected(centerSubSceneA)) {
                error = true;
                errorMessage = "Not all desired source files are selected for processing, did you forget to refresh?";
            } else if (!controller.checkDestinationPath(destination)) {
                error = true;
                errorMessage = "Destination selected doesn't exist on the system!  Double check the path you entered.";
            }
            if (!error) {
                File[] parsingFiles = new File[controller.getSourceFiles().size()];
                controller.getSourceFiles().toArray(parsingFiles);
                try {
                    controller.getFileParser().parseFilesAndGenerateOutputFiles(parsingFiles,
                            controller.getDestinationFile());
                    window.hide();
                    buildGenerateWindow();

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                AlertBox.simpleDisplay(errorMessage);
            }
        });
        /*
        Action listener for the 'Help' button that will display the 'Help' menu for the user
         */
        helpButton.setOnAction(event -> buildHelpWindow());

        /*
        Action listener for the 'Preview' button that will display the 'Preview' window for the user
         */
        previewButton.setOnAction(event -> buildPreviewWindow());
        /*
        Action listener for the 'Preferences' button that will display the 'Preferences' window/menu for the user
         */
        preferencesButton.setOnAction(event -> buildPreferencesWindow());
        /*
        Action Listener for the 'Defaults' button that build the 'Defaults' window that allows the user select and load
        one of their predefined destination paths.
         */
        defaultsButton.setOnAction(event -> {

            if (!defaultPaths.getItems().isEmpty()) {
                HBox defaultPathWindow = new HBox();

                Button loadButton = new Button();
                loadButton.setText("Load");
                loadButton.setPrefSize(78, 20);
                Button closeButton = new Button();
                closeButton.setText("Close");
                closeButton.setPrefSize(78, 20);

                defaultPaths.setMinWidth(500);
                defaultPaths.getSelectionModel().selectFirst();

                defaultPathWindow.getChildren().addAll(defaultPaths, loadButton, closeButton);
                defaultPathWindow.setSpacing(10);
                defaultPathWindow.setPadding(new Insets(10, 10, 10, 10));

                Stage defaultPathStage = new Stage();
                defaultPathStage.setScene(new Scene(defaultPathWindow, 650, 50));
                defaultPathStage.initModality(Modality.APPLICATION_MODAL);
                defaultPathStage.setTitle("AxolotlSWENG:        Powered by Rowan University");
                defaultPathStage.setResizable(false);
                defaultPathStage.getIcons().add(new Image("CuteLizard.PNG"));
                defaultPathStage.show();

                closeButton.setOnAction(eventA -> {
                    defaultPathStage.close();
                });

                loadButton.setOnAction(eventA -> {
                    destinationPath.setText(defaultPaths.getSelectionModel().getSelectedItem());
                    defaultPathStage.close();
                });
            } else {
                AlertBox.simpleDisplay("No default paths found.  Try loading some up!");
            }
        });
    }
}