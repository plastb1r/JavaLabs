package day1;

import java.util.Comparator;
import java.util.TreeSet;

public class MyList<T> {
    /**
     * Массивы, необходимые для хранения элементов,
     * и реализации функционала класса
     */
    private T[] array;
    private T[] tempArray;

    private int count;

    Comparator<Object> comparator;

    public MyList() {
        array = (T[]) new Object[16];
        count = 0;
    }

    public MyList(Comparator comparator) {
        array = (T[]) new Object[16];
        count = 0;
        this.comparator = comparator;
    }

    /**
     * Добавление нового элемента в список.
     *
     * @param value значение элемента
     */
    public void add(T value) {
        tempArray = (T[]) new Object[count + 1];
        for (int i = 0; i < count; i++) {
            tempArray[i] = array[i];
        }
        tempArray[count++] = value;
        array = tempArray;
    }

    /**
     * Удаление всех элементов из списка.
     */
    public void clear() {
        array = (T[]) new Object[0];
        count = 0;
    }

    /**
     * Вставка нового элемента
     * по определенному индексу.
     * со сдвигом последующих элементов
     *
     * @param index место вставки
     * @param value значение элемента
     */
    public void insert(int index, T value) {
        if (index < 0 || index > count) {
            return;
        }
        tempArray = (T[]) new Object[count + 1];
        for (int i = 0; i < index; i++) {
            tempArray[i] = array[i];
        }
        tempArray[index] = value;
        for (int i = index + 1; i < count + 1; i++) {
            tempArray[i] = array[i - 1];
        }
        array = tempArray;
        count++;
    }

    /**
     * Удаление первого встречного элемента
     * из списка по значению.
     *
     * @param value значение удаляемого элемента
     */
    public void remove(T value) {
        int k = 0;
        while (!array[k].equals(value) && k < count) {
            k++;
        }
        removeAt(k);
    }

    /**
     * Удаление элемента по индексу.
     *
     * @param index индекс элемента
     */
    public void removeAt(int index) {
        tempArray = (T[]) new Object[--count];
        for (int i = 0; i < index; i++) {
            tempArray[i] = array[i];
        }
        for (int i = index; i < count; i++) {
            tempArray[i] = array[i + 1];
        }
        array = tempArray;
    }

    /**
     * возвращает количество элементов в списке.
     *
     * @return количество элементов
     */
    public int getCount() {
        return count;
    }

    public T get(int index) {
        if (index < 0 || index >= count) {
            return null;    //Optional.empty
        }
        return array[index];
    }
    public void sort() {
        if (count == 0 || count == 1)
            return;
        for (int i = 0; i < count-1;i++) {
            int cmp = compare(array[i],array[i+1]);
            if(cmp>0) {
               T temp =  array[i];
               array[i] = array[i+1];
               array[i+1] = temp;
                if(i>0)
                    i-=2;
            }
        }
    }

    private int compare(Object k1, Object k2) {
        return this.comparator == null ? ((Comparable) k1).compareTo(k2) : this.comparator.compare(k1, k2);
    }

    public int search(T obj) {
        for (int i = 0; i < count; i++) {
            if(array[i].hashCode() == obj.hashCode()) {
                return i;
            }
        }
        return -1;
    }
}
