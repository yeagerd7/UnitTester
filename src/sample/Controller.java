package sample;

//Import Statements
import java.io.*;
import java.util.List;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * Controller class controls the GUI view and responds to inputs. Communicates with and organizes all parts.
 * Will utilize the singleton principle.
 * @author Axolotl Development Team
 */
public class Controller {
    //Source File List Field Declaration
    private List<File> sourceFiles;

    //Destination File Field Declaration
    private File destinationFile;

    //Required Declaration for Singleton Pattern
    private static Controller singletonInstance = new Controller();

    /**
     * Empty Constructor that generates an "Empty" object that initializes its fields upon user manipulation in the
     * FrontEndGUI class
     */
    private Controller(){}

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
    public List<File> getSourceFiles() {
        return sourceFiles;
    }

    /**
     * Setter method for 'sourceFiles' attribute that sets the value to a new source file list denoted as a
     * parameter
     * @param newSourceFiles new source file list
     */
    public void setSourceFiles(List<File> newSourceFiles) {
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
     * parameter
     * @param newDestinationFile new destination directory
     */
    public void setDestinationFile(File newDestinationFile) {
        destinationFile = newDestinationFile;
    }
    /**
     * sourceBrowse is called up following a button click on the front end GUI (graphical user interface) and allows
     * user to search their local machine for .cpp files
     * @param fileListGUI existing ListView<CheckBox> object from FrontEndGUI
     * @return fileListGUI populated and displaying .cpp files selected by the user
     */
    public ListView<CheckBox> sourceBrowse(ListView<CheckBox> fileListGUI) {
        FileChooser window = new FileChooser();
        window.setInitialDirectory(new File("C:\\"));
        window.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(".cpp Files", "*.cpp"));
        sourceFiles = window.showOpenMultipleDialog(null);
        if(sourceFiles != null) {
            int size = sourceFiles.size();
            CheckBox box;
            for(int i = 0; i < size; i++) {
                box = new CheckBox(sourceFiles.get(i).getAbsolutePath());
                box.setSelected(true);
                fileListGUI.getItems().add(box);
            }
        }
        return fileListGUI;
    }

    /**
     * destinationBrowse is called up following a button click on the front end GUI (graphical user interface) and
     * allows user to search their local machine for .cpp files
     * @return selectedFile
     */
    public File destinationBrowse() {
        DirectoryChooser window = new DirectoryChooser();
        window.setInitialDirectory(new File("C:\\"));
        destinationFile = window.showDialog(null);
        return destinationFile;
    }
}