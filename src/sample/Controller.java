package sample;

//Import Statements
import java.io.*;
import java.util.*;
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
    private HashSet<File> sourceFiles;

    //Destination File Field Declaration
    private File destinationFile;

    //Required Declaration for Singleton Pattern
    private static Controller singletonInstance = new Controller();

    /**
     * Private Singleton Constructor that generates an Controller object that initializes most of its fields upon
     * user manipulation in the FrontEndGUI class
     */
    private Controller(){
        sourceFiles = new HashSet<>();
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
     * parameter
     * @param newDestinationFile new destination directory
     */
    public void setDestinationFile(File newDestinationFile) {
        destinationFile = newDestinationFile;
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
        window.setInitialDirectory(new File("C:\\"));
        window.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(".cpp Files", "*.cpp"));
        List<File> tempSourceFiles = window.showOpenMultipleDialog(null);
        if (tempSourceFiles != null) {
            int size = tempSourceFiles.size();
            int index = 0;
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
                    AlertBox.simpleDisplay("Error: Duplicate File Name", "File: " + fileNameToCompare
                        + " appears to be a duplicate.  File will not be processed.");
                }
                index++;
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
        window.setInitialDirectory(new File("C:\\"));
        destinationFile = window.showDialog(null);
        return destinationFile;
    }

    /**
     * refreshSourceBrowse is called up following a button click on the front end GUI (graphical user interface) and a
     * @param fileListGUI
     * @return
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
     * TEST METHOD
     */
    private void printSourceFiles() {
        Iterator<File> itty1 = sourceFiles.iterator();
        while(itty1.hasNext()) {
            File f = itty1.next();
            System.out.println(f.getAbsolutePath());
        }
        System.out.println();
    }
}