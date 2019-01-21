package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The type Data.
 */
public class Data {

    /**
     * Gets data from url.
     *
     * @param path the path
     * @return the data from url
     * @throws IOException the io exception
     */
    public static List<String> getDataSentencesFromUrl(final String path) throws IOException {
        List<String> sentences = new ArrayList<>();
        BreakIterator bi = BreakIterator.getSentenceInstance(Locale.US);

        URL url = new URL(path);

        Scanner scanner = new Scanner(url.openStream());

        scanner.useDelimiter("(?m:^$)");

        while (scanner.hasNext()) {
            String line = scanner.next();

            bi.setText(line);

            int lastIndex = bi.first();

            while (lastIndex != BreakIterator.DONE) {
                int firstIndex = lastIndex;
                lastIndex = bi.next();

                if (lastIndex != BreakIterator.DONE) {
                    String trim = line.substring(firstIndex, lastIndex).replace("\n", " ").replace("\r", " ").trim();
                    if (!trim.isEmpty()) {
                        sentences.add(trim);
                    }
                }
            }
        }
        scanner.close();
        return sentences;
    }

    /**
     * Get data words from url list.
     *
     * @param fileName the file name
     * @return the list
     */
    public static List<String> getDataWordsFromUrl(final String fileName){
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());


            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
