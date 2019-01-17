package main.java;

import main.java.algorithms.BubbleSort;
import main.java.algorithms.Generator;
import main.java.algorithms.QuickSort;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        final int numberOfElements = 10000;

        Generator generator = new Generator(numberOfElements);
        QuickSort quickSort = new QuickSort();
        BubbleSort bubbleSort = new BubbleSort();
        Stopwatch stopwatch = Stopwatch.createUnstarted();

        System.out.println("***Cравнение сортировок разной временной сложности для одинаковых входных данных***");
        System.out.println("====================================================================================");

        /**
         * BubbleSort one of the weakest
         */
        stopwatch.start();
        bubbleSort.doSort(generator.getArray());
        stopwatch.stop();
        System.out.println("BubbleSort for: " + numberOfElements + " elements");
        printElapsedTime(stopwatch);

        /**
         * QuickSort one of the fastest
         */
        stopwatch.reset();
        stopwatch.start();
        quickSort.doSort(generator.getArray());
        stopwatch.stop();
        System.out.println("=====================================");
        System.out.println("QuickSort for: " + numberOfElements + " elements");
        printElapsedTime(stopwatch);


    }

    private static void printElapsedTime(final Stopwatch stopwatch) {
        if (stopwatch.isRunning()) {
            System.out.println("WARNING! Your stopwatch is still running!");
        } else // stopwatch not running
        {
            System.out.println("\t" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");
            System.out.println("\t" + stopwatch.elapsed(TimeUnit.NANOSECONDS) + " nanoseconds");
        }
    }

}
