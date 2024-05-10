package Src.DSA_visual.sorts;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Implements the QuickSort algorithm extending a base Sort class.
 * 
 *  @author Manglam patel
 */
public class QuickSort extends Sort { // Assuming Sort is a class you have that QuickSort should extend.

    private long numberOfRecursiveInvocations = 0;
    private long maximumDepthOfRecursion = 0;

    /**
     * Constructs a QuickSort object and sorts the array.
     *
     * @param a   the array to be sorted
     * @param out the PrintStream to output the array states
     */
    public QuickSort(int[] a, PrintStream out) {
        super(a, out); // Assuming the Sort class constructor.
        quickSort(a, 0, a.length - 1, 1, out);
    }

    @Override
    protected void sort(int[] a, PrintStream out) {
    }

    /**
     * Recursively sorts the array using the Quicksort algorithm.
     *
     * @param a               the array to sort
     * @param start           the starting index
     * @param end             the ending index
     * @param depthOfRecursion the current depth of recursion
     * @param out             the PrintStream for output
     */
    protected void quickSort(int[] a, int start, int end, int depthOfRecursion, PrintStream out) {
    
    	numberOfRecursiveInvocations++;
 
    	if (start < end) {
			int pivotPoint = partition(a, start, end, null);
			int currentDepth = depthOfRecursion +1;
			
	    	maximumDepthOfRecursion = Math.max(maximumDepthOfRecursion, currentDepth);
			
			if (out != null) // do NOT delete this statement
				out.println(Arrays.toString(a));

			quickSort(a, start, pivotPoint - 1, currentDepth, out);
	        quickSort(a, pivotPoint + 1, end, currentDepth,out);
		}
    }

    /**
     * Partitions the array around a pivot.
     *
     * @param a     the array to partition
     * @param start the starting index
     * @param end   the ending index
     * @param out   the PrintStream for output
     * @return the index of the pivot after partitioning
     */
    private int partition(int[] a, int start, int end, PrintStream out) {
    	 	 	
    	int pivotValue = a[start];
		int endOfLeftList = start;
		if (out != null) {
            out.println(Arrays.toString(a));  // Print the array after each insertion
        }
		
		for (int scan = start + 1; scan <= end; scan++) {
			numberOfComparisons++;
			if (a[scan] < pivotValue) {
				endOfLeftList++;
                swap(a, endOfLeftList, scan);
            }
			if (out != null) {
                out.println(Arrays.toString(a));  // Print the array after each insertion
            }
		}
		
		swap(a, start, endOfLeftList);
	
		if (out != null) {
            out.println(Arrays.toString(a));  // Print the array after each insertion
        }
			
		return endOfLeftList;
    }

    /**
     * Swaps two elements in an array.
     *
     * @param a the array
     * @param i the index of the first element
     * @param j the index of the second element
     */
    protected void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        numberOfArrayElementUpdates += 2;
    }

    public long numberOfRecursiveInvocations() {
        return numberOfRecursiveInvocations;
    }

    public long maximumDepthOfRecursion() {
        return maximumDepthOfRecursion;
    }

    /**
     * The main method for testing the quick sort algorithm.
     * 
     * @param args the program arguments (not used).
     */
    public static void main(String[] args) {
        
    	int[] a = { 5, 3, 1, 2, 4 };
        QuickSort s = new QuickSort(a, System.out);
        System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
        System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
        
    	System.out.println("Number of recursive invocations: " + s.numberOfRecursiveInvocations());
        System.out.println("Maximum depth of recursion: " + s.maximumDepthOfRecursion());

        
        int[] b = {7, 6, 5, 1, 2, 3, 4};
        s = new QuickSort(b, System.out);
        System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
        System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
        
    	System.out.println("Number of recursive invocations: " + s.numberOfRecursiveInvocations());
        System.out.println("Maximum depth of recursion: " + s.maximumDepthOfRecursion());

    }
    
    
}
