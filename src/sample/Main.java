package sample;
//import statements
import javafx.application.Application;
import javafx.stage.*;
import java.io.*;
import java.util.logging.*;

/**
 * Main Class that represents the BackEnd of the C++ Unit Test Generator Software.  Contains the main method/start()
 * that runs the program, a final, static LOGGER variable that can be accessed across the entire program, and a private
 * method to format said logger to ultimately send output to a TXT and an XML file.
 */
public class Main extends Application {
    //Logger object instance variable
    public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    //Main Method that launches execution of the program
    public static void main(String[] args) {launch(args);}

    /**
     * Effectively 'starts' the program, first by formatting the logger, creating the FrontEndGUI object and populating
     * the actual main window with a call to mainWindowDisplay() in the FrontEndGUI class.
     * @param primaryStage Main Window to be populated in the FrontEndGUI class
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        formatLogger();
        FrontEndGUI mainWindow = new FrontEndGUI();
        mainWindow.mainWindowDisplay(primaryStage);
        LOGGER.info("Program begins to execute,   Logger Formatted");
    }

    /**
     * Private method that formats the logger by creating 2 handlers (one user-related, one developer-related), and
     * sends their log messages to a 'UserLog.txt' file and a 'DeveloperLog.xml' depending upon severity.
     */
    private void formatLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        //Declare and Initialize Developer Log File Handler
        try {
            FileHandler developerLogTXT = new FileHandler("UserLog.txt");
            FileHandler developerLogXML= new FileHandler("DeveloperLog.xml");
            developerLogTXT.setLevel(Level.ALL); //Receives ALL log messages
            developerLogTXT.setFormatter(new SimpleFormatter());
            developerLogXML.setLevel(Level.CONFIG); //Receives CONFIG, INFO, WARNING, SEVERE log messages
            LOGGER.addHandler(developerLogTXT);
            LOGGER.addHandler(developerLogXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}