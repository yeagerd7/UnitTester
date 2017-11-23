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

    //Shadow Effect Field Declaration
    private DropShadow shadow;

    //Default Path Choice Box Field Declaration
    private ChoiceBox<String> defaultPaths;

    //Serialized Default Preference Field Declaration;
    private ArrayList<ChoiceBox<String>> preferences;

    //Toggles Field declaration
    private ArrayList<Boolean> toggles;

    //manageSubSceneA field declaration
    private ListView<CheckBox> manageSubSceneA;

    //Method List(Test Fixtures) Field Declaration
    private ListView<CheckBox> methodCheckList;

    //CFlag List(Test Fixture) Field Declaration
    private ListView<CheckBox> cFlagCheckList;

    //Default (Test Fixtures) TextField Declarations
    private TextField stringDefault;
    private TextField characterDefault;
    private TextField integerDefault;
    private TextField doubleDefault;
    private TextField booleanDefault;

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
        destinationLabel = new Label("Destination:");

        //Region Initialization
        topRegionA = new Region();
        topRegionB = new Region();
        centerRegion = new Region();
        bottomRegion = new Region();

        //TextField Initialization
        destinationPath = new TextField();
        stringDefault = new TextField("Axolotl");
        characterDefault = new TextField("X");
        integerDefault = new TextField("36");
        doubleDefault = new TextField("3.14");
        booleanDefault = new TextField("true");

        //Button/Tooltip Initialization/Declaration and Formatting
        browseButton1 = new Button("Browse");
        browseButton1.setTooltip(new Tooltip("Search for .cpp \nand .h source files!"));
        browseButton1.setPrefSize(78, 20);
        browseButton2 = new Button("Browse");
        browseButton2.setTooltip(new Tooltip("Search for or enter in \nyour destination directory!"));
        browseButton2.setPrefSize(78, 20);
        previewButton = new Button("Preview");
        previewButton.setTooltip(new Tooltip("Preview output files!"));
        previewButton.setPrefSize(78, 20);
        generateButton = new Button("Generate");
        generateButton.setTooltip(new Tooltip("Parse source files and select \ntest fixture preferences!"));
        generateButton.setPrefSize(78, 20);
        preferencesButton = new Button("Preferences");
        preferencesButton.setTooltip(new Tooltip("Select preferences and manage \nuser-selected default " +
                                                    "destination paths!"));
        preferencesButton.setPrefSize(80, 27);
        preferencesButton.setFont(Font.font(11));
        helpButton = new Button("Help");
        helpButton.setTooltip(new Tooltip("Access the help manual!"));
        helpButton.setPrefSize(78, 20);
        refreshButton = new Button("Refresh");
        refreshButton.setTooltip(new Tooltip("Remove all \nunselected files!"));
        refreshButton.setPrefSize(78, 20);
        selectAllButton = new Button("Select All");
        selectAllButton.setPrefSize(78, 20);
        deselectAllButton = new Button("Deselect All");
        deselectAllButton.setFont(Font.font(11));
        deselectAllButton.setPrefSize(78, 27);
        defaultsButton = new Button("Defaults");
        defaultsButton.setTooltip(new Tooltip("Load a default \ndestination path!"));
        defaultsButton.setPrefSize(78, 20);

        //Axolotl Image Initialization and Formatting
        image = new Image("CuteLizard.PNG", 140, 140,
                false, false);

        //(To be)Parsed Method List
        methodCheckList = new ListView<>();

        //CFlag List Declaration/Initialization and Populating
        cFlagCheckList = new ListView<>();
        for(int i = 0; i < 5; i++) {
            CheckBox box = new CheckBox("Flag" + (i + 1));
            cFlagCheckList.getItems().add(box);
        }

        /*
         * Initializes the ListView and ChoiceBox objects that represents the lists of preferred destination paths
         * and loads the check lists coinciding with the serialized user preference settings defined in the Preferences
         * class.  The ListView lives in the 'Manage' window while the ChoiceBox lives in the window resulting from the
         * 'Defaults' button click on the main gui.
         */
        manageSubSceneA = new ListView<>();
        defaultPaths = new ChoiceBox<>();
        Iterator<File> it = controller.setPreferredDestinations().iterator();
        while(it.hasNext()) {
            File destination = it.next();
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
            if(!toggles.get(i)) {
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

        //ShadowEffect for Program Name and Axolotl Image in Top Layer
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
        topLabelA.setFont(Font.font("Courier New", FontWeight.BOLD, 66));
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
        window.setScene(new Scene(mainScene, 800, 510));
        mainScene.setMinWidth(800);
        mainScene.setMinHeight(510);
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

        //Generate window button declarations/initialization and formatting
        Button closeButton = new Button("Close");
        closeButton.setPrefSize(80, 20);
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefSize(90, 20);
        Button destinationOutput = new Button("Destination");
        destinationOutput.setTooltip(new Tooltip("Access your output files!"));
        destinationOutput.setPrefSize(90, 20);

        //Generate window image declaration and initialization
        Image partyLizard = new Image("HappyLizard.JPG",200, 200,
                false, false);

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

         */
        stage.setOnCloseRequest(event -> {
            controller.serializePreference();
        });
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
        Label bottomPreviewLabel = new Label("Sample File:  ");
        bottomPreviewLabel.setFont(Font.font("Courier New"));
        bottomPreviewLabel.setTextFill(Color.web("#DED8D8"));

        Button sampleFileButton = new Button("Assignment.cpp");
        sampleFileButton.setPrefSize(120, 20);
        Button previewCloseButton = new Button("Close");
        previewCloseButton.setPrefSize(80, 20);

        //Populating Bottom Preview Window Scene
        HBox.setHgrow(previewRegion, Priority.ALWAYS);
        bottomPreviewSubSceneA.getChildren().addAll(bottomPreviewLabel, sampleFileButton);
        bottomPreviewSubSceneB.getChildren().addAll(previewCloseButton);
        bottomPreviewScene.getChildren().addAll(bottomPreviewSubSceneA, previewRegion, bottomPreviewSubSceneB);

        //Preview window Tab/Tab Pane declaration/initialization and formatting
        Tab makefileTab = new Tab("Makefile");
        Tab unitTestTab = new Tab("Unit Test");
        Tab testFixtureTab = new Tab("Test Fixture");
        TabPane previewTabPane = new TabPane();
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

        //Tooltip Declaration/Initialization

        //Preferences window button/tooltip declaration/initialization and formatting.
        Button saveButton = new Button("Save");
        saveButton.setTooltip(new Tooltip("Save a destination path!"));
        saveButton.setPrefSize(78, 20);
        Button applyButton = new Button("Apply");
        applyButton.setTooltip(new Tooltip("Save your changes!"));
        applyButton.setPrefSize(78, 20);
        Button closeButton = new Button("Cancel");
        closeButton.setTooltip(new Tooltip("Did you save your changes?"));
        closeButton.setPrefSize(78, 20);
        Button browseButton = new Button("Browse");
        browseButton.setTooltip(new Tooltip("Search for a destination path!"));
        browseButton.setPrefSize(78, 20);
        Button manageButton = new Button("Manage");
        manageButton.setTooltip(new Tooltip("Manage your default \ndestination paths!"));
        manageButton.setPrefSize(78, 20);

        //TextField declaration/initialization
        TextField preferredDestinationPath = new TextField();

        //Bottom Region Declaration/Initialization
        Region bottomRegion = new Region();

        //Preferences window label declaration/initialization and formatting
        Label preferenceLabelA = new Label("This will contain info for the first preference       ");
        preferenceLabelA.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelA.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelB = new Label("This will contain info for the second preference      ");
        preferenceLabelB.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelB.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelC = new Label("This will contain info for the third preference       ");
        preferenceLabelC.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelC.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelD = new Label("This will contain info for the fourth preference      ");
        preferenceLabelD.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelD.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelE = new Label("This will contain info for the fifth preference       ");
        preferenceLabelE.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelE.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelF = new Label("This will contain info for the sixth preference       ");
        preferenceLabelF.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelF.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelG = new Label("This will contain info for the seventh preference     ");
        preferenceLabelG.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        preferenceLabelG.setTextFill(Color.web("#DED8D8"));
        Label preferenceLabelH = new Label("Update Preferred Destinations:  ");
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
            manageSubSceneA = controller.saveDestinationPath(manageSubSceneA, preferredDestinationPath);
            defaultPaths = controller.updateDefaultPaths();
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
            Button manageRefreshButton = new Button("Refresh");
            manageRefreshButton.setTooltip(new Tooltip("Removes any unselected destination path " +
                                                    "\nin the checklist!"));
            manageRefreshButton.setPrefSize(78, 20);
            Button manageSelectAllButton = new Button("Select All");
            manageSelectAllButton.setPrefSize(78, 20);
            Button manageDeselectAllButton = new Button("Deselect All");
            manageDeselectAllButton.setPrefSize(78, 20);
            Button manageCloseButton = new Button("Close");
            manageCloseButton.setTooltip(new Tooltip("Did you click 'Refresh' so \nsave your changes?"));
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
                    defaultPaths = controller.updateDefaultPaths();
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
     * Private Method that builds the Test Fixture window for the user
     */
    private void buildTestFixtureWindow() {
        //Test fixture main window BorderPane layout
        BorderPane fixtureLayout = new BorderPane();

        //Test fixture window scene declaration and initialization
        HBox fixtureTopScene = new HBox();
        VBox fixtureCenterScene  = new VBox();
        HBox fixtureBottomScene = new HBox();
        HBox centerSceneA = new HBox();  //Compiler Preference
        HBox centerSceneB = new HBox();  //Executable Name
        HBox centerSceneC = new HBox();  //CFlags
        HBox centerSceneD = new HBox();  //Add Methods
        HBox centerSceneE = new HBox();  //Default Primitive Values Label
        HBox centerSceneF = new HBox();  //String Default
        HBox centerSceneG = new HBox();  //Character(Character, char) Default
        HBox centerSceneH = new HBox();  //Integer(Integer, int, short, long, byte) Default
        HBox centerSceneI = new HBox();  //Floating-Point Number (float, double, real, doublePrecision) Default
        HBox centerSceneJ = new HBox();  //Boolean (true, false) Default


        //Fixture Region Initialization and Declaration
        Region topRegionA = new Region();
        Region topRegionB = new Region();
        Region centerRegionA = new Region();
        Region centerRegionB = new Region();
        Region centerRegionC = new Region();
        Region centerRegionD = new Region();
        Region centerRegionEA = new Region();
        Region centerRegionEB = new Region();
        Region centerRegionF = new Region();
        Region centerRegionG = new Region();
        Region centerRegionH = new Region();
        Region centerRegionI = new Region();
        Region centerRegionJ = new Region();
        Region bottomRegion = new Region();

        //Test fixture labels declaration/initialization and formatting
        Label centerLabelA = new Label("Compiler: ");
        centerLabelA.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelA.setTextFill(Color.web("#DED8D8"));
        Label centerLabelB = new Label("Executable Name: ");
        centerLabelB.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelB.setTextFill(Color.web("#DED8D8"));
        Label centerLabelC = new Label("Enable CFlags: ");
        centerLabelC.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelC.setTextFill(Color.web("#DED8D8"));
        Label centerLabelD = new Label("Add Methods To Test: ");
        centerLabelD.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelD.setTextFill(Color.web("#DED8D8"));
        Label centerLabelE = new Label("Specify Default Values: ");
        centerLabelE.setFont(Font.font("Courier New", FontWeight.BOLD, 38));
        centerLabelE.setTextFill(Color.web("#DED8D8"));
        centerLabelE.setEffect(shadow);
        Label centerLabelF = new Label("String: ");
        centerLabelF.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelF.setTextFill(Color.web("#DED8D8"));
        Label centerLabelG = new Label("Character: ");
        centerLabelG.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelG.setTextFill(Color.web("#DED8D8"));
        Label centerLabelH = new Label("Integer: ");
        centerLabelH.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelH.setTextFill(Color.web("#DED8D8"));
        Label centerLabelI = new Label("Double: ");
        centerLabelI.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelI.setTextFill(Color.web("#DED8D8"));
        Label centerLabelJ = new Label("Boolean: ");
        centerLabelJ.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        centerLabelJ.setTextFill(Color.web("#DED8D8"));

        //Compiler Choice Initialization and Declaration along with population of compiler choices and scene
        ChoiceBox<String> compilerChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList("g++"));
        compilerChoiceBox.setMinWidth(115);
        compilerChoiceBox.getSelectionModel().selectFirst();
        centerSceneA.getChildren().addAll(centerLabelA, centerRegionA, compilerChoiceBox);
        HBox.setHgrow(centerRegionA,Priority.ALWAYS);
        centerSceneA.setAlignment(Pos.CENTER);
        centerSceneA.setPadding(new Insets(0,15,10,15));

        //Executable Name text field declaration/initialization, formatting and population of scene
        TextField executableName = new TextField("executable");
        executableName.setMinWidth(165);
        centerSceneB.getChildren().addAll(centerLabelB, centerRegionB, executableName);
        HBox.setHgrow(centerRegionB,Priority.ALWAYS);
        centerSceneB.setAlignment(Pos.CENTER);
        centerSceneB.setPadding(new Insets(0,15,10,15));

        //CFlag enabling
        Button enableCFlagsButton = new Button("Enable CFlags");
        enableCFlagsButton.setPrefSize(115,20 );
        centerSceneC.getChildren().addAll(centerLabelC, centerRegionC, enableCFlagsButton);
        centerSceneC.setHgrow(centerRegionC,Priority.ALWAYS);
        centerSceneC.setAlignment(Pos.CENTER);
        centerSceneC.setPadding(new Insets(0,15,10,15));

        //Add Methods button declaration/initialization and formatting and population of Scene
        Button addMethodsButton = new Button("Add Methods");
        addMethodsButton.setPrefSize(115,20 );
        centerSceneD.getChildren().addAll(centerLabelD, centerRegionD, addMethodsButton);
        HBox.setHgrow(centerRegionD,Priority.ALWAYS);
        centerSceneD.setAlignment(Pos.CENTER);
        centerSceneD.setPadding(new Insets(0,15,5,15));

        //Default Value Section (Label) and String Default Value Scene population and formatting
        centerSceneE.getChildren().addAll(centerRegionEA, centerLabelE, centerRegionEB);
        HBox.setHgrow(centerRegionEA, Priority.ALWAYS );
        HBox.setHgrow(centerRegionEB, Priority.ALWAYS );
        centerSceneE.setPadding(new Insets(0,0,10,0));
        stringDefault.setMinWidth(150);
        centerSceneF.getChildren().addAll(centerLabelF, centerRegionF, stringDefault);
        HBox.setHgrow(centerRegionF,Priority.ALWAYS);
        centerSceneF.setAlignment(Pos.CENTER);
        centerSceneF.setPadding(new Insets(0,15,10,15));

        //Character Default Value Scene Population and Formatting
        centerSceneG.getChildren().addAll(centerLabelG, centerRegionG, characterDefault);
        HBox.setHgrow(centerRegionG,Priority.ALWAYS);
        centerSceneG.setAlignment(Pos.CENTER);
        centerSceneG.setPadding(new Insets(0,15,10,15));

        //Integer Default Value Scene Population and Formatting
        centerSceneH.getChildren().addAll(centerLabelH, centerRegionH, integerDefault);
        HBox.setHgrow(centerRegionH,Priority.ALWAYS);
        centerSceneH.setAlignment(Pos.CENTER);
        centerSceneH.setPadding(new Insets(0,15,10,15));

        //Floating-Point # Default Value Scene Population and Formatting
        centerSceneI.getChildren().addAll(centerLabelI, centerRegionI, doubleDefault);
        HBox.setHgrow(centerRegionI,Priority.ALWAYS);
        centerSceneI.setAlignment(Pos.CENTER);
        centerSceneI.setPadding(new Insets(0,15,10,15));

        //Boolean Default Value Scene Population and Formatting
        centerSceneJ.getChildren().addAll(centerLabelJ, centerRegionJ, booleanDefault);
        HBox.setHgrow(centerRegionJ,Priority.ALWAYS);
        centerSceneJ.setAlignment(Pos.CENTER);
        centerSceneJ.setPadding(new Insets(0,15,5,15));

        //Center Scene populating & formatting
        fixtureCenterScene.getChildren().addAll(centerSceneA, centerSceneB, centerSceneC, centerSceneD, centerSceneE,
                centerSceneF, centerSceneG, centerSceneH, centerSceneI, centerSceneJ);
        fixtureCenterScene.setPadding(new Insets(0, 5, 0, 5));

        //Top label declaration/initialization and formatting
        Label fixtureLabel = new Label("Almost There!");
        fixtureLabel.setTextFill(Color.web("#DED8D8"));
        fixtureLabel.setEffect(shadow);
        fixtureLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 69));
        //Top Scene populating & formatting
        fixtureTopScene.setStyle("-fx-background-color: #373747;");
        fixtureTopScene.getChildren().addAll(topRegionA, fixtureLabel, topRegionB);
        HBox.setHgrow(topRegionA, Priority.ALWAYS);
        HBox.setHgrow(topRegionB, Priority.ALWAYS);

        //Bottom Scene Button Declaration/Initialization and formatting
        Button generateButton = new Button("Generate");
        generateButton.setTooltip(new Tooltip("Generate output files!"));
        generateButton.setPrefSize(78,20);
        Button helpButton = new Button("Help");
        helpButton.setTooltip(new Tooltip("Access the help manual!"));
        helpButton.setPrefSize(78,20);

        //Bottom Scene Formatting and Population
        fixtureBottomScene.setPadding(new Insets(0,20,20,20));
        fixtureBottomScene.getChildren().addAll(helpButton, bottomRegion, generateButton);
        HBox.setHgrow(bottomRegion, Priority.ALWAYS);
        fixtureBottomScene.setStyle("-fx-background-color: #373747;");

        //Test fixture window display and and formatting
        fixtureLayout.setTop(fixtureTopScene);
        fixtureLayout.setCenter(fixtureCenterScene);
        fixtureLayout.setBottom(fixtureBottomScene);
        fixtureLayout.setStyle("-fx-background-color: #373747;");
        Stage fixtureStage = new Stage();
        fixtureStage.initModality(Modality.APPLICATION_MODAL);
        fixtureStage.setTitle("AxolotlSWENG:        Powered by Rowan University");
        fixtureStage.setScene(new Scene(fixtureLayout, 580, 590));
        fixtureStage.setResizable(false);
        fixtureStage.getIcons().add(new Image("CuteLizard.PNG"));
        fixtureStage.show();

        /*
         Action Listener for the 'Help' button which will display the help window/menu for the user
         */
        helpButton.setOnAction(event -> buildHelpWindow());

        /*
         Action Listener for the 'Apply' button thaT, after handling error checking, directs the user to the 'Success'
         screen that guides the user to their output files
         */
        generateButton.setOnAction(event -> {
            String errorMessage = "";
            String userValue = booleanDefault.getText();
            boolean error = false;
            if(compilerChoiceBox.getSelectionModel().getSelectedItem().isEmpty()) {
                errorMessage = "Please specify your compiler!";
                error = true;
            }
            else if(executableName.getText().isEmpty()) {
                errorMessage = "Please specify a valid executable name!";
                error = true;
            }
            else if(stringDefault.getText().isEmpty()) {
                errorMessage = "Please specify a valid String default value!";
                error = true;
            }
            else if(!characterDefault.getText().trim().matches("[a-zA-Z\\d]")) {
                errorMessage = "Please specify a valid Character default value!";
                error = true;
            }
            else if(!integerDefault.getText().trim().matches("^-?\\d+$")) {
                errorMessage = "Please specify a valid Integer default value!";
                error = true;
            }
            else if(!doubleDefault.getText().trim().matches("^-?\\d+(\\.\\d+)?")) {
                errorMessage = "Please specify a valid Double default value!";
                //error = true;
            }
            else if(!userValue.equalsIgnoreCase("true") &&
                            !userValue.equalsIgnoreCase("false")) {
                errorMessage = "Please specify a valid Boolean default value!";
            }
            if(!error) {
                buildGenerateWindow();
                controller.printTextFixturePreferences(); //Test Method
                controller.updateTestFixturePreferences(compilerChoiceBox.getSelectionModel().getSelectedItem(),
                        executableName.getText(), stringDefault.getText(), characterDefault.getText(),
                        integerDefault.getText(), doubleDefault.getText(), booleanDefault.getText());
                controller.printTextFixturePreferences(); //Test Method
                controller.getFileParser().generateOutputFiles(controller.getDestinationFile(), //Destination
                                                    //MORE TO BE ADDED
                                                    controller.getExecutableName()); //Executable Name
                fixtureStage.close();
            }
            else {
                AlertBox.simpleDisplay(errorMessage);
            }
        });

        /*
           Action Listener for the 'Enable CFlags' button that builds and displays a new window showing a check list of
           all the selectable CFlags, as well as a 'Select All' and 'Deselect All' (for obvious uses) as well as an
           'Apply' button that updates the Controller component of the CFlags selected by the user.
         */
        enableCFlagsButton.setOnAction(event -> {
            //'Enable CFlags' window main layout declaration and initialization
            BorderPane enableCFlagsLayout = new BorderPane();

            //'Enable CFlags' window scene declaration/initialization
            HBox cFlagCenterScene = new HBox();
            VBox cFlagCenterSubScene = new VBox();
            HBox cFlagBottomScene = new HBox();

            //'Enable CFlags' region declaration/initialization
            Region regionA = new Region();
            Region regionB = new Region();

            //'Enable CFlags' window button initialization/declaration and formatting
            Button cFlagSelectAllButton = new Button("Select All");
            cFlagSelectAllButton.setPrefSize(93, 20);
            Button cFlagDeselectAllButton = new Button("Deselect All");
            cFlagDeselectAllButton.setPrefSize(93, 20);
            Button cFlagApplyButton = new Button("Apply");
            cFlagApplyButton.setPrefSize(78, 20);
            Button cFlagCloseButton = new Button("Close");
            cFlagCloseButton.setPrefSize(78, 20);

            //'Enable CFlags' window center scene formatting and populating
            cFlagCenterSubScene.getChildren().addAll(cFlagSelectAllButton, cFlagDeselectAllButton);
            cFlagCenterSubScene.setSpacing(10);
            cFlagCenterSubScene.setAlignment(Pos.TOP_CENTER);
            cFlagCenterScene.getChildren().addAll(cFlagCheckList, regionA, cFlagCenterSubScene);
            HBox.setHgrow(regionA, Priority.ALWAYS);
            cFlagCenterScene.setPadding(new Insets(20, 15, 15, 10));
            cFlagCenterScene.setStyle("-fx-background-color: #373747;");
            methodCheckList.setMinWidth(400);

            ////'Enable CFlags' window bottom scene formatting and populating
            cFlagBottomScene.getChildren().addAll(regionB, cFlagApplyButton, cFlagCloseButton);
            cFlagBottomScene.setSpacing(10);
            cFlagBottomScene.setAlignment(Pos.CENTER_RIGHT);
            cFlagBottomScene.setPadding(new Insets(0, 15, 15, 0));
            cFlagBottomScene.setStyle("-fx-background-color: #373747;");
            HBox.setHgrow(regionB, Priority.ALWAYS);

            ////'Enable CFlags' main window population, formatting and display
            enableCFlagsLayout.setCenter(cFlagCenterScene);
            enableCFlagsLayout.setBottom(cFlagBottomScene);
            Stage cFlagStage = new Stage();
            cFlagStage.initModality(Modality.APPLICATION_MODAL);
            cFlagStage.setTitle("AxolotlSWENG:        Powered by Rowan University");
            cFlagStage.setScene(new Scene(enableCFlagsLayout, 375, 300));
            cFlagStage.setResizable(false);
            cFlagStage.getIcons().add(new Image("CuteLizard.PNG"));
            cFlagStage.show();

            /*
            Action Listener for the 'Enable CFlags' 'Close' buttons that closes the window for the user
            */
            cFlagCloseButton.setOnAction(eventA -> {
                cFlagStage.close();
            });

            /*
            Action Listener for the 'Enable CFlags' 'Apply' button that makes a call to the controller to update
            the Controller component's 'cFlagList' (ArrayList) attribute to be transparent and synonymous with the
            check list of CFlags on the 'Enable CFlags' window
            */
            cFlagApplyButton.setOnAction(eventA -> {
                //controller.updateParsedMethodsForTesting(methodCheckList);
            });

            /*
            Action Listener for the 'Enable CFlags' window that makes a call to the controller that sends the
            checklist to the controller and sends it back to the FrontEndGUI with all CFlags selected
            */
            cFlagSelectAllButton.setOnAction(eventA -> {
                cFlagCheckList = controller.selectAllSourceFiles(cFlagCheckList);
            });

            /*
            Action Listener for the 'Enable CFlags' window that makes a call to the controller that sends the
            checklist to the controller and sends it back to the FrontEndGUI with all CFlags deselected
            */
            cFlagDeselectAllButton.setOnAction(eventA -> {
                cFlagCheckList = controller.deselectAllSourceFiles(cFlagCheckList);
            });
        });

        /*
         Action Listener for the 'Add Methods' button that builds and displays a new window showing a check list of
         all the parsed methods selectable for testing , as well as a 'Select All' and 'Deselect All' (for obvious uses)
         as well as an 'Apply' button that updates the Controller component of the parsed methods selected by the user
         for testing

         */
        addMethodsButton.setOnAction(event -> {
            if(!methodCheckList.getItems().isEmpty()) {
                //'Add Methods' window main layout declaration and initialization
                BorderPane addMethodsLayout = new BorderPane();

                //'Add Methods' window scene declaration/initialization
                HBox methodCenterScene = new HBox();
                VBox methodCenterSubScene = new VBox();
                HBox methodBottomScene = new HBox();

                //'Add Methods' region declaration and initialization
                Region regionA = new Region();
                Region regionB = new Region();

                //Add Methods window button initialization/declaration and formatting
                Button methodSelectAllButton = new Button("Select All");
                methodSelectAllButton.setPrefSize(93, 20);
                Button methodDeselectAllButton = new Button("Deselect All");
                methodDeselectAllButton.setPrefSize(93, 20);
                Button methodApplyButton = new Button("Apply");
                methodApplyButton.setTooltip(new Tooltip("All unselected methods will \nnot be tested!"));
                methodApplyButton.setPrefSize(78, 20);
                Button methodCloseButton = new Button("Close");
                methodCloseButton.setTooltip(new Tooltip("Did you click 'Apply' \nto save your changes?"));
                methodCloseButton.setPrefSize(78, 20);

                //'Add Methods' window center scene formatting and populating
                methodCenterSubScene.getChildren().addAll(methodSelectAllButton, methodDeselectAllButton);
                methodCenterSubScene.setSpacing(10);
                methodCenterSubScene.setAlignment(Pos.TOP_CENTER);
                methodCenterScene.getChildren().addAll(methodCheckList, regionA, methodCenterSubScene);
                HBox.setHgrow(regionA, Priority.ALWAYS);
                methodCenterScene.setPadding(new Insets(20, 15, 15, 10));
                methodCenterScene.setStyle("-fx-background-color: #373747;");
                methodCheckList.setMinWidth(400);

                //'Add Method's window bottom scene formatting and populating
                methodBottomScene.getChildren().addAll(regionB, methodApplyButton, methodCloseButton);
                methodBottomScene.setSpacing(10);
                methodBottomScene.setAlignment(Pos.CENTER_RIGHT);
                methodBottomScene.setPadding(new Insets(0, 15, 15, 0));
                methodBottomScene.setStyle("-fx-background-color: #373747;");
                HBox.setHgrow(regionB, Priority.ALWAYS);

                //'Add Methods' main window population, formatting and display
                addMethodsLayout.setCenter(methodCenterScene);
                addMethodsLayout.setBottom(methodBottomScene);
                Stage methodStage = new Stage();
                methodStage.initModality(Modality.APPLICATION_MODAL);
                methodStage.setTitle("AxolotlSWENG:        Powered by Rowan University");
                methodStage.setScene(new Scene(addMethodsLayout, 540, 500));
                methodStage.setResizable(false);
                methodStage.getIcons().add(new Image("CuteLizard.PNG"));
                methodStage.show();

                /*
                Action Listener for the 'Add Methods' 'Close' button that closes the window for the user
                */
                methodCloseButton.setOnAction(eventA -> {
                    methodStage.close();
                });

                /*
                Action Listener for the 'Add Methods' 'Apply' button that makes a call to the controller to update
                the FileParser component's 'method' (ArrayList) attribute to be transparent and synonymous with the
                check list of parsed methods on the 'Add Methods' window
                 */
                methodApplyButton.setOnAction(eventA -> {
                    controller.updateParsedMethodsForTesting(methodCheckList);
                });

                /*
                Action Listener for the 'Add Methods' window that makes a call to the controller that sends the
                checklist to the controller and sends it back to the FrontEndGUI with all Methods selected
                */
                methodSelectAllButton.setOnAction(eventA -> {
                    methodCheckList = controller.selectAllSourceFiles(methodCheckList);
                });

                /*
                Action Listener for the 'Add Methods' window that makes a call to the controller that sends the
                checklist to the controller and sends it back to the FrontEndGUI with all Methods deselected
                 */
                methodDeselectAllButton.setOnAction(eventA -> {
                    methodCheckList = controller.deselectAllSourceFiles(methodCheckList);
                });
            }
            else {
               AlertBox.simpleDisplay("No methods appear to exist!  Did you remember to the include .h file(s)?");
            }

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
                    controller.getFileParser().parseSourceFiles(parsingFiles); //PARSES SOURCE FILES
                    window.hide();
                    methodCheckList = controller.populateMethodsOnGui(methodCheckList);
                    buildTestFixtureWindow();

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
                //'Defaults' window main scene declaration/initialization
                HBox defaultPathWindow = new HBox();

                //'Defaults' window button declaration/initialization and formatting
                Button loadButton = new Button("Load");
                loadButton.setPrefSize(78, 20);
                Button closeButton = new Button("Close");
                closeButton.setPrefSize(78, 20);

                //Default destination path checkList formatting
                defaultPaths.setMinWidth(500);
                defaultPaths.getSelectionModel().selectFirst();

                //'Defaults window population, formatting and display
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

                /*
                 * Action Listener for the 'Load' button in the 'Defaults' window that closes the window
                 */
                closeButton.setOnAction(eventA -> {
                    defaultPathStage.close();
                });

                /*
                 * Action Listener for the 'Load' button in the 'Defaults' window that loads the selected destination from
                 * the drop down menu onto the main GUI for the user
                 */
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