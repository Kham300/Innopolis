package main.java;

/**
 * The type Reflection utils.
 */
public class ReflectionUtils {

    /**
     * Gets string as object.
     *
     * @param typeName the type name
     * @param value    the value
     * @return the string as object
     */
    public static Object getStringAsObject(String typeName, String value) {
        if (typeName.equals("class java.lang.String")) {
            return value;
        }
        if (typeName.equals("int")) {
            return getStringAsInteger(value);
        }

        if (typeName.equals("long")) {
            return getStringAsLong(value);
        }
        if (typeName.equals("double")) {
            return getStringAsDouble(value);
        }
        if (typeName.equals("float")) {
            return getStringAsFloat(value);
        }
        throw new RuntimeException("Unrecognized type encountered:" + typeName);
    }

    /**
     * Gets string as integer.
     *
     * @param value the value
     * @return the string as integer
     */
    public static Integer getStringAsInteger(String value) {
        return new Integer(Integer.parseInt(value));
    }

    /**
     * Gets string as long.
     *
     * @param value the value
     * @return the string as long
     */
    public static Long getStringAsLong(String value) {
        return new Long(Long.parseLong(value));
    }

    /**
     * Gets string as double.
     *
     * @param value the value
     * @return the string as double
     */
    public static Double getStringAsDouble(String value) {
        return new Double(Double.parseDouble(value));
    }

    /**
     * Gets string as float.
     *
     * @param value the value
     * @return the string as float
     */
    public static Float getStringAsFloat(String value) {
        return new Float(Float.parseFloat(value));
    }

}

