package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Console parser.
 */
public class ConsoleParser {
    private static List<Person> results = new ArrayList<>();

    /**
     * Deserialize.
     */
    public static void deserialize() {
        ArrayList<Person> newPeople = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/home/sa/IdeaProjects/SortingAlgorithms/HW3.2/src/main/resources/person.ser"))) {
            newPeople=(ArrayList<Person>)ois.readObject();
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        if (!newPeople.isEmpty()) {
            for (Person p : newPeople)
                System.out.printf("Имя: %s \t Возраст: %d \n", p.getName(), p.getAge());
        } else {
            System.out.println("Ошибка нет данных");
        }
    }

    /**
     * Serialize.
     */
    public static void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home/sa/IdeaProjects/SortingAlgorithms/HW3.2/src/main/resources/person.ser"))) {

            oos.writeObject(results);

            oos.flush();

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        if (!results.isEmpty()) {
            System.out.println("Данные успешно сериализованы");
        }
    }

    /**
     * Parse.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void parse() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter path to string: ");
        String path = scanner.next();

        Scanner reader = new Scanner(new File(path));
        reader.useDelimiter(" \n");

        while (reader.hasNext()) {
            results.add(new Person(reader.nextLine()));
        }

        for (Person p : results)
            System.out.printf("Имя: %s \t Возраст: %d \n", p.getName(), p.getAge());
    }

}

