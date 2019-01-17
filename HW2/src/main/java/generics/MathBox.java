package main.java.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Math box.
 *
 * @param <T> the type parameter
 */
public final class MathBox<T extends Number> extends ObjectBox {
    private T[] array;

    /**
     * Instantiates a new Math box.
     *
     * @param array the array
     */
    public MathBox(final T[] array) {
        super(array);
        this.array = array;
        try {
            checkForDublicates(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkForDublicates(final T[] array) throws Exception {
        List<T> set = new ArrayList<>();
        for (T i : array) {
            if (set.contains(i)) throw new Exception("В массиве не должно быть дубликатов");
            set.add(i);
        }
    }

    /**
     *
     * @return
     */
    public int summator() {
        int summ = 0;
        for (T i : array) {
            summ += (Integer) i;//большое допущение
        }
        return summ;
    }

    public List<Integer> splitter(final int delimeter) {
        List<Integer> list = new ArrayList<>();
        for (T i : array) {
            list.add((Integer) i / delimeter);// AutoBoxing будет лишь для типов,
            // которые имеют примитивы, а класс Number не имеет поэтому математические операции не возможны
            // без явного приведения
        }
        return list;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * @param element
     */
    public void deleteValue(final Integer element) {
        super.collection.remove(element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return (Arrays.equals(array, mathBox.array));

    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 17 * result + (array != null ? Arrays.deepHashCode(array) : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.dump();
    }


}
