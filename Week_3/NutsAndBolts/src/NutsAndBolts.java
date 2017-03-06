import edu.princeton.cs.algs4.StdRandom;

/**
 * Problem statement: Nuts and bolts.
 * A disorganized carpenter has a mixed pile of n nuts and n bolts.
 * The goal is to find the corresponding pairs of nuts and bolts.
 * Each nut fits exactly one bolt and each bolt fits exactly one nut.
 * By fitting a nut and a bolt together, the carpenter can see which one is bigger (but the carpenter cannot compare
 * two nuts or two bolts directly). Design an algorithm for the problem that uses nlogn compares (probabilistically).
 *
 * Created by upokatik on 06.03.17.
 */

public class NutsAndBolts {

    interface Component {
        char getSize();
    }

    private static class Nut implements Component {
        private char size;

        Nut(char size) {
            this.setSize(size);
        }

        public char getSize() {
            return size;
        }

        public void setSize(char size) {
            this.size = size;
        }
    }

    private static class Bolt implements Component {
        private char size;

        Bolt(char size) {
            this.setSize(size);
        }

        public char getSize() {
            return size;
        }

        public void setSize(char size) {
            this.size = size;
        }
    }

    private static int partition(Component[] components, char size, int low, int high) {
        int i = low;
        int j = high;

        while (true) {
            while (i <= high && components[i].getSize() < size) {
                ++i;
            }
            while (j >= 0 && components[j].getSize() > size) {
                --j;
            }

            if (i > j) {
                break;
            }

            Component temp = components[i];
            components[i] = components[j];
            components[j] = temp;

            i++;
            j--;
        }

        return j;
    }

    private static void sortNutsAndBolts(Nut[] nuts, Bolt[] bolts, int low, int high) {
        while (low < high) {
            // Sorting nuts and determining pivot element index
            char sizeOfBolt = Character.toLowerCase(bolts[low].getSize());
            int pivot = partition(nuts, sizeOfBolt, low, high);

            // Sorting bolts using size of pivot element calculated during sorting of nuts
            char sizeOfNut = Character.toUpperCase(nuts[pivot].getSize());
            pivot = partition(bolts, sizeOfNut, low, high);

            sortNutsAndBolts(nuts, bolts, low, pivot - 1);
            sortNutsAndBolts(nuts, bolts, pivot + 1, high);
        }
    }

    private static void sortNutsAndBolts(Nut[] nuts, Bolt[] bolts) {
        sortNutsAndBolts(nuts, bolts, 0, nuts.length - 1);
    }

    public static void main(String[] args) {
        final int size = 25;

        Nut[] nuts = new Nut[size];
        Bolt[] bolts = new Bolt[size];

        for (int i = 0; i < size; ++i) {
            char nutSize = (char)('a' + i);
            char boltSize = (char)('A' + i);

            nuts[i] = new Nut(nutSize);
            bolts[i] = new Bolt(boltSize);
        }

        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

        sortNutsAndBolts(nuts, bolts);

        boolean isOk = true;
        for (int i = 0; i < size; ++i) {
            if (Character.toLowerCase(nuts[i].getSize()) != Character.toLowerCase(bolts[i].getSize())) {
                isOk = false;
            }

            System.out.print(nuts[i].getSize() + "-" + bolts[i].getSize());
            if (i + 1 < size) {
                System.out.print(", ");
            }
            else {
                System.out.println();
            }
        }

        System.out.println(isOk ? "OK!" : "FAIL!");
    }
}
