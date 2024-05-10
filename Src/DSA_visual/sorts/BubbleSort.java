package Src.DSA_visual.sorts;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * A {@code BubbleSort} instance runs the bubble sort algorithm on the array given to it when it is being constructed.
 * 
 * @author Manglam Patel
 */
public class BubbleSort extends Sort {

	private SortVisualizerCallback callback;

    public BubbleSort(int[] a, PrintStream out, SortVisualizerCallback callback) {
        super(a, out);
        this.callback = callback;
    }

	public BubbleSort(int[] a, PrintStream out) {
		super(a, out);
	}

	protected void sort(int[] a, PrintStream out) {
		System.out.println("inside sort : "+this.callback);

		for (int last = a.length - 1; last >= 1; last--) {
			for (int i = 0; i < last; i++) {
            	numberOfComparisons++;
                if (a[i] > a[i + 1]) {
                    // Swap elements
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    numberOfArrayElementUpdates += 2;
				}
				count++;
				if(count % 10 == 0){
					if (callback != null) {
                        callback.update(a, i, i + 1);
						try {
							Thread.sleep(1);  // Control the speed of the animation
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
                    }
                }
            }
			if (out != null)
				out.println(Arrays.toString(a));
		}
	}
// main method for running the BubbleSort class as a standalone program.
	public static void main(String[] args) {
		int[] a = { 5, 3, 1, 2, 4 };
		BubbleSort s = new BubbleSort(a, System.out);
		s.startSort();
		System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
		System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
		System.out.println(String.format("duration of sorting: %,.3f (milliseconds)", 1.0e-6 * s.duration()));
		System.out.println();

		int[] b = { 7, 6, 5, 1, 2, 3, 4 };
		s = new BubbleSort(b, System.out);
		s.startSort();
		System.out.println(String.format("number of comparisons: %,d", s.numberOfComparisons()));
		System.out.println(String.format("number of array element updates: %,d", s.numberOfArrayElementUpdates()));
		System.out.println(String.format("duration of sorting: %,.3f (milliseconds)", 1.0e-6 * s.duration()));
	}

}
