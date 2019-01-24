package algorithms;

import java.util.Random;

/**
 * The random ints array Generator.
 */
public class Generator {

    private Integer[] array;
    private int numberElements;
    /**
     * The Random.
     */
    private final Random random = new Random();

    /**
     * Instantiates a new Generator.
     *
     * @param numberElements the number elements
     */
    public Generator(int numberElements) {
        this.numberElements = numberElements;
        this.array = new Integer[numberElements];
        generateArr();
    }

    /**
     * Наполняем массив случайными числами в заданном диапазоне
     */
    private void  generateArr() {
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(numberElements * 1000);
        }
    }

    /**
     * Get array int [ ].
     *
     * @return the int [ ]
     */
    public Integer[] getArray() {
        return array;
    }
}
