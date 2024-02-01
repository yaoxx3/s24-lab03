package edu.cmu.cs.cs214.rec02;

import java.util.Arrays;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 *
 * TODO: This implementation contains two bugs! Use your tests to determine the
 * source of the bugs and correct them!
 * Done: Specification-based Testing
 *
 * @author Alex Lockwood
 * @author Ye Lu
 */
public class ArrayIntQueue implements IntQueue {

    /**
     * An array holding this queue's data
     */
    private int[] elementData;

    /**
     * Index of the next dequeue-able value
     */
    private int head;

    /**
     * Current size of queue
     */
    private int size;

    /**
     * The initial size for new instances of ArrayQueue
     */
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructs an empty queue with an initial capacity of ten.
     */
    public ArrayIntQueue() {
        elementData = new int[INITIAL_SIZE];
        head = 0;
        size = 0;
    }

    /** {@inheritDoc} */
    public void clear() {
        Arrays.fill(elementData, 0);
        size = 0;
        head = 0;
    }

    /** {@inheritDoc} */
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer value = elementData[head];
        head = (head + 1) % elementData.length;
        size--;
        System.out.println("head for dequene: " + head);
        return value;
    }

    /** {@inheritDoc} */
    public boolean enqueue(Integer value) {
        ensureCapacity();
        System.out.println("head for enquene: " + head);
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }

    /** {@inheritDoc} */
    public boolean isEmpty() {
        return size == 0; //fix 1 bug
    }

    /** {@inheritDoc} */
    public Integer peek() {
        if (isEmpty()) {
            return null; // fix 1 bug
        }                
        return elementData[head];
    }

    /** {@inheritDoc} */
    public int size() {
        return size;
    }

    /**
     * Increases the capacity of this <tt>ArrayIntQueue</tt> instance, if
     * necessary, to ensure that it can hold at least size + 1 elements.
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = 2 * oldCapacity + 1;
            int[] newData = new int[newCapacity];
            for (int i = head; i < oldCapacity; i++) {
                newData[i - head] = elementData[i]; // newData 0 ~ (oldCapacity-head), elementData head ~ oldCapacity
            }
            for (int i = 0; i < head; i++) {
                newData[head - i] = elementData[i]; // newData head ~ 0 -> oldCapacity-head ~ oldCapacity, elementData 0 ~ head, so newData[oldCapacity - head + i] = elementData[i];
                System.out.println("head for ensureCapacity:" + head);
            }
            elementData = newData;
            head = 0;
        }
    }
}
