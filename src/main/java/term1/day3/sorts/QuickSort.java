package term1.day3.sorts;

import term1.day1.PersonAgeComparator;

import java.util.Comparator;

public class QuickSort<T> implements ISort<T> {

    private Comparator comparator;
    private int begin;
    private int end;

    public QuickSort(Comparator comparator) {
        this.comparator = comparator;
    }

    public QuickSort() {
        this.comparator = new PersonAgeComparator();
    }

    @Override
    public void sort(T[] array) {
        int begin = 0;
        int end = array.length;

        recursiveSort(array, begin, end);
    }

    private void recursiveSort(T[] array, int begin, int end) {
        if (end <= begin)
            return;

        int pivot = end - 1;
        int counter = begin;

        for (int i = begin; i < end; i++) {
            if (comparator.compare(array[i], array[pivot]) > 0) {
                T temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        T temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;

        recursiveSort(array, begin, counter - 1);
        recursiveSort(array, counter + 1, end);
    }
}
