package main.java;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The type Util.
 */
public class Util {


    /**
     * Create file file.
     * create an empty source file
     *
     * @param name the name
     * @return the file
     */

    public static File createFile(final String name) {

        File sourceFile = new File("/home/sa/IdeaProjects/SortingAlgorithms/HW6/src/main/java/SomeClass.java"); //File.createTempFile("Hello", ".java");

        return sourceFile;
    }

    /**
     * Generate code string.
     * generate the source code, using the source filename as the class name
     *
     * @param sourceFile the source file
     * @param sourceCode the source code
     * @return the string
     */
    public static String generateCode(File sourceFile, final String sourceCode) {
        String classname = sourceFile.getName().split("\\.")[0];

        // write the source code into the source file
        try {
            FileWriter writer = new FileWriter(sourceFile);

            writer.write(sourceCode);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classname;
    }

    /**
     * Compile source file file.
     *
     * @param sourceFile the source file
     * @return the file
     */
    public static File compileSourceFile(File sourceFile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        try {
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).
                call();
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parentDirectory;
    }

    /**
     * Load compiled class.
     *
     * @param parentDirectory the parent directory
     */
    public static void loadCompiledClass(File parentDirectory) {
        URLClassLoader classLoader = null;
        try {
            classLoader = URLClassLoader.newInstance(new URL[]{parentDirectory.toURI().toURL()});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Class<?> aClass = null;
        try {
            aClass = classLoader.loadClass("SomeClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // call a method on the loaded class
        Method[] methods = aClass.getDeclaredMethods();
        for (Method m : methods) {
            try {
                System.out.println("calling method of class " + m.getName());
                m.invoke(aClass.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Gets input from console
     * if exit it calls break
     *
     * @return the input
     */
    public static String getInput() {
        StringBuilder stringBuilder = new StringBuilder();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("exit")) {
                break;
            } else {
                stringBuilder.append(line).append("\n");
            }
        }
        scanner.close();
        return stringBuilder.toString();
    }

}

