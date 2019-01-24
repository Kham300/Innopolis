package main.java.generics;

import java.util.*;

/**
 * The type Object box.
 */
public class ObjectBox {

    List collection;
    int id = 1;

    /**
     * Instantiates a new Object box.
     */
    public ObjectBox() {
        this.collection = new ArrayList<>();
    }

    ObjectBox(final Object[] array) {
        collection = Arrays.asList(array);
        Collections.sort(collection);
    }


    /**
     * Add a new Element to Collection
     *
     * @param o the Object
     */
    public void add(final Object o) {
        collection.add(o);
    }

    /**
     * Deletes a given Object
     * in existing Data
     *
     * @param o the Object
     */
    public void delete(final Object o) {
        collection.remove(o);
    }

    /**
     * Dump string.
     * Convert A lsit of data
     * to row separated by ;
     *
     * @return the string
     */
    public String dump() {
        StringBuilder stringBuilder = new StringBuilder();
        collection.forEach(o -> stringBuilder.append(o.toString()).append("; "));
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return this.hashCode() == objectBox.hashCode();
    }

}
