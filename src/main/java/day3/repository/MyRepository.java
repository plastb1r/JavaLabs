package day3.repository;

import ru.vsu.lab.repository.IRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MyRepository<T> implements IRepository {
    private T[] array;
    private T[] tempArray;

    private int count;

    public MyRepository() {
        array = (T[]) new Object[16];
        count = 0;
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
        if (count == 0 || count == 1)
            return;
        for (int i = 0; i < count - 1; i++) {
            int cmp = compare(array[i], array[i + 1], comparator);
            if (cmp > 0) {
                T temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                if (i > 0)
                    i -= 2;
            }
        }
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
}
