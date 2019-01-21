package main.java.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Generator.
 */
public class Generator {
    private final Random random = new Random();


    private String[] generatedWords;

    private char[] endText = { '.', '!', '?'};

    private final String PATH_WORDS = "/home/sa/IdeaProjects/SortingAlgorithms/HW3/src/main/resources/1-1000.txt";

    /**
     * Instantiates a new Generator.
     */
    public Generator() {
        generatedWords = createWordsArray();
    }


    /**
     * Create words array string [ ].
     *
     * @return the string [ ]
     */
    private String[] createWordsArray() {
        //определяем пустой массив изначально
        String[] text = new String[0];
        try {
            String str;
            BufferedReader br = new BufferedReader(new FileReader(PATH_WORDS));
            while ((str = br.readLine()) != null) {
                //получаем новые слова
                String[] newWords = str.split(" ");
                //создаем расширенный массив
                String[] result = new String[text.length + newWords.length];
                //копируем элементы в массив
                System.arraycopy(text, 0, result, 0, text.length);
                System.arraycopy(newWords, 0, result, text.length, newWords.length);
                //присваиваем результирующий массив текущему
                text = result;
            }
            br.close();
        } catch (IOException exc) {
            System.out.println("IO error!" + exc);
        }
        return text;
    }

    /**
     * Create sentence string.
     *
     * @return the string
     */
    private String createSentence(String[] words, int pobability) {
        List<String> wordsList = new ArrayList<>();
        if (words == null) {
            words = generatedWords;
        }
        StringBuilder buffer = new StringBuilder();
        int length = random.nextInt(15) + 1;
        int numberCommas = length / 5;

        for (int i = 0; i < length; i++) {

            if (getProbability(pobability)) {
                wordsList.add(words[random.nextInt(1000)]);
            }

        }

        for (int j = 0; j < numberCommas; j++) {
            int index;
            int max = wordsList.size() - 1;
            int min = 1;
            index = random.nextInt((max - min) + 1) + min;
            wordsList.add(index, ",");

        }

        for (String string : wordsList) {
            if (string.equals(",")) {
                buffer.setLength(buffer.length() - 1);
            }
            buffer.append(string).append(" ");
        }

        buffer.setLength(buffer.length() - 1);
        buffer.append(endText[random.nextInt(endText.length)]);
        String generatedString = buffer.toString();
        return Character.toUpperCase(generatedString.charAt(0)) + generatedString.substring(1);
    }

    private String createText(final String[] words, final int probability) {
        StringBuilder builder = new StringBuilder();
        int txtLength = random.nextInt(20) + 1;
        for (int i = 0; i < txtLength; i++) {
            builder.append(createSentence(words, probability)).append("\n");
        }
        return builder.toString();
    }

    private boolean getProbability(final int n) {
        return random.nextInt(100) * (1 / n) > 1;
    }

    /**
     * Gets files.
     *
     * @param path        the path
     * @param n           the n
     * @param size        the size
     * @param words       the words
     * @param probability the probability
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) {
        for (int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder();
            try (FileOutputStream fos = new FileOutputStream(path + "notes" + i + ".txt")) {
                for (int j = 0; j < size; j++) {
                    builder.append(createText(words, probability)).append("\n");
                }
                // перевод строки в байты
                byte[] buffer = builder.toString().getBytes();
                fos.write(buffer, 0, buffer.length);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

