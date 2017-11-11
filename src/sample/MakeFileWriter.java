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

    private static String tempFile = "C:\\\\Users\\lynne9\\Downloads\\makefile";

    //The compiler to be used- default is g++
    private static String compilerName = "g++";

    //Flags to compile with= default is that no flags are set
    private static String flags = "";

    public MakeFileWriter(){

    }


    public static File writeMakefile(HashSet<Dependence> depList, String exeName) throws IOException {

        File temp = new File(tempFile);
        temp.createNewFile();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(temp)))) {

            pw.println("#Variable for the compiler to be used\nCC=" + compilerName + "\n#Variable for the flags to use when compiling\nFLAGS=" + flags + "\n");
            pw.print("all: " + exeName + "\n\n" + exeName + ":");
            StringBuilder objectList = new StringBuilder();
            depList.forEach(c -> objectList.append(" " + c.getClassName() + ".o"));
            pw.print(objectList.toString() + "\n\t$(CC) " + objectList.toString() + " -o " + exeName + "\n\n");
            depList.forEach(c -> pw.println(c.getClassName() + ".o: " + c.getClassName() + ".cpp\n\t$(CC) $(FLAGS) " + c.getClassName() + ".cpp\n"));
            pw.print("clean:\n\trm *.o " + exeName);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        return temp;
    }

    public static void setCompiler(String newCompilerName){
        compilerName = newCompilerName;
    }

    public static void setFlags(String newFlags){
        flags = newFlags;
    }



}



