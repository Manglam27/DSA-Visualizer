package Src.DSA_visual.sorts;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * The {@code InsertionSort} class implements the insertion sort algorithm for sorting an array of integers.
 * This class extends the {@code Sort} class and overrides the sort method to perform the insertion sort.
 * 
 * @author Manglam Patel
 */
public class InsertionSort extends Sort {

    /**
     * Constructs a {@code InsertionSort} instance and sorts the specified array using insertion sort.
     * 
     * @param a   an array of integers to be sorted.
     * @param out a {@code PrintStream} for displaying the array's state during the sorting process.
     */
    public InsertionSort(int[] a, PrintStream out) {
        super(a, out);
    }

    /**
     * Sorts the specified array using the insertion sort algorithm.
     * 
     * @param a   an array of integers to be sorted.
     * @param out a {@code PrintStream} for displaying the array's state during the sorting process.
     */
    @Override
    protected void sort(int[] a, PrintStream out) {
    	for (int i = 1; i < a.length; i++) {
            int current = a[i];
            int j = i - 1;

            // Compare 'current' with each element on its left until an element smaller than
            // it is found or until the beginning of the array is reached.
            while (j >= 0) {
            	numberOfComparisons++;  // Comparison is made here.
            	if( a[j] > current) {
            		a[j + 1] = a[j];
            		j--;
            		numberOfArrayElementUpdates++;  // Update is made here for each shift.
            	}
            	else {
            		break;
            	}
            }
            // Placing 'current' after the smaller element or at the beginning of the array.
            a[j + 1] = current;
            numberOfArrayElementUpdates++; // Update for placing 'current' in its correct position.
            
            if (out != null) {
                out.println(Arrays.toString(a));  // Print the array after each insertion
            }
    	}
    }

    /**
     * The main method for testing the insertion sort algorithm.
     * 
     * @param args the program arguments (not used).
     */
    public static void main(String[] args) {
        int[] a = { 5, 3, 1, 2, 4 };
        InsertionSort s = new InsertionSort(a, System.out);
        System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
        System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
        
        // Sorting another array for demonstration
        int[] b = { 7, 6, 5, 1, 2, 3, 4 };
        s = new InsertionSort(b, System.out);
        System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
        System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
    }
}
