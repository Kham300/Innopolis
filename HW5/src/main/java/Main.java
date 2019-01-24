package main.java;

public class Main {

    public static void main(String[] args) {

        Person person1 = new Person(1, "Mike");

        try {

            ClassInfo info = new ClassInfo(person1);

            System.out.println("Serializing an Object to File:\n");
            info.serialize(person1);

            System.out.println("====================================\n");
            System.out.println("Deserializing an Object from File:\n");
            info.deserialize("/home/sa/IdeaProjects/SortingAlgorithms/HW5/src/main/resources/notes.txt");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
