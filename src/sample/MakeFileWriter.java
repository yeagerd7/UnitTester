package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Library class that uses the name of the main executable and its dependencies to generate a makefile that would create
 * the desired executable from given c++ files.
 * Compiler being used can be changed, as can flags used when compiling .cpp files into .o files.
 * @author Axolotl Development Team
 * Created by lynne9 on 11/11/2017.
 */
public class MakeFileWriter {

    private static String destFile = "C:\\\\Users\\lynne9\\Downloads\\makefile";

    //The compiler to be used- default is g++
    private static String compilerName = "g++";

    //Flags to compile with= default is that no flags are set
    private static String flags = "";


    /*
    Creates a makefile for a project given a list of the dependencies for the project and a name for the final executable.
    @param depList a HashSet of depencencies for each file the executable is dependant on
    @param exeName the desired name of the final executable
    @returns a java.io.File form of the created Makefile
     */
    public static File writeMakefile(HashSet<Dependence> depList, String exeName) throws IOException {

        File temp = new File(destFile + "\\makefile");
        temp.createNewFile();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(temp)))) {

            pw.println("#Variable for the compiler to be used\nCC=" + compilerName + "\n#Variable for the flags to use when compiling\nFLAGS=" + flags + "\n");
            pw.print("all: " + exeName + "\n\n" + exeName + ":");
            StringBuilder objectList = new StringBuilder();
            depList.forEach(c -> objectList.append(" " + c.getClassName() + ".o"));
            pw.print(objectList.toString() + "\n\t$(CC) " + objectList.toString() + " -o " + exeName + "\n\n");
            depList.forEach(c -> pw.print(c.toMakeString()));
            pw.print("clean:\n\trm *.o " + exeName);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        return temp;
    }

    /*
    Setter for the name of the compiler to be used when generating the makefile
    @param newCompilerName The name of the compiler as it would appear in the command-line, ex. "gcc" or "g++"
     */
    public static void setCompiler(String newCompilerName){
        compilerName = newCompilerName;
    }

    /*
    Setter for the list of flags for the compiler to be run with
    @param newFlags The list of flags as they would appear in the command-line, ex. "-c" or "-o -Wall"
     */
    public static void setFlags(String newFlags){
        flags = newFlags;
    }

    /*
    Setter for the destination of the final exectuable
    @param newDestFile The full pathname of the final executable file
     */
    public static void setDestinationFilepath(String newDestFile){
        destFile = newDestFile;
    }


}



