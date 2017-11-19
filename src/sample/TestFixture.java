package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An extendable class made to model a test fixture for testing a particular C++ file with
 * @author Axolotl Development Team
 */
public class TestFixture implements java.io.Serializable {

    /*
    The user-specified name of this test fixture
     */
    private String fixtureName;
    /*
    Name of compiler to compile all .cpp source code with. Default to g++
     */
    private String compiler;
    /*
    List of flags for source code to be compiled with. Default to "-c"
     */
    private String flags;
    /*
    Name of the final executable for this project when the makefile links all object files, default is "executable"
     */
    private String finalExecutableName;
    /*
    Default list of files to have unit tests generated for
     */
    private ArrayList<File> testSuite;

    /*
       Hashmap of methods that need to be tested with particular parameters mapped to a .csv file with the parameters stored
       Format of paramlist in .csv (for a function with 2 parameters being tested 3 times) should be:
       test1param1, test1param2
       test2param1, test2param2
       test3param1, test3param2

       TOBE used in actual unit test generation
        */
    private HashMap<Method, File> inputValues;

    /*
       Hashmap of methods that need to output particular values mapped to a .csv file with the parameters stored
       Output values in the file for each test to occur should be newline-separated
       TOBE used in actual unit test generation
        */
    private HashMap<Method, File> outputValues;


    public TestFixture(){
        fixtureName = "TF";
        compiler = "g++";
        flags = "-c";
        finalExecutableName = "executable";
        testSuite = new ArrayList<File>();
        inputValues = new HashMap<Method, File>();
        outputValues = new HashMap<Method, File>();
    }

    public TestFixture(String fixtureName, String compiler, String flags, String finalExecutableName, ArrayList<File> testSuite,
                        HashMap<Method, File> inputValues, HashMap<Method,File>outputValues){
        this.fixtureName = fixtureName;
        this.compiler = compiler;
        this.flags = flags;
        this.finalExecutableName = finalExecutableName;
        this.testSuite = testSuite;
        this.inputValues = inputValues;
        this.outputValues = outputValues;
    }


    /*
    Getter for the name of this test fixture
    @returns the name of the test fixture
     */
    public String getFixtureName() {
        return fixtureName;
    }

    /*
    Setter for the name of this text fixture
    @param fixturename the new name of the test fixture
     */
    public void setFixtureName(String fixtureName) {
        this.fixtureName = fixtureName;
    }

    /*
    Getter for the name of the compiler
    @returns the name of the compiler
     */
    public String getCompiler() {
        return compiler;
    }

    /*
    Setter for the name of the compiler
    @param compiler the name of the new compiler
     */
    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    /*
    Getter for the name of the flags used in compiling
    @returns the name of the flags used in compiling
     */
    public String getFlags() {
        return flags;
    }

    /*
    Setter for the compilation flags
    @param flags the names of the flags
     */
    public void setFlags(String flags) {
        this.flags = flags;
    }

    /*
    Getter for the name of the final executable
    @returns the name of the final executable
     */
    public String getFinalExecutableName() {
        return finalExecutableName;
    }

    /*
    Setter for the name of the final executable
    @param finalExecutableName the new name of the final executable file
     */
    public void setFinalExecutableName(String finalExecutableName) {
        this.finalExecutableName = finalExecutableName;
    }

    /*
    Getter for the input files to be used when generating tests
    @returns the input files to be used when generating tests
     */
    public ArrayList<File> gettestSuite() {
        return testSuite;
    }

    /*
    Setter for the input files to be used when generating tests
    @param inputFiles the input files to be used when generating tests
     */
    public void setTestSuite(ArrayList<File> inputFiles) {
        testSuite = inputFiles;
    }

    /*
      Getter for the methods to be tested with particular expected outputs
      @returns a HashMap where methods are mapped to a file full of outputs they should produce
    */
    public HashMap<Method, File> getOutputValues() {
        return outputValues;
    }

    /*
      Getter for the methods to be tested with particular parameters
      @param outputValues a HashMap where methods are mapped to a file full of outputs they should produce
    */
    public void setOutputValues(HashMap<Method, File> outputValues) {
        this.outputValues = outputValues;
    }

    /*
      Getter for the methods to be tested with particular parameters
      @returns a HashMap where methods are mapped to a file full of parameters they should be tested with
    */
    public HashMap<Method, File> getInputValues() {
        return inputValues;
    }

    /*
      Getter for the methods to be tested with particular parameters
      @param inputValues a HashMap where methods are mapped to a file full of parameters they should be tested with
    */
    public void setInputValues(HashMap<Method, File> inputValues) {
        this.inputValues = inputValues;
    }


}
