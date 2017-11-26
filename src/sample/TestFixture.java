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
    Default list of methods to test
     */
    private ArrayList<Method> testSuite;

    /*
    Default values for each C++ data type
     */
    private String stringDefault;
    private Character characterDefault;
    private Integer integerDefault;
    private Double doubleDefault;
    private Boolean booleanDefault;

    /*
       Hashmap of methods that need to be tested with particular parameters mapped to a .csv file with the parameters stored
       Format of paramlist in .csv (for a function with 2 parameters being tested 3 times) should be:
       test1param1, test1param2
       test2param1, test2param2
       test3param1, test3param2

       TOBE used in actual unit test generation
        */
    private HashMap<Method, File> inputValues;


    public TestFixture(){
        fixtureName = "TF";
        compiler = "g++";
        flags = "-c";
        finalExecutableName = "executable";
        testSuite = new ArrayList<Method>();
        stringDefault = "Axolotl";
        characterDefault = 'X';
        integerDefault = 36;
        doubleDefault = 3.14;
        booleanDefault = true;
    }

    public TestFixture(String fixtureName, String compiler, String flags, String finalExecutableName, ArrayList<Method> testSuite){
        this.fixtureName = fixtureName;
        this.compiler = compiler;
        this.flags = flags;
        this.finalExecutableName = finalExecutableName;
        this.testSuite = testSuite;
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
    public ArrayList<Method> gettestSuite() {
        return testSuite;
    }

    /*
    Setter for the input files to be used when generating tests
    @param inputFiles the input files to be used when generating tests
     */
    public void setTestSuite(ArrayList<Method> inputFiles) {
        testSuite = inputFiles;
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


}
