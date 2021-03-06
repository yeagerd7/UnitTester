package sample;

/**
 * Stores the dependence list of one class for easy data transfer.
 *
 * @author Axolotl Development Team
 */
public class Dependence implements Comparable<Dependence>{

    /*
     * className - The name of the class this dependency list refers to.
     * dependencies - The names of the classes that this class depends on.
     */
    private String className;
    private String[] dependencies, libraries;

    /**
     * Creates a new Dependence object.
     *
     * @param className    The name of the class this dependence list refers to.
     * @param dependencies The list of classes this class depends on.
     * @param libraries    The list of c++ libraries that this class uses.
     */
    Dependence(String className, String[] dependencies, String[] libraries) {
        this.className = className;
        this.dependencies = new String[dependencies.length];
        this.libraries = new String[libraries.length];
        System.arraycopy(dependencies, 0, this.dependencies, 0, this.dependencies.length);
        System.arraycopy(libraries, 0, this.libraries, 0, this.libraries.length);
    }

    /**
     *
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @return
     */
    public String[] getDependencies() {
        String[] copy = new String[dependencies.length];
        System.arraycopy(dependencies, 0, copy, 0, copy.length);
        return copy;
    }

    /**
     *
     * @return
     */
    public String[] getLibraries() {
        String[] copy = new String[libraries.length];
        System.arraycopy(libraries, 0, copy, 0, copy.length);
        return copy;
    }

    /**
     * Returns in the format of className: <library1> <library2>... "dependence1" "dependence2"...
     *
     * @return A string representation of this classes fields.
     */
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(className).append(": ");
        for (String cLib : libraries)
            toReturn.append("<").append(cLib).append("> ");
        for (String cDep : dependencies)
            toReturn.append("\"").append(cDep).append("\" ");
        toReturn = new StringBuilder(toReturn.toString().trim());
        return toReturn.toString();
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Dependence o) {
        return className.compareTo(o.getClassName());
    }

    /**
     *
     * @return
     */
    public String toMakeString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(className + ".o: " + className + ".cpp");
        for(int i = 0; i < dependencies.length; i++)
            toReturn.append(" " + dependencies[i] + ".h");
        toReturn.append("\n\t$(CC) $(FLAGS) " + className + ".cpp");
        for(int i = 0; i < dependencies.length; i++)
            toReturn.append(" " + dependencies[i] + ".cpp");
        toReturn.append("\n\n");
        return toReturn.toString();
    }
}