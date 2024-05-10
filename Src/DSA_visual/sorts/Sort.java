package Src.DSA_visual.sorts;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

/**
 * A {@code Sort} instance sorts the array given to it when it is being constructed.
 * 
 * @author Manglam Patel
 */
public abstract class Sort {
	
	long startTime;
	long completionTime;
	long numberOfComparisons = 0;
	long numberOfArrayElementUpdates = 0;

	public Sort(int[] a, PrintStream out) {
		startTime = System.nanoTime();
		sort(a, out);
		completionTime = System.nanoTime();
	}

	abstract protected void sort(int[] a, PrintStream out);


	public long duration() {
		return completionTime - startTime;
	}

	public long numberOfComparisons() {
		return numberOfComparisons;
	}

	public long numberOfArrayElementUpdates() {
		return numberOfArrayElementUpdates;
	}

	public static int[] createSortedArray(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++)
			a[i] = i;
		return a;
	}

	public static int[] shuffle(int[] a, Random r) {
		a = Arrays.copyOf(a, a.length);
		for (int i = 0; i < a.length; i++) {
			int index = (int) (r.nextDouble() * (a.length - i)) + i;
			int temp = a[i];
			a[i] = a[index];
			a[index] = temp;
		}
		return a;
	}
}
