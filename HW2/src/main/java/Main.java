package main.java;

import main.java.generics.MathBox;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Integer[] integers = genetrateArr();
        Integer[] integers2 = genetrateArr();

        MathBox objectBox = new MathBox<>(integers);
        MathBox objectBox2 = new MathBox<>(integers);

        System.out.println(objectBox.summator());

        System.out.println(objectBox.dump());
        System.out.println(objectBox2.dump());

        System.out.println(objectBox.equals(objectBox2));
        System.out.println(objectBox.hashCode());
        System.out.println(objectBox2.hashCode());
    }


    private static Integer[] genetrateArr() {
        Random random = new Random();
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10000);
        }
        return a;
    }
}
