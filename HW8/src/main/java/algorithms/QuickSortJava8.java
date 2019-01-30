package main.java.algorithms;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSortJava8 {

    public static List<Integer> quickSort(List<Integer> xs) {

        if (xs.size() <= 1) {
            return xs;
        }

        int x = xs.get(0); //choosing the first element as the pivot

        //smallerSorted = quicksort [a | a <- xs, a <= x]
        List<Integer> smallerSorted = quickSort(
                xs.stream()
                        .skip(1) //removing pivot
                        .filter(i -> i <= x) //filter the elements <= x
                        .collect(Collectors.toList())); //convert the stream back to List

        //biggerSorted = quicksort [a | a <- xs, a > x]
        List<Integer> biggerSorted = quickSort(
                xs.stream()
                        .skip(1) //removing pivot
                        .filter(i -> i > x) //filter the elements > x
                        .collect(Collectors.toList())); //convert the stream back to List

        //smallerSorted ++ [x] ++ biggerSorted
        return Stream.concat(Stream.concat(smallerSorted.stream(), Stream.of(x)), biggerSorted.stream())
                .collect(Collectors.toList());
    }

}
