package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable{
    private T[] items;
    private int front;
    private int rear;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        front = rear = 0;
    }
    private int getPosition(int index){
        return (index + items.length) % items.length;
    }
    private void resize(int capacity){
        if (size() == items.length){
            T[] a = (T[]) new Object[capacity];
            for (int i = 0; i < size(); i++){
                a[i] = items[getPosition(front + i)];
            }
            front = 0;
            rear = size();
            items = a;
        }
    }
    public void addFirst(T item){
        resize(items.length * 2);
        front = getPosition(front - 1);
        items[front] = item;
    }
    public void addLast(T item){
        resize(items.length * 2);
        items[rear] = item;
        rear = getPosition(rear + 1);
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public int size(){
        return (rear - front + items.length) % items.length;
    }
    public void printDeque(){
        int first = front;
        for (int i = 0; i < size(); i++){
            System.out.println(items[first]);
            System.out.println(" ");
            first = getPosition(first + 1);
        }
    }
    public T removeFirst(){
        if (isEmpty()) return null;
        T deletedItem = items[front];
        items[front] = null;
        front = getPosition(front + 1);
        return deletedItem;
    }
    public T removeLast(){
        if (isEmpty()) return null;
        rear = getPosition(rear - 1);
        T deletedItem = items[rear];
        items[rear] = null;
        return deletedItem;
    }
    public T get(int index){
        if (index < 0 || index >= size()) return null;
        int position = getPosition(front + index);
        return items[position];
    }
    public Iterator<T> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<T> {
        private int pos;
        public DequeIterator(){
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size();
        }

        public T next() {
            int current = getPosition(front + pos);
            T returnItem = items[current];
            pos++;
            return returnItem;
        }

        public void remove() {

        }
    }
}