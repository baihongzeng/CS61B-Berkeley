public class LinkedListDeque<T> {
  /*nested class of elements in linked list deque*/
  private class Node { //should be private
    public T item;
    public Node Prev;
    public Node Next;
    // constructor
    public Node (T x) {
      item = x;
      Prev = null;
      Next = null;

    }
  }

  // instance variables
  private Node element;
  public Node head; // sentinel node
  private int size;

  /*Adds an item of type T to the front of the deque.
   * time complexity: O(1)*/
  public void addFirst(T item) {
    Node current = new Node(item);
    current.Next = head.Next;
    head.Next.Prev = current;
    current.Prev = head;
    head.Next = current;

    size += 1;
  }
  /* Adds an item of type T to the back of the deque.
   * time complexity: O(1)*/
  public void addLast(T item) {
    Node current = new Node(item);
    current.Prev = head.Prev;
    head.Prev.Next = current;
    current.Next = head;
    head.Prev = current;

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
  public int size () {
    return size;
  }

  /*Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
  public T removeFirst() {
    if (size == 0) {
      return null;
    }
    Node first = head.Next;
    head.Next = head.Next.Next;//update head.Next
    head.Next.Prev = head;

    size -= 1;
    return first.item;
  }

  /*Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
  public T removeLast () {
    if (size == 0) {
      return null;
    }
    Node last = head.Prev;
    head.Prev = head.Prev.Prev;//update head.Prev
    head.Prev.Next = head;

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
      current = current.Next;
      count += 1;
    }
    return current.Next.item;
  }
  /*Same as get, but uses recursion.*/
  public T getRecursive(int index) {
    if (size == 0 || index >= size || index < 0) { //corner case
      return null;
    }
    Node current = head;
    return getRecursiveHelper(index, current);
  }

  private T getRecursiveHelper (int index, Node current) {
    if (index == 0) {
      return current.Next.item;
    }
    current = current.Next;
    index--;
    return getRecursiveHelper(index, current);
  }

  /*Creates an empty linked list deque.*/
  //用链表来做double ended queue, 那就是要双向链表，
  public LinkedListDeque() {
    head = new Node(null);
    head.Prev = head;// circular linked list
    head.Next = head;
    size = 0;
  }

  /*Prints the items in the deque from first to last, separated by a space.*/
  public void printDeque () {
    Node current = head;
    int count = 0;
    while (count < size) {
      String separator = count < (size - 1) ? " " : ""; //# of separator is one less than # of items
      System.out.print(current.Next.item + separator);// the last item has no separator following it
      current = current.Next;
      count++;
    }
    System.out.println();
  }
}