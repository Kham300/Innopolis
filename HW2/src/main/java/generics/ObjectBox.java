package main.java.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The type Object box.
 */
public class ObjectBox {

     List collection;
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
    public void add(final Object o){
        collection.add(o);
    }

    /**
     * Deletes a given Object
     * in existing Data
     *
     * @param o the Object
     */
    public void delete(final Object o){
        collection.remove(o);
    }

    /**
     * Dump string.
     * Convert A lsit of data
     * to row separated by ;
     *
     * @return the string
     */
    public String dump(){
        StringBuilder stringBuilder = new StringBuilder();
        collection.forEach(o -> stringBuilder.append(o.toString()).append("; "));
        return stringBuilder.toString();
    }
}
