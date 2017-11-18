package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
/**
 * Preference class creates a serializable Preference object that represents the set of preferences the user used during
 * the last program execution as well as a list of user-selected pre-defined destination paths.
 * @author Axolotl Development Team
 */
public class Preference implements java.io.Serializable {
    //serialVersionID Field Declaration
    private static final long serialVersionUID = 8563439202L;
    //Preferences ArrayList Field Declaration
    private ArrayList<Boolean> preferences;
    //Preferred Destination Path HashSet Field Declaration
    private HashSet<File> preferredDestinationPaths;

    /**
     * Constructor for the Preference class that initializes all its attributes
     */
    public Preference() {
        preferences = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            preferences.add(i, false);
        }
        preferredDestinationPaths = new HashSet<>();
    }

    /**
     * Accessor method for the preferences attribute and returns said attribute
     * @return preferences
     */
    public ArrayList<Boolean> getPreferences() {
        return preferences;
    }

    /**
     * Setter method for the preferences attribute that sets said attribute to an ArrayList object defined as a
     * parameter
     * @param preferences
     */
    public void setPreferences(ArrayList<Boolean> preferences) {
        this.preferences = preferences;
    }

    /**
     * Accessor method for the preferredDestinationPaths attribute and returns said attribute
     * @return preferredDestinationPaths
     */
    public HashSet<File> getPreferredDestinationPaths() {
        return preferredDestinationPaths;
    }

    /**
     * Setter method for the preferredDestinationPaths attribute that sets said attribute to an ArrayList object
     * defined as a paramater
     * @param preferredDestinationPaths
     */
    public void setPreferredDestinationPaths(HashSet<File> preferredDestinationPaths) {
        this.preferredDestinationPaths = preferredDestinationPaths;
    }

    /**
     * TEST METHOD
     */
    public void printPreferences() {
        for(int i = 0; i < 7; i++) {
            System.out.println("Preference " + i + ": "+ preferences.get(i));
        }
    }

    /**
     * TEST METHOD
     */
    public void printDestinationFiles() {
        if(!preferredDestinationPaths.isEmpty()) {
            Iterator<File> itty1 = preferredDestinationPaths.iterator();
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
