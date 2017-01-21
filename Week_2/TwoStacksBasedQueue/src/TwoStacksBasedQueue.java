import edu.princeton.cs.algs4.Stack;

/**
 * Problem statement: Create a queue, implemented using two stacks. All queue operations
 * should take amortized constant number of the stack's operations.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 20.01.17.
 */

public class TwoStacksBasedQueue<T> {

    private Stack<T> inbox = null;
    private Stack<T> outbox = null;

    /**
     * Creates an empty queue.
     */
    private TwoStacksBasedQueue() {
        inbox = new Stack<>();
        outbox = new Stack<>();
    }

    /**
     * Adds new element to the queue
     *
     * @param item an element to be added to the queue.
     */
    private void enqueue(T item) {
        inbox.push(item);
    }

    /**
     * Removes element from the queue.
     *
     * @return the item being removed from the queue.
     */
    private T deque() {
        if (outbox.isEmpty()) {
            moveItemsFromInboxToOutbox();
        }

        return outbox.pop();
    }

    /**
     * Moves all elements from the inbox stack to the outbox stack.
     * By that elements in the outbox stack will be stored in reversed order.
     */
    private void moveItemsFromInboxToOutbox() {

        if (!outbox.isEmpty()) {
            return;
        }

        while (!inbox.isEmpty()) {
            T item = inbox.pop();
            outbox.push(item);
        }
    }

    public static void main(String[] args) {
        TwoStacksBasedQueue<String> myQueue = new TwoStacksBasedQueue<>();

        myQueue.enqueue("one");
        myQueue.enqueue("two");
        myQueue.enqueue("three");

        System.out.println(myQueue.deque());

        myQueue.enqueue("four");

        System.out.println(myQueue.deque());
        System.out.println(myQueue.deque());

        myQueue.enqueue("five");

        System.out.println(myQueue.deque());
        System.out.println(myQueue.deque());
    }
}
