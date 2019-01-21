package main.java;

import java.io.Serializable;

/**
 * The type Person.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 20190120;

    private String name;
    private int age;

    /**
     * Instantiates a new Person.
     *
     * @param name the name
     * @param age  the age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Instantiates a new Person.
     *
     * @param line the line
     */
    public Person(String line) {

        String[] split = line.split(" ");
        String name = split[0].substring(split[0].lastIndexOf("=") + 1);
        String age = split[1].substring(split[1].lastIndexOf("=") + 1);
        this.name = name;
        this.age = Integer.parseInt(age);
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }
}
