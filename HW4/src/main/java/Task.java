package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * The type Task.
 */
public class Task implements Callable<ArrayList<String>> {

    private List<String> dataSentences;
    private List<String> dataWords;

    /**
     * Instantiates a new Task.
     *
     * @param data      the data
     * @param dataWords the data words
     */
    public Task(List<String> data, List<String> dataWords) {
        this.dataSentences = data;
        this.dataWords = dataWords;
    }

    @Override
    public ArrayList<String> call() {
        List<String> result = new ArrayList<>();
        for (String dataSentence : dataSentences) {
            for (String dataWord : dataWords) {
                if (dataSentence.contains(dataWord)) {
                    result.add(dataSentence);
                }
            }
        }
        return (ArrayList<String>) result;
    }
}
