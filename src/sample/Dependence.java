package sample;

/**
 * Stores the dependence list of one class for easy data transfer.
 *
 * @author Axolotl Development Team
 */
public class Dependence {

    /*
     * className - The name of the class this dependency list refers to.
     * dependencies - The names of the classes that this class depends on.
     */
    private String className;
    private String[] dependencies;

    /**
     * Creates a new Dependence object.
     *
     * @param className    The name of the class this dependence list refers to.
     * @param dependencies The list of classes this class depends on.
     */
    public Dependence(String className, String[] dependencies) {
        this.className = className;
        this.dependencies = new String[dependencies.length];
        System.arraycopy(dependencies, 0, this.dependencies, 0, this.dependencies.length);
    }

    public String getClassName() {
        return className;
    }

    public String[] getDependencies() {
        String[] copy = new String[dependencies.length];
        System.arraycopy(dependencies, 0, copy, 0, copy.length);
        return copy;
    }
}
