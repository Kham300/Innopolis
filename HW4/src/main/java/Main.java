package main.java;


public class Main {
    /**
     * The constant outputPath.
     */
    final static String outputPath = "/home/sa/IdeaProjects/SortingAlgorithms/HW4/src/main/resources/generated/";


    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        for (int i = 0; i < 5; i ++){
            Util.getOccurencies(null, null, outputPath);
        }

        stopWatch.stop();

        System.out.println("Time: " + stopWatch.toString());
    }


}
