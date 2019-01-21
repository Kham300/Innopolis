import algorithms.StopWatch;
import main.java.algorithms.BubbleSort;
import main.java.algorithms.Generator;
import main.java.algorithms.QuickSort;

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
        StopWatch stopwatch = new StopWatch();

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
        stopwatch.stop();
        stopwatch.start();
        quickSort.doSort(generator.getArray());
        stopwatch.stop();
        System.out.println("=====================================");
        System.out.println("QuickSort for: " + numberOfElements + " elements");
        printElapsedTime(stopwatch);


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
