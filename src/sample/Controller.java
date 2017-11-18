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
    public FileParser getFileParser() { return fileParser; }

    /**
     * Accessor method for the 'defaultPreference attribute that returns said attribute.
     * @return defaultPreference
     */
    public Preference getDefaultPreference() {
        return defaultPreference;
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
     * Clears all the instance variables in the Controller class
     */
    public void clearController() {
        sourceFiles = new HashSet<>();
        destinationFile = null;
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
     * Serializes the Preference object and allows the user to load up preferences they used on the last program
     * execution and select as well as save pre-defined destination paths every time they open and close the program.
     * This method is what essentially stores the data for next time (aka serialize)
     */
    public void serializePreference()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("preference.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(defaultPreference);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in preference.ser");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    /**
     * Deserializes the Preference object and allows the user to load up preferences they used on the last program
     * execution and select as well as save pre-defined destination paths every time they open and close the program.
     * This method is what essentially loads the data for the last time the preferences were set.
     * @return
     */
    public Preference deserializePreference()
    {
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
     * saveBrowse is called up following a button click on the preferences window/menu and allow user to search their
     * local machine for a destination path they wanna save and adds it to the list of predefined destination paths in
     * the front end gui, upon it not being a duplicate,  and updates the preferred destinations list in the Preferences
     * object
     * @param preferredDestinations
     * @return preferredDestinations
     */
    public ListView<CheckBox> saveBrowse(ListView<CheckBox> preferredDestinations,TextField potentialDestination) {
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
     * @param defaultPaths
     * @param potentialDestination
     * @return temp;
     */
    public ChoiceBox<String> updateDefaultPaths(ChoiceBox<String> defaultPaths, TextField potentialDestination ) {
        ChoiceBox<String> temp = new ChoiceBox<>();
        Iterator<File> itty = defaultPreference.getPreferredDestinationPaths().iterator();
        while(itty.hasNext()) {
            String path = itty.next().getAbsolutePath();
            temp.getItems().add(path);
        }
        return temp;
    }

    /**
     * TEST METHOD
     */
    public void printSourceFiles() {
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
    }
}