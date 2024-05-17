package Src.DSA_visual.sorts;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Implements the selection sort algorithm.
 * This class extends {@code Sort}, providing a specific implementation of the sort method
 * to perform selection sort on an array of integers.
 * 
 * @author Manglam Patel
 */
public class SelectionSort extends Sort {

    /**
     * Constructs a {@code SelectionSort} instance and sorts the specified array.
     * 
     * @param a   the {@code int} array to be sorted.
     * @param out the {@code PrintStream} used to show the array's state at each important step.
     */
    public SelectionSort(int[] a, PrintStream out) {
        super(a, out);
    }
    public SelectionSort(int[] a, PrintStream out, SortVisualizerCallback callback, int delay) {
        super(a, out);
        this.callback = callback;
        this.delay = delay;
    }

    /**
     * Sorts the specified array using the selection sort algorithm. This method
     * overrides the abstract sort method defined in the {@code Sort} class.
     * 
     * @param a   the {@code int} array to sort.
     * @param out the {@code PrintStream} to show the array at each important point during sorting.
     */
    @Override
    protected void sort(int[] a, PrintStream out) {
    	for (int i = a.length-1 ; i > 0; i--) {
            // Assume the maximum is the first element of the unsorted part
            int maxIndex = i;
            
            for (int j = i - 1; j >= 0; j--) {
                // Increment comparison count for each comparison made to find the maximum element
                numberOfComparisons++;
                if (a[j] > a[maxIndex]) {
                    maxIndex = j;
                }
            }
            // Swap the found maximum element with the last element of the unsorted part
            if (maxIndex <= i) {
                int temp = a[maxIndex];
                a[maxIndex] = a[i];
                a[i] = temp;
                // Count this as two updates: one for each element involved in the swap
                numberOfArrayElementUpdates += 2;
                if (callback != null) {
                    callback.update(a, i, i + 1);
                    try {
                        Thread.sleep(delay);  // Control the speed of the animation
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }  
            if (out != null) {
                // Output the current state of the array if the PrintStream is provided
                out.println(Arrays.toString(a));
            }
        }
        
    }

    /**
     * Main method to test the selection sort implementation. Demonstrates sorting
     * of a simple integer array and prints the number of comparisons and updates made.
     * 
     * @param args not used.
     */
    public static void main(String[] args) {
        int[] a = { 5, 3, 1, 2, 4 };
        SelectionSort s = new SelectionSort(a, System.out);
        System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
        System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
        System.out.println(String.format("duration of sorting: %,.3f (milliseconds)", 1.0e-6 * s.duration()));
        
        int[] b = { 7, 6, 5, 1, 2, 3, 4 };
        s = new SelectionSort(b, System.out);
        System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
        System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
        System.out.println(String.format("duration of sorting: %,.3f (milliseconds)", 1.0e-6 * s.duration()));
    }

}
