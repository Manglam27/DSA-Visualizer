package Src.DSA_visual.sorts;

import java.io.PrintStream;

/**
 * An improved version of the QuickSort algorithm that addresses its limitations.
 * 
 *  @author Manglam Patel
 */
public class QuickSortImproved extends QuickSort {

    private long numberOfRecursiveInvocations = 0;
    private long maximumDepthOfRecursion = 0;

    public long numberOfRecursiveInvocations() {
        return numberOfRecursiveInvocations;
    }

    public long maximumDepthOfRecursion() {
        return maximumDepthOfRecursion;
    }
    /**
     * Constructs a QuickSortImproved object and sorts the array.
     *
     * @param a   the array to be sorted
     * @param out the PrintStream to output the array states
     */
    public QuickSortImproved(int[] a, PrintStream out) {
        super(a, out); // Call the base class constructor
    }

    @Override
    protected void quickSort(int[] a, int start, int end, int depthOfRecursion, PrintStream out) {
        numberOfRecursiveInvocations++;

        if (start < end) {
            // Improved partitioning: 3-way partitioning to handle duplicates efficiently
            int[] pivotBounds = threeWayPartition(a, start, end, out);
            int lt = pivotBounds[0];
            int gt = pivotBounds[1];
            int currentDepth = depthOfRecursion + 1;

            maximumDepthOfRecursion = Math.max(maximumDepthOfRecursion, currentDepth);

            quickSort(a, start, lt - 1, currentDepth, out); // Sort the left part
            quickSort(a, gt + 1, end, currentDepth, out);   // Sort the right part
        }
    }

    /**
     * Partitions the array into three parts: < pivot, = pivot, > pivot.
     * This method improves the handling of duplicate keys.
     *
     * @param a     the array to partition
     * @param start the starting index
     * @param end   the ending index
     * @param out   the PrintStream for output, if any
     * @return the indices of the boundaries for elements equal to the pivot
     */
    private int[] threeWayPartition(int[] a, int start, int end, PrintStream out) {
        int lt = start, gt = end;
        int pivotValue = a[start];
        int i = start;

        while (i <= gt) {
            if (a[i] < pivotValue) {
                swap(a, lt++, i++);
            } else if (a[i] > pivotValue) {
                swap(a, i, gt--);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt}; // Return boundaries of elements equal to pivot
    }
}
