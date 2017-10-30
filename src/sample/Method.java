package sample;

/**
 * Stores necessary data of one method for easy transfer.
 *
 * @author Axolotl Development Team
 */
public class Method {

    /*
     * className - The name of the class that this method belongs to.
     * returnType - The name of the data type that this method returns.
     * methodName - The name of the method
     * paramTypes - The data types of the method's parameters.
     */
    private String className, returnType, methodName;
    private String[] paramTypes;

    /**
     * Creates a new Method object.
     *
     * @param className  The name of the class this method belongs to.
     * @param returnType The return type of the method.
     * @param methodName The name of the method
     * @param paramTypes The types of the method's parameters.
     */
    Method(String className, String returnType, String methodName, String[] paramTypes) {
        this.className = className;
        this.returnType = returnType;
        this.methodName = methodName;
        // Copies the values of the array
        this.paramTypes = new String[paramTypes.length];
        System.arraycopy(paramTypes, 0, this.paramTypes, 0, this.paramTypes.length);
    }

    public String getClassName() {
        return className;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getMethodName() { return methodName; }

    public String[] getParamTypes() {
        String[] copy = new String[paramTypes.length];
        System.arraycopy(paramTypes, 0, copy, 0, copy.length);
        return copy;
    }

    /**
     * Returns in the format of className: returnType methodName(paramType1, paramType2,...)
     *
     * @return A string representation of this classes fields.
     */
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(className).append(": ").append(returnType).append(" ").append(methodName).append("(");
        for (String cParam: paramTypes)
            toReturn.append(cParam).append(", ");
        toReturn = new StringBuilder(toReturn.toString().indexOf(',') == -1 ? toReturn + ")" : toReturn.substring(0, toReturn.toString().lastIndexOf(',')) + ")");
        return toReturn.toString();
    }
}
