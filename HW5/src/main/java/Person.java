package main.java;

/**
 * The type Person.
 */
public class Person {
    /**
     * The Age.
     */
    public int age;
    /**
     * The Name.
     */
    public String name;


    /**
     * Instantiates a new Person.
     */
    public Person() {

    }

    /**
     * Instantiates a new Person.
     *
     * @param age  the age
     * @param name the name
     */
    public Person(int age, String name) {
        this.age = age;
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

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + "\n\t"+
                "age: " + getAge() + "\n\t" +
                "name: " + getName();
    }
}
