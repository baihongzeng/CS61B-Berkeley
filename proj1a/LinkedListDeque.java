public class LinkedListDeque<T> {
    /*nested class of elements in linked list deque*/
    private class Node { //should be private
        private T item;
        private Node prev;
        private Node next;
        // constructor, modifier the same as the class scope, i.e. private for private class
        Node(T x) {
            item = x;
            prev = null;
            next = null;

        }
    }

    // instance variables
    private Node head; // sentinel node
    private int size;

    /*Adds an item of type T to the front of the deque.
     * time complexity: O(1)*/
    public void addFirst(T item) {
        Node current = new Node(item);
        current.next = head.next;
        head.next.prev = current;
        current.prev = head;
        head.next = current;

        size += 1;
    }
    /* Adds an item of type T to the back of the deque.
     * time complexity: O(1)*/
    public void addLast(T item) {
        Node current = new Node(item);
        current.prev = head.prev;
        head.prev.next = current;
        current.next = head;
        head.prev = current;

        size += 1;
    }

    /*  *//*Same as get, but uses recursion.*//*
  public T getRecursive(int index) {

  }*/

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /*Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    /*Removes and returns the item at the front of the deque.
    If no such item exists, returns null.*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = head.next;
        head.next = head.next.next; //update head.next
        head.next.prev = head;

        size -= 1;
        return first.item;
    }

    /*Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = head.prev;
        head.prev = head.prev.prev; //update head.prev
        head.prev.next = head;

        size -= 1;
        return last.item;
    }

    /*Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index) {
        if (size == 0 || index >= size || index < 0) { //corner case
            return null;
        }
        int count = 0;
        Node current = head;
        while (count < index) {
            current = current.next;
            count += 1;
        }
        return current.next.item;
    }
    /*Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        if (size == 0 || index >= size || index < 0) { //corner case
            return null;
        }
        Node current = head;
        return getRecursiveHelper(index, current);
    }

    private T getRecursiveHelper(int index, Node current) {
        if (index == 0) {
            return current.next.item;
        }
        current = current.next;
        index--;
        return getRecursiveHelper(index, current);
    }

    /*Creates an empty linked list deque.*/
    //用链表来做double ended queue, 那就是要双向链表，
    public LinkedListDeque() {
        head = new Node(null);
        head.prev = head; // circular linked list
        head.next = head;
        size = 0;
    }

    /*Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque() {
        Node current = head;
        int count = 0;
        while (count < size) {
            //# of separator is one less than # of items
            String separator = count < (size - 1) ? " " : "";
            // the last item has no separator following it
            System.out.print(current.next.item + separator);
            current = current.next;
            count++;
        }
        System.out.println();
    }
}
