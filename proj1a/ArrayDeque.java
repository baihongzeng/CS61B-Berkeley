public class ArrayDeque<T> {
    private T[] item;
    private int start = 0;
    private int end = 0;
    private int sizeQueue;
    private int sizeArray = 8;
    private final double expandRatio = 1.5;
    private final double usageRatio = 0.25;

    public static void main(String[] args) {
        ArrayDeque array = new ArrayDeque();
        array.resize();
    }
    private void resize() {
        T[] destArray = (T[]) new Object[(int) (sizeArray * expandRatio)];
        System.arraycopy(item, 0, destArray, 0, sizeArray);
        item = destArray;
        sizeArray = (int) (sizeArray * expandRatio);
    }

    /*Adds an item of type T to the front of the deque.*/
    public void addFirst(T x) {
        if (size() >= sizeArray) {
            resize();
        }

        if (start == 0) {
            T temp = x;
            for (int i = start; i <= end; i++) {
                T temp2 = item[i];
                item[i] = temp;
                temp = temp2;
            }
            end++;
        } else {
            item[start - 1] = x;
            start--;
        }
    }

    /*Adds an item of type T to the back of the deque.*/
    public void addLast(T x) {
        if (size() >= sizeArray) {
            resize();
        }
        item[end] = x;
        end++;
    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size() == 0;
    }

    /*Returns the number of items in the deque.*/
    public int size() {
        return (end - start + 1);
    }

    /*resize T[] item down when too many items are removed.*/
    public void resizeDown() {
        T[] destArray = (T[]) new Object[(int) (sizeArray * usageRatio)];
        int larger = (end > (int)usageRatio * 2 * sizeArray) ?
                end : ((int)usageRatio * 2 * sizeArray);
        System.arraycopy(item, start, destArray, 0, larger - start + 1);
        item = destArray;
        sizeArray = larger - start;
    }
    /*Removes and returns the item at the front of the deque.
    If no such item exists, returns null.*/
    public T removeFirst() {
        if (end - start <= 0) {
            return null;
        } else if (end - start < sizeArray * usageRatio) {
            resizeDown();
        }

        T front = item[start];
        item[start] = null; //reset the unused element to be null,
        // so that no pointer is pointing to that memory, garbage collector can free it
        start++;
        return front;
    }

    /*Removes and returns the item at the back of the deque.
    If no such item exists, returns null.*/
    public T removeLast() {
        if (end - start <= 0) {
            return null;
        } else if (end - start < sizeArray * usageRatio) {
            resizeDown();
        }
        T back = item[end - 1];
        item[end - 1] = null; //reset the unused element to be null,
        // so that no pointer is pointing to that memory, garbage collector can free it
        end--;
        return back;
    }

    /*Gets the item at the given index,
    where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index) {
        if (end - start <= 0 || index < 0 || index >= sizeArray) {
            return null;
        }
        return item[start + index];
    }

    public ArrayDeque() {
        item = (T[]) new Object[sizeArray];
        sizeQueue = end - start;
    }

    /*Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque() {
        for (int i = 0; i < (end - start); i++) {
            //# of separator is one less than # of items
            String separator = i < (end - start - 1) ? " " : "";
            // the last item has no separator following it
            System.out.print(item[i] + separator);
        }
        System.out.println();
    }
}
