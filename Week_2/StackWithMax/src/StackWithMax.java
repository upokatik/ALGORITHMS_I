import edu.princeton.cs.algs4.Stack;

/**
 * Problem statement: Stack with max. Create a data structure that efficiently supports the stack operations
 * (push and pop) and also a return-the-maximum operation.
 * Assume the elements are reals numbers so that you can compare them.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 20.01.17.
 */

public class StackWithMax {
    private Stack<Double> itemsStack = null;

    private Stack<Double> maximumsStack = null;

    /**
     * Constructs an empty stack
     */
    private StackWithMax() {
        itemsStack = new Stack<>();
        maximumsStack = new Stack<>();
    }

    /**
     * Adds new element to the stack
     *
     * @param d an element to be added to the queue.
     */
    private void push(Double d) {
        itemsStack.push(d);

        if (maximumsStack.isEmpty() || d > maximumsStack.peek()) {
            maximumsStack.push(d);
        }
        else {
            maximumsStack.push(maximumsStack.peek());
        }
    }

    /**
     * Removes element from the stack
     *
     * @return An item that was on top of the stask
     */
    private Double pop() {
        maximumsStack.pop();
        return itemsStack.pop();
    }

    /**
     * Returns maximum item stored in the stack
     *
     * @return An item with the maximum value that is stored inside the stack
     */
    private Double max() {
        return maximumsStack.peek();
    }

    public static void main(String[] args) {
        StackWithMax myStack = new StackWithMax();

        myStack.push(3.);
        myStack.push(7.);
        myStack.push(12.);
        System.out.println(myStack.max());
        myStack.pop();
        myStack.push(13.);
        System.out.println(myStack.max());
        myStack.pop();
        System.out.println(myStack.max());
        myStack.pop();
        System.out.println(myStack.max());
    }
}
