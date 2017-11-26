package sample;

import java.io.*;
import java.util.HashSet;

/**
 * Library class that uses the name of the main executable and its dependencies to generate a makefile that would create
 * the desired executable from given c++ files.
 * Compiler being used can be changed, as can flags used when compiling .cpp files into .o files.
 * @author Axolotl Development Team
 */
public class MakeFileWriter {

    /**
     * Creates a makefile for a project given a list of the dependencies for the project and a name for the
     * final executable
     * @param depList a HashSet of depencencies for each file the executable is dependant on
     * @param fixture exeName the test fixture containing the information pertainent to making this makefile
     * @param destination
     * @return
     * @throws IOException
     */
    public static File writeMakefile(HashSet<Dependence> depList, TestFixture fixture, File destination)
            throws IOException {
        File temp = new File(destination.getAbsolutePath() + "/makefile");
        temp.createNewFile();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(temp)))) {
            pw.println("#Variable for the compiler to be used\nCC=" + fixture.getCompiler() + "\n#Variable for the " +
                    "flags to use when compiling\nFLAGS=" + fixture.getFlags() + "\n");
            pw.print("all: " + fixture.getFinalExecutableName() + "\n\n" + fixture.getFinalExecutableName() + ":");
            StringBuilder objectList = new StringBuilder();
            depList.forEach(c -> objectList.append(" " + c.getClassName() + ".o"));
            pw.print(objectList.toString() + "\n\t$(CC) " + objectList.toString() + " -o " + fixture.getFinalExecutableName() + "\n\n");
            depList.forEach(c -> pw.print(c.toMakeString()));
            pw.print("clean:\n\trm *.o " + fixture.getFinalExecutableName());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        return temp;
    }
}



