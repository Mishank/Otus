package ru.otus;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;

public class MyArrayList<E> implements List<E> { //создадим и унаследуем от интерфейса List реализуем его методы

    private static final Object[] EMPTY_LIST = {}; // создаем массив обьектов EMPTY_LIST неизменяемый
    private static final int CAPACITY_INCREMENT = 10;// создаем неизменяемую велечину

    private Object[] elements; // Массив обьектов где elements ссылка
    private int size; // Целочисленная переменная , размер

    public MyArrayList() {
        elements = EMPTY_LIST; // Если на вход MyArrayList не получает значения то создаем пустой
    }

    public MyArrayList(int capacity) {        //передаем на вход целочиленный capacity в диапозоне 32 бит
        if (capacity > 0) {                   //если больше то
            elements = new Object[capacity]; //записываем в новый массив обьектов где будет хранниться величина
        } else if (capacity == 0) { // или равно 0 значит лист пустой
            elements = EMPTY_LIST; // присвоем пустому списку
        } else { //во всех других случаях выкидываем ошибку нет такой цифры
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    public MyArrayList(Collection<? extends E> c) { // при передаче коллекции неизвестного параметра унаследуем от нашей
        elements = c.toArray(); //toArray Возвращает массив, содержащий все элементы в этой коллекции; запишу в elements
        size = elements.length; //В size записываем длину массива обьекта
    }

    private void checkIndex(int index) { // на вход метод получает индекс
        if (index < 0 || index >= size) {//если  < 0 или индекс равен или больше целочисленной переменной , размера
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");// кидаем ошибку границы индекса
        }
    }

    private void increaseCapacity(int newCapacity) { //на вход получаем новую величину
        elements = Arrays.copyOf(elements, newCapacity);//Копирует указанный массив, обрезает или заполняет нулями
    }

    @Override // переопределяем метод List
    public int size() {
        return size; //размер
    }

    @Override // переопределяем метод List
    public boolean isEmpty() {
        return size == 0; //возвратим true
    }

    @Override
    public boolean contains(Object o) { //проверим на содержание обьекта
        boolean result = false; // запишем в результат false
        int i = 0;
        while (i < size && !result) { // пока размер меньше или результат равен true
            result = (o == null ? elements[i] == null : o.equals(elements[i]));// проверяем обьект
            i++;
        }
        return result;// возвращаем результат
    }

    @Override // переопределяем метод List
    public Iterator<E> iterator() {
        return null; // возратим true если в итерации больше элементов.
    }

    @Override // переопределяем метод List
    public Object[] toArray() {
        Object[] copy = new Object[size]; // копируем наш массив обьектов

        for (int i = 0; i < copy.length; i++) { //пробегаемся в цикле
            copy[i] = elements[i];//заполняя его
        }
        return copy; // вернем значение
    }

    @SuppressWarnings("unchecked") //Набор предупреждений, которые должны быть подавлены компилятором
    @Override // переопределяем метод List
    public <T> T[] toArray(T[] a) {
        T[] copy = a.length < size ? Arrays.copyOf(a, size) : a;

        for (int i = 0; i < size; i++) {
            copy[i] = (T) elements[i];
        }

        if (a.length > size) {
            for (int i = size; i < a.length; i++) {
                copy[i] = null;
            }
        }
        return copy;
    }

    @Override // переопределяем метод List
    public boolean add(E e) {
        if (size == elements.length) {
            increaseCapacity(size + CAPACITY_INCREMENT);
        }
        elements[size++] = e;
        return true;
    }

    @Override // переопределяем метод List
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // переопределяем метод List
    public boolean containsAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        Object[] containElements = c.toArray();
        for (int i = 0; i < containElements.length; i++) {
            if (!contains(containElements[i])) {
                return false;
            }
            ;
        }
        return true;
    }

    @Override // переопределяем метод List
    public boolean addAll(Collection<? extends E> c) { // получим коллекцию ,добавим все елементы в коллекцию
        if (c.size() == 0) {// если наша коллекция "?" равна 0
            return false; // вернем значение False
        }

        Object[] addElements = c.toArray(); // в массив обьектов добавим элементы

        int capacity = elements.length; // запишем в capacity capacity длину массива обьектов где elements ссылка
        int newCapacity = size + addElements.length;// в новую величину запишем сумму размера и длины элементов

        if (capacity < newCapacity) { // если старая величина меньше
            increaseCapacity(newCapacity); // увеличим до новой
        }
        for (int i = 0; i < addElements.length; i++) {// в цикле добавим ко всем индексам размер
            elements[i + size] = addElements[i];
        }
        size += addElements.length; // в размер положим сумму
        return true;// веренем
    }

    @Override // переопределяем метод List
    public boolean addAll(int index, Collection<? extends E> c) { //Добавляем в коллекцию всех элементов по индексу
        checkIndex(index);

        if (c.size() == 0) {//если  наша "?" коллекция равна 0
            return false; // вернем
        }

        Object[] addElements = c.toArray(); // запишем в массив обьектов все элементы коллекции

        int capacity = elements.length;// запишем в capacity capacity длину массива обьектов где elements ссылка
        int newCapacity = size + addElements.length;// в новую величину запишем сумму размера и длины элементов

        if (capacity < newCapacity) {// если старая величина меньше
            increaseCapacity(newCapacity);// увеличим до новой
        }
        for (int i = size - 1; i >= index; i--) { // в цикле заменим элементы
            elements[i + addElements.length] = elements[i];
        }
        for (int i = 0; i < addElements.length; i++) {//пробежим по всем индексам и добавим элемент
            elements[i + index] = addElements[i];
        }
        size += addElements.length; // в размер положим сумму
        return true;
    }

    @Override // переопределяем метод List
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean result = false;

        Object[] removeElements = c.toArray();
        for (int i = 0; i < removeElements.length; i++) {
            while (remove(removeElements[i])) {
                result = true;
            }
        }
        return result;
    }

    @Override // переопределяем метод List
    public boolean retainAll(Collection<?> c) {
        int oldSize = size;

        if (c.size() == 0) {
            clear();
        } else {
            int i = 0;
            while (i < size) {
                if (!c.contains(elements[i])) {
                    remove(i);
                } else {
                    i++;
                }
            }
        }
        return (oldSize != size);
    }

    @Override // переопределяем метод List
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override// переопределяем метод List
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @SuppressWarnings("unchecked")
    @Override // переопределяем метод List
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    @Override// переопределяем метод List
    public void add(int index, E element) {
        checkIndex(index);
        elements = Arrays.copyOf(elements, size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override // переопределяем метод List
    public E remove(int index) {
        checkIndex(index);
        E element = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return element;
    }

    @Override // переопределяем метод List
    public int indexOf(Object o) {
        boolean result = false;
        int i = 0;
        while (i < size && !result) {
            result = (o == null ? elements[i] == null : o.equals(elements[i]));
            i++;
        }
        return (result ? i - 1 : -1);
    }

    @Override // переопределяем метод List
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override // переопределяем метод List
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override // переопределяем метод List
    public ListIterator<E> listIterator(int index) {
        return null;
    }



    @Override // переопределяем метод List
    public List<E> subList(int fromIndex, int toIndex) {
      return null;
    }

}
