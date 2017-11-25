package sample;

//Import Statements
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * Controller class controls the GUI view and responds to inputs. Communicates with and organizes all parts.
 * Will utilize the singleton principle.
 * @author Axolotl Development Team
 */
public class Controller {
    //Source File List Field Declaration
    private HashSet<File> sourceFiles;

    //Destination File Field Declaration
    private File destinationFile;

    //FileParser Field Declaration
    private FileParser fileParser;

    //Desktop Field Declaration
    private Desktop desktop;

    //Preference Field Declaration
    private Preference defaultPreference;

    //Compiler Choice Field Declaration
    private String compilerChoice;

    //Executable Name Choice Field Declaration
    private String executableName;

    //User-Selected CFlag List Field Declaration
    private ArrayList<String> cFlagList;

    //User-Selected List of Methods to to be tested Field Declararion
    private ArrayList<Method> guiMethodList;

    //Default Data Type Value Field Declarations
    private String stringDefault;
    private Character characterDefault;
    private Integer integerDefault;
    private Double doubleDefault;
    private Boolean booleanDefault;

    //Required Declaration for Singleton Pattern
    private static Controller singletonInstance = new Controller();

    /**
     * Private Singleton Constructor that generates an Controller object that initializes most of its fields upon
     * user manipulation in the FrontEndGUI class
     */
    private Controller(){
        desktop = Desktop.getDesktop();
        sourceFiles = new HashSet<>();
        fileParser = new FileParser();
        defaultPreference = deserializePreference();
        compilerChoice = "g++";
        executableName = "executable";
        cFlagList = new ArrayList<>();
        guiMethodList = new ArrayList<>();
        stringDefault = "Axolotl";
        characterDefault = 'X';
        integerDefault = 36;
        doubleDefault = 3.14;
        booleanDefault = true;
        Main.LOGGER.finest("Controller object created and initialized");
    }

    /**
     * Static Singleton Method that returns a singletonInstance(one and only instance) of this class
     * @return singletonInstance(One and only)
     */
    public static Controller getInstance() {
        return singletonInstance;
    }

    /**
     * Accessor method for 'sourceFiles' attribute that returns said attribute
     * @return sourceFiles
     */
    public HashSet<File> getSourceFiles() {
        return sourceFiles;
    }

    /**
     * Setter method for 'sourceFiles' attribute that sets the value to a new source file list denoted as a
     * parameter
     * @param newSourceFiles new source file list
     */
    public void setSourceFiles(HashSet<File> newSourceFiles) {
        sourceFiles = newSourceFiles;
    }

    /**
     * Accessor method for 'destinationFile' attribute that returns said attribute
     * @return destinationFile
     */
    public File getDestinationFile() {
        return destinationFile;
    }

    /**
     * Setter method for 'destinationFile' attribute that sets the value to a new destination file denoted as a
     * parameter newDestinationFile
     * @param newDestinationFile new destination directory
     */
    public void setDestinationFile(File newDestinationFile) {
        destinationFile = newDestinationFile;
    }

    /**
     * Accessor method for the 'fileParser' attribute that returns said attribute
     * @return fileParser
     */
    public FileParser getFileParser() {
        return fileParser; }

    /**
     * Accessor method for the 'defaultPreference' attribute that returns said attribute.
     * @return defaultPreference
     */
    public Preference getDefaultPreference() {
        return defaultPreference;
    }

    /**
     * Accessor method for the 'compilerChoice' attribute that returns said attribute.
     * @return
     */
    public String getCompilerChoice() {
        return compilerChoice;
    }

    /**
     * Setter method for 'compilerChoice' attribute that sets the value to a new compilerChoice String value denoted as a
     * parameter
     * @param  compilerChoice new value
     */
    public void setCompilerChoice(String compilerChoice) {
        this.compilerChoice = compilerChoice;
    }

    /**
     * Accessor method for the 'executableName' attribute that returns said attribute.
     * @return
     */
    public String getExecutableName() {
        return executableName;
    }

    /**
     * * Setter method for 'executableName' attribute that sets the value to a new exectutableName String value denoted
     * as aparameter
     * @param executableName new value
     */
    public void setExecutableName(String executableName) {
        this.executableName = executableName;
    }

    /**
     * Accessor method for the 'cFlagsList' attribute that returns said attribute.
     * @return
     */
    public ArrayList<String> getcFlagList() {
        return cFlagList;
    }

    /**
     * Setter method for 'cFlagList' attribute that sets the value to a new cFlagList ArrayList<String> value denoted
     * as a parameter
     * @param cFlagList new value
     */
    public void setcFlagList(ArrayList<String> cFlagList) {
        this.cFlagList = cFlagList;
    }

    /**
     * Accessor method for the 'guiMethodList' attribute that returns said attribute.
     * @return
     */
    public ArrayList<Method> getGuiMethodList() {
        return guiMethodList;
    }

    /**
     * Setter method for 'guiMethodList' attribute that sets the value to a new guiMethodList ArrayList<String> value
     * denoted as a parameter
     * @param guiMethodList new value
     */
    public void setGuiMethodList(ArrayList<Method> guiMethodList) {
        this.guiMethodList = guiMethodList;
    }

    /**
     * Accessor method for the 'stringDefault' attribute that returns said attribute.
     * @return
     */
    public String getStringDefault() {
        return stringDefault;
    }

    /**
     * Setter method for 'stringDefault' attribute that sets the value to a new stringDefault String value denoted as a
     * parameter
     * @param stringDefault new value
     */
    public void setStringDefault(String stringDefault) {
        this.stringDefault = stringDefault;
    }

    /**
     * Accessor method for the 'characterDefault' attribute that returns said attribute.
     * @return
     */
    public Character getCharacterDefault() {
        return characterDefault;
    }

    /**
     * Setter method for 'characterDefault' attribute that sets the value to a new characterDefault character value
     * denoted as a parameter
     * @param characterDefault new value
     */
    public void setCharacterDefault(Character characterDefault) {
        this.characterDefault = characterDefault;
    }

    /**
     * Accessor method for the 'integerDefault' attribute that returns said attribute.
     * @return
     */
    public Integer getIntegerDefault() {
        return integerDefault;
    }

    /**
     * Setter method for 'integerDefault' attribute that sets the value to a new integerDefault integer value
     * denoted as a parameter
     * @param integerDefault new value
     */
    public void setIntegerDefault(Integer integerDefault) {
        this.integerDefault = integerDefault;
    }

    /**
     * Accessor method for the 'floatingPointDefault' attribute that returns said attribute.
     * @return
     */
    public Double getDoubleDefault() {
        return doubleDefault;
    }

    /**
     * Setter method for 'floatingPointDefault' attribute that sets the value to a new floatingPointDefault float value
     * denoted as a parameter
     * @param doubleDefault new value
     */
    public void setDoubleDefault(Double doubleDefault) {
        this.doubleDefault = doubleDefault;
    }

    /**
     * Accessor method for the 'booleanDefault' attribute that returns said attribute.
     * @return
     */
    public Boolean getBooleanDefault() {
        return booleanDefault;
    }

    /**
     * Setter method for 'booleanDefault' attribute that sets the value to a new booleanDefault float value
     * denoted as a parameter
     * @param booleanDefault new value
     */
    public void setBooleanDefault(Boolean booleanDefault) {
        this.booleanDefault = booleanDefault;
    }

    /**
     * sourceBrowse is called up following a button click on the front end GUI (graphical user interface) and allows
     * user to search their local machine for .cpp files and does not in any point allow duplicate file names in the GUI
     * checkbox list
     * @param fileListGUI existing ListView<CheckBox> object from FrontEndGUI
     * @return fileListGUI populated and displaying .cpp files selected by the user
     */
    public ListView<CheckBox> sourceBrowse(ListView<CheckBox> fileListGUI) {
        FileChooser window = new FileChooser();
        window.getExtensionFilters().add(new FileChooser.ExtensionFilter(".cpp and .h Files",
                                                    "*.cpp", "*.h"));
        List<File> tempSourceFiles = window.showOpenMultipleDialog(null);
        if (tempSourceFiles != null) {
            int size = tempSourceFiles.size();
            int index = 0;
            ListView<String> duplicates =  new ListView<>();
            CheckBox box;
            while (index < size) {
                File fileToAdd = tempSourceFiles.get(index);
                boolean duplicate = false;
                String fileNameToCompare = "";
                Iterator<File> itty = sourceFiles.iterator();
                while (itty.hasNext() && !duplicate) {
                    fileNameToCompare = itty.next().getName();
                    if (fileNameToCompare.equalsIgnoreCase(fileToAdd.getName())) {
                        duplicate = true;
                    }
                }
                if (duplicate == false) {
                    sourceFiles.add(fileToAdd);
                    box = new CheckBox(tempSourceFiles.get(index).getAbsolutePath());
                    box.setSelected(true);
                    fileListGUI.getItems().add(box);
                } else {
                    duplicates.getItems().add(fileNameToCompare);
                }
                index++;
            }
            if(!duplicates.getItems().isEmpty()) {
                AlertBox.duplicateSourceFilesErrorDisplay(duplicates);
            }
        }
        printSourceFiles();
        return fileListGUI;
    }

    /**
     * destinationBrowse is called up following a button click on the front end GUI (graphical user interface) and
     * allows user to specify their desired destination folder for the output
     * @return selectedFile
     */
    public File destinationBrowse() {
        DirectoryChooser window = new DirectoryChooser();
        setDestinationFile(window.showDialog(null));
        return destinationFile;
    }

    /**
     * refreshSourceBrowse is called up following a button click on the front end GUI (graphical user interface) and a
     * CheckBox list of files to be refreshed. It removes all files unselected and updates the HashSet of source files
     * in the controller.
     * @param fileListGUI
     * @return fileListGUI
     */
    public ListView<CheckBox> refreshSourceFiles(ListView<CheckBox> fileListGUI) {
        int index = 0;
        int size = fileListGUI.getItems().size();
        CheckBox box;
        while(index < size) {
            box = fileListGUI.getItems().get(index);
            if(!box.isSelected()) {
                Iterator<File> itty = sourceFiles.iterator();
                while (itty.hasNext()) {
                    File file = itty.next();
                    if (file.getAbsolutePath().equalsIgnoreCase(box.getText())) {
                        itty.remove();
                    }
                }
                fileListGUI.getItems().remove(index);
                size = fileListGUI.getItems().size();
                index--;
            }
            index++;
        }
        printSourceFiles();
        return fileListGUI;
    }

    /**
     * refreshPreferredDestinationFiles is called up following a button click on the Manage Window and a CheckBox list
     * of files to be refreshed.  It removes all files unselected and updates the HashSet of predefined user destination
     * paths in the defaultPreference object in the controller (serializable)
     * @param destinationFiles
     * @return fileListGUI
     */
    public ListView<CheckBox> refreshPreferredDestinationFiles(ListView<CheckBox> destinationFiles) {
        int index = 0;
        int size = destinationFiles.getItems().size();
        CheckBox box;
        while(index < size) {
            box = destinationFiles.getItems().get(index);
            if(!box.isSelected()) {
                Iterator<File> itty = defaultPreference.getPreferredDestinationPaths().iterator();
                while (itty.hasNext()) {
                    File file = itty.next();
                    if (file.getAbsolutePath().equalsIgnoreCase(box.getText())) {
                        itty.remove();
                    }
                }
                destinationFiles.getItems().remove(index);
                size = destinationFiles.getItems().size();
                index--;
            }
            index++;
        }
        return destinationFiles;
    }

    /**
     * Checks to see if all files in the Checkbox list are selected in order to process them.
     * @param checkList  Checkbox list to be examined
     * @return  Either true or false indicating
     */
    public boolean checkThatAllDesiredFilesAreSelected(ListView<CheckBox> checkList) {
        boolean allSelected = true;
        int index = 0;
        int size = checkList.getItems().size();
        CheckBox box;
        while(allSelected && index < size) {
            box = checkList.getItems().get(index);
            if(!box.isSelected()) {
                allSelected = false;
            }
           index++;
        }
        return allSelected;
    }

    /**
     * Selects all files in a ListView of Check Box objects and then returns the updated listview object.
     * @param files
     * @return Updated ListView<CheckBox> object
     */
    public ListView<CheckBox> selectAllSourceFiles(ListView<CheckBox> files) {
        int index = 0;
        int size = files.getItems().size();
        CheckBox box;
        while(index < size){
            box = files.getItems().get(index);
            if(!box.isSelected()) {
                box.setSelected(true);
            }
            index++;
        }
        return files;
    }

    /**
     * Deselects all files in a ListView of Check Box objects and then returns the updated listview object.
     * @param files
     * @return
     */
    public ListView<CheckBox> deselectAllSourceFiles(ListView<CheckBox> files) {
        int index = 0;
        int size = files.getItems().size();
        CheckBox box;
        while(index < size){
            box = files.getItems().get(index);
            if(box.isSelected()) {
                box.setSelected(false);
            }
            index++;
        }
        return files;
    }
    /**
     * Checks if the current File's path in the parameter exists on the system.
     * @param destination incoming destination path to be checked
     * @return true or false indicating if the file exists on the system.
     */
    public boolean checkDestinationPath(File destination) {
        if(destination.exists() && destination.isDirectory()) {
            destinationFile = destination;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Opens the destination directory selected from the user on the front end GUI.
     */
    public void openDestinationDirectory() {
        try {
            desktop.open(destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Updates the check list of preferences on the front end GUI to directly match the the ArrayList object of booleans
     * representing whether a specific preference is active or not.
     */
    public void updatePreferences(ArrayList<ChoiceBox<String>> guiPreferences) {
        int size = guiPreferences.size();
        for(int i = 0; i < size; i++) {
            ChoiceBox<String> choiceBox = guiPreferences.get(i);
            if(choiceBox.getSelectionModel().getSelectedItem().equals("Off")) {
               defaultPreference.getPreferences().set(i, false);
            }
            else {
                defaultPreference.getPreferences().set(i, true);
            }
        }
        defaultPreference.printPreferences();
    }

    /**
     * Method essentially grabs the serialized data representing the preferences either turned on or off and reflects
     * that upon initial loadup of the preferences menu
     * @return
     */
    public ArrayList<Boolean> setToggles() {
        return defaultPreference.getPreferences();
    }

    /**
     * Method essentially grabs the serialized data representing the list of saved/predefined destination paths and reflects
     * that upon initial load up of the preferences menu/managee window
     * @return
     */
    public HashSet<File> setPreferredDestinations() {
        return defaultPreference.getPreferredDestinationPaths();
    }


    /**
     * saveBrowse is called up following a button click on the preferences window/menu and allow user to search their
     * local machine for a destination path they wanna save and adds it to the list of predefined destination paths in
     * the front end gui, upon it not being a duplicate,  and updates the preferred destinations list in the Preferences
     * object
     * @param preferredDestinations
     * @return preferredDestinations
     */
    public ListView<CheckBox> saveDestinationPath(ListView<CheckBox> preferredDestinations,
                                                                TextField potentialDestination) {
        File destination = new File(potentialDestination.getText());
        if(checkDestinationPath(destination)) {
            if(!defaultPreference.getPreferredDestinationPaths().contains(destination)) {
                defaultPreference.getPreferredDestinationPaths().add(destination);
                CheckBox box = new CheckBox();
                box.setSelected(true);
                box.setText(destination.getAbsolutePath());
                preferredDestinations.getItems().add(box);
            }
            else{
                AlertBox.simpleDisplay("Potential destination appears to be a duplicate");
            }
        }
        else {
            AlertBox.simpleDisplay("Bad Destination Path, enter in a valid one!");
        }
        return preferredDestinations;
    }

    /**
     * Method makes sure that the default path attribute in the Front End GUI class is transparent with the default
     * destination path list stored in the serialized Preference class.
     * @return temp;
     */
    public ChoiceBox<String> updateDefaultPaths() {
        ChoiceBox<String> temp = new ChoiceBox<>();
        Iterator<File> itty = defaultPreference.getPreferredDestinationPaths().iterator();
        while(itty.hasNext()) {
            String path = itty.next().getAbsolutePath();
            temp.getItems().add(path);
        }
        return temp;
    }

    /**
     * Allows the user to load their parsed methods from their source .cpp/.h files on a selectable checkbox list
     * denoting the methods to be tested
     * @param list CheckBox list from the GUI of Methods to be populated and then returned
     * @return
     */
    public ListView<CheckBox> populateMethodsOnGuiCheckList(ListView<CheckBox> list) {
        ArrayList<Method> parsedMethods = fileParser.getMethods();
        int size = parsedMethods.size();
        int index = 0;
        while(index < size) {
            CheckBox box = new CheckBox();
            box.setText(parsedMethods.get(index).toString());
            box.setSelected(true);
            list.getItems().add(box);
            index++;
        }
        return list;
    }

    /**
     * Allows the user to load all the tested selected into a ChoiceBox drop down which is transparent across all
     * components
     * @return
     */
    public ChoiceBox<String> populateMethodsOnGuiChoiceList() {
        ArrayList<Method> parsedMethods = fileParser.getMethods();
        ChoiceBox<String> list = new ChoiceBox<>();
        int size = parsedMethods.size();
        int index = 0;
        while(index < size) {
            Method methodChoice = parsedMethods.get(index);
            if(methodChoice.getWillBeTested()) {
                list.getItems().add(methodChoice.toString());
            }
            index++;
        }
        return list;
    }

    /**
     * Allows the user to update the FileParser object's methods attribute (ArrayList) to directly correlate with the
     * method checklist on the GUI.  Used to insure transparency between components.  If the user deselected method(s)
     * then saved it, those specific Method object's attributes need to be updated for testing purposes.
     * @param guiMethodList
     */
    public void updateParsedMethodsForTesting(ListView<CheckBox> guiMethodList) {
        ArrayList<Method> methodsForTesting = fileParser.getMethods();
        int size = methodsForTesting.size();
        int index = 0;
        System.out.println("FileParser Method List : GUI Method List (willBeTested Boolean Value) ");
        while(index < size) {
            Method parsedMethod = methodsForTesting.get(index);
            CheckBox guiMethod = guiMethodList.getItems().get(index);
            if(guiMethod.isSelected()) {
                parsedMethod.setWillBeTested(true);
            }
            else {
                parsedMethod.setWillBeTested(false);
            }
            System.out.println(parsedMethod.getWillBeTested() + " : "
                                    + guiMethodList.getItems().get(index).isSelected()); //TEST
            index++;
        }
        System.out.println();
        fileParser.setMethods(methodsForTesting);
    }

    /**
     * Allows the user to update the controller's test fixture attributes (String/Character/Integer/Double/Float
     * Boolean according to the one's inputted in by the user (otherwise default) in the text fixture window
     * @param userCompiler, userExecutableName, userStringDefault, userCharacterDefault, userIntegerDefault
     *  userFloatingPointDefault, userBooleanDefault
     */
    public void updateTestFixturePreferences(String userCompiler, String userExecutableName, String userStringDefault,
                                             String userCharacterDefault, String userIntegerDefault,
                                             String userFloatingPointDefault, String userBooleanDefault) {
        setCompilerChoice(userCompiler);
        setExecutableName(userExecutableName);
        setStringDefault(userStringDefault);
        setCharacterDefault(userCharacterDefault.toCharArray()[0]);
        setIntegerDefault(Integer.parseInt(userIntegerDefault));
        setDoubleDefault(Double.parseDouble(userFloatingPointDefault));
        if(userBooleanDefault.equalsIgnoreCase("True")) {
            setBooleanDefault(true);
        }
        else {
            setBooleanDefault(false);
        }

    }

    /**
     * Allows the user to attach a CSV file (w/default values) to a method and updates the method object's 'csvFile'
     * attribute to the non-null user-selected CSV file.
     * @param methodString, csvFile
     */
    public void attachCSVToMethod(String methodString) {
        FileChooser window = new FileChooser();
        window.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv Files", "*.csv"));
        File csvFile = window.showOpenDialog(null);
        int size = fileParser.getMethods().size();
        int index = 0;
        boolean searching = true;
        while(index < size) {
            Method method = fileParser.getMethods().get(index);
            if(method.toString().equals(methodString)) {
                fileParser.getMethods().get(index).setCsvFile(csvFile);
                //searching = false;
            }
            //TEST
            if(fileParser.getMethods().get(index).getCsvFile() == null) {
                System.out.println("Method CSV: null");
            }
            else {
                System.out.println("Method CSV: " + fileParser.getMethods().get(index).getCsvFile().getAbsolutePath());
            }
            index++;
        }
    }

    /**
     * Clears all the instance variables in the Controller class
     */
    public void clearController() {
        sourceFiles = new HashSet<>();
        destinationFile = null;
        fileParser.setMethods(new ArrayList<>());
        fileParser.setDependencies(new HashSet<>());
    }

    /**
     * Serializes the Preference object and allows the user to load up preferences they used on the last program
     * execution and select as well as save pre-defined destination paths every time they open and close the program.
     * This method is what essentially stores the data for next time (aka serialize)
     */
    public void serializePreference() {
        try {
            FileOutputStream fileOut = new FileOutputStream("preference.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(defaultPreference);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in preference.ser");
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Deserializes the Preference object and allows the user to load up preferences they used on the last program
     * execution and select as well as save pre-defined destination paths every time they open and close the program.
     * This method is what essentially loads the data for the last time the preferences were set.
     * @return
     */
    public Preference deserializePreference() {
        Preference p = null;
        try {
            FileInputStream fileIn = new FileInputStream("preference.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            p = (Preference) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("Did not read serialized file");
            p = new Preference();
        } catch (ClassNotFoundException c) {
            System.out.println("Preference class not found");
            c.printStackTrace();
        }
        //TESTS
        p.printPreferences();
        p.printDestinationFiles();
        return p;
    }

    /**
     * TEST METHOD
     */
    public void printSourceFiles() {
        System.out.println(".CPP and .H Source Files");
        if(!sourceFiles.isEmpty()) {
            Iterator<File> itty1 = sourceFiles.iterator();
            while (itty1.hasNext()) {
                File f = itty1.next();
                System.out.println(f.getAbsolutePath());
            }
            System.out.println();
        }
        else {
            System.out.println("No files selected");
        }
        System.out.println();
    }

    /**
     * TEST METHOD
     */
    public void printTextFixturePreferences() {
        System.out.println("Current Test Fixture Preferences: ");
        System.out.println(compilerChoice);
        System.out.println(executableName);
        System.out.println(stringDefault);
        System.out.println(characterDefault);
        System.out.println(integerDefault);
        System.out.println(doubleDefault);
        System.out.println(booleanDefault + "\n");
    }
}