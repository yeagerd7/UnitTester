package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class meant to be used by the FileParser class. Purpose of use is to generate a set of unit tests, one per .cpp file,
 * for a C++ project
 * @author Axolotl Development Team
 */
public class UnitTestWriter {

    /*
        All unit tests need some sort of header to differentiate them from other .cpp files so unit tests
        aren't generated for other unit tests.
        Should be in the form of a C++ comment.
         */
    public static final String unitTestHeader = "//Unit Test File";

    /*
    The absolute pathname of the folder the tests are to be written to
     */
    public File destination;

    /*
    Data structure to hold particular parameters regarding unit test generation
     */
    public TestFixture testFixture;

    public UnitTestWriter(File destination){
        this.destination = destination;
    }

    /*
    Method to take a set of Method objects and generate a set of unit tests for them.
    Separates out which method belongs to which file manually, then uses a subroutine to actually write
    the individual test files.
    @param methods an ArrayList of methods to be tested
    @param fixture TestFixture data structure containing information on how the tests are to be built
     */
    public void writeUnitTests(ArrayList<Method> methods, TestFixture fixture) throws IOException{
        //Holds each class name mapped to an arraylist of its methods
        HashMap<String, ArrayList<Method>> methodsByClass = new HashMap <String, ArrayList<Method>>();

        for(int i = 0; i<methods.size(); i++){
            if(!(methodsByClass.containsKey(methods.get(i).getClassName()))){
                methodsByClass.put(methods.get(i).getClassName(), new ArrayList<Method>());
            }
            methodsByClass.get(methods.get(i).getClassName()).add(methods.get(i));
        }

        Iterator it = methodsByClass.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, ArrayList<Method>> entry = (Map.Entry<String, ArrayList<Method>>) it.next();
            writeSingleTest(entry.getValue(), fixture);
        }
    }

    /*
    Subroutine to write each individual test file given the methods to be tested and certain parameters about the test
    @param methods an ArrayList of methods to be tested
    @param fixture TestFixture data structure containing information on how the tests are to be built
    @throws IOException
     */
    private void writeSingleTest(ArrayList<Method> methods, TestFixture fixture) throws IOException {
        File temp = new File(destination.getAbsolutePath() + "/" + methods.get(0).getClassName() + "TEST.cpp");
        temp.createNewFile();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(temp)))) {

            //TODO The actual writing
            //       O
            //     / | \
            //       |
            //      / \

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    /*
    Method to return the header for all unit tests
    @returns the header used for all CUT-generated unit tests
     */
    public static String getUnitTestHeader() {
        return unitTestHeader;
    }

    /*
    Method to return the destination folder
    @returns File object form of the destination path of the current unit test files
     */
    public File getDestination() {
        return destination;
    }

    /*Method to set the destination folder of the unit test files
    @param destination, the new destination folder for the unit test files
     */
    public void setDestination(File destination) {
        this.destination = destination;
    }


}
