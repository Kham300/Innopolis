package main.java;


import algorithms.StopWatch;

public class Main {
    /**
     * The constant outputPath.
     */
    final static String outputPath = "/home/sa/IdeaProjects/SortingAlgorithms/HW4/src/main/resources/generated/";


    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Util.getOccurencies(null, null, outputPath);
        stopWatch.stop();

        System.out.println("Time: " + stopWatch.toString());
    }


}
