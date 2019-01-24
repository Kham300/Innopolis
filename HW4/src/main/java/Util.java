package main.java;

import org.apache.commons.collections4.ListUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The type Util.
 */
public class Util {

    /**
     * Gets occurencies.
     *
     * @param sources the sources
     * @param words   the words
     * @param res     the res
     */
    static void getOccurencies(String[] sources, String[] words, String res) {

        int numberThreads = 4;

        try {

            List<String> dataFromUrl = Data
                    .getDataSentencesFromUrl("http://www.gutenberg.org/files/2600/2600-0.txt");

            List<String> dataWordsFromUrl = Data
                    .getDataWordsFromUrl("/home/sa/IdeaProjects/SortingAlgorithms/HW4/src/main/resources/1-1000.txt");


            List<List<String>> subSets = ListUtils.partition(dataFromUrl, numberThreads);
            Collection<Task> tasks = new ArrayList<>();

            for (List<String> list : subSets) {
                tasks.add(new Task(list, dataWordsFromUrl));
            }
            ExecutorService exec = Executors.newFixedThreadPool(numberThreads);
            List<Future<ArrayList<String>>> futures = exec.invokeAll(tasks);

            exec.shutdown();

            writeToFile(res, futures);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write to file.
     *
     * @param path    the path
     * @param futures the futures
     */
    static void writeToFile(final String path, List<Future<ArrayList<String>>> futures) {

        try {
            FileWriter writer = new FileWriter(path + "output.txt");

            writer.write("");

            for (Future<ArrayList<String>> future : futures) {

                for (String str : future.get()) {
                    writer.write(str + "\n");
                }
            }

            writer.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
