/*use circular array to do! no linear array
* useful circular array link: https://blog.csdn.net/yyoc97/article/details/88759562*/
public class ArrayDeque<T> implements Deque<T> {
    private T[] item;
    private int start = 0;
    private int end = 0;
    private int sizeQueue = 0;
    private int sizeArray = 8; // initial size of array
    private final double expandRatio = 1.5;
    private final double usageRatio = 0.25;

//    for testing purpose
//    public static void main(String[] args) {
//        ArrayDeque array = new ArrayDeque();
//        array.addFirst(0);
//        array.get(0); //      ==> 0
//        array.removeLast(); //      ==> 0
//        array.addLast(3);
//        array.addLast(4);
//        array.addFirst(5);
//        array.removeFirst(); //     ==> 5
//        array.removeFirst(); //     ==> 4
//    }

    /*check if the array is separated into two segments or one continuous segment*/
    private boolean endInFrontOfStart() {
        return end <= start;
    }

    /*increase the size of embedded array*/
    private void resize() {
        int newLength = (int) (sizeArray * expandRatio);
        T[] newArray = (T[]) new Object[newLength];
        if (endInFrontOfStart()) { // end is in front of start, two segments
            System.arraycopy(item, 0, newArray, 0, end); //copy mid - end portion
            System.arraycopy(item, start,
                    newArray, newLength - (sizeArray - start),
                    sizeArray - start); // copy start - mid portion
            start = newLength - (sizeArray - start);
            end = end;
        } else { // start is in front of end, one segment
            System.arraycopy(item, start, newArray, 0, sizeQueue);
            start = 0;
            end = start + sizeQueue;
        }
        item = newArray;
        sizeArray = newLength;
    }

    /*resize T[] item down when too many items are removed.*/
    private void resizeDown() {
        int newLength = (int) (sizeArray * usageRatio * 2);
        T[] newArray = (T[]) new Object[newLength];

        if (endInFrontOfStart()) { // end is in front of start, two segments
            System.arraycopy(item, 0, newArray, 0, end); //copy mid - end portion
            System.arraycopy(item, start, newArray, newLength - (sizeArray - start),
                    sizeArray - start); // copy start - mid portion
            start = newLength - (sizeArray - start);
        } else {
            System.arraycopy(item, start, newArray, 0, sizeQueue);
            start = 0;
            end = start + sizeQueue;
        }
        item = newArray;
        sizeArray = newLength;
    }

    /*Adds an item of type T to the front of the deque.*/
    @Override
    public void addFirst(T x) {
        if (sizeQueue >= sizeArray) {
            resize();
        }

        if (start - 1 < 0) { // go to the back of array
            start = sizeArray - 1;
        } else {             // simply add in front
            start = start - 1;
        }
        item[start] = x;
        sizeQueue++;
    }

    /*Adds an item of type T to the back of the deque.*/
    @Override
    public void addLast(T x) {
        if (sizeQueue >= sizeArray) {
            resize();
        }
        item[end] = x;
        if (end + 1 == sizeArray) { // go to start of the array
            end = 0;
        } else {                    // simply add in the back
            end++;
        }
        sizeQueue++;
    }

    /*Returns true if deque is empty, false otherwise.*/
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /*Returns the number of items in the deque.*/
    @Override
    public int size() {
        return sizeQueue;
    }

    /*Removes and returns the item at the front of the deque.
    If no such item exists, returns null.*/
    @Override
    public T removeFirst() {
        if (sizeQueue <= 0) {
            return null;
        } else if (sizeQueue < sizeArray * usageRatio) {
            resizeDown();
        }

        T front = item[start];
        item[start] = null; //reset the unused element to be null,
        // so that no pointer is pointing to that memory, garbage collector can free it
        if (start == sizeArray - 1) {
            start = 0;
        } else {
            start++;
        }
        sizeQueue--;
        return front;
    }

    /*Removes and returns the item at the back of the deque.
    If no such item exists, returns null.*/
    @Override
    public T removeLast() {
        if (sizeQueue <= 0) {
            return null;
        } else if (sizeQueue < sizeArray * usageRatio) {
            resizeDown();
        }

        if (end == 0) {
            end = sizeArray - 1;
        } else {
            end--;
        }
        T back = item[end];
        item[end] = null; //reset the unused element to be null,
        // so that no pointer is pointing to that memory, garbage collector can free it
        sizeQueue--;
        return back;
    }

    /*Gets the item at the given index,
    where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!*/
    @Override
    public T get(int index) {
        if (sizeQueue <= 0 || index < 0 || index >= sizeArray) {
            return null;
        }

        if (endInFrontOfStart()) {
            if (start + index >= sizeArray) {
                return item[start + index - sizeArray];
            } else {
                return item[start + index];
            }
        } else {
            return item[start + index];
        }

    }

    /*constructor*/
    public ArrayDeque() {

        item = (T[]) new Object[sizeArray];
    }

    /*Prints the items in the deque from first to last, separated by a space.*/
    @Override
    public void printDeque() {
        if (endInFrontOfStart()) {
            for (int i = start; i < sizeArray; i++) {
                //# of separator is one less than # of items
                String separator = (i - start < sizeQueue) ? " " : "";
                // the last item has no separator following it
                System.out.print(item[i] + separator);
            }

            for (int i = 0; i < end; i++) {
                //# of separator is one less than # of items
                String separator = i < (end - 1) ? " " : "";
                // the last item has no separator following it
                System.out.print(item[i] + separator);
            }
        } else {
            for (int i = 0; i < (end - start); i++) {
                //# of separator is one less than # of items
                String separator = i < (end - start - 1) ? " " : "";
                // the last item has no separator following it
                System.out.print(item[i] + separator);
            }
        }

        System.out.println();
    }
}
