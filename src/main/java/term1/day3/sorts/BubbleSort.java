package term1.day3.sorts;

import java.util.Comparator;

public class BubbleSort<T> implements ISort<T> {
    private Comparator comparator;

    public BubbleSort(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int cmp = comparator.compare(array[i], array[i + 1]);
            if (cmp > 0) {
                T temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                if (i > 0)
                    i -= 2;
            }
        }
    }
}
