package main.java;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * The type Class info.
 * Given an object manipulate it using reflection
 */
public class ClassInfo {
    /**
     * The Xml node name.
     */
    public String xmlNodeName;
    private Class clazz;

    private List<String> fieldList = new ArrayList<>();
    private List<FieldInfo> fieldInfoList = new ArrayList<>();
    private Map<String, FieldInfo> fieldInfoMap = new HashMap<>();

    private static boolean xmlMapInitialized = false;
    private static Map<String, FieldInfo> m_xmlNameMap = new HashMap<>();

    /**
     * Instantiates a new Class info.
     * Primary constructor
     * @param inClass the in class
     * @throws NoSuchMethodException the no such method exception
     * @throws NoSuchFieldException  the no such field exception
     */
    public ClassInfo(Class inClass) throws NoSuchMethodException, NoSuchFieldException {
        clazz = inClass;
        Field[] fieldArray = clazz.getDeclaredFields();
        for (Field thisField : fieldArray) {
            String fieldName = thisField.getName();
            registerField(fieldName);
        }
        xmlNodeName = clazz.getName();
    }


    /**
     * Instantiates a new Class info.
     *
     * @param bean the bean
     * @throws NoSuchMethodException the no such method exception
     * @throws NoSuchFieldException  the no such field exception
     */
    public ClassInfo(Person bean) throws NoSuchMethodException, NoSuchFieldException {
        this(bean.getClass());
    }


    /**
     * Gets field names.
     *  Give me all the field names for this bean in a string format
     * @return the field names
     */
    public Iterator<String> getFieldNames() {
        return fieldList.iterator();
    }

    /**
     * Register mapping.
     * Register an xml mapping for a particualr field
     * This is like supplying metadata to the field
     *
     * @param fieldName   the field name
     * @param xmlName     the xml name
     * @param isAttribute the is attribute
     */
    public void registerMapping(String fieldName, String xmlName, boolean isAttribute) {
        FieldInfo fi = fieldInfoMap.get(fieldName);
        if (fi == null) {
            throw new RuntimeException("No field information found for field:" + fieldName);
        }
        fi.xmlName = xmlName;
        fi.attribute = isAttribute;
    }

    /**
     * Un register mapping.
     * Remove a field from xml mapping
     *
     * @param fieldName the field name
     */
    public void unRegisterMapping(String fieldName) {
        FieldInfo fi = fieldInfoMap.get(fieldName);
        if (fi == null) {
            throw new RuntimeException(
                    "No field information found for field:" + fieldName);
        }
        fieldInfoMap.remove(fi);
    }

    /**
     * Gets field info.
     * Access the fieldinfo for a more closer manipulation
     *
     * @param xmlName the xml name
     * @return the field info
     */
    public FieldInfo getFieldInfo(String xmlName) {
        initializeXmlMap();
        FieldInfo fi = m_xmlNameMap.get(xmlName);
        return fi;
    }


    /**
     * Gets value as string.
     *
     * @param fieldName the field name
     * @param o         the o
     * @return the value as string
     * @throws InvocationTargetException the invocation target exception
     * @throws IllegalAccessException    the illegal access exception
     */
    public String getValueAsString(String fieldName, Object o) throws InvocationTargetException, IllegalAccessException {
        FieldInfo fi = fieldInfoMap.get(fieldName);
        return fi.getValueAsString(o);

    }


    private void initializeXmlMap() {
        if (xmlMapInitialized == true) return;

        Iterator<FieldInfo> fieldInfos = getFieldInfos();
        while (fieldInfos.hasNext()) {
            FieldInfo fi = fieldInfos.next();
            String xmlName = fi.xmlName;
            m_xmlNameMap.put(xmlName, fi);
        }
    }

    /**
     * Gets field infos.
     *  Give me all the field infos for this class
     *
     * @return the field infos
     */
    public Iterator<FieldInfo> getFieldInfos() {
        return fieldInfoList.iterator();
    }

    private void registerField(String fieldName) throws NoSuchMethodException, NoSuchFieldException {
        fieldList.add(fieldName);
        FieldInfo fi = new FieldInfo(clazz, fieldName);
        this.fieldInfoMap.put(fieldName, fi);
        fieldInfoList.add(fi);
    }

    /**
     * Serialize.
     * Converting a class to a string
     *
     * @param o the o
     */
    public void serialize(Person o) {
        //Make sure the types are right
        String myClassName = clazz.getName();
        String inClassName = o.getClass().getName();
        if (!(myClassName.equals(inClassName))) {
            throw new RuntimeException(
                    "Unexpected classname. Expecting "
                            + myClassName
                            + " but got "
                            + inClassName);
        }

        StringBuffer sbuf = new StringBuffer();

        sbuf.append(xmlNodeName).append("{");

        //Get all public fields
        Iterator<FieldInfo> fieldInfos = this.getFieldInfos();
        while (fieldInfos.hasNext()) {
            FieldInfo fi = fieldInfos.next();
            String fieldName = fi.name;
            String value;
            try {
                value = fi.getValueAsString(o);
            } catch (Exception x) {
                x.printStackTrace();
                value = "Exception. The message is:" + x.getMessage();
            }
            sbuf.append("\n\t\t")
                    .append(fieldName)
                    .append("=")
                    .append(value);
        }
        sbuf.append("}");
        writeToFile(sbuf.toString());
        System.out.println(sbuf.toString());
    }

    /**
     * Write to file.
     *
     * @param info the info
     */
    public void writeToFile(final String info) {
        try (FileOutputStream fos = new FileOutputStream("/home/sa/IdeaProjects/SortingAlgorithms/HW5/src/main/resources/notes.txt")) {
            // перевод строки в байты
            byte[] buffer = info.getBytes();

            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    /**
     * Read file hash map.
     *
     * @param path the path
     * @return the hash map
     */
    public Map readFile(final String path) {

        Map<String, String> result = new HashMap<>();
        String value, line;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()) {
            line = scanner.next();
            if (line.contains("{")) {
                value = line.substring(0, line.length() - 1);
                result.put("class", value);

            } else if (line.contains("}")) {
                String s = line.substring(0, line.length() - 1);
                String[] strings = s.split("=");
                result.put(strings[0], strings[1]);
            } else {
                String[] strings = line.split("=");
                result.put(strings[0], strings[1]);
            }
        }

        scanner.close();

        return result;
    }

    /**
     * Deserialize.
     *
     * @param path the path
     * @throws ClassNotFoundException the class not found exception
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     */
    public void deserialize(final String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, String> hashMap = readFile(path);

        Class c = Class.forName(hashMap.get("class"));
        Object o = c.newInstance();

        Field[] declaredFields = c.getDeclaredFields();

        for (Field f : declaredFields) {

            String key = f.getName();

            String value = hashMap.get(key);

            Object parameter = ReflectionUtils.getStringAsObject(f.getType().toString(), value);

            f.set(o, parameter);

        }

        Person person = (Person) o;

        System.out.println(person.toString());
    }
}


