package algorithms;

/**
 * The type Bubble sort.
 * Временная сложность алгоритма O(n^2)
 * Одна из самых неэфективных сортировок
 */
public class BubbleSort extends BaseAlg {

    public void doSort(Integer[] a) {
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 0; j < a.length - i - 1; j++)
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
    }


}
