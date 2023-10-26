package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable {
    private int size;
    private Node sentinel;
    private class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
        public Node(){
            item = null;
            prev = null;
            next = null;
        }
    }

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public void addFirst(T item){
        size += 1;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }
    public void addLast(T item){
        size += 1;
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node current = sentinel.next;
        for (int i = 0; i < size; i++){
            System.out.println(current.item);
            System.out.println(" ");
            current = current.next;
        }
    }
    public T removeFirst(){
        if (isEmpty()) return null;
        size -= 1;
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return first;
    }
    public T removeLast(){
        if (isEmpty()) return null;
        size -= 1;
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return last;
    }
    public T get(int index){
        if (index < 0 || index >= size) return null;
        Node current = sentinel;
        for (int i = 0; i < index + 1; i++){
            current = current.next;
        }
        return current.item;
    }
    public T getRecursive(int index){
        if (index < 0 || index >= size) return null;
        return getRecursiveHelper(index, sentinel.next);
    }
    private T getRecursiveHelper(int index, Node current){
        if (index == 0){
            return current.item;
        }
        if (current == sentinel) return null;
        return getRecursiveHelper(index - 1, current.next);
    }

    public boolean equals(Object o){
        if (!(o instanceof LinkedListDeque) || this.size() != ((LinkedListDeque<?>) o).size()) return false;
        
        return true;
    }
    public Iterator<T> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<T>{
        private int pos;
        public boolean hasNext() {
            return pos < size();
        }

        public T next() {
            T returnItem = get(pos);
            pos++;
            return returnItem;
        }

        public void remove() {

        }
    }
}