package Src.DSA_visual;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class newGuiSortTest {
    private static int[] readArrayFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            long lines;
            try {
                lines = Files.lines(Paths.get(filename)).count();
            } catch (IOException e) {
                lines = 0;
                e.printStackTrace();
            }
            System.out.println("Total lines: " + lines);
            
            int size = (int)lines;
            
            int[] array = new int[size];
            int i = 0;
            System.out.println("Reading array from file:");
            while(scanner.hasNextInt()){ // Fix: Change the condition to check if there is an integer available to read
                array[i] = scanner.nextInt(); // Fix: Use nextInt() to read the integer
                System.out.println("Read " + i + " elements. Current element: " + array[i]);
                i++;
            }
            System.out.println("Array was read successfully.");
            scanner.close();
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private static void displayArray(int[] array) {
        Frame frame = new Frame("Sorted Array");
        frame.setSize(300, 200);

        List list = new List();
        for (int i = 0; i < array.length; i++) {
            list.add(String.valueOf(array[i]));
        }

        frame.add(list);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        int[] array = readArrayFromFile("data_dump.txt");
        System.out.println("Array was fetched:");
        bubbleSort(array);
        displayArray(array);
    }
}
