package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class FileParser {

    public File[] parseFiles(String projectName, File[] projectFiles) {
        return projectFiles;
    }

    /**
     * Reads a c++ file and creates a list of all the project files and c++ libraries the class depends on.
     *
     * @param cppFile The c++ file to be read
     * @return The list of project files and c++ libraries that this class files depends on
     * @throws IOException If and I/O exception occurred when attempting to read the file
     */
    private Dependence makeDependance(File cppFile) throws IOException {
        // Gets the string of the file name without its file type signifier
        String className = cppFile.getName().substring(0, cppFile.getName().indexOf('.'));
        HashSet<String> dependencies = new HashSet<>();
        HashSet<String> libraries = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cppFile))) {
            String line = br.readLine();
            // Reads the whole file for now
            while (line != null) {
                line = line.trim();
                if (line.startsWith("#include")) {
                    // Gets just the class name after "#includes"
                    String shortLine = line.substring(8).trim();
                    // Libraries should be enclosed in <> while project classes should be enclosed in ""
                    switch (shortLine.charAt(0)) {
                        case '<':
                            // Checks to see if it was noted with .h or not;
                            // Strips off the file type if any
                            libraries.add(shortLine.substring(1, shortLine.indexOf('.') == -1 ? shortLine.indexOf('>') : shortLine.indexOf('.')));
                            break;
                        case '"':
                            // Again checks to see if it was noted with .h;
                            // If it was it will strip the file type from the name
                            dependencies.add(shortLine.substring(1, shortLine.indexOf('.') == -1 ? shortLine.lastIndexOf('"') : shortLine.indexOf('.')));
                            break;
                        default:
                            throw new RuntimeException("The read #include line does not appear to be formatted properly.");
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            // Log Error
            throw e;
        } catch (IOException e) {
            // Log Error
            throw e;
        }

        // Deals with empty ArrayLists
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

    /*
    Reads a C++ file and finds each method declared in the file and its header files, turning them into
    a Method object
    @param cppFile the java.io.File to be searched through
    @returns a list of the file's methods
     */
    private Method[] makeMethods(File cppFile, File hFile) throws IOException{
        String regex = "[A-Za-z]+[ \t]+[A-Za-z]+[ \t]*\\([ \t]*([A-Za-z]+[ \t]+[A-Za-z]+[ \t]*,?)*\\)[ \t]*;";
        ArrayList<Method> methods = new ArrayList<>();
        String className = cppFile.getName().substring(0,cppFile.getName().indexOf('.'));
        String currentReturnType, currentMethodName;
        String[] currentParamTypes;

        try (BufferedReader br = new BufferedReader(new FileReader(cppFile))) {
            String line = br.readLine();
            // Reads the whole file for now
            while (line != null) {
                line = line.trim();
                if(line.matches(regex)){
                    currentReturnType = line.substring(0, line.indexOf(' ') == -1 ? line.indexOf('\t') : line.indexOf(' ')).trim();
                    line = line.substring(line.indexOf(' ') == -1 ? line.indexOf('\t') : line.indexOf(' ')).trim();
                    currentMethodName = line.substring(0, line.indexOf('(')).trim();
                    line = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
                    currentParamTypes = line.split(",");
                    for(int i = 0; i < currentParamTypes.length; i++) {
                        currentParamTypes[i] = currentParamTypes[i].trim();
                        currentParamTypes[i] = currentParamTypes[i].substring(0, currentParamTypes[i].indexOf(' ') == -1 ? currentParamTypes[i].indexOf('\t') : currentParamTypes[i].indexOf(' ')).trim();
                    }

                    methods.add(new Method(className, currentReturnType, currentMethodName, currentParamTypes));
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            // Log Error
            throw e;
        } catch (IOException e) {
            // Log Error
            throw e;
        }

        return (Method[])methods.toArray();
    }


}
