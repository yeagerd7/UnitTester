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
    private HBox centerSubSceneE;
    private HBox centerSubSceneF;

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

    //ToolTip Field Declaration
    private Tooltip sourceBrowseTip;
    private Tooltip destinationBrowseTip;
    private Tooltip refreshTip;

    //Shadow Effect Field Declaration
    private DropShadow shadow;

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
        centerSubSceneE = new HBox();
        centerSubSceneF = new HBox();

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
        selectAllButton = new Button();
        deselectAllButton = new Button();

        //Axolotyl Image Initialization and Formatting
        image = new Image("CuteLizard.PNG",100, 100,
                false, false);

        //ToolTip Initialization/Formatting
        sourceBrowseTip = new Tooltip();
        destinationBrowseTip = new Tooltip();
        refreshTip = new Tooltip();

        shadow = new DropShadow();
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.BLACK);

        //Controller Initialization (Singleton)
        controller = Controller.getInstance();
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
        destinationLabel.setText("Destination:");
        destinationLabel.setFont(Font.font("Courier New"));
        destinationLabel.setTextFill(Color.web("#DED8D8"));
        destinationPath.setFocusTraversable(false);
        HBox.setHgrow(destinationPath, Priority.ALWAYS);
        bottomSubSceneF.getChildren().add(browseButton2);
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
        window.setScene(new Scene(mainScene, 700, 430));
        mainScene.setMinWidth(700);
        mainScene.setMinHeight(430);
        window.show();

        Main.LOGGER.info("Front End User Interface built and displayed");

        //Internal Method call to handle all action listeners
        handleButtons();
    }

    /**
     * Private display method tha displays the 'Help' Window that will assist users if they have any problems or
     * questions regarding the program.
     */
    private void helpWindowDisplay() {
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

        /**
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
        Label browseInfo = new Label(  "Browse Buttons: " + "\n" + "With the upper browse button, you can select filepaths/files that are to be tested." +
                " It should pull up a typical browse window. With the lower browse button, " +
                "you can select where you like to save the generated file.  ");
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

        /**
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

        /**
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

        /**
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

        /**
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
                "by hitting the browse button to locate a .Cpp file (or files), and once that is selected it should appear " +
                "in the white box. ");
        Label walkthroughInfo2 = new Label("Next, a destination folder must be selected. The destination box IS editable," +
                " so if you had a specific location already in mind you would be able to key it in. If there" +
                " isn't a selected folder, the program currently will open an alert box. The refresh button will" +
                "clear the white box of selected files but not the destination folder." + "\n" + "\n" +
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

        //ShadowEffect for Program Name and Axolotyl Image in Top Layer
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
            stage.close();
            window.close();
        });
        /*
        Action Listener for the Generate window that closes this window and returns the user back to the main menu
         */
        mainMenuButton.setOnAction(event -> {
            stage.close();
            window.show();
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
                AlertBox.simpleDisplay("Nothing to refresh... No source files selected!");
            }
            else if(controller.checkThatAllDesiredFilesAreSelected(centerSubSceneA)) {
                AlertBox.simpleDisplay("Nothing to refresh... All files are selected and up to date!");
            }
            else {
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
            if(centerSubSceneA.getItems().isEmpty()) {
                AlertBox.simpleDisplay("\"No source files selected.  Search for source files by clicking " +
                        "the 'browse' button!");
            }
            else {
                if(!controller.checkThatAllDesiredFilesAreSelected(centerSubSceneA)) {
                    centerSubSceneA = controller.selectAllSourceFiles(centerSubSceneA);
                }
                else {
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
            if(centerSubSceneA.getItems().isEmpty()) {
                AlertBox.simpleDisplay("No source files selected.  Search for source files by clicking " +
                        "the 'browse' button!");
            }
            else {
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
            if(centerSubSceneA.getItems().isEmpty() && !controller.checkDestinationPath(destination)) {
                error = true;
                errorMessage = "No source files selected and the destination selected doesn't exist on this system!";
            }
            else if(centerSubSceneA.getItems().isEmpty()) {
                error = true;
                errorMessage = "No source files selected.  Search for source files by clicking the 'browse' button!";
            }
            else if(!controller.checkThatAllDesiredFilesAreSelected(centerSubSceneA)) {
                error = true;
                errorMessage = "Not all desired source files are selected for processing, did you forget to refresh?";
            }
            else if(!controller.checkDestinationPath(destination)) {
                error = true;
                errorMessage = "Destination selected doesn't exist on the system!  Double check the path you entered.";
            }
            if(!error) {
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
            }
            else {
                AlertBox.simpleDisplay(errorMessage);
            }
        });
        /*
        Action listener for the 'Help' button that will display the 'Help' menu for the user
         */
        helpButton.setOnAction(event -> helpWindowDisplay());
    }
}
