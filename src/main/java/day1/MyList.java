package day1;

import java.lang.reflect.Array;

public class MyList<T> {

    private T[] array;
    private T[] tempArray;

    private int count;

    public MyList() {
        array = (T[]) new Object[16];
        count = 0;
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
     * Вставка нового элемента по определенному индексу.
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
     * Удаление первого встречного элемента из списка по значению.
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
     * @return
     */
    public int getCount() {
        return count;
    }

    public T get(int index) {
        if (index < 0 || index >= count) {
            return null;
        }
        return array[index];
    }
}
