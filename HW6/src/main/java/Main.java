package main.java;

import java.io.File;

/**
 * The type Main
 *
 */
public class Main {
    private final static String CLASS_NAME = "SomeClass";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) {

        File file = Util.createFile(CLASS_NAME);

        System.out.println("Please input your class implementation: \n");
        System.out.println("Print exit after code!!! \n ");


        //TODO please enter this for compiling the class should named "SomeClass" for example :
        // public class SomeClass{ public void hello() { System.out.print("Hello world");}}
        String sourceCode = Util.getInput();


        System.out.println("generating file.java ....");
        Util.generateCode(file, sourceCode);

        System.out.println("compile the source file: ");
        File sourceFile = Util.compileSourceFile(file);

        System.out.println("Loading compiled class....");
        Util.loadCompiledClass(sourceFile);
    }
}
