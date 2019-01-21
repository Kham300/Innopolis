package main.java;

import main.java.io.Generator;

public class Main {
    public static void main(String[] args) {
        final String path = "/home/sa/IdeaProjects/SortingAlgorithms/HW3/src/main/resources/generated";

        Generator generator = new Generator();
        generator.getFiles(path,2,3, null, 1);

    }
}
