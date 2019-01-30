package main.java;

import main.java.algorithms.BubbleSortJava8;
import main.java.algorithms.Generator;

import java.util.Arrays;

import static main.java.algorithms.QuickSortJava8.quickSort;

public class Main {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        final int numberOfElements = 1000;

        Generator generator = new Generator(numberOfElements);
        System.out.println("***Cравнение сортировок разной временной сложности для одинаковых входных данных ***");
        System.out.println("***Используя Stream api ***");
        System.out.println("====================================================================================");

        /**
         * BubbleSort one of the weakest
         */
        stopWatch.start();
        BubbleSortJava8.bubbleSort(generator.getArray());
        stopWatch.stop();
        System.out.println("BubbleSort for: " + numberOfElements + " elements");
        printElapsedTime(stopWatch);

        /**
         * QuickSort one of the fastest
         */
        stopWatch.start();
        quickSort(Arrays.asList(generator.getArray()));
        stopWatch.stop();
        System.out.println("=====================================");
        System.out.println("QuickSort for: " + numberOfElements + " elements");
        printElapsedTime(stopWatch);


    }

    private static void printElapsedTime(final StopWatch stopwatch) {
        if (stopwatch.isRunning()) {
            System.out.println("WARNING! Your stopwatch is still running!");
        } else // stopwatch not running
        {
            System.out.println("\t" + stopwatch.elapsed() + " nanoseconds");
        }
    }
}