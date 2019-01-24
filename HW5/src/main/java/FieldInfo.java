package main.java;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * The type Field info.
 */
public class FieldInfo {

    /**
     * The Name.
     */
    public String name;
    /**
     * The Xml name.
     */
    public String xmlName;
    /**
     * The Attribute.
     */
    public boolean attribute;
    private Class clazz;

    /**
     * The Get accessor.
     */
    public Method getAccessor;
    /**
     * The Set accessor.
     */
    public Method setAccessor;
    private Class fieldType;

    /**
     * Gets value as string.
     *
     * @param o the o
     * @return the value as string
     * @throws InvocationTargetException the invocation target exception
     * @throws IllegalAccessException    the illegal access exception
     */
    String getValueAsString(Object o) throws InvocationTargetException, IllegalAccessException {
        Object reply = getAccessor.invoke(o, null);
        return reply.toString();
    }


    /**
     * Instantiates a new Field info.
     *
     * @param fieldClass  the field class
     * @param name        the name
     * @param xmlName     the xml name
     * @param isAttribute the is attribute
     * @throws NoSuchMethodException the no such method exception
     * @throws NoSuchFieldException  the no such field exception
     */
    public FieldInfo(Class fieldClass, String name, String xmlName, boolean isAttribute)
            throws NoSuchMethodException, NoSuchFieldException {
        this.name = name;
        this.xmlName = xmlName;
        attribute = isAttribute;
        clazz = fieldClass;
        fieldType = getFieldType(name);
        getAccessor = getAccessorGetMethod(name);
        setAccessor = getAccessorSetMethod(name);
    }


    /**
     * Instantiates a new Field info.
     *
     * @param fieldClass the field class
     * @param name       the name
     * @param xmlName    the xml name
     * @throws NoSuchMethodException the no such method exception
     * @throws NoSuchFieldException  the no such field exception
     */
    public FieldInfo(Class fieldClass, String name, String xmlName)
            throws NoSuchMethodException, NoSuchFieldException {
        this(fieldClass, name, xmlName, false);
    }

    /**
     * Instantiates a new Field info.
     *
     * @param fieldClass the field class
     * @param name       the name
     * @throws NoSuchMethodException the no such method exception
     * @throws NoSuchFieldException  the no such field exception
     */
    public FieldInfo(Class fieldClass, String name) throws NoSuchMethodException, NoSuchFieldException {
        this(fieldClass, name, name, false);
    }


    private Method getAccessorGetMethod(String fieldName) throws NoSuchMethodException {
        String firstPart = fieldName.substring(0, 1);
        String secondPart = fieldName.substring(1);
        String getMethodName = "get" + firstPart.toUpperCase() + secondPart;
        //System.out.println(getMethodName);
        return clazz.getDeclaredMethod(getMethodName, null);
    }

    private Method getAccessorSetMethod(String fieldName) throws NoSuchMethodException {
        String firstPart = fieldName.substring(0, 1);
        String secondPart = fieldName.substring(1);
        String getMethodName = "set" + firstPart.toUpperCase() + secondPart;
        //System.out.println(getMethodName);
        Class[] parameters = new Class[1];
        parameters[0] = this.fieldType;

        return clazz.getDeclaredMethod(getMethodName, parameters);
    }

    private Class getFieldType(String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        Class fieldClass = field.getType();
        return fieldClass;
    }

    private String getTypeName(String fieldName) {
        return this.fieldType.getName();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

}
