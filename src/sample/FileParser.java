package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * A statically used class that takes an array of cpp and header files and reads them;
 * This class parses for information about the classes's methods and what it includes.
 *
 * @author Axolotl Development Team
 */
public class FileParser {

    //Methods Field Declaration
    private ArrayList<Method> methods;
    //Dependencies Field Declaration
    private HashSet<Dependence> dependencies;

    /**
     * Constructor for the FileParser class that initializes methods and dependencies instance variables
     */
    public FileParser() {
        methods = new ArrayList<>();
        dependencies = new HashSet<>();
    }

    /**
     * Receives the files to be parsed and extracts the necessary information;
     * Currently prints that information to the console, but will ultimately pass the information to the file writers.
     *
     * @param projectFiles The array of files to be read.
     * @return The projectFiles array at the current time;
     * Will ultimately return the makefiles and the unit test and test fixture files.
     * @throws IOException Thrown if an IOException was experienced by BufferedReader reading a passed file.
     */
    public void parseFilesAndGenerateOutputFiles(File[] projectFiles, File destination) throws IOException {
        for (File cFile : projectFiles) {
            if (cFile.getName().endsWith(".cpp"))
                dependencies.add(makeDependence(cFile));
            else if (cFile.getName().endsWith(".h"))
                methods.addAll(Arrays.asList(makeMethods(cFile)));
            else
                throw new IOException("An unexpected file has been passed.");
        }
        MakeFileWriter.setCompiler("g++");
        MakeFileWriter.setFlags("-c");
        MakeFileWriter.writeMakefile(dependencies, "executable", destination);
        consoleTestBecauseWeDontKnowHowToUseJUnitRightNow(methods, dependencies);
    }

    /**
     * Reads a c++ file and creates a list of all the project files and c++ libraries the class depends on.
     *
     * @param cppFile The c++ file to be read
     * @return The list of project files and c++ libraries that this class files depends on
     * @throws IOException If and I/O exception occurred when attempting to read the file
     */
    private static Dependence makeDependence(File cppFile) throws IOException {
        // Gets the string of the file name without its file type signifier
        String className = cppFile.getName().substring(0, cppFile.getName().indexOf('.'));
        HashSet<String> dependencies = new HashSet<>();
        HashSet<String> libraries = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cppFile))) {
            String line = br.readLine();
            // Reads the whole file
            while (line != null) {
                // Cuts any line comments out of the considered line
                if (line.contains("//"))
                    line = line.substring(0, line.indexOf("//"));
                line = line.trim();
                /* Looks to see if the signifier "#include" heads the line;
                   If so, it reads the line;
                   If not, it ignores the line.
                 */
                if (line.startsWith("#include")) {
                    // Gets just the class name after the signifier "#includes"
                    String shortLine = line.substring(8).trim();
                    // Libraries should be enclosed in <> while project classes should be enclosed in ""
                    switch (shortLine.charAt(0)) {
                        case '<':
                            // Strips .h if it exists in the include deceleration
                            libraries.add(shortLine.substring(1, shortLine.indexOf('.') == -1 ?
                                    shortLine.indexOf('>') : shortLine.indexOf('.')));
                            break;
                        case '"':
                            // Strips .h if it exists in the include deceleration
                            dependencies.add(shortLine.substring(1, shortLine.indexOf('.') == -1 ?
                                    shortLine.lastIndexOf('"') : shortLine.indexOf('.')));
                            break;
                        default:
                            /* C++ #include signifier is very syntactically strict;
                               We can expect any compilable code to never cause this exception to be thrown.
                             */
                            throw new RuntimeException("The #include line does not appear to be formatted properly.");
                    }
                }
                // Reads the next line
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            Main.LOGGER.severe("Somehow, a file that made its way into the FileParser was not found." +
                    "These files should have ultimately come from a browse functionality." +
                    "This should not be possible.");
            throw e;
        } catch (IOException e) {
            Main.LOGGER.warning("An IOException was caught processing: " + cppFile.getName() +
                    ". This occurred while parsing for methods.");
            throw e;
        }

        // Deals with empty sets, setting them to an array of size 1 with the empty string in it
        String[] depArray, libArray;
        if (dependencies.isEmpty()) {
            depArray = new String[1];
            depArray[0] = "";
        } else {
            depArray = new String[dependencies.size()];
            dependencies.toArray(depArray);
        }
        if (libraries.isEmpty()) {
            libArray = new String[1];
            libArray[0] = "";
        } else {
            libArray = new String[libraries.size()];
            libraries.toArray(libArray);
        }
        return new Dependence(className, depArray, libArray);
    }

    /**
     * Reads a C++ file and finds each method declared in the file, turning them into a Method object;
     * The methods must be specifically declared, with no body, for this method to be able to read them;
     * A header file is recommended, but results will be given with any file that declares methods.
     *
     * @param hFile The header file to be searched through;
     *              Will accept any file in actuality,
     *              However any file without a properly declared method will yield no results.
     * @return a list of the passed file's methods
     */
    private static Method[] makeMethods(File hFile) throws IOException {
        /* This regex represents methodReturnType methodName(paramType1 param1, paramType2 param2,...);
           The important thing is the parenthesis and the white spaces;
           Even though this regex could allow methods with improperly formatted parts,
           ie Stri[]ng or a method name with illegal characters,
           these uncompilable parts are not expected in the passes files.
         */
        String regex = "\\S+\\s+\\S+\\s*\\(\\s*(\\S+\\s+\\S+\\s*,?\\s*)*\\)\\s*;";
        ArrayList<Method> methods = new ArrayList<>();
        // Grabs the class name by taking every part before the file's type
        String className = hFile.getName().substring(0, hFile.getName().indexOf('.'));
        String currentReturnType, currentMethodName;
        String[] currentParamTypes;

        try (BufferedReader br = new BufferedReader(new FileReader(hFile))) {
            String line = br.readLine();
            // Reads the whole file
            while (line != null) {
                // Cuts any line comments out of the considered line
                if (line.contains("//"))
                    line = line.substring(0, line.indexOf("//"));
                /* Extends the considered string to include the next line if an open parenthesis was not closed;
                   It is possible that, do to page space constraints,
                   the parameters were declared across different lines;
                   This part accounts for that possibility.
                 */
                while (line.contains("(") && !line.contains(")")) {
                    line += " " + br.readLine().trim();
                    if (line.contains("//"))
                        line = line.substring(0, line.indexOf("//"));
                }
                line = line.trim();
                /* Checks if the considered string mates the regex defined above;
                   If so, begins to break apart and store the information of the line;
                   If not, the line is ignored.
                 */
                if (line.matches(regex)) {
                    /* Gets the first piece of information separated by a space or tab;
                       This piece of information is the method's return type.
                     */
                    currentReturnType = line.substring(0, line.indexOf(' ') == -1 ?
                            line.indexOf('\t') : line.indexOf(' ')).trim();
                    // Trims down the considered string to removed the information recently stored
                    line = line.substring(line.indexOf(' ') == -1 ? line.indexOf('\t') : line.indexOf(' ')).trim();
                    /* Gets the next piece of information just before the open parenthesis, ignoring whitespaces;
                       This piece of information is the method's name.
                     */
                    currentMethodName = line.substring(0, line.indexOf('(')).trim();
                    /* Trims down the considered string to removed the information recently stored;
                       Also removes anything past the close parenthesis;
                       What we should have now is the list of method parameters.
                     */
                    line = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
                    // Splits the method parameters around any commas and puts the pieces into an array
                    currentParamTypes = line.split(",");
                    for (int i = 0; i < currentParamTypes.length; i++) {
                        currentParamTypes[i] = currentParamTypes[i].trim();
                        /* Accounts for the possibility that there are no parameters;
                           If there are parameters,
                           represented by currentParamTypes[0] not containing the empty string,
                           the parameter type is separated from the parameter name by reading for a white space;
                           The parameter name is discarded.
                         */
                        if (!currentParamTypes[i].isEmpty())
                            currentParamTypes[i] = currentParamTypes[i].substring(0,
                                    currentParamTypes[i].indexOf(' ') == -1 ?
                                            currentParamTypes[i].indexOf('\t') :
                                            currentParamTypes[i].indexOf(' ')).trim();
                    }
                    methods.add(new Method(className, currentReturnType, currentMethodName, currentParamTypes));
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            Main.LOGGER.severe("Somehow, a file that made its way into the FileParser was not found." +
                    "These files should have ultimately come from a browse functionality." +
                    "This should not be possible.");
            throw e;
        } catch (IOException e) {
            Main.LOGGER.warning("An IOException was caught processing: " + hFile.getName() +
                    ". This occurred while parsing for methods.");
            throw e;
        }
        Method[] methodsArray = new Method[methods.size()];
        methods.toArray(methodsArray);
        return methodsArray;
    }

    /**
     * A temporary test method that prints all parsed information to the console;
     * Will ultimately be removed and its functionality will be covered by JUnit testing.
     *
     * @param methods      The collection of methods to be printed.
     * @param dependencies The collection of dependencies to be printed.
     */
    private static void consoleTestBecauseWeDontKnowHowToUseJUnitRightNow(ArrayList<Method> methods,
                                                                          HashSet<Dependence> dependencies) {
        methods.forEach(n -> System.out.println(n.toString()));
        dependencies.forEach(n -> System.out.println(n.toString()));
    }
}