/**
 * Problem statement: Shuffling a linked list.
 * Given a singly-linked list containing n items, rearrange the items uniformly at random.
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory
 * and run in time proportional to nlogn in the worst case.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 11.02.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class ShufflingLinkedList {

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private static class List {
        Node first = null;
        Node last = null;
        int size;

        boolean isEmpty() {
            return first == null && last == null;
        }

        int getValue(Node node) {
            if (node == null) {
                throw new NullPointerException();
            }
            return node.value;
        }

        Node getFirst() {
            return first;
        }

        Node getNextTo(Node node) {
            if (node == null) {
                throw new NullPointerException();
            }
            return node.next;
        }

        Node addLast(Node node) {
            if (node == null) {
                throw new NullPointerException();
            }
            if (isEmpty()) {
                first = last = node;
            } else {
                last.next = node;
                last = node;
            }
            ++size;
            return last;
        }

        Node addFirst(Node node) {
            if (node == null) {
                throw new NullPointerException();
            }
            Node temp = first;
            first = node;
            node.next = temp;
            if (isEmpty()) {
                last = first;
            }
            ++size;
            return first;
        }

        Node insertAfter(Node afterNode, Node insertNode) {
            if (insertNode == null) {
                throw new NullPointerException();
            }
            if (afterNode == null) {
                addFirst(insertNode);
            } else {
                Node temp = afterNode.next;
                afterNode.next = insertNode;
                insertNode.next = temp;
                ++size;
            }
            return insertNode;
        }

        void removeFirst() {
            if (isEmpty()) {
                throw new UnsupportedOperationException();
            }
            first = getNextTo(first);
            if (--size == 0) {
                first = last = null;
            }
        }

        void removeNextTo(Node node) {
            if (node == null) {
                throw new NullPointerException();
            }
            Node next = getNextTo(node);
            if (next != null) {
                node.next = getNextTo(next);
            }
            if (--size == 0) {
                first = last = null;
            }
        }

        int getSize() {
            return size;
        }

        void print() {
            System.out.print("[");
            Node current = first;
            for (int i = 0; i < size; ++i) {
                System.out.print(current.value);
                if (i + 1 < size) {
                    System.out.print(", ");
                }
                current = current.next;
            }
            System.out.println("]");
        }
    }

    private static Node merge(List list, Node beforeMergeNode, int length) {

        List firstHalf = new List();
        List secondHalf = new List();
        Node node = beforeMergeNode != null ? list.getNextTo(beforeMergeNode) : list.getFirst();
        int halvesLength = 0;
        for (int i = 0; i < 2 * length; ++i, ++halvesLength) {
            if (node == null) {
                break;
            }

            if (i < length && list.getNextTo(node) != null) {
                firstHalf.addLast(node);
            } else {
                secondHalf.addLast(node);
            }

            node = list.getNextTo(node);

            if (beforeMergeNode == null) {
                list.removeFirst();
            } else {
                list.removeNextTo(beforeMergeNode);
            }
        }

        Node firstHalfNode = firstHalf.getFirst();
        Node secondHalfNode = secondHalf.getFirst();
        Node afterMergeNode = null;
        Node temp = beforeMergeNode;
        for (int k = 0; k < halvesLength; ++k) {
            // Flipping a coin
            boolean doShuffle = StdRandom.uniform(2) != 1;

            if (firstHalf.isEmpty() || !secondHalf.isEmpty() && doShuffle) {
                temp = list.insertAfter(temp, new Node(secondHalf.getValue(secondHalfNode)));
                secondHalfNode = secondHalf.getNextTo(secondHalfNode);
                secondHalf.removeFirst();
            } else {
                temp = list.insertAfter(temp, new Node(firstHalf.getValue(firstHalfNode)));
                firstHalfNode = firstHalf.getNextTo(firstHalfNode);
                firstHalf.removeFirst();
            }

            if (k + 1 == halvesLength) {
                afterMergeNode = temp;
            }
        }

        return afterMergeNode;
    }

    private static void shuffle(List list) {

        for (int length = 1; length < list.getSize(); length *= 2) {

            int lowIndex = 0;
            Node mergeStart = null;

            for (; lowIndex < list.getSize(); lowIndex += 2 * length) {
                mergeStart = merge(list, mergeStart, length);
            }
        }
    }

    public static void main(String[] args) {
        List list = new List();
        Node node = new Node(0);
        list.addLast(node);
        for (int i = 1; i < 50; ++i) {
            node = new Node(i);
            list.addLast(node);
        }

        list.print();
        shuffle(list);
        list.print();
    }
}
