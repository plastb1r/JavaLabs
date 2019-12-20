package day3.repository;

import day3.DI.Inject;
import day3.sorts.BubbleSort;
import day3.sorts.ISort;
import ru.vsu.lab.repository.IRepository;

import java.util.*;
import java.util.function.Predicate;

public class MyRepository<T> implements IRepository {

    private T[] array;
    private T[] tempArray;

    private int count;

    @Inject
    ISort sorter;

    public MyRepository() {
        array = (T[]) new Object[16];
        count = 0;
    }

    public MyRepository(ISort sorter) {
        array = (T[]) new Object[16];
        count = 0;
        this.sorter = sorter;
    }

    public T[] getArray() {
        return array;
    }

    public ISort getSorter() {
        return sorter;
    }

    public void setSorter(ISort sorter) {
        this.sorter = sorter;
    }

    @Override
    public void add(Object value) {
        tempArray = (T[]) new Object[count + 1];
        for (int i = 0; i < count; i++) {
            tempArray[i] = array[i];
        }
        tempArray[count++] = (T) value;
        array = tempArray;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= count) {
            return Optional.empty();
        }
        return array[index];
    }

    @Override
    public Object delete(int index) {
        Object obj;

        tempArray = (T[]) new Object[--count];
        for (int i = 0; i < index; i++) {
            tempArray[i] = array[i];
        }
        obj = array[index];
        for (int i = index; i < count; i++) {
            tempArray[i] = array[i + 1];
        }
        array = tempArray;

        return obj;
    }

    @Override
    public Object set(int index, Object value) {
        if (index < 0 || index >= count) {
            return Optional.empty();
        }
        array[index] = (T) value;

        return array[index];
    }

    @Override
    public void add(int index, Object value) {
        if (index < 0 || index > count) {
            return;
        }
        tempArray = (T[]) new Object[count + 1];
        for (int i = 0; i < index; i++) {
            tempArray[i] = array[i];
        }
        tempArray[index] = (T) value;
        for (int i = index + 1; i < count + 1; i++) {
            tempArray[i] = array[i - 1];
        }
        array = tempArray;
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public List toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(array[i]);
        }
        return list;
    }

    @Override
    public void sortBy(Comparator comparator) {
        if (comparator == null) {
            return;
        }
        if (sorter == null) {
            new BubbleSort<T>(comparator).sort(array);
            return;
        }
        sorter.sort(array);
    }

    private int compare(Object k1, Object k2, Comparator comparator) {
        return comparator == null ? ((Comparable) k1).compareTo(k2) : comparator.compare(k1, k2);
    }

    @Override
    public IRepository searchBy(Predicate predicate) {
        IRepository repository = new MyRepository();
        for (int i = 0; i < count; i++) {
            if (predicate.test(array[i])) {
                repository.add(array[i]);
            }
        }
        return repository;
    }

    @Override
    public String toString() {
        return "MyRepository{" +
                "array=" + Arrays.toString(array) +
                ", tempArray=" + Arrays.toString(tempArray) +
                ", count=" + count +
                ", sorter=" + sorter +
                '}';
    }
}
